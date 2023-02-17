package com.favorite.model;

import java.util.List;

public interface FavoriteDAO_interface {
	
    public void insert(FavoriteVO CartVO);
    public void update(FavoriteVO CartVO);
    public void updateCount(FavoriteVO CartVO);
    public void delete(Integer memNo);
    public FavoriteVO findByPrimaryKey(Integer memNo);

    public List<FavoriteVO> getAll();
	public FavoriteVO findBySerialNo(Integer serialNo);
}
