package com.github.jucovschi.workflowy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jucovschi.model.TaskTree;

public class WorkflowyClient extends RouteBuilder {
	
	Logger log = LoggerFactory.getLogger(getClass());
	ProducerTemplate producer;
	String cookie = null;
	JacksonDataFormat snapshotFormat = new JacksonDataFormat(TaskTree.class);

	public WorkflowyClient() {
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
	
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	public TaskTree getStatus() {
		return producer.requestBody("direct:getStatus", "", TaskTree.class);
	}
	
	public static void main(String[] args) throws Exception {
		WorkflowyClient client = new WorkflowyClient();
		client.start();

		client.login("someuser", "somepassword");
		// or
		client.setCookie("sessionid=somesession");

		TaskTree tree = client.getStatus();		
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
	}
}
