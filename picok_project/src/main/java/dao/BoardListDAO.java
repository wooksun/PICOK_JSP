package dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import vo.BoardList;
import vo.BoardVO;

public class BoardListDAO {



	public static BoardListDAO instance = new BoardListDAO();
	public static BoardListDAO getInstance() {
		return instance;
	}

	public BoardList getboardList(int pageSize, int totalCount, int currentPage, int category) {
		return null;
	}

	public int selectCount(SqlSession mapper, int category) {
		System.out.println("BoardlistDAO 클래스의 selectCount() 메소드 실행");
		return (int) mapper.selectOne("selectCount", category);
	}

	public ArrayList<BoardVO> selectList(SqlSession mapper, HashMap<String, Integer> hmap) {
		System.out.println("BoardlistDAO 클래스의 selectList() 메소드 실행");
		return (ArrayList<BoardVO>) mapper.selectList("selectList", hmap);
	}

	public ArrayList<BoardVO> selectListbyPopular(SqlSession mapper, HashMap<String, Integer> hmap) {
		return (ArrayList<BoardVO>) mapper.selectList("selectListbyPopular", hmap);
	}

	
}
