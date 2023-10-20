<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	HttpSession loginsession = request.getSession(false);

	//로그인 여부 확인 방법

	//1단계. 세션이 존재하는지
	//session이 null이 아니면(session이 있으면)
	if (loginsession != null) {
		//session에서 mvo값 가져오기
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");

		// 2단계. 로그인 시 할당한 attribute가 존재하는지
		//mvo도 null이 아니면
		if (mvo != null) {
	        if (mvo != null) {
	            response.sendRedirect("/picok_project/index.jsp");
	            return; // 리다이렉트 후 스크립트 종료
	        }
	    }

	    // 로그인 실패 시 login.jsp로 리다이렉트
	    response.sendRedirect("/picok_project/login/login.jsp");
	%>

	<script>
		// login.jsp로 리다이렉트 된 후, alert 창 띄우기
		alert("로그인에 실패하였습니다. 다시 로그인해주세요.");
	</script>
	<%
	}
	%>
</body>
</html>