<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOP Page</title>
</head>
<body>
	<h1>ログイン画面</h1>

	<c:if test="${!empty eMessage}">
		<c:forEach var="items" items="${eMessage}">
  			<h3><c:out value="${items}" /></h3>
  		</c:forEach>
	</c:if>

	<form action="LoginCon" method="post">
		<div>
			<label for="username">名前:</label> <input type="text" id="username"
				name="username" />
		</div>

		<div>
			<label for="pass">パスワード:</label> <input type="password" id="pass"
				name="password" />
		</div>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>