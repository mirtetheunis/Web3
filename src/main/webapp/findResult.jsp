<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Find Result</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
    <div id="container">
        <header>
            <h1><span>Results Find</span></h1>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>

                    <c:if test="${user.role=='ADMIN' || user.role=='CUSTOMER'}">
                        <li><a href="Controller?command=Overview">Overview</a></li>
                        <li><a href="Controller?command=ContactOverviewPersonal">Own Contacts</a> </li>
                        <li><a href="registerPositiveTest.jsp">Register Test</a> </li>
                        <li><a href="Controller?command=Search">Search</a> </li>
                        <li id="actual"><a href="find.jsp">Find</a> </li>
                    </c:if>

                    <c:if test="${user.role=='ADMIN'}">
                        <li><a href="Controller?command=ContactOverview">Contacts</a></li>
                        <li><a href="Controller?command=Register">Register</a></li>
                        <li><a href="delete.jsp">Delete</a> </li>
                    </c:if>

                </ul>
            </nav>
            <h2>
                Results Find
            </h2>
        </header>
        <main>
            <table>
                <tr>
                    <th>Date</th>
                    <th>Name</th>
                </tr>
                <c:forEach var="contact" items="${contacts}">
                    <tr>
                        <td><c:out value="${contact.date}"/></td>
                        <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                    </tr>
                </c:forEach>
                <caption>Find contacts</caption>
            </table>
        </main>
    </div>
</body>
</html>
