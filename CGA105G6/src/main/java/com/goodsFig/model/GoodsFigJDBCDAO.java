package com.goodsFig.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.*;

public class GoodsFigJDBCDAO implements GoodsFigDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO goods_fig(GAME_PIC,SERIAL_NO) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT FIG_NO,GAME_PIC,SERIAL_NO FROM cga105g6.goods_fig order by FIG_NO";
	private static final String GET_ONE_STMT = "SELECT FIG_NO,GAME_PIC,SERIAL_NO FROM cga105g6.goods_fig where serial_no = ?";
	private static final String DELETE = "DELETE cga105g6.goods_fig FROM  where FIG_NO = ?";
	private static final String UPDATE = "UPDATE cga105g6.goods_fig set FIG_NO=?,GAME_PIC=?,SERIAL_NO=? where FIG_NO = ?";

	@Override
	public void insert(GoodsFigVO goodsFigVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			File imageFile = new File("C:\\Users\\Tibame_T14\\Desktop\\gmaesfig\\games.jpg");
//			byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
//			InputStream in = Files.newInputStream(Path.of("C:\\\\Users\\\\Tibame_T14\\\\Desktop\\\\gmaesfig\\\\games.jpg"));
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setBytes(1, goodsFigVO.getGamePic());
			pstmt.setInt(2, goodsFigVO.getSerialNo());
			
			

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
	public void update(GoodsFigVO goodsFigVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, goodsFigVO.getFigNo());
			pstmt.setBytes(2, goodsFigVO.getGamePic());
			pstmt.setInt(3, goodsFigVO.getSerialNo());
			pstmt.setInt(4, goodsFigVO.getFigNo());
			
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
	public void delete(Integer figNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public GoodsFigVO findByPrimaryKey(Integer serialNo) {
		GoodsFigVO goodsFigVO = null;
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

				goodsFigVO = new GoodsFigVO();

				goodsFigVO.setFigNo(rs.getInt("FIG_NO"));
				goodsFigVO.setGamePic(rs.getBytes("GAME_PIC"));
				goodsFigVO.setSerialNo(rs.getInt("SERIAL_NO"));
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
		return goodsFigVO;
	}

	@Override
	public List<GoodsFigVO> getAll() {
		List<GoodsFigVO> list = new ArrayList<GoodsFigVO>();
		GoodsFigVO goodsFigVO = null;

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
				goodsFigVO = new GoodsFigVO();
				goodsFigVO.setFigNo(rs.getInt("FIG_NO"));
				goodsFigVO.setGamePic(rs.getBytes("GAME_PIC"));
				goodsFigVO.setSerialNo(rs.getInt("SERIAL_NO"));
				list.add(goodsFigVO); // Store the row in the list
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
	public static void main(String[] args) throws IOException {

		GoodsFigJDBCDAO dao = new GoodsFigJDBCDAO();

		// �s�W
//		File imageFile = new File("C:\\Users\\Tibame_T14\\Desktop\\gmaesfig\\games.jpg");
//		byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
//		GoodsFigVO GoodsFigVO1 = new GoodsFigVO();
//		GoodsFigVO1.setFigNo(6);
//		GoodsFigVO1.setGamePic(imageBytes);
//		GoodsFigVO1.setSerialNo(6);
//		dao.insert(GoodsFigVO1);

//		
		// �ק�
	File imageFile = new File("C:\\Users\\Tibame_T14\\Desktop\\gmaesfig\\5.jpg");
	byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
	GoodsFigVO GoodsFigVO2 = new GoodsFigVO();
	
	GoodsFigVO2.setFigNo(5);
	GoodsFigVO2.setGamePic(imageBytes);
	GoodsFigVO2.setSerialNo(5);
	dao.update(GoodsFigVO2);
//
		// �R��
//		dao.delete(9);

		// �d��
	GoodsFigVO GoodsFigVO3 = dao.findByPrimaryKey(1);
	System.out.print(GoodsFigVO3.getFigNo() + ",");
	System.out.print(GoodsFigVO3.getGamePic() + ",");
	System.out.print(GoodsFigVO3.getSerialNo() + ",");


		List<GoodsFigVO> list = dao.getAll();
		for (GoodsFigVO aGoodsFigVO : list) {
			System.out.print(aGoodsFigVO.getFigNo() + ",");
			System.out.print(aGoodsFigVO.getGamePic() + ",");
			System.out.print(aGoodsFigVO.getSerialNo() + ",");
			System.out.println();
		}
	}
}


