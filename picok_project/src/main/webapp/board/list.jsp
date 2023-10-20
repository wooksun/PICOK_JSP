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

	<!-- 좌측 메뉴 -->
	<div class="container py-5">
		<div class="row">

			<div class="col-lg-3">
				<h1 class="h1">
					<c:choose>
						<c:when test="${param.category == 1}">Nature</c:when>
						<c:when test="${param.category == 2}">City</c:when>
						<c:when test="${param.category == 3}">Daily</c:when>
					</c:choose>
				</h1>
				<br/>
				<ul class="list-unstyled templatemo-accordion">
					<li class="pb-3"><a
						class="collapsed d-flex justify-content-between h4 text-decoration-none"
						href="./list.do?category=${param.category}&currentPage=${param.currentPage}">
							Latest <i class="fa fa-fw fa-chevron-circle-right mt-1"></i>
					</a></li>
					<li class="pb-3"><a
						class="collapsed d-flex justify-content-between h4 text-decoration-none"
						href="./list2.do?category=${param.category}&currentPage=${param.currentPage}">
							Popular <i
							class="pull-right fa fa-fw fa-chevron-circle-right mt-1"></i>
					</a></li>
				</ul>
			</div>
			<!-- 좌측 메뉴 끝  -->

			<!-- 게시판 -->
			<!-- 게시글  -->
			<div class="col-lg-9">
				<div class="row">
					<div class="col-md-6">
						<ul class="list-inline shop-top-menu pb-3 pt-1">
							<li class="list-inline-item"></li>
							<li class="list-inline-item"></li>
							<li class="list-inline-item"></li>
						</ul>
					</div>
				</div>
				<div class="row">
					<c:forEach var="board" items="${boardList.list}">
						<div class="col-md-4">
							<div class="card mb-4 product-wap rounded-0">
								<div class="card rounded-0">
									<img class="card-img rounded-0 img-fluid" style="max-height: 300px; overflow: hidden;   width: auto;
  height: 100%; object-fit: cover;"
										src="/picok_project/upload/<c:out value='${board.getFile_name()}' />">
									<div
										class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
										<ul class="list-unstyled">
										</ul>
									</div>
								</div>
								<div class="card-body">
									<a
										href="./BoardSingleController?category=${board.getCategory()}&idx=${board.getBoard_idx() }"
										class="h3 text-decoration-none" style="font-style: italic;"> <c:out
											value="${board.getBoard_title()}" />
									</a>
									<p>
										<c:out value="${board.getBoard_content()}" />
									</p>
								
								</div>
							</div>
						</div>
					</c:forEach>


				</div>
				<!-- 페이지 이동 버튼 -->
				<div class="row">
					<ul class="pagination pagination-md justify-content-end">
						<li
							class="page-item ${boardList.currentPage == 1 ? 'disabled' : ''}">
							<c:if test="${boardList.currentPage > 1}">
								<a
									class="page-link rounded-pill mr-2 shadow-sm border-0 text-dark bg-light"
									href="?category=${param.category}&currentPage=1" tabindex="-1">처음</a>
							</c:if> <c:if test="${boardList.currentPage <= 1}">
								<span
									class="page-link mr-2 shadow-sm border-0 text-dark bg-light"
									style="border-radius: 8px;">처음</span>
							</c:if>
						</li>
						<li
							class="page-item ${boardList.currentPage == 1 ? 'disabled' : ''}">
							<c:if test="${boardList.currentPage > 1}">
								<a
									class="page-link rounded-pill mr-2 shadow-sm border-0 text-dark bg-light"
									href="?category=${param.category}&currentPage=${boardList.currentPage - 1}">이전</a>
							</c:if> <c:if test="${boardList.currentPage <= 1}">
								<span
									class="page-link mr-2 shadow-sm border-0 text-dark bg-light"
									style="border-radius: 8px;">이전</span>
							</c:if>
						</li>
						<c:forEach var="i" begin="${boardList.startPage}"
							end="${boardList.endPage}" step="1">
							<li
								class="page-item ${boardList.currentPage == i ? 'active' : ''}">
								<c:if test="${boardList.currentPage == i}">
									<span class="page-link  bg-info border-0 text-white"
										style="border-radius: 8px;">${i}</span>
								</c:if> <c:if test="${boardList.currentPage != i}">
									<a class="page-link text-dark bg-light border-0"
										href="?category=${param.category}&currentPage=${i}"
										style="border-radius: 8px;">${i}</a>
								</c:if>
							</li>
						</c:forEach>
						<li
							class="page-item ${boardList.currentPage >= boardList.totalPage ? 'disabled' : ''}">
							<c:if test="${boardList.currentPage < boardList.totalPage}">
								<a class="page-link  mr-2 shadow-sm border-0 text-dark bg-light"
									href="?category=${param.category}&currentPage=${boardList.currentPage + 1}"
									style="border-radius: 8px;">다음</a>
							</c:if> <c:if test="${boardList.currentPage >= boardList.totalPage}">
								<span
									class="page-link mr-2 shadow-sm border-0 text-dark bg-light"
									style="border-radius: 8px;">다음</span>
							</c:if>
						</li>
						<li
							class="page-item ${boardList.currentPage >= boardList.totalPage ? 'disabled' : ''}">
							<c:if test="${boardList.currentPage < boardList.totalPage}">
								<a class="page-link  mr-2 shadow-sm border-0 text-dark bg-light"
									href="?category=${param.category}&currentPage=${boardList.totalPage}"
									style="border-radius: 8px;">마지막</a>
							</c:if> <c:if test="${boardList.currentPage >= boardList.totalPage}">
								<span
									class="page-link mr-2 shadow-sm border-0 text-dark bg-light"
									style="border-radius: 8px;">마지막</span>
							</c:if>
						</li>
					</ul>
				</div>
				<!-- 페이징 끝 -->

			</div>
		</div>
		<!-- 게시판 끝 -->
	</div>


	<!-- 푸터 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<!-- Start Script -->
	<script src="https://kit.fontawesome.com/a5f5e6fa14.js"
		crossorigin="anonymous"></script>
	<script src="/picok_project/assets/js/jquery-1.11.0.min.js"></script>
	<script src="/picok_project/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/picok_project/assets/js/bootstrap.bundle.min.js"></script>
	<script src="/picok_project/assets/js/custom.js"></script>
	<!-- End Script -->
</body>

</html>