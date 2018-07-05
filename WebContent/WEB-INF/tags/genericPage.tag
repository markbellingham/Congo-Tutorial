<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/congo.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
    
  <!-- Google Analytics -->
  <script async src='https://www.google-analytics.com/analytics.js'></script>
  <script>
	  window.ga=window.ga||function(){(ga.q=ga.q||[]).push(arguments)};ga.l=+new Date;
	  ga('create', 'UA-115626803-1', 'auto');
	  ga('send', 'pageview');
  </script>
  <!-- End Google Analytics -->
</head>
<div id="wrapper">
		<c:choose>
			<c:when test="${customer.loggedIn == true}">
				Welcome ${customer.fName} ${customer.lName}.
			</c:when>
			<c:otherwise>
				Please log in to add items to the basket.
			</c:otherwise>
		</c:choose>
	<div id="pageheader">
	   	<h1 id="name">Congo's Music Store</h1>
	   	<nav>
			<a href="/Congo">Home</a> | 
			<a href="Categories">Categories</a> | 
			<a href="PricePicker">Price Picker</a> | 
			<a href="ArtistFinder">Artist Finder</a> | 
			<c:if test="${(customer.loggedIn != null) && (customer.loggedIn == true)}">
				<a href="Basket">Show Order</a> | 
				<a href="ShowAllMyOrders">Show All My Orders</a> | 
			</c:if>
			<c:choose>
				<c:when test="${(customer.loggedIn != null) && (customer.loggedIn == true)}">
					<a href="Logout">Log out</a>
				</c:when>
				<c:otherwise>
					<a href="Login">Log in/Register</a>
				</c:otherwise>
			</c:choose>
		</nav>
	   	<jsp:invoke fragment="header"/>
	</div> <!-- ends pageheader -->
	<div id="body">
	   	<jsp:doBody/>
	</div> <!-- ends body -->
	<div id="pagefooter">
	   	<jsp:invoke fragment="footer"/>
	</div> <!-- ends pagefooter -->
</div> <!-- ends wrapper -->