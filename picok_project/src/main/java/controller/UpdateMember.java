package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RegisterDAO;
import vo.MemberVO;

@WebServlet("/UpdateMember")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("updatemember 서블릿의 actionDo() 메소드 실행");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id").trim();
		String password1 = request.getParameter("password1").trim();
		String nickname = request.getParameter("nickname").trim();
		String email = request.getParameter("email").trim();
		String address = request.getParameter("address").trim();
		String detailAddress = request.getParameter("detailAddress").trim();
		// addr 변수를 address와 detailAddress의 결합으로 변경
		String addr = address + " " + detailAddress;
		String phone_num = request.getParameter("phone_num").trim();

		MemberVO vo = new MemberVO(id, password1, nickname, email, addr, phone_num);

		new RegisterDAO().updateMember(vo);
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");

		// 객체 업데이트
		mvo.setId(id); // 새로운 아이디로 업데이트
		mvo.setPassword(password1); // 새로운 이름으로 업데이트
		mvo.setNickname(nickname); // 새로운 이름으로 업데이트
		mvo.setEmail(email); // 새로운 이름으로 업데이트
		mvo.setAddr(addr); // 새로운 이름으로 업데이트
		mvo.setPhone_num(phone_num); // 새로운 이름으로 업데이트
		// 필요한 다른 속성들도 업데이트

		// 세션 업데이트
		session.setAttribute("mvo", mvo);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/mypage/mypage.jsp");
			dispatcher.forward(request, response);

	}
}