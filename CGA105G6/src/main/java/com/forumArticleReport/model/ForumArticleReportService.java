package com.forumArticleReport.model;

import java.sql.Timestamp;
import java.util.List;


public class ForumArticleReportService {
	private ForumArticleReportDAO_interface dao;

	public ForumArticleReportService() {
		dao = new ForumArticleReportJDBCDAO();
	}

	public ForumArticleReportVO addForumArticleReport(Integer articleNo, Integer memNo, 
			Integer articleReportState, String reportReason) {
		ForumArticleReportVO forumArticleReportVO = new ForumArticleReportVO();
		forumArticleReportVO.setArticleNo(articleNo);
		forumArticleReportVO.setMemNo(memNo);
		forumArticleReportVO.setArticleReportState(articleReportState);
		forumArticleReportVO.setReportReason(reportReason);
		forumArticleReportVO.setArticleReportResult(0);
		dao.insert(forumArticleReportVO);
		return forumArticleReportVO;
	}

	public ForumArticleReportVO updateForumArticleReport(Integer articleReportNo , 
			Integer articleReportState, Integer articleReportResult
			) {
		ForumArticleReportVO forumArticleReportVO = new ForumArticleReportVO();
		forumArticleReportVO.setArticleReportNo(articleReportNo);
		forumArticleReportVO.setArticleReportState(articleReportState);
		forumArticleReportVO.setArticleReportResult(articleReportResult);
		dao.update(forumArticleReportVO);
		return forumArticleReportVO;
	}

	public void deleteForumArticleReport(Integer articleReportNo) {
		dao.delete(articleReportNo);
	}

	public ForumArticleReportVO getOneForumArticleReport(Integer articleReportNo) {
		return dao.findByPrimaryKey(articleReportNo);
	}

	public List<ForumArticleReportVO> getAll() {
		return dao.getAll();
	}
}
