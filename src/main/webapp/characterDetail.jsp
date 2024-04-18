<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="pm3.model.Character" %>
<!DOCTYPE html>
<html>
<head>
    <title>Character Details</title>
</head>
<body>
    <h1>Character Details</h1>
    <%
        Character character = (Character) request.getAttribute("character");
        if (character != null) {
    %>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
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
        <tr>
            <th>${character.getCharacterID()}</th>
            <th>${character.getFirstName()}</th>
            <th>${character.getLastName()}</th>
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
    </table>
    <%--
    <p>ID: <%= character.getCharacterID() %></p>
    <p>First Name: <%= character.getFirstName() %></p>
    <p>Last Name: <%= character.getLastName() %></p>
    <p>Strength: <%= character.getStrength() %></p>
    <p>Vitality: <%= character.getVitality() %></p>
    --%>
    <!-- More details can be added similarly -->

    <h2>Edit Character</h2>
    <form action="updateCharacter" method="post">
        New First Name: <input type="text" name="firstName" value="<%= character.getFirstName() %>"><br>
        New Last Name: <input type="text" name="lastName" value="<%= character.getLastName() %>"><br>
        <input type="hidden" name="id" value="<%= character.getCharacterID() %>">
        <input type="submit" value="Update">
    </form>
    <%
        } else {
    %>
    <p>Character not found.</p>
    <%
        }
    %>
    <a href="characterList">Return to Home</a>
</body>
</html>
