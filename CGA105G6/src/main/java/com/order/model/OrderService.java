package com.order.model;

import java.sql.Timestamp;
import java.util.List;

import com.shop.model.ShopVO;

public class OrderService {
	private OrderDAO_interface dao;

	public OrderService() {
		dao = new OrderJDBCDAO();
	}

//	public OrderVO addOrder(Integer memNo, Integer orderTotal, Integer orderState,
//			Integer orderShip, Integer orderPay) {
//
//		OrderVO orderVO = new OrderVO();
//		orderVO.setMemNo(memNo);		
//		orderVO.setOrderTotal(orderTotal);
//		orderVO.setOrderState(orderState);
//		orderVO.setOrderShip(orderShip);
//		orderVO.setOrderPay(orderPay);
//		dao.insert(orderVO);
//		return orderVO;
//	}
	public int addOrder(OrderVO orderVO) {

//		OrderVO orderVO = new OrderVO();
//		orderVO.setMemNo(memNo);		
//		orderVO.setOrderTotal(orderTotal);
//		orderVO.setOrderState(orderState);
//		orderVO.setOrderShip(orderShip);
//		orderVO.setOrderPay(orderPay);
//		dao.insert(orderVO);
		return dao.insert(orderVO);
	}
	public OrderVO updateOrder(Integer orderNo,Integer memNo, Integer orderTotal, Integer orderState,
			Integer orderShip, Integer orderPay,Timestamp orderTime) {

		OrderVO orderVO = new OrderVO();
		orderVO.setOrderNo(orderNo);
		orderVO.setMemNo(memNo);		
		orderVO.setOrderTotal(orderTotal);
		orderVO.setOrderState(orderState);
		orderVO.setOrderShip(orderShip);
		orderVO.setOrderPay(orderPay);
		orderVO.setOrderTime(orderTime);
	
		
	
		dao.update(orderVO);

		return orderVO;
	}

	public void deleteOrder(Integer orderNo) {
		dao.delete(orderNo);
	}

	public OrderVO getOneOrder(Integer orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	public List<OrderVO> getOneOrderForMemNo(Integer MemNo) {
		return dao.findByMemNo(MemNo);
	}
	public List<OrderVO> getAll() {
		return dao.getAll();
	}
}
