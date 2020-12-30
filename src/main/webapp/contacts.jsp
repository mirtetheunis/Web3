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
                    <li><a href="Controller?command=OPEN_registertest">Register Test</a> </li>
                    <li><a href="Controller?command=Search">Search</a> </li>
                    <li><a href="Controller?command=OPEN_find">Find</a> </li>
                </c:if>

                <c:if test="${user.role=='ADMIN'}">
                    <li id="actual"><a href="Controller?command=ContactOverview">Contacts</a></li>
                    <li><a href="Controller?command=Register">Register</a></li>
                    <li><a href="Controller?command=OPEN_delete">Delete</a> </li>
                </c:if>

            </ul>
        </nav>
    </header>
    <main>

        <c:if test="${gelukt != null}">
            <div class="alert-feedback">
                <p>${gelukt}</p>
            </div>
        </c:if>

        <h2>
            Contact Overview
        </h2>
    <table>
        <tr>
            <th>Date</th>
            <th>Hour</th>
            <th>Name</th>
            <th>Member id</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${contact.date}"/></td>
                <td><fmt:formatDate pattern="HH:mm" value="${contact.date}"/></td>
                <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                <td><c:out value="${contact.personid}"/></td>
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

        <form method="post" action="Controller?command=GetContactsForDate" novalidate>
            <p>
                <label for="from">From</label>
                <input type="date" id="from" name="from">
                <label for="until">Until</label>
                <input type="date" id="until" name="until"> </p>
            <p>
                <label for="member">Member</label>
                <input type="text" id="member" name="member" placeholder="Member id or empty">
                <input type="submit" id="filter" value="Filter">
            </p>
        </form>
        <form method="post" action="Controller?command=ContactOverview" novalidate>
            <input type="submit" id="clear" value="Clear filter">
        </form>
        <form method="POST" action="Controller?command=AddContact" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                               required value="<c:out value="${voornaamVorige}"/>"
                                                               pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$"> </p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="<c:out value="${naamVorige}"/>"
                                                             required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$"> </p>
            <p><label for="date">Date</label><input type="text" id="date" name="date" value="<c:out value="${datumVorige}"/>"
                                                            required placeholder="YYYY-mm-dd" pattern ="[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])"> </p>
            <p><label for="hour">Hour</label><input type="text" id="hour"  name="hour" value="<c:out value="${uurVorige}"/>"
                                                            required placeholder="hh:mm" pattern ="(2[0-3]|[01][0-9]):[0-5][0-9]"> </p>
            <p><label for="gsm">GSM</label><input type="tel" id="gsm"  name="gsm" value="<c:out value="${gsmVorige}"/>" placeholder="04xxxxxxxx"
                                                            required pattern="04[0-9]{8}"> </p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" value="<c:out value="${emailVorige}"/>"
                                                            required pattern ="^[A-Za-z0-9+_.-]+@(.+)$"></p>

            <p><input type="submit" id="addContact" value="Add Contact"></p>

        </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
<script>
    window.addEventListener("load", initPage, false);

    function initPage() {

        document.addEventListener("blur", checkField, true);

        document.addEventListener("submit", finalValidation, false);
    }

    function finalValidation(event) {
        let fields = event.target.elements;
        let error, hasErrors;
        for (let i = 0; i < fields.length; i++) {
            error = hasError(fields[i]);
            if (error) {
                showError(fields[i], error);
                if (!hasErrors) {
                    hasErrors = fields[i];
                }
            }

        }

        if (hasErrors) {
            event.preventDefault();
            hasErrors.focus();
        }

    }

    function checkField(event) {
        let error = hasError(event.target);
        if (error)
            showError(event.target, error);
        else
            removeError(event.target);
    }

    function hasError(field) {
        if (field.disabled || field.type === "file" || field.type === "submit")
            return;

        let validity = field.validity;
        if (validity == null || validity.valid) {
            return;
        }

        if (validity.valueMissing) {
            return "Please fill out a value";
        }
        if (validity.typeMismatch) {
            return "Please use the correct input type";
        }
        if (validity.patternMismatch) {
            if (field.type === "email") {
                return "This is not a valid email.";
            }
            if (field.type === "tel") {
                return "This is not a valid phonenumber."
            }
            if (field.type === "password") {
                return "Password is not long enough."
            }
            if (field.type === "text") {
                return "Input isn't correct."
            }
        }
        return "Please complete the form correct";
    }

    function removeError(field) {
        if (field.classList != null && field.classList.length > 0) {
            field.classList.remove("error");
            let id = field.id;
            let message = document.getElementById("error-for-" + id);
            if (message != null)
                message.parentNode.removeChild(message);
        }
    }

    function showError(field, error) {
        field.classList.add("error");
        let id = field.id;
        if (!id)
            return;
        let message = document.getElementById("error-for-" + id);
        if (!message) {
            message = document.createElement("span");
            message.className = "error";
            message.id = "error-for-" + id;
            field.parentNode.insertBefore(message, field.nextSibling);
        }
        message.innerHTML = error;
    }

</script>
</body>
</html>
