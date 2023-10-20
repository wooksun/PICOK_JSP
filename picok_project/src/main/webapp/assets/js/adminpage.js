//카테고리별 글을 따로 모아보기.. 
function getFilteredData() {
	var category = document.getElementById("categorySelect").value;

	if (category === "0") {
		// 카테고리가 0인 경우 초기화면으로 돌아감
	} else {
		// 카테고리가 0이 아닌 경우 필터링된 데이터를 가져옴
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					// 서버로부터 데이터를 정상적으로 받아왔을 경우 테이블 업데이트
					var parser = new DOMParser();
					var responseDoc = parser.parseFromString(xhr.responseText, "text/html");
					var tableBody = responseDoc.querySelector("tbody");

					// 현재 문서의 테이블 바디를 찾아서 새로운 데이터로 교체
					var currentTableBody = document.querySelector("tbody");
					currentTableBody.innerHTML = tableBody.innerHTML;
				} else {
					// 서버로부터 데이터를 받지 못했을 경우 에러 처리
					console.error("Failed to get filtered data.");
				}
			}
		};
		xhr.open('GET', 'admin_board.do/filter?category=' + category, true);
		xhr.send();
	}
}




function deleteData() {
    var checkboxes = document.getElementsByClassName('checkbox');

    // 선택한 체크박스의 값을 저장할 배열을 생성합니다.
    var selectedIds = [];

    // forEach를 사용하여 각 체크박스에 대해 처리합니다.
    Array.prototype.forEach.call(checkboxes, function(checkbox) {
        // 체크박스가 체크된 경우에만 처리합니다.
        if (checkbox.checked) {
            // 체크된 체크박스의 데이터를 가져와서 배열에 추가합니다.
            var boardIdx = checkbox.getAttribute('data-board-idx');
            selectedIds.push(boardIdx);
        }
    });

    // 선택된 글을 컨트롤러로 보내 삭제하는 함수를 호출합니다.
    if (selectedIds.length === 0) {
        // 선택된 체크박스가 없는 경우에 대한 처리
        alert('선택된 글이 없습니다.');
    } else {
        var xhr = new XMLHttpRequest();
        var url = 'admin_board.do/delete'; // 컨트롤러 URL

        xhr.open('POST', url, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // 요청이 성공적으로 완료되었을 때 처리
                    alert('삭제 요청이 성공적으로 완료되었습니다.');
                    // 페이지를 새로고침하여 변경된 내용을 확인합니다.
                    window.location.reload();
                } else {
                    // 요청이 실패하거나 오류가 발생한 경우 처리
                    alert('삭제 요청이 실패하였거나 오류가 발생하였습니다.');
                }
            }
        };
        xhr.send('postIds=' + encodeURIComponent(selectedIds.join(',')));
    }
}




//회원탈퇴시키기
function deleteMember() {
    var checkboxes = document.getElementsByClassName('checkbox');
    var selectedIds = [];

    Array.prototype.forEach.call(checkboxes, function(checkbox) {
        if (checkbox.checked) {
            var memberIdx = checkbox.getAttribute('data-member-idx');
            selectedIds.push(memberIdx);
        }
    });

    if (selectedIds.length === 0) {
        alert('선택된 회원이 없습니다.');
        return;
    }

    var confirmation = confirm('정말로 선택한 회원을 삭제하시겠습니까?');
    if (confirmation) {
        var xhr = new XMLHttpRequest();
        var url = 'admin_member.do/delete';

        xhr.open('POST', url, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert('삭제 요청이 성공적으로 완료되었습니다.');
                    window.location.reload();
                } else {
                    alert('삭제 요청이 실패하였거나 오류가 발생하였습니다.');
                }
            }
        };

        xhr.send('postIds=' + encodeURIComponent(selectedIds.join(',')));
    } else {
        alert('삭제가 취소되었습니다.');
    }
}



function goBack() {
    window.location.href = 'admin/adminpage.jsp'; // adminpage.jsp로 이동
}


