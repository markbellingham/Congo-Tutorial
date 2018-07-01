<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<html>
<head>
	<title>Login | Congo</title>
</head>
	<t:genericPage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
		<br/>
		<br/>
		<div id="login-register-container">
			<!-- Tab Buttons -->
			<button class="tablink" onclick="change_tab('login', this, '#0b5000')" id="login_tab">Login</button>
			<button class="tablink" onclick="change_tab('register', this, '#777')">Register</button>
			
			<!-- Tab Content -->
			<div id="login" class="login-register tabcontent">
			<br/><br/>
				<form action="Login" method="POST">
			        <div align="center">
			        <div>${msg}</div>
			        <div class="error">${error}</div>
			            <table>
			                <tr>
			                    <td>Email Address</td>
			                    <td><input name="email" /></td>
			                </tr>
			                <tr>
			                    <td>Password</td>
			                    <td><input name="password" type="password" /></td>
			                </tr>
			                <tr>
			                    <td></td>
			                    <td><input type="submit" value="Submit" /></td>
			                </tr>
			            </table>
			        </div> <!-- ends centre align -->
			    </form>
		    </div> <!-- ends login -->
		    <div id="register" class="login-register tabcontent">
		    <br/><br/>
		    	<form action="Register" method="POST">
		    		<div align="center">
		    			<div>${msg}</div>
		    			<div class="error">${error}</div>
		    			<table>
		    				<tr>
		    					<td class="right">First Name</td><td class="left"><input name="fname" required="required" /></td>
		    				</tr>
		    				<tr>
		    					<td class="right">Surname</td><td class="left"><input name="lname" required="required" /></td>
		    				</tr>
		    				<tr>	    				
		    					<td class="right">Address</td><td class="left"><input name="address1" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Address</td><td class="left"><input name="address2" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">City</td><td class="left"><input name="city" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Postcode</td><td class="left"><input name="postcode" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Phone Number</td><td class="left"><input name="phone" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Email</td><td class="left"><input name="email" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Password</td><td class="left"><input name="password" type="password" required="required" /></td>
		    				</tr>
		    				<tr>
		    					<td></td><td><input type="submit" value="Submit"></td>
		    				</tr>
		    			</table>
		    		</div> <!-- ends centre align -->
		    	</form>
		    </div> <!-- ends register -->
		</div> <!-- ends login-register-container -->
		</jsp:body>
	</t:genericPage>
</html>

<script>
// Get the element with id="defaultOpen" and click on it
document.getElementById("login_tab").click();

function change_tab (type, elmnt, color) {
    // Hide all elements with class="tabcontent" by default */
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Remove the background color of all tablinks/buttons
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].style.backgroundColor = "";
    }

    // Show the specific tab content
    document.getElementById(type).style.display = "block";

    // Add the specific color to the button used to open the tab content
    elmnt.style.backgroundColor = color;
}
</script>

