package com.shpe.model;

import java.util.*;

public interface ShpeDAO_interface {
	public void insert(ShpeVO shpeVO);
	public void update(ShpeVO shpeVO);
	public void delete(Integer applicationNo);
	public ShpeVO findByPrimaryKey(Integer applicationNo);
	public List<ShpeVO> getAll();
	public ShpeVO selectAppNo(Integer applicationNo);
	public void recycleAdd(ShpeVO shpeVO);
	public ShpeVO selectItem(String gameCode);
	public List<ShpeVO> selectRecycleInf();
	public void updateState(ShpeVO shpeVO);
	public  List<ShpeVO> selectRecycleMem(Integer memNo);
	public ShpeVO getRecycleState(Integer applicationNo);
}
