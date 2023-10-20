package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikeDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;
	
	public static LikeDAO instance = new LikeDAO();
	public static LikeDAO getInstance() {
		return instance;
	}
	
	//connection pool 오라클과연결 
	public LikeDAO() {
		
	try {
		Context initctx = new InitialContext();
		Context envctx = (Context) initctx.lookup("java:comp/env");
		ds = (DataSource) envctx.lookup("jdbc/pool");
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
	//	좋아요를 했는지 판별하는 메소드
	public boolean selectLike(int board_idx, String id) {
		System.out.println("LikeDAO로 인수보냈고 addLike() 메소드 실행할거임");
		
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from picok.likes where board_idx = ? and id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("cnt") > 0) {
					result = true;
				}
			}
			
			conn.close();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//	좋아요 추가 메소드
	public void addLike(int board_idx, String id, Date date) {
		System.out.println("LikeDAO로 인수보냈고 addLike() 메소드 실행할거임");
		try {
			conn = ds.getConnection();
			String sql = "insert into picok.likes(board_idx, id, like_reg_date) values (?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//	좋아요 취소 메소드
	public void cancelLike(int board_idx, String id) {
		System.out.println("LikeDAO로 인수보냈고 cancelLike() 메소드 실행할거임");
		try {
			conn = ds.getConnection();
			
			String query = "delete from picok.likes where board_idx = ? and id = ?";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setInt(1, board_idx);
			 pstmt.setString(2, id);
			 pstmt.executeUpdate();
			 
			 conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//	좋아요 개수를 얻어오는 메소드
	public int countLikes(int board_idx) {
		System.out.println("LikeDAO로 인수보냈고 countLikes() 메소드 실행할거임");
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from picok.likes where board_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("cnt");
			}
			
			conn.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
