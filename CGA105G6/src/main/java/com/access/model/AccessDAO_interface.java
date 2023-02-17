package com.access.model;

import java.util.List;

import com.access.*;

public interface AccessDAO_interface {
	public void insert(AccessVO accessVO);
	public void update(AccessVO accessVO);
	public void delete(Integer adminNo, Integer adminFunction);
	public AccessVO findByPrimaryKey(Integer adminNo, Integer adminFunction);
	public List<AccessVO> getByAdmin(Integer adminNo);
	public List<AccessVO> getByAccess(Integer adminFunction);
	public List<AccessVO> getAll();

}
