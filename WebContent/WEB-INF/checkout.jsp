<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Checkout | Congo</title>
</head>
<t:genericPage>
	<jsp:attribute name="header">
   	</jsp:attribute>
	<jsp:attribute name="footer">
   	</jsp:attribute>
	<jsp:body>
		<br/>
		<br/>
		<h2 class="indent">Confirm Your Order</h2>
		<a id="checkout" href="SubmitOrder">Confirm Order</a>
		<table class="musicList sortable">
			<tr>
				<th class="sorttable_nosort"></th><th>Artist</th><th>Album</th><th>Category</th><th># of Tracks</th><th>Price</th><th>Quantity</th><th>Total</th>
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
					<td class="center">${album.quantity}</td>
					<td class="right">£${album.totalPrice}</td>
				</tr>
			</c:forEach>
			<tfoot><tr><td id="grandTotal" colspan="7">Grand Total:</td><td class="right">£${grandTotal}</td></tr></tfoot>
		</table>
		<br/>
		<br/>
		<div id="customer-address">
			<h4>Delivery Address:</h4>
			${customer.fName} ${customer.lName}<br/>
			${customer.address1}<br/>
			${customer.address2}<br/>
			${customer.city}<br/>
			${customer.postcode}
		</div> <!-- ends customer-address -->
		<div id="customer-contact-details">
			<h4>Contact Number:</h4>
			${customer.phone}
			<h4>Contact Email:</h4>
			${customer.email}
		</div> <!-- ends customer-contact-details -->
	</jsp:body>
</t:genericPage>
</html>