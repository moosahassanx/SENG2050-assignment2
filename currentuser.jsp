<jsp:useBean id="user"
    scope="session"
    class="beans.UserBean"/>

Username: <jsp:getProperty name="user" property="username"> <br>
Password: <jsp:getProperty name="user" property="password"> <br>
