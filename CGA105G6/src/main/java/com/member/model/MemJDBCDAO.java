package com.member.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.Blob;

public class MemJDBCDAO implements Mem_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO member_data (MEM_NAME,MEM_TEL,Mem_City,Mem_Birth,Mem_Dist,Mem_Add,Mem_EMail,Mem_Account,Mem_Password,Mem_Photo,Mem_Access,Article_Report,Message_Report) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM member_data order by MEM_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM member_data where MEM_NO = ?";
	private static final String DELETE = "DELETE FROM member_data where mem_No = ?";
	private static final String UPDATE = "UPDATE member_data set Mem_Name=?,Mem_Tel=?,Mem_City=?,Mem_Birth=?,Mem_Dist=?,Mem_Add=?,Mem_EMail=?,Mem_Account=?,Mem_Password=?,Mem_Photo=?,Mem_Access=?,Article_Report=?,Message_Report=?  where MEM_NO = ?";
	private static final String FIND_PASSWORD = "SELECT mem_password from member_data where mem_Account=? and mem_email = ? ";
	private static final String CHECK_ACCOUNT = "SELECT mem_Account from member_data where mem_Account = ?";
	private static final String UPDATESTATUS = "UPDATE member_data set mem_state=? where MEM_NO=?";
	private static final String SHOW_INFO = "select * from MEMBER_data where MEM_ACCOUNT = ? and MEM_PASSWORD = ?";
	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getMemTel());
			pstmt.setString(3, memVO.getMemCity());
			pstmt.setDate(4, memVO.getMemBirth());
			pstmt.setString(5, memVO.getMemDist());
			pstmt.setString(6, memVO.getMemAdd());
			pstmt.setString(7, memVO.getMemMail());
			pstmt.setString(8, memVO.getMemAccount());
			pstmt.setString(9, memVO.getMemPassword());
			pstmt.setBytes(10, memVO.getMemPhoto());
			pstmt.setInt(11, memVO.getMemAccess());
			pstmt.setInt(12, memVO.getArticleReport());
			pstmt.setInt(13, memVO.getMessageReport());
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
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getMemTel());
			pstmt.setString(3, memVO.getMemCity());
			pstmt.setDate(4, memVO.getMemBirth());
			pstmt.setString(5, memVO.getMemDist());
			pstmt.setString(6, memVO.getMemAdd());
			pstmt.setString(7, memVO.getMemMail());
			pstmt.setString(8, memVO.getMemAccount());
			pstmt.setString(9, memVO.getMemPassword());
			pstmt.setBytes(10, memVO.getMemPhoto());
			pstmt.setInt(11, memVO.getMemAccess());
			pstmt.setInt(12, memVO.getArticleReport());
			pstmt.setInt(13, memVO.getMessageReport());
			pstmt.setInt(14, memVO.getMemNo());
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
	public void delete(Integer memNO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memNO);
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
	public MemVO findByPrimaryKey(Integer memNo) {

		MemVO memVO = null;
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
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("meM_No"));
				memVO.setMemName(rs.getString("mem_Name"));
				memVO.setMemTel(rs.getString("mem_Tel"));
				memVO.setMemCity(rs.getString("mem_City"));
				memVO.setMemBirth(rs.getDate("mem_Birth"));
				memVO.setMemDist(rs.getString("mem_Dist"));
				memVO.setMemAdd(rs.getString("mem_Add"));
				memVO.setMemMail(rs.getString("mem_EMail"));
				memVO.setMemAccount(rs.getString("mem_Account"));
				memVO.setMemPassword(rs.getString("mem_Password"));
				memVO.setMemPhoto(rs.getBytes("mem_Photo"));
				memVO.setMemAccess(rs.getInt("mem_Access"));
				memVO.setArticleReport(rs.getInt("article_Report"));
				memVO.setMessageReport(rs.getInt("message_Report"));

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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO=new MemVO();
				memVO.setMemNo(rs.getInt("meM_No"));
				memVO.setMemName(rs.getString("mem_Name"));
				memVO.setMemTel(rs.getString("mem_Tel"));
				memVO.setMemCity(rs.getString("mem_City"));
				memVO.setMemBirth(rs.getDate("mem_Birth"));
				memVO.setMemDist(rs.getString("mem_Dist"));
				memVO.setMemAdd(rs.getString("mem_Add"));
				memVO.setMemMail(rs.getString("mem_EMail"));
				memVO.setMemAccount(rs.getString("mem_Account"));
				memVO.setMemPassword(rs.getString("mem_Password"));
				memVO.setMemPhoto(rs.getBytes("mem_Photo"));
				memVO.setMemAccess(rs.getInt("mem_Access"));
				memVO.setArticleReport(rs.getInt("article_Report"));
				memVO.setMessageReport(rs.getInt("message_Report"));

				list.add(memVO); // Store the row in the list
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
	

	public MemVO findPassword(String memAccount,String mem_email) {
		
		MemVO memVO = null;
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try {
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,userid,passwd);
		pstmt = con.prepareStatement(FIND_PASSWORD);
		
		pstmt.setString(1, memAccount);
		pstmt.setString(2, mem_email);

		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			memVO = new MemVO();
			memVO.setMemPassword(rs.getString("mem_Password")); 
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
	return memVO;
}

	public MemVO checkAccount(String memAccount) {
		MemVO memVO = null;
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try {
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,userid,passwd);
		pstmt = con.prepareStatement(CHECK_ACCOUNT);
		
		pstmt.setString(1, memAccount);

		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			memVO = new MemVO();
			memVO.setMemAccount(rs.getString("mem_Account")); 
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
	return memVO;
}
	public void updateStatus(Integer mem_id, Integer state) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, mem_id);
			pstmt.setInt(2, state);
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
	
	
	public List<MemVO> getAllMem(Map<String, String[]> map) {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String MySql = "select * from member_data "
					+MemUtil.getWhereCondition(map)
					+"order by mem_No";
			pstmt = con.prepareStatement(MySql);
			rs = pstmt.executeQuery();
		
			System.out.println(MySql);
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("mem_no"));
				memVO.setMemName(rs.getString("mem_Name"));
				memVO.setMemTel(rs.getString("mem_Tel"));
				memVO.setMemCity(rs.getString("mem_City"));
				memVO.setMemBirth(rs.getDate("mem_Birth"));
				memVO.setMemDist(rs.getString("mem_Dist"));
				memVO.setMemAdd(rs.getString("mem_Add"));
				memVO.setMemMail(rs.getString("mem_EMail"));
				memVO.setMemAccount(rs.getString("mem_Account"));
				memVO.setMemPassword(rs.getString("mem_Password"));
				memVO.setMemPhoto(rs.getBytes("mem_Photo"));
				memVO.setMemAccess(rs.getInt("mem_Access"));
				memVO.setArticleReport(rs.getInt("article_Report"));
				memVO.setMessageReport(rs.getInt("message_Report"));
				list.add(memVO);
			}


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || pstmt != null || con != null) {
				try {
					rs.close();
					pstmt.close();
					con.close();
					
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public MemVO findByAcAndPwd(String memAccount, String memPassword) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SHOW_INFO);
			pstmt.setString(1, memAccount);
			pstmt.setString(2, memPassword);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("mem_No"));
				memVO.setMemName(rs.getString("mem_Name"));
				memVO.setMemTel(rs.getString("mem_Tel"));
				memVO.setMemCity(rs.getString("mem_City"));
				memVO.setMemBirth(rs.getDate("mem_Birth"));
				memVO.setMemDist(rs.getString("mem_Dist"));
				memVO.setMemAdd(rs.getString("mem_Add"));
				memVO.setMemMail(rs.getString("mem_EMail"));
				memVO.setMemAccount(rs.getString("mem_Account"));
				memVO.setMemPassword(rs.getString("mem_Password"));
				memVO.setMemPhoto(rs.getBytes("mem_Photo"));
				memVO.setMemAccess(rs.getInt("mem_Access"));
				memVO.setArticleReport(rs.getInt("article_Report"));
				memVO.setMessageReport(rs.getInt("message_Report"));
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
		return memVO;
	};	
	
	
	
	public static void main(String[] args) {

		MemJDBCDAO dao = new MemJDBCDAO();

		// �s�W
//		MemVO memVO1 = new MemVO();
//		memVO1.setMemName("林小明");
//		memVO1.setMemTel("0123456789");
//		memVO1.setMemCity("新竹市");
//		memVO1.setMemBirth(java.sql.Date.valueOf("2012-10-10"));
//		memVO1.setMemDist("東區");
//		memVO1.setMemAdd("中正路");
//		memVO1.setMemMail("tmpss");
//		memVO1.setMemAccount("12345");
//		memVO1.setMemPassword("22332456");
//		File file = new File("image/none.jpg");
//		byte[] b = new byte[(int) file.length()];
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream(file);
//			fis.read(b);
//			memVO1.setMemPhoto(b);
//			fis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		memVO1.setMemAccess(1);
//		memVO1.setArticleReport(3);
//		memVO1.setMessageReport(3);
//		dao.insert(memVO1);

		// �ק�
//		MemVO memVO2 = new MemVO();
//		memVO2.setMemNo(1);
//		memVO2.setMemName("王曉華");
//		memVO2.setMemTel("01234");
//		memVO2.setMemCity("新竹市");
//		memVO2.setMemBirth(java.sql.Date.valueOf("2012-10-10"));
//		memVO2.setMemDist("東區");
//		memVO2.setMemAdd("中正街");
//		memVO2.setMemMail("tmpss@");
//		memVO2.setMemAccount("aa123");
//		memVO2.setMemPassword("456");
//		memVO2.setMemPhoto();
//		memVO2.setMemAccess(1);
//		memVO2.setArticleReport(3);
//		memVO2.setMessageReport(3);
//		dao.update(memVO2);

		// 
//		dao.delete(1);

		
//		MemVO memVO3 = dao.findByPrimaryKey(1);
//		
//		System.out.print(memVO3.getMemNo() + ",");
//		System.out.print(memVO3.getMemName() + ",");
//		System.out.print(memVO3.getMemTel() + ",");
//		System.out.print(memVO3.getMemCity() + ",");
//		System.out.print(memVO3.getMemBirth() + ",");
//		System.out.print(memVO3.getMemDist() + ",");
//		System.out.print(memVO3.getMemAdd() + ",");
//		System.out.print(memVO3.getMemMail() + ",");
//		System.out.print(memVO3.getMemAccount() + ",");
//		System.out.print(memVO3.getMemPassword() + ",");
//		System.out.print(memVO3.getArticleReport() + ",");
//		System.out.print(memVO3.getMessageReport() + ",");
//
//		System.out.println("---------------------");

		
		List<MemVO> list = dao.getAll();
		for (MemVO aEmp : list) {
			System.out.print(aEmp.getMemName() + ",");
			System.out.print(aEmp.getMemTel() + ",");
			System.out.print(aEmp.getMemCity() + ",");
			System.out.print(aEmp.getMemBirth() + ",");
			System.out.print(aEmp.getMemDist() + ",");
			System.out.print(aEmp.getMemAdd() + ",");
			System.out.print(aEmp.getMemMail() + ",");
			System.out.print(aEmp.getMemAccount() + ",");
			System.out.print(aEmp.getMemPassword() + ",");
			System.out.print(aEmp.getArticleReport() + ",");
			System.out.print(aEmp.getMessageReport() + ",");
			System.out.println();
		}
	}

}
