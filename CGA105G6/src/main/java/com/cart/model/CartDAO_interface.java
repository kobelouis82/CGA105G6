package com.cart.model;

import java.util.List;

public interface CartDAO_interface {
	
    public void insert(CartVO CartVO);
    public void update(CartVO CartVO);
    public void updateCount(CartVO CartVO);
    public void delete(Integer memNo);
    public CartVO findByPrimaryKey(Integer memNo);

    public List<CartVO> getAll();
	public CartVO findBySerialNo(Integer serialNo);
}
