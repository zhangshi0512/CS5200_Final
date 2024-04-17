<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Player</title>
</head>
<body>
	<%-- <form action="findplayers" method="get">
		<h1>Search for a Player by Email</h1>
		<p>
			<label for="email">Email</label>
			<input id="email" name="email" type="text" value="${fn:escapeXml(param.email)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form> --%>
	
	<h1>Matching Player</h1>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>EmailAddress</th>
            </tr>
            <tr>
        		<td>${player.getName()}</td>
        		<td>${player.getEmailAddress()}</td>
    		</tr>
       </table>
    <a href="characterList">Return to Home</a>
</body>
</html>
