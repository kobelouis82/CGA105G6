package com.order.model;

import java.sql.Date;
import java.sql.Timestamp;
//import java.util.*;
//import com.order_detail.model.*;


public class OrderVO {
	private Integer orderNo;
	private Integer memNo;
	private Timestamp orderTime;
	private Integer orderTotal;
	private Integer orderState;
	private Integer orderShip;
	private Integer orderPay;
//	private List<OrderDetailVO> orderDetails;
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Integer getOrderShip() {
		return orderShip;
	}
	public void setOrderShip(Integer orderShip) {
		this.orderShip = orderShip;
	}
	public Integer getOrderPay() {
		return orderPay;
	}
	public void setOrderPay(Integer orderPay) {
		this.orderPay = orderPay;
	}
}
