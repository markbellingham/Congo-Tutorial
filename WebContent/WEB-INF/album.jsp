<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${album.title} by ${album.artistName} | Congo</title>
</head>
<t:genericPage>
	<jsp:attribute name="header">
   	</jsp:attribute>
	<jsp:attribute name="footer">
   	</jsp:attribute>
	<jsp:body>
		<p class="center">
			<a href="Albums">See a list of all our albums!</a>
		</p>
		<br />
		<br />
		<div id="album-container">
			<h2>${album.title} by ${album.artistName}</h2>
			<h2 class="error">${error}</h2>
			<div id="album-cover">
			   <img src="${pageContext.request.contextPath}/resources/images/covers/${tracks[0].recordingId}.jpg"/>
			</div> <!-- ends album-cover -->
			<div id="album-tracks">
				<table class="musicList sortable" id="trackLister">
					<tr>
						<th>Track #</th>
						<th>Title</th>
						<th>Duration</th>
					</tr>
					<c:forEach items="${tracks}" var="track">
						<tr>
						   <td>${track.trackNumber}</td>
						   <td>${track.title}</td>
						   <td class="right">${track.strDuration}</td>
						</tr>
					</c:forEach>
					<c:if test="${customer.admin == 1}">
						<tr><td class="right" colspan="3" style="border: 0;"><a class="btn btn-update" href="TrackLister?recordingId=${album.recordingId}&action=modify">Modify</a></td></tr>
					</c:if>
				</table>
			</div> <!-- ends album-tracks -->
		</div> <!-- ends album-container -->
	</jsp:body>
</t:genericPage>
</html>