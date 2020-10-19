<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <h1><span>Delete</span></h1>
        <nav>
            <ul>
                <li><a href="Controller?command=Index">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
                <li id="actual"><a href="delete.jsp">Delete</a></li>
            </ul>
        </nav>

        <main>
            <c:when test="${not empty user}">
                <p>Wil je jouw account verwijderen?</p>
                <br>
                <form action="Controller?command=Delete" method="POST">
                    <p><input type="submit" id="signUp" value="Delete"></p>
                </form>
            </c:when>
            <c:otherwise>
                <p>Je bent niet ingelogd.</p>
            </c:otherwise>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
