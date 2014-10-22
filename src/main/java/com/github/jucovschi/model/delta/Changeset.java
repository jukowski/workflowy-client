package com.github.jucovschi.model.delta;

import java.util.ArrayList;
import java.util.List;


public class Changeset {
	String most_recent_operation_transaction_id;
	
	List<Operation> operations;
	
	public Changeset() {
		operations = new ArrayList<Operation>();
	}

	public String getMost_recent_operation_transaction_id() {
		return most_recent_operation_transaction_id;
	}
	
	public List<Operation> getOperations() {
		return operations;
	}
	public void setMost_recent_operation_transaction_id(
			String most_recent_operation_transaction_id) {
		this.most_recent_operation_transaction_id = most_recent_operation_transaction_id;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
}
