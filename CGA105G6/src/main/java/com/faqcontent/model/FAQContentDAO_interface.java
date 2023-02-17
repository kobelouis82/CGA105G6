package com.faqcontent.model;

import java.util.*;


public interface FAQContentDAO_interface {
	public void insert(FAQContentVO faqContentVO);
    public void update(FAQContentVO faqContentVO);
    public void delete(Integer FAQNo);
    public FAQContentVO findByPrimaryKey(Integer FAQNo);
    public List<FAQContentVO> getAll();
    public List<FAQContentVO> getAll(Map<String, String[]> map);
}
