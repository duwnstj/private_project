<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/css/chat_css.css">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta name="_csrf" content="${_csrf.token}">
<meta charset="UTF-8">
	<title>Chating</title>	
</head>


<body>
<jsp:include page="../include/header.jsp" />
<div class="wrap">
	<div class="button_box">
		<a href="wallet" class="btn_1">지갑</a> 
		<a href="/Alert" class="btn_2">정보 수정</a>
		<a href="/chat" class="btn_3">고객 상담</a>
	</div>
</div>


	<div id="container" class="container">
		<h1>관리자와의 채팅</h1>
		<input type="hidden" id="sessionId" value="">
		<input type="hidden" id="chatNumber" value="${chatNumber}">
		<input type="hidden" id="member_id" value="${member_id}">
		<div id="chating" class="chating">
			<c:forEach var="message" items="${allMessage}">
            <c:choose>
                <c:when test="${fn:startsWith(message.messageText, '관리자')}">
                    <p class='others'>${message.messageText} 
                    <br><span class="time">${fn:substring(message.message_time, 0, 16)}</span></p><br>
                </c:when>
                <c:otherwise>
                    <p class='me'>${message.messageText} 
                    <br><span class="time">${fn:substring(message.message_time, 0, 16)}</span></p><br>
                </c:otherwise>
            </c:choose>
        	</c:forEach>
		</div>
		
		
		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th>${member_id} 님</th>
					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
					<th><button onclick="send()" id="sendBtn">보내기</button></th>
				</tr>
			</table>
		</div>
	</div>
	
	
<jsp:include page="../include/footer.jsp" />	
<script src="/js/chat.js"></script>	
</body>
</html>