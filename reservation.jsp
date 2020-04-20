<jsp:useBean id="restaurant" class="pkg.Restaurant" scope="application" />

<html>
    <head>
        <title>My Restaurant</title>
    </head>
    <body>
        <h1>My Restaurant - Booking</h1>
        <% int idx = Integer.parseInt(restaurant.getParameter("idx")); %>

        <% if(restaurant.isReserved(idx)){ %>
            <b>Sorry, this seat is booked.</b>
        <% } else { %>
            <form action="/mvc/reservation" method="post">
            Name: <input type="text" name="name"/> <br>
            <input type="hidden" name="idx" value="<%= idx %>">
            <input type="submit">
            </form>
        <% } %>
    </body>
</html>