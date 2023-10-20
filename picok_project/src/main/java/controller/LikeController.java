package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardList;
import vo.BoardVO;

@WebServlet("/like.do")
public class LikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikeController() {
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
		
		
	    System.out.println("like 컨트롤러까지 옴 ");

	    String id = request.getParameter("id");
	    int currentPage = 1; // currentPage 기본값을 1로 설정
	    

	    // currentPage 파라미터가 전달되었다면 해당 값을 currentPage에 대입
	    String currentPageParam = request.getParameter("currentPage");
	    if (currentPageParam != null && !currentPageParam.isEmpty()) {
	        try {
	            currentPage = Integer.parseInt(currentPageParam);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
	    }

	    int pageSize = 8; // 한 페이지에 표시할 게시글 수

	    // BoardDAO의 인스턴스를 생성
	    BoardDAO boardDAO = new BoardDAO();

	    // 좋아요한 게시글 수 가져오기 (인스턴스 메서드로 호출)
	    int totalCount = boardDAO.getLikeCountById(id);

	    // BoardList 객체를 생성하여 페이징 처리를 수행
	    BoardList page = new BoardList(pageSize, totalCount, currentPage);
	    ArrayList<BoardVO> list = boardDAO.LikeById(id, page.getStartNo(), page.getEndNo());
	    
	    System.out.println(page);
	    System.out.println(list);

	    // request 객체에 글 목록과 페이징 정보를 저장
	    request.setAttribute("boardList", list);
	    request.setAttribute("boardPaging", page);

	    String url = "/mypage/like.jsp?currentPage="+currentPage;
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}
}
