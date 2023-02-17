package com.favoriteArticle.model;

public class ForumFavoriteArticleVO {
	private Integer articleNo;
	private Integer memNo;
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public com.forumArticle.model.ForumArticleVO getForumArticleVO() {
		com.forumArticle.model.ForumArticleService forumArticleSvc = new com.forumArticle.model.ForumArticleService();
		com.forumArticle.model.ForumArticleVO forumArticleVO = forumArticleSvc.getOneForumArticle(articleNo);
		return forumArticleVO;
	}

}
