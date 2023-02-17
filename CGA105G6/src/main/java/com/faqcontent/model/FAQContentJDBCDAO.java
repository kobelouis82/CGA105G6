package com.faqcontent.model;

import java.util.*;

import jdbc.util.CompositeQuery.jbdcUtil_CompositeQuery_FAQContent;

import java.sql.*;

public class FAQContentJDBCDAO implements FAQContentDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password"; // 7-10�@��s�u

	private static final String INSERT_STMT = 
		"INSERT INTO faq_content (FAQ_CONTENT,ANS_CONTENT,FQKEYWORD) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT FAQ_NO,FAQ_CONTENT,ANS_CONTENT,FQKEYWORD FROM faq_content order by FAQ_NO";
	private static final String GET_ONE_STMT = 
		"SELECT FAQ_NO,FAQ_CONTENT,ANS_CONTENT,FQKEYWORD FROM FAQ_CONTENT where FAQ_NO = ?";
	private static final String DELETE = 
		"DELETE FROM FAQ_CONTENT where FAQ_NO = ?";
	private static final String UPDATE = 
		"UPDATE faq_content set FAQ_CONTENT=?, ANS_CONTENT=?, FQKEYWORD=? where FAQ_NO = ?";

	@Override
	public void insert(FAQContentVO faqContentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			//35-40 object�ݩʶ�JSQL���O
			pstmt.setString(1, faqContentVO.getFaqContent());
			pstmt.setString(2, faqContentVO.getAnsContent());			
			pstmt.setString(3, faqContentVO.getFqKeyWord());
			

			//����SQL���O
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(FAQContentVO faqContentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, faqContentVO.getFaqContent());
			pstmt.setString(2, faqContentVO.getAnsContent());
			pstmt.setString(3, faqContentVO.getFqKeyWord());
			pstmt.setInt(4, faqContentVO.getFaqNo());
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer faqNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, faqNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public FAQContentVO findByPrimaryKey(Integer faqNo) {

		FAQContentVO faqContentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, faqNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				faqContentVO = new FAQContentVO();
				faqContentVO.setFaqNo(rs.getInt("FAQ_NO"));
				faqContentVO.setFaqContent(rs.getString("FAQ_CONTENT"));
				faqContentVO.setAnsContent(rs.getString("ANS_CONTENT"));
				faqContentVO.setFqKeyWord(rs.getString("FQKEYWORD"));
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return faqContentVO;
	}

	@Override
	public List<FAQContentVO> getAll() {
		List<FAQContentVO> list = new ArrayList<FAQContentVO>();
		FAQContentVO faqContentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				faqContentVO = new FAQContentVO();
				faqContentVO.setFaqNo(rs.getInt("FAQ_NO"));
				faqContentVO.setFaqContent(rs.getString("FAQ_CONTENT"));
				faqContentVO.setAnsContent(rs.getString("ANS_CONTENT"));
				faqContentVO.setFqKeyWord(rs.getString("FQKEYWORD"));
				list.add(faqContentVO); // Store the row in the list
			}
			
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL error
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<FAQContentVO> getAll(Map<String, String[]> map) {
		List<FAQContentVO> list = new ArrayList<FAQContentVO>();
		FAQContentVO faqContentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
//			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			String finalSQL = "select * from faq_content "
		          + jbdcUtil_CompositeQuery_FAQContent.get_WhereCondition(map)
		          + "order by FAQ_NO";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				faqContentVO = new FAQContentVO();
				faqContentVO.setFaqNo(rs.getInt("FAQ_NO"));
				faqContentVO.setFaqContent(rs.getString("FAQ_CONTENT"));
				faqContentVO.setAnsContent(rs.getString("ANS_CONTENT"));
				faqContentVO.setFqKeyWord(rs.getString("FQKEYWORD"));
				list.add(faqContentVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	public static void main(String[] args) {

		FAQContentJDBCDAO dao = new FAQContentJDBCDAO();

		// �s�W
//		FAQContentVO faqContentVO1 = new FAQContentVO();
//		faqContentVO1.setfaqContent("�P�~�y�O����ɭ�?");
//		faqContentVO1.setansContent("�Ա��Ьݩx�����i");
//		faqContentVO1.setfqKeyWord("�P�~�y");
//		dao.insert(faqContentVO1);

//		// �ק�
//		FAQContentVO faqContentVO2 = new FAQContentVO();
//		faqContentVO2.setfaqContent("����ɭԦ�1111����?");
//		faqContentVO2.setansContent("�Ա��Ѧ�DM");
//		faqContentVO2.setfqKeyWord("1111����");
//		faqContentVO2.setfaqNo(1);
//		
//		dao.update(faqContentVO2);

//		// �R��
//		dao.delete(1);
		

//		// �d��
//		FAQContentVO faqContentVO3 = dao.findByPrimaryKey(12);
//		System.out.print(faqContentVO3.getFaqContent() + ",");
//		System.out.print(faqContentVO3.getAnsContent() + ",");
//		System.out.print(faqContentVO3.getFqKeyWord());
	
		
//		System.out.println("---------------------");

		// �d��
//		List<FAQContentVO> list = dao.getAll();
//		for (FAQContentVO aEmp : list) {
//			System.out.print(aEmp.getFaqNo() + ",");
//			System.out.print(aEmp.getFaqContent() + ",");
//			System.out.print(aEmp.getAnsContent() + ",");
//			System.out.print(aEmp.getFqKeyWord());
//
//			System.out.println();
//		}
	}
}