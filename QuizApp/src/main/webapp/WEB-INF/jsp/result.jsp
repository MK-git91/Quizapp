<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result Page</title>
</head>
<body>
	<h1>結果</h1>
	
	<table>
		<thead>
			<tr>
				<td>回数</td>
				<td>1問目</td>
				<td>2問目</td>
				<td>3問目</td>
				<td>4問目</td>
				<td>5問目</td>
			</tr>
		</thead>
		
		<tbody>
				<tr>
					<c:forEach var="items" items="${resList}" varStatus="i">
						<c:if test="${i.index % 5 == 0 && loopStatus.index != 0}">
      						</tr><tr>
      						<td><c:out value="${items.resultNumber}" />回目</td>
    					</c:if>
						<td><c:out value="${items.correct}" /></td>
  					</c:forEach>
				</tr>
		</tbody>
	
	
	</table>
	
	
</body>
</html>