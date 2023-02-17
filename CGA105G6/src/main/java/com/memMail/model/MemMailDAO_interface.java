package com.memMail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface MemMailDAO_interface {

	public void insert(MemMailVO member_mailVO);
    public void insertWithMbr (MemMailVO member_mailVO, Connection con);
    public void update(MemMailVO member_mailVO);
    public void delete(Integer mail_no);
    public MemMailVO findByPrimaryKey(Integer mail_no);
    public List<MemMailVO> getAll();
    public List<MemMailVO> getSend(Integer send_no);
    public List<MemMailVO> getRcpt(Integer rcpt_no);
    public List<MemMailVO> getStat(Integer mail_stat);
}