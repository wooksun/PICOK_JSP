package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.BoardVO;
import vo.MemberVO;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	// connection pool 오라클과연결
	public MemberDAO() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("jdbc/pool");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	// 관리자페이지_전체멤버를 가져올거임
	public ArrayList<MemberVO> adminBoardlist() {
		ArrayList<MemberVO> lists = new ArrayList<MemberVO>();

		try {
			conn = ds.getConnection();
			String sql = "SELECT mem_idx, id, password, name, nickname, email, addr, phone_num, joindate, mem_lv FROM picok.member ORDER BY mem_idx DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 데이터베이스에서 글 목록 가져와서 리스트에 추가
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setNickname(rs.getString("nickname"));
				vo.setEmail(rs.getString("email"));
				vo.setAddr(rs.getString("addr"));
				vo.setPhone_num(rs.getString("phone_num"));
				vo.setJoindate(rs.getDate("joindate"));
				vo.setMem_lv(rs.getInt("mem_lv"));

				lists.add(vo);
			}
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	// 페이징 하려고 total 멤버 수 가져옴
	public int getTotalCount() {
		int totalCount = 0;

		try {
			conn = ds.getConnection();
			String sql = "SELECT COUNT(*) AS total FROM picok.member";
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

	// id에 맞는 멤버 지우기
	public boolean deleteById(String deleteid) {

		try {
			conn = ds.getConnection();

			String query = "DELETE FROM picok.member WHERE id = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, deleteid);

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

	// 아이디 하나를 받아와서 맞는 회원정보를 담아 vo로 반환하는 메소드
	public MemberVO findMemberById(String id) throws SQLException {
		MemberVO vo = null;

		try {
			conn = ds.getConnection();

			String sql = "SELECT mem_idx, id, password, name, nickname, email, addr, phone_num, joindate, mem_lv FROM picok.member "
					+ "WHERE id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setNickname(rs.getString("nickname"));
				vo.setEmail(rs.getString("email"));
				vo.setAddr(rs.getString("addr"));
				vo.setPhone_num(rs.getString("phone_num"));
				vo.setJoindate(rs.getDate("joindate"));
				vo.setMem_lv(rs.getInt("mem_lv"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// 아이디랑 비밀번호가 똑같다면 mvo로 담아서 회원정보반환
	public MemberVO login(MemberVO vo) throws SQLException {
		MemberVO mvo = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT mem_idx, id, password, name, nickname, email, addr, phone_num, joindate, mem_lv FROM picok.member "
					+ "WHERE id = ? and password = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mvo = new MemberVO();
				mvo.setMem_idx(rs.getInt("mem_idx"));
				mvo.setId(rs.getString("id"));
				mvo.setPassword(rs.getString("password"));
				mvo.setName(rs.getString("name"));
				mvo.setNickname(rs.getString("nickname"));
				mvo.setEmail(rs.getString("email"));
				mvo.setAddr(rs.getString("addr"));
				mvo.setPhone_num(rs.getString("phone_num"));
				mvo.setJoindate(rs.getDate("joindate"));
				mvo.setMem_lv(rs.getInt("mem_lv"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mvo;

	}
	//검색어에 맞는 정보 반환할거임.. (이름, 닉네임, 이메일, 아이디)
	public ArrayList<MemberVO> adminSearchlist(String query) {
		ArrayList<MemberVO> lists = new ArrayList<MemberVO>();
		MemberVO vo = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT mem_idx, id, password, name, nickname, email, addr, phone_num, joindate, mem_lv FROM picok.member "
					+ "WHERE name LIKE ? OR nickname LIKE ? OR email LIKE ? OR id LIKE ?";
			pstmt = conn.prepareStatement(sql);
			String searchKeyword = "%" + query + "%";
			for (int i = 1; i <= 4; i++) {
				pstmt.setString(i, searchKeyword);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new MemberVO();
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setNickname(rs.getString("nickname"));
				vo.setEmail(rs.getString("email"));
				vo.setAddr(rs.getString("addr"));
				vo.setPhone_num(rs.getString("phone_num"));
				vo.setJoindate(rs.getDate("joindate"));
				vo.setMem_lv(rs.getInt("mem_lv"));

				lists.add(vo);
			}
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	//멤버 id를 받아서 해당 글을 지우는 메소드
		public boolean deleteById(int deleteid) {

			try {
				conn = ds.getConnection();

				String query = "DELETE FROM picok.member WHERE id = ?";
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

}
