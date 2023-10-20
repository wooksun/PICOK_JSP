<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Picok</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- 부트스트랩 외 아이콘 -->
    <link rel="stylesheet" href="/picok_project/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/picok_project/assets/css/templatemo.css">
    <link rel="stylesheet" href="/picok_project/assets/css/custom.css">
    <link rel="stylesheet" href="/picok_project/assets/css/fontawesome.min.css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">

    <!-- Load map styles -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
        integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
        crossorigin="" />

    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=77de66c22d90259a9cbc73de80505b03&libraries=services"></script>
</head>

<body>

    <!-- 헤더 include -->
    <jsp:include page="../common/header.jsp"></jsp:include>

    <!-- Start Content Page -->
    <div class="container-fluid bg-light py-5">
        <div class="row py-5">
            <div class="col-md-6 m-auto text-center">
                <h1 class="h1">Contact Us</h1>
                <p>
                    궁금한 점이 있거나 의견을 공유하고 싶으시다면 아래의 폼을 통해 언제든지 연락주세요.<br /> <br />
                </p>
                <h5 style="font-size: 15px; font-style: italic;">PICOK</h5>
                <h5 style="font-size: 15px; font-weight: lighter; font-style: italic;">서울특별시 종로구 관철동 180</h5>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6" style="padding-top: 100px; padding-left:100px; ">
                <div id="map" style="height: 350px; "></div>
            </div>
            <div class="col-md-6">
                <!-- Start Contact -->
                <div class="container py-5">
                    <div class="row py-5">
                        <form class="col-md-9 m-auto" method="post" role="form">
                            <div class="row">
                                <div class="form-group col-md-6 mb-3">
                                    <label for="inputname">이름</label>
                                    <input type="text" class="form-control mt-1" id="name" name="name"
                                        placeholder="Name">
                                </div>
                                <div class="form-group col-md-6 mb-3">
                                    <label for="inputemail">Email</label>
                                    <input type="email" class="form-control mt-1" id="email" name="email"
                                        placeholder="Email">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="inputsubject">제목</label>
                                <input type="text" class="form-control mt-1" id="subject" name="subject"
                                    placeholder="Subject">
                            </div>
                            <div class="mb-3">
                                <label for="inputmessage">내용</label>
                                <textarea class="form-control mt-1" id="message" name="message"
                                    placeholder="Message" rows="8"></textarea>
                            </div>
                            <div class="row">
                                <div class="col text-end mt-2">
                                    <button type="submit" class="btn btn-success btn-lg px-3">보내기!</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- End Contact -->
            </div>
        </div>
    </div>

    <!-- 푸터 include -->
    <jsp:include page="../common/footer.jsp"></jsp:include>

    <!-- Start Script -->
    <script src="https://kit.fontawesome.com/a5f5e6fa14.js" crossorigin="anonymous"></script>
    <script src="/picok_project/assets/js/jquery-1.11.0.min.js"></script>
    <script src="/picok_project/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="/picok_project/assets/js/bootstrap.bundle.min.js"></script>
    <script src="/picok_project/assets/js/templatemo.js"></script>
    <script src="/picok_project/assets/js/custom.js"></script>
    <!-- End Script -->

    <script>
        var mapContainer = document.getElementById('map'); // 지도를 표시할 div
        var mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

        // 지도를 생성합니다
        var map = new kakao.maps.Map(mapContainer, mapOption);

        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch('서울특별시 종로구 관철동 180', function (result, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });
                // 인포윈도우로 장소에 대한 설명을 표시합니다
                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">picok사옥</div>'
                });
                infowindow.open(map, marker);
                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
            }
        });
    </script>
</body>

</html>
