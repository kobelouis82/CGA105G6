package com.shop.model;
import java.util.List;
import java.util.Map;


public class ShopService {
	private ShopDAO_interface dao;
	
//	private GoodsFigDAO_interface addPicDAO;
	
	public ShopService() {
		dao = new ShopJDBCDAO();
	}
//	GoodsFigJDBCDAO addPicDAO = new GoodsFigJDBCDAO();

	
	
	public int addShop(ShopVO shopVO) {
//		ShopVO shopVO = new ShopVO();

//		shopVO.setGameCode(gameCode);
//		shopVO.setItemName(itemName);
//		shopVO.setItemPrice(itemPrice);
//		shopVO.setGameClassNo(gameClassNo);
//		shopVO.setGamePlatformNo(gamePlatformNo);
//		shopVO.setComNo(comNo);
//		shopVO.setStatus(status);
//		shopVO.setItemDes(itemDes);
//		shopVO.setInventoryStock(inventoryStock);
//		shopVO.setPreorderQty(preorderQty);
//		shopVO.setItemState(itemState);
		return dao.insert(shopVO);
		
	}

	public ShopVO updateShop(Integer serialNo,String gameCode,String itemName,Integer itemPrice,
			Integer gameClassNo,Integer gamePlatformNo,Integer comNo,Integer status,String itemDes
			,Integer inventoryStock,Integer preorderQty,Integer itemState) {
		
		ShopVO shopVO = new ShopVO();
		shopVO.setSerialNo(serialNo);
		shopVO.setGameCode(gameCode);
		shopVO.setItemName(itemName);
		shopVO.setItemPrice(itemPrice);
		shopVO.setGameClassNo(gameClassNo);
		shopVO.setGamePlatformNo(gamePlatformNo);
		shopVO.setComNo(comNo);
		shopVO.setStatus(status);
		shopVO.setItemDes(itemDes);
		shopVO.setInventoryStock(inventoryStock);
		shopVO.setPreorderQty(preorderQty);
		shopVO.setItemState(itemState);
		dao.update(shopVO);		
		return shopVO;
	}
	public void deleteShop(Integer serialNo) {
		dao.delete(serialNo);
	}
	public ShopVO getOneShop(Integer serialNo) {
		return dao.findByPrimaryKey(serialNo);
	}
	public ShopVO getOneItemName(Integer serialNo) {
		return dao.findOneItemName(serialNo);
	}
	public List<ShopVO> getAll() {
		return dao.getAll();
	}
	public List<ShopVO> getAllGamePlatformNo(Integer gamePlatformNo) {
		return dao.getAllGamePlatformNo(gamePlatformNo);
	}
	public List<ShopVO> getByItemName(String itemName) {
		return dao.getByItemName(itemName);
	}
	public List<ShopVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public ShopVO selectItemInf(String gameCode) {
		return dao.selectItemInf(gameCode);
	}
	
	
}