package com.sermeg.model;

import java.util.List;


public class SerMegService {
	
	private SerMegDAO_interface dao;
	
	public SerMegService() {
		dao = new SerMegJDBCDAO();
	}
	
	public SerMegVO addSerMeg(Integer adminNo,Integer memNo,String messageContent) {
//		boolean messageDirections,java.sql.Date messageTime
		
		SerMegVO serMegVO = new SerMegVO();
		serMegVO.setAdminNo(adminNo);
		serMegVO.setMemNo(memNo);
		serMegVO.setMessageContent(messageContent);
//		serMegVO.setMessageDirections(messageDirections);
//		serMegVO.setMessageTime(messageTime);
		dao.insert(serMegVO);
		return serMegVO;
	}
	public SerMegVO updateSerMeg(Integer messageNo,Integer adminNo,Integer memNo,String messageContent) {
//		boolean messageDirections,java.sql.Date messageTime
		SerMegVO serMegVO = new SerMegVO();
		serMegVO.setMessageNo(messageNo);
		serMegVO.setAdminNo(adminNo);
		serMegVO.setMemNo(memNo);
		serMegVO.setMessageContent(messageContent);
//		serMegVO.setMessageDirections(messageDirections);
//		serMegVO.setMessageTime(messageTime);

		dao.update(serMegVO);
		
		return serMegVO;
	}
	public void deleteSerMeg(Integer messageNo) {
		dao.delete(messageNo);
	}
	public SerMegVO getOneSerMeg(Integer messageNo) {
		return dao.findByPrimaryKey(messageNo);
	}
	public List<SerMegVO> getAll() {
		return dao.getAll();
	}

}
