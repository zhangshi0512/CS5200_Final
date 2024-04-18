<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<head>
    <title>Character List</title>
</head>
<body>
    <h1>Search Characters</h1>
	<form action="characterList" method="get">
	    First Name: <input type="text" name="searchName" />
	    Job: 
	    <select name="filterJob">
	        <option value="">Select a Job</option>
	        <c:forEach var="job" items="${allJobs}">
	            <option value="${job.jobID}">${job.name}</option>
	        </c:forEach>
	    </select>
	    <input type="submit" value="Search" />
	</form>

    <h2>Character Results</h2>
    <table>
    	<tr>
    		<th>Player</th>
    		<th>First Name</th>
    		<th>Last Name</th>
    		<th>Max HP</th>
    		<th>Max MP</th>
    		<th>Current Job</th>
    		<th>Main-hand Weapon</th>
    		<th>Character Details</th>
    		
    		
    		
    	</tr>
       <c:forEach var="character" items="${characters}">
       
           <tr>
           		<td><a href="findplayers?email=${character.player.emailAddress}">${character.player.name}</a></td>
           		<%-- <td><a href="characterDetail?id=${character.characterID}">${character.firstName}</a></td> --%>
           		<td>${character.firstName}</td>
           		<td>${character.lastName}</td>
           		<td>${character.maxHP}</td>
           		<td>${character.maxMP}</td>
           		<td><a href="findjob?jobID=${character.currentJob.jobID}">${character.currentJob.name}</a></td>
           		<td><a href="findmainhandweapon?weaponID=${character.mainHandWeapon.itemID}">${character.mainHandWeapon.name}</a></td>
           		<td><a href="characterDetail?id=${character.characterID}">details</a></td>
           		
           </tr>
       </c:forEach>
   
    </table>
</body>
</html>
