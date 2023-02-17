package com.forumArticlePhoto.model;

import java.sql.Connection;
import java.util.List;

public interface ForumArticlePhotoDAO_interface {
	  public void insert(ForumArticlePhotoVO forumArticlePhotoVO);
      public void update(ForumArticlePhotoVO forumArticlePhotoVO);
      public void delete(Integer photoNo);
      public ForumArticlePhotoVO findByPrimaryKey(Integer photoNo);
      public List<ForumArticlePhotoVO> getAll();
      public List<ForumArticlePhotoVO> findByArticleNo(Integer articleNo);
}
