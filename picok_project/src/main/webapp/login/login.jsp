<%@page import="vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html lang="en">
<head>

<title>Picok</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 외 아이콘 -->
<link rel="stylesheet"
	href="/picok_project/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/picok_project/assets/css/templatemo.css">
<link rel="stylesheet" href="/picok_project/assets/css/custom.css">
<link rel="stylesheet"
	href="/picok_project/assets/css/fontawesome.min.css">
<link rel="stylesheet" href="/picok_project/assets/css/liststyle.css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">

<!-- 파비콘 -->
<link rel="icon" href="assets/img/ms-icon-310x310.png"
	type="image/x-icon">
</head>


<body>

	<!-- 헤더 include -->
	<jsp:include page="../common/header.jsp"></jsp:include>


	<div class="container-fluid bg-light py-5">
		<div class="row py-3">
			<div class="col-md-6 m-auto text-center">
				<h1 class="h1">로그인</h1>
					<p>picok을 더 편리하게 이용하세요</p>
			</div>
		</div>
	</div>
<div class="col-lg-4 m-auto">
  <div class="form-group"><br/>
    <form action="../front" method="POST">
      <input class="form-control"  type="hidden" name="command" value="login" style="width: 80px;height:30px;"><br />
      <p>아이디&nbsp;&nbsp;&nbsp;&nbsp;<input class="form-control"  type="text" name="id" required="required"></p>
      <p>패스워드 <input class="form-control"  type="password" name="password" required="required"></p><br />
      <div style="display: flex; justify-content: flex-end; align-items: center; height: 50px;">
        <a href="../index.jsp" class="btn btn-outline-info">돌아가기</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" class="btn btn-info" value="로그인">
      </div><br/><br/>
    </form>
  </div>
</div>



	<!-- 푸터 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<!-- Script -->
	<script src="https://kit.fontawesome.com/a5f5e6fa14.js"
		crossorigin="anonymous"></script>
	<script src="/picok_project/assets/js/jquery-1.11.0.min.js"></script>
	<script src="/picok_project/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/picok_project/assets/js/bootstrap.bundle.min.js"></script>
	<script src="/picok_project/assets/js/templatemo.js"></script>
	<script src="/picok_project/assets/js/custom.js"></script>

</body>

</html>