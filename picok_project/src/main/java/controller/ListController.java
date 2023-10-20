package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.BoardService;
import vo.BoardList;

@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	

	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("list가져오는 컨트롤러 에서 actiondo 하는 중 ");
		//	여기까지 console에 찍힘 
		
		System.out.println(request.getParameter("category")+"category");
		System.out.println(request.getParameter("currentPage")+"currentpage");
		BoardService boardService = BoardService.getInstance();
		  int category = Integer.parseInt(request.getParameter("category"));
		  int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		  
		  BoardList boardList = boardService.selectList(category, currentPage);
		  
		  System.out.println(boardList);
		  //	board_idx 44번부터 81번까지 가져옴
		  
		  request.setAttribute("boardList", boardList);
			   
		// JSP 파일로 포워딩
		// /a.jsp 로 들어온 요청을 /a.jsp 내에서 RequestDispatcher를 사용하여 b.jsp로 요청을 보낼 수 있습니다. 
		//또는 a.jsp에서 b.jsp로 처리를 요청하고 b.jsp에서 처리한 결과 내용을 a.jsp의 결과에 포함시킬 수 있습니다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/list.jsp");
		dispatcher.forward(request, response);
		
		
		}


}
