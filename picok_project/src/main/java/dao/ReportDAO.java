package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//	신고를 실행하는 DAO
public class ReportDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;
	
	public static ReportDAO instance = new ReportDAO();
	public static ReportDAO getInstance() {
		return instance;
	}
	
	//connection pool 오라클과연결 
	public ReportDAO() {
		
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("jdbc/pool");
			} catch ( Exception e) {
				e.printStackTrace();
			}
	}
	//	신고 판별
	public boolean ReportCheck(int board_idx, String ip) {
		System.out.println("ReportDAO로 인수보냈고 ReportCheck() 메소드 실행할거임");
		
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from picok.report where board_idx = ? and ip = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.setString(2, ip);
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
	
	public void Report(int board_idx, String ip) {
		System.out.println("ReportDAO로 인수보냈고 Report() 메소드 실행할거임");
		
		try {
			conn = ds.getConnection();
			String sql = "insert into picok.report(board_idx,ip) values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.setString(2, ip);
		
			pstmt.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
