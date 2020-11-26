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
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li id="actual"><a href="Controller?command=ContactOverview">Contacts</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
                <li><a href="delete.jsp">Delete</a> </li>
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
        </tr>
        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${contact.date}"/></td>
                <td><fmt:formatDate pattern="HH:mm" value="${contact.date}"/></td>
                <td>${contact.firstName} ${contact.lastName}</td>
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
                                                               required value="${voornaamVorige}"> </p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="${naamVorige}"
                                                             required> </p>
            <p><label for="date">Date</label><input type="text" id="date" name="date" value="${datumVorige}"
                                                         required > </p>
            <p><label for="hour">Hour</label><input type="text" id="hour"  name="hour" value="${uurVorige}"
                                                            required> </p>
            <p><label for="gsm">GSM</label><input type="text" id="gsm"  name="gsm" value="${gsmVorige}"
                                                            required> </p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" value="${emailVorige}" required></p>

            <p><input type="submit" id="addContact" value="Add Contact"></p>

        </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
