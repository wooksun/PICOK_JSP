
var isLoading = false; // 데이터 로딩 중인지 여부를 확인하기 위한 변수
var currentPage = 1; // 현재 페이지 번호
var container = document.getElementById("boardContainer");

// 스크롤 이벤트 핸들러
function handleScroll() {
	// 스크롤이 맨 아래에 도달했을 때 추가 데이터 로딩
	if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
		loadMoreData();
	}
}

// 데이터 로딩 함수
function loadMoreData() {
	// 이미 데이터를 로딩 중인 경우 중복 로딩 방지
	if (isLoading) {
		return;
	}

	isLoading = true; // 데이터 로딩 상태로 변경

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				var response = JSON.parse(xhr.responseText);
				updateUI(response);

				// 데이터를 모두 로딩한 경우 멈추기
				if (response.page.currentPage >= response.page.totalPages) {
					window.removeEventListener("scroll", handleScroll);
				}

				isLoading = false; // 데이터 로딩 완료 상태로 변경
				currentPage++; // 페이지 번호 증가

			} else {
				console.error('Failed to load data.');
			}
		}
	};

	xhr.open('GET', '/picok_project/myphoto.do?id=sky20808&currentPage=' + currentPage, true);
	xhr.send();
}


// UI 업데이트 함수
function updateUI(data) {
	var totalCount = data.page.totalCount;
	var pageSize = data.page.pageSize;
	var currentPage = data.page.currentPage;
	var totalPages = Math.ceil(totalCount / pageSize); // 총 페이지 수
	var url = data.boardList[0].file_name;
	console.log(url + "은 url");
	console.log(data.boardList);
	console.log(data.boardList.length);

	for (var i = 0; i < data.boardList.length; i++) {
		var imageUrl = "/picok_project/upload/" + data.boardList[i].file_name; // 이미지 경로 생성
		console.log(imageUrl);
		var boardHtml = `
            <div class="col-md-4" style="width: 300px;">
                <div class="card mb-3 product-wap rounded-0" style="width: 300px;">
                    <div class="card rounded-0">
                        <div style="position: relative; width: 300px; height: 300px; overflow: hidden;">
                            <img class="card-img rounded-0 img-fluid"
                                style="object-fit: cover; width: 100%; height: 100%;"
                      	  src="${imageUrl}">
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

// 스크롤 이벤트 리스너 등록
window.addEventListener("scroll", handleScroll);

// 초기 데이터 로딩
loadMoreData();
