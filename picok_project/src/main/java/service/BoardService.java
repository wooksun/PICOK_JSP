package service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import dao.BoardListDAO;
import mybatis.MySession;
import vo.BoardList;

public class BoardService {

	public static BoardService instance = new BoardService();

	private BoardService() {
	}

	public static BoardService getInstance() {
		return instance;
	}

	public BoardList selectList(int category, int currentPage) {
		System.out.println("BoardService클래스의 selectList 실행");
		SqlSession mapper = MySession.getSession();
		BoardListDAO dao = BoardListDAO.getInstance();
		int pageSize = 12;
		int totalCount = dao.selectCount(mapper, category);
		
		
		System.out.println(totalCount+"selectcount돌린 totalcount값");
		
		BoardList boardList = new BoardList(pageSize, totalCount, currentPage);
		// BoardList boardList = new BoardList(pageSize, totalCount, currentPage,
		// category); // dao에서 catrgory를 받아 boardList에 넘겨줌
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("startNo", boardList.getStartNo());
		hmap.put("endNo", boardList.getEndNo());
		hmap.put("category", category);
		boardList.setList(dao.selectList(mapper, hmap));

		mapper.close();
		return boardList;
	}

	public BoardList selectListbyPopular(int category, int currentPage) {
		System.out.println("BoardService클래스의 selectList 실행");
		SqlSession mapper = MySession.getSession();
		BoardListDAO dao = BoardListDAO.getInstance();
		int pageSize = 12;
		int totalCount = dao.selectCount(mapper, category);
		
		System.out.println(totalCount);
		BoardList boardList = new BoardList(pageSize, totalCount, currentPage);
		// BoardList boardList = new BoardList(pageSize, totalCount, currentPage,
		// category); // dao에서 catrgory를 받아 boardList에 넘겨줌
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("startNo", boardList.getStartNo());
		hmap.put("endNo", boardList.getEndNo());
		hmap.put("category", category);
		boardList.setList(dao.selectListbyPopular(mapper, hmap));
		
		mapper.close();
		return boardList;
	}

}
