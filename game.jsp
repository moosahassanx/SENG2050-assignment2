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
    <script>
        function testRightClick(event) {
            event.preventDefault();
            
            if (event.which == 3) {
                alert("right clicked!");
            }
        }
    </script>
    <title>Document</title>
</head>
<body>
    <c:set var = "cellArray" scope = "session" value = "${minesweeper.getArray()}"/>

    <div class="box">

        <h1>Minesweeper</h1>

        <!-- testing right click -->
        <button oncontextmenu="testRightClick(event)">right click alert</button>
        
        <c:set var = "difficulty" scope="session" value="${minesweeper.getDifficulty()}"/>
            
            <c:choose>
                <c:when test="${minesweeper.getWin()}">
                    <p id="p-text">GAME OVER</p>
                </c:when>
                <c:otherwise>
                    <form action="gameServlet" method="post">
                    <p id="p-text">Difficulty: <c:out value = "${minesweeper.difficulty}"/></p>
                    <table class="minesweeper-table">
                        <c:forEach begin = "0" end = "${minesweeper.getRow() - 1}" varStatus = "x">
                            <tr>
                                <c:forEach begin = "0" end = "${minesweeper.getRow() - 1}" varStatus = "y">
        
                                    <c:choose>
                                        <c:when test="${cellArray[x.index][y.index].isVisited()}">
                                            
                                            <c:choose>
                                                <c:when test="${cellArray[x.index][y.index].isMine()}">
                                                    <td class="chill-cell"><button class="sweep-button" value="${x.index} ${y.index}" name="cellLabel">M</button></th>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="chill-cell"><button class="not-sweep-button" value="${x.index} ${y.index}" name="cellLabel"><c:out value = "${cellArray[x.index][y.index].surroundingMines()}"/></button></th>
                                                </c:otherwise>
                                            </c:choose>
        
                                        </c:when>
                                        <c:otherwise>

                                            <c:choose>
                                                <c:when test="${cellArray[x.index][y.index].isFlagged()}">
                                                    <td class="chill-cell"><button class="sweep-button" value="${x.index} ${y.index}" name="cellLabel">F</button></th>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="chill-cell"><button class="sweep-button" value="${x.index} ${y.index}" name="cellLabel"></button></th>
                                                </c:otherwise>
                                            </c:choose>
                                            
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
                </c:otherwise>
            </c:choose>
            
        <form action="newgame.jsp" method="post">
            <input type="submit" name="" value="Restart">
        </form>
        
    </div>
</body>
</html>