package com.forumArticlePhoto.model;

import java.sql.Blob;

public class ForumArticlePhotoVO implements java.io.Serializable{
	private Integer photoNo;
	public Integer getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(Integer photoNo) {
		this.photoNo = photoNo;
	}
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	private Integer articleNo;
	private byte[] photo;
	
	
	
	

	
	
	
}
