package com.admin.model;

import java.util.List;
import java.util.Map;

import com.access.model.*;

public class AdminService {
	private AdminDAO_interface dao;

	public AdminService() {
		dao = new AdminJDBCDAO();
	}

	public AdminVO addAdmin(String AdminName,
			Integer AdminTitleno,String Phone,String Mail,String Account,
			String Password,byte[] photo, Integer State) {

		AdminVO adminVO = new AdminVO();
		adminVO.setAdminName(AdminName);
		adminVO.setAdminTitleNo(AdminTitleno);
		adminVO.setPhone(Phone);
		adminVO.setMail(Mail);
		adminVO.setAccount(Account);
		adminVO.setPassword(Password);
		adminVO.setPhoto(photo);
		adminVO.setState(State);
		dao.insert(adminVO);
		return adminVO;
	}

	public AdminVO updateAdmin(Integer adminno, String adminName,
			Integer adminTitleno,String phone,String mail,String account,
			String password,byte[] photo, Integer state) {

		AdminVO adminVO = new AdminVO();
		adminVO.setAdminNo(adminno);
		adminVO.setAdminName(adminName);
		adminVO.setAdminTitleNo(adminTitleno);
		adminVO.setPhone(phone);
		adminVO.setMail( mail);
		adminVO.setAccount(account);
		adminVO.setPassword(password);
		adminVO.setPhoto(photo);
		adminVO.setState(state);
		dao.update(adminVO);
		return adminVO;
	}

	public void deleteAdmin(Integer adminno) {
		dao.delete(adminno);
	}

	public AdminVO getOneAdmin(Integer adminno) {
		return dao.findByPrimaryKey(adminno);
	}

	public List<AdminVO> getAll() {
		return dao.getAll();
	}
	public List<AdminVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public AdminVO findByAcAndPwd(String account, String password) {
		return dao.findByAcAndPwd(account, password);
		};
	public AdminVO findByAcAndEmail(String account, String mail) {
			return dao.findByAcAndEmail(account, mail);
			};
	public List<AdminVO> getFunctionAdminNo(Integer adminFunction) {
				return dao.getFunctionAdminNo(adminFunction);
			}
}
