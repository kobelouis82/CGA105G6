package com.favoriteArticle.model;

import java.util.List;

import com.favoriteArticle.*;

public class ForumFavoriteArticleService {
	private ForumFavoriteArticleDAO_interface dao;

	public ForumFavoriteArticleService() {
		dao = new ForumFavoriteArticleJDBCDAO();
	}

	public ForumFavoriteArticleVO addForumFavoriteArticle(Integer memNo, Integer articleNo) {

		ForumFavoriteArticleVO ForumFavoriteArticleVO = new ForumFavoriteArticleVO();

		ForumFavoriteArticleVO.setMemNo(memNo);
		ForumFavoriteArticleVO.setArticleNo(articleNo);

		dao.insert(ForumFavoriteArticleVO);

		return ForumFavoriteArticleVO;
	}


	public void deleteForumFavoriteArticle(Integer memNo, Integer articleNo) {
		dao.delete(memNo, articleNo);

	}
	
	public ForumFavoriteArticleVO getOneFavorite(Integer memNo, Integer articleNo) {
		
		return dao.findByPrimaryKey(memNo, articleNo);
	}

	public List<ForumFavoriteArticleVO> getOneAllFavorite(Integer memNo) {
		return dao.getAllbyMem(memNo);
	}
}
