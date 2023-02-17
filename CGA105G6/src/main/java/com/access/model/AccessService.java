package com.access.model;

import java.util.List;
import com.access.model.*;

public class AccessService {
	private AccessDAO_interface dao;

	public AccessService() {
		dao = new AccessJDBCDAO();
	}

	public AccessVO addAccess(Integer adminNo, Integer adminFunction) {

		AccessVO accessVO = new AccessVO();
		accessVO.setAdminNo(adminNo);
		accessVO.setAdminFunction(adminFunction);
		dao.insert(accessVO);
		return accessVO;
	}

	public AccessVO updateAccess(Integer adminNo, Integer adminFunction) {

		AccessVO accessVO = new AccessVO();
		accessVO.setAdminFunction(adminFunction);
		accessVO.setAdminNo(adminNo);
		dao.update(accessVO);
		return accessVO;
	}

	public void deleteAccess(Integer adminNo, Integer adminFunction) {
		dao.delete(adminNo, adminFunction);
	}

	public AccessVO getOneAccess(Integer adminNo, Integer adminFunction) {
		return dao.findByPrimaryKey(adminNo, adminFunction);
	}
	public List <AccessVO> getbyAccess(Integer adminFunction) {
		return dao.getByAccess(adminFunction);
	}
	public List <AccessVO> getbyAdmin(Integer adminNo) {
		return dao.getByAdmin(adminNo);
	}
	public List<AccessVO> getAll() {
		return dao.getAll();
	}
}
