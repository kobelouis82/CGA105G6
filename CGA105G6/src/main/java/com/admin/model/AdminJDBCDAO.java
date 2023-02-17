package com.admin.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminJDBCDAO implements AdminDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO admin (Admin_Name,Admin_Title_No,ADMIN_PHONE,ADMIN_MAIL,ADMIN_ACCOUNT,ADMIN_PASSWORD,ADMIN_PHOTO,ADMIN_STATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT Admin_No, Admin_Name,Admin_Title_No,ADMIN_PHONE,ADMIN_MAIL,ADMIN_ACCOUNT,ADMIN_PASSWORD,ADMIN_PHOTO,ADMIN_STATE FROM admin order by Admin_No";
	private static final String GET_ONE_STMT = "SELECT Admin_No, Admin_Name,Admin_Title_No,ADMIN_PHONE,ADMIN_MAIL,ADMIN_ACCOUNT,ADMIN_PASSWORD,ADMIN_PHOTO,ADMIN_STATE FROM admin where Admin_No = ?";
	private static final String DELETE = "DELETE FROM admin where Admin_No = ?";
	private static final String UPDATE = "UPDATE admin set Admin_Name = ?,Admin_Title_No = ?,ADMIN_PHONE= ?,ADMIN_MAIL= ?,ADMIN_ACCOUNT= ?,ADMIN_PASSWORD= ?,ADMIN_PHOTO= ?,ADMIN_STATE = ? where Admin_No = ?";
	private static final String LOGIN_CHECK = "select * from admin where ADMIN_ACCOUNT = ? and ADMIN_PASSWORD = ?";
	private static final String FOGETPASSWORD = "select * from admin where ADMIN_ACCOUNT = ? and ADMIN_MAIL = ?";
	private static final String FINDMAIL = "select * from admin where ADMIN_MAIL =  ?";
	private static final String FINDAC = "select * from admin where ADMIN_ACCOUNT =  ?";
	private static final String GET_ALL_FUNCTION_EMP_NO_STMT = "SELECT * "
			+ "FROM admin a " + "join admin_access ac " + "on a.admin_no = ac.admin_no "
			+ "join admin_function af " + "on ac.admin_Function = af.admin_Function " + "where af.admin_Function = ?";
//	private static final String ACCESS = "select * from admin where ADMIN_ACCOUNT =  ?";
// 找帳號密碼
	public AdminVO findByAcAndPwd(String account, String password) {
//=========================管理員登入===================================
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LOGIN_CHECK);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminNo(rs.getInt("Admin_No"));
				adminVO.setAdminName(rs.getString("Admin_Name"));
				adminVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				adminVO.setPhone(rs.getString("Admin_Phone"));
				adminVO.setMail(rs.getString("Admin_Mail"));
				adminVO.setAccount(rs.getString("Admin_Account"));
				adminVO.setPassword(rs.getString("Admin_Password"));
				adminVO.setPhoto(rs.getBytes("Admin_Photo"));
				adminVO.setState(rs.getInt("Admin_State"));

			}
			;

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

		return adminVO;
	};

	@Override
	public void insert(AdminVO adminVO) {
		//=========================新增管理員===================================
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminVO.getAdminName());
			pstmt.setInt(2, adminVO.getAdminTitleNo());
			pstmt.setString(3, adminVO.getPhone());
			pstmt.setString(4, adminVO.getMail());
			pstmt.setString(5, adminVO.getAccount());
			pstmt.setString(6, adminVO.getPassword());
			pstmt.setBytes(7, adminVO.getPhoto());
			pstmt.setInt(8, adminVO.getState());

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
	public void update(AdminVO adminVO) {
//=========================更新管理員資料===================================
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adminVO.getAdminName());
			pstmt.setInt(2, adminVO.getAdminTitleNo());
			pstmt.setString(3, adminVO.getPhone());
			pstmt.setString(4, adminVO.getMail());
			pstmt.setString(5, adminVO.getAccount());
			pstmt.setString(6, adminVO.getPassword());
			pstmt.setBytes(7, adminVO.getPhoto());
			pstmt.setInt(8, adminVO.getState());
			pstmt.setInt(9, adminVO.getAdminNo());

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
	public void delete(Integer Admin_No) {
		//=========================刪除管理員===================================
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, Admin_No);

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
	public List<AdminVO>  getFunctionAdminNo(Integer fx_no) {
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_FUNCTION_EMP_NO_STMT);
			pstmt.setInt(1, fx_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminNo(rs.getInt("Admin_No"));
				adminVO.setAdminName(rs.getString("Admin_Name"));
				adminVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				adminVO.setPhone(rs.getString("Admin_Phone"));
				adminVO.setMail(rs.getString("Admin_Mail"));
				adminVO.setAccount(rs.getString("Admin_Account"));
				adminVO.setPassword(rs.getString("Admin_Password"));
				adminVO.setPhoto(rs.getBytes("Admin_Photo"));
				adminVO.setState(rs.getInt("Admin_State"));
				list.add(adminVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public AdminVO findByPrimaryKey(Integer admin_No) {
		//=========================管理員搜尋===================================
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, admin_No);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminNo(rs.getInt("Admin_No"));
				adminVO.setAdminName(rs.getString("Admin_Name"));
				adminVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				adminVO.setPhone(rs.getString("Admin_Phone"));
				adminVO.setMail(rs.getString("Admin_Mail"));
				adminVO.setAccount(rs.getString("Admin_Account"));
				adminVO.setPassword(rs.getString("Admin_Password"));
				adminVO.setPhoto(rs.getBytes("Admin_Photo"));
				adminVO.setState(rs.getInt("Admin_State"));
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
		return adminVO;
	}

	@Override
	//=========================查詢所有管理員===================================
	public List<AdminVO> getAll() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminNo(rs.getInt("Admin_No"));
				adminVO.setAdminName(rs.getString("Admin_Name"));
				adminVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				adminVO.setPhone(rs.getString("Admin_Phone"));
				adminVO.setMail(rs.getString("Admin_Mail"));
				adminVO.setAccount(rs.getString("Admin_Account"));
				adminVO.setPassword(rs.getString("Admin_Password"));
				adminVO.setPhoto(rs.getBytes("Admin_Photo"));
				adminVO.setState(rs.getInt("Admin_State"));
				list.add(adminVO); // Store the row in the list
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//=========================管理員"複合查詢" 未使用===================================
	public List<AdminVO> getAll(Map<String, String[]> map) {
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminNo(rs.getInt("Admin_No"));
				adminVO.setAdminName(rs.getString("Admin_Name"));
				adminVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				adminVO.setPhone(rs.getString("Admin_Phone"));
				adminVO.setMail(rs.getString("Admin_Mail"));
				adminVO.setAccount(rs.getString("Admin_Account"));
				adminVO.setPassword(rs.getString("Admin_Password"));
				adminVO.setPhoto(rs.getBytes("Admin_Photo"));
				adminVO.setState(rs.getInt("Admin_State"));
				list.add(adminVO); // Store the row in the list
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AdminVO findByAcAndEmail(String account, String mail) {
//=========================管理員忘記密碼，以信箱和帳號找回原密碼===================================
		Connection con = null;
		PreparedStatement pstmt = null;
		AdminVO adminVO = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FOGETPASSWORD);

			pstmt.setString(1, account);
			pstmt.setString(2, mail);

//			pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminNo(rs.getInt("Admin_No"));
				adminVO.setAdminName(rs.getString("Admin_Name"));
				adminVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				adminVO.setPhone(rs.getString("Admin_Phone"));
				adminVO.setMail(rs.getString("Admin_Mail"));
				adminVO.setAccount(rs.getString("Admin_Account"));
				adminVO.setPassword(rs.getString("Admin_Password"));
				adminVO.setPhoto(rs.getBytes("Admin_Photo"));
				adminVO.setState(rs.getInt("Admin_State"));
			}
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
		return adminVO;
	}

	@Override
	public AdminVO findByEmail(String mail) {
//=========================新增管理員時，不可使用相同信箱===================================
		Connection con = null;
		PreparedStatement pstmt = null;
		AdminVO adminVO = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FINDMAIL);

			pstmt.setString(1, mail);

			pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminNo(rs.getInt("Admin_No"));
				adminVO.setAdminName(rs.getString("Admin_Name"));
				adminVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				adminVO.setPhone(rs.getString("Admin_Phone"));
				adminVO.setMail(rs.getString("Admin_Mail"));
				adminVO.setAccount(rs.getString("Admin_Account"));
				adminVO.setPassword(rs.getString("Admin_Password"));
				adminVO.setPhoto(rs.getBytes("Admin_Photo"));
				adminVO.setState(rs.getInt("Admin_State"));
			}
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
		return adminVO;
	}
	@Override
	public AdminVO findByAccount(String account) {
//=========================新增管理員時，檢查是否有用到相同帳號===================================
		Connection con = null;
		PreparedStatement pstmt = null;
		AdminVO adminVO = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FINDAC);

			pstmt.setString(1, account);

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
		return adminVO;
	}
	
	public List<AdminVO> findOneACCESS(Integer adminno) {
//=========================列出管理員的權限，待修正===================================
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
//=========================不使用JOIN方法，而是直接呼叫accessVO物件===================================
			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminNo(rs.getInt("Admin_No"));
				adminVO.setAdminName(rs.getString("Admin_Name"));
				adminVO.setAdminTitleNo(rs.getInt("Admin_Title_No"));
				adminVO.setPhone(rs.getString("Admin_Phone"));
				adminVO.setMail(rs.getString("Admin_Mail"));
				adminVO.setAccount(rs.getString("Admin_Account"));
				adminVO.setPassword(rs.getString("Admin_Password"));
				adminVO.setPhoto(rs.getBytes("Admin_Photo"));
				adminVO.setState(rs.getInt("Admin_State"));
				list.add(adminVO); // Store the row in the list
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//-----------------------------以下main方法測試-----------------------------------
	public static void main(String[] args) {

		AdminJDBCDAO dao = new AdminJDBCDAO();

		// 新增
		AdminVO adminVO1 = new AdminVO();
//
		adminVO1.setAdminName("陳小美");
		adminVO1.setAdminTitleNo(1);
		adminVO1.setPhone("0912345678");
		adminVO1.setMail("jj@gmail.com");
		adminVO1.setAccount("test111ww");
		adminVO1.setPassword("p@ssword");
		adminVO1.setState(1);
		File file = new File("image/none.jpg");
		byte[] b = new byte[(int) file.length()];
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			fis.read(b);
			adminVO1.setPhoto(b);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(adminVO1);

		// �ק�
//		AdminVO adminVO2 = new AdminVO();
//		adminVO2.setAdminNo(1);
//		adminVO2.setAdminName("王小智");
//		adminVO2.setAdminTitleNo(1);
//		adminVO2.setPhone("0910000000");
//		adminVO2.setMail("aa@gmail.com");
//		adminVO2.setAccount("myaccnt");
//		adminVO2.setPassword("p@ssword");
//		File file1 = new File("image/none.jpg");
//		byte[] b1 = new byte[(int) file1.length()];
//		FileInputStream fis1;
//		try {
//			fis1 = new FileInputStream(file1);
//			fis1.read(b1);
//			adminVO2.setPhoto(b1);
//			fis1.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		adminVO2.setState(2);
//		dao.update(adminVO2);

		// 刪除
//		dao.delete(1);
//
//		// 單一搜尋
//		AdminVO adminVO3 = dao.findByAcAndEmail("A0002","admin@gmail.com");
//		AdminVO adminVO3 = dao.findByPrimaryKey(1);
//		System.out.print(adminVO3.getAdminName() + ",");
//		System.out.print(adminVO3.getAdminTitleNo() + ",");
//		System.out.print(adminVO3.getPhone() + ",");
//		System.out.print(adminVO3.getMail() + ",");
//		System.out.print(adminVO3.getAccount() + ",");
//		System.out.print(adminVO3.getPassword() + ",");
//		System.out.println("---------------------");
//
//		// 全部搜尋
//		List<AdminVO> list = dao.getAll();
//		for (AdminVO aEmp : list) {
//			System.out.print(aEmp.getAdminName() + ",");
//			System.out.print(aEmp.getAdminNo() + ",");
//			System.out.print(aEmp.getAdminTitleNo() + ",");
//			System.out.print(aEmp.getPhone() + ",");
//			System.out.print(aEmp.getMail() + ",");
//			System.out.println();
//		}
		List<AdminVO> list1 = dao.getFunctionAdminNo(1);
		for (AdminVO aEmp : list1) {
			System.out.print(aEmp.getAdminName() + ",");
			System.out.print(aEmp.getAdminNo() + ",");
			System.out.print(aEmp.getAdminTitleNo() + ",");
			System.out.print(aEmp.getPhone() + ",");
			System.out.print(aEmp.getMail() + ",");
			System.out.println();
		}
	}
}