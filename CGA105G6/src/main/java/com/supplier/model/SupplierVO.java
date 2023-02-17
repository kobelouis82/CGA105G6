package com.supplier.model;


public class SupplierVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer supplyNo;
	private String supplyName;
	private String  supplyContact;
	private String  supplyPhone;
	private String  supplyAddress;
	
	public Integer getSupplyNo() {
		return supplyNo;
	}
	public void setSupplyNo(Integer supplyNo) {
		this.supplyNo = supplyNo;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public String getSupplyContact() {
		return supplyContact;
	}
	public void setSupplyContact(String supplyContact) {
		this.supplyContact = supplyContact;
	}
	public String getSupplyPhone() {
		return supplyPhone;
	}
	public void setSupplyPhone(String supplyPhone) {
		this.supplyPhone = supplyPhone;
	}
	public String getSupplyAddress() {
		return supplyAddress;
	}
	public void setSupplyAddress(String supplyAddress) {
		this.supplyAddress = supplyAddress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	



}
