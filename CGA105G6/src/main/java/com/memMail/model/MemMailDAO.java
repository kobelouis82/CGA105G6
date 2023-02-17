package com.memMail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.admin.model.AdminJDBCDAO;
import com.admin.model.AdminVO;


public class MemMailDAO implements MemMailDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO mem_mail (Send_No,Rcpt_No,Mail_Read_Stat,Mail_Stat,Mail_Cont,Mail_Time,mail_title) VALUES (?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT mail_no,Send_No,Rcpt_No,Mail_Read_Stat,Mail_Stat,Mail_Cont,Mail_Time,mail_title  FROM mem_mail order by Mail_Time desc";
	private static final String GET_ALL_SEND_STMT = "SELECT mail_no,Send_No,Rcpt_No,Mail_Read_Stat,Mail_Stat,Mail_Cont,Mail_Time,mail_title FROM mem_mail where SendNo = ? order by MailTime desc";
	private static final String GET_ALL_RCPT_STMT = "SELECT mail_no,Send_No,Rcpt_No,Mail_Read_Stat,Mail_Stat,Mail_Cont,Mail_Time,mail_title FROM mem_mail where SendNo = ? order by MailTime desc";
	private static final String GET_ALL_STAT_STMT = "SELECT mail_no,Send_No,Rcpt_No,Mail_Read_Stat,Mail_Stat,Mail_Cont,Mail_Time,mail_title FROM mem_mail where MailStat = ? order by MailTime desc";
	private static final String GET_ONE_STMT = "SELECT mail_no,Send_No,Rcpt_No,Mail_Read_Stat,Mail_Stat,Mail_Cont,Mail_Time,mail_title FROM mem_mail where mail_no = ?";
	private static final String DELETE = "DELETE FROM mem_mail where mail_no = ?";
	private static final String UPDATE = "UPDATE mem_mail set Send_No=?, Rcpt_No=?, Mail_Read_Stat=?, Mail_Stat=? ,Mail_Cont=?, Mail_Time=? ,mail_title=? where mail_no = ?";

	@Override
	public void insert(MemMailVO memMailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memMailVO.getSendNo());
			pstmt.setInt(2, memMailVO.getRcptNo());
			pstmt.setInt(3, memMailVO.getMailReadStat());
			pstmt.setInt(4, memMailVO.getMailStat());
			pstmt.setString(5, memMailVO.getMailCont());
			pstmt.setString(6, memMailVO.getMailTime());
			pstmt.setString(7, memMailVO.getMailTitle());

			pstmt.executeUpdate();

//			

			MemMailDAO member_mailDAO = new MemMailDAO();
			Integer SendNo = memMailVO.getSendNo();
			Integer RcptNo = memMailVO.getRcptNo();
			Integer MailReadStat = 0;
			Integer MailStat = 0;
			String MailCont = memMailVO.getMailCont();
			String MailTime = memMailVO.getMailTime();
			String MailTitle = memMailVO.getMailTitle();
			MemMailVO memMailVORcpt = new MemMailVO(SendNo, RcptNo, MailReadStat, MailStat, MailCont, MailTime,
					MailTitle);
			member_mailDAO.insertWithMbr(memMailVORcpt, con);

			con.commit();
			con.setAutoCommit(true);

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-��-member_mail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void update(MemMailVO memMailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memMailVO.getSendNo());
			pstmt.setInt(2, memMailVO.getRcptNo());
			pstmt.setInt(3, memMailVO.getMailReadStat());
			pstmt.setInt(4, memMailVO.getMailStat());
			pstmt.setString(5, memMailVO.getMailCont());
			pstmt.setString(6, memMailVO.getMailTime());
			pstmt.setString(7, memMailVO.getMailTitle());
			pstmt.setInt(8, memMailVO.getMailNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void delete(Integer mail_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mail_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public MemMailVO findByPrimaryKey(Integer mail_no) {

		MemMailVO memMailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mail_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMailNo(rs.getInt("mail_no"));
				memMailVO.setSendNo(rs.getInt("Send_No"));
				memMailVO.setRcptNo(rs.getInt("Rcpt_No"));
				memMailVO.setMailReadStat(rs.getInt("Mail_Read_Stat"));
				memMailVO.setMailStat(rs.getInt("Mail_Stat"));
				memMailVO.setMailCont(rs.getString("Mail_Cont"));
				memMailVO.setMailTime(rs.getString("Mail_Time"));
				memMailVO.setMailTitle(rs.getString("Mail_Title"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
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
		return memMailVO;
	}

	@Override
	public List<MemMailVO> getAll() {
		List<MemMailVO> list = new ArrayList<MemMailVO>();
		MemMailVO memMailVO = null;

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
				memMailVO = new MemMailVO();
				memMailVO.setMailNo(rs.getInt("mail_no"));
				memMailVO.setSendNo(rs.getInt("Send_No"));
				memMailVO.setRcptNo(rs.getInt("Rcpt_No"));
				memMailVO.setMailReadStat(rs.getInt("Mail_Read_Stat"));
				memMailVO.setMailStat(rs.getInt("Mail_Stat"));
				memMailVO.setMailCont(rs.getString("Mail_Cont"));
				memMailVO.setMailTime(rs.getString("Mail_Time"));
				memMailVO.setMailTitle(rs.getString("Mail_Title"));
				list.add(memMailVO); // Store the row in the list
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

	
//	
	@Override
	public void insertWithMbr(MemMailVO memMailVO, Connection con) {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memMailVO.getSendNo());
			pstmt.setInt(2, memMailVO.getRcptNo());
			pstmt.setInt(3, memMailVO.getMailReadStat());
			pstmt.setInt(4, memMailVO.getMailStat());
			pstmt.setString(5, memMailVO.getMailCont());
			pstmt.setString(6, memMailVO.getMailTime());
			pstmt.setString(7, memMailVO.getMailTitle());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-��-member_mail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}
	}

//	

	@Override
	public List<MemMailVO> getSend(Integer SendNo) {
		List<MemMailVO> list = new ArrayList<MemMailVO>();
		MemMailVO memMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_SEND_STMT);
			pstmt.setInt(1, SendNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMailNo(rs.getInt("mail_no"));
				memMailVO.setSendNo(rs.getInt("Send_No"));
				memMailVO.setRcptNo(rs.getInt("Rcpt_No"));
				memMailVO.setMailReadStat(rs.getInt("Mail_Read_Stat"));
				memMailVO.setMailStat(rs.getInt("Mail_Stat"));
				memMailVO.setMailCont(rs.getString("Mail_Cont"));
				memMailVO.setMailTime(rs.getString("Mail_Time"));
				memMailVO.setMailTitle(rs.getString("Mail_Title"));
				list.add(memMailVO); // Store the row in the list
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
	public List<MemMailVO> getRcpt(Integer RcptNo) {
		List<MemMailVO> list = new ArrayList<MemMailVO>();
		MemMailVO memMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_RCPT_STMT);
			pstmt.setInt(1, RcptNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memMailVO = new MemMailVO();
				memMailVO.setMailNo(rs.getInt("mail_no"));
				memMailVO.setSendNo(rs.getInt("Send_No"));
				memMailVO.setRcptNo(rs.getInt("Rcpt_No"));
				memMailVO.setMailReadStat(rs.getInt("Mail_Read_Stat"));
				memMailVO.setMailStat(rs.getInt("Mail_Stat"));
				memMailVO.setMailCont(rs.getString("Mail_Cont"));
				memMailVO.setMailTime(rs.getString("Mail_Time"));
				memMailVO.setMailTitle(rs.getString("Mail_Title"));
				list.add(memMailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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
	public List<MemMailVO> getStat(Integer MailStat) {

		List<MemMailVO> list = new ArrayList<MemMailVO>();
		MemMailVO memMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STAT_STMT);
			pstmt.setInt(1, MailStat);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMailNo(rs.getInt("mail_no"));
				memMailVO.setSendNo(rs.getInt("Send_No"));
				memMailVO.setRcptNo(rs.getInt("Rcpt_No"));
				memMailVO.setMailReadStat(rs.getInt("Mail_Read_Stat"));
				memMailVO.setMailStat(rs.getInt("Mail_Stat"));
				memMailVO.setMailCont(rs.getString("Mail_Cont"));
				memMailVO.setMailTime(rs.getString("Mail_Time"));
				memMailVO.setMailTitle(rs.getString("Mail_Title"));
				list.add(memMailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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

	public static void main(String[] args) {
		
		MemMailDAO dao = new MemMailDAO();
		MemMailVO adminVO3 = dao.findByPrimaryKey(1);
		System.out.print(adminVO3.getMailCont() + ",");
		System.out.print(adminVO3.getMailTime() + ",");
		System.out.println("---------------------");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.format(new java.util.Date());
		MemMailVO member_mailVO = new MemMailVO(3, 1, 0, 0, "這是內容123", sdf.format(new java.util.Date()),"test1 title");
		dao.insert(member_mailVO);
		
		List<MemMailVO> list = dao.getAll();
		for (MemMailVO aEmp : list) {
			System.out.print(aEmp.getMailCont() + ",");
			System.out.print(aEmp.getMailTime() + ",");
			System.out.print(aEmp.getSendNo() + ",");
			System.out.println();
		}
	}
}