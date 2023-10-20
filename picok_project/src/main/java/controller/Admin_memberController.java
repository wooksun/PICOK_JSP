package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberList;
import vo.MemberVO;

@WebServlet("/admin_member.do")
public class Admin_memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Admin_memberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	    // BoardDAO의 인스턴스를 생성
	    MemberDAO memberDAO = new MemberDAO();
	    ArrayList<MemberVO> list;

	    String query = request.getParameter("query");
	    if (query != null && !query.isEmpty()) {
	        // 검색어가 제공된 경우 검색 기능 수행
	        list = memberDAO.adminSearchlist(query);
	    } else {
	        // 검색어가 제공되지 않은 경우 전체 멤버 리스트를 가져옴
	        list = memberDAO.adminBoardlist();
	    }

	    request.setAttribute("memberList", list);
	    request.setAttribute("searchQuery", query); //새고 후에도 검색어가 남아있게 하려고 값을 같이 던져주는 것 

	    String url = "/admin/admin_member.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}
}
