package com.order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.ShopVO;

public class OrderJDBCDAO implements OrderDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO cga105g6.order(MEM_NO,ORDER_TOTAL,ORDER_STATE,ORDER_SHIP,ORDER_PAY) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ORDER_NO,MEM_NO,ORDER_TIME,ORDER_TOTAL,ORDER_STATE,ORDER_SHIP,ORDER_PAY FROM cga105g6.order order by ORDER_NO";
	private static final String GET_ONE_STMT = "SELECT ORDER_NO,MEM_NO,ORDER_TIME,ORDER_TOTAL,ORDER_STATE,ORDER_SHIP,ORDER_PAY"
			+ " FROM cga105g6.order where ORDER_NO = ?";
	private static final String DELETE = "DELETE FROM order where ORDER_NO = ?";
	private static final String UPDATE = "UPDATE cga105g6.order set ORDER_STATE=?,ORDER_SHIP=?,ORDER_PAY=? where ORDER_NO = ?";
	private static final String GET_ONE_STMT_MEMNO = "SELECT ORDER_NO,MEM_NO,ORDER_TIME,ORDER_TOTAL,ORDER_STATE,ORDER_SHIP,ORDER_PAY"
			+ " FROM cga105g6.order where MEM_NO = ?";
	@Override
	public int insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String columns[] = { "orderNo" };
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT,columns);

			pstmt.setInt(1, orderVO.getMemNo());
			pstmt.setInt(2, orderVO.getOrderTotal());
			pstmt.setInt(3, orderVO.getOrderState());
			pstmt.setInt(4, orderVO.getOrderShip());
			pstmt.setInt(5, orderVO.getOrderPay());
			

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int orderNo = 0;
			while (rs.next()) {
				orderNo = rs.getInt(1);				
			}
			System.out.println("orderNo="+ orderNo);
			return orderNo;
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
	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setInt(1, orderVO.getOrderState());
			pstmt.setInt(2, orderVO.getOrderShip());
			pstmt.setInt(3, orderVO.getOrderPay());
			pstmt.setInt(4, orderVO.getOrderNo());
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
	public OrderVO findByPrimaryKey(Integer orderNo) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects

				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("ORDER_NO"));
				orderVO.setMemNo(rs.getInt("MEM_NO"));
				orderVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				orderVO.setOrderTotal(rs.getInt("ORDER_TOTAL"));
				orderVO.setOrderState(rs.getInt("ORDER_STATE"));
				orderVO.setOrderShip(rs.getInt("ORDER_SHIP"));
				orderVO.setOrderPay(rs.getInt("ORDER_PAY"));

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
		return orderVO;
	}

	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;

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
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("ORDER_NO"));
				orderVO.setMemNo(rs.getInt("MEM_NO"));
				orderVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				orderVO.setOrderTotal(rs.getInt("ORDER_TOTAL"));
				orderVO.setOrderState(rs.getInt("ORDER_STATE"));
				orderVO.setOrderShip(rs.getInt("ORDER_SHIP"));
				orderVO.setOrderPay(rs.getInt("ORDER_PAY"));
				list.add(orderVO); // Store the row in the list
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
	public List<OrderVO> findByMemNo(Integer memNo) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_MEMNO);
			
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("ORDER_NO"));
				orderVO.setMemNo(rs.getInt("MEM_NO"));
				orderVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				orderVO.setOrderTotal(rs.getInt("ORDER_TOTAL"));
				orderVO.setOrderState(rs.getInt("ORDER_STATE"));
				orderVO.setOrderShip(rs.getInt("ORDER_SHIP"));
				orderVO.setOrderPay(rs.getInt("ORDER_PAY"));
				list.add(orderVO); // Store the row in the list
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

		OrderJDBCDAO dao = new OrderJDBCDAO();

////		 �s�W
//			OrderVO OrderVO1 = new OrderVO();
//			
//			OrderVO1.setMemNo(2);;
//			OrderVO1.setOrderTime(java.sql.Timestamp.valueOf("2005-01-01 04:04:04"));
//			OrderVO1.setOrderTotal(1000);
//			OrderVO1.setOrderState(1);
//			OrderVO1.setOrderShip(1);
//			OrderVO1.setOrderPay(1);
//
//			
//			dao.insert(OrderVO1);
	
		// �ק�
//		OrderVO OrderVO2 = new OrderVO();
//
//		OrderVO2.setOrderState(1);
//		OrderVO2.setOrderShip(2);
//		OrderVO2.setOrderPay(3);
//		OrderVO2.setOrderNo(6);
//
//		dao.update(OrderVO2);
//
		// �R��
//			dao.delete(9);

			// �d��
//		OrderVO orderVO3 = dao.findByPrimaryKey(1);
//		System.out.print(orderVO3.getOrderNo() + ",");
//		System.out.print(orderVO3.getMemNo() + ",");
//		System.out.print(orderVO3.getOrderTime() + ",");
//		System.out.print(orderVO3.getOrderTotal() + ",");
//		System.out.print(orderVO3.getOrderState() + ",");
//		System.out.print(orderVO3.getOrderShip() + ",");
//		System.out.print(orderVO3.getOrderPay() + ",");

		List<OrderVO> list = dao.findByMemNo(1);
		for (OrderVO aOrder : list) {
			System.out.print(aOrder.getOrderNo() + ",");
			System.out.print(aOrder.getMemNo() + ",");
			System.out.print(aOrder.getOrderTime() + ",");
			System.out.print(aOrder.getOrderTotal() + ",");
			System.out.print(aOrder.getOrderState() + ",");
			System.out.print(aOrder.getOrderShip() + ",");
			System.out.print(aOrder.getOrderPay() + ",");
			System.out.println();
		}
	}

	@Override
	public void delete(Integer orderNo) {
		// TODO Auto-generated method stub
		
	}

	
}
