<form action="nibba.jsp">
    <input type="text" name="name">
    <input type="submit">
</form>


<%
String bg = request.getParameter("color");

boolean hasBackgroundColour;
if(bg != null){
    hasBackgroundColour = true;
}else{
    hasBackgroundColour = false;
    bg = "white";
}
%>


<p style="background-color: <%= bg %>">This is a coloured paragraph</p>
    
<% if (hasBackgroundColour) { %>
<p>A colour was provided</p> 
<% }else{ %>
<p>A colour was not provided</p>
<% } %>


<%
int num1 = 9;
int num2 = 10;

out.println("num1 is: " + num1);
out.println("num2 is: " + num2);
%>