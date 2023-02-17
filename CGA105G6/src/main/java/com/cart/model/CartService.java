package com.cart.model;

import java.util.ArrayList;
import java.util.List;

public class CartService {
	private CartDAO_interface dao;
	
	public CartService() {
		dao = new CartJDBCDAO();
	}
	
	public CartVO addCart(Integer memNo, Integer serialNo, 
			String itemName,Integer itemPrice,Integer itemSale) {
//		List<CartVO> cart = new ArrayList<CartVO>();
//		cart.add(new CartVO(1,cartVO.setMemNo(memNo)));
		
		CartVO cartVO = new CartVO();
		cartVO.setMemNo(memNo);
		cartVO.setSerialNo(serialNo);
		cartVO.setItemName(itemName);
		cartVO.setItemPrice(itemPrice);
		cartVO.setItemSale(itemSale);		
		dao.insert(cartVO);
		return cartVO;
	}
	
	public CartVO updateCart(Integer serialNo, 
			String itemName,Integer itemPrice,Integer itemSale) {
				
		CartVO cartVO = new CartVO();
		cartVO.setSerialNo(serialNo);
		cartVO.setItemName(itemName);
		cartVO.setItemPrice(itemPrice);
		cartVO.setItemSale(itemSale);
		dao.update(cartVO);
		return cartVO;
	}
	public CartVO updateCount(Integer serialNo,Integer itemSale) {
				
		CartVO cartVO = new CartVO();
		cartVO.setSerialNo(serialNo);
		cartVO.setItemSale(itemSale);
		dao.update(cartVO);
		return cartVO;
	}
    public boolean isItemExist(Integer serialNo) {
        
        CartVO cartVO = dao.findBySerialNo(serialNo);
        return cartVO != null;
    }

    public CartVO updateCart(Integer serialNo,Integer itemSale) {		
      CartVO cartVO = dao.findBySerialNo(serialNo);
		int newItemSale = cartVO.getItemSale() + 1;
		System.out.println("itemSale="+newItemSale);
		cartVO.setSerialNo(serialNo);
		cartVO.setItemSale(newItemSale);		
		dao.update(cartVO);
		return cartVO;
	}
	public void deleteCart(Integer serialNo) {
		dao.delete(serialNo);
	}
	
	public CartVO getOneCart(Integer memNo) {
		return dao.findByPrimaryKey(memNo);
	}
	
	public List<CartVO> getAll() {
		return dao.getAll();
	}
}
