package com.forumArticle.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.forumArticlePhoto.model.ForumArticlePhotoVO;

public interface ForumArticleDAO_interface {
	public Integer insert(ForumArticleVO forumArticleVO);
    public void update(ForumArticleVO forumArticleVO);
    public void delete(Integer articleNo);
    public void hideArticle(ForumArticleVO forumArticleVO);
    public ForumArticleVO findByPrimaryKey(Integer articleNo);
	public List<ForumArticleVO> getAll();
	public List<ForumArticleVO> getAllArt(Map<String, String[]> map);
	public List<ForumArticleVO> findByMem(Integer memNo);
}
