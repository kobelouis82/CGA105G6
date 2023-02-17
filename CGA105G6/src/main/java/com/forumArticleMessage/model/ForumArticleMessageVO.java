package com.forumArticleMessage.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemVO;

public class ForumArticleMessageVO {
	private Integer messageNo;
	private Integer articleNo;
	private Integer memNo;
	private String messageContent;
	private Timestamp editTime;
	private Integer messageState;
	
	
	public Integer getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(Integer messageNo) {
		this.messageNo = messageNo;
	}
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
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
	public Timestamp getEditTime() {
		return editTime;
	}
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
	public Integer getMessageState() {
		return messageState;
	}
	public void setMessageState(Integer messageState) {
		this.messageState = messageState;
	}
	
	  public MemVO getMemVO() {
		    com.member.model.MemService memSvc = new com.member.model.MemService();
		    com.member.model.MemVO memVO = memSvc.getOneMem(memNo);
		    return memVO;
	    }
	
}