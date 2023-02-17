package com.shop.model;

public class ShopVO implements java.io.Serializable{
	private Integer serialNo;
	private String gameCode;
	private String itemName;
	private Integer itemPrice;
	private Integer gameClassNo;
	private Integer gamePlatformNo;
	private Integer comNo;
	private Integer status;
	private String itemDes;
	private Integer inventoryStock;
	private Integer preorderQty;
	private Integer itemState;
	
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
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
	public Integer getGameClassNo() {
		return gameClassNo;
	}
	public void setGameClassNo(Integer gameClassNo) {
		this.gameClassNo = gameClassNo;
	}
	public Integer getGamePlatformNo() {
		return gamePlatformNo;
	}
	public void setGamePlatformNo(Integer gamePlatformNo) {
		this.gamePlatformNo = gamePlatformNo;
	}
	public Integer getComNo() {
		return comNo;
	}
	public void setComNo(Integer comNo) {
		this.comNo = comNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getItemDes() {
		return itemDes;
	}
	public void setItemDes(String itemDes) {
		this.itemDes = itemDes;
	}
	public Integer getInventoryStock() {
		return inventoryStock;
	}
	public void setInventoryStock(Integer inventoryStock) {
		this.inventoryStock = inventoryStock;
	}
	public Integer getPreorderQty() {
		return preorderQty;
	}
	public void setPreorderQty(Integer preorderQty) {
		this.preorderQty = preorderQty;
	}
	public Integer getItemState() {
		return itemState;
	}
	public void setItemState(Integer itemState) {
		this.itemState = itemState;
	}
//    public com.goodsFig.model.GoodsFigVO getGoodsFigVO() {
//    	com.goodsFig.model.GoodsFigService goodsFigSvc = new com.goodsFig.model.GoodsFigService();
//    	com.goodsFig.model.GoodsFigVO goodsFigVO = goodsFigSvc.getOneGoodsFig(serialNo);
//	    return goodsFigVO;
//    }
}
