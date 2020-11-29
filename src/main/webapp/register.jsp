<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Register</span></h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=ContactOverview">Contacts</a></li>
                <li id="actual"><a href="Controller?command=Register">Register</a></li>
                <li><a href="delete.jsp">Delete</a> </li>
                <li><a href="registerPositiveTest.jsp">Register Test</a> </li>
            </ul>
        </nav>
        <h2>
            Register
        </h2>

    </header><main>
    <c:if test = "${not empty errors}">
        <div class="alert-danger">
            <ul>
                <c:forEach items = "${errors}" var="error">
                    <li><c:out value="${error}"/></li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <form method="POST" action="Controller?command=Add" novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="text" id="userid" name="userid" value="<c:out value="${idVorige}"/>"
                                                         required > </p>
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                           required value="<c:out value="${voornaamVorige}"/>"> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="<c:out value="${naamVorige}"/>"
                                                         required> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" value="<c:out value="${emailVorige}"/>"
                                                        required></p>
        <p><label for="password">Password</label><input type="password" id="password"  name="password" value="<c:out value="${passwordVorige}"/>"
                                                        required> </p>
        <p><label for="role">Role</label><input type="text" id="role" name="role" value="<c:out value="${rolVorige}"/>"
                                                        required></p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>

    </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>