package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//	처음 들어온 사용자면 조회수를 1 증가시키고, 이미 조회수를 증가시킨 사용자라면 조회수를 증가시키지 않는 클래스
public class BoardViewLog {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	public static BoardViewLog instance = new BoardViewLog();
	public static BoardViewLog getInstance() {
		return instance;
	}
	
	//connection pool 오라클과연결 
	public BoardViewLog() {
		
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("jdbc/pool");
			} catch ( Exception e) {
				e.printStackTrace();
			}
	}
	
	//파일을 업로드 할때마다 업로드하는 파일 이름과 실제로 저장되는 파일 이름을 테이블에 가져와 저장하는 메소드 insert 
	public void upload(int board_idx, String ip) {
		
		try {
			conn = ds.getConnection();
			String sql = "insert into picok.view_log(board_idx,ip) values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.setString(2, ip);
			pstmt.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int selectBoardView(int board_idx, String ip) {
		System.out.println("selectBoardView 시작했어요");
		int result = 0;
		
		try {
			System.out.println("selectBoardView DB 드갔어요");
			conn = ds.getConnection();
			String sql = "select count(*) cnt from picok.view_log where board_idx = ? and ip = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.setString(2, ip);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("rs : " + rs.getInt("cnt"));
				result = rs.getInt("cnt");
			}
			
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("result : " + result);
		
		System.out.println("selectBoardView 끝났어요");
		return result;
	}

}
    

