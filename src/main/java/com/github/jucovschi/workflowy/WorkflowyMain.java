package com.github.jucovschi.workflowy;

import java.util.Scanner;

import com.github.jucovschi.model.delta.response.OpResult;
import com.github.jucovschi.model.snapshot.TaskTree;


public class WorkflowyMain {
	
	public static void main(String[] args) throws Exception {
		WorkflowyComm workflowyComm = new WorkflowyComm();
		workflowyComm.start();
		workflowyComm.setCookie("sessionid=");

		WorkflowyClient client = new WorkflowyClient(workflowyComm);
		client.setListener(new WorkflowyUpdateListener() {
			
			public void run(TaskTree tree, OpResult result) {
				
			}
		});
		client.start();
		
		Scanner keyboard = new Scanner(System.in);
		int myint = keyboard.nextInt();
	}
	
}
