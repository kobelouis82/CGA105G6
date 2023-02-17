package com.requisitions_detail.model;

import com.admin.model.AdminVO;
import com.shop.model.ShopVO;

public class Requisitions_DetailVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reqDetailNo;
	private Integer reqNo;
	private Integer serialNo;
	private Byte status;
	private Integer qty;
	private Integer price;
	
	public Integer getReqDetailNo() {
		return reqDetailNo;
	}
	public void setReqDetailNo(Integer reqDetailNo) {
		this.reqDetailNo = reqDetailNo;
	}
	public Integer getReqNo() {
		return reqNo;
	}
	public void setReqNo(Integer reqNo) {
		this.reqNo = reqNo;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

    public ShopVO getShopVO() {
    	com.shop.model.ShopService shopSvc = new com.shop.model.ShopService();
    	com.shop.model.ShopVO shopVO = shopSvc.getOneShop(serialNo);
    	return shopVO;
}
	
}
