package com.requisitions.model;

import java.util.List;

import com.requisitions_detail.model.Requisitions_DetailVO;


public interface RequisitionsDAO_interface {

	public void update(RequisitionsVO requisitionsVO);
	public void delete(Integer requisitionsVO);
	public RequisitionsVO findByPrimaryKey(Integer requisitionsVO);
	public List<RequisitionsVO> getAll();
	public int insertReq(RequisitionsVO requisitionsVO);
	public List<RequisitionsVO> findByAdmin(Integer admin);
}
