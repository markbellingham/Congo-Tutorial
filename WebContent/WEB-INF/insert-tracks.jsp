<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert Tracks for ${album.title} by ${album.artistName} | Congo</title>
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
			<h2>Insert tracks for ${album.title} by ${album.artistName}</h2>
			<div id="album-cover">
			   <img src="${pageContext.request.contextPath}/resources/images/covers/${album.recordingId}.jpg"/>
			</div> <!-- ends album-cover -->
			<div id="album-tracks">
				<form action="InsertTracks" method="POST">
					<input type="hidden" name="recordingId" value="${album.recordingId}">
					<table class="musicList sortable" id="trackLister">
						<tr>
							<th>Track #</th>
							<th>Title</th>
							<th>Duration</th>
						</tr>
						<c:choose>
							<c:when test="${not empty tracks}">
								<c:forEach items="${tracks}" var="track">
									<tr>
									   <td>${track.trackNumber}</td>
									   <td><input name="title" type="text" placeholder="Track Title" value="${track.title}"></td>
									   <td><input name="duration" type="number" min="1" max="1500" style="width: 3em" value="${track.duration}"> seconds</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>						
								<c:forEach begin="1" end="${album.num_tracks}" varStatus="loop">
									<tr>
									   <td>${loop.index}</td>
									   <td><input name="title" type="text" placeholder="Track Title"></td>
									   <td><input name="duration" type="number" min="1" max="999" style="width: 3em"> seconds</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<tr><td class="right" colspan="3" style="border: 0;"><button class="btn btn-update" type="submit">Submit</button></td></tr>
					</table>
				</form>
			</div> <!-- ends album-tracks -->
		</div> <!-- ends album-container -->
	</jsp:body>
</t:genericPage>
</html>