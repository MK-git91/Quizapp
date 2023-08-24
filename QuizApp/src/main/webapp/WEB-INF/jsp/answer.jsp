<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Anser Page</title>
</head>
<body>
	<h1>問題です</h1>
	
	<c:if test="${!empty decision}">
  		<h3><c:out value="${decision}" /></h3>
	</c:if>
	
  	<h3><c:out value="${quiz.question}" /></h3>
  	
	<form action="QuizCon" method="post">
		<c:forEach var="items" items="${ansList}">
		<button type="submit" name="button" value=<c:out value="${items.ansNumber}" /> 
			<c:if test="${!empty decision}">
				<c:out value="disabled" />
			</c:if>>
			
			<c:out value="${items.answer}" />
		</button>
  		</c:forEach>
		<input type="submit" name = "submit" value="次へ">
	</form>
</body>
</html>