package com.orderDetail.model;

import java.sql.Timestamp;
import java.util.List;

import com.shop.model.ShopVO;


public class OrderDetailService {
	private OrderDetailDAO_interface dao;
	public OrderDetailService() {
		dao = new OrderDetailJDBCDAO();
	}
	public OrderDetailVO addOrderDetail(Integer orderNo,Integer serialNo,Integer memNo,Integer itemPrice,
			Integer itemSale,Integer status) {
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		orderDetailVO.setOrderNo(orderNo);
		orderDetailVO.setSerialNo(serialNo);
		orderDetailVO.setMemNo(memNo);
		orderDetailVO.setItemPrice(itemPrice);
		orderDetailVO.setItemSale(itemSale);
		orderDetailVO.setStatus(status);
//		orderDetailVO.setCommentContent(commentContent);
//		orderDetailVO.setCommentTime(commentTime);
//		orderDetailVO.setCommentStar(commentStar);
		dao.insert(orderDetailVO);
		return orderDetailVO;
	}
	public OrderDetailVO updateOrderDetail(Integer status,Integer orderNo) {
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		orderDetailVO.setStatus(status);
		orderDetailVO.setOrderNo(orderNo);
		dao.update(orderDetailVO);
		
		return orderDetailVO;
	}
	public void deleteOrderDetail(Integer orderNo) {
		dao.delete(orderNo);
	}
	public OrderDetailVO getOneOrderDetail(Integer orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	public List<OrderDetailVO> getAll() {
		return dao.getAll();
	}
	public List<OrderDetailVO> getAllOrderNo(Integer orderNo) {
		return dao.getAllOrderNo(orderNo);
	}
}
