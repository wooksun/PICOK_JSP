package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LikeDAO;

@WebServlet("/LikeCancelController")
public class LikeCancelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikeCancelController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	한글 깨짐 방지
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8"); 
		
		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		String id = request.getParameter("id");
		
		LikeDAO dao = LikeDAO.getInstance();
		dao.cancelLike(board_idx, id);
	}

}
