<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Character List</title>
</head>
<body>
    <h1>Search Characters</h1>
    <form action="CharacterList" method="get">
        First Name: <input type="text" name="searchName" />
        Job: <input type="text" name="filterJob" />
        <input type="submit" value="Search" />
    </form>

    <h2>Character Results</h2>
    <ul>
        <c:forEach var="character" items="${characters}">
            <li>
                <a href="characterDetail.jsp?characterId=${character.characterId}">
                    ${character.firstName} ${character.lastName} - ${character.job}
                </a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
