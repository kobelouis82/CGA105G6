package com.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.goodsFig.model.*;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Shop;

public class ShopJDBCDAO implements ShopDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO goods(GAME_CODE,ITEM_NAME,ITEM_PRICE,GAME_CLASS_NO,GAME_PLATFORM_NO,COM_NO,`STATUS`,ITEM_DES,INVENTORY_STOCK,PREORDER_QTY,ITEM_STATE) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT SERIAL_NO,GAME_CODE,ITEM_NAME,ITEM_PRICE,GAME_CLASS_NO,GAME_PLATFORM_NO,COM_NO,`STATUS`,ITEM_DES,INVENTORY_STOCK,PREORDER_QTY,ITEM_STATE FROM "
			+ "goods order by SERIAL_NO";
	private static final String GET_ONE_STMT = "SELECT SERIAL_NO,GAME_CODE,ITEM_NAME,ITEM_PRICE,GAME_CLASS_NO,GAME_PLATFORM_NO,COM_NO,`STATUS`,ITEM_DES,INVENTORY_STOCK,PREORDER_QTY,ITEM_STATE"
			+ " FROM goods where SERIAL_NO = ?";
	private static final String DELETE = "DELETE FROM goods where SERIAL_NO = ?";
	private static final String UPDATE = "UPDATE goods set GAME_CODE=?,ITEM_NAME=?,ITEM_PRICE=?,GAME_CLASS_NO=?,GAME_PLATFORM_NO=?,COM_NO=?,`STATUS`=?,ITEM_DES=?,INVENTORY_STOCK=?,PREORDER_QTY=?,ITEM_STATE=? where SERIAL_NO = ?";
	private static final String GET_ALL_SWITCH = "SELECT SERIAL_NO,GAME_CODE,ITEM_NAME,ITEM_PRICE,GAME_CLASS_NO,GAME_PLATFORM_NO,COM_NO,`STATUS`,ITEM_DES,INVENTORY_STOCK,PREORDER_QTY,ITEM_STATE FROM "
			+ "goods where game_Platform_No = ? order by SERIAL_NO";
	private static final String FIND_BY_ITEM_NAME  = "SELECT SERIAL_NO,GAME_CODE,ITEM_NAME,ITEM_PRICE,GAME_CLASS_NO,GAME_PLATFORM_NO,COM_NO,`STATUS`,ITEM_DES,INVENTORY_STOCK,PREORDER_QTY,ITEM_STATE FROM "
			+ "goods WHERE ITEM_NAME LIKE ?";
	private static final String GET_ONE_ITEM_NAME = "SELECT ITEM_NAME FROM goods where SERIAL_NO = ?";
	private static final String selectItemInf="SELECT SERIAL_NO,GAME_CODE,ITEM_NAME,ITEM_PRICE FROM GOODS WHERE GAME_CODE=?";
	@Override
	public int insert(ShopVO shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String columns[] = { "serialNo" };
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT, columns);

			pstmt.setString(1, shopVO.getGameCode());
			pstmt.setString(2, shopVO.getItemName());
			pstmt.setInt(3, shopVO.getItemPrice());
			pstmt.setInt(4, shopVO.getGameClassNo());
			pstmt.setInt(5, shopVO.getGamePlatformNo());
			pstmt.setInt(6, shopVO.getComNo());
			pstmt.setInt(7, shopVO.getStatus());
			pstmt.setString(8, shopVO.getItemDes());
			pstmt.setInt(9, shopVO.getInventoryStock());
			pstmt.setInt(10, shopVO.getPreorderQty());
			pstmt.setInt(11, shopVO.getItemState());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int serialNo = 0;
			while (rs.next()) {
				serialNo = rs.getInt(1);
			}
			System.out.println("serialNo=" + serialNo);
			return serialNo;

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
	public void update(ShopVO shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, shopVO.getGameCode());
			pstmt.setString(2, shopVO.getItemName());
			pstmt.setInt(3, shopVO.getItemPrice());
			pstmt.setInt(4, shopVO.getGameClassNo());
			pstmt.setInt(5, shopVO.getGamePlatformNo());
			pstmt.setInt(6, shopVO.getComNo());
			pstmt.setInt(7, shopVO.getStatus());
			pstmt.setString(8, shopVO.getItemDes());
			pstmt.setInt(9, shopVO.getInventoryStock());
			pstmt.setInt(10, shopVO.getPreorderQty());
			pstmt.setInt(11, shopVO.getItemState());
			pstmt.setInt(12, shopVO.getSerialNo());
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

			pstmt.setInt(1, serialNo);

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
	public ShopVO findByPrimaryKey(Integer serialNo) {
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, serialNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects

				shopVO = new ShopVO();
				shopVO.setSerialNo(rs.getInt("SERIAL_NO"));
				shopVO.setGameCode(rs.getString("GAME_CODE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemPrice(rs.getInt("ITEM_PRICE"));
				shopVO.setGameClassNo(rs.getInt("GAME_CLASS_NO"));
				shopVO.setGamePlatformNo(rs.getInt("GAME_PLATFORM_NO"));
				shopVO.setComNo(rs.getInt("COM_NO"));
				shopVO.setStatus(rs.getInt("STATUS"));
				shopVO.setItemDes(rs.getString("ITEM_DES"));
				shopVO.setInventoryStock(rs.getInt("INVENTORY_STOCK"));
				shopVO.setPreorderQty(rs.getInt("PREORDER_QTY"));
				shopVO.setItemState(rs.getInt("ITEM_STATE"));

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
		return shopVO;
	}

	@Override
	public List<ShopVO> getAll() {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;

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
				shopVO = new ShopVO();
				shopVO.setSerialNo(rs.getInt("SERIAL_NO"));
				shopVO.setGameCode(rs.getString("GAME_CODE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemPrice(rs.getInt("ITEM_PRICE"));
				shopVO.setGameClassNo(rs.getInt("GAME_CLASS_NO"));
				shopVO.setGamePlatformNo(rs.getInt("GAME_PLATFORM_NO"));
				shopVO.setComNo(rs.getInt("COM_NO"));
				shopVO.setStatus(rs.getInt("STATUS"));
				shopVO.setItemDes(rs.getString("ITEM_DES"));
				shopVO.setInventoryStock(rs.getInt("INVENTORY_STOCK"));
				shopVO.setPreorderQty(rs.getInt("PREORDER_QTY"));
				shopVO.setItemState(rs.getInt("ITEM_STATE"));
				list.add(shopVO); // Store the row in the list
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
	public List<ShopVO> getAllGamePlatformNo(Integer gamePlatformNo) {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_SWITCH);

			pstmt.setInt(1, gamePlatformNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects

				shopVO = new ShopVO();
				shopVO.setSerialNo(rs.getInt("SERIAL_NO"));
				shopVO.setGameCode(rs.getString("GAME_CODE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemPrice(rs.getInt("ITEM_PRICE"));
				shopVO.setGameClassNo(rs.getInt("GAME_CLASS_NO"));
				shopVO.setGamePlatformNo(rs.getInt("GAME_PLATFORM_NO"));
				shopVO.setComNo(rs.getInt("COM_NO"));
				shopVO.setStatus(rs.getInt("STATUS"));
				shopVO.setItemDes(rs.getString("ITEM_DES"));
				shopVO.setInventoryStock(rs.getInt("INVENTORY_STOCK"));
				shopVO.setPreorderQty(rs.getInt("PREORDER_QTY"));
				shopVO.setItemState(rs.getInt("ITEM_STATE"));
				list.add(shopVO); // Store the row in the list
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
	public List<ShopVO> getByItemName(String itemName) {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_ITEM_NAME);

			pstmt.setString(1, "%" + itemName + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects

				shopVO = new ShopVO();
				shopVO.setSerialNo(rs.getInt("SERIAL_NO"));
				shopVO.setGameCode(rs.getString("GAME_CODE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemPrice(rs.getInt("ITEM_PRICE"));
				shopVO.setGameClassNo(rs.getInt("GAME_CLASS_NO"));
				shopVO.setGamePlatformNo(rs.getInt("GAME_PLATFORM_NO"));
				shopVO.setComNo(rs.getInt("COM_NO"));
				shopVO.setStatus(rs.getInt("STATUS"));
				shopVO.setItemDes(rs.getString("ITEM_DES"));
				shopVO.setInventoryStock(rs.getInt("INVENTORY_STOCK"));
				shopVO.setPreorderQty(rs.getInt("PREORDER_QTY"));
				shopVO.setItemState(rs.getInt("ITEM_STATE"));
				list.add(shopVO); // Store the row in the list
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
	public List<ShopVO> getAll(Map<String, String[]> map) {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String finalSQL = "select * from goods "
		          + jdbcUtil_CompositeQuery_Shop.get_WhereCondition(map)
		          + "order by SERIAL_NO";
	

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
			
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setSerialNo(rs.getInt("serial_No"));
				shopVO.setGameCode(rs.getString("game_Code"));
				shopVO.setItemName(rs.getString("item_Name"));
				shopVO.setItemPrice(rs.getInt("item_Price"));
				
				
				
				list.add(shopVO); 
			}
	
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
	public ShopVO selectItemInf(String gameCode) {
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(selectItemInf);

			pstmt.setString(1, gameCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopVO = new ShopVO();
				shopVO.setSerialNo(rs.getInt("SERIAL_NO"));
				shopVO.setGameCode(rs.getString("GAME_CODE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemPrice(rs.getInt("ITEM_PRICE"));

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
		return shopVO;
	}

	public static void main(String[] args) {

		ShopJDBCDAO dao = new ShopJDBCDAO();

		// �s�W
//		ShopVO ShopVO1 = new ShopVO();
//
//		ShopVO1.setGameCode("1");
//		ShopVO1.setItemName("100");
//		ShopVO1.setItemPrice(100);
//		ShopVO1.setGameClassNo(100);
//		ShopVO1.setGamePlatformNo(100);
//		ShopVO1.setComNo(100);
//		ShopVO1.setStatus(100);
//		ShopVO1.setItemDes("100");
//		ShopVO1.setInventoryStock(100);
//		ShopVO1.setPreorderQty(100);
//		ShopVO1.setItemState(1);
//
//		dao.insert(ShopVO1);
//
//		System.out.println("==========================");

//			
		// �ק�
//		ShopVO shopVO2 = new ShopVO();
//
//		shopVO2.setGameCode("00000001");
//		shopVO2.setItemName("�c�F�j�� 8");
//		shopVO2.setItemPrice(100);
//		shopVO2.setGameClassNo(100);
//		shopVO2.setGamePlatformNo(100);
//		shopVO2.setComNo(100);
//		shopVO2.setStatus(100);
//		shopVO2.setItemDes("100");
//		shopVO2.setInventoryStock(100);
//		shopVO2.setPreorderQty(100);
//		shopVO2.setItemState(0);
//		shopVO2.setSerialNo(00000001);
//		dao.update(shopVO2);
//
		// �R��
//			dao.delete(9);

//			// �d��
//			ShopVO shopVO3 = dao.findByPrimaryKey(123456789);
//			System.out.print(shopVO3.getItemName() + ",");
//			System.out.print(shopVO3.getSerialNo() + ",");
//			System.out.print(shopVO3.getGameCode() + ",");
//			System.out.print(shopVO3.getItemPrice() + ",");
//			System.out.print(shopVO3.getGameClassNo() + ",");
//			System.out.print(shopVO3.getGamePlatformNo() + ",");
//			System.out.print(shopVO3.getComNo() + ",");
//			System.out.print(shopVO3.getStatus() + ",");
//			System.out.print(shopVO3.getItemDes() + ",");
//			System.out.print(shopVO3.getInventoryStock() + ",");
//			System.out.print(shopVO3.getPreorderQty() + ",");
//			System.out.print(shopVO3.isItemState() + ",");


//		List<ShopVO> list = dao.getAll();
//		for (ShopVO aShop : list) {
//			System.out.print(aShop.getSerialNo() + ",");
//			System.out.print(aShop.getGameCode() + ",");
//			System.out.print(aShop.getItemName() + ",");
//			System.out.print(aShop.getItemPrice() + ",");
//			System.out.print(aShop.getGameClassNo() + ",");
//			System.out.print(aShop.getGamePlatformNo() + ",");
//			System.out.print(aShop.getComNo() + ",");
//			System.out.print(aShop.getStatus() + ",");
//			System.out.print(aShop.getItemDes() + ",");
//			System.out.print(aShop.getInventoryStock() + ",");
//			System.out.print(aShop.getPreorderQty() + ",");
//			System.out.print(aShop.getItemState() + ",");
//			System.out.println();

		List<ShopVO> list = dao.getByItemName("PGA");
		for (ShopVO aShop : list) {
			System.out.print(aShop.getSerialNo() + ",");
			System.out.print(aShop.getGameCode() + ",");
			System.out.print(aShop.getItemName() + ",");
			System.out.print(aShop.getItemPrice() + ",");
			System.out.print(aShop.getGameClassNo() + ",");
			System.out.print(aShop.getGamePlatformNo() + ",");
			System.out.print(aShop.getComNo() + ",");
			System.out.print(aShop.getStatus() + ",");
			System.out.print(aShop.getItemDes() + ",");
			System.out.print(aShop.getInventoryStock() + ",");
			System.out.print(aShop.getPreorderQty() + ",");
			System.out.print(aShop.getItemState() + ",");
			System.out.println();
		}
	}

	@Override
	public ShopVO findOneItemName(Integer serialNo) {
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ITEM_NAME);

			pstmt.setInt(1, serialNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects

				shopVO = new ShopVO();				
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				

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
		return shopVO;
	}


}
