//	XHR 객체의 readyState
//	0: 아직 실행되지 않음 
//	1: 로딩중
//	2: 로딩됨
//	3: 통신중
//	4: 통신완료
//	console.log('readyState: ' + searchRequest.readyState);

//	XHR 객체의 status
//	200: 수신성공
//	3xx: 금지
//	4xx: 페이지없음
//	5xx: 서버오류
//	console.log('status: ' + searchRequest.status);

//	비밀번호가 일치하는가 확인하는 함수
function passwordCheckFunction() {
 	var pw = $("#password1").val();
	var id = $("#id").val();
	 let password1 = $('#password1').val();
	 let password2 = $('#password2').val();
			
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	var hangulcheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	 
	if(false === reg.test(pw)) {
	alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
	}else if(/(\w)\1\1\1/.test(pw)){
	 alert('같은 문자를 4번 이상 사용하실 수 없습니다.');
	 return false;
//	 }else if(pw.search(id) > -1){
//	 alert("비밀번호에 아이디가 포함되었습니다.");
//	  return false;
	 }else if(pw.search(/\s/) != -1){
	 alert("비밀번호는 공백 없이 입력해주세요.");
	 return false;
	 }else if(hangulcheck.test(pw)){
	 alert("비밀번호에 한글을 사용 할 수 없습니다."); 
	 }else {
	 console.log("통과");
	 }
if (password1 != password2) {
		$('#passwordCheckMessage').html('비밀번호가 일치하지 않습니다.');
	} else {
		$('#passwordCheckMessage').html('');
	}
}



//	아이디 중복 검사를 실행하는 함수
function registerCheckFunction() {
//	ajax를 이용해서 중복 검사할 아이디를 얻어온다.
	let id = document.getElementById('id').value;

//	jQuery ajax
	$.ajax({
		type: 'POST', // 요청 방식
		url: '../UserRegisterCheck',
		data: {
			id: id
		},
		success: res => {
			switch (res) {
				case '1':
					$('#messageContent').html('아이디를 입력하고 중복 체크 버튼을 누르세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#id').val('');
					break;
				case '2':
					$('#messageContent').html('사용중인 아이디 입니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#id').val('');
					break;
				case '3':
					$('#messageContent').html('사용 가능한 아이디입니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					break;
			}
			$('#messageModal').modal('show');
		},
		error: e => console.log('1요청 실패:', e.status)
	});
}


//	닉네임 중복 검사를 실행하는 함수
function registerCheckFunction2() {
//	ajax를 이용해서 중복 검사할 닉네임을 얻어온다.
	let nickname = document.getElementById('nickname').value;

//	jQuery ajax
	$.ajax({
		type: 'POST', // 요청 방식
		url: '../UserRegisterCheck2',
		data: {
			nickname: nickname
		},
		success: res => {
			switch (res) {
				case '1':
					$('#messageContent2').html('닉네임를 입력하고 중복 체크 버튼을 누르세요.');
					$('#errorMessage2').html('닉네임를 입력하고 중복 체크 버튼을 누르세요.');
					$('#messageCheck2').attr('class', 'modal-content panel-warning');
					$('#nickname').val('');
					break;
				case '2':
					$('#messageContent2').html('사용중인 닉네임 입니다.');
					$('#errorMessage2').html('사용중인 닉네임 입니다.');
					$('#messageCheck2').attr('class', 'modal-content panel-warning');
					$('#nickname').val('');
					break;
				case '3':
					$('#messageContent2').html('사용 가능한 닉네임입니다.');
					$('#messageCheck2').attr('class', 'modal-content panel-success');
					break;
			}
			$('#messageModal2').modal('show');
		},
		error: e => console.log('2요청 실패:', e.status)
	});
}

















