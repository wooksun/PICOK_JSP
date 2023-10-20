package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.MemberVO;



public class RegisterDAO {
   private Connection conn = null;
   private PreparedStatement pstmt = null;
   private ResultSet rs = null;
   private DataSource ds = null;

   public RegisterDAO() {
      try {
         Context initctx = new InitialContext();
         Context envctx = (Context)initctx.lookup("java:comp/env");
         this.ds = (DataSource)envctx.lookup("jdbc/pool");
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public int joinMember(MemberVO vo) {
      System.out.println("MemberDAO \ud074\ub798\uc2a4\uc758 regiater() \uba54\uc18c\ub4dc \uc2e4\ud589");

      try {
         this.conn = this.ds.getConnection();
         String sql = "insert into picok.member(mem_idx, id, password, name, nickname, email, addr, phone_num, joindate) values (picok.member_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setString(1, vo.getId());
         this.pstmt.setString(2, vo.getPassword());
         this.pstmt.setString(3, vo.getName());
         this.pstmt.setString(4, vo.getNickname());
         this.pstmt.setString(5, vo.getEmail());
         this.pstmt.setString(6, vo.getAddr());
         this.pstmt.setString(7, vo.getPhone_num());
         this.pstmt.setDate(8, new Date(vo.getJoindate().getTime()));
         this.pstmt.executeUpdate();
         this.conn.close();
         return 1;
      } catch (SQLException var3) {
         var3.printStackTrace();
         return 0;
      }
   }

   public int registerCheck(String id) {
      System.out.println("RegisterDAO \ud074\ub798\uc2a4\uc758 registerCheck() \uba54\uc18c\ub4dc \uc2e4\ud589");

      try {
         this.conn = this.ds.getConnection();
         String sql = "select * from member where trim(id) = ?";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setString(1, id);
         this.rs = this.pstmt.executeQuery();
         if (id.trim().equals("")) {
            return 1;
         } else {
            return this.rs.next() ? 2 : 3;
         }
      } catch (SQLException var3) {
         var3.printStackTrace();
         return 0;
      }
   }

   public int registerCheck2(String nickname) {
      System.out.println("RegisterDAO \ud074\ub798\uc2a4\uc758 registerCheck2() \uba54\uc18c\ub4dc \uc2e4\ud589");

      try {
         this.conn = this.ds.getConnection();
         String sql = "select * from member where trim(nickname) = ?";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setString(1, nickname);
         this.rs = this.pstmt.executeQuery();
         if (nickname.trim().equals("")) {
            return 1;
         } else {
            return this.rs.next() ? 2 : 3;
         }
      } catch (SQLException var3) {
         var3.printStackTrace();
         return 0;
      }
   }

   public int deleteMember(String id) {
      System.out.println("deleteMember \uba54\uc18c\ub4dc\uac00 \ud638\ucd9c\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \uc0ad\uc81c\ud560 ID: " + id);

      try {
         this.conn = this.ds.getConnection();
         String sql = "DELETE FROM picok.member WHERE id = ?";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setString(1, id);
         this.pstmt.executeUpdate();
         this.conn.close();
         return 1;
      } catch (SQLException var3) {
         var3.printStackTrace();
         return 0;
      }
   }
   

   public MemberVO updateMember(MemberVO vo) {
		MemberVO mvo = null;

	   System.out.println("RegisterDAO 클래스의 updateMember() 메소드 실행");
	    try {
	        conn = ds.getConnection();
	        String sql = "UPDATE picok.member \r\n"
	        		+ "SET password=?, nickname=?, email=?, addr=?, phone_num=? \r\n"
	        		+ "WHERE id=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, vo.getPassword());
	        pstmt.setString(2, vo.getNickname());
	        pstmt.setString(3, vo.getEmail());
	        pstmt.setString(4, vo.getAddr());
	        pstmt.setString(5, vo.getPhone_num());
	        pstmt.setString(6, vo.getId());
	        pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setPassword(rs.getString("password"));
				mvo.setNickname(rs.getString("nickname"));
				mvo.setEmail(rs.getString("email"));
				mvo.setAddr(rs.getString("addr"));
				mvo.setPhone_num(rs.getString("phone_num"));
			}
	        
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mvo;
	}
}

