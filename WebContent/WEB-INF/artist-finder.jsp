<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${artist} | Congo</title>
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
			<form action="ArtistFinder" method="GET">
				<input type="text" name="artist" placeholder="${artist}" required/>
				<input type="submit" value="Search"/>
			</form>
		</div> <!-- ends indent -->
		<br/>
		<br/>
		<jsp:include page="album-list.jsp"/>
	</jsp:body>
</t:genericPage>
</html>