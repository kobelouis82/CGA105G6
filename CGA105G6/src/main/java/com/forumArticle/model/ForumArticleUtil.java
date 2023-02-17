package com.forumArticle.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ForumArticleUtil {
	
public static String getArticleCondition(String columnName, String value) {
		
		String Condition = null;
		
		if ("article_no".equals(columnName))
			Condition = columnName + "=" + value;
		else if ("title".equals(columnName) || "mem_Account".equals(columnName))
			Condition = columnName + " like '%" + value + "%'";

		return Condition + " ";
	}
	
	public static String getWhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String Condition = getArticleCondition(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + Condition);
				else
					whereCondition.append(" and " + Condition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("sort_id", new String[] { "1" });
		map.put("article_title", new String[] { "" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from forum_article "
				          + ForumArticleUtil.getWhereCondition(map)
				          + "order by article_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}

}