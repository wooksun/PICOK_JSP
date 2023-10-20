package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import vo.MemberVO;

@WebServlet("/BoardUpdateController")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardUpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardUpdateController의 actionDo() 실행");

	    // 한글 깨짐 방지
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");

	    BoardDAO boardDao = BoardDAO.getInstance();

	    // 작성자인지 판별하는 기본값은 작성자임(true)을 기본값으로 함
	    boolean writeUpdate = true;

	    int board_idx = Integer.parseInt(request.getParameter("board_idx").trim());
	    try {
	        HttpSession session = request.getSession();
	        MemberVO mvo = (MemberVO) session.getAttribute("mvo");

	        // multi객체만들어서 파일 받아주기 
		    String realPath = getServletContext().getRealPath("./upload");
		    MultipartRequest multi = new MultipartRequest(
		        request,
		        realPath,
		        5 * 1024 * 1024 * 1024,
		        "UTF-8",
		        new DefaultFileRenamePolicy()
		    );
		    // 제목과 파일 첨부 확인
		    String file_name = multi.getFilesystemName("file_name");
		    if (file_name == null) {
		        // 파일이 첨부되지 않았을 경우, 기존의 파일 이름을 유지
		        file_name = request.getParameter("/picok_project/upload/${singlepage.getFile_name()}");
		    }
		    String board_title = multi.getParameter("board_title");
		    String category = multi.getParameter("category");
		    String board_content = multi.getParameter("board_content");
			System.out.println(board_content);
			System.out.println(board_title);
			System.out.println(file_name);
			System.out.println(category);
	        
	            writeUpdate = boardDao.writeUpdate(board_idx, mvo.getId(), category, board_title, board_content, file_name);
	            System.out.println("BoardUpdateController의 writeUpdate 값 : " + writeUpdate);


	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // 수정 결과를 "writeUpdate" 속성으로 설정하여 JSP에 전달
	    //request.setAttribute("writeUpdate", writeUpdate);

	    request.getRequestDispatcher("/BoardSingleController?idx=" + board_idx).forward(request, response);


	}

}
