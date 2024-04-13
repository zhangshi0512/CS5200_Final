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
    <p>ID: <%= character.getCharacterID() %></p>
    <p>First Name: <%= character.getFirstName() %></p>
    <p>Last Name: <%= character.getLastName() %></p>
    <p>Strength: <%= character.getStrength() %></p>
    <p>Vitality: <%= character.getVitality() %></p>
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
</body>
</html>
