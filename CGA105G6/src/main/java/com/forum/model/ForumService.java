package com.forum.model;

import java.util.List;


public class ForumService {
	
	private ForumDAO_interface dao;

	public ForumService() {
		dao = new ForumJDBCDAO();
	}

	public ForumVO addForum(Integer adminNo, String forumName) {
		ForumVO forumVO = new ForumVO();
		forumVO.setAdminNo(adminNo);
		forumVO.setForumName(forumName);
		dao.insert(forumVO);
		return forumVO;
	}

	public ForumVO updateForum(Integer forumNo, Integer adminNo, String forumName) {
		ForumVO forumVO = new ForumVO();
		forumVO.setForumNo(forumNo);
		forumVO.setAdminNo(adminNo);
		forumVO.setForumName(forumName);
		dao.update(forumVO);
		return forumVO;
	}
	
	public void deleteForum(Integer forumNo) {
		dao.delete(forumNo);
	}

	public ForumVO getOneForum(Integer forumNo) {
		return dao.findByPrimaryKey(forumNo);
	}

	public List<ForumVO> getAll() {
		return dao.getAll();
	}

}
