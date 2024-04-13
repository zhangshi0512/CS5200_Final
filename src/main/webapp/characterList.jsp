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
        Job: <input type="text" name="filterJob" />
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
    		<th>Strength</th>
    		<th>Dexterity</th>
    		<th>Vitality</th>
    		<th>Intelligence</th>
    		<th>Mind</th>
    		<th>Critical Hit</th>
    		<th>Determination</th>
    		<th>Direct Hit Rate</th>
    		<th>Defense</th>
    		<th>Magic Defense</th>
    		<th>Attack Power</th>
    		<th>Skill Speed</th>
    		<th>Attack Magic Potency</th>
    		<th>Healing Magic Potency</th>
    		<th>Spell Speed</th>
    		<th>Average Item Level</th>
    		<th>Tenacity</th>
    		<th>Piety</th>
    		
    		
    		
    	</tr>
       <c:forEach var="character" items="${characters}">
       
           <tr>
           		<td>${character.player.name}</td>
           		<td>${character.firstName}</td>
           		<td>${character.lastName}</td>
           		<td>${character.maxHP}</td>
           		<td>${character.maxMP}</td>
           		<td>${character.currentJob.name}</td>
           		<td>${character.mainHandWeapon.name}</td>
           		<td>${character.strength}</td>
           		<td>${character.dexterity}</td>
           		<td>${character.vitality}</td>
           		<td>${character.intelligence}</td>
           		<td>${character.mind}</td>
           		<td>${character.criticalHit}</td>
           		<td>${character.determination}</td>
           		<td>${character.directHitRate}</td>
           		<td>${character.defense}</td>
           		<td>${character.magicDefense}</td>
           		<td>${character.attackPower}</td>
           		<td>${character.skillSpeed}</td>
           		<td>${character.attackMagicPotency}</td>
           		<td>${character.healingMagicPotency}</td>
           		<td>${character.spellSpeed}</td>
           		<td>${character.averageItemLevel}</td>
           		<td>${character.tenacity}</td>
           		<td>${character.piety}</td>
           		
           </tr>
       </c:forEach>
   
    </table>
</body>
</html>
