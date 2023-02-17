package com.access.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Blob;

public class AccessJDBCDAO implements AccessDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO admin_access (Admin_No,Admin_function) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT Admin_No,Admin_function FROM admin_access order by Admin_No";
	private static final String GET_ONE_STMT = "SELECT Admin_No,Admin_function FROM admin_access where Admin_No = ? and Admin_function=?";
	private static final String DELETE = "DELETE FROM admin_access where Admin_No = ? and Admin_function=? ";
	private static final String UPDATE = "UPDATE admin_access set Admin_No=?, Admin_function=?  where Admin_No = ? and Admin_function=?";
	private static final String GETBYFUNCTION = "SELECT * FROM admin_access  where Admin_function = ?";
	private static final String GETBYADMIN =  "SELECT * FROM admin_access  where Admin_No = ?";;

	@Override
	public void insert(AccessVO accessVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, accessVO.getAdminNo());
			pstmt.setInt(2, accessVO.getAdminFunction());

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
	public void update(AccessVO accessVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, accessVO.getAdminNo());
			pstmt.setInt(2, accessVO.getAdminFunction());

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
	public void delete(Integer adminNo, Integer adminFunction) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adminNo);
			pstmt.setInt(2, adminFunction);
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
	public List<AccessVO> getByAdmin(Integer adminNo) {
		List<AccessVO> list = new ArrayList<AccessVO>();
		AccessVO accessVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETBYADMIN);

			pstmt.setInt(1, adminNo);


			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				accessVO = new AccessVO();
				accessVO.setAdminNo(rs.getInt("Admin_No"));
				accessVO.setAdminFunction(rs.getInt("Admin_function"));
				list.add(accessVO);
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
	public List<AccessVO> getByAccess(Integer adminFunction) {
		List<AccessVO> list = new ArrayList<AccessVO>();
		AccessVO accessVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETBYFUNCTION);

			pstmt.setInt(1, adminFunction);


			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				accessVO = new AccessVO();
				accessVO.setAdminNo(rs.getInt("Admin_No"));
				accessVO.setAdminFunction(rs.getInt("Admin_function"));
				list.add(accessVO);
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
	public AccessVO findByPrimaryKey(Integer adminNo, Integer adminFunction) {

		AccessVO accessVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adminNo);
			pstmt.setInt(2, adminFunction);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				accessVO = new AccessVO();
				accessVO.setAdminNo(rs.getInt("Admin_No"));
				accessVO.setAdminFunction(rs.getInt("Admin_function"));

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
		return accessVO;
	}
	@Override
	public List<AccessVO> getAll() {
		List<AccessVO> list = new ArrayList<AccessVO>();
		AccessVO accessVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				accessVO = new AccessVO();
				accessVO.setAdminNo(rs.getInt("Admin_No"));
				accessVO.setAdminFunction(rs.getInt("Admin_function"));

				list.add(accessVO); // Store the row in the list
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

		AccessJDBCDAO dao = new AccessJDBCDAO();

		// 新增
//		AccessVO accessVO1 = new AccessVO();
//
//		accessVO1.setAdminNo(1);
//		accessVO1.setAdminFunction(2);
//
//		dao.insert(accessVO1);

		// 更新
//		AccessVO accessVO2 = new AccessVO();
//
//		accessVO2.setAdminNo(1);
//		accessVO2.setAdminFunction(1);
//		dao.update(accessVO2);

//	刪除
//		dao.delete(1,2);

		// 單一搜尋
//		AccessVO accessVO3 = dao.findByPrimaryKey(1,2);
//		System.out.print(accessVO3.getAdminFunction() + ",");
//		System.out.print(accessVO3.getAdminNo() + ",");
//
//		System.out.println("---------------------");

		// 全部搜尋
		List<AccessVO> list = dao.getAll();
		for (AccessVO aEmp : list) {
			System.out.print(aEmp.getAdminNo() + ",");
			System.out.print(aEmp.getAdminFunction() + ",");
			
			System.out.println();
		}
	}

}

