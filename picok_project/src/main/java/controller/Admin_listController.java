package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardList;
import vo.BoardVO;

@WebServlet("/admin_board.do")
public class Admin_listController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_listController() {
        super();}

   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		actionDo(request, response);
   	}

   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		actionDo(request, response);
    	

    	}
    	
   	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    		System.out.println("admin_board컨트롤러 입니다. 리스트 전체를 가져올거에요.  ");
    		// BoardDAO의 인스턴스를 생성
    		BoardDAO boardDAO = new BoardDAO();

     		ArrayList<BoardVO> list = boardDAO.adminBoardlist();
   
    		// request 객체에 글 목록 저장
    		request.setAttribute("boardList", list);
    		
    		
    		String url = "/admin/admin_board.jsp";
    		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    		dispatcher.forward(request, response);
   	}


}

