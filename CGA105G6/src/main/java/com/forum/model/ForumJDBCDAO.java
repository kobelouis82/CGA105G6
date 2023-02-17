package com.forum.model;

import java.util.*;

import java.sql.*;

public class ForumJDBCDAO implements ForumDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CGA105G6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO forum (ADMIN_NO,FORUM_NAME) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM forum order by FORUM_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM forum where FORUM_NO = ?";
	private static final String DELETE = "DELETE FROM forum where FORUM_NO = ?";
	private static final String UPDATE = "UPDATE forum set ADMIN_NO=?, FORUM_NAME=? where FORUM_NO = ?";

	@Override
	public void insert(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumVO.getAdminNo());
			pstmt.setString(2, forumVO.getForumName());
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
	public void update(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forumVO.getAdminNo());
			pstmt.setString(2, forumVO.getForumName());
			pstmt.setInt(3, forumVO.getForumNo());
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
	public void delete(Integer forumNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, forumNo);

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
	public ForumVO findByPrimaryKey(Integer forumNo) {

		ForumVO forumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forumNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				forumVO = new ForumVO();
				forumVO.setForumNo(rs.getInt("Forum_NO"));
				forumVO.setAdminNo(rs.getInt("ADMIN_No"));
				forumVO.setForumName(rs.getString("FORUM_NAME"));

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
		return forumVO;
	}

	@Override
	public List<ForumVO> getAll() {
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;

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
				forumVO = new ForumVO();
				forumVO.setForumNo(rs.getInt("Forum_NO"));
				forumVO.setAdminNo(rs.getInt("ADMIN_NO"));
				forumVO.setForumName(rs.getString("FORUM_NAME"));
				list.add(forumVO); // Store the row in the list
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

		ForumJDBCDAO dao = new ForumJDBCDAO();

//		// �s�W
//		ForumVO forumVO1 = new ForumVO();
//		forumVO1.setAdminNo(1);
//		forumVO1.setForumName("forum_6");
//		dao.insert(forumVO1);

//		// �ק�
//		ForumVO forumVO2 = new ForumVO();
//		forumVO2.setAdminNo(1);
//		forumVO2.setForumName("refix_forum_1");
//		forumVO2.setForumNo(7);
//		dao.update(forumVO2);
//
//		// �R��
//		dao.delete(7);
//
//		// �d��
		ForumVO forumVO3 = dao.findByPrimaryKey(1);
		System.out.print(forumVO3.getAdminNo() + ",");
		System.out.print(forumVO3.getForumName());
		System.out.print("---------------------------------------");
//
//		// �d��
		List<ForumVO> list = dao.getAll();
		for (ForumVO aEmp : list) {
			System.out.print(aEmp.getForumNo() + ",");
			System.out.print(aEmp.getAdminNo() + ",");
			System.out.print(aEmp.getForumName() + ",");
			System.out.println();
		}
	}
}

