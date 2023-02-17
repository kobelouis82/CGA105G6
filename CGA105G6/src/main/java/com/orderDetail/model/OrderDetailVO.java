package com.orderDetail.model;

import java.sql.Timestamp;

public class OrderDetailVO {
	private Integer orderNo;
	private Integer serialNo;
	private Integer memNo;
	private Integer itemPrice;
	private Integer itemSale;
	private Integer status;
	private String commentContent;
	private Timestamp commentTime;
	private Integer commentStar;
	private Integer itemName;

	public Integer getItemName() {
		return itemName;
	}

	public void setItemName(Integer itemName) {
		this.itemName = itemName;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer seialNo) {
		this.serialNo = seialNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemSale() {
		return itemSale;
	}

	public void setItemSale(Integer itemSale) {
		this.itemSale = itemSale;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getCommentStar() {
		return commentStar;
	}

	public void setCommentStar(Integer commentStar) {
		this.commentStar = commentStar;
	}

	public com.shop.model.ShopVO getShopVO() {
		com.shop.model.ShopService shopFigSvc = new com.shop.model.ShopService();
		com.shop.model.ShopVO shopVO = shopFigSvc.getOneShop(serialNo);
		return shopVO;
	}
}
