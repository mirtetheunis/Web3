<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Find</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Find</span></h1>
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
            Find
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

                <form method="POST" action="Controller?command=Find" novalidate="novalidate">
                    <p><label for="date">Date</label><input type="text" id="date" name="date" placeholder="YYYY-mm-dd"
                                                            required > </p>
                    <p><label for="hour">Hour</label><input type="text" id="hour"  name="hour" placeholder="hh:mm"
                                                            required> </p>
                    <p><input type="submit" id="signUp" value="Find"></p>
                </form>
    </main>
</div>

</body>
</html>
