package com.faqcontent.model;

import java.util.List;
import java.util.Map;


public class FAQContentService {
	private FAQContentDAO_interface dao;

	public FAQContentService() {
		dao = new FAQContentJDBCDAO();
	}

	public FAQContentVO addFAQContent(String faqContent, String ansContent, String fqKeyWord) {

		FAQContentVO FAQContentVO = new FAQContentVO();

		FAQContentVO.setFaqContent(faqContent);
		FAQContentVO.setAnsContent(ansContent);
		FAQContentVO.setFqKeyWord(fqKeyWord);

		dao.insert(FAQContentVO);

		return FAQContentVO;
	}

	public FAQContentVO updateFAQContent(Integer faqno, String faqContent, String ansContent, String fqKeyWord) {

		FAQContentVO faqContentVO = new FAQContentVO();

		faqContentVO.setFaqNo(faqno);
		faqContentVO.setFaqContent(faqContent);
		faqContentVO.setAnsContent(ansContent);
		faqContentVO.setFqKeyWord(fqKeyWord);
		dao.update(faqContentVO);

		return faqContentVO;
	}

	public void deleteFAQContent(Integer faqno) {
		dao.delete(faqno);
	}

	public FAQContentVO getOneFAQContent(Integer faqno) {
		return dao.findByPrimaryKey(faqno);
	}

	public List<FAQContentVO> getAll() {
		return dao.getAll();
	}
	
	public List<FAQContentVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
