package com.member.model;

import java.util.List;
import java.util.Map;

import com.member.*;

public interface Mem_interface {
	public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(Integer memNo);
    public MemVO findByPrimaryKey(Integer memNo);
    public List<MemVO> getAll();
    public List<MemVO> getAllMem(Map<String, String[]> map);
    public void updateStatus(Integer mem_id, Integer state) ;
    public MemVO checkAccount(String mem_account);
    public MemVO findPassword(String mem_account,String mem_email);
    public MemVO findByAcAndPwd(String memAccount, String memPassword);
}
