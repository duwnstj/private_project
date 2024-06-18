<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="../css/postMake.css">
<jsp:include page="../include/header.jsp" />

<h1 class="header-title">게시글 수정하기</h1>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta name="_csrf" content="${_csrf.token}"> 


<div class="container">
	<div class="input-container">
	<form method="Post" action="/post_edit_ok" onsubmit="return write_check();"
	enctype="multipart/form-data"> 
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<%--text와 file등 혼합된 경우 서버에 원할하게 보내기 위한 기능 --%>
	
		<input type="hidden" name="mateno" value="${postId}">
		<input type="text" name="mate_title" id="mate_title" 
		value="${mate_title}" placeholder="제목을 입력하세요...">
		
		<textarea name="mate_cont" id="mate_cont" 
		 placeholder="내용을 입력하세요...">${mate_cont}</textarea>
		 
		 <label for="uploadFile">새 이미지 추가:</label>
		 <input type="file" multiple name="uploadFile" id="uploadFile" accept="image/*">
		 
		 <label>기존 이미지 미리보기 및 삭제</label>
		 <div class="existing-images" id="existing-images">
		 <c:forEach var="img" items="${images}">
		 <div class="image-item" data-upload-file="${img.uploadFile}">
		  <img src="${pageContext.request.contextPath}/upload${img.uploadFile}" alt="이미지" width="100">
		  <input type="checkbox" id="delete-${img.uploadFile}" name="deleteImages" value="${img.uploadFile}">
		  </div>
		  </c:forEach>
		 </div>
		
		
		<input type="text" name="mt_hashtag" id="mt_hashtag" value="${mt_hashtag}" placeholder="태그를 입력하세요... (쉼표로 구분)">
		
     
    
		
			<button type="submit" id="post-button">게시물 수정</button>
			
		</form>
	</div>
</div>

<script src="../js/postMake.js"></script>

