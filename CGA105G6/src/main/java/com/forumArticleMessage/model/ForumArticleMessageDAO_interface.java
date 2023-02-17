package com.forumArticleMessage.model;

import java.util.*;

public interface ForumArticleMessageDAO_interface {
	public void insert(ForumArticleMessageVO forumArticleMessageVO);
	 public void update(ForumArticleMessageVO forumArticleMessageVO);
	 public void delete(Integer messageNo);
	 public ForumArticleMessageVO findByPrimaryKey(Integer messageNo);
	 public List<ForumArticleMessageVO> getAll();
	 public List<ForumArticleMessageVO> getbyArticle(Integer articleNo);
}
