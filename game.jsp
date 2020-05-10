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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>

        $(document).ready(function(){

            $("button").mouseup(function(event){
                event.preventDefault();

                if(event.which == 3){
                    var position = $(this).val();
                    window.location.href="http://localhost:8080/c3331532_assignment2/gameServlet?button=" + position;
                }
            });
        });
        
    </script>
    <title>Document</title>
</head>
<body>
    <c:set var = "cellArray" scope = "session" value = "${minesweeper.getArray()}"/>

    <div class="box">

        <h1>Minesweeper</h1>
        
        <c:set var = "difficulty" scope="session" value="${minesweeper.getDifficulty()}"/>
            
            <c:choose>
                <c:when test="${minesweeper.getWin()}">
                    <p id="p-text">YOU WIN</p>
                    <p id="p-text"><c:out value = "${minesweeper.getTimeElapsed()}"/> seconds</p>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${minesweeper.getLose()}">
                            <p id="p-text">GAME OVER</p>
                            <p id="p-text"><c:out value = "${minesweeper.getTimeElapsed()}"/> seconds</p>
                        </c:when>
                        <c:otherwise>
                            <form action="gameServlet" method="post">
                            <p id="p-text"><c:out value = "${minesweeper.difficulty}"/></p>
                            <table class="minesweeper-table">
                                <c:forEach begin = "0" end = "${minesweeper.getRow() - 1}" varStatus = "x">
                                    <tr>
                                        <c:forEach begin = "0" end = "${minesweeper.getRow() - 1}" varStatus = "y">

                                            <c:choose>
                                                <c:when test="${cellArray[x.index][y.index].isVisited()}">
                                                    
                                                    <c:choose>
                                                        <c:when test="${cellArray[x.index][y.index].isMine()}">
                                                            <td class="chill-cell"><button class="sweep-button" value="${x.index}::${y.index}" name="cellLabel">M</button></th>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="chill-cell"><button class="not-sweep-button" value="${x.index}::${y.index}" name="cellLabel"><c:out value = "${cellArray[x.index][y.index].surroundingMines()}"/></button></th>
                                                        </c:otherwise>
                                                    </c:choose>
                                                
                                                </c:when>
                                                <c:otherwise>
                                                
                                                    <c:choose>
                                                        <c:when test="${cellArray[x.index][y.index].isFlagged()}">
                                                            <td class="chill-cell-flagged"><button class="sweep-button-flagged" value="${x.index}::${y.index}" name="cellLabel"></button></th>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="chill-cell"><button class="sweep-button" value="${x.index}::${y.index}" name="cellLabel" id="rightClickBtn" oncontextmenu="testRightClick(event)"></button></th>
                                                        </c:otherwise>
                                                    </c:choose>
                                                
                                                </c:otherwise>
                                            </c:choose>                       
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </table>
                        </form>
                        <form action="saveServlet" method="post">
                            <input type="submit" name="" value="Save">
                            <input type="hidden" name="difficulty" value="${minesweeper.getDifficulty()}">
                            <input type="hidden" name="username" value="${minesweeper.getUsername()}">
                        </form>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>


            
            
        <form action="newgame.jsp" method="post">
            <input type="submit" name="" value="Restart">
        </form>
        
    </div>
</body>
</html>