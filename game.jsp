<%@page contentType="text/html" import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="java.io.IOException" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<jsp:useBean id="minesweeper"
    scope="session"
    class="beans.GameBean"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/javascript.js"></script>
    <title>Document</title>
</head>
<body>
    <div class="box">
        <form action="gameServlet" method="post">
            <h1>Minesweeper</h1>

            <c:set var = "difficulty" scope="session" value="${gameBeanObject.getDifficulty()}"/>
            
            <p id="p-text">Difficulty: <c:out value = "${minesweeper.difficulty}"/></p>

            
            <table class="minesweeper-table">
                <tr>
                    <c:forEach begin = "1" end = "${minesweeper.getRow()}">
                        <th class="chill-cell"><button class="sweep-button" name="cell" value="00"><img class="img-fill" src="" alt=""></button></th>
                    </c:forEach>
                </tr>
                <c:forEach begin = "1" end = "${minesweeper.getRow() - 1}">
                    <tr>
                        <c:forEach begin = "1" end = "${minesweeper.getRow()}">
                            <td class="chill-cell"><button class="sweep-button"><img src="img-fill" alt=""></button></th>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <form action="login.jsp" method="post">
            <input type="submit" name="" value="Save">
        </form>
        <form action="newgame.jsp" method="post">
            <input type="submit" name="" value="Restart">
        </form>
        
    </div>
</body>
</html>