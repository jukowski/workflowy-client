package com.github.jucovschi.model.delta;

public class Operation {
	String type;
	OpData data;
	String client_timestamp;
	String undo_data;
	
	public String getClient_timestamp() {
		return client_timestamp;
	}
	public OpData getData() {
		return data;
	}
	public String getType() {
		return type;
	}
	public String getUndo_data() {
		return undo_data;
	}
	public void setClient_timestamp(String client_timestamp) {
		this.client_timestamp = client_timestamp;
	}
	public void setData(OpData data) {
		this.data = data;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUndo_data(String undo_data) {
		this.undo_data = undo_data;
	}
}
