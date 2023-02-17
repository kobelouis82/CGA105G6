package com.faqcontent.model;

public class FAQContentVO implements java.io.Serializable{
	private Integer faqNo;
	private String faqContent;
	private String ansContent;
	private String fqKeyWord;
	
	public Integer getFaqNo() {
		return faqNo;
	}
	public void setFaqNo(Integer faqNo) {
		this.faqNo = faqNo;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	public String getAnsContent() {
		return ansContent;
	}
	public void setAnsContent(String ansContent) {
		this.ansContent = ansContent;
	}
	public String getFqKeyWord() {
		return fqKeyWord;
	}
	public void setFqKeyWord(String fqKeyWord) {
		this.fqKeyWord = fqKeyWord;
	}
	
	
	
	
}
