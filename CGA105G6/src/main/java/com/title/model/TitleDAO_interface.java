package com.title.model;

import java.util.List;


public interface TitleDAO_interface {
	public void insert(TitleVO titleVO);
    public void update(TitleVO titleVO);
    public void delete(Integer adminTitleNo);
    public TitleVO findByPrimaryKey(Integer adminTitleNo);
    public List<TitleVO> getAll();
}
