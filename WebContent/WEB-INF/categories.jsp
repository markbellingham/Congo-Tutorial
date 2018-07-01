<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${category} | Congo</title>
</head>
<t:genericPage>
	<jsp:attribute name="header">
   	</jsp:attribute>
	<jsp:attribute name="footer">
   	</jsp:attribute>
	<jsp:body>
		<br />
		<br />
		<div class="indent">
			<form action="Categories" method="GET">
				<select name="category" onchange="this.form.submit()">
					<c:forEach items="${categories}" var="category">
						<c:choose>
							<c:when test="${category.name == param.category}">
								<option selected>${category.name}</option>
							</c:when>
							<c:otherwise>
								<option>${category.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</form>
		</div> <!-- ends indent -->
		<br/>
		<br/>
		<jsp:include page="album-list.jsp"/>
	</jsp:body>
</t:genericPage>
</html>