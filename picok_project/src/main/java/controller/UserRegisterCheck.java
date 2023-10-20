package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegisterDAO;

/**
 * Servlet implementation class UserRegisterCheck
 */
@WebServlet("/UserRegisterCheck")
public class UserRegisterCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 public UserRegisterCheck() {
	        super();
	    }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      actionDo(request, response);
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      actionDo(request, response);
	   }

	   protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      System.out.println("UserRegisterCheck 서블릿의 actionDo() 메소드 실행");
	      request.setCharacterEncoding("UTF-8");
	      response.setContentType("text/html; charset=UTF-8");
	      
	      String id = request.getParameter("id").trim();
	      int checkResult = new RegisterDAO().registerCheck(id);
	      System.out.println(checkResult);
	      
	      if (id.equals("")) {
	         // 중복 검사할 아이디를 입력하지 않은 경우
	         response.getWriter().write("1");
	      } else if (checkResult == 2) {
	         // 사용 중인 아이디인 경우
	         response.getWriter().write("2");
	      } else if (checkResult == 3) {
	         // 사용 가능한 아이디인 경우
	         response.getWriter().write("3");
	      }
	   }  

	}