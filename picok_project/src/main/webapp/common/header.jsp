<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Picok</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
<!-- 파비콘 -->
<link rel="icon" href="assets/img/ms-icon-310x310.png"
	type="image/x-icon">
</head>
<body>

	<!-- 헤더 -->
	<nav class="navbar navbar-expand-lg navbar-light shadow">
		<div
			class="container d-flex justify-content-between align-items-center">
			<a class="navbar-brand text-info logo h1 align-self-center"
				href="/picok_project/index.jsp"> Picok </a>

			<button class="navbar-toggler border-0" type="button"
				data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div
				class="align-self-center collapse navbar-collapse flex-fill d-lg-flex justify-content-lg-between"
				id="templatemo_main_nav">
				<div class="flex-fill">
					<ul class="nav navbar-nav d-flex align-items-center justify-content-between mx-lg-auto">
						<li class="nav-item"><a class="nav-link"
							href="/picok_project/list.do?category=1&currentPage=1">Nature</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/picok_project/list.do?category=2&currentPage=1">City</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/picok_project/list.do?category=3&currentPage=1">Daily</a></li>
						<li class="nav-item dropdown">
							<a class="nav-icon position-relative text-decoration-none dropdown-toggle"
							href="#" id="navbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Picok</a>
							<div class="dropdown-menu" aria-labelledby="picokDropdown">
								<a class="dropdown-item" href="/picok_project/menu/about.jsp">About</a>
								<a class="dropdown-item" href="/picok_project/menu/contact.jsp">Contact</a>
								<a class="dropdown-item" href="/picok_project/menu/qna.jsp">QnA</a>
							</div></li>
				</div>

				<!-- 로그인시 닉네임 출력 -->
				<c:set var="mvo" value="${sessionScope.mvo}" />
				<%-- mvo가 null이 아니면 로그인이 성공한 상태이므로 사용자 이름을 표시한다 --%>
				<c:if test="${not empty mvo}">
					<span style="font-size: 15px;">${mvo.nickname}님&nbsp;&nbsp;
					</span>
				</c:if>

				<div class="navbar align-self-center d-flex">
					<div
						class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="write">
							<div class="input-group-text">
								<i class="fa fa-fw fa-pencil-square-o" aria-hidden="true"></i>
							</div>
						</div>
					</div>
					<!-- 1.로그인안됐다 ->login.jsp 로, 2. 로그인됐다 -> upload.jsp로. -->
					<c:choose>
<c:when test="${empty mvo}">
  <a class="nav-icon position-relative text-decoration-none" href="#" onclick="showAlert()">
    <i class="fa fa-fw fa-pencil-square-o text-dark mr-3"></i>
  </a>
</c:when>
						<c:otherwise>
							<a class="nav-icon position-relative text-decoration-none"
								href="/picok_project/board/upload.jsp"> <i
								class="fa fa-fw fa-pencil-square-o text-dark mr-3"></i>
							</a>
						</c:otherwise>
					</c:choose>

					<div class="dropdown">
						<a
							class="nav-icon position-relative text-decoration-none dropdown-toggle"
							href="#" id="navbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i
							class="fa fa-fw fa-user text-dark mr-3"></i>
						</a>
						<div class="dropdown-menu">
							<c:if test="${empty mvo}">
								<a class="dropdown-item" href="/picok_project/login/login.jsp">Login</a>
								<a class="dropdown-item" href="/picok_project/login/join.jsp">Join</a>
							</c:if>
							<c:if test="${not empty mvo}">
								<c:choose>
									<c:when test="${mvo.mem_lv == 1}">
										<!-- Admin menu items -->
										<a class="dropdown-item"
											href="/picok_project/admin/adminpage.jsp">Admin</a>
										<!-- Add more admin menu items here -->
									</c:when>
									<c:otherwise>
										<!-- Regular menu items -->
										<a class="dropdown-item"
											href="/picok_project/mypage/mypage.jsp">MyPage</a>
										<a class="dropdown-item"
											href="/picok_project/myphoto.do?id=${mvo.id}">MyPhoto</a>
										<a class="dropdown-item"
											href="/picok_project/like.do?id=${mvo.id}">Like</a>
									</c:otherwise>
								</c:choose>
								<a class="dropdown-item"
									href="/picok_project/front?command=logout">Logout</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>

		</div>
	</nav>
	<!-- 헤더끝 -->





<script>
function showAlert() {
  alert("로그인이 필요합니다.");
  window.location.href = "/picok_project/login/login.jsp";
}
</script>

</body>
</html>