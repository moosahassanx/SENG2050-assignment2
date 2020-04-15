<%! private int accessCount = 0; %>
<h2>Accesses to this page since last reboot: <%= ++accessCount %></h2>

<%!
    public java.util.Date getDate(){
        return new java.util.Date();
    }
%>
<p>The current time is: <%= getDate() %></p>

<h2>JSP Expressions</h2>

Current time: <%= new java.util.Date() %>
<br>
<br>
Host name: <%= request.getRemoteHost() %>
<br>
<br>
Session id: <%= session.getId() %>
<br>
<br>
Parameter: <%= request.getParameter("param") %>
<br>
<br>
<h1>Counting</h1>
<ul>
    <% for(int i = 1; i <= 5; i++){ %>
        <li><%= i %></li>
    <% } %>
</ul>
<br>
<br>
<h1>Random Greeting</h1>
<% if(Math.random() < 0.5){ %>
    <p>generated number LESS THAN 0.5</p>
<% }else{ %>
    <p>generated number GREATER THAN 0.5</p>
<% } %>