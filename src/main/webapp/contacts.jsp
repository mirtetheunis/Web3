<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Contacts</title>
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
                    <li><a href="Controller?command=Overview">Overview</a></li>
                    <li><a href="Controller?command=ContactOverviewPersonal">Own Contacts</a> </li>
                    <li><a href="registerPositiveTest.jsp">Register Test</a> </li>
                </c:if>

                <c:if test="${user.role=='ADMIN'}">
                    <li id="actual"><a href="Controller?command=ContactOverview">Contacts</a></li>
                    <li><a href="Controller?command=Register">Register</a></li>
                    <li><a href="delete.jsp">Delete</a> </li>
                </c:if>

            </ul>
        </nav>
    </header>
    <main>
        <h2>
            Contact Overview
        </h2>
    <table>
        <tr>
            <th>Date</th>
            <th>Hour</th>
            <th>Name</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${contact.date}"/></td>
                <td><fmt:formatDate pattern="HH:mm" value="${contact.date}"/></td>
                <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                <td><a href="Controller?command=DeleteContact&firstName=${contact.firstName}&lastName=${contact.lastName}&date=${contact.date}">Delete</a></td>
            </tr>
        </c:forEach>
        <caption>Contact Overview</caption>
    </table>

        <h2>
            Register Contact
        </h2>

        <c:if test = "${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items = "${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="POST" action="Controller?command=AddContact" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                               required value="<c:out value="${voornaamVorige}"/>"> </p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="<c:out value="${naamVorige}"/>"
                                                             required> </p>
            <p><label for="date">Date</label><input type="text" id="date" name="date" value="<c:out value="${datumVorige}"/>"
                                                            required > </p>
            <p><label for="hour">Hour</label><input type="text" id="hour"  name="hour" value="<c:out value="${uurVorige}"/>"
                                                            required> </p>
            <p><label for="gsm">GSM</label><input type="text" id="gsm"  name="gsm" value="<c:out value="${gsmVorige}"/>"
                                                            required> </p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" value="<c:out value="${emailVorige}"/>"
                                                            required></p>

            <p><input type="submit" id="addContact" value="Add Contact"></p>

        </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
