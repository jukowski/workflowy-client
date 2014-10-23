package com.github.jucovschi.model.delta.response;

public class OpResultDataOps {
	String type;
	OpResultDataOpsData data;

	public OpResultDataOpsData getData() {
		return data;
	}
	public String getType() {
		return type;
	}

	public void setData(OpResultDataOpsData data) {
		this.data = data;
	}
	public void setType(String type) {
		this.type = type;
	}
}
