package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {
			
			HttpSession session = request.getSession(false);
			
			//로그인 정보가 있으면 세션정보 초기화
			if (session != null)
				session.invalidate();
			

	        // index.jsp로 리다이렉트
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('로그아웃되었습니다.'); location.href='/picok_project/index.jsp';</script>");
	        out.flush();

	        return null;
	    }
	}