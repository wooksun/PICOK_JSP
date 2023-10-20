package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.BoardCommentVO;
import vo.BoardVO;

public class BoardCommentDAO {


	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;
	
	public static BoardCommentDAO instance = new BoardCommentDAO();
	public static BoardCommentDAO getInstance() {
		return instance;
	}

	
	
	public BoardCommentDAO() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("jdbc/pool");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addComment(BoardCommentVO boardCommentVO) {    
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO picok.board_comment (board_idx, comment_idx, id, comment_content, comment_reg_date) "
					+ "VALUES (?, picok.board_comment_seq.nextval, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			System.out.println("boardCommentVO.getBoard_idx(): " + boardCommentVO.getBoard_idx());
			pstmt.setInt(1, boardCommentVO.getBoard_idx());
			pstmt.setString(2, boardCommentVO.getId());
			pstmt.setString(3, boardCommentVO.getComment_content());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("BoardCommentDAO의 addComment()끝");
		}
	}

	// listselect()
	public ArrayList<BoardCommentVO> viewComment(int board_idx) {
		ArrayList<BoardCommentVO> lists = new ArrayList<BoardCommentVO>();
		
		try {
			conn = ds.getConnection();
		    // 현재 페이지 목록 가져오기. (관리자 권한으로 전체) 
		    String sql = "SELECT bc.*, m.nickname FROM picok.board_comment bc, picok.member m where bc.id = m.id and bc.board_idx = ? order by bc.comment_reg_date";
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, board_idx);
		    rs = pstmt.executeQuery();
			
			// 데이터베이스에서 글 목록 가져와서 리스트에 추가
			while (rs.next()) {
				BoardCommentVO vo = new BoardCommentVO();
				vo.setBoard_idx(rs.getInt("board_idx"));
				vo.setComment_idx(rs.getInt("comment_idx"));
				vo.setId(rs.getString("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setComment_content(rs.getString("comment_content"));
				vo.setComment_ceg_Date(rs.getDate("comment_reg_Date"));
				
				lists.add(vo);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public boolean commentDelete(int board_idx, int comment_idx) {
			System.out.println("BoardCommentDAO로 인수보냈고 commentDelete() 메소드 실행할거임");

			try {
				conn = ds.getConnection();

				// 게시글에 연결된 댓글 삭제
				String deleteCommentQuery = "DELETE FROM picok.board_comment WHERE comment_idx = ?";
				pstmt = conn.prepareStatement(deleteCommentQuery);
				pstmt.setInt(1, comment_idx);
				pstmt.executeUpdate();

				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}

	
}
