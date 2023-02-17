package com.requisitions_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class Requisitions_DetailJDBCDAO implements Requisitions_DetailDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO requisitions_detail (req_no,serial_no,status,qty,price) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT req_no,req_detail_no,serial_no,status,qty,price FROM requisitions_detail order by req_detail_no";
	private static final String GET_ONE_STMT = 
		"SELECT req_no,req_detail_no,serial_no,status,qty,price FROM requisitions_detail where req_detail_no = ?";
	private static final String DELETE = 
		"DELETE FROM requisitions_detail where req_detail_no = ?";
	private static final String UPDATE = 
		"UPDATE requisitions_detail set req_no=?,serial_no=?,status = ?,qty = ?,price = ? where req_detail_no = ?";
	private static final String GET_ONE_BY_REQ = 
		"SELECT req_no,req_detail_no,serial_no,status,qty,price FROM requisitions_detail where req_no = ?";


	@Override
	public int insert(Requisitions_DetailVO requisitions_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String columns[] = { "reqNo" };
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT, columns);
			
			pstmt.setInt(1, requisitions_detailVO.getReqNo());
			pstmt.setInt(2, requisitions_detailVO.getSerialNo());
			pstmt.setByte(3, requisitions_detailVO.getStatus());
			pstmt.setInt(4, requisitions_detailVO.getQty());
			pstmt.setInt(5, requisitions_detailVO.getPrice());

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			int reqNo = 0;
			while (rs.next()) {
				reqNo = rs.getInt(1);}	
		  return reqNo; 
			}
			catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. insert"
					+ e.getMessage());
			
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
	public void update(Requisitions_DetailVO requisitions_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, requisitions_detailVO.getReqNo());
			pstmt.setInt(2, requisitions_detailVO.getSerialNo());
			pstmt.setByte(3, requisitions_detailVO.getStatus());
			pstmt.setInt(4, requisitions_detailVO.getQty());
			pstmt.setInt(5, requisitions_detailVO.getPrice());
			pstmt.setInt(6, requisitions_detailVO.getReqDetailNo());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver.update "
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
	public void delete(Integer reqDetailNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reqDetailNo);

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
	public Requisitions_DetailVO findByPrimaryKey(Integer reqDetailNo) {

		Requisitions_DetailVO requisitions_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reqDetailNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				requisitions_detailVO = new Requisitions_DetailVO();
				requisitions_detailVO.setReqDetailNo(rs.getInt("req_Detail_No"));
				requisitions_detailVO.setReqNo(rs.getInt("req_No"));
				requisitions_detailVO.setSerialNo(rs.getInt("serial_No"));
				requisitions_detailVO.setStatus(rs.getByte("status"));
				requisitions_detailVO.setQty(rs.getInt("qty"));
				requisitions_detailVO.setPrice(rs.getInt("price"));

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
		return requisitions_detailVO;
	}

	@Override
	public List<Requisitions_DetailVO> getAll() {
		List<Requisitions_DetailVO> list = new ArrayList<Requisitions_DetailVO>();
		Requisitions_DetailVO requisitions_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				requisitions_detailVO = new Requisitions_DetailVO();
				requisitions_detailVO.setReqDetailNo(rs.getInt("req_Detail_No"));
				requisitions_detailVO.setReqNo(rs.getInt("req_No"));
				requisitions_detailVO.setSerialNo(rs.getInt("serial_No"));
				requisitions_detailVO.setStatus(rs.getByte("status"));
				requisitions_detailVO.setQty(rs.getInt("qty"));
				requisitions_detailVO.setPrice(rs.getInt("price"));
				list.add(requisitions_detailVO); // Store the row in the list
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
	public List<Requisitions_DetailVO> getReqDetailByReq(Integer reqNo) {
		List<Requisitions_DetailVO> list = new ArrayList<Requisitions_DetailVO>();
		Requisitions_DetailVO requisitions_detailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_REQ);
			pstmt.setInt(1, reqNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				requisitions_detailVO = new Requisitions_DetailVO();
				requisitions_detailVO.setReqDetailNo(rs.getInt("req_Detail_No"));
				requisitions_detailVO.setReqNo(rs.getInt("req_No"));
				requisitions_detailVO.setSerialNo(rs.getInt("serial_No"));
				requisitions_detailVO.setStatus(rs.getByte("status"));
				requisitions_detailVO.setQty(rs.getInt("qty"));
				requisitions_detailVO.setPrice(rs.getInt("price"));
				list.add(requisitions_detailVO);
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
	

	
//	public void insert2 (Requisitions_DetailVO requisitions_detailVO , Connection con) {
//
//		PreparedStatement pstmt = null;
//
//		try {
//
//     		pstmt = con.prepareStatement(INSERT_STMT);
//
//		
//			pstmt.setInt(1, requisitions_detailVO.getSerialNo());
//			pstmt.setByte(2, requisitions_detailVO.getStatus());
//			pstmt.setInt(3, requisitions_detailVO.getQty());
//			pstmt.setInt(4, requisitions_detailVO.getPrice());
//
//			Statement stmt=	con.createStatement();
//			//stmt.executeUpdate("set auto_increment_offset=7001;"); //�ۼW�D��-��l��
//			stmt.executeUpdate("set auto_increment_increment=1;");   //�ۼW�D��-���W
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3���]�w���exception�o�ͮɤ�catch�϶���
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back-��-emp");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. "
//							+ excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	public static void main(String[] args) {

		Requisitions_DetailJDBCDAO dao = new Requisitions_DetailJDBCDAO();
		
		// �s�W
//		Requisitions_DetailVO requisitions_detailVO1 = new Requisitions_DetailVO();
//		requisitions_detailVO1.setReqNo(1);		
//		requisitions_detailVO1.setSerialNo("2");
//		requisitions_detailVO1.setStatus((byte)(3));
//		requisitions_detailVO1.setQty(4);
//		requisitions_detailVO1.setPrice(5);
//		dao.insert(requisitions_detailVO1);

		// �ק�
//		Requisitions_DetailVO requisitions_detailVO2 = new Requisitions_DetailVO();
//		requisitions_detailVO2.setReqDetailNo(1);
//		requisitions_detailVO2.setReqNo(1);
//		requisitions_detailVO2.setSerialNo("2");
//		requisitions_detailVO2.setStatus((byte)(3));
//		requisitions_detailVO2.setQty(4);
//		requisitions_detailVO2.setPrice(5);
//		dao.update(requisitions_detailVO2);

		// �R��
//		dao.delete(7);

		// �d��
//		Requisitions_DetailVO requisitions_detailVO3 = dao.findByPrimaryKey(1);
//		System.out.print(requisitions_detailVO3.getReqDetailNo() + ",");
//		System.out.print(requisitions_detailVO3.getReqNo() + ",");
//		System.out.print(requisitions_detailVO3.getSerialNo() + ",");
//		System.out.print(requisitions_detailVO3.getStatus() + ",");
//		System.out.print(requisitions_detailVO3.getQty() + ",");
//		System.out.print(requisitions_detailVO3.getPrice());
//		System.out.println("---------------------");
//
		// �d��
//		List<Requisitions_DetailVO> list = dao.getAll();
//		for (Requisitions_DetailVO aRequisitions_Detail : list) {
//			System.out.print(aRequisitions_Detail.getReqDetailNo() + ",");			
//			System.out.print(aRequisitions_Detail.getReqNo() + ",");
//			System.out.print(aRequisitions_Detail.getSerialNo() + ",");
//			System.out.print(aRequisitions_Detail.getStatus() + ",");
//			System.out.print(aRequisitions_Detail.getQty() + ",");
//			System.out.print(aRequisitions_Detail.getPrice());
//			System.out.println();
//		}
		
		
		
		List<Requisitions_DetailVO> list = dao.getReqDetailByReq(1);
//		System.out.println(list);
		for (Requisitions_DetailVO aRequisitions_Detail : list) {
			System.out.print(aRequisitions_Detail.getReqDetailNo() + ",");			
			System.out.print(aRequisitions_Detail.getReqNo() + ",");
			System.out.print(aRequisitions_Detail.getSerialNo() + ",");
			System.out.print(aRequisitions_Detail.getStatus() + ",");
			System.out.print(aRequisitions_Detail.getQty() + ",");
			System.out.print(aRequisitions_Detail.getPrice());
			System.out.println();
		}
	}
	
}



