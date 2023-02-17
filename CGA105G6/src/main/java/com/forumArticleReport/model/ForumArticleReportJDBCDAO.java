package com.forumArticleReport.model;

import java.util.*;

import java.sql.*;

public class ForumArticleReportJDBCDAO implements ForumArticleReportDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO forum_article_report (ARTICLE_NO,MEM_NO,ARTICLE_REPORT_STATE,REPORT_REASON,ARTICLE_REPORT_result) VALUES (?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM forum_article_report order by ARTICLE_REPORT_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM forum_article_report where ARTICLE_REPORT_NO = ?";
	private static final String DELETE = "DELETE FROM forum_article_report where ARTICLE_REPORT_NO = ?";
	private static final String UPDATE = "UPDATE forum_article_report set ARTICLE_REPORT_STATE=? ,ARTICLE_REPORT_result=? where ARTICLE_REPORT_NO = ?";

	@Override
	public void insert(ForumArticleReportVO forumArticleReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, forumArticleReportVO.getArticleNo());
			pstmt.setInt(2, forumArticleReportVO.getMemNo());
			pstmt.setInt(3, forumArticleReportVO.getArticleReportState());
			pstmt.setString(4, forumArticleReportVO.getReportReason());
			pstmt.setInt(5, forumArticleReportVO.getArticleReportResult());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ForumArticleReportVO forumArticleReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forumArticleReportVO.getArticleReportState());
//			pstmt.setString(2, forumArticleReportVO.getReportReason());
			pstmt.setInt(2, forumArticleReportVO.getArticleReportResult());
			pstmt.setInt(3, forumArticleReportVO.getArticleReportNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer articleReportNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleReportNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ForumArticleReportVO findByPrimaryKey(Integer articleReportNo) {

		ForumArticleReportVO forumArticleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleReportNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				forumArticleReportVO = new ForumArticleReportVO();

				forumArticleReportVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticleReportVO.setMemNo(rs.getInt("MEM_NO"));
				forumArticleReportVO.setArticleReportState(rs.getInt("ARTICLE_REPORT_STATE"));
				forumArticleReportVO.setReportReason(rs.getString("REPORT_REASON"));
				forumArticleReportVO.setReportTime(rs.getTimestamp("REPORT_Time"));
				forumArticleReportVO.setArticleReportResult(rs.getInt("ARTICLE_REPORT_result"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return forumArticleReportVO;
	}

	@Override
	public List<ForumArticleReportVO> getAll() {
		List<ForumArticleReportVO> list = new ArrayList<ForumArticleReportVO>();
		ForumArticleReportVO forumArticleReportVO = null;

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

				forumArticleReportVO = new ForumArticleReportVO();
				forumArticleReportVO.setArticleReportNo(rs.getInt("ARTICLE_REPORT_NO"));
				forumArticleReportVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticleReportVO.setMemNo(rs.getInt("MEM_NO"));
				forumArticleReportVO.setArticleReportState(rs.getInt("ARTICLE_REPORT_STATE"));
				forumArticleReportVO.setReportReason(rs.getString("REPORT_REASON"));
				forumArticleReportVO.setReportTime(rs.getTimestamp("REPORT_Time"));
				forumArticleReportVO.setArticleReportResult(rs.getInt("ARTICLE_REPORT_result"));

				list.add(forumArticleReportVO);// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) {
		
		ForumArticleReportJDBCDAO dao = new ForumArticleReportJDBCDAO();
		
		// �s�W
//		ForumArticleReportVO forumArticleReportVO1 = new ForumArticleReportVO();
//		forumArticleReportVO1.setArticleNo(1);
//		forumArticleReportVO1.setMemNo(1);
//		forumArticleReportVO1.setArticleReportState(1);
//		forumArticleReportVO1.setArticleReportResult(1);
//		forumArticleReportVO1.setReportReason("bad word in forum");
//		dao.insert(forumArticleReportVO1);

		// �ק�
		ForumArticleReportVO forumArticleReportVO2 = new ForumArticleReportVO();
		forumArticleReportVO2.setArticleReportNo(4);
//		forumArticleReportVO2.setMemNo(1);
		forumArticleReportVO2.setArticleReportState(1);
		forumArticleReportVO2.setArticleReportResult(1);
//		forumArticleReportVO2.setReportReason("bad word in forum");
		dao.update(forumArticleReportVO2);

		// �R��
//		dao.delete(1);

		// �d��

//		ForumArticleReportVO forumArticleReportVO3 = dao.findByPrimaryKey(1);
//		System.out.print(forumArticleReportVO3.getArticleNo() + ",");
//		System.out.print(forumArticleReportVO3.getMemNo() + ",");
//		System.out.print(forumArticleReportVO3.getArticleReportState() + ",");
//		System.out.print(forumArticleReportVO3.getReportReason());
//
		System.out.println("---------------------");
		List<ForumArticleReportVO> list = dao.getAll();
		for (ForumArticleReportVO aEmp : list) {
			System.out.print(aEmp.getArticleReportNo() + ",");
			System.out.print(aEmp.getArticleNo() + ",");
			System.out.print(aEmp.getMemNo() + ",");
			System.out.print(aEmp.getArticleReportState() + ",");
			System.out.print(aEmp.getArticleReportResult() + ",");
			System.out.print(aEmp.getReportReason() + ",");
			System.out.println();
//
		}
	}
}
