package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import vo.BoardVO;
import vo.MemberVO;

@WebServlet("/upload.do")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("actiondo 하는 중 ");
	    request.setCharacterEncoding("UTF-8");

	    // multi객체만들어서 파일 받아주기 
	    String realPath = getServletContext().getRealPath("/upload");
//	    String realPath = "/Users/jineunyoung/Downloads";
	    MultipartRequest multi = new MultipartRequest(
	        request,
	        realPath,
	        5 * 1024 * 1024 * 1024,
	        "UTF-8",
	        new DefaultFileRenamePolicy()
	    );
	    System.out.println(realPath);
	    // 제목과 파일 첨부 확인
	    String file_name = multi.getFilesystemName("filename");
	    String board_title = multi.getParameter("subject");
	    
	    if (board_title == null || board_title.trim().isEmpty()) {
	        // 제목이 입력되지 않았을 때 알림창 띄우기
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('제목을 입력해주세요.'); history.back();</script>");
	        out.close();
	        return;
	    }
	    
	    if (file_name == null || file_name.trim().isEmpty()) {
	        // 파일 첨부가 되지 않았을 때 알림창 띄우기
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('파일을 첨부해주세요.'); history.back();</script>");
	        out.close();
	        return;
	    }

	    // 나머지 값들 받아주기 
	    String id = multi.getParameter("writer");
	    String category = multi.getParameter("category");
	    String board_content = multi.getParameter("content");
	    char secretOK = 'n';
	    if (multi.getParameter("secretOK") != null) {
	        secretOK = multi.getParameter("secretOK").charAt(0);
	    }
	    new BoardDAO().upload(file_name, id, category, board_title, board_content, secretOK);
	    
	    request.getRequestDispatcher("/myphoto.do?id="+id).forward(request, response);
	}
}
