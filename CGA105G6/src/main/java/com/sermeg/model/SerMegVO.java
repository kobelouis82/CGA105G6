package com.sermeg.model;

import java.sql.Date;

public class SerMegVO implements java.io.Serializable{
	
	private Integer messageNo;
	private Integer memNo;
	private Integer adminNo;
	private Date messageTime;
	private String messageContent;
	private Integer messageDirections;	
	
	
	public Integer getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(Integer messageNo) {
		this.messageNo = messageNo;
	}
	public Integer getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Integer getMessageDirections() {
		return messageDirections;
	}
	public void setMessageDirections(Integer messageDirections) {
		this.messageDirections = messageDirections;
	}
	public Date getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	
	
	
}
