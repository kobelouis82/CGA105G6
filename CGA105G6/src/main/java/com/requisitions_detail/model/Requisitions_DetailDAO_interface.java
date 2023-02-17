package com.requisitions_detail.model;

import java.util.List;


public interface Requisitions_DetailDAO_interface {

	public int insert(Requisitions_DetailVO requisitions_detailVO);
	public void update(Requisitions_DetailVO requisitions_detailVO);
	public void delete(Integer requisitions_detailVO);
	public Requisitions_DetailVO findByPrimaryKey(Integer requisitions_detailVO);
	public List<Requisitions_DetailVO> getAll();
	public List<Requisitions_DetailVO> getReqDetailByReq(Integer reqNo);
}
