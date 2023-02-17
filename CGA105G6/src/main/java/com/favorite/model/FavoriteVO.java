package com.favorite.model;

import java.util.*;

public class FavoriteVO {
	private Integer memNo;
	private Integer serialNo;
	private String itemName;
	private Integer itemPrice;
	private Integer itemSale;
	private Map<Integer, Integer> items;
	public FavoriteVO() {
        this.items = new HashMap<>();
    }
//	private List<CartVO> products = new ArrayList<CartVO>();
//
//	public void addProduct(CartVO cartVO) {
//		products.add(cartVO);
//	}
//	public List<CartVO> getProducts() {
//        return products;
//    }
	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemSale() {
		return itemSale;
	}

	public void setItemSale(Integer itemSale) {
		this.itemSale = itemSale;
	}
//	public static void main(String[] args) {
//		CartVO c1 = new CartVO();
//		System.out.println(c1.getProducts());
//	}
}
