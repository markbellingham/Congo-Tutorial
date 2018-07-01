<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Basket | Congo</title>
</head>
<t:genericPage>
	<jsp:attribute name="header">
   	</jsp:attribute>
	<jsp:attribute name="footer">
   	</jsp:attribute>
	<jsp:body>
		<br />
		<br />
		<h2 class="indent">Shopping Basket</h2>
		<c:if test="${empty error}">
			<a id="checkout" href="Checkout">Checkout</a>
			<table class="musicList sortable">
				<tr>
					<th class="sorttable_nosort"></th><th>Artist</th><th>Album</th><th>Category</th><th># of Tracks</th><th>Price</th><th>Quantity</th><th>Total</th><th class="sorttable_nosort"></th>
				</tr>
				<c:forEach items="${albums}" var="album">
					<tr>
						<td class="center"><a href="TrackLister?recordingId=${album.recordingId}">
							<img src="${pageContext.request.contextPath}/resources/images/covers/sm/${album.recordingId}.jpg"/></a></td>
						<td class="left"><a href="ArtistFinder?artist=${album.artistName}">${album.artistName}</a></td>
						<td class="left"><a href="TrackLister?recordingId=${album.recordingId}">${album.title}</a></td>
						<td class="left"><a href="Categories?category=${album.category}">${album.category}</a></td>
						<td class="center">${album.num_tracks}</td>
						<td class="right">£${album.price}</td>
						<td class="center">
							<form action="UpdateOrder?recordingId=${album.recordingId}" method="post">
								<input name="quantity" type="number" value="${album.quantity}" min="1" max="99" style="width: 3.5em;">
								<button class="btn btn-update" type="submit">Update</button>
							</form>
						</td>
						<td class="right">£${album.totalPrice}</td>
						<td class="center">
							<form action="DeleteFromOrder?recordingId=${album.recordingId}" method="post">
								<button class="btn btn-delete" type="submit">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				<tfoot><tr><td id="grandTotal" colspan="7">Grand Total:</td><td class="right">£${grandTotal}</td></tr></tfoot>
			</table>
		</c:if>
		<div class="center">
			<h2 class="error">${error}</h2>
		</div>
	</jsp:body>
</t:genericPage>
</html>