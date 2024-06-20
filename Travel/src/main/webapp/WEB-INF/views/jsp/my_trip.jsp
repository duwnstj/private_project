<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../include/header.jsp" />

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Trip</title>
<link rel="stylesheet" type="text/css" href="../css/my_trip.css">
<link rel="stylesheet" type="text/css" href="../css/planPost.css">
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
    integrity="sha512-10/jx2EXwxxWqCLX/hHth/vu2KY3jCF70dCQB8TSgNjbCVAC/8vai53GfMDrO2Emgwccf2pJqxct9ehpzG+MTw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

	<%-- 버튼 박스 --%>
	<div class="button_box">
		<a id="btn_1">New Schedule</a>
		<a id="btn_2">My Trip</a>
		<a id="btn_3">Wish List</a>
		<a id="btn_4">Share square</a>
	</div>
	<button id="btn_shcedule" type="button">
		<i class="fa-solid fa-calendar-plus fa-2x"></i>
	</button>
	<p id="my_trip_p">My Trip</p>
	<%-- 게시글 영역 --%>
    <div class="posts" id="postsContainer">
        <c:forEach var="plan" items="${planList}" varStatus="loop">
    <div class="post">
        <h2>${loop.index + 1}번째 일정</h2> <!-- loop.index는 0부터 시작하므로 +1을 해서 1부터 시작하는 일정 순서로 표시 -->
        여행 도시
        <ul id="cityList-${plan.planNo}" class="city-list">
            <c:forEach var="city" items="${plan.cities}">
                <li data-city-code="${city.cityCode}" data-city-image="${city.cityImage}">
                    ${city.cityName}
                </li>
            </c:forEach>
        </ul>
        <div class="dates">
            <p><fmt:formatDate value="${plan.departureDate}" pattern="yyyy-MM-dd" />~<fmt:formatDate value="${plan.arrivalDate}" pattern="yyyy-MM-dd" /></p>
        </div>
    </div>
</c:forEach>
    </div>

<jsp:include page="../include/footer.jsp"/>

<%-- 외부 javascript 파일 연결 --%>
<script src="../js/mytrip.js"></script>
<script src="https://kit.fontawesome.com/9d75e77952.js"crossorigin="anonymous"></script>