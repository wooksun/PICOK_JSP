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
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">

<!-- 파비콘 -->
<link rel="icon" href="assets/img/ms-icon-310x310.png"
	type="image/x-icon">
	
</head>
<body>
	<!-- 헤더 include -->
	<jsp:include page="../common/header.jsp"></jsp:include>


	<section class="bg-light py-5">
		<div class="container my-4">
			<div class="row text-center py-2">
				<div class="col-lg-6 m-auto">
					<h1 class="h1">My Page</h1>
					<p>profile</p>
				</div>
			</div>

			<div class="row d-flex flex-row">
				<!--Controls-->
				<div class="col-1 align-self-center">
					<!-- 프로필 이미지나 아이콘 등을 여기에 추가할 수 있습니다. -->
				</div>

				<!-- 프로필 정보 -->
				<div class="col-lg-6 m-auto">
					<div class="row">
						<div class="col-6">
							<p>아이디:</p>
						</div>
						<div class="col-6">
							<p>${sessionScope.mvo.id}</p>
							<!-- 여기에 아이디 정보를 출력합니다. -->
						</div>
					</div>
					<div class="row">
						<div class="col-6">
							<p>이름:</p>
						</div>
						<div class="col-6">
							<p>${sessionScope.mvo.name}</p>
							<!-- 여기에 이름 정보를 출력합니다. -->
						</div>
					</div>
					<div class="row">
						<div class="col-6">
							<p>닉네임:</p>
						</div>
						<div class="col-6">
							<p>${sessionScope.mvo.nickname}</p>
							<!-- 여기에 닉네임 정보를 출력합니다. -->
						</div>
					</div>
					<div class="row">
						<div class="col-6">
							<p>이메일:</p>
						</div>
						<div class="col-6">
							<p>${sessionScope.mvo.email}</p>
							<!-- 여기에 이메일 정보를 출력합니다. -->
						</div>
					</div>
					<div class="row">
						<div class="col-6">
							<p>주소:</p>
						</div>
						<div class="col-6">
							<p>${sessionScope.mvo.addr}</p>
							<!-- 여기에 주소 정보를 출력합니다. -->
						</div>
					</div>
					<div class="row">
						<div class="col-6">
							<p>휴대폰번호:</p>
						</div>
						<div class="col-6">
							<p>${sessionScope.mvo.phone_num}</p>
							<!-- 여기에 휴대폰번호 정보를 출력합니다. -->
						</div>
					</div>
<br/>
				</div>
            <div class="form-group d-flex justify-content-center">
                <form action="update.jsp" method="post">
                    <button type="submit" class="btn btn-info">수정하기</button>
                </form>&nbsp;&nbsp;&nbsp;&nbsp;
                 <form action="deleteAccount.jsp" method="post">
                   <button type="submit" class="btn btn-outline-danger">탈퇴하기</button>
              </form>
            </div>

				</div>
			</div>

	</section>



	<!-- 푸터 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<!-- Script -->
	<script src="https://kit.fontawesome.com/a5f5e6fa14.js"
		crossorigin="anonymous"></script>
	<script src="/picok_project/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/picok_project/assets/js/bootstrap.bundle.min.js"></script>
	<script src="/picok_project/assets/js/templatemo.js"></script>
	<script src="/picok_project/assets/js/custom.js"></script>
</body>
</html>