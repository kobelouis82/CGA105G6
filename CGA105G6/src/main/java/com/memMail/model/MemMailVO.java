package com.memMail.model;

import java.io.Serializable;

public class MemMailVO implements Serializable {

	private Integer mailNo;
	private Integer sendNo;
	private Integer rcptNo;
	private Integer mailReadStat; //1已讀 0 未讀
	private Integer mailStat; //1寄出 0 收到
	private String mailCont;
	private String mailTime;
	private String mailTitle;

	public MemMailVO() {
	}

	public MemMailVO( Integer sendNo, Integer rcptNo, Integer mailReadStat, Integer mailStat,
			String mailCont, String mailTime, String mailTitle) {
		super();
		this.sendNo = sendNo;
		this.rcptNo = rcptNo;
		this.mailReadStat = mailReadStat;
		this.mailStat = mailStat;
		this.mailCont = mailCont;
		this.mailTime = mailTime;
		this.mailTitle = mailTitle;
	}

	public Integer getMailNo() {
		return mailNo;
	}

	public void setMailNo(Integer mailNo) {
		this.mailNo = mailNo;
	}

	public Integer getSendNo() {
		return sendNo;
	}

	public void setSendNo(Integer sendNo) {
		this.sendNo = sendNo;
	}

	public Integer getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(Integer rcptNo) {
		this.rcptNo = rcptNo;
	}

	public Integer getMailReadStat() {
		return mailReadStat;
	}

	public void setMailReadStat(Integer mailReadStat) {
		this.mailReadStat = mailReadStat;
	}

	public Integer getMailStat() {
		return mailStat;
	}

	public void setMailStat(Integer mailStat) {
		this.mailStat = mailStat;
	}

	public String getMailCont() {
		return mailCont;
	}

	public void setMailCont(String mailCont) {
		this.mailCont = mailCont;
	}

	public String getMailTime() {
		return mailTime;
	}

	public void setMailTime(String mailTime) {
		this.mailTime = mailTime;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}


}
