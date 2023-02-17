package com.shpe.model;



public class ShpeVO implements java.io.Serializable{
	private Integer applicationNo;
	private String gameCode;
	private Integer memNo;
	private byte[] productPhoto;
	private Integer estimate;
	private Byte diskBox;
	private Byte disk;
	private Boolean diskFrom;
	private Integer recycleState;
	private String itemName;
	private Integer itemPrice;
	private String shpDescription;
	
	public Integer getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(Integer applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public byte[] getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}
	public Integer getEstimate() {
		return estimate;
	}
	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}
	public Byte getDiskBox() {
		return diskBox;
	}
	public void setDiskBox(Byte diskBox) {
		this.diskBox = diskBox;
	}
	public Byte getDisk() {
		return disk;
	}
	public void setDisk(Byte disk) {
		this.disk = disk;
	}
	public Boolean getDiskFrom() {
		return diskFrom;
	}
	public void setDiskFrom(Boolean diskFrom) {
		this.diskFrom = diskFrom;
	}
	public Integer getRecycleState() {
		return recycleState;
	}
	public void setRecycleState(Integer recycleState) {
		this.recycleState = recycleState;
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
	public String getShpDescription() {
		return shpDescription;
	}
	public void setShpDescription(String shpDescription) {
		this.shpDescription = shpDescription;
	}

}
