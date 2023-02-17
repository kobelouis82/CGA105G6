package com.access.model;

public class AccessVO {
	private Integer adminNo;
	private Integer adminFunction;
	public Integer getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}
	public Integer getAdminFunction() {
		return adminFunction;
	}
	public void setAdminFunction(Integer adminFunction) {
		this.adminFunction = adminFunction;
	}
	public com.function.model.FunctionVO getFunctionVO() {
		com.function.model.FunctionService functionSvc = new com.function.model.FunctionService();
		com.function.model.FunctionVO functionVO =functionSvc.getOneFunction(adminFunction);
		return functionVO;
	}
	public com.admin.model.AdminVO getAdminVO() {
		com.admin.model.AdminService adminSvc = new com.admin.model.AdminService();
		com.admin.model.AdminVO adminVO =adminSvc.getOneAdmin(adminNo);
		return adminVO;
	}
}
