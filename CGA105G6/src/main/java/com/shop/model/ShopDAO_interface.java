package com.shop.model;
import java.util.*;

public interface ShopDAO_interface {
    public int insert(ShopVO shopVO);
    public void update(ShopVO shopVO);
    public void delete(Integer serialNo);
    public ShopVO findByPrimaryKey(Integer serialNo);
    public ShopVO findOneItemName(Integer serialNo);
    public List<ShopVO> getAll();
    public List<ShopVO> getAllGamePlatformNo(Integer gamePlatformNo);
    public List<ShopVO> getByItemName(String itemName);
    public ShopVO selectItemInf(String gameCode);   
    public List<ShopVO> getAll(Map<String, String[]> map); 
}
