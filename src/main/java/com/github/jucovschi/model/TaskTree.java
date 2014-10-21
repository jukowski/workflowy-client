package com.github.jucovschi.model;

import java.util.List;

public class TaskTree{
   	private List globals;
   	private ProjectTreeData projectTreeData;
   	private Settings settings;

 	public List getGlobals(){
		return this.globals;
	}
	public void setGlobals(List globals){
		this.globals = globals;
	}
 	public ProjectTreeData getProjectTreeData(){
		return this.projectTreeData;
	}
	public void setProjectTreeData(ProjectTreeData projectTreeData){
		this.projectTreeData = projectTreeData;
	}
 	public Settings getSettings(){
		return this.settings;
	}
	public void setSettings(Settings settings){
		this.settings = settings;
	}
}
