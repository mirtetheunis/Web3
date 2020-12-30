<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Overview</span></h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>

                <c:if test="${user.role=='ADMIN' || user.role=='CUSTOMER'}">
                    <li id="actual"><a href="Controller?command=Overview">Overview</a></li>
                    <li><a href="Controller?command=ContactOverviewPersonal">Own Contacts</a> </li>
                    <li><a href="Controller?command=OPEN_registertest">Register Test</a> </li>
                    <li><a href="Controller?command=Search">Search</a> </li>
                    <li><a href="Controller?command=OPEN_find">Find</a> </li>
                </c:if>

                <c:if test="${user.role=='ADMIN'}">
                    <li><a href="Controller?command=ContactOverview">Contacts</a></li>
                    <li><a href="Controller?command=Register">Register</a></li>
                    <li><a href="Controller?command=OPEN_delete">Delete</a> </li>
                </c:if>

            </ul>
        </nav>
        <h2>
            Member Overview
        </h2>

        <c:if test="${gelukt != null}">
            <div class="alert-feedback">
                <p>${gelukt}</p>
            </div>
        </c:if>

    </header><main>
    <table>
        <tr>
            <th>E-mail</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <c:forEach var="person" items="${persons}">
            <tr>
                <td><c:out value="${person.email}"/></td>
                <td><c:out value="${person.firstName}"/></td>
                <td><c:out value="${person.lastName}"/></td>
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
