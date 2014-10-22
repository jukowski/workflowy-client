package com.github.jucovschi.workflowy;

import com.github.jucovschi.model.snapshot.TaskTree;

public class WorkflowyMain {
	
	public static void main(String[] args) throws Exception {
		WorkflowyClient client = new WorkflowyClient();
		client.start();

		client.setCookie("sessionid=");

		TaskTree tree = client.getStatus();
		client.getUpdates();
	}
	
}
