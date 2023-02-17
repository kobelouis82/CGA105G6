package com.forumArticleReport.model;

import java.util.*;

public interface ForumArticleReportDAO_interface {
	 public void insert(ForumArticleReportVO forumArticleReportVO);
     public void update(ForumArticleReportVO forumArticleReportVO);
     public void delete(Integer articleReportNo);
     public ForumArticleReportVO findByPrimaryKey(Integer articleReportNo);
     public List<ForumArticleReportVO> getAll();
    
    }

