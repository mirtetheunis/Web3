<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Overview</span></h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
                <li><a href="delete.jsp">Delete</a> </li>
            </ul>
        </nav>
        <h2>
            Member Overview
        </h2>

    </header><main>
    <table>
        <tr>
            <th>E-mail</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <c:forEach var="person" items="${persons}">
            <tr>
                <td>${person.email}</td>
                <td>${person.firstName}</td>
                <td>${person.lastName}</td>
            </tr>
        </c:forEach>
        <caption>Member Overview</caption>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>