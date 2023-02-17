package com.orderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.order.model.OrderJDBCDAO;
import com.order.model.OrderVO;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO order_detail(ORDER_NO,SERIAL_NO,MEM_NO,ITEM_PRICE,ITEM_SALE,`STATUS`) VALUES (?,?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ORDER_NO,SERIAL_NO,MEM_NO,ITEM_PRICE,ITEM_SALE,`STATUS` FROM cga105g6.order_detail order by ORDER_NO";
	private static final String GET_ONE_STMT = "SELECT ORDER_NO,SERIAL_NO,MEM_NO,ITEM_PRICE,ITEM_SALE,`STATUS` FROM cga105g6.order_detail where ORDER_NO = ?";
	private static final String DELETE = "DELETE FROM order_detail where ORDER_NO = ?";
	private static final String UPDATE = "UPDATE cga105g6.order_detail set `STATUS`=? where ORDER_NO = ?";
	
	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, orderDetailVO.getOrderNo());
			pstmt.setInt(2, orderDetailVO.getSerialNo());
			pstmt.setInt(3, orderDetailVO.getMemNo());
			pstmt.setInt(4, orderDetailVO.getItemPrice());
			pstmt.setInt(5, orderDetailVO.getItemSale());
			pstmt.setInt(6, orderDetailVO.getStatus());
//			pstmt.setString(7, orderDetailVO.getCommentContent());
//			pstmt.setTimestamp(8, orderDetailVO.getCommentTime());
//			pstmt.setInt(9, orderDetailVO.getCommentStar());

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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, orderDetailVO.getStatus());
			pstmt.setInt(2, orderDetailVO.getOrderNo());
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
	public void delete(Integer orderNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderDetailVO findByPrimaryKey(Integer orderNo) {

		OrderDetailVO orderDetailVO = null;
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

				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNo(rs.getInt("ORDER_NO"));
				orderDetailVO.setSerialNo(rs.getInt("SERIAL_NO"));
				orderDetailVO.setMemNo(rs.getInt("MEM_NO"));
				orderDetailVO.setItemPrice(rs.getInt("ITEM_PRICE"));
				orderDetailVO.setItemSale(rs.getInt("ITEM_SALE"));
				orderDetailVO.setStatus(rs.getInt("STATUS"));

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
		return orderDetailVO;

	}

	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

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
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNo(rs.getInt("ORDER_NO"));
				orderDetailVO.setSerialNo(rs.getInt("SERIAL_NO"));
				orderDetailVO.setMemNo(rs.getInt("MEM_NO"));
				orderDetailVO.setItemPrice(rs.getInt("ITEM_PRICE"));
				orderDetailVO.setItemSale(rs.getInt("ITEM_SALE"));
				orderDetailVO.setStatus(rs.getInt("STATUS"));
//				orderDetailVO.setCommentContent(rs.getString("COMMENT_CONTENT"));
//				orderDetailVO.setCommentTime(rs.getTimestamp("COMMENT_TIME"));
//				orderDetailVO.setCommentStar(rs.getInt("COMMENT_STAR"));
				list.add(orderDetailVO); // Store the row in the list
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
	public List<OrderDetailVO> getAllOrderNo(Integer orderNo) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

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
				// empVO �]�٬� Domain objects
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNo(rs.getInt("ORDER_NO"));
				orderDetailVO.setSerialNo(rs.getInt("SERIAL_NO"));
				orderDetailVO.setMemNo(rs.getInt("MEM_NO"));
				orderDetailVO.setItemPrice(rs.getInt("ITEM_PRICE"));
				orderDetailVO.setItemSale(rs.getInt("ITEM_SALE"));
				orderDetailVO.setStatus(rs.getInt("STATUS"));
//				orderDetailVO.setCommentContent(rs.getString("COMMENT_CONTENT"));
//				orderDetailVO.setCommentTime(rs.getTimestamp("COMMENT_TIME"));
//				orderDetailVO.setCommentStar(rs.getInt("COMMENT_STAR"));
				list.add(orderDetailVO); // Store the row in the list
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

		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();

//		// �s�W
//		OrderDetailVO OrderDetailVO1 = new OrderDetailVO();
//		OrderDetailVO1.setOrderNo(4);
//		OrderDetailVO1.setSerialNo(6);
//		OrderDetailVO1.setMemNo(1);
//		OrderDetailVO1.setItemPrice(1000);
//		OrderDetailVO1.setItemSale(1);
//		OrderDetailVO1.setStatus(1);
////		OrderDetailVO1.setCommentContent("1");	
////		OrderDetailVO1.setCommentTime(java.sql.Timestamp.valueOf("2005-01-01 04:04:04"));
////		OrderDetailVO1.setCommentStar(1);
//		dao.insert(OrderDetailVO1);

//		
		// �ק�
//	OrderDetailVO OrderDetailVO2 = new OrderDetailVO();
//
//	OrderDetailVO2.setStatus(1);
//	OrderDetailVO2.setOrderNo(26);
//
//	dao.update(OrderDetailVO2);
//
		// �R��
//		dao.delete(9);

		// �d��
//	OrderDetailVO orderDetailVO3 = dao.findByPrimaryKey(4);
//	System.out.print(orderDetailVO3.getOrderNo() + ",");
//	System.out.print(orderDetailVO3.getSerialNo() + ",");
//	System.out.print(orderDetailVO3.getMemNo() + ",");
//	System.out.print(orderDetailVO3.getItemPrice() + ",");
//	System.out.print(orderDetailVO3.getItemSale() + ",");
//	System.out.print(orderDetailVO3.getStatus() + ",");
		
		List<OrderDetailVO> list = dao.getAllOrderNo(6);
		for (OrderDetailVO aOrderDetailVO : list) {
			System.out.print(aOrderDetailVO.getOrderNo() + ",");
			System.out.print(aOrderDetailVO.getSerialNo() + ",");
			System.out.print(aOrderDetailVO.getMemNo() + ",");
			System.out.print(aOrderDetailVO.getItemPrice() + ",");
			System.out.print(aOrderDetailVO.getItemSale() + ",");
			System.out.print(aOrderDetailVO.getStatus() + ",");
//			System.out.print(aOrderDetailVO.getCommentContent() + ",");
//			System.out.print(aOrderDetailVO.getCommentTime() + ",");
//			System.out.print(aOrderDetailVO.getCommentStar() + ",");
			System.out.println();
		}
	}

}
