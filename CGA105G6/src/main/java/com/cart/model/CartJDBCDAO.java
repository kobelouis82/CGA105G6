package com.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartJDBCDAO implements CartDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = "INSERT INTO cga105g6.cart(MEM_NO,SERIAL_NO,ITEM_NAME,ITEM_PRICE,ITEM_SALE) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MEM_NO,SERIAL_NO,ITEM_NAME,ITEM_PRICE,ITEM_SALE FROM cga105g6.cart order by MEM_NO";
	private static final String GET_ONE_STMT = "SELECT MEM_NO,SERIAL_NO,ITEM_NAME,ITEM_PRICE,ITEM_SALE"
			+ " FROM cga105g6.cart where MEM_NO = ?";
	private static final String GET_ONE_STMT1 = "SELECT ITEM_SALE"
			+ " FROM cga105g6.cart where SERIAL_NO = ?";
	private static final String DELETE = "DELETE FROM cga105g6.cart where SERIAL_NO = ?";
	private static final String UPDATE = "UPDATE cga105g6.cart set ITEM_SALE=? where SERIAL_NO = ?";
	
	@Override
	public void insert(CartVO CartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, CartVO.getMemNo());
			pstmt.setInt(2, CartVO.getSerialNo());
			pstmt.setString(3, CartVO.getItemName());
			pstmt.setInt(4, CartVO.getItemPrice());
			pstmt.setInt(5, CartVO.getItemSale());
			

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
	public void update(CartVO CartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, CartVO.getItemSale());
			pstmt.setInt(2, CartVO.getSerialNo());
		
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
	public void delete(Integer serialNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,serialNo);

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
	public CartVO findByPrimaryKey(Integer memNo) {
		CartVO CartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, memNo);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects

				CartVO = new CartVO();
				CartVO.setMemNo(rs.getInt("MEM_NO"));;
				CartVO.setSerialNo(rs.getInt("SERIAL_NO"));
				CartVO.setItemName(rs.getString("ITEM_NAME"));
				CartVO.setItemPrice(rs.getInt("ITEM_PRICE"));;
				CartVO.setItemSale(rs.getInt("ITEM_SALE"));
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
		return CartVO;
	}

	@Override
	public List<CartVO> getAll() {
		List<CartVO> list = new ArrayList<CartVO>();
		CartVO CartVO = null;
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
				CartVO = new CartVO();
				CartVO.setMemNo(rs.getInt("MEM_NO"));;
				CartVO.setSerialNo(rs.getInt("SERIAL_NO"));
				CartVO.setItemName(rs.getString("ITEM_NAME"));
				CartVO.setItemPrice(rs.getInt("ITEM_PRICE"));;
				CartVO.setItemSale(rs.getInt("ITEM_SALE"));
				list.add(CartVO); // Store the row in the list
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

		CartJDBCDAO dao = new CartJDBCDAO();

////		 �s�W
//			CartVO CartVO = new CartVO();
//			
//			CartVO.setMemNo(2);;
//			CartVO.setSerialNo(1);
//			CartVO.setItemName("GTA");
//			CartVO.setItemPrice(1300);
//			CartVO.setItemSale(2);
//			dao.insert(CartVO);
	
	// �ק�
			CartVO CartVO2 = new CartVO();

			
			CartVO2.setSerialNo(2);
			CartVO2.setItemSale(4);
		dao.update(CartVO2);

		// �R��
//			dao.delete(2);

			// �d��
//		CartVO CartVO3 = dao.findByPrimaryKey(2);
//		System.out.print(CartVO3.getMemNo() + ",");
//		System.out.print(CartVO3.getSerialNo() + ",");
//		System.out.print(CartVO3.getItemName() + ",");
//		System.out.print(CartVO3.getItemPrice() + ",");
//		System.out.print(CartVO3.getItemSale() );

		List<CartVO> list = dao.getAll();
		for (CartVO aCartVO : list) {
			System.out.print(aCartVO.getMemNo() + ",");
			System.out.print(aCartVO.getSerialNo() + ",");
			System.out.print(aCartVO.getItemName() + ",");
			System.out.print(aCartVO.getItemPrice() + ",");
			System.out.print(aCartVO.getItemSale() );
			System.out.println();
		}
	}

	@Override
	public void updateCount(CartVO CartVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CartVO findBySerialNo(Integer serialNo) {
		CartVO CartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT1);
			pstmt.setInt(1, serialNo);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects

				CartVO = new CartVO();
				
				CartVO.setItemSale(rs.getInt("ITEM_SALE"));
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
		return CartVO;
	}

	
}
