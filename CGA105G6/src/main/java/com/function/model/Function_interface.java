package com.function.model;

import java.util.List;

import com.function.model.FunctionVO;

public interface Function_interface {
	public void insert(FunctionVO functionVO);
    public void update(FunctionVO functionVO);
    public void delete(Integer adminFunction);
    public FunctionVO findByPrimaryKey(Integer adminFunction);
    public List<FunctionVO> getAll();
}
