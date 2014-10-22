package com.github.jucovschi.model.snapshot;

import java.util.List;

public class ProjectTreeData{
   	private List auxiliaryProjectTreeInfos;
   	private String clientId;
   	private MainProjectTreeInfo mainProjectTreeInfo;

 	public List getAuxiliaryProjectTreeInfos(){
		return this.auxiliaryProjectTreeInfos;
	}
	public void setAuxiliaryProjectTreeInfos(List auxiliaryProjectTreeInfos){
		this.auxiliaryProjectTreeInfos = auxiliaryProjectTreeInfos;
	}
 	public String getClientId(){
		return this.clientId;
	}
	public void setClientId(String clientId){
		this.clientId = clientId;
	}
 	public MainProjectTreeInfo getMainProjectTreeInfo(){
		return this.mainProjectTreeInfo;
	}
	public void setMainProjectTreeInfo(MainProjectTreeInfo mainProjectTreeInfo){
		this.mainProjectTreeInfo = mainProjectTreeInfo;
	}
}
