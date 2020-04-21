<jsp:useBean id="user"
    scope="session"
    class="beans.UserBean"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Document</title>
</head>
<body>
    <!-- takes username -->
    
    <form class="box" action="newGameServlet" method="post">
        <h1>Select Level</h1>

        <p id="p-text">User: <jsp:getProperty name="user" property="username" /></p>
        <input type="hidden" name="username" value="<jsp:getProperty name="user" property="username" />">
        <input type="submit" name="gameDifficulty" value="Beginner">
        <input type="submit" name="gameDifficulty" value="Intermediate">
        <input type="submit" name="gameDifficulty" value="Advanced">
        <hr>
        <input type="submit" name="" value="Continue">
    </form>
</body>
</html>