<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/sample.css">
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<main id="container">
    <header>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Contact">Contacts</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
                <li><a href="delete.jsp">Delete</a> </li>
            </ul>
        </nav>
    </header>
    <p>${error}</p>
</main>
</body>
<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</html>
