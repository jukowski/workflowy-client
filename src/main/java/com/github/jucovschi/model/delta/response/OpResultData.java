package com.github.jucovschi.model.delta.response;

import java.util.List;

public class OpResultData {
	int client_timestamp;
	String ppid;
	int id;
	List<OpResultDataOps> ops;
	
	public OpResultData() {
	}
	
	public int getClient_timestamp() {
		return client_timestamp;
	}
	public int getId() {
		return id;
	}
	public List<OpResultDataOps> getOps() {
		return ops;
	}
	public String getPpid() {
		return ppid;
	}
	public void setClient_timestamp(int client_timestamp) {
		this.client_timestamp = client_timestamp;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOps(List<OpResultDataOps> ops) {
		this.ops = ops;
	}
	public void setPpid(String ppid) {
		this.ppid = ppid;
	}
}
