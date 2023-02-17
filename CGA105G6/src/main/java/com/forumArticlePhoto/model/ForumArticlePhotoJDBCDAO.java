package com.forumArticlePhoto.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumArticlePhotoJDBCDAO implements ForumArticlePhotoDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO forum_article_photo (ARTICLE_NO,PHOTO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM forum_article_photo order by PHOTO_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM forum_article_photo where PHOTO_NO = ?";
	private static final String DELETE = "DELETE FROM forum_article_photo where PHOTO_NO = ?";
	private static final String UPDATE = "UPDATE forum_article_photo set PHOTO=? where PHOTO_NO = ?";
	private static final String GET_ByArticle_STMT = "SELECT * FROM forum_article_photo where ARTICLE_NO = ?";
	
		

	@Override
	public void insert(ForumArticlePhotoVO forumArticlePhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumArticlePhotoVO.getArticleNo());
			pstmt.setBytes(2, forumArticlePhotoVO.getPhoto());
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
	public void update(ForumArticlePhotoVO forumArticlePhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, forumArticlePhotoVO.getPhoto());
			pstmt.setInt(2, forumArticlePhotoVO.getPhotoNo());
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
	public void delete(Integer photoNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, photoNo);

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
	public ForumArticlePhotoVO findByPrimaryKey(Integer photoNo) {
		ForumArticlePhotoVO forumArticlePhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, photoNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				forumArticlePhotoVO = new ForumArticlePhotoVO();
				forumArticlePhotoVO.setPhotoNo(rs.getInt("PHOTO_NO"));
				forumArticlePhotoVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticlePhotoVO.setPhoto(rs.getBytes("PHOTO"));
			
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
		return forumArticlePhotoVO;
	}

	@Override
	public List<ForumArticlePhotoVO> getAll() {
		List<ForumArticlePhotoVO> list = new ArrayList<ForumArticlePhotoVO>();
		ForumArticlePhotoVO forumArticlePhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumArticlePhotoVO = new ForumArticlePhotoVO();
				forumArticlePhotoVO.setPhotoNo(rs.getInt("PHOTO_NO"));
				forumArticlePhotoVO.setArticleNo(rs.getInt("ARTICLE_NO"));
				forumArticlePhotoVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(forumArticlePhotoVO); // Store the row in the list
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
	public List<ForumArticlePhotoVO> findByArticleNo(Integer articleNo){
		List<ForumArticlePhotoVO> list = new ArrayList<ForumArticlePhotoVO>();
		ForumArticlePhotoVO ForumArticlePhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ByArticle_STMT);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ForumArticlePhotoVO = new ForumArticlePhotoVO();
				ForumArticlePhotoVO.setArticleNo(rs.getInt("Article_no"));
				ForumArticlePhotoVO.setPhotoNo(rs.getInt("Photo_No"));
				ForumArticlePhotoVO.setPhoto(rs.getBytes("Photo"));

				list.add(ForumArticlePhotoVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		ForumArticlePhotoJDBCDAO dao = new ForumArticlePhotoJDBCDAO();
		
		// �s�W
		ForumArticlePhotoVO forumArticlePhotoVO1 = new ForumArticlePhotoVO();
		forumArticlePhotoVO1.setArticleNo(2);
		File file = new File("image/none.jpg");
		byte[] b = new byte[(int) file.length()];
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			fis.read(b);
			forumArticlePhotoVO1.setPhoto(b);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file2 = new File("image/none.jpg");
		byte[] a = new byte[(int) file.length()];
		FileInputStream fiss;
		try {
			fiss = new FileInputStream(file2);
			fiss.read(a);
			forumArticlePhotoVO1.setPhoto(a);
			fiss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(forumArticlePhotoVO1);

//		// �ק�
//		ForumArticlePhotoVO forumArticlePhotoVO2 = new ForumArticlePhotoVO();
//		forumArticlePhotoVO2.setArticleNo(1);
//		forumArticlePhotoVO2.setPhoto();
//		forumArticlePhotoVO2.setPhotoNo(1);
//		dao.update(forumArticlePhotoVO2);
		
//		// �R��
//		dao.delete(5);

//		// �d��
		List<ForumArticlePhotoVO> list = dao.findByArticleNo(1);
		for (ForumArticlePhotoVO aEmp : list) {
			System.out.print(aEmp.getArticleNo() + ",");
			System.out.print(aEmp.getPhoto() + ",");
			System.out.print(aEmp.getPhoto());
			System.out.println();	
//		
//		System.out.println("---------------------");
//
//		// �d��
//		List<ForumArticlePhotoVO> list = dao.getAll();
//		for (ForumArticlePhotoVO aEmp : list) {
//			System.out.print(aEmp.getArticleNo() + ",");
//			System.out.print(aEmp.getPhoto());
//			System.out.println();		
		}
	}
}