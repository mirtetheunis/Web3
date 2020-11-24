<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
<div id="container">
    <div id="content">
        <h1><span>Delete</span></h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=ContactOverview">Contacts</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
                <li id="actual"><a href="delete.jsp">Delete</a></li>
            </ul>
        </nav>

        <main>
            <h2>
                Delete member
            </h2>
                <p>Wil je jouw account verwijderen?</p>
                <br>
                <form action="Controller?command=Delete" method="POST">
                    <p><label for="userid">User id</label>
                        <input type="text" id="userid" name="userid" required > </p>
                    <p><input type="submit" id="signUp" value="Delete member"></p>
                </form>

            <h2>
                Delete contact
            </h2>
                <p>Wil je een contact verwijderen?</p>
            <c:if test = "${not empty errors}">
                <div class="alert-danger">
                    <ul>
                        <c:forEach items = "${errors}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
                <form action="Controller?command=DeleteContact" method="POST">
                    <p><label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" required> </p>
                    <p><label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" required> </p>
                    <p><label for="date">Date</label>
                        <input type="text" id="date" name="lastName" required> </p>
                    <p><label for="hour">Hour</label>
                        <input type="text" id="hour" name="hour" required> </p>
                    <p><input type="submit" id="delete" value="Delete Contact"></p>
                </form>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
