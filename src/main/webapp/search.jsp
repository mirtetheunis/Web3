<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Search</span></h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>

                <c:if test="${user.role=='ADMIN' || user.role=='CUSTOMER'}">
                    <li><a href="Controller?command=Overview">Overview</a></li>
                    <li><a href="Controller?command=ContactOverviewPersonal">Own Contacts</a> </li>
                    <li><a href="Controller?command=OPEN_registertest">Register Test</a> </li>
                    <li id="actual"><a href="Controller?command=Search">Search</a> </li>
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
            Contacts
        </h2>
    </header>
    <main>
        <c:if test = "${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items = "${errors}" var="error">
                        <li><c:out value="${error}"/></li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        You tested positive on <c:out value="${dateTest}"/>
        <table>
            <tr>
                <th>Name</th>
                <th>GSM</th>
                <th>E-mail</th>
            </tr>
            <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                    <td><c:out value="${contact.phonenumber}"/></td>
                    <td><c:out value="${contact.email}"/></td>
                </tr>
            </c:forEach>
            <caption>Contacts</caption>
        </table>
    </main>

</div>


</body>
</html>
