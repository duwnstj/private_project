<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/admin_homepage.css">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta name="_csrf" content="${_csrf.token}">
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>

<body>
    <h1>관리자 페이지</h1>
    
    <div id="user-list-container">
        <ul id="user-list">
            <c:forEach var="user" items="${users}">
                <li class="user-item">
                <a href="/admin/chat?user=${user}">${user}</a></li>       
            </c:forEach>
        </ul>
    </div>
    
    <div id="pagination">
        <button id="prev">이전</button>
        <span id="page-info"></span>
        <button id="next">다음</button>
    </div>
    
<script src="/js/admin_homepage.js"></script>    
</body>
</html>
