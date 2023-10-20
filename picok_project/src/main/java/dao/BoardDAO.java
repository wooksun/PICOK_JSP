package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;

import vo.BoardList;
import vo.BoardVO;

public class BoardDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	public static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	// connection pool 오라클과연결
	public BoardDAO() {

		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("jdbc/pool");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 파일을 업로드 할때마다 업로드하는 파일 이름과 실제로 저장되는 파일 이름을 테이블에 가져와 저장하는 메소드 insert
	public void upload(String file_name, String id, String category, String board_title, String board_content,
			char secretOK) {
		System.out.println("boarddao로 인수보냈고 upload() 메소드 실행할거임");
		try {
			conn = ds.getConnection();
			String sql = "insert into picok.board(board_idx,id,category,board_title,board_content,file_name,secretOK) values (picok.board_seq.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, category);
			pstmt.setString(3, board_title);
			pstmt.setString(4, board_content);
			pstmt.setString(5, file_name);
			pstmt.setString(6, String.valueOf(secretOK));
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			;
		}
	}

	public ArrayList<BoardVO> adminBoardlist() {
		ArrayList<BoardVO> lists = new ArrayList<BoardVO>();

		try {
			conn = ds.getConnection();
			// 현재 페이지 목록 가져오기. (관리자 권한으로 전체)
			String sql = "SELECT board_idx, id, category, board_title, board_content, file_name, "
			        + "       TO_CHAR(board_reg_date, 'YYYY-MM-DD') AS board_reg_date, view_num, likes_num, report_num, secretOK "
			        + "FROM picok.board "
			        + "ORDER BY board_idx DESC";
	        pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			// 데이터베이스에서 글 목록 가져와서 리스트에 추가
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoard_idx(rs.getInt("board_idx"));
				vo.setId(rs.getString("id"));
				vo.setCategory(rs.getString("category"));
				vo.setBoard_title(rs.getString("board_title"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setFile_name(rs.getString("file_name"));
				vo.setBoard_reg_date(rs.getDate("board_reg_date"));
				vo.setView_num(rs.getInt("view_num"));
				vo.setLikes_num(rs.getInt("likes_num"));
				vo.setReport_num(rs.getInt("report_num"));
				vo.setSecretOK(rs.getString("secretOK").charAt(0));

				lists.add(vo);
			}
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	// 총 게시글 갯수 가져오기
	public int getTotalCount() {
		int totalCount = 0;

		try {
			conn = ds.getConnection();
			String sql = "SELECT COUNT(*) AS total FROM picok.board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("total");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalCount;
	}

	// 카테고리에 맞는 글 전부를 가져옴
	public ArrayList<BoardVO> adminFilterlist(String category) {
		ArrayList<BoardVO> lists = new ArrayList<BoardVO>();
		System.out.println(category + "다오로 넘어온 카테고리값..");
		try {
			conn = ds.getConnection();
			String sql = "SELECT board_idx, id, category, board_title, board_content, file_name, "
					+ "       TO_CHAR(board_reg_date, 'YYYY-MM-DD') AS board_reg_date, view_num, likes_num, report_num, secretOK "
					+ "FROM picok.board " + "WHERE category = ? " + "ORDER BY board_idx DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();

			// 데이터베이스에서 글 목록 가져와서 리스트에 추가
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoard_idx(rs.getInt("board_idx"));
				vo.setId(rs.getString("id"));
				vo.setCategory(rs.getString("category"));
				vo.setBoard_title(rs.getString("board_title"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setFile_name(rs.getString("file_name"));
				vo.setBoard_reg_date(rs.getDate("board_reg_date"));
				vo.setView_num(rs.getInt("view_num"));
				vo.setLikes_num(rs.getInt("likes_num"));
				vo.setReport_num(rs.getInt("report_num"));
				vo.setSecretOK(rs.getString("secretOK").charAt(0));

				lists.add(vo);
			}
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

//게시글 idx를 받아서 해당 글을 지우는 메소드
	public boolean deleteByIdx(int deleteid) {

		try {
			conn = ds.getConnection();

			String query = "DELETE FROM picok.board WHERE board_idx = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, deleteid);

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// id를 검색해서 해당 계정의 총 게시글 갯수를 얻어오는 메소드
	public int getTotalCountById(String id) {
		int totalCount = 0;

		try {
			conn = ds.getConnection();
			String sql = "SELECT COUNT(*) AS total FROM picok.board where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 맞는 id가 있었다면 totalcount에 게시글 수를 넣어라
			if (rs.next()) {
				totalCount = rs.getInt("total");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalCount;
	}

	// id를 검색해서 본인이 쓴 글만 (한페이지!) 얻어오는 메소드
	public ArrayList<BoardVO> BoardlistById(String id, int startNo, int endNo) {
		ArrayList<BoardVO> lists = new ArrayList<BoardVO>();
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM ("
				    + "    SELECT board_idx, category, board_title, board_content, file_name, "
				    + "           TO_CHAR(board_reg_date, 'YYYY-MM-DD') AS board_reg_date, "
				    + "           ROW_NUMBER() OVER (ORDER BY board_idx DESC) AS row_num FROM picok.board "
				    + "    WHERE id = ?" + ") WHERE row_num BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			rs = pstmt.executeQuery();

			// 데이터베이스에서 글 목록 가져와서 리스트에 추가
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoard_idx(rs.getInt("board_idx"));
				vo.setCategory(rs.getString("category"));
				vo.setBoard_title(rs.getString("board_title"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setFile_name(rs.getString("file_name"));
				vo.setBoard_reg_date(rs.getDate("board_reg_date"));


				lists.add(vo);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	// 아이디별로 like한 글만 가져오는 메소드
	public ArrayList<BoardVO> LikeById(String id, int startNo, int endNo) {
		ArrayList<BoardVO> lists = new ArrayList<BoardVO>();
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM (" + "    SELECT b.board_idx, b.category, b.file_name "
					+ "    FROM picok.likes l " + "    JOIN picok.board b ON l.board_idx = b.board_idx "
					+ "    WHERE l.id = ? AND ROWNUM BETWEEN ? AND ?" + ")";

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, id);
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo - startNo + 1);
				rs = pstmt.executeQuery();

				// 데이터베이스에서 글 목록 가져와서 리스트에 추가
				while (rs.next()) {
					BoardVO vo = new BoardVO();
					vo.setBoard_idx(rs.getInt("board_idx"));
					vo.setCategory(rs.getString("category"));
					vo.setFile_name(rs.getString("file_name"));

					lists.add(vo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	// id 로 검색해서 좋아요 한 게시글 갯수만 얻어오기
	public int getLikeCountById(String id) {
		int totalCount = 0;

		try {
			conn = ds.getConnection();
			String sql = "SELECT COUNT(*) AS likeCount FROM picok.likes WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 맞는 id가 있었다면 totalcount에 게시글 수를 넣어라
			if (rs.next()) {
				totalCount = rs.getInt("likeCount");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalCount;
	}

	//	게시글 모든 정보 가져오기
	public BoardVO boardSingleList(int board_idx) {
		BoardVO vo = new BoardVO();
		
		try {
			conn = ds.getConnection();
			String query = "select * FROM picok.board WHERE board_idx = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_idx);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setBoard_idx(rs.getInt("board_idx"));
				vo.setId(rs.getString("id"));
				vo.setCategory(rs.getString("category"));
				vo.setBoard_title(rs.getString("board_title"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setFile_name(rs.getString("file_name"));
				vo.setBoard_reg_date(rs.getDate("board_reg_date"));
				vo.setView_num(rs.getInt("view_num"));
				vo.setLikes_num(rs.getInt("likes_num"));
				vo.setReport_num(rs.getInt("report_num"));
				vo.setSecretOK(rs.getString("secretOK").charAt(0));

				// System.out.println(vo + "sdffsd");
			}
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return vo;

	}

	//	조회수
	public void addViewNum(int board_idx) {
		try {
			conn = ds.getConnection();
			String query = "update board set view_num = view_num + 1 where board_idx = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_idx);
			rs = pstmt.executeQuery();
			
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//	작성자 게시글 삭제하는 메소드
	public boolean writeDelete(int board_idx, String id) {
		 System.out.println("BoardDAO로 인수보냈고 writeDelete() 메소드 실행할거임");

	    try {
	        conn = ds.getConnection();
	        
	        // 게시글에 연결된 댓글 삭제
	        String deleteCommentQuery = "DELETE FROM picok.board_comment WHERE board_idx = ?";
	        pstmt = conn.prepareStatement(deleteCommentQuery);
	        pstmt.setInt(1, board_idx);
	        pstmt.executeUpdate();
	        
	        // 게시글에 연결된 댓글 삭제
	        String deleteViewLogQuery = "DELETE FROM picok.view_log WHERE board_idx = ?";
	        pstmt = conn.prepareStatement(deleteViewLogQuery);
	        pstmt.setInt(1, board_idx);
	        pstmt.executeUpdate();
	        
	        // 게시글에 연결된 댓글 삭제
	        String deleteLikeQuery = "DELETE FROM picok.likes WHERE board_idx = ?";
	        pstmt = conn.prepareStatement(deleteLikeQuery);
	        pstmt.setInt(1, board_idx);
	        pstmt.executeUpdate();
	        
	        // 게시글에 연결된 댓글 삭제
	        String deleteReportQuery = "DELETE FROM picok.report WHERE board_idx = ?";
	        pstmt = conn.prepareStatement(deleteReportQuery);
	        pstmt.setInt(1, board_idx);
	        pstmt.executeUpdate();

	        // 게시글 삭제
	        String deleteBoardQuery = "DELETE FROM picok.board WHERE board_idx = ? and id = ?";
	        pstmt = conn.prepareStatement(deleteBoardQuery);
	        pstmt.setInt(1, board_idx);
	        pstmt.setString(2, id);
	        pstmt.executeUpdate();
	        
	        conn.close();
	        //pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return true;
	}
	
	//	작성자 게시글 수정하는 메소드
	public boolean writeUpdate(int board_idx, String id, String category, String board_title, String board_content, String file_name) {
		System.out.println("BoardDAO로 인수보냈고 writeUpdate() 메소드 실행할거임");

		try {
	        conn = ds.getConnection();

	        // 게시글 업데이트
	        String updateBoardQuery = "UPDATE picok.board SET category = ?, board_title = ?, board_content = ?, file_name = ? WHERE board_idx = ? AND id = ?";
	        pstmt = conn.prepareStatement(updateBoardQuery);
	        pstmt.setString(1, category);
	        pstmt.setString(2, board_title);
	        pstmt.setString(3, board_content);
	        pstmt.setString(4, file_name);
	        pstmt.setInt(5, board_idx);
	        pstmt.setString(6, id); // mvo관련하여 받아와야 할 것 같음
	        //pstmt.setString(6, mvo.getId());

	        pstmt.executeUpdate();

	        conn.close();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
}
    

