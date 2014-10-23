package com.github.jucovschi.workflowy;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jucovschi.model.delta.response.OpResult;
import com.github.jucovschi.model.snapshot.TaskTree;

public class WorkflowyClient extends RouteBuilder {
	WorkflowyComm workflowyComm;
	TaskTree tree;
	
	WorkflowyUpdateListener listener;
	
	public WorkflowyUpdateListener getListener() {
		return listener;
	}
	
	public void setListener(WorkflowyUpdateListener listener) {
		this.listener = listener;
	}

	Logger log = LoggerFactory.getLogger(getClass());

	public WorkflowyClient(WorkflowyComm workflowyComm) {
		this.workflowyComm = workflowyComm;
	}
	
	public void start() throws Exception {
		tree = workflowyComm.getSnapshot();
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(this);
		context.start();
	}
	
	public void applyUpdate(OpResult result) {
		log.info("new id="+result.getNew_most_recent_operation_transaction_id());
		
	}
	
	@Override
	public void configure() throws Exception {
		from("timer:pool?period=10000")
			.bean(method(workflowyComm, "getUpdates"))
			.bean(method(this, "applyUpdate"));
	}
	
}
