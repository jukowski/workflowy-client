
package com.github.jucovschi.model;

import java.util.List;

public class MainProjectTreeInfo{
   	private Number dateJoinedTimestampInSeconds;
   	private String initialMostRecentOperationTransactionId;
   	private Number initialPollingIntervalInMs;
   	private boolean isReadOnly;
   	private Number itemsCreatedInCurrentMonth;
   	private Number monthlyItemQuota;
   	private String rootProject;
   	private List<Ch> rootProjectChildren;
   	private List<String> serverExpandedProjectsList;

 	public Number getDateJoinedTimestampInSeconds(){
		return this.dateJoinedTimestampInSeconds;
	}
	public void setDateJoinedTimestampInSeconds(Number dateJoinedTimestampInSeconds){
		this.dateJoinedTimestampInSeconds = dateJoinedTimestampInSeconds;
	}
 	public String getInitialMostRecentOperationTransactionId(){
		return this.initialMostRecentOperationTransactionId;
	}
	public void setInitialMostRecentOperationTransactionId(String initialMostRecentOperationTransactionId){
		this.initialMostRecentOperationTransactionId = initialMostRecentOperationTransactionId;
	}
 	public Number getInitialPollingIntervalInMs(){
		return this.initialPollingIntervalInMs;
	}
	public void setInitialPollingIntervalInMs(Number initialPollingIntervalInMs){
		this.initialPollingIntervalInMs = initialPollingIntervalInMs;
	}
 	public boolean getIsReadOnly(){
		return this.isReadOnly;
	}
	public void setIsReadOnly(boolean isReadOnly){
		this.isReadOnly = isReadOnly;
	}
 	public Number getItemsCreatedInCurrentMonth(){
		return this.itemsCreatedInCurrentMonth;
	}
	public void setItemsCreatedInCurrentMonth(Number itemsCreatedInCurrentMonth){
		this.itemsCreatedInCurrentMonth = itemsCreatedInCurrentMonth;
	}
 	public Number getMonthlyItemQuota(){
		return this.monthlyItemQuota;
	}
	public void setMonthlyItemQuota(Number monthlyItemQuota){
		this.monthlyItemQuota = monthlyItemQuota;
	}
 	public String getRootProject(){
		return this.rootProject;
	}
	public void setRootProject(String rootProject){
		this.rootProject = rootProject;
	}
 	public List<Ch> getRootProjectChildren(){
		return this.rootProjectChildren;
	}
	public void setRootProjectChildren(List<Ch> rootProjectChildren){
		this.rootProjectChildren = rootProjectChildren;
	}
 	public List<String> getServerExpandedProjectsList(){
		return this.serverExpandedProjectsList;
	}
	public void setServerExpandedProjectsList(List<String> serverExpandedProjectsList){
		this.serverExpandedProjectsList = serverExpandedProjectsList;
	}
}
