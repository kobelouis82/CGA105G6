package com.requisitions.model;

import java.sql.Timestamp;

import com.admin.model.AdminVO;


public class RequisitionsVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer reqNo;
	private Integer adminNo;
	private Integer supplyNo;
	private Timestamp reqDate;
	private Byte reqStatus;
	private Byte reqPay;
	private Integer totalPrice;
	
	public Integer getReqNo() {
		return reqNo;
	}
	public void setReqNo(Integer reqNo) {
		this.reqNo = reqNo;
	}
	public Integer getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}
	public Integer getSupplyNo() {
		return supplyNo;
	}
	public void setSupplyNo(Integer supplyNo) {
		this.supplyNo = supplyNo;
	}
	public Timestamp getReqDate() {
		return reqDate;
	}
	public void setReqDate(Timestamp reqDate) {
		this.reqDate = reqDate;
	}
	public Byte getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(Byte reqStatus) {
		this.reqStatus = reqStatus;
	}
	
	public Byte getReqPay() {
		return reqPay;
	}
	public void setReqPay(Byte reqPay) {
		this.reqPay = reqPay;
	}
	
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    public com.supplier.model.SupplierVO getSupplierVO() {
    	com.supplier.model.SupplierService supplierSvc = new com.supplier.model.SupplierService();
    	com.supplier.model.SupplierVO supplierVO = supplierSvc.getOneSupplier(supplyNo);
	    return supplierVO;
    }
    
    public AdminVO getAdminVO() {
    	com.admin.model.AdminService adminSvc = new com.admin.model.AdminService();
    	com.admin.model.AdminVO adminVO = adminSvc.getOneAdmin(adminNo);
	    return adminVO;
    }
	

}
