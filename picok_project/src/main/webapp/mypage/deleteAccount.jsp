<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="dao.RegisterDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteAccount.jsp</title>
</head>
<body>

<%
  // 세션에서 사용자 ID를 가져옵니다.
  //String id = (String) session.getAttribute("mvo.id");

MemberVO mvo = (MemberVO)session.getAttribute("mvo");
String id = mvo.getId();

System.out.println("세션에서 가져온 ID: " + id);
  Connection conn = null;
  PreparedStatement pstmt = null;
  RegisterDAO dao = new RegisterDAO();

  try {
    // 사용자를 삭제합니다.
    int result = dao.deleteMember(id);

    // 성공적으로 사용자를 삭제했는지 확인합니다.
    if (result == 1) {
      // 세션을 종료하고 로그인 페이지로 이동합니다.
      out.println("<script>");
      out.println("alert('회원 탈퇴되었습니다. 안녕히가세요.....');");
      out.println("</script>");
      session.invalidate();
      response.sendRedirect("/picok_project/index.jsp");
    } else {
      // 사용자 삭제에 실패한 경우, 오류 메시지를 출력하고 이전 페이지로 돌아갑니다.
      out.println("<script>");
      out.println("alert('회원 탈퇴에 실패했습니다.');");
      out.println("history.go(-1);");
      out.println("</script>");
    }

  } catch (Exception e) {
    e.printStackTrace();
  } finally {
    if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) {} }
    if (conn != null) { try { conn.close(); } catch (SQLException e) {} }
  }
%>

</body>
</html>