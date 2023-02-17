package com.forumArticlePhoto.model;

import java.util.List;

public class ForumArticlePhotoService {
	private ForumArticlePhotoDAO_interface dao;

	public ForumArticlePhotoService() {
		dao = new ForumArticlePhotoJDBCDAO();
	}

	public ForumArticlePhotoVO addForumArticlePhoto(Integer articleNo, byte[] photo) {
		ForumArticlePhotoVO forumArticlePhotoVO = new ForumArticlePhotoVO();
		forumArticlePhotoVO.setArticleNo(articleNo);
		forumArticlePhotoVO.setPhoto(photo);
		dao.insert(forumArticlePhotoVO);
		return forumArticlePhotoVO;
	}

	public ForumArticlePhotoVO updateForumArticlePhoto(Integer photoNo, Integer articleNo) {
		ForumArticlePhotoVO forumArticlePhotoVO = new ForumArticlePhotoVO();
		forumArticlePhotoVO.setPhotoNo(photoNo);
		forumArticlePhotoVO.setArticleNo(articleNo);
		dao.update(forumArticlePhotoVO);
		return forumArticlePhotoVO;
	}

	public void deleteForumArticlePhoto(Integer photoNo) {
		dao.delete(photoNo);
	}

	public ForumArticlePhotoVO getOneForumArticlePhoto(Integer photoNo) {
		return dao.findByPrimaryKey(photoNo);
	}

	public List<ForumArticlePhotoVO> getAll() {
		return dao.getAll();
	}
	public List<ForumArticlePhotoVO> getByArticleNo(Integer articleNo) {
		return dao.findByArticleNo(articleNo);
	}
}
