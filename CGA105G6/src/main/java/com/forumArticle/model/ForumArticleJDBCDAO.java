package com.forumArticle.model;

import java.util.*;

import com.forumArticlePhoto.model.ForumArticlePhotoJDBCDAO;
import com.forumArticlePhoto.model.ForumArticlePhotoVO;

import java.sql.*;

public class ForumArticleJDBCDAO implements ForumArticleDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CGA105G6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";


	private static final String INSERT_STMT = "INSERT INTO forum_article(MEM_NO,FORUM_NO,CONTENT,TITLE) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM forum_article order by ARTICLE_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM forum_article order by ARTICLE_NO = ?";
	private static final String DELETE = "DELETE FROM forum_article where ARTICLE_NO = ?";
	private static final String UPDATE = "UPDATE forum_article set MEM_NO=?, FORUM_NO=?, CONTENT=?, TITLE=? ,article_state=? where ARTICLE_NO = ?;";
	private static final String HIDE = "update forum_article set content = ?, ARTICLE_STATE = ? where ARTICLE_NO = ?";
	private static final String GET_ONE_MEM_STMT = "SELECT * FROM forum_article WHERE Mem_No = ? ";
	
	@Override
	public List<ForumArticleVO> findByMem(Integer memNo) {

		List<ForumArticleVO> list = new ArrayList<ForumArticleVO>();

		ForumArticleVO forumArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MEM_STMT);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumArticleVO = new ForumArticleVO();

				forumArticleVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticleVO.setMemNo(rs.getInt("MEM_NO"));
				forumArticleVO.setForumNo(rs.getInt("FORUM_NO"));
				forumArticleVO.setPublishTime(rs.getTimestamp("PUBLISH_TIME"));
				forumArticleVO.setContent(rs.getString("CONTENT"));
				forumArticleVO.setTitle(rs.getString("TITLE"));
				forumArticleVO.setEditTime(rs.getTimestamp("EDIT_TIME"));
				forumArticleVO.setArticleState(rs.getInt("ARTICLE_STATE"));

				list.add(forumArticleVO);
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
//	@Override
//	public void insert(ForumArticleVO forumForumArticleVO) {
//		// TODO Auto-generated method stub
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, forumForumArticleVO.getMemNo());
//			pstmt.setInt(2, forumForumArticleVO.getForumNo());
//			pstmt.setString(3, forumForumArticleVO.getContent());
//			pstmt.setString(4, forumForumArticleVO.getTitle());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	public Integer insert(ForumArticleVO forumArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String columns[] = { "articleNo" };
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT, columns);

			pstmt.setInt(1,  forumArticleVO.getMemNo());
			pstmt.setInt(2,  forumArticleVO.getForumNo());
			pstmt.setString(3,  forumArticleVO.getContent());
			pstmt.setString(4,  forumArticleVO.getTitle());

			pstmt.executeUpdate();

			Integer nextArticleNo = null;
			// 印出現在新增的文章編號 用於新增該圖片
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextArticleNo = rs.getInt(1);
				System.out.println("自動增加欄位號碼為: " + nextArticleNo);
			}
			rs.close();
			return nextArticleNo;

			// Handle any SQL errors
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
	public void update(ForumArticleVO forumForumArticleVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, forumForumArticleVO.getMemNo());
			pstmt.setInt(2, forumForumArticleVO.getForumNo());
//			pstmt.setTimestamp(3, forumForumArticleVO.getPublishTime());
			pstmt.setString(3, forumForumArticleVO.getContent());
			pstmt.setString(4, forumForumArticleVO.getTitle());
//			pstmt.setTimestamp(6, forumForumArticleVO.getEditTime());
			pstmt.setInt(5, forumForumArticleVO.getArticleState());
			pstmt.setInt(6, forumForumArticleVO.getArticleNo());

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
	public void delete(Integer articleNO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleNO);

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
	public void hideArticle(ForumArticleVO articleVO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(HIDE);
			

			ps.setString(1, articleVO.getContent());
			ps.setInt(2, articleVO.getArticleState());
			ps.setInt(3, articleVO.getArticleNo());

			ps.executeUpdate();

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
			if (ps != null) {
				try {
					ps.close();
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
	public ForumArticleVO findByPrimaryKey(Integer articleNo) {
		
		ForumArticleVO forumArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumArticleVO = new ForumArticleVO();
				forumArticleVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticleVO.setMemNo(rs.getInt("MEM_NO"));
				forumArticleVO.setForumNo(rs.getInt("FORUM_NO"));
				forumArticleVO.setPublishTime(rs.getTimestamp("PUBLISH_TIME"));
				forumArticleVO.setContent(rs.getString("CONTENT"));
				forumArticleVO.setTitle(rs.getString("TITLE"));
				forumArticleVO.setEditTime(rs.getTimestamp("EDIT_TIME"));
				forumArticleVO.setArticleState(rs.getInt("ARTICLE_STATE"));
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
		return forumArticleVO;
	}

	@Override
	public List<ForumArticleVO> getAll(){
		// TODO Auto-generated method stub
		List<ForumArticleVO> list = new ArrayList<ForumArticleVO>();
		ForumArticleVO forumForumArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumForumArticleVO = new ForumArticleVO();
				forumForumArticleVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumForumArticleVO.setMemNo(rs.getInt("MEM_NO"));
				forumForumArticleVO.setForumNo(rs.getInt("FORUM_NO"));
				forumForumArticleVO.setPublishTime(rs.getTimestamp("PUBLISH_TIME"));
				forumForumArticleVO.setContent(rs.getString("CONTENT"));
				forumForumArticleVO.setTitle(rs.getString("TITLE"));
				forumForumArticleVO.setEditTime(rs.getTimestamp("EDIT_TIME"));
				forumForumArticleVO.setArticleState(rs.getInt("ARTICLE_STATE"));
				list.add(forumForumArticleVO); // Store the row in the list
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
	public List<ForumArticleVO> getAllArt(Map<String, String[]> map) {
		List<ForumArticleVO> list = new ArrayList<ForumArticleVO>();
		ForumArticleVO forumArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String MySql = "select * from forum_article "
					+ForumArticleUtil.getWhereCondition(map)
					+"order by article_no";
			pstmt = con.prepareStatement(MySql);
			rs = pstmt.executeQuery();
		
			System.out.println(MySql);
			while (rs.next()) {
		
				forumArticleVO = new ForumArticleVO();
				forumArticleVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticleVO.setMemNo(rs.getInt("MEM_NO"));
				forumArticleVO.setForumNo(rs.getInt("FORUM_NO"));
				forumArticleVO.setPublishTime(rs.getTimestamp("PUBLISH_TIME"));
				forumArticleVO.setContent(rs.getString("CONTENT"));
				forumArticleVO.setTitle(rs.getString("TITLE"));
				forumArticleVO.setEditTime(rs.getTimestamp("EDIT_TIME"));
				forumArticleVO.setArticleState(rs.getInt("ARTICLE_STATE"));
				list.add(forumArticleVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || pstmt != null || con != null) {
				try {
					rs.close();
					pstmt.close();
					con.close();
					
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public static void main(String[] args) {

		ForumArticleJDBCDAO dao = new ForumArticleJDBCDAO();

		ForumArticleVO forumArticleVO1 = new ForumArticleVO();
		forumArticleVO1.setArticleNo(1);
		forumArticleVO1.setMemNo(1);
		forumArticleVO1.setForumNo(1);
//		forumArticleVO1.setPublishTime(java.sql.Timestamp.valueOf("2005-01-01 22:22:22"));
		forumArticleVO1.setContent("content_1");
		forumArticleVO1.setTitle("title_5");
//		forumArticleVO1.setEditTime(java.sql.Timestamp.valueOf("2005-01-01 22:22:22"));
		forumArticleVO1.setArticleState(1);
		dao.update(forumArticleVO1);

		//�ק�
//		ForumForumArticleVO forumForumArticleVO2 = new ForumForumArticleVO();
//		
//		forumForumArticleVO2.setmem_no(1);
//		forumForumArticleVO2.setforum_no(1);
//		forumForumArticleVO2.setpublish_time(java.sql.Timestamp.valueOf("2011-09-14 00:00:00"));
//		forumForumArticleVO2.setcontent("內容1");
//		forumForumArticleVO2.settitle("標題5");
//		forumForumArticleVO2.setedit_time(java.sql.Timestamp.valueOf("2011-09-14 00:00:00"));
//		forumForumArticleVO2.setarticle_state(true);
//		forumForumArticleVO2.setarticle_no(1);
//		dao.update(forumForumArticleVO2);

		// �R��
//		dao.delete(3);
		
		// �d��
//		ForumArticleVO forumArticleVO3 = dao.findByPrimaryKey(3);
//		System.out.print(forumArticleVO3.getArticleNo() + ",");
//		System.out.print(forumArticleVO3.getMemNo() + ",");
//		System.out.print(forumArticleVO3.getForumNo() + ",");
//		System.out.print(forumArticleVO3.getPublishTime() + ",");
//		System.out.print(forumArticleVO3.getContent() + ",");
//		System.out.print(forumArticleVO3.getTitle() + ",");
//		System.out.print(forumArticleVO3.getEditTime());
//		System.out.println("---------------------");
//		System.out.println(forumArticleVO3.getArticleState());
//		System.out.println("---------------------");
		
		// �d��
		List<ForumArticleVO> list = dao.findByMem(1);
		for (ForumArticleVO art : list) {
			System.out.print(art.getArticleNo() + ",");
			System.out.print(art.getMemNo() + ",");
			System.out.print(art.getForumNo() + ",");
			System.out.print(art.getPublishTime() + ",");
			System.out.print(art.getContent() + ",");
			System.out.print(art.getTitle() + ",");
			System.out.print(art.getEditTime());
			System.out.println("---------------------");
			System.out.println(art.getArticleState());
			System.out.println("---------------------");
		}
	}
}