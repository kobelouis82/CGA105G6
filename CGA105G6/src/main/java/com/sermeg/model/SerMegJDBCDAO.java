package com.sermeg.model;

import java.util.*;
import java.sql.*;



public class SerMegJDBCDAO implements SerMegDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO ser_meg (ADMIN_NO,MEM_NO,MESSAGE_CONTENT,MESSAGE_DIRECTIONS) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT MESSAGE_NO,ADMIN_NO,MEM_NO,MESSAGE_CONTENT,MESSAGE_DIRECTIONS,MESSAGE_TIME FROM ser_meg order by MESSAGE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT MESSAGE_NO,ADMIN_NO,MEM_NO,MESSAGE_CONTENT,MESSAGE_DIRECTIONS,MESSAGE_TIME FROM ser_meg where MESSAGE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM ser_meg where MESSAGE_NO = ?";
	private static final String UPDATE = 
		"UPDATE ser_meg set ADMIN_NO=?, MEM_NO=?, MESSAGE_CONTENT=?, MESSAGE_DIRECTIONS=? where MESSAGE_NO = ?";

	@Override
	public void insert(SerMegVO serMegVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, serMegVO.getAdminNo());
			pstmt.setInt(2, serMegVO.getMemNo());
			pstmt.setString(3, serMegVO.getMessageContent());
			pstmt.setInt(4, serMegVO.getMessageDirections());
//			pstmt.setDate(5, serMegVO.getMessageTime());

			
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
	public void update(SerMegVO serMegVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, serMegVO.getAdminNo());
			pstmt.setInt(2, serMegVO.getMemNo());
			pstmt.setString(3, serMegVO.getMessageContent());
			pstmt.setInt(4, serMegVO.getMessageDirections());
//			pstmt.setDate(5, serMegVO.getMessageTime());
			pstmt.setInt(5, serMegVO.getMessageNo());
			
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
	public SerMegVO findByPrimaryKey(Integer messageNo) {

		SerMegVO serMegVO = null;
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

				serMegVO = new SerMegVO();
				serMegVO.setAdminNo(rs.getInt("ADMIN_NO"));
				serMegVO.setMemNo(rs.getInt("MEM_NO"));
				serMegVO.setMessageContent(rs.getString("MESSAGE_CONTENT"));
				serMegVO.setMessageDirections(rs.getInt("MESSAGE_DIRECTIONS"));
				serMegVO.setMessageTime(rs.getDate("MESSAGE_TIME"));
				
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
		return serMegVO;
	}

	@Override
	public List<SerMegVO> getAll() {
		List<SerMegVO> list = new ArrayList<SerMegVO>();
		SerMegVO serMegVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				serMegVO = new SerMegVO();
				serMegVO.setAdminNo(rs.getInt("ADMIN_NO"));
				serMegVO.setMemNo(rs.getInt("MEM_NO"));
				serMegVO.setMessageContent(rs.getString("MESSAGE_CONTENT"));
				serMegVO.setMessageDirections(rs.getInt("MESSAGE_DIRECTIONS"));
				serMegVO.setMessageTime(rs.getDate("MESSAGE_TIME"));
				
				list.add(serMegVO); // Store the row in the list
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

	public static void main(String[] args) {

		// add
		SerMegJDBCDAO dao = new SerMegJDBCDAO();
		
		SerMegVO serMegVO1 = new SerMegVO();
		serMegVO1.setAdminNo(1);
		serMegVO1.setMemNo(1);
		serMegVO1.setMessageContent("xxxxx");
		serMegVO1.setMessageDirections(1);
//		serMegVO1.setMessageTime(java.sql.Date.valueOf("2111-11-11"));
		
		dao.insert(serMegVO1);

		// alter
		SerMegVO serMegVO2 = new SerMegVO();
		serMegVO2.setAdminNo(1);
		serMegVO2.setMemNo(3);
		serMegVO2.setMessageContent("XXXXX");
		serMegVO2.setMessageDirections(1);
//		serMegVO2.setMessageTime(java.sql.Date.valueOf("2002-01-01"));
		serMegVO2.setMessageNo(1);
		dao.update(serMegVO2);

		// delete
		dao.delete(1);

		// search for one
		SerMegVO serMegVO3 = dao.findByPrimaryKey(1);
		System.out.print(serMegVO3.getAdminNo() + ",");
		System.out.print(serMegVO3.getMemNo() + ",");
		System.out.print(serMegVO3.getMessageContent() + ",");
		System.out.print(serMegVO3.getMessageDirections() + ",");
		System.out.print(serMegVO3.getMessageTime());
		
//		System.out.println("---------------------");

		// search for all
		List<SerMegVO> list = dao.getAll();
		for (SerMegVO serMegVO : list) {
			System.out.print(serMegVO.getAdminNo() + ",");
			System.out.print(serMegVO.getMemNo() + ",");
			System.out.print(serMegVO.getMessageContent() + ",");
			System.out.print(serMegVO.getMessageDirections() + ",");
			System.out.print(serMegVO.getMessageTime());

			System.out.println();
		}
	}
}