package com.github.jucovschi.model;

import java.util.List;

public class Ch{
   	private List<Ch> ch;
   	private String id;
   	private Number lm;
   	private String nm;
   	private String no;

   	public String getNo() {
		return no;
	}
   	
   	public void setNo(String no) {
		this.no = no;
	}
   	
 	public List<Ch> getCh(){
		return this.ch;
	}
	public void setCh(List<Ch> ch){
		this.ch = ch;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public Number getLm(){
		return this.lm;
	}
	public void setLm(Number lm){
		this.lm = lm;
	}
 	public String getNm(){
		return this.nm;
	}
	public void setNm(String nm){
		this.nm = nm;
	}
}
