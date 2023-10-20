package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegisterDAO;
import vo.MemberVO;


@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public UserRegister() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      actionDo(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      actionDo(request, response);
   }

   protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("UserRegister 서블릿의 actionDo() 메소드 실행");
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      
      String id = request.getParameter("id").trim();
      String password1 = request.getParameter("password1").trim();
      String password2 = request.getParameter("password2").trim();
      String name = request.getParameter("name").trim();
      String nickname = request.getParameter("nickname").trim();
      String email = request.getParameter("email").trim();
      String address = request.getParameter("address").trim();
      String detailAddress = request.getParameter("detailAddress").trim();
      // addr 변수를 address와 detailAddress의 결합으로 변경
      String addr = address + " " + detailAddress;
      String phone_num = request.getParameter("phone_num").trim();
//      
//      // 회원 아이디 중복 체크
//      int checkResult = new RegisterDAO().registerCheck(id);
//      if (checkResult != 3) {
//         // 이미 사용 중인 아이디인 경우
//         response.getWriter().write("o이미 사용중인 아이디");
//         return;
//      }
//
//      // 회원 닉네일 중복 체크
//      int checkResult2 = new RegisterDAO().registerCheck2(nickname);
//      if (checkResult2 != 3) {
//    	  // 이미 사용 중인 닉네임일 경우
//    	  response.getWriter().write("o이미 사용중인 닉네임");
//    	  return;
//      }
      
      // 중복 체크를 통과한 경우 회원 가입 처리를 수행합니다
      java.util.Date joinDate = new java.util.Date();
      MemberVO vo = new MemberVO(id, password1, name, nickname, email, addr, phone_num, joinDate);
      int result = new RegisterDAO().joinMember(vo);
      
      if (result == 1) {
         // 회원 가입 성공
         //response.getWriter().write("회원가입 성공");
         // response.sendRedirect("login.jsp"); // login.jsp로 이동
          response.setContentType("text/html; charset=UTF-8");
          
          PrintWriter out = response.getWriter();
           
          out.println("<script>alert('회원가입이 완료 되었습니다. 로그인 후 이용해주세요'); location.href='/picok_project/login/login.jsp';</script>");
           
          out.flush();

      } else {
         // 회원 가입 실패
         response.getWriter().write("회원 가입 실패");
      }
   }
}