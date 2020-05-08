<%@page contentType="text/html" import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="java.io.IOException" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<jsp:useBean id="user"
    scope="session"
    class="beans.UserBean"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Document</title>
</head>
<body>
    <!-- takes username -->
    
    <form class="box" action="newGameServlet" method="post">
        <h1>Select Level</h1>

        <p id="p-text">User: <c:out value = "${user.getUsername()}"/></p>
        <input type="hidden" name="username" value="<c:out value = "${user.getUsername()}"/>">
        <input type="submit" name="gameDifficulty" value="Beginner">
        <input type="submit" name="gameDifficulty" value="Intermediate">
        <input type="submit" name="gameDifficulty" value="Advanced">
        <hr>
        <input type="submit" name="gameDifficulty" value="Continue">
    </form>
</body>
</html>