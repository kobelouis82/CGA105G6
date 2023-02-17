package com.supplier.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierJDBCDAO implements SupplierDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO supplier (supply_name,supply_contact,supply_phone,supply_address) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT supply_no,supply_name,supply_contact,supply_phone,supply_address FROM supplier order by supply_no";
	private static final String GET_ONE_STMT = 
		"SELECT supply_no,supply_name,supply_contact,supply_phone,supply_address FROM supplier where supply_no = ?";
	private static final String DELETE = 
		"DELETE FROM supplier where supply_no = ?";
	private static final String UPDATE = 
		"UPDATE supplier set supply_name=?,supply_contact=?,supply_phone=?,supply_address=? where supply_no = ?";

	@Override
	public void insert(SupplierVO supplierVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, supplierVO.getSupplyName());
			pstmt.setString(2, supplierVO.getSupplyContact());
			pstmt.setString(3, supplierVO.getSupplyPhone());
			pstmt.setString(4, supplierVO.getSupplyAddress());

			pstmt.executeUpdate();

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
	public void update(SupplierVO supplierVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, supplierVO.getSupplyName());
			pstmt.setString(2, supplierVO.getSupplyContact());
			pstmt.setString(3, supplierVO.getSupplyPhone());
			pstmt.setString(4, supplierVO.getSupplyAddress());
			pstmt.setInt(5, supplierVO.getSupplyNo());

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
	public void delete(Integer supply_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, supply_no);

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
	public SupplierVO findByPrimaryKey(Integer supplyNo) {

		SupplierVO supplierVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, supplyNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				supplierVO = new SupplierVO();
				supplierVO.setSupplyNo(rs.getInt("supply_no"));
				supplierVO.setSupplyName(rs.getString("Supply_name"));
				supplierVO.setSupplyContact(rs.getString("supply_contact"));
				supplierVO.setSupplyPhone(rs.getString("supply_phone"));
				supplierVO.setSupplyAddress(rs.getString("supply_address"));
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
		return supplierVO;
	}

	@Override
	public List<SupplierVO> getAll() {
		List<SupplierVO> list = new ArrayList<SupplierVO>();
		SupplierVO supplierVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				supplierVO = new SupplierVO();
				supplierVO.setSupplyNo(rs.getInt("supply_no"));
				supplierVO.setSupplyName(rs.getString("supply_name"));
				supplierVO.setSupplyContact(rs.getString("supply_contact"));
				supplierVO.setSupplyPhone(rs.getString("supply_phone"));
				supplierVO.setSupplyAddress(rs.getString("supply_address"));
				list.add(supplierVO); // Store the row in the list
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

	public static void main(String[] args) {

		SupplierJDBCDAO dao = new SupplierJDBCDAO();

		// 新增
		SupplierVO supplierVO1 = new SupplierVO();
		supplierVO1.setSupplyNo(7);
		supplierVO1.setSupplyName("任天堂專賣店");
		supplierVO1.setSupplyContact("瑪莉歐");
		supplierVO1.setSupplyPhone("0806449");
		supplierVO1.setSupplyAddress("火星");
		dao.insert(supplierVO1);

		// 修改
//		SupplierVO supplierVO2 = new SupplierVO();
//		supplierVO2.setSupplyNo(7);
//		supplierVO2.setSupplyName("大型電玩專賣店");
//		supplierVO2.setSupplyContact("格鬥天王");
//		supplierVO2.setSupplyPhone("8888");
//		supplierVO2.setSupplyAddress("月球");
//		dao.update(supplierVO2);

//		// 刪除
//		dao.delete(7);

		// 查詢
//		SupplierVO supplierVO3 = dao.findByPrimaryKey(1);
//		System.out.print(supplierVO3.getSupplyNo() + ",");
//		System.out.print(supplierVO3.getSupplyName() + ",");
//		System.out.print(supplierVO3.getSupplyContact() + ",");
//		System.out.print(supplierVO3.getSupplyPhone() + ",");
//		System.out.print(supplierVO3.getSupplyAddress());
//		System.out.println("---------------------");

		// 查詢
//		List<SupplierVO> list = dao.getAll();
//		for (SupplierVO aSupplierVO : list) {
//			System.out.print(aSupplierVO.getSupplyNo() + ",");
//			System.out.print(aSupplierVO.getSupplyName() + ",");
//			System.out.print(aSupplierVO.getSupplyContact() + ",");
//			System.out.print(aSupplierVO.getSupplyPhone() + ",");
//			System.out.print(aSupplierVO.getSupplyAddress());
//			System.out.println();
//		}
	}
	
}



