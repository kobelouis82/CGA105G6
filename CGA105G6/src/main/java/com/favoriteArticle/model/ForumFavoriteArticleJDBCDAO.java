package com.favoriteArticle.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumFavoriteArticleJDBCDAO implements ForumFavoriteArticleDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO FORUM_FAVORTIE_ARTICLE (MEM_NO,ARTICLE_NO) VALUES (?,? )";
	private static final String GET_ALL_STMT = "SELECT ARTICLE_NO,MEM_NO FROM FORUM_FAVORTIE_ARTICLE  where MEM_NO=?";
	private static final String GET_ONE_STMT = "SELECT ARTICLE_NO,MEM_NO FROM FORUM_FAVORTIE_ARTICLE where MEM_NO=? and ARTICLE_NO=?";
	private static final String DELETE = "DELETE FROM FORUM_FAVORTIE_ARTICLE where MEM_NO=? and ARTICLE_NO = ?";
	


	@Override
	public void insert(ForumFavoriteArticleVO forumFavoriteArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumFavoriteArticleVO.getMemNo());
			pstmt.setInt(2, forumFavoriteArticleVO.getArticleNo());

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
	public void delete(Integer memNo, Integer articleNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memNo);
			pstmt.setInt(2, articleNo);

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
	public ForumFavoriteArticleVO findByPrimaryKey(Integer memNo, Integer articleNo) {

		ForumFavoriteArticleVO forumFavoriteArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memNo);
			pstmt.setInt(2, articleNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumFavoriteArticleVO = new ForumFavoriteArticleVO();
				forumFavoriteArticleVO.setMemNo(rs.getInt("mem_No"));
				forumFavoriteArticleVO.setArticleNo(rs.getInt("article_No"));

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
		return forumFavoriteArticleVO;
	}

	@Override
	public List<ForumFavoriteArticleVO> getAllbyMem(Integer memNo) {
		List<ForumFavoriteArticleVO> list = new ArrayList<ForumFavoriteArticleVO>();
		ForumFavoriteArticleVO forumFavoriteArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumFavoriteArticleVO = new ForumFavoriteArticleVO();
				forumFavoriteArticleVO.setMemNo(rs.getInt("mem_No"));
				forumFavoriteArticleVO.setArticleNo(rs.getInt("article_No"));

				list.add(forumFavoriteArticleVO); // Store the row in the list
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

		ForumFavoriteArticleJDBCDAO dao = new ForumFavoriteArticleJDBCDAO();

			//新增 
//		ForumFavoriteArticleVO newForumFavoriteArticle = new ForumFavoriteArticleVO();
//		newForumFavoriteArticle.setMemNo(1);
//		newForumFavoriteArticle.setArticleNo(8);
//		
//		dao.insert(newForumFavoriteArticle);

			//刪除
//		dao.delete(1,4);

			//查詢
//		List<ForumFavoriteArticleVO> list = dao.getAllbyMem(1);
//		for (ForumFavoriteArticleVO emp : list) {
//			System.out.println(emp.getArticleNo());
//		}
	}
}
