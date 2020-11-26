<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Home</span>
        </h1>
        <nav>
            <ul>
                <li id="actual"><a href="index.jsp">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=ContactOverview">Contacts</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
                <li><a href="delete.jsp">Delete</a> </li>
            </ul>
        </nav>
        <h2>Home</h2>

    </header>
    <main>
        <c:choose>
            <c:when test="${not empty user}">
                    <p>Welkom, ${user.firstName}!</p>
                    <br>
                    <form action="Controller?command=Logout" method="POST">
                        <p><input type="submit" id="signUp" value="Log out"></p>
                    </form>
            </c:when>
            <c:otherwise>
                <c:if test="${not empty fout}">
                    <div class="alert-danger">
                        <ul>
                            <li>${fout}</li>
                        </ul>
                    </div>
                </c:if>
                <form action="Controller?command=Login" method="POST">
                    <p><label for="userId">Your userID</label><input type="text" id="userId" name="userId"
                                                                     value="${userIdPrevious}" required></p>
                    <p><label for="password">Your password</label><input type="password" id="password"
                                                                         name="password" required></p>
                    <p><input type="submit" id="logIn" value="Log in"></p>
                </form>
            </c:otherwise>
        </c:choose>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
