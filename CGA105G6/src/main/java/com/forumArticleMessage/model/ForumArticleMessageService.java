package com.forumArticleMessage.model;

import java.sql.Timestamp;
import java.util.List;

public class ForumArticleMessageService {

	private ForumArticleMessageDAO_interface dao;

	public ForumArticleMessageService() {
		dao = new ForumArticleMessageJDBCDAO();
	}

	public ForumArticleMessageVO addForumArticleMessage(Integer articleNo, Integer memNo, String messageContent,
			Integer messageState  ) {
		ForumArticleMessageVO forumArticleMessageVO = new ForumArticleMessageVO();
		forumArticleMessageVO.setArticleNo(articleNo);
		forumArticleMessageVO.setMemNo(memNo);
		forumArticleMessageVO.setMessageContent(messageContent);
		forumArticleMessageVO.setMessageState(messageState);
		dao.insert(forumArticleMessageVO);
		return forumArticleMessageVO;
	}

	public ForumArticleMessageVO updateForumArticleMessage(Integer messageNo, Integer articleNo, Integer memNo,
			String messageContent,  Integer messageState) {
		ForumArticleMessageVO forumArticleMessageVO = new ForumArticleMessageVO();
		forumArticleMessageVO.setMessageNo(messageNo);
		forumArticleMessageVO.setArticleNo(articleNo);
		forumArticleMessageVO.setMemNo(memNo);
		forumArticleMessageVO.setMessageContent(messageContent);
		forumArticleMessageVO.setMessageState(messageState);
		dao.insert(forumArticleMessageVO);

		dao.update(forumArticleMessageVO);

		return forumArticleMessageVO;
	}

	public void deleteForumArticleMessage(Integer messageNo) {
		dao.delete(messageNo);
	}

	public ForumArticleMessageVO getOneForumArticleMessage(Integer messageNo) {
		return dao.findByPrimaryKey(messageNo);
	}

	public List<ForumArticleMessageVO> getAll() {
		return dao.getAll();
	}
	public List<ForumArticleMessageVO> getByArticle(Integer articleNo) {
		return dao.getbyArticle(articleNo);
	}
}
