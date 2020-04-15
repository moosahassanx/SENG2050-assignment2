The name is: <%= request.getParameter("name") %>

<% 
    session.setAttribute("username", request.getParameter("name"));
%>

<a href="currentuser.jsp">Click to see the current user</a>