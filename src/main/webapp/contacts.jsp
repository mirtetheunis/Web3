<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contacts</title>
</head>
<body>
<div id="container">
    <header>
        <h1><span>Overview</span></h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Contact">Contacts</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
                <li><a href="delete.jsp">Delete</a> </li>
            </ul>
        </nav>
        <h2>
            Contact Overview
        </h2>

    </header><main>
    <table>
        <tr>
            <th>Date</th>
            <th>Hour</th>
            <th>Name</th>
        </tr>
        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td>${contact.datum}</td>
                <td>${contact.uur}</td>
                <td>${contact.firstName} ${contact.lastName}</td>
            </tr>
        </c:forEach>
        <caption>Contact Overview</caption>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
