package com.github.jucovschi.model.delta;

public class OpData {
	String projectid;
	String parentid;
	String priority;
	
	public String getParentid() {
		return parentid;
	}
	public String getPriority() {
		return priority;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
}
