<%@page contentType="text/html" import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="java.io.IOException" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>



<jsp:useBean id="user"
    scope="session"
    class="beans.GameBean"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Document</title>
</head>
<body>
    <div class="box">
        <form action="#" method="post">
            <h1>Minesweeper</h1>
            
            <p id="p-text">Difficulty: <jsp:getProperty name="user" property="difficulty" /></p>

            <c:set var = "difficulty" scope="session" value="${gameBeanObject.getDifficulty()}"/>

            <c:set var = "rowNum" scope="session" value="${gameBeanObject.getRow()}"/>
            <c:set var = "colNum" scope="session" value="${gameBeanObject.getColumn()}"/>

            <c:forEach items="${rowNum}" var="8">
                <p>test</p>
            </c:forEach>
            
            <table class="minesweeper-table">
                <tr class="chill-cell">
                    <c:forEach var = "i" begin = "1" end = "10">
                        <th><button class="sweep-button"><img src="" alt=""></button></th>
                    </c:forEach>
                </tr>
                <c:forEach var = "i" begin = "1" end = "9">
                    <tr class="chill-cell">
                        <c:forEach var = "i" begin = "1" end = "10">
                            <td><button class="sweep-button"><img src="" alt=""></button></th>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <form action="login.jsp" method="post">
            <input type="submit" name="" value="Save">
            <input type="submit" name="" value="Restart">
        </form>
        
    </div>
</body>
</html>