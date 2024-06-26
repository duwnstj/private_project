<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
	integrity="sha512-10/jx2EXwxxWqCLX/hHth/vu2KY3jCF70dCQB8TSgNjbCVAC/8vai53GfMDrO2Emgwccf2pJqxct9ehpzG+MTw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Reddit+Mono:wght@200..900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/itinerary.css">
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<script>
// 장소 데이터
var destinations = [
    <c:forEach items="${destinations}" var="destination" varStatus="loop">
        {
            placeName: '${destination.placeName}',
            placeLatitude: ${destination.placeLatitude},
            placeLongitude: ${destination.placeLongitude}
        }<c:if test="${!loop.last}">,</c:if>
    </c:forEach>
];
var nationalCode= "${nationalCode}";
var planNo= "${planNo}"

</script>

</head>
<body>

<nav class="navlogo">
<div id="logoid">
    <img id="logo"
	src="../images/main_logo.png" alt="HTML Logo">
</div>
</nav>
    <div class="wrapper">
        
        <div class="container">
            <div class="tab_menu">
				<div class="tab_items">
					<div class="btn_item">
					    <a id="first_btn" class="first_btn">전체 일정</a>
					</div>
				</div>
				
				<div id= "tab_items2" class="tab_items2">
                    <button id="planDelete">일정 삭제</button>
                </div>
                
                <div id= "tab_items3" class="tab_items3">
                    <button id="planModify">일정 수정</button>
                </div>
			</div>

			
            <div class="tab_menu2">
                <div class= "totalPlan" id= "totalPlan">
                    <p>${departureDate} - ${arrivalDate}</p>
                </div>
                    <table id="placeTable">

                    <tr>
                        <th>장소</th>
                    </tr>
                        <c:forEach items="${destinations}" var="destination">
                            <tr id="place_tr" data-latitude="${destination.placeLatitude}" data-longitude="${destination.placeLongitude}">
                                <td>${destination.placeName}</td>
                                <td><button class="deleteBtn"><i class="fa fa-trash"></i></button></td>
                            </tr>
                        </c:forEach>

                    </table>
                <input type="text" id="placeSearch" placeholder="장소를 검색하여 일정에 추가해보세요." />
            </div>
        </div>
		    <div id="map"></div>
	</div>
	
	<%-- 외부 javascript 파일 연결 --%>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDQ4RyCuYJe7JbBpsNsi3_CUlpNlsKxOe8&libraries=places"></script>
    
	<script 
	    src="https://kit.fontawesome.com/9d75e77952.js"crossorigin="anonymous">
	</script>
	<script src="../js/itinerary.js"></script>
</body>
</html>