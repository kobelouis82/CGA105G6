package com.goodsFig.model;

import java.util.List;

public class GoodsFigService {
	private GoodsFigDAO_interface dao;
	
	public GoodsFigService() {
		dao = new GoodsFigJDBCDAO();
	}
	public GoodsFigVO addGoodsFig(Integer figNo,byte[] gamePic,Integer serialNo) {
		
		GoodsFigVO goodsFigVO = new GoodsFigVO();
		goodsFigVO.setFigNo(figNo);
		goodsFigVO.setGamePic(gamePic);
		goodsFigVO.setSerialNo(serialNo);
		dao.insert(goodsFigVO);
		return goodsFigVO;
	}
	public GoodsFigVO addGoodsOneFig(int serialNo,byte[] gamePic) {
		
		GoodsFigVO goodsOneFigVO = new GoodsFigVO();
		goodsOneFigVO.setGamePic(gamePic);
		goodsOneFigVO.setSerialNo(serialNo);
		dao.insert(goodsOneFigVO);
		return goodsOneFigVO;
	}
	public GoodsFigVO updateGoodsFig(Integer figNo,byte[] gamePic,Integer serialNo) {
		
		GoodsFigVO goodsFigVO = new GoodsFigVO();
		goodsFigVO.setFigNo(figNo);
		goodsFigVO.setGamePic(gamePic);
		goodsFigVO.setSerialNo(serialNo);

		dao.update(goodsFigVO);
		
		return goodsFigVO;
	}
	public void deleteGoodsFig(Integer figNo) {
		dao.delete(figNo);
	}
	public GoodsFigVO getOneGoodsFig(Integer serialNo) {
		return dao.findByPrimaryKey(serialNo);
	}
	public List<GoodsFigVO> getAll() {
		return dao.getAll();
	}
}
