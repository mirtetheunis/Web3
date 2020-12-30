<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Register</span></h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>

                <c:if test="${user.role=='ADMIN' || user.role=='CUSTOMER'}">
                    <li><a href="Controller?command=Overview">Overview</a></li>
                    <li><a href="Controller?command=ContactOverviewPersonal">Own Contacts</a> </li>
                    <li><a href="registerPositiveTest.jsp">Register Test</a> </li>
                    <li><a href="Controller?command=Search">Search</a> </li>
                    <li><a href="find.jsp">Find</a> </li>
                </c:if>

                <c:if test="${user.role=='ADMIN'}">
                    <li><a href="Controller?command=ContactOverview">Contacts</a></li>
                    <li id="actual"><a href="Controller?command=Register">Register</a></li>
                    <li><a href="delete.jsp">Delete</a> </li>
                </c:if>

            </ul>
        </nav>
        <h2>
            Register
        </h2>

    </header><main>
    <c:if test = "${not empty errors}">
        <div class="alert-danger">
            <ul>
                <c:forEach items = "${errors}" var="error">
                    <li><c:out value="${error}"/></li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <form method="POST" action="Controller?command=Add" novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="text" id="userid" name="userid" value="<c:out value="${idVorige}"/>"
                                                         required pattern="^[a-zA-Z0-9-_.]{1,20}$"> </p>
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                           required value="<c:out value="${voornaamVorige}"/>"
                                                           pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$"> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="<c:out value="${naamVorige}"/>"
                                                         required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$"> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" value="<c:out value="${emailVorige}"/>"
                                                        required
                                                  pattern ="^[A-Za-z0-9+_.-]+@(.+)$"></p>
        <p><label for="password">Password</label><input type="password" id="password"  name="password" value="<c:out value="${passwordVorige}"/>"
                                                        required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{3,20}$"> </p>
        <p><label for="role">Role</label><input type="text" id="role" name="role" value="<c:out value="${rolVorige}"/>"
                                                        required></p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>

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

