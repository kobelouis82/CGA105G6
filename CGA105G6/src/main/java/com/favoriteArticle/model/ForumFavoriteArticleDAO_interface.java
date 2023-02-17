package com.favoriteArticle.model;

import java.util.List;

public interface ForumFavoriteArticleDAO_interface {
    public void insert(ForumFavoriteArticleVO forumfavortiearticleVO);
//    public void update(ForumFavortieArticleVO forumfavortiearticleVO);
    public void delete(Integer articleNo,Integer memNo);
    public ForumFavoriteArticleVO findByPrimaryKey(Integer articleNo, Integer memNo);
    public List<ForumFavoriteArticleVO> getAllbyMem(Integer memNo);
}
