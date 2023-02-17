package com.requisitions.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.requisitions_detail.model.Requisitions_DetailJDBCDAO;
import com.requisitions_detail.model.Requisitions_DetailVO;


public class RequisitionsJDBCDAO implements RequisitionsDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO requisitions (admin_no,supply_no,req_status,req_pay,total_price) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT req_no,admin_no,supply_no,req_date,req_status,req_pay,total_price FROM requisitions order by req_no";
	private static final String GET_ONE_STMT = 
		"SELECT req_no,admin_no,supply_no,req_date,req_status,req_pay,total_price FROM requisitions where req_no = ?";
	private static final String DELETE = 
		"DELETE FROM requisitions where req_no = ?";
	private static final String UPDATE = 
		"UPDATE requisitions set admin_no=?, supply_no=? , req_status=?, req_pay=?,total_price=? where req_no = ?";
	private static final String GET_REQ_ADMIN = 
		"SELECT req_no,admin_no,supply_no,req_date,req_status,req_pay,total_price FROM requisitions where admin_no = ?";

	@Override
	public int insertReq(RequisitionsVO requisitionsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String columns[] = {"reqNO"};

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT,columns);

			pstmt.setInt(1, requisitionsVO.getAdminNo());
			pstmt.setInt(2, requisitionsVO.getSupplyNo());
			pstmt.setByte(3, requisitionsVO.getReqStatus());
			pstmt.setByte(4, requisitionsVO.getReqPay());
			pstmt.setInt(5, requisitionsVO.getTotalPrice());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int reqNo = 0;
			while (rs.next()) {
				reqNo = rs.getInt(1);
			} 
			return reqNo;
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. insert"
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
	public void update(RequisitionsVO requisitionsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, requisitionsVO.getAdminNo());
			pstmt.setInt(2, requisitionsVO.getSupplyNo());
			pstmt.setByte(3, requisitionsVO.getReqStatus());
			pstmt.setByte(4, requisitionsVO.getReqPay());
			pstmt.setInt(5, requisitionsVO.getTotalPrice());
			pstmt.setInt(6, requisitionsVO.getReqNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver.update "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.update "
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
	public void delete(Integer reqNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reqNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver.de "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. de"
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
	public RequisitionsVO findByPrimaryKey(Integer reqNo) {

		RequisitionsVO  requisitionsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reqNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				requisitionsVO = new RequisitionsVO();
				requisitionsVO.setReqNo(rs.getInt("req_No"));
				requisitionsVO.setAdminNo(rs.getInt("admin_No"));
				requisitionsVO.setSupplyNo(rs.getInt("supply_No"));
				requisitionsVO.setReqDate(rs.getTimestamp("req_Date"));
				requisitionsVO.setReqStatus(rs.getByte("req_Status"));
				requisitionsVO.setReqPay(rs.getByte("req_Pay"));				
				requisitionsVO.setTotalPrice(rs.getInt("total_Price"));
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
		return requisitionsVO;
	}

	@Override
	public List<RequisitionsVO> getAll() {
		List<RequisitionsVO> list = new ArrayList<RequisitionsVO>();
		RequisitionsVO requisitionsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				requisitionsVO = new RequisitionsVO();
				requisitionsVO.setReqNo(rs.getInt("req_No"));
				requisitionsVO.setAdminNo(rs.getInt("admin_No"));
				requisitionsVO.setSupplyNo(rs.getInt("supply_No"));
				requisitionsVO.setReqDate(rs.getTimestamp("req_Date"));
				requisitionsVO.setReqStatus(rs.getByte("req_Status"));
				requisitionsVO.setReqPay(rs.getByte("req_Pay"));
				requisitionsVO.setTotalPrice(rs.getInt("total_Price"));
				list.add(requisitionsVO); // Store the row in the list
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
		return list;
	}
		
	@Override
	public List<RequisitionsVO> findByAdmin(Integer adminNo) {
		List<RequisitionsVO> list = new ArrayList<RequisitionsVO>();
		RequisitionsVO requisitionsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_REQ_ADMIN);
			pstmt.setInt(1, adminNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				requisitionsVO = new RequisitionsVO();
				requisitionsVO.setReqNo(rs.getInt("req_No"));
				requisitionsVO.setAdminNo(rs.getInt("admin_No"));
				requisitionsVO.setSupplyNo(rs.getInt("supply_No"));
				requisitionsVO.setReqDate(rs.getTimestamp("req_Date"));
				requisitionsVO.setReqStatus(rs.getByte("req_Status"));
				requisitionsVO.setReqPay(rs.getByte("req_Pay"));
				requisitionsVO.setTotalPrice(rs.getInt("total_Price"));
				list.add(requisitionsVO);
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
		return list;
	}

		// �s�W
//		RequisitionsVO requisitionsVO1 = new RequisitionsVO();
//		requisitionsVO1.setAdminNo(1);
//		requisitionsVO1.setSupplyNo(2);
//		requisitionsVO1.setReqDate(java.sql.Timestamp.valueOf("2005-01-01 10:01:01"));
//		requisitionsVO1.setReqStatus((byte)(3));
//		requisitionsVO1.setReqPay((byte)(0));		
//		requisitionsVO1.setTotalPrice(10);
//		dao.insert(requisitionsVO1);

		// �ק�
//		RequisitionsVO requisitionsVO2 = new RequisitionsVO();
//		requisitionsVO2.setReqNo(1);
//		requisitionsVO2.setAdminNo(1);
//		requisitionsVO2.setSupplyNo(2);
//		requisitionsVO2.setReqDate(java.sql.Timestamp.valueOf("2005-01-01 10:01:01"));
//		requisitionsVO2.setReqStatus((byte)(3));
//		requisitionsVO2.setReqPay((byte)(1));
//		requisitionsVO2.setTotalPrice(10);
//		dao.update(requisitionsVO2);

		// �R��
//		dao.delete(6);
//		System.out.println("�R�����\");

		// �d��
//		RequisitionsVO requisitionsVO3 = dao.findByPrimaryKey(1);
//		System.out.print(requisitionsVO3.getReqNo() + ",");
//		System.out.print(requisitionsVO3.getAdminNo() + ",");
//		System.out.print(requisitionsVO3.getSupplyNo() + ",");
//		System.out.print(requisitionsVO3.getReqDate() + ",");
//		System.out.print(requisitionsVO3.getReqStatus() + ",");
//		System.out.print(requisitionsVO3.getReqPay() + ",");
//		System.out.print(requisitionsVO3.getTotalPrice() + ",");
//		System.out.println("---------------------");

		// �d��
//		List<RequisitionsVO> list = dao.getAll();
//		for (RequisitionsVO aRequisitionsVO : list) {
//			System.out.print(aRequisitionsVO.getReqNo() + ",");
//			System.out.print(aRequisitionsVO.getAdminNo() + ",");
//			System.out.print(aRequisitionsVO.getSupplyNo() + ",");
//			System.out.print(aRequisitionsVO.getReqDate() + ",");
//			System.out.print(aRequisitionsVO.getReqStatus() + ",");
//			System.out.print(aRequisitionsVO.getReqPay() + ",");
//			System.out.print(aRequisitionsVO.getTotalPrice());
//			System.out.println();
//		}
	
	
}



