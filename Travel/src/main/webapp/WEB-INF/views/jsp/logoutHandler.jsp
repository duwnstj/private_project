<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �α׾ƿ��� ���������� ���Ѽ� ��ť��Ƽ�� �����ϱ� ���� ������ jsp</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<form id="logoutForm" action="logout_ok" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<br> 	
					<input type="submit" value="Logout"> 
				</form>
				
<script type="text/javascript">
$(document).ready(function(){
	$("#logoutForm").submit(); // ��� ����
});
</script>
</body>
</html>