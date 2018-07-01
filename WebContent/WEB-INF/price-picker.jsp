<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Price Picker | Congo</title>
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
			<c:set var="string1" value="${param.price}"/>
			<c:set var="string2" value="${fn:replace(string1, '%2C', ',')}"/>
			<form action="PricePicker" method="GET">
				<select name="price" onchange="this.form.submit()">
					<option value="0,10" ${string2 == '0,10' ? "selected" : ""}>Under £10</option>
					<option value="10,12" ${string2 == '10,12' ? "selected" : ""}>£10 &lt; £12
					<option value="12,14" ${string2 == '12,14' ? "selected" : ""}>£12 &lt; £14</option>
					<option value="14,100" ${string2 == '14,100' ? "selected" : ""}>Over £14</option>
				</select>
			</form>
		</div> <!-- ends indent -->
		<br/>
		<br/>
		<jsp:include page="album-list.jsp"/>
	</jsp:body>
</t:genericPage>
</html>