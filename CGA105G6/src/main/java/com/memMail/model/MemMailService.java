package com.memMail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class MemMailService {
	
	private MemMailDAO_interface dao;
	
	public MemMailService() {
		dao = new MemMailDAO();
	}
	
	public MemMailVO addMemMail(Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat, String mail_cont, String mail_time, String mail_title) {
		
		MemMailVO member_mailVO = new MemMailVO();
		
		member_mailVO.setSendNo(send_no);
		member_mailVO.setRcptNo(rcpt_no);
		member_mailVO.setMailReadStat(mail_read_stat);
		member_mailVO.setMailStat(mail_stat);
		member_mailVO.setMailCont(mail_cont);
		member_mailVO.setMailTime(mail_time);
		member_mailVO.setMailTitle(mail_title);
		dao.insert(member_mailVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return member_mailVO;// no return is OK, but has return may be can do more things
	}
	
	
	public MemMailVO updateMemMail(Integer mail_no, Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat, String mail_cont, String mail_time, String mail_title) {
		
		MemMailVO member_mailVO = new MemMailVO();
		
		member_mailVO.setMailNo(mail_no);
		member_mailVO.setSendNo(send_no);
		member_mailVO.setRcptNo(rcpt_no);
		member_mailVO.setMailReadStat(mail_read_stat);
		member_mailVO.setMailStat(mail_stat);
		member_mailVO.setMailCont(mail_cont);
		member_mailVO.setMailTime(mail_time);
		member_mailVO.setMailTitle(mail_title);
		dao.update(member_mailVO);
		
		return dao.findByPrimaryKey(mail_no);
	}
	
	
	public void deleteMemMail(Integer mail_no) {
		dao.delete(mail_no);
	}

	public MemMailVO getOneMemMail(Integer mail_no) {
		return dao.findByPrimaryKey(mail_no);
	}

	public List<MemMailVO> getAll() {
		return dao.getAll();
	}

	
	
	public List<MemMailVO> getSend(Integer send_no){
		return dao.getSend(send_no);
	}
    public List<MemMailVO> getRcpt(Integer rcpt_no){
    	return dao.getSend(rcpt_no);
    }
    public List<MemMailVO> getStat(Integer mail_stat){
//    	System.out.println("service");
    	return dao.getStat(mail_stat);
    }
}