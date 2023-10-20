package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardCommentDAO;
import vo.BoardCommentVO;
import vo.MemberVO;

@WebServlet("/BoardCommentController")
public class BoardCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BoardCommentController의 actionDo() 메소드 실행");
		
		//	한글 깨짐 방지
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8"); 
		
		// 댓글 작성 폼에서 전달된 데이터 가져오기
		String board_idx = request.getParameter("board_idx");
	    HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("mvo");
	    String comment_content = request.getParameter("comment_content");
	    
	    System.out.println("board_idx : " + board_idx);
	    System.out.println("id : " + mvo.getId());
	    System.out.println("comment_content : " + comment_content);
	    
	    // null 체크 후에 Integer.parseInt() 호출
	    int boardIdx = (board_idx != null && !board_idx.isEmpty()) ? Integer.parseInt(board_idx) : 0;
	    
	    // 댓글 객체 생성
	    BoardCommentVO boardCommentVO = new BoardCommentVO();
	    boardCommentVO.setBoard_idx(boardIdx);
	    boardCommentVO.setId(mvo.getId());
	    boardCommentVO.setComment_content(comment_content);
	    
	    // BoardCommentDAO를 사용하여 댓글 저장
	    BoardCommentDAO boardCommentDAO = new BoardCommentDAO();
        boardCommentDAO.addComment(boardCommentVO);
        
	    // 댓글 작성 후, 원래의 페이지로 리다이렉트
	    response.sendRedirect("BoardSingleController?idx=" + boardIdx);
	}
}

