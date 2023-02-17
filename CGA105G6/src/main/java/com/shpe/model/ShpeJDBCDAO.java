package com.shpe.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.*;


public class ShpeJDBCDAO implements ShpeDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO SECOND_HAND_PRODUCT_EVALUATION (GAME_CODE,MEM_NO,PRODUCT_PHOTOS,ESTIMATE,DISK_BOX,DISK,DISK_FROM,SHP_DESCRIPTION) VALUES(?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT APPLICATION_NO,GAME_CODE,MEM_NO,PRODUCT_PHOTOS,ESTIMATE,DISK_BOX,DISK,DISK_FROM,SHP_DESCRIPTION FROM SECOND_HAND_PRODUCT_EVALUATION order by APPLICATION_NO ";
	private static final String GET_ONE_STMT = "SELECT APPLICATION_NO,GAME_CODE,MEM_NO,PRODUCT_PHOTOS,ESTIMATE,DISK_BOX,DISK,DISK_FROM,SHP_DESCRIPTION FROM SECOND_HAND_PRODUCT_EVALUATION where APPLICATION_NO = ?";
	private static final String DELETE = "DELETE FROM SECOND_HAND_PRODUCT_EVALUATION where APPLICATION_NO = ?";
	private static final String UPDATE = "UPDATE SECOND_HAND_PRODUCT_EVALUATION set GAME_CODE = ?, MEM_NO = ?, PRODUCT_PHOTOS = ?, ESTIMATE = ?, DISK_BOX = ?, `DISK`= ?, DISK_FROM = ?, SHP_DESCRIPTION = ? where APPLICATION_NO = ?";
	private static final String selectAppNo = "SELECT APPLICATION_NO,RECYCLE_STATE,ITEM_NAME,DISK_BOX,DISK,DISK_FROM,ESTIMATE FROM SECOND_HAND_PRODUCT_EVALUATION where APPLICATION_NO =?";
	private static final String recycleAdd = "INSERT  INTO SECOND_HAND_PRODUCT_EVALUATION (MEM_NO,GAME_CODE,ITEM_NAME,ITEM_PRICE,DISK_BOX,DISK,DISK_FROM,ESTIMATE,RECYCLE_STATE) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String selectItem = "SELECT GAME_CODE,ITEM_NAME,ITEM_PRICE FROM SECOND_HAND_PRODUCT_EVALUATION where GAME_CODE =?";
	private static final String selectRecycleInf = "SELECT APPLICATION_NO,GAME_CODE,ITEM_NAME,DISK_BOX,DISK,DISK_FROM,ESTIMATE,RECYCLE_STATE FROM SECOND_HAND_PRODUCT_EVALUATION order by APPLICATION_NO";
	private static final String updateState = "UPDATE SECOND_HAND_PRODUCT_EVALUATION set GAME_CODE=?,ITEM_NAME=?,DISK_BOX=?,DISK=?,DISK_FROM=?,ESTIMATE=?,RECYCLE_STATE = ? where APPLICATION_NO = ?";
	private static final String selectRecycleMem = "SELECT MEM_NO,APPLICATION_NO,ITEM_NAME,DISK_BOX,DISK,DISK_FROM,ESTIMATE,RECYCLE_STATE FROM SECOND_HAND_PRODUCT_EVALUATION where MEM_NO =?";
	private static final String getRecycleState = "SELECT APPLICATION_NO,GAME_CODE,ITEM_NAME,DISK_BOX,DISK,DISK_FROM,ESTIMATE,RECYCLE_STATE FROM SECOND_HAND_PRODUCT_EVALUATION where APPLICATION_NO = ?";
	
	@Override
	public void insert(ShpeVO shpeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shpeVO.getGameCode());
			pstmt.setInt(2, shpeVO.getMemNo());
			pstmt.setBytes(3, shpeVO.getProductPhoto());
			pstmt.setInt(4, shpeVO.getEstimate());
			pstmt.setByte(5, shpeVO.getDiskBox());
			pstmt.setByte(6, shpeVO.getDisk());
			pstmt.setBoolean(7, shpeVO.getDiskFrom());
			pstmt.setString(8, shpeVO.getShpDescription());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ShpeVO shpeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, shpeVO.getGameCode());
			pstmt.setInt(2, shpeVO.getMemNo());
			pstmt.setBytes(3, shpeVO.getProductPhoto());
			pstmt.setInt(4, shpeVO.getEstimate());
			pstmt.setByte(5, shpeVO.getDiskBox());
			pstmt.setByte(6, shpeVO.getDisk());
			pstmt.setBoolean(7, shpeVO.getDiskFrom());
			pstmt.setString(8, shpeVO.getShpDescription());
			pstmt.setInt(9, shpeVO.getApplicationNo());
			File file = new File("image/None.jpg");
			byte[] b1 = new byte[(int) file.length()];

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer applicationNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, applicationNo);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ShpeVO findByPrimaryKey(Integer applicationNo) {

		ShpeVO shpeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, applicationNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shpeVO = new ShpeVO();
				shpeVO.setApplicationNo(rs.getInt("APPLICATION_NO"));
				shpeVO.setGameCode(rs.getString("GAME_CODE"));
				shpeVO.setMemNo(rs.getInt("MEM_NO"));
				shpeVO.setProductPhoto(rs.getBytes("PRODUCT_PHOTOS"));
				shpeVO.setEstimate(rs.getInt("ESTIMATE"));
				shpeVO.setDiskBox(rs.getByte("DISK_BOX"));
				shpeVO.setDisk(rs.getByte("DISK"));
				shpeVO.setDiskFrom(rs.getBoolean("DISK_FROM"));
				shpeVO.setShpDescription(rs.getString("SHP_DESCRIPTION"));
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
		return shpeVO;
	}

	@Override
	public List<ShpeVO> getAll() {
		List<ShpeVO> list = new ArrayList<ShpeVO>();
		ShpeVO shpeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				shpeVO = new ShpeVO();
				shpeVO.setApplicationNo(rs.getInt("APPLICATION_NO"));
				shpeVO.setGameCode(rs.getString("GAME_CODE"));
				shpeVO.setMemNo(rs.getInt("MEM_NO"));
				shpeVO.setProductPhoto(rs.getBytes("PRODUCT_PHOTOS"));
				shpeVO.setEstimate(rs.getInt("ESTIMATE"));
				shpeVO.setDiskBox(rs.getByte("DISK_BOX"));
				shpeVO.setDisk(rs.getByte("DISK"));
				shpeVO.setDiskFrom(rs.getBoolean("DISK_FROM"));
				shpeVO.setShpDescription(rs.getString("SHP_DESCRIPTION"));
				list.add(shpeVO);
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
	public ShpeVO selectAppNo(Integer applicationNo) {

		ShpeVO shpeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(selectAppNo);

			pstmt.setInt(1, applicationNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shpeVO = new ShpeVO();
				shpeVO.setApplicationNo(rs.getInt("APPLICATION_NO"));
				shpeVO.setRecycleState(rs.getInt("RECYCLE_STATE"));
				shpeVO.setItemName(rs.getString("ITEM_NAME"));
				shpeVO.setDiskBox(rs.getByte("DISK_BOX"));
				shpeVO.setDisk(rs.getByte("DISK"));
				shpeVO.setDiskFrom(rs.getBoolean("DISK_FROM"));
				shpeVO.setEstimate(rs.getInt("ESTIMATE"));

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
		return shpeVO;
	}

	@Override
	public void recycleAdd(ShpeVO shpeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(recycleAdd);
			
			pstmt.setInt(1, shpeVO.getMemNo());
			pstmt.setString(2, shpeVO.getGameCode());
			pstmt.setString(3, shpeVO.getItemName());
			pstmt.setInt(4, shpeVO.getItemPrice());
			pstmt.setByte(5, shpeVO.getDiskBox());
			pstmt.setByte(6, shpeVO.getDisk());
			pstmt.setBoolean(7, shpeVO.getDiskFrom());
			pstmt.setInt(8, shpeVO.getEstimate());
			pstmt.setInt(9, shpeVO.getRecycleState());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ShpeVO selectItem(String gameCode) {

		ShpeVO shpeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(selectItem);

			pstmt.setString(1, gameCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shpeVO = new ShpeVO();
				shpeVO.setGameCode(rs.getString("GAME_CODE"));
				shpeVO.setItemName(rs.getString("ITEM_NAME"));
				shpeVO.setItemPrice(rs.getInt("ITEM_PRICE"));

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
		return shpeVO;
	}

	@Override
	public List<ShpeVO> selectRecycleInf() {

		List<ShpeVO> list = new ArrayList<ShpeVO>();
		ShpeVO shpeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(selectRecycleInf);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				shpeVO = new ShpeVO();
				shpeVO.setApplicationNo(rs.getInt("APPLICATION_NO"));
				shpeVO.setGameCode(rs.getString("GAME_CODE"));
				shpeVO.setItemName(rs.getString("ITEM_NAME"));
				shpeVO.setDiskBox(rs.getByte("DISK_BOX"));
				shpeVO.setDisk(rs.getByte("DISK"));
				shpeVO.setDiskFrom(rs.getBoolean("DISK_FROM"));
				shpeVO.setEstimate(rs.getInt("ESTIMATE"));
				shpeVO.setRecycleState(rs.getInt("RECYCLE_STATE"));

				list.add(shpeVO);
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
	public void updateState(ShpeVO shpeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(updateState);
			
			pstmt.setNString(1, shpeVO.getGameCode());
			pstmt.setString(2, shpeVO.getItemName());
			pstmt.setByte(3, shpeVO.getDiskBox());
			pstmt.setByte(4, shpeVO.getDisk());
			pstmt.setBoolean(5, shpeVO.getDiskFrom());
			pstmt.setInt(6, shpeVO.getEstimate());
			pstmt.setInt(7, shpeVO.getRecycleState());			
			pstmt.setInt(8, shpeVO.getApplicationNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public List<ShpeVO> selectRecycleMem(Integer memNo) {

		List<ShpeVO> list = new ArrayList<ShpeVO>();
		ShpeVO shpeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(selectRecycleMem);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shpeVO = new ShpeVO();
				shpeVO.setMemNo(rs.getInt("MEM_NO"));
				shpeVO.setApplicationNo(rs.getInt("APPLICATION_NO"));
				shpeVO.setItemName(rs.getString("ITEM_NAME"));
				shpeVO.setDiskBox(rs.getByte("DISK_BOX"));
				shpeVO.setDisk(rs.getByte("DISK"));
				shpeVO.setDiskFrom(rs.getBoolean("DISK_FROM"));
				shpeVO.setEstimate(rs.getInt("ESTIMATE"));
				shpeVO.setRecycleState(rs.getInt("RECYCLE_STATE"));
				
				list.add(shpeVO);

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
	
	public ShpeVO getRecycleState(Integer applicationNo) {

		ShpeVO shpeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(getRecycleState);

			pstmt.setInt(1, applicationNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shpeVO = new ShpeVO();
				shpeVO.setApplicationNo(rs.getInt("APPLICATION_NO"));
				shpeVO.setGameCode(rs.getString("GAME_CODE"));
				shpeVO.setItemName(rs.getString("ITEM_NAME"));				
				shpeVO.setDiskBox(rs.getByte("DISK_BOX"));
				shpeVO.setDisk(rs.getByte("DISK"));
				shpeVO.setDiskFrom(rs.getBoolean("DISK_FROM"));
				shpeVO.setEstimate(rs.getInt("ESTIMATE"));
				shpeVO.setRecycleState(rs.getInt("RECYCLE_STATE"));
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
		return shpeVO;
	}

	public static void main(String[] args) {

		ShpeJDBCDAO dao = new ShpeJDBCDAO();

		// 新增
//		ShpeVO shpeVO = new ShpeVO();
//		shpeVO.setGameCode("1234567891234");
//		shpeVO.setMemNo(5);
//		shpeVO.setProductPhoto(null);
//		File file1 = new File("image/none.jpg");//測試用要先確認路徑
//		byte[] b1 = new byte[(int) file1.length()];
//		FileInputStream fis1;
//		try {
//			fis1 = new FileInputStream(file1);
//			fis1.read(b1);
//			shpeVO.setProductPhoto(b1);
//			fis1.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		shpeVO.setEstimate(2000);
//		shpeVO.setDiskBox(new Byte("1"));
//		shpeVO.setDisk(new Byte("1"));
//		shpeVO.setDiskFrom(false);
//		shpeVO.setShpDescription("遊戲太簡單一下就破關");
//		dao.insert(shpeVO);

		// 修改
//		ShpeVO shpeVO2 = new ShpeVO();
//
//		shpeVO2.setGameCode("4321987654321");
//		shpeVO2.setMemNo(5);
//		shpeVO2.setProductPhoto(null);
//		shpeVO2.setEstimate(10000);
//		shpeVO2.setDiskBox(new Byte("5"));
//		shpeVO2.setDisk(new Byte("5"));
//		shpeVO2.setDiskFrom(false);
//		shpeVO2.setShpDescription("遊戲太難隨便賣");
//		shpeVO2.setApplicationNo(6);
//		
//		dao.update(shpeVO2);

		// 刪除
//		dao.delete(6);

		// 查詢
//		ShpeVO shpeVO3 = dao.findByPrimaryKey(1);
//		System.out.print(shpeVO3.getApplicationNo()+",");
//		System.out.print(shpeVO3.getGameCode()+",");
//		System.out.print(shpeVO3.getMemNo()+",");
//		System.out.print(shpeVO3.getProductPhoto()+",");
//		System.out.print(shpeVO3.getEstimate()+",");
//		System.out.print(shpeVO3.getDiskBox()+",");
//		System.out.print(shpeVO3.getDisk()+",");
//		System.out.print(shpeVO3.isDiskFrom()+",");
//		System.out.print(shpeVO3.getShpDescription());
//		System.out.println("--------------------------------");

		// 查詢全部
//		List<ShpeVO> list = dao.getAll();
//		for (ShpeVO shpes : list) {
//			System.out.print(shpes.getApplicationNo()+",");
//			System.out.print(shpes.getGameCode()+",");
//			System.out.print(shpes.getMemNo()+",");
//			System.out.print(shpes.getProductPhoto()+",");
//			System.out.print(shpes.getEstimate()+",");
//			System.out.print(shpes.getDiskBox()+",");
//			System.out.print(shpes.getDisk()+",");
//			System.out.print(shpes.isDiskFrom()+",");
//			System.out.print(shpes.getShpDescription());
//			System.out.println();				
//			
//		}

		// 查詢部分
//		ShpeVO shpeVO4 = dao.selectAppNo(1);
//		System.out.print(shpeVO4.getApplicationNo()+",");
//		System.out.print(shpeVO4.getRecycleState()+",");
//		System.out.print(shpeVO4.getItemName()+",");
//		System.out.print(shpeVO4.getDiskBox()+",");
//		System.out.print(shpeVO4.getDisk()+",");
//		System.out.print(shpeVO4.getDiskFrom()+",");
//		System.out.print(shpeVO4.getEstimate()+",");

		// 新增部分
//		ShpeVO shpeVO = new ShpeVO();
//		shpeVO.setItemName("PS4 隻狼");;
//		shpeVO.setItemPrice(1790);
//		shpeVO.setDiskBox(new Byte("2"));
//		shpeVO.setDisk(new Byte("3"));
//		shpeVO.setDiskFrom(true);
//		shpeVO.setEstimate(950);
//		dao.recycleAdd(shpeVO);

		// 查詢新品價格跟名稱
//		ShpeVO shpeVO5 = dao.selectItem("711719546672");
//		System.out.print(shpeVO5.getGameCode() + ",");
//		System.out.print(shpeVO5.getItemName() + ",");
//		System.out.print(shpeVO5.getItemPrice() + ",");

		// 查詢二手審核資訊
//		List<ShpeVO> list = dao.selectRecycleInf();
//		for (ShpeVO shpes : list) {
//		System.out.print(shpes.getApplicationNo()+",");
//		System.out.print(shpes.getGameCode()+",");
//		System.out.print(shpes.getItemName()+",");
//		System.out.print(shpes.getDiskBox()+",");
//		System.out.print(shpes.getDisk()+",");
//		System.out.print(shpes.getDiskFrom()+",");
//		System.out.print(shpes.getEstimate()+",");
//		System.out.print(shpes.getRecycleState()+",");			
//			
//		}

		// 二手審核狀態修改
//		ShpeVO shpeVO7 = new ShpeVO();
//
//		shpeVO7.setRecycleState(2);
//		shpeVO7.setApplicationNo(6);
//		
//		dao.updateState(shpeVO7);
		
		// 查詢單一會員二手回收申請全部
		List<ShpeVO> list = dao.selectRecycleMem(5);
		for (ShpeVO shpes : list) {		
			System.out.print(shpes.getApplicationNo()+",");	
			System.out.print(shpes.getItemName()+",");	
			System.out.print(shpes.getDiskBox()+",");
			System.out.print(shpes.getDisk()+",");
			System.out.print(shpes.getDiskFrom()+",");
			System.out.print(shpes.getEstimate()+",");
			System.out.print(shpes.getRecycleState());
			System.out.println();				
			
		}

	}

}
