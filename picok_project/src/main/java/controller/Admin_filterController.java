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

@WebServlet("/admin_board.do/filter")
public class Admin_filterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_filterController() {
        super();
    }

   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		actionDo(request, response);
   	}

   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		actionDo(request, response);
    	

    	}
    	
   	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    		System.out.println("카테고리별로 가져올거임..");
    		
			String category = request.getParameter("category");
    	    // BoardDAO의 인스턴스를 생성
    	    BoardDAO boardDAO = new BoardDAO();

    	    ArrayList<BoardVO> list = boardDAO.adminFilterlist(category);

    	    // request 객체에 글 목록과 페이징 정보를 저장
    	    request.setAttribute("boardList", list);

    	    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin_board.jsp");
    	    dispatcher.forward(request, response);
    		}


}

