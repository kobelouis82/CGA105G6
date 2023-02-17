package com.forumArticle.model;

import java.sql.Timestamp;
import java.util.Set;

import com.member.model.MemVO;

public class ForumArticleVO implements java.io.Serializable {
	
	private Integer articleNo;
	private Integer memNo;
	private Integer forumNo;
	private Timestamp publishTime;
	private String content;
	private String title;
	private Timestamp editTime;
	private Integer articleState;
	
	
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
	public Integer getForumNo() {
		return forumNo;
	}
	public void setForumNo(Integer forumNo) {
		this.forumNo = forumNo;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getEditTime() {
		return editTime;
	}
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
	public Integer getArticleState() {
		return articleState;
	}
	public void setArticleState(Integer articleState) {
		this.articleState = articleState;
	}	

	public MemVO getMemVO() {
		    com.member.model.MemService memSvc = new com.member.model.MemService();
		    com.member.model.MemVO memVO = memSvc.getOneMem(memNo);
		    return memVO;
	    }
	public com.forum.model.ForumVO getForumVO() {
			com.forum.model.ForumService forumSvc = new com.forum.model.ForumService();
			com.forum.model.ForumVO forumVO = forumSvc.getOneForum(forumNo);
		    return forumVO;
	    }
	public com.forumArticlePhoto.model.ForumArticlePhotoVO getArticlePhotoVO() {
			com.forumArticlePhoto.model.ForumArticlePhotoService forumArticlePhotoSvc = new com.forumArticlePhoto.model.ForumArticlePhotoService();
			com.forumArticlePhoto.model.ForumArticlePhotoVO forumArticlePhotoVO = forumArticlePhotoSvc.getOneForumArticlePhoto(articleNo);
		    return forumArticlePhotoVO;
		}

}
