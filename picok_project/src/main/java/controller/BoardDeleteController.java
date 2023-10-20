package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import service.BoardService;
import vo.BoardList;
import vo.MemberVO;

@WebServlet("/BoardDeleteController")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BoardDeleteController의 actionDo() 실행");
		//	한글 깨짐 방지
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8"); 
		
		BoardDAO boardDao = BoardDAO.getInstance();
		
		
		//	작성자인지 판별하는 기본값은 작성자임(true)을 기본값으로 함
		boolean writeDelete = true;
		
		String categoryParam = request.getParameter("category");
		String currentPageParam = request.getParameter("currentPage");
		int category = categoryParam != null ? Integer.parseInt(categoryParam) : 0;
		int currentPage = currentPageParam != null ? Integer.parseInt(currentPageParam) : 0;
		
		try {
			int board_idx = Integer.parseInt(request.getParameter("board_idx").trim());
			
			HttpSession session = request.getSession();
		    MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		    
			System.out.println("BoardDeleteController의 board_idx : " + board_idx);
			System.out.println("BoardDeleteController의 id : " + mvo.getId());
			writeDelete = boardDao.writeDelete(board_idx, mvo.getId());
			
			
			BoardService boardService = BoardService.getInstance();
			BoardList boardList = boardService.selectList(category, currentPage);
			
			// 게시글 목록을 다시 설정
		    request.setAttribute("boardList", boardList);
			
			System.out.println("BoardDeleteController의 writeDelete값 : " + writeDelete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//	해당 게시글의 작성자인지 판별함
		//request.setAttribute("writeDelete", writeDelete);
		
		
		request.getRequestDispatcher("/list.do?category=" + category + "&currentPage=1").forward(request, response);
//		String url = "/picok_project/list.do?category=" + category + "&currentPage=" + currentPage;
//		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//		dispatcher.forward(request, response);


	}

}
