package com.title.model;

import java.io.Serializable;

public class TitleVO implements Serializable{
	private Integer adminTitleNo;
	private String adminTitle;
	
	public Integer getAdminTitleNo() {
		return adminTitleNo;
	}

	public void setAdminTitleNo(Integer adminTitleNo) {
		this.adminTitleNo = adminTitleNo;
	}

	public String getAdminTitle() {
		return adminTitle;
	}

	public void setAdminTitle(String adminTitle) {
		this.adminTitle = adminTitle;
	}


	
}
