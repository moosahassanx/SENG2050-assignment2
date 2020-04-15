<%@ page import="java.io.IOException" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Home</title>
</head>
<body>
    <h1>Hello world of Java</h1>

    <p>The time on the server <%= new java.util.Date() %></p>

    <p>Lab03 example: <%= 3 + 3 %> and <%= "6" %></p>

    <p>Converting string to uppercase: <%= new String("Hello world").toUpperCase() %></p>

    <p>Is 75 less than 69: <%= 75 < 69 %></p>

    <p>6 times 4: <%= 6*4 %></p>

    <p>
        <%
            for(int i = 0; i < 5; i++){
                out.println("<br/>for loop " + i);
            }
        %>
    </p>

    <%!
        String makeItLower(String data){
            return data.toLowerCase();
        }
    %>

    <p>Lower case "BoOmEr": <%= makeItLower("BoOmEr")%></p>
    
    <%!
        String makeTable(){
            String line += "<table style=\"width: 100%\">";
            line += "<tbody>";
            line += "<tr class=\"pointless-cell\">";
            line += "<th></th>";
            for(int i = 1; i <= 8; i++){
                line += "<th>" + i + "</th>";
            }
            line += "</tr>";

            line += "";
            return line;
        }
    %>

    <%= makeTable() %>

    

    <table style="width: 100%">
        <tbody>
            <tr class="pointless-cell">
                <th></th>
                <th>1</th>
                <th>2</th>
                <th>3</th>
                <th>4</th>
                <th>5</th>
                <th>6</th>
                <th>7</th>
                <th>8</th>
            </tr>
            <tr>
                <td class="pointless-cell">A</td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="A1">A1</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="A2">A2</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="A3">A3</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="A4">A4</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="A5">A5</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="A6">A6</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="A7">A7</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="A8">A8</button></td>
            </tr>
            <tr>
                <td class="pointless-cell">B</td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="B1">B1</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="B2">B2</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="B3">B3</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="B4">B4</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="B5">B5</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="B6">B6</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="B7">B7</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="B8">B8</button></td>
            </tr>
            <tr>
                <td class="pointless-cell">C</td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="C1">C1</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="C2">C2</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="C3">C3</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="C4">C4</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="C5">C5</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="C6">C6</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="C7">C7</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="C8">C8</button></td>
            </tr>
            <tr>
                <td class="pointless-cell">D</td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="D1">D1</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="D2">D2</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="D3">D3</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="D4">D4</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="D5">D5</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="D6">D6</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="D7">D7</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="D8">D8</button></td>
            </tr>
            <tr>
                <td class="pointless-cell">E</td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="E1">E1</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="E2">E2</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="E3">E3</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="E4">E4</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="E5">E5</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="E6">E6</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="E7">E7</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="E8">E8</button></td>
            </tr>
            <tr>
                <td class="pointless-cell">F</td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="F1">F1</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="F2">F2</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="F3">F3</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="F4">F4</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="F5">F5</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="F6">F6</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="F7">F7</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="F8">F8</button></td>
            </tr>
            <tr>
                <td class="pointless-cell">G</td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="G1">G1</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="G2">G2</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="G3">G3</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="G4">G4</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="G5">G5</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="G6">G6</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="G7">G7</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="G8">G8</button></td>
            </tr>
            <tr>
                <td class="pointless-cell">H</td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="H1">H1</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="H2">H2</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="H3">H3</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="H4">H4</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="H5">H5</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="H6">H6</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="H7">H7</button></td>
                <td><button action="Booker" class="seat-button-available" name="seatButton" value="H8">H8</button></td>
            </tr>
        </tbody>
    </table>
</body>
</html>