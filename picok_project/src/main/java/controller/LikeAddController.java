package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LikeDAO;
import vo.MemberVO;

@WebServlet("/LikeAddController")
public class LikeAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikeAddController() {
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
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		Date date = new Date();
		
		System.out.println("addcontroller boardidx : " + board_idx);
		System.out.println("addcontroller id : " + mvo.getId());
		LikeDAO dao = LikeDAO.getInstance();
		
		if(dao.selectLike(board_idx, mvo.getId())) {
			// 좋아요한 사람일 때
			dao.cancelLike(board_idx, mvo.getId());
		} else {
			// 좋아요 안 한 사람일 때
			dao.addLike(board_idx, mvo.getId(), date);
		}
	}

}
