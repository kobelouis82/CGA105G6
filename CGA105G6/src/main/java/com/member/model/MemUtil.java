package com.member.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MemUtil {
	
	public static String getEmpCondition(String columnName, String value) {
		
		String Condition = null;
		
		if ("mem_No".equals(columnName) || "mem_Access".equals(columnName))
			Condition = columnName + "=" + value;
		else if ("mem_Name".equals(columnName)||"mem_Account".equals(columnName))
			Condition = columnName + " like '%" + value + "%'";
//		else if ("mem_dob".equals(columnName))                        
//			Condition = columnName + "=" + "'"+ value +"'";                         
//		    Condition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		System.out.println(Condition+"測試");
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
				String Condition = getEmpCondition(key, value.trim());

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
		map.put("mem_No", new String[] { "1" });
		map.put("mem_name", new String[] { "" });
//		map.put("mem_dob", new String[] { "" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from member_data "
				          + MemUtil.getWhereCondition(map)
				          + "order by mem_No";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}