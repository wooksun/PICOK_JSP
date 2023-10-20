package dao;

import org.apache.ibatis.session.SqlSession;

import vo.MemberVO;

public class MypageDAO {

	private static MypageDAO instance = new MypageDAO();
	private MypageDAO() {}
	public static MypageDAO getInstace() {
		return instance;
	}
	
//	mypageService 클래스에서 호출되는 mapper와 테이블에 저장할 메인 정보가 저장된 객체를 넘겨받고
//	테이블에 메인을 저장하는 
	public void update(SqlSession mapper, MemberVO vo) {
		mapper.update("update", vo);
	}
	
//	삭제할 id한건을 delete sql 명령을 실행하는 메소드
	public void leave(SqlSession mapper, String id) {
		mapper.delete("leave", id);
	}
	
}
