package com.github.jucovschi.workflowy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;

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
import com.github.jucovschi.model.snapshot.TaskTree;
import com.github.jucovschi.workflowy.utils.UrlQueryString;

public class WorkflowyClient extends RouteBuilder {
	
	Logger log = LoggerFactory.getLogger(getClass());
	ProducerTemplate producer;
	String cookie = null;
	JacksonDataFormat snapshotFormat = new JacksonDataFormat(TaskTree.class);
	JacksonDataFormat opFormat = new JacksonDataFormat(Changeset.class);
	
	String most_recent_operation_transaction_id;
	int reqID;

	public WorkflowyClient() {
		reqID = 0;
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
	
	public void sendOp(Changeset []changeSet) {
		Object o = producer.requestBody("direct:sendOp", changeSet);
	}
	
	public void getUpdates() {
		Changeset ch = new Changeset();
		ch.setMost_recent_operation_transaction_id(most_recent_operation_transaction_id);
		sendOp(new Changeset[]{ch});
	}
	
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	public TaskTree getStatus() {
		TaskTree result = producer.requestBody("direct:getStatus", "", TaskTree.class);
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
			data.put("client_id", new Date().toGMTString());
			data.put("push_poll_id", "T"+reqID);
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

		from("direct:getStatus")
			.process(setCookie)
			.inOnly("https://workflowy.com/get_initialization_data?client_version=14")
			.unmarshal(snapshotFormat);
		
		from("direct:sendOp")
			.setHeader(Exchange.HTTP_METHOD, constant("POST"))
			.setHeader(Exchange.CONTENT_TYPE, constant("application/x-www-form-urlencoded"))
			.marshal(opFormat)
			.process(genDataRequest)
			.process(setCookie)
			.to("log:sendPolling")
			.inOut("https://workflowy.com/push_and_poll")
			.convertBodyTo(String.class)
			.to("log:polling?showHeaders");
	}
}
