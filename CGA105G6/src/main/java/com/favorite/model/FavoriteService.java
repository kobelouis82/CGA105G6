package com.favorite.model;

import java.util.ArrayList;
import java.util.List;

public class FavoriteService {
	private FavoriteDAO_interface dao;
	
	public FavoriteService() {
		dao = new FavoriteJDBCDAO();
	}
	
	public FavoriteVO addCart(Integer memNo, Integer serialNo, 
			String itemName,Integer itemPrice,Integer itemSale) {
//		List<CartVO> cart = new ArrayList<CartVO>();
//		cart.add(new CartVO(1,cartVO.setMemNo(memNo)));
		
		FavoriteVO cartVO = new FavoriteVO();
		cartVO.setMemNo(memNo);
		cartVO.setSerialNo(serialNo);
		cartVO.setItemName(itemName);
		cartVO.setItemPrice(itemPrice);
		cartVO.setItemSale(itemSale);		
		dao.insert(cartVO);
		return cartVO;
	}
	
	public FavoriteVO updateCart(Integer serialNo, 
			String itemName,Integer itemPrice,Integer itemSale) {
				
		FavoriteVO cartVO = new FavoriteVO();
		cartVO.setSerialNo(serialNo);
		cartVO.setItemName(itemName);
		cartVO.setItemPrice(itemPrice);
		cartVO.setItemSale(itemSale);
		dao.update(cartVO);
		return cartVO;
	}
	public FavoriteVO updateCount(Integer serialNo,Integer itemSale) {
				
		FavoriteVO cartVO = new FavoriteVO();
		cartVO.setSerialNo(serialNo);
		cartVO.setItemSale(itemSale);
		dao.update(cartVO);
		return cartVO;
	}
    public boolean isItemExist(Integer serialNo) {
        
        FavoriteVO cartVO = dao.findBySerialNo(serialNo);
        return cartVO != null;
    }

    public FavoriteVO updateCart(Integer serialNo,Integer itemSale) {		
      FavoriteVO cartVO = dao.findBySerialNo(serialNo);
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
	
	public FavoriteVO getOneCart(Integer memNo) {
		return dao.findByPrimaryKey(memNo);
	}
	
	public List<FavoriteVO> getAll() {
		return dao.getAll();
	}
}
