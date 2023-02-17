package com.admin.model;

import java.io.Serializable;

import com.mysql.cj.jdbc.Blob;

public class AdminVO implements Serializable {
	/**
	 * 
	 */
	private Integer adminNo;
	private String adminName;
	private Integer adminTitleNo;
	private String phone;
	private String mail;
	private String account;
	private String password;
	private byte[] photo;
	private Integer state;

	public com.title.model.TitleVO getTitleVO() {
		com.title.model.TitleService titleSvc = new com.title.model.TitleService();
		com.title.model.TitleVO titleVO = titleSvc.getOneTitle(adminTitleNo);
		return titleVO;
	}

	public Integer getAdminTitleNo() {
		return adminTitleNo;
	}

	public void setAdminTitleNo(Integer adminTitleNo) {
		this.adminTitleNo = adminTitleNo;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
