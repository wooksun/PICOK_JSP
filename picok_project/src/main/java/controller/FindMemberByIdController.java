package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

public class FindMemberByIdController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//반환할 url 주소
		String url = null;
		
		//View에서 입력값 받아오기
		String id = request.getParameter("id");
		
		//Model(MemberDAO)과 연동
		MemberVO vo = MemberDAO.getInstance().findMemberById(id);
		
		//연동 결과에 따라 View 화면 이동할 url 제공
		if (vo == null) { //아이디에 해당하는 값이 없으면
			url = "findmemberbyid-fail.jsp";
		} else { //아이디에 해당하는 값이 있으면
			url = "findmemberbyid-ok.jsp";
			//View에 연동 결과 공유
			request.setAttribute("vo", vo);
		}
		
		return url;
	}

}