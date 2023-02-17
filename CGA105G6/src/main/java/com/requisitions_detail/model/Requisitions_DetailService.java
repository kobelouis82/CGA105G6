package com.requisitions_detail.model;

import java.util.List;


public class Requisitions_DetailService {

	private Requisitions_DetailDAO_interface dao;

	public Requisitions_DetailService() {
		dao = new Requisitions_DetailJDBCDAO();
	}

	public Requisitions_DetailVO addRequisitions_Detail(Integer reqNo, Integer serialNo, Byte status,
			Integer qty, Integer price) {

		Requisitions_DetailVO requisitions_detailVO = new Requisitions_DetailVO();
		requisitions_detailVO.setReqNo(reqNo);
		requisitions_detailVO.setSerialNo(serialNo);
		requisitions_detailVO.setStatus(status);
		requisitions_detailVO.setQty(qty);
		requisitions_detailVO.setPrice(price);
		dao.insert(requisitions_detailVO);

		return requisitions_detailVO;
	}

	public Requisitions_DetailVO updateRequisitions_Detail(Integer reqNo,Integer reqDetailNo, Integer serialNo, Byte status,
			Integer qty, Integer price) {

		Requisitions_DetailVO requisitions_detailVO = new Requisitions_DetailVO();
		requisitions_detailVO.setReqNo(reqNo);
		requisitions_detailVO.setReqDetailNo(reqDetailNo);
		requisitions_detailVO.setSerialNo(serialNo);
		requisitions_detailVO.setStatus(status);
		requisitions_detailVO.setQty(qty);
		requisitions_detailVO.setPrice(price);
		dao.update(requisitions_detailVO);

		return requisitions_detailVO;
	}


	public void deleteRequisitions_Detail(Integer reqDetailNo) {
		dao.delete(reqDetailNo);
	}

	public Requisitions_DetailVO getOneRequisitions_Detail(Integer reqDetailNo) {
		return dao.findByPrimaryKey(reqDetailNo);
	}

	public List<Requisitions_DetailVO> getAll() {
		return dao.getAll();
	}
	
	public List<Requisitions_DetailVO> getReqDetailByReq(Integer reqNo) {
		return dao.getReqDetailByReq(reqNo);
	}
	

	}
	  
	 


