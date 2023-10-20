package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardList;
import vo.BoardVO;

/**
 * Servlet implementation class MyphotoNextController
 */
@WebServlet("/myphotonext.do")
public class MyphotoNextController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyphotoNextController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ajax로 요청처리할거임..");
		
		// Ajax 요청 처리
		String id = request.getParameter("id");
		System.out.println(id);
		
		// currentPage받아오기
		String currentPageParam = request.getParameter("currentPage");
	    int currentPage = Integer.parseInt(currentPageParam);
	    System.out.println(currentPage+"현재페이지");
	    
	    
	    
		int pageSize = 8;
		BoardDAO boardDAO = new BoardDAO();
		int totalCount = boardDAO.getTotalCountById(id);
		BoardList page = new BoardList(pageSize, totalCount, currentPage);
		ArrayList<BoardVO> list = boardDAO.BoardlistById(id, page.getStartNo(), page.getEndNo());
		
		// JSON 배열 문자열 생성
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append("{");
		jsonBuffer.append("\"page\": {");
		jsonBuffer.append("\"currentPage\": ").append(currentPage).append(",");
		jsonBuffer.append("\"pageSize\": ").append(pageSize).append(",");
		jsonBuffer.append("\"totalCount\": ").append(totalCount);
		jsonBuffer.append("},");
		jsonBuffer.append("\"boardList\": [");
		for (int i = 0; i < list.size(); i++) {
		    BoardVO vo = list.get(i);
		    jsonBuffer.append("{");
		    jsonBuffer.append("\"board_idx\": ").append(vo.getBoard_idx()).append(",");
		    jsonBuffer.append("\"category\": ").append(vo.getCategory()).append(",");
		    jsonBuffer.append("\"board_title\": \"").append(escapeJsonString(vo.getBoard_title())).append("\",");
		    jsonBuffer.append("\"board_content\": \"").append(escapeJsonString(vo.getBoard_content())).append("\",");
		    jsonBuffer.append("\"file_name\": \"").append(escapeJsonString(vo.getFile_name())).append("\",");
		    jsonBuffer.append("\"board_reg_date\": \"").append(vo.getBoard_reg_date()).append("\"");
		    jsonBuffer.append("}");
		    if (i < list.size() - 1) {
		        jsonBuffer.append(",");
		    }
		}
		jsonBuffer.append("]");
		jsonBuffer.append("}");

		
		System.out.println(jsonBuffer+"제이슨");
		
		// 응답 설정
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonBuffer.toString());
	}
	
	// JSON 문자열 안에 포함될 수 있는 특수 문자를 이스케이프 처리
	private String escapeJsonString(String str) {
		if (str == null) {
			return null;
		}
		str = str.replace("\\", "\\\\");
		str = str.replace("\"", "\\\"");
		str = str.replace("/", "\\/");
		str = str.replace("\b", "\\b");
		str = str.replace("\f", "\\f");
		str = str.replace("\n", "\\n");
		str = str.replace("\r", "\\r");
		str = str.replace("\t", "\\t");
		return str;
	}
}
