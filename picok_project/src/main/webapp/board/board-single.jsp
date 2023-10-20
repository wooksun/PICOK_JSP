<%@page import="vo.BoardCommentVO"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!--jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Picok</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon"
	href="/picok_project/assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="/picok_project/assets/img/favicon.ico">

<link rel="stylesheet"
	href="/picok_project/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/picok_project/assets/css/templatemo.css">
<link rel="stylesheet" href="/picok_project/assets/css/custom.css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet"
	href="/picok_project/assets/css/fontawesome.min.css">

<link rel="stylesheet" type="text/css"
	href="/picok_project/assets/css/slick.min.css">
<link rel="stylesheet" type="text/css"
	href="/picok_project/assets/css/slick-theme.css">
<!-- 파비콘 -->
<link rel="icon" href="assets/img/ms-icon-310x310.png"
	type="image/x-icon">

</head>

<body>

	<!-- 헤더 include -->
	<jsp:include page="../common/header.jsp"></jsp:include>


	<!-- Open Content -->
	<section class="bg-light">
		<div class="container pb-5" style="max-width: 70%">
			<div class="row">
				<div class="col-lg-7 mt-5">
					<div class="card mb-5">
						<img class="card-img img-fluid"
							src="/picok_project/upload/${singlepage.getFile_name()}"
							alt="Card image cap" id="product-detail">
					</div>
				</div>
				<!-- col end -->

				<div class="col-md-5 mt-5" style="max-width: 500px;">
					<div class="card">
						<div class="card-body">
							<h1 class="h1" style="font-style: italic;">${singlepage.getBoard_title()}</h1>
							<br />
							<ul class="list-inline">
								<li class="list-inline-item">
									<h5>작성자</h5>
								</li>
								<li class="list-inline-item">
									<p class="text-muted">
										<strong>${singlepage.getId()}</strong> <input type="hidden"
											id="writer-id" value="${singlepage.getId()}" />
									</p>
								</li>
								<br />
								<li class="list-inline-item">
									<h5>작성일</h5>
								</li>
								<li class="list-inline-item">
									<p class="text-muted">
										<strong>${singlepage.getBoard_reg_date()}</strong>
									</p>
								</li>
								<br />
								<li class="list-inline-item">
									<h5>내용</h5>
								<li class="list-inline-item">
									<p class="text-muted">${singlepage.getBoard_content()}</p>

								</li>
							</ul>
							<ul class="list-inline">
								<p class="py-1">
									<i class="fa fa-eye text-warning"></i> <span
										class="list-inline-item text-dark">조회수
										${singlepage.getView_num()}</span> <i
										class="fa fa-thumbs-up text-warning"></i> <span
										class="list-inline-item text-dark">좋아요 ${countLikes}</span>
								</p>
							</ul>
							<!-- 버튼 양 옆 마진 -->
							<input type="hidden" name="product-title" value="Activewear">
							<div class="row">
								<div class="col-auto">
									<ul class="list-inline pb-3">

									</ul>
								</div>
								<div class="col-auto">
									<ul class="list-inline pb-3">

									</ul>
								</div>
							</div>


							<div class="row pb-2">
								<div class="col d-grid">
									<!-- 수정 버튼 -->
									<c:choose>
										<c:when test="${empty mvo}">
											<!-- 세션 정보가 없는 경우 아무 내용도 표시하지 않음 -->
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${singlepage.getId() == mvo.id}">
													<!-- 작성자와 현재 사용자가 동일한 경우 -->
													<button type="submit" class="btn btn-info btn-lg"
														onclick="editPost()" style="color: white;">수정</button>
												</c:when>
												<c:otherwise>
													<!-- 작성자와 현재 사용자가 다른 경우 -->
													<!-- 좋아요 버튼 -->
													<c:choose>
														<c:when test="${likes == false}">
															<button type="button" class="btn btn-outline-info btn-lg"
																onclick="addLikes()">좋아요</button>
														</c:when>
														<c:otherwise>
															<button type="button" class="btn btn-info btn-lg"
																onclick="addLikes()">좋아요 취소</button>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col d-grid">
									<!-- 삭제 버튼 -->
									<c:choose>
										<c:when test="${empty mvo}">
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${singlepage.getId() == mvo.id}">
													<!-- 작성자와 현재 사용자가 동일한 경우 -->
													<button type="submit" class="btn btn-danger btn-lg"
														onclick="deletePost()">삭제</button>
												</c:when>
												<c:otherwise>
													<!-- 작성자와 현재 사용자가 다른 경우 -->
													<!-- 신고 버튼 -->
													<c:choose>
														<c:when test="${report == false}">
															<button type="button"
																class="btn btn-outline-warning btn-lg"
																onclick="addReport()">신고하기</button>
														</c:when>
														<c:otherwise>
															<button type="button" class="btn btn-warning btn-lg"
																disabled>신고 완료</button>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>

								</div>
								<div>
									<br /> <br />
									<h5>댓글</h5>
									<hr>
									<div class="comment-view">
										<div id="commentList">
											<c:choose>
												<c:when test="${empty listResult}">
													<p>아직 댓글이 없습니다.</p>
												</c:when>
												<c:otherwise>
													<c:forEach var="comment" items="${listResult}">
														<div>
															<mark>
																<strong>${comment.nickname}</strong>
															</mark>
															/ <small>${comment.comment_content}</small>
															<c:if test="${comment.id == mvo.id}">
																<button type="button" class="btn btn-light-sm"
																	onclick="deleteComment(${singlepage.getBoard_idx()},${comment.comment_idx})">삭제</button>
															</c:if>
														</div>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</div>
									</div>

								</div>
							</div>

						</div>
					</div>
					<!-- 댓글 폼 -->
					<div class="comment">
						<div class="comment-body">
							<br />
						</div>
						<%
						// 세션에서 값 가져오기
						MemberVO id = (MemberVO) session.getAttribute("mvo");

						// 가져온 값 출력하기
						// out.println("현재 로그인된 사용자 ID: " + id);
						BoardCommentVO bcvo = (BoardCommentVO) session.getAttribute("bcvo");
						%>
						<div>
							작성자 <input type="text" value="${mvo.nickname}"
								readonly="readonly" style="width: 100%" /> <input type="hidden"
								id="writer" value="${mvo.id}">
							<!-- 추가 -->
							<input type="hidden" id="board_idx"
								value="${singlepage.getBoard_idx()}"> <input
								type="hidden" id="category" value="${singlepage.getCategory()}">
							<input type="hidden" value="${singlepage.getBoard_idx()}">
						</div>
						댓글
						<div class="form-group d-flex">
							<c:choose>
								<c:when test="${empty mvo}">
									<textarea class="form-control" id="comment" name="comment"
										rows="2" style="resize: none; width: 600px;"
										readonly="readonly">로그인 후에 작성할 수 있습니다.</textarea>
								</c:when>
								<c:otherwise>
									<textarea class="form-control" id="comment" name="comment"
										rows="4" style="resize: none; width: 600px;"></textarea>
								</c:otherwise>
							</c:choose>
							<button type="submit" class="btn btn-outline-info ml-2"
								style="width: 100px;" onclick="addComment()">작성</button>
						</div>
					</div>
				</div>


				<!-- 저장된 댓글 출력 -->

			</div>
		</div>
	</section>
	<!-- 푸터 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script>
	
	
		//	댓글
		function addComment() {
			// 작성한 댓글 내용 가져오기
			var commentContent = document.getElementById("comment").value;
			// 작성자 정보 가져오기
			var commentWriter = document.getElementById("writer").value; // writer는 댓글 작성자, writer-id는 게시글 작성자
			var commentBoardIdx = document.getElementById("board_idx").value;

			// Ajax를 사용하여 댓글 저장 요청
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "BoardCommentController", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					// 댓글 저장 후 서버로부터 응답 받음
					// 새로운 댓글을 commentList에 추가
					var commentList = document.getElementById("commentList");
					var newComment = document.createElement("div");
				}
			};
			xhr.send("comment_content=" + encodeURIComponent(commentContent)
					+ "&board_idx=" + encodeURIComponent(commentBoardIdx)
					+ "&id=" + encodeURIComponent(commentWriter));
			location.reload();
		}

		//	좋아요
		function addLikes() {
			// 작성자 정보 가져오기
			var commentWriter = document.getElementById("writer-id").value; // writer는 댓글 작성자, writer-id는 게시글 작성자
			var commentBoardIdx = document.getElementById("board_idx").value;

			// xhr로 요청
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "LikeAddController", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {

				}
			};
			xhr.send("&board_idx=" + encodeURIComponent(commentBoardIdx)
					+ "&id=" + encodeURIComponent(commentWriter));
			location.reload();
		}

		//	신고
		function addReport() {
			var commentBoardIdx = document.getElementById("board_idx").value;

			// xhr로 요청
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "ReportController", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {

				}
			};
			xhr.send("&board_idx=" + encodeURIComponent(commentBoardIdx));
			location.reload();
		}

		//	작성자 게시글 삭제 메소드
		function deletePost() {
			var boardWriter = document.getElementById("board_idx").value;
			var category = document.getElementById("category").value;

			// xhr로 요청
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "BoardDeleteController", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					/* var deletionMessage = document.getElementById("deletion-message");
					 deletionMessage.style.display = "block"; */
					alert("삭제가 완료되었습니다.");
					location.href = '/picok_project/list.do?category='
							+ category + '&currentPage=1';
				}
			};
			xhr.send("&board_idx=" + encodeURIComponent(boardWriter));
		}

		// 작성자 게시글 수정 메소드
		function editPost() {
			var board_idx = ${singlepage.getBoard_idx()};

			// xhr로 요청
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "BoardSingle2Controller", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					location.href = "/picok_project/BoardSingle2Controller?board_idx="
							+ board_idx;
				}
			};
			xhr.send("&board_idx=" + encodeURIComponent(board_idx));
		}
		
	    function deleteComment(board_idx, comment_idx) {
	    	var confirmDelete = confirm("댓글을 삭제하시겠습니까?");
	        if (confirmDelete) {
	            var xhr = new XMLHttpRequest();
	            xhr.open("POST", "CommentDeleteController", true);
	            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	            xhr.onreadystatechange = function () {
	                if (xhr.readyState === 4 && xhr.status === 200) {
	                       
						    alert("삭제 완료되었습니다.");
	                        location.reload();
	                }
	            };
	            xhr.send("board_idx=" + encodeURIComponent(board_idx) + "&comment_idx=" + encodeURIComponent(comment_idx));
	        }
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