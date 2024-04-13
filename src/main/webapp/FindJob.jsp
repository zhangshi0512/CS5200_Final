<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Job</title>
</head>
<body>
	<%-- <form action="findjob" method="get">
		<h1>Search for a Job by ID</h1>
		<p>
			<label for="jobID">JobID</label>
			<input id="jobID" name="jobID" value="${fn:escapeXml(param.jobID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form> --%>
	
	<h1>Matching Job</h1>
        <table border="1">
    		<tr>
    			<th>Name</th>
        		<th>LevelCap</th>
    		</tr>
    		<tr>
    			<td>${job.getName()}</td>
        		<td>${job.getLevelCap()}</td>
    		</tr>
		</table>
	
	<h1>Equippable Weapon</h1>
	 <ul>
        <c:forEach items="${weaponTypeList}" var="weaponType">
            <li>${weaponType}</li>
        </c:forEach>
    </ul>
		
	
	<h1>Equippable Gear</h1>
	<ul>
        <c:forEach items="${gearTypeList}" var="gearType">
            <li>${gearType}</li>
        </c:forEach>
    </ul>
	
		

</body>
</html>
