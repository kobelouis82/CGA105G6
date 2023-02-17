package com.title.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admin.model.*;
import com.mysql.cj.jdbc.Blob;

public class TitleJDBCDAO implements TitleDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO title (admin_Title) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT admin_Title_No,admin_Title FROM title order by admin_Title_No";
	private static final String GET_ONE_STMT = "SELECT admin_Title_No,admin_Title FROM title where admin_Title_No = ?";
	private static final String DELETE = "DELETE FROM title where admin_Title_No = ?";
	private static final String UPDATE = "UPDATE title set admin_Title=?  where admin_Title_No = ?";

	@Override
	public void insert(TitleVO titleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, titleVO.getAdminTitle());

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
	public void update(TitleVO titleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, titleVO.getAdminTitle());
			pstmt.setInt(2, titleVO.getAdminTitleNo());
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
	public void delete(Integer AdminTitleNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, AdminTitleNo);
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
	public TitleVO findByPrimaryKey(Integer AdminTitleNo) {

		TitleVO titleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, AdminTitleNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				titleVO = new TitleVO();
				titleVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				titleVO.setAdminTitle(rs.getString("Admin_Title"));

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
		return titleVO;
	}

	@Override
	public List<TitleVO> getAll() {
		List<TitleVO> list = new ArrayList<TitleVO>();
		TitleVO titleVO = null;

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
				titleVO = new TitleVO();
				titleVO.setAdminTitleNo(rs.getInt("admin_Title_No"));
				titleVO.setAdminTitle(rs.getString("admin_Title"));

				list.add(titleVO); // Store the row in the list
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

		TitleJDBCDAO dao = new TitleJDBCDAO();

		// �s�W
//		TitleVO titleVO1 = new TitleVO();
//
//		titleVO1.setAdminTitle("��F�S�諸�|���޲z��");
//
//		dao.insert(titleVO1);

		// �ק�
//		TitleVO titleVO2 = new TitleVO();
//
//		titleVO2.setAdminTitle("��F�S�諸�|���޲z��");
//		dao.update(titleVO2);

//		// �R��
//		dao.delete(1);
//
//		// �d��
//		TitleVO titleVO3 = dao.findByPrimaryKey(1);
//		
//		System.out.println(titleVO3.getAdminTitleNo());
//		System.out.print(titleVO3.getAdminTitle() + ",");
//		System.out.println("---------------------");
//
//		// �d��
		List<TitleVO> list = dao.getAll();
		for (TitleVO aEmp : list) {
			
			System.out.print(aEmp.getAdminTitleNo() + ",");
			System.out.print(aEmp.getAdminTitle() + ",");
			System.out.println();
		}
	}

}