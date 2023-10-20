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
<link rel="stylesheet"
	href="/picok_project/assets/css/fontawesome.min.css">
<link rel="stylesheet" href="/picok_project/assets/css/liststyle.css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
	<!-- 파비콘 -->
<link rel="icon" href="assets/img/ms-icon-310x310.png"
	type="image/x-icon">
	
<style>
  .loading-bar {
    width: 100%;
    height: 4px;
    background-color: #ccc;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 9999;
    display: none;
  }

  .loading-bar.active {
    display: block;
  }
</style>
	
</head>

<body>
	<!-- 헤더 include -->
	<jsp:include page="../common/header.jsp"></jsp:include>



	<!-- 상단메뉴소개 -->
	<div class="container my-4">
		<br />
		<div class="row text-center py-2">
			<div class="col-lg-6 m-auto">
				<h1 class="h1">My Photo</h1>
			</div>
		</div><br/><br/>
		<!-- 게시글 영역 -->
		<div class="col-lg-12">
			<div class="row justify-content-center">
				<c:forEach var="board" items="${boardList}">
					<div class="col-md-4" style="width: 300px;">
						<div class="card mb-3 product-wap rounded-0" style="width: 300px;">
							<div class="card rounded-0">
								<div style="width: 300px; height: 300px; overflow: hidden;">
									<img class="card-img rounded-0 img-fluid"
										style="object-fit: cover; width: 100%; height: 100%; z-index: 9999px;" 
										src="/picok_project/upload/<c:out value='${board.getFile_name()}' />" onclick="goSingle(${board.getCategory()},${board.getBoard_idx()})">
								</div>
							</div>
						</div>
					</div>
					</c:forEach>
			</div></div>
		</div>


	<!-- 푸터 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>




<script>


var isLoading = false; // 데이터 로딩 중인지 여부를 확인하기 위한 변수

// 스크롤 이벤트 리스너 등록
window.addEventListener('scroll', handleScroll);

// 스크롤 이벤트 핸들러
function handleScroll() {
	// 스크롤이 맨 아래에 도달했을 때 추가 데이터 로딩
	if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
		loadMoreData();
	}
}

var currentPage = 2;

function loadMoreData() {
  if (isLoading) {
    return;
  }

  isLoading = true;


  fetch('/picok_project/myphotonext.do?id=sky20808&currentPage=' + currentPage, {
	  method: 'GET',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Failed to load data.');
      }
      return response.json();
    })
    .then((data) => {
      updateUI(data);

      currentPage++; 
      if (currentPage >= data.page.totalPages) {
        window.removeEventListener('scroll', handleScroll);
      }

      isLoading = false;
    })
    .catch((error) => {
      console.error(error);
    });
}




// UI 업데이트 함수
function updateUI(data) {
	console.log(data);
	var totalCount = data.page.totalCount;
	var pageSize = data.page.pageSize;
	var totalPages = Math.ceil(totalCount / pageSize); // 총 페이지 수
    var container = document.querySelector(".row.justify-content-center"); // container 변수 정의

	for (var i = 0; i < data.boardList.length; i++) {

	    var imageUrl = "/picok_project/upload/" + data.boardList[i].file_name; 
		  var boardHtml = `
		    <div class="col-md-4" style="width: 300px;">
		      <div class="card mb-3 product-wap rounded-0" style="width: 300px;">
		        <div class="card rounded-0">
		          <div style="position: relative; width: 300px; height: 300px; overflow: hidden;">
		            <img class="card-img rounded-0 img-fluid"
		              style="object-fit: cover; width: 100%; height: 100%;"
		              src= "`+imageUrl+`" onclick="goSingle(`+data.boardList[i].category+`,`+data.boardList[i].board_idx+`)"> 
		          </div>
		        </div>
		      </div>
		    </div>
		  `;
		  container.innerHTML += boardHtml;
		}

	// 마지막 페이지인 경우 스크롤 이벤트 리스너 제거
	if (currentPage >= totalPages) {
		window.removeEventListener("scroll", handleScroll);
	}
}



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


	<!-- 스크롤 감지 및 추가 데이터 로딩 -->


</body>
</html>
