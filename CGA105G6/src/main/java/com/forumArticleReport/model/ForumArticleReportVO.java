package com.forumArticleReport.model;

import java.sql.*;

public class ForumArticleReportVO implements java.io.Serializable{
	private Integer articleReportNo;
	private Integer articleNo;
	private Integer memNo;
	private Integer articleReportState;
	private Integer articleReportResult;
	private String reportReason;
	private Timestamp reportTime;
	
	
	public Integer getArticleReportNo() {
		return articleReportNo;
	}
	public void setArticleReportNo(Integer articleReportNo) {
		this.articleReportNo = articleReportNo;
	}
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
	public Integer getArticleReportState() {
		return articleReportState;
	}
	public void setArticleReportState(Integer articleReportState) {
		this.articleReportState = articleReportState;
	}
	public Integer getArticleReportResult() {
		return articleReportResult;
	}
	public void setArticleReportResult(Integer articleReportResult) {
		this.articleReportResult = articleReportResult;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public Timestamp getReportTime() {
		return reportTime;
	}
	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}
	public com.forumArticle.model.ForumArticleVO getArticleVO() {
		com.forumArticle.model.ForumArticleService forumArticleSvc = new com.forumArticle.model.ForumArticleService();
		com.forumArticle.model.ForumArticleVO forumArticlVO = forumArticleSvc.getOneForumArticle(articleNo);
	    return forumArticlVO;
    }
	
	public com.forumArticlePhoto.model.ForumArticlePhotoVO getForumArticlePhotoVO() {
		com.forumArticlePhoto.model.ForumArticlePhotoService forumArticlePhotoSvc = new com.forumArticlePhoto.model.ForumArticlePhotoService();
		com.forumArticlePhoto.model.ForumArticlePhotoVO forumArticlePhotoVO = forumArticlePhotoSvc.getOneForumArticlePhoto(articleNo);
	    return forumArticlePhotoVO;
	}
	
	public com.member.model.MemVO getMemVO() {
		com.member.model.MemService memSvc = new com.member.model.MemService();
		com.member.model.MemVO memVO = memSvc.getOneMem(memNo);
	    return memVO;
    }
	
	
	
	
	
	  

}
