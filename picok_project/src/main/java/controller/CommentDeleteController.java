package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardCommentDAO;

@WebServlet("/CommentDeleteController")
public class CommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommentDeleteController의 actionDo() 실행");
		//	한글 깨짐 방지
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8"); 
		
		BoardCommentDAO commentDao = BoardCommentDAO.getInstance();
		
		boolean commentDelete = true;
		
		int board_idx = Integer.parseInt(request.getParameter("board_idx").trim());
		try {
			int comment_idx = Integer.parseInt(request.getParameter("comment_idx").trim());
			System.out.println(board_idx);
			System.out.println(comment_idx);
			
			
			commentDelete = commentDao.commentDelete(board_idx, comment_idx);
			System.out.println(commentDelete);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//	경로 설정
		request.getRequestDispatcher("/BoardSingleController?idx=" + board_idx).forward(request, response);
		
	}

}
