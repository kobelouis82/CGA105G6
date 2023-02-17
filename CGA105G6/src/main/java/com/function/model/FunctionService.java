package com.function.model;

import java.util.List;

import com.admin.model.AdminVO;

public class FunctionService {
	private Function_interface dao;

	public FunctionService() {
		dao = new FunctionJDBCDAO();
	}

	public FunctionVO addFunction(String adminFname) {
		FunctionVO functionVO = new FunctionVO();
		functionVO.setAdminFunctionName(adminFname);
		dao.insert(functionVO);
		return functionVO;
	}

	public FunctionVO updateFunction(Integer adminFNo, String adminFname) {
		FunctionVO functionVO = new FunctionVO();
		functionVO.setAdminFunction(adminFNo);
		functionVO.setAdminFunctionName(adminFname);
		dao.update(functionVO);
		return functionVO;
	}

	public void deleteFunction(Integer adminFNo) {
		dao.delete(adminFNo);
	}

	public FunctionVO getOneFunction(Integer adminFNo) {
		return dao.findByPrimaryKey(adminFNo);
	}

	public List<FunctionVO> getAll() {
		return dao.getAll();
	}
}
