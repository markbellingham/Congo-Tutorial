<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="${customer.admin == 1 ? '' : 'sortable' } musicList">
	<c:if test="${customer.admin == 1}">
		<tr>
			<td style="border: 0;"><form id="UpdateDB" class="tr" action="UpdateDB" method="POST"><input type="hidden" name="recordingId" value="${empty select.recordingId ? '' : select.recordingId}"/></form></td>
			<td style="border: 0;"><input form="UpdateDB" class="admin-edit" name="artist_name" type="text" placeholder="Artist Name" value="${empty select.artistName ? '' : select.artistName}"/></td>
			<td style="border: 0;"><input form="UpdateDB" class="admin-edit" name="title" type="text" placeholder="Album Title" value="${empty select.title ? '' : select.title}"/></td>
			<td style="border: 0;"><select form="UpdateDB" name="category"><c:forEach items="${categories}" var="category"><option ${category.name == select.category ? 'selected' : ''}>${category.name}</option></c:forEach></select></td>
			<td style="border: 0;"><input form="UpdateDB" class="small-td" name="num_tracks" type="number" min="1" max="99" placeholder="Tracks" value="${empty select.num_tracks ? '' : select.num_tracks}"/></td>
			<td style="border: 0;"><input form="UpdateDB" class="small-td" name="price" type="text" placeholder="Price" value="${empty select.price ? '' : select.price}"/></td>
			<td style="border: 0;"><input form="UpdateDB" class="small-td" name="stockCount" type="number" min="0" max="999" placeholder="Stock" value="${empty select.stockCount ? '' : select.stockCount}"/></td>
			<td style="border: 0;">
				<button form="UpdateDB" class="btn btn-insert">Add/Update</button>
				<button form="UpdateDB" value="clear" class="btn btn-clear" onClick="this.form.reset()">Clear</button>
			</td>
		</tr>
	</c:if>
	<tr><th class="sorttable_nosort"></th><th>Artist</th><th>Album</th><th>Category</th><th>Tracks</th><th>Price</th><th class="sorttable_nosort">${customer.admin == 1 ? 'Stock' : '' }</th></tr>
	<c:forEach items="${albums}" var="album">
		<tr>
			<td class="center"><a href="TrackLister?recordingId=${album.recordingId}">
				<img src="${pageContext.request.contextPath}/resources/images/covers/sm/${album.recordingId}.jpg"/></a></td>
			<td class="left"><a href="ArtistFinder?artist=${album.artistName}">${album.artistName}</a></td>
			<td class="left"><a href="TrackLister?recordingId=${album.recordingId}">${album.title}</a></td>
			<td class="left"><a href="Categories?category=${album.category}">${album.category}</a></td>
			<td class="center small-td">${album.num_tracks}</td>
			<td class="right small-td">£${album.price}</td>
			<td class="center small-td">
				<form action="AddToOrder?recordingId=${album.recordingId}" method="POST">
					<c:choose>
						<c:when test="${customer.admin == 1}">
							${album.stockCount}
						</c:when>
				   		<c:when test="${(customer.loggedIn != null) && (customer.loggedIn == true) && (album.stockCount > '0')}">
					    	<button class="btn btn-insert">Add</button>
						</c:when>
						<c:otherwise>
					    	<button disabled>Add</button>
						</c:otherwise>
					</c:choose>
				</form>
			</td>
			<c:if test="${customer.admin == 1}">
				<td style="border: 0;">
					<a class="btn btn-delete" href="DeleteItem?recordingId=${album.recordingId}">
						Delete
					</a>
					<a class="btn btn-select" href="SelectItem?recordingId=${album.recordingId}">
						Select
					</a>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>