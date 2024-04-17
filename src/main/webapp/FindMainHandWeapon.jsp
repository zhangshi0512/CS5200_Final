<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Weapon</title>
</head>
<body>
	<%-- <form action="findmainhandweapon" method="get">
		<h1>Search for a Weapon by ID</h1>
		<p>
			<label for="weaponID">WeaponID</label>
			<input id="weaponID" name="weaponID" value="${fn:escapeXml(param.weaponID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form> --%>
	
	<h1>Matching Weapon</h1>
        <table border="1">
    		<tr>
    			<th>Name</th>
        		<th>Weapon Type</th>
        		<th>Required Level</th>
        		<th>Damage Done</th>
        		<th>Auto Attack</th>
        		<th>Attack Delay</th>
        		<th>Strength Bonus</th>
        		<th>Vitality Bonus</th>
        		<th>Determination Bonus</th>
        		<th>Direct Hit Rate Bonus</th>
    		</tr>
    		<tr>
    			<td>${weapon.getName()}</td>
        		<td>${weapon.getWeaponType()}</td>
        		<td>${weapon.getRequiredLevel()}</td>
        		<td>${weapon.getDamageDone()}</td>
        		<td>${weapon.getAutoAttack()}</td>
        		<td>${weapon.getAttackDelay()}</td>
        		<td>${weapon.getStrengthBonus()}</td>
        		<td>${weapon.getVitalityBonus()}</td>
        		<td>${weapon.getDeterminationBonus()}</td>
        		<td>${weapon.getDirectHitRateBonus()}</td>
    		</tr>
		</table>
		<p></p>
		
		<table border="1">
			<tr>
				<th>MaxStackSize</th>
				<th>VendorPrice</th>
				<th>ForSale</th>
				<th>ItemLevel</th>
			</tr>
			<tr>
        		<td>${item.getMaxStackSize()}</td>
        		<td>${item.getVendorPrice()}</td>
        		<td>${item.getForSale()}</td>
        		<td>${item.getItemLevel()}</td>
    		</tr>
		</table> 
		
		<h1>Jobs that can equip this weapon</h1>
	 <ul>
        <c:forEach items="${jobList}" var="job">
            <li>${job.getName()}</li>
        </c:forEach>
    </ul>
	<a href="characterList">Return to Home</a>
</body>
</html>
