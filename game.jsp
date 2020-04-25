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
    <c:set var = "cellArray" scope = "session" value = "${minesweeper.getArray()}"/>

    <div class="box">
        <form action="gameServlet" method="post">
            <h1>Minesweeper</h1>

            <c:set var = "difficulty" scope="session" value="${minesweeper.getDifficulty()}"/>
            
            <p id="p-text">Difficulty: <c:out value = "${minesweeper.difficulty}"/></p>

            <table class="minesweeper-table">
                <c:forEach begin = "0" end = "${minesweeper.getRow() - 1}" varStatus = "x">
                    <tr>
                        <c:forEach begin = "0" end = "${minesweeper.getRow() - 1}" varStatus = "y">
                            <c:choose>
                                <c:when test="${cellArray[x.index][y.index].isVisited()}">
                                    <td class="chill-cell"><button class="sweep-button" value="${x.index} ${y.index}" name="cellLabel"><c:out value = "${cellArray[x.index][y.index].surroundingMines()}"/></button></th>
                                </c:when>
                                <c:when test="${cellArray[x.index][y.index].isMine()}">
                                        <td class="chill-cell"><button class="sweep-button" value="${x.index} ${y.index}" name="cellLabel">M</button></th>
                                    </c:when>
                                <c:otherwise>
                                    <td class="chill-cell"><button class="sweep-button" value="${x.index} ${y.index}" name="cellLabel">V</button></th>
                                </c:otherwise>  
                            </c:choose>                         
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