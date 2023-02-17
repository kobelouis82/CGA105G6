package com.shpe.model;

import java.util.List;


public class ShpeService {

	private ShpeDAO_interface dao;

	public ShpeService() {
		dao = new ShpeJDBCDAO();
	}

	public ShpeVO addShpe(String gameCode, Integer memNo, byte[] productPhoto, Integer estimate, byte diskBox,
			byte disk, boolean diskFrom, String shpDescription) {

		ShpeVO shpeVO = new ShpeVO();

		shpeVO.setGameCode(gameCode);
		shpeVO.setMemNo(memNo);
		shpeVO.setProductPhoto(productPhoto);
		shpeVO.setEstimate(estimate);
		shpeVO.setDiskBox(diskBox);
		shpeVO.setDisk(disk);
		shpeVO.setDiskFrom(diskFrom);
		shpeVO.setShpDescription(shpDescription);
		dao.insert(shpeVO);

		return shpeVO;
	}

	public ShpeVO updateShpe(String gameCode, Integer memNo, byte[] productPhoto, Integer estimate, byte diskBox,
			byte disk, boolean diskFrom, String shpDescription, Integer applicationNo) {

		ShpeVO shpeVO = new ShpeVO();

		shpeVO.setGameCode(gameCode);
		shpeVO.setMemNo(memNo);
		shpeVO.setProductPhoto(productPhoto);
		shpeVO.setEstimate(estimate);
		shpeVO.setDiskBox(diskBox);
		shpeVO.setDisk(disk);
		shpeVO.setDiskFrom(diskFrom);
		shpeVO.setShpDescription(shpDescription);
		shpeVO.setApplicationNo(applicationNo);
		dao.update(shpeVO);

		return shpeVO;
	}

	public void deleteShpe(Integer applicationNo) {
		dao.delete(applicationNo);
	}

	public ShpeVO getOneShpe(Integer applicationNo) {
		return dao.findByPrimaryKey(applicationNo);
	}

	public List<ShpeVO> getAll() {
		return dao.getAll();
	}

	public ShpeVO selectAppNo(Integer applicationNo) {
		return dao.selectAppNo(applicationNo);
	}

	public ShpeVO recycleAdd(Integer memNo ,String gameCode, String itemName, Integer itemPrice, byte diskBox, byte disk,
			boolean diskFrom, Integer estimate, Integer recycleState) {

		ShpeVO shpeVO = new ShpeVO();
		
		shpeVO.setMemNo(memNo);
		shpeVO.setGameCode(gameCode);
		shpeVO.setItemName(itemName);
		shpeVO.setItemPrice(itemPrice);
		shpeVO.setDiskBox(diskBox);
		shpeVO.setDisk(disk);
		shpeVO.setDiskFrom(diskFrom);
		shpeVO.setEstimate(estimate);
		shpeVO.setRecycleState(recycleState);
		dao.recycleAdd(shpeVO);

		return shpeVO;
	}

	public ShpeVO selectItem(String gameCode) {
		return dao.selectItem(gameCode);
	}

	public List<ShpeVO> selectRecycleInf() { 
		return dao.selectRecycleInf();
	}

	public ShpeVO updateState(Integer applicationNo, String gameCode, String itemName, Byte diskBox, Byte disk, Boolean diskFrom, Integer estimate, Integer recycleState ) {

		ShpeVO shpeVO = new ShpeVO();
		shpeVO.setApplicationNo(applicationNo);
		shpeVO.setGameCode(gameCode);
		shpeVO.setItemName(itemName);
		shpeVO.setDiskBox(diskBox);
		shpeVO.setDisk(disk);
		shpeVO.setDiskFrom(diskFrom);
		shpeVO.setEstimate(estimate);
		shpeVO.setRecycleState(recycleState);
		
		dao.updateState(shpeVO);

		return shpeVO;
	}

	public List<ShpeVO> selectRecycleMem(Integer memNo) {
		return dao.selectRecycleMem(memNo);
	}
	
	public ShpeVO getRecycleState(Integer applicationNo) {
		return dao.getRecycleState(applicationNo);
	}
}
