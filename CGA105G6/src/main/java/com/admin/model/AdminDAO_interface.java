package com.admin.model;

import java.util.List;
import java.util.Map;

public interface AdminDAO_interface {
	public void insert(AdminVO adminVO);
    public void update(AdminVO adminVO);
    public void delete(Integer adminno);
    public AdminVO findByPrimaryKey(Integer adminno);
	public List<AdminVO> findOneACCESS(Integer adminno);
	public List<AdminVO> getFunctionAdminNo(Integer fx_no);
    public List<AdminVO> getAll();
    public List<AdminVO> getAll(Map<String, String[]> map); 
    public AdminVO findByAcAndPwd(String account, String password);
    public AdminVO findByAcAndEmail(String account, String mail);
	public AdminVO findByEmail(String mail);
	public AdminVO findByAccount(String account);
}
