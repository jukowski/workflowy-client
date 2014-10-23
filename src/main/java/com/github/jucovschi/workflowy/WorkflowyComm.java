package com.github.jucovschi.workflowy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jucovschi.model.delta.Changeset;
import com.github.jucovschi.model.delta.response.OpResult;
import com.github.jucovschi.model.delta.response.OpResultContainer;
import com.github.jucovschi.model.snapshot.TaskTree;
import com.github.jucovschi.workflowy.utils.UrlQueryString;

public class WorkflowyComm extends RouteBuilder {
	
	Logger log = LoggerFactory.getLogger(getClass());
	ProducerTemplate producer;
	String cookie = null;
	JacksonDataFormat snapshotFormat = new JacksonDataFormat(TaskTree.class);
	JacksonDataFormat opFormat = new JacksonDataFormat(Changeset.class);
	JacksonDataFormat opResultFormat = new JacksonDataFormat(OpResultContainer.class);
	DateFormat dateFormat;
	
	String most_recent_operation_transaction_id;
	
	int reqID;

	public WorkflowyComm() {
		reqID = 0;
		dateFormat = new SimpleDateFormat("Y-M-d H-m-s.S");
	}

	public void start() throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(this);
		context.start();
		producer = context.createProducerTemplate();
	}
	
	public void login(String user, String password) throws UnsupportedEncodingException {
		user = URLEncoder.encode(user, "UTF-8");
		password = URLEncoder.encode(password, "UTF-8");
		producer.sendBody("direct:login", "username="+user+"&password="+password);
	}
	
	public void markAsDone() {
		
	}
	
	public List<OpResult> sendOp(Changeset []changeSet) {
		return producer.requestBody("direct:sendOp", changeSet, OpResultContainer.class).getResults();
	}
	
	public OpResult getUpdates() {
		Changeset ch = new Changeset();
		ch.setMost_recent_operation_transaction_id(most_recent_operation_transaction_id);
		List<OpResult> results = sendOp(new Changeset[]{ch});
		if (results.size() == 0)
			return null;
		OpResult res = results.get(0);
		most_recent_operation_transaction_id = res.getNew_most_recent_operation_transaction_id();
		return res;
	}
	
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	public TaskTree getSnapshot() {
		TaskTree result = producer.requestBody("direct:getSnapshot", "", TaskTree.class);
		most_recent_operation_transaction_id = result.getProjectTreeData().getMainProjectTreeInfo().getInitialMostRecentOperationTransactionId();
		return result;
	}
	
	Processor getCookies = new Processor() {
		public void process(Exchange exchange) throws Exception {
			cookie = exchange.getIn().getHeader("set-cookie", String.class);
			log.info("logged in with " + cookie);
		}
	};
	
	Processor setCookie = new Processor() {
		public void process(Exchange exchange) throws Exception {
			exchange.getIn().setHeader("cookie", cookie);
		}
	};
	
	Processor genDataRequest = new Processor() {
		public void process(Exchange exchange) throws Exception {
			LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("client_version", "14");
			
			data.put("client_id", dateFormat.format(new Date()));
			data.put("push_poll_id", Long.toHexString(Double.doubleToLongBits(Math.random())));
			data.put("push_poll_data", exchange.getIn().getBody(String.class));
			
			exchange.getIn().setBody(UrlQueryString.buildQueryString(data));
		}
	};
	
	@Override
	public void configure() throws Exception {
		from("direct:login")
			.setHeader(Exchange.HTTP_METHOD, constant("POST"))
			.setHeader(Exchange.CONTENT_TYPE, constant("application/x-www-form-urlencoded"))
			.to("https://workflowy.com/accounts/login/?throwExceptionOnFailure=false")
			.process(getCookies);

		from("direct:getSnapshot")
			.process(setCookie)
			.to("https://workflowy.com/get_initialization_data?client_version=14")
			.unmarshal(snapshotFormat);
				
		from("direct:sendOp")
			.setHeader(Exchange.HTTP_METHOD, constant("POST"))
			.setHeader(Exchange.CONTENT_TYPE, constant("application/x-www-form-urlencoded"))
			.marshal(opFormat)
			.process(genDataRequest)
			.process(setCookie)
			.inOut("https://workflowy.com/push_and_poll")
			.convertBodyTo(String.class)
			.to("log:poll")
			.unmarshal(opResultFormat);
	}
}
