package com.requisitions.model;

import java.util.List;


import com.requisitions_detail.model.Requisitions_DetailVO;

public class RequisitionsService {

	private RequisitionsDAO_interface dao;

	public RequisitionsService() {
		dao = new RequisitionsJDBCDAO();
	}

//	public RequisitionsVO addRequisitions(Integer adminNo, Integer supplyNo, Byte reqStatus, Byte reqPay, Integer totalPrice) {
//
//		RequisitionsVO requisitionsVO = new RequisitionsVO();
//
//		requisitionsVO.setAdminNo(adminNo);
//		requisitionsVO.setSupplyNo(supplyNo);
//		requisitionsVO.setReqStatus(reqStatus);
//		requisitionsVO.setReqPay(reqPay);
//		requisitionsVO.setTotalPrice(totalPrice);
//		dao.insert(requisitionsVO);
//
//		return requisitionsVO;
//	}

	public RequisitionsVO updateRequisitions(Integer reqNo,Integer adminNo, Integer supplyNo, Byte reqStatus, Byte reqPay, Integer totalPrice) {

		RequisitionsVO requisitionsVO = new RequisitionsVO();
		
		requisitionsVO.setReqNo(reqNo);
		requisitionsVO.setAdminNo(adminNo);
		requisitionsVO.setSupplyNo(supplyNo);
		requisitionsVO.setReqStatus(reqStatus);
		requisitionsVO.setReqPay(reqPay);
		requisitionsVO.setTotalPrice(totalPrice);
		dao.update(requisitionsVO);

		return requisitionsVO;
	}

	public void deleteRequisitions(Integer reqNo) {
		dao.delete(reqNo);
	}

	public RequisitionsVO getOneRequisitions(Integer reqNo) {
		return dao.findByPrimaryKey(reqNo);
	}

	public List<RequisitionsVO> getAll() {
		return dao.getAll();
	}
	
	
	public int addReq(RequisitionsVO requisitionsVO) {
		return dao.insertReq(requisitionsVO);
	}
	
	public List<RequisitionsVO> getOneRequisitionsByAdmin(Integer adminNo) {
		return dao.findByAdmin(adminNo);
	}	
	
}


