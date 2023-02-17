package com.forumArticleMessage.model;

import java.util.*;

import java.sql.*;

public class ForumArticleMessageJDBCDAO implements ForumArticleMessageDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO forum_article_message(ARTICLE_NO,MEM_NO,MESSAGE_CONTENT,MESSAGE_STATE)VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM forum_article_message order by MESSAGE_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM forum_article_message where MESSAGE_NO = ?";
	private static final String DELETE = "DELETE FROM forum_article_message where MESSAGE_NO = ?";
	private static final String UPDATE = "UPDATE forum_article_message set ARTICLE_NO=?,MEM_NO=?,MESSAGE_CONTENT=?,EDIT_TIME=?,MESSAGE_STATE=? where MESSAGE_NO = ?";
	private static final String GET_ALL_BY_ART = "SELECT * FROM forum_article_message where article_NO = ?";
	@Override
	public void insert(ForumArticleMessageVO forumArticleMessageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, forumArticleMessageVO.getArticleNo());
			pstmt.setInt(2, forumArticleMessageVO.getMemNo());
			pstmt.setString(3, forumArticleMessageVO.getMessageContent());
//			pstmt.setTimestamp(4, forumArticleMessageVO.getEditTime());
			pstmt.setInt(4, forumArticleMessageVO.getMessageState());

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
	public void update(ForumArticleMessageVO forumArticleMessageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forumArticleMessageVO.getArticleNo());
			pstmt.setInt(2, forumArticleMessageVO.getMemNo());
			pstmt.setString(3, forumArticleMessageVO.getMessageContent());
			pstmt.setTimestamp(4, forumArticleMessageVO.getEditTime());
			pstmt.setInt(5, forumArticleMessageVO.getMessageState());
			pstmt.setInt(6, forumArticleMessageVO.getMessageNo());

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
	public void delete(Integer messageNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, messageNo);

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
	public ForumArticleMessageVO findByPrimaryKey(Integer messageNo) {

		ForumArticleMessageVO forumArticleMessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, messageNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				forumArticleMessageVO = new ForumArticleMessageVO();

				forumArticleMessageVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticleMessageVO.setMemNo(rs.getInt("MEM_NO"));
				forumArticleMessageVO.setMessageContent(rs.getString("MESSAGE_CONTENT"));
				forumArticleMessageVO.setEditTime(rs.getTimestamp("EDIT_TIME"));
				forumArticleMessageVO.setMessageState(rs.getInt("MESSAGE_STATE"));

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
		return forumArticleMessageVO;
	}

	@Override
	public List<ForumArticleMessageVO> getAll() {
		List<ForumArticleMessageVO> list = new ArrayList<ForumArticleMessageVO>();
		ForumArticleMessageVO forumArticleMessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
		
			rs = pstmt.executeQuery();
			while (rs.next()) {

				forumArticleMessageVO = new ForumArticleMessageVO();
				forumArticleMessageVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticleMessageVO.setMemNo(rs.getInt("MEM_NO"));
				forumArticleMessageVO.setMessageContent(rs.getString("MESSAGE_CONTENT"));
				forumArticleMessageVO.setEditTime(rs.getTimestamp("EDIT_TIME"));
				forumArticleMessageVO.setMessageState(rs.getInt("MESSAGE_STATE"));
				list.add(forumArticleMessageVO); // Store the row in the list
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
	@Override
	public List<ForumArticleMessageVO> getbyArticle(Integer articleNo) {
		List<ForumArticleMessageVO> list = new ArrayList<ForumArticleMessageVO>();
		ForumArticleMessageVO forumArticleMessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_ART);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				forumArticleMessageVO = new ForumArticleMessageVO();
				forumArticleMessageVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticleMessageVO.setMemNo(rs.getInt("MEM_NO"));
				forumArticleMessageVO.setMessageContent(rs.getString("MESSAGE_CONTENT"));
				forumArticleMessageVO.setEditTime(rs.getTimestamp("EDIT_TIME"));
				forumArticleMessageVO.setMessageState(rs.getInt("MESSAGE_STATE"));
				list.add(forumArticleMessageVO); // Store the row in the list
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
		
	
		ForumArticleMessageJDBCDAO dao = new ForumArticleMessageJDBCDAO();
		ForumArticleMessageVO forumArticleMessageVO1 = new ForumArticleMessageVO();
//		forumArticleMessageVO1.setArticleNo(1);
//		forumArticleMessageVO1.setMemNo(1);
//		forumArticleMessageVO1.setMessageContent("69696969");
//		forumArticleMessageVO1.setEditTime(java.sql.Timestamp.valueOf("2444-02-19 00:00:00"));
//		forumArticleMessageVO1.setMessageState(1);
//		dao.insert(forumArticleMessageVO1);

//		// �ק�
//		ForumArticleMessageVO forumArticleMessageVO2 = new ForumArticleMessageVO();
//		forumArticleMessageVO2.setArticleNo(1);
//		forumArticleMessageVO2.setMemNo(1);
//		forumArticleMessageVO2.setMessageContent("fk...tired");
//		forumArticleMessageVO2.setEditTime(java.sql.Timestamp.valueOf("2005-02-20 00:00:00"));
//		forumArticleMessageVO2.setMessageState(1);
//		forumArticleMessageVO2.setMessageNo(8);
//		dao.update(forumArticleMessageVO2);
//
//		// �R��
//		dao.delete(7);
//		
//		// �d��
//		ForumArticleMessageVO forumArticleMessageVO3 = dao.findByPrimaryKey(1);
//		System.out.print(forumArticleMessageVO3.getArticleNo() + ",");
//		System.out.print(forumArticleMessageVO3.getMemNo() + ",");
//		System.out.print(forumArticleMessageVO3.getMessageContent() + ",");
//		System.out.print(forumArticleMessageVO3.getEditTime() + ",");
//		System.out.print(forumArticleMessageVO3.getMessageState());
//		
//		System.out.println("---------------------");
//List<ForumArticleMessageVO> list = dao.getAll();
		List<ForumArticleMessageVO> list = dao.getbyArticle(1);
		for (ForumArticleMessageVO aEmp : list) {
			System.out.print("文章編號"+aEmp.getArticleNo() + ",");
			System.out.print("會員編號"+aEmp.getMemNo() + ",");
			System.out.print(aEmp.getMessageContent() + ",");
			System.out.print(aEmp.getEditTime() + ",");
			System.out.print(aEmp.getMessageState() + ",");
			System.out.println("---------------------");

			System.out.println();
		}
//		// �d��
//		List<ForumArticleMessageVO> list2 = dao.getAll();
//		
//		for (ForumArticleMessageVO aEmp : list2) {
//			System.out.print("文章編號"+aEmp.getArticleNo() + ",");
//			System.out.print("會員編號"+aEmp.getMemNo() + ",");
//			System.out.print(aEmp.getMessageContent() + ",");
//			System.out.print(aEmp.getEditTime() + ",");
//			System.out.print(aEmp.getMessageState() + ",");
//			System.out.println("---------------------");
//
//			System.out.println();
//		}
	}
}
