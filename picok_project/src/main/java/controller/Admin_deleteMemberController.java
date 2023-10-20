package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.MemberDAO;

/**
 * Servlet implementation class Admin_deleteMemberController
 */
@WebServlet("/admin_member.do/delete")
public class Admin_deleteMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_deleteMemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("admin_member delete 하는 actiondo.");

		// 클라이언트로부터 전송된 postIds 파라미터 값을 읽습니다.
		String postIdsParam = request.getParameter("postIds");
		System.out.println(postIdsParam);

		String[] postIdsArray = postIdsParam.split(",");

		// BoardDAO 객체 생성
		MemberDAO memberdao = new MemberDAO();

		boolean isSuccess = true; // 삭제 성공 여부를 기본으로 세팅

		// 배열을 순회하며 글을 삭제
		for (String deleteid : postIdsArray) {
			System.out.println("deleteid: " + deleteid + "삭제하는중");

			boolean isDeleted = memberdao.deleteById(deleteid);

			if (isDeleted) {

				System.out.println("글 삭제 성공");
			} else {
				System.out.println("글 삭제 실패");
				isSuccess = false; // 하나라도 삭제 실패하면 isSuccess를 false로 변경

			}
		}

		
		response.sendRedirect(request.getContextPath() + "/admin/admin_member.jsp");

	}
}
