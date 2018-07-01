<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders for ${customer.fName} ${customer.lName} | Congo</title>
</head>
<t:genericPage>
	<jsp:attribute name="header">
   	</jsp:attribute>
	<jsp:attribute name="footer">
   	</jsp:attribute>
	<jsp:body>
		<br />
		<br />
		<h2 class="indent">Orders for ${customer.fName} ${customer.lName}</h2>
		<c:forEach items="${orders}" var="order">
		
			<div class="indent">Order ID: ${order.orderId} &nbsp - &nbsp Order Date: ${order.orderDate} &nbsp - &nbsp Order Total: £${order.orderTotal}</div>			
			<table class="musicList sortable">
			<tr><th></th><th>Artist</th><th>Title</th><th>Category</th><th># of Tracks</th><th>Price</th><th>Quantity</th><th>Total Price</th></tr>
			
			<c:forEach items="${order_details}" var="album">
				<c:if test="${album.orderId == order.orderId}">
					<tr>
						<td class="center"><a href="TrackLister?recordingId=${album.recordingId}">
							<img src="${pageContext.request.contextPath}/resources/images/covers/sm/${album.recordingId}.jpg"/></a></td>
						<td class="left"><a href="ArtistFinder?artist=${album.artistName}">${album.artistName}</a></td>
						<td class="left"><a href="TrackLister?recordingId=${album.recordingId}">${album.title}</a></td>
						<td class="left"><a href="Categories?category=${album.category}">${album.category}</a></td>
						<td class="center">${album.numTracks}</td>
						<td class="right">£${album.price}</td>
						<td class="center">${album.orderQuantity}</td>
						<td class="right">£${album.itemTotal}</td>
					</tr>
				</c:if>
			</c:forEach>
			</table>
			<br /><br />
		</c:forEach>
	</jsp:body>
</t:genericPage>
</html>