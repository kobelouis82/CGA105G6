package com.title.model;

import java.util.List;
import com.access.model.*;

public class TitleService {
	private TitleDAO_interface dao;

	public TitleService() {
		dao = new TitleJDBCDAO();
	}

	public TitleVO addTitle(String adminTitle) {

		TitleVO TitleVO = new TitleVO();
		TitleVO.setAdminTitle(adminTitle);
		
		return TitleVO;
	}

	public TitleVO updateTitle(Integer adminTitleNo, String adminTitle) {

		TitleVO TitleVO = new TitleVO();
		TitleVO. setAdminTitleNo(adminTitleNo);
		TitleVO. setAdminTitle(adminTitle);
		
		dao.update(TitleVO);
		return TitleVO;
	}

	public void deleteTitle(Integer adminTitleNo) {
		dao.delete(adminTitleNo);
	}

	public TitleVO getOneTitle(Integer adminTitleNo) {
		return dao.findByPrimaryKey(adminTitleNo);
	}

	public List<TitleVO> getAll() {
		return dao.getAll();
	}
}
