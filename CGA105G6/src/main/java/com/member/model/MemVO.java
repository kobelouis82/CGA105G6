package com.member.model;

import java.sql.Date;

public class MemVO {
	private Integer memNo;
	private String memName;
	private String memTel;
	private String memCity;
	private Date memBirth;
	private String memDist;
	private String memAdd;
	private String memMail;
	private String memAccount;
	private String memPassword;
	private byte[] memPhoto;
	private Integer memAccess;
	private Integer articleReport;
	private Integer messageReport;
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemCity() {
		return memCity;
	}
	public void setMemCity(String memCity) {
		this.memCity = memCity;
	}
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public String getMemDist() {
		return memDist;
	}
	public void setMemDist(String memDist) {
		this.memDist = memDist;
	}
	public String getMemAdd() {
		return memAdd;
	}
	public void setMemAdd(String memAdd) {
		this.memAdd = memAdd;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public byte[] getMemPhoto() {
		return memPhoto;
	}
	public void setMemPhoto(byte[] memPhoto) {
		this.memPhoto = memPhoto;
	}
	public Integer getMemAccess() {
		return memAccess;
	}
	public void setMemAccess(Integer memAccess) {
		this.memAccess = memAccess;
	}
	public Integer getArticleReport() {
		return articleReport;
	}
	public void setArticleReport(Integer articleReport) {
		this.articleReport = articleReport;
	}
	public Integer getMessageReport() {
		return messageReport;
	}
	public void setMessageReport(Integer messageReport) {
		this.messageReport = messageReport;
	}
	
	

}
