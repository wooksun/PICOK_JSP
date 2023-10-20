package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/front")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//POST 방식 한글 처리
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	
	public void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("logincontroller로 왔음");
		
		try {
			//2. 클라이언트가 전송한 command를 return 받아 온다.
			String command = request.getParameter("command");
			
			//3. HanlerMapping에게 command를 전달하여 개발 컨드롤러 객체를 리턴 받는다.
			Controller controller = LoginMapping.getInstance().create(command);
			
			//4. 개별 Controller 객체를 실행시킨다.
			String url = controller.execute(request, response).trim();
			
			//5. 실행 후 return 값에 의해 forward와 redirect방식으로 각각 응답한다.
			if (url.startsWith("redirect:"))
				response.sendRedirect(url.substring(9));
			else
				request.getRequestDispatcher(url).forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}
}