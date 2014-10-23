package com.github.jucovschi.model.delta.response;

import java.util.List;

public class OpResult {
	String monthly_item_quota;
	int items_created_in_current_month;
	int new_polling_interval_in_ms;
	
	List<String> concurrent_remote_operation_transactions;
	
	boolean error_encountered_in_remote_operations;
	String new_most_recent_operation_transaction_id;
	String server_run_operation_transaction_json;
	public String getMonthly_item_quota() {
		return monthly_item_quota;
	}
	public void setMonthly_item_quota(String monthly_item_quota) {
		this.monthly_item_quota = monthly_item_quota;
	}
	public int getItems_created_in_current_month() {
		return items_created_in_current_month;
	}
	public void setItems_created_in_current_month(int items_created_in_current_month) {
		this.items_created_in_current_month = items_created_in_current_month;
	}
	public int getNew_polling_interval_in_ms() {
		return new_polling_interval_in_ms;
	}
	public void setNew_polling_interval_in_ms(int new_polling_interval_in_ms) {
		this.new_polling_interval_in_ms = new_polling_interval_in_ms;
	}
	public List<String> getConcurrent_remote_operation_transactions() {
		return concurrent_remote_operation_transactions;
	}
	public void setConcurrent_remote_operation_transactions(
			List<String> concurrent_remote_operation_transactions) {
		this.concurrent_remote_operation_transactions = concurrent_remote_operation_transactions;
	}
	public boolean isError_encountered_in_remote_operations() {
		return error_encountered_in_remote_operations;
	}
	public void setError_encountered_in_remote_operations(
			boolean error_encountered_in_remote_operations) {
		this.error_encountered_in_remote_operations = error_encountered_in_remote_operations;
	}
	public String getNew_most_recent_operation_transaction_id() {
		return new_most_recent_operation_transaction_id;
	}
	public void setNew_most_recent_operation_transaction_id(
			String new_most_recent_operation_transaction_id) {
		this.new_most_recent_operation_transaction_id = new_most_recent_operation_transaction_id;
	}
	public String getServer_run_operation_transaction_json() {
		return server_run_operation_transaction_json;
	}
	public void setServer_run_operation_transaction_json(
			String server_run_operation_transaction_json) {
		this.server_run_operation_transaction_json = server_run_operation_transaction_json;
	}
	
	
}
