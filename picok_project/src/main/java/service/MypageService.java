package service;

import org.apache.ibatis.session.SqlSession;

import dao.MemberDAO;
import dao.MypageDAO;
import mybatis.MySession;
import vo.MemberVO;

public class MypageService {
	
	private static MypageService instance = new MypageService();
public MypageService() {
	// TODO Auto-generated constructor stub
}	public static MypageService getInstace() {
		return instance;
	}
	
//	update.jsp에서 호출되는 테이블에 저장할 정보가 저장된 객체를 넘겨받고 mapper를 얻어온 후
//	updateDAO 클래스의 정보를 테이블에 저장하느 insert sql 명령을 실행하는 메소드를 
	public void update(MemberVO vo) {
		System.out.println("MypageService 클래스의 update() 메소드 실행");
		// mapper를 얻어온다.
		SqlSession mapper = MySession.getSession();
		System.out.println("연결성공: "+ mapper);
		
		//전처리
		
		MypageDAO.getInstace().update(mapper, vo);

			
		// 실행한 sql 명령이 테이블을 변경하는 insert, delete, update sql 명령일 경우 작업 결과를 테이블에
		// 반영시키기 위해서 작업이 완료되면 반드시 commit() 메소드를 실행해야 한다.
		// 테이블이 변경되지 않는 select sql 명령은 commit() 메소드를 실행하지 않아도 상관없다.
		mapper.commit(); //선택
		mapper.close(); // 필수사항
	}
	
//	delete.jsp에서 
	public void leave(String id) {
		System.out.println("MypageService 클래스의 leave() 메소드 실행");
		// mapper를 얻어온다.
		SqlSession mapper = MySession.getSession();
		MypageDAO.getInstace().leave(mapper, id);
		mapper.commit(); //선택
		mapper.close(); // 필수사항
		
	}
	
}
