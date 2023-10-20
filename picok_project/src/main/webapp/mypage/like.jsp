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
	<link rel="stylesheet" href="/picok_project/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="/picok_project/assets/css/templatemo.css">
	<link rel="stylesheet" href="/picok_project/assets/css/fontawesome.min.css">
	<link rel="stylesheet" href="/picok_project/assets/css/liststyle.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
	<!-- 파비콘 -->
    <link rel="icon" href="/picok_project/assets/img/favicon.ico" type="image/x-icon">


</head>


<body>

	<!-- 헤더 include -->
	<jsp:include page="../common/header.jsp"></jsp:include>

	<!-- 상단메뉴소개 -->
	<div class="container my-4">
		<br />
		<div class="row text-center py-2">
			<div class="col-lg-6 m-auto">
				<h1 class="h1">Like</h1>
			</div>
		</div>
		
		

		<!-- 게시글  -->
		<div class="col-lg-12">
			<div class="row justify-content-center">
				<div class="col-md-4">
					<ul class="list-inline shop-top-menu pb-1 pt-3">
						<li class="list-inline-item"></li>
						<li class="list-inline-item"></li>
						<li class="list-inline-item"></li>
					</ul>
				</div>
			</div>
			<div class="row justify-content-center">
				<c:forEach var="board" items="${boardList}">
					<div class="col-md-4" style="width: 300px;">
						<div class="card mb-3 product-wap rounded-0" style="width: 300px;">
							<div class="card rounded-0">
								<div
									style="position: relative; width: 300px; height: 300px; overflow: hidden;">
									<img class="card-img rounded-0 img-fluid"
										style="object-fit: cover; width: 100%; height: 100%;"
										src="/picok_project/upload/<c:out value='${board.getFile_name()}' />" onclick="goSingle(${board.getCategory()},${board.getBoard_idx()})">
									<!-- 하트아이콘 --><!-- 
									<div class="icon-overlay"
										style="position: absolute; top: 10px; right: 10px;">
										<i class="fa-solid fa-heart"></i>
									</div> -->
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<br/> <br/>

	

		</div>
		
	</div>
	<!-- 푸터 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

<script>
function goSingle(a,b) {
	   // JSP 페이지로 이동'
	   window.location.href = './BoardSingleController?category='+ a+'&idx='+b;
}
</script>

	<!-- Start Script -->
	<script src="https://kit.fontawesome.com/a5f5e6fa14.js"
		crossorigin="anonymous"></script>
	<script src="/picok_project/assets/js/jquery-1.11.0.min.js"></script>
	<script src="/picok_project/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/picok_project/assets/js/bootstrap.bundle.min.js"></script>
	<script src="/picok_project/assets/js/templatemo.js"></script>
	<script src="/picok_project/assets/js/custom.js"></script>
	<!-- End Script -->
</body>

</html>