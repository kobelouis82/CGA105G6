package com.forumArticle.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.forumArticlePhoto.model.ForumArticlePhotoVO;


public class ForumArticleService {
	private ForumArticleDAO_interface dao;

	public ForumArticleService() {
		dao = new ForumArticleJDBCDAO();
	}

	public Integer addForumArticle(Integer memNo, Integer forumNo, String content,
			String title) {
		ForumArticleVO forumArticleVO = new ForumArticleVO();
		forumArticleVO.setMemNo(memNo);
		forumArticleVO.setForumNo(forumNo);
		forumArticleVO.setContent(content);
		forumArticleVO.setTitle(title);
//		forumArticleVO.setArticleState(articleState);
		Integer nextArticleNo =dao.insert(forumArticleVO);

		return nextArticleNo;
	}
	public Integer addForumPost(Integer memNo, Integer forumNo, String content,
			String title) {

		ForumArticleVO forumArticleVO = new ForumArticleVO();

		forumArticleVO.setMemNo(memNo);
		forumArticleVO.setForumNo(forumNo);
		forumArticleVO.setContent(content);
		forumArticleVO.setTitle(title);

		Integer nextForumPostNo = dao.insert(forumArticleVO);

		return nextForumPostNo;
	}

	public ForumArticleVO updateForumArticle(Integer articleNo, Integer memNo, Integer forumNo, String content,
			String title, Integer articleState) {
		ForumArticleVO forumArticleVO = new ForumArticleVO();
		forumArticleVO.setArticleNo(articleNo);
		forumArticleVO.setMemNo(memNo);
		forumArticleVO.setForumNo(forumNo);
		forumArticleVO.setContent(content);
		forumArticleVO.setTitle(title);
		forumArticleVO.setArticleState(articleState);
		dao.update(forumArticleVO);

		return forumArticleVO;
	}
	public ForumArticleVO hideArticle(Integer articleNo,
			Integer articleState, String content) {
		ForumArticleVO forumArticleVO = new ForumArticleVO();
		forumArticleVO.setArticleNo(articleNo);
		forumArticleVO.setContent(content);
		forumArticleVO.setArticleState(articleState);
		dao.hideArticle(forumArticleVO);
		return forumArticleVO;
	}
	public void deleteForumArticle(Integer articleNo) {
		dao.delete(articleNo);
	}
	
	public ForumArticleVO getOneForumArticle(Integer articleNo) {
		return dao.findByPrimaryKey(articleNo);
	}
	
	public List<ForumArticleVO> getAll() {
		return dao.getAll();
	}
	public List<ForumArticleVO> getAllArt(Map<String, String[]> map){
		return dao.getAllArt(map);
	}
	public List<ForumArticleVO> findMyPost(Integer memNo) {
		return dao.findByMem(memNo);
	}
}