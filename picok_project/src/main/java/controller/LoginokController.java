package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import vo.MemberVO;

public class LoginokController implements Controller {
	

		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			System.out.println("loginokcontroller로 왔음");
			
			//반환할 url 주소
			String url = null;
			
			//JSP에서 입력값 받아오기
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			//Model과 연동
			//vo : 입력받은 값으로 만든 객체
			MemberVO vo = new MemberVO(id,  password);
			//mvo : 입력받은 값을 비교해서 결과값으로 받은 객체 (전체정보)
			MemberVO mvo = MemberDAO.getInstance().login(vo);
					
			//View로 이동 : redirect방식으로 
			if (mvo == null) { //로그인 실패
				url = "redirect:login/login_fail.jsp";
			} else { //로그인 성공
				url = "redirect:login/login_ok.jsp";
				//세션 생성
				HttpSession session = request.getSession();
				//세션에 정보(mvo) 할당 - View에 정보 공유
				session.setAttribute("mvo", mvo);
			}
			
			return url;	
		}
		}
