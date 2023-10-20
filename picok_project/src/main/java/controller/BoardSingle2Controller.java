package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardCommentDAO;
import dao.BoardDAO;
import dao.BoardViewLog;
import dao.LikeDAO;
import dao.ReportDAO;
import vo.BoardCommentVO;
import vo.BoardVO;
import vo.MemberVO;

@WebServlet("/BoardSingle2Controller")
public class BoardSingle2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardSingle2Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("BoardSingle2Controller의 actionDo 실행");
		
		System.out.println("IPADDR : " + getRemoteAddr(request));
		
		//	한글 깨짐 방지
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8"); 
		
		//	제목, 닉네임, 내용, 사진을 불러올 코딩 생성
		BoardVO result = null;
		ArrayList<BoardCommentVO> listResult = null;
		//	좋아요 기본 상태값 false로 선언
		boolean likes = false;
		int countLikes = 0;
		boolean report = false;
		
		try {
			//	insert.jsp 에서 입력한 데이터가 ajax를 통해서 넘어오는 데이터를 받는다.
			int board_idx = Integer.parseInt(request.getParameter("board_idx").trim());
			System.out.println(board_idx+"SMS");
			BoardDAO boardDao = BoardDAO.getInstance();
			BoardViewLog viewLogDao = BoardViewLog.getInstance();
			LikeDAO likeDao = LikeDAO.getInstance();
			ReportDAO reportDao = ReportDAO.getInstance();
			
			// 조회 검증(이미 조회를 한 사람인지 판별)
			 if (viewLogDao.selectBoardView(board_idx, getRemoteAddr(request)) == 0) { // 조회수 1 증가 
				 boardDao.addViewNum(board_idx);
				 viewLogDao.upload(board_idx, getRemoteAddr(request));
			 }

			// BoardCommentDAO 를 활용해서 댓글리스트를 가져와야함.
			BoardCommentDAO dao =  new BoardCommentDAO();
			
			// 좋아요 한 유저인지 판별
			HttpSession session = request.getSession();
		    MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		    likes = likeDao.selectLike(board_idx, mvo.getId());
		    
		    //	좋아요 개수를 얻어옴
		    countLikes = likeDao.countLikes(board_idx);
		    
		    //	신고
		    report = reportDao.ReportCheck(board_idx, getRemoteAddr(request));
		    
			//	넘겨받은 데이터를 테이블에 저장하는 메소드를 실행한다. 
			 result = new BoardDAO().boardSingleList(board_idx);
			 //	작성한 댓글을 출력할 데이터를 listResult에 담는다.
			 listResult = dao.viewComment(board_idx);
			 
			 
			 System.out.println("@@@@@@@@@@@@@@@@@@@리스트 사이즈"+listResult.size());
			 
			} catch (Exception e) {
		}
		System.out.println(result+"dddd");
		
		//	boardSingleList를 보내줌
		request.setAttribute("singlepage", result);
		//	댓글정보를 담아 보내줌
		request.setAttribute("listResult", listResult);
		//	좋아요 상태 보내줌
		request.setAttribute("likes", likes);
		//	좋아요 개수 보내줌
		request.setAttribute("countLikes", countLikes);
		//	신고 
		request.setAttribute("report", report);
		
		String url = "/board/update.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	//	조회수 중복증가 방지를 위해 사용자 ip 주소를 가져오는 메소드
	protected String getRemoteAddr(HttpServletRequest request){
	    return request.getRemoteAddr();
	}
}

