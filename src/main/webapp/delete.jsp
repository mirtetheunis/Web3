<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/stijl.css">
</head>
<body>
<div id="container">
    <div id="content">
        <h1><span>Delete</span></h1>
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
                    <li><a href="Controller?command=ContactOverview">Contacts</a></li>
                    <li><a href="Controller?command=Register">Register</a></li>
                    <li id="actual"><a href="Controller?command=OPEN_delete">Delete</a> </li>
                </c:if>

            </ul>
        </nav>

        <main>
            <h2>
                Delete member
            </h2>
                <p>Wil je een account verwijderen?</p>
                <br>
                <form action="Controller?command=Delete" method="POST">
                    <p><label for="userid">User id</label>
                        <input type="text" id="userid" name="userid" required pattern="^[a-zA-Z0-9]{1,20}$"> </p>
                    <p><input type="submit" id="signUp" value="Delete member"></p>
                </form>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
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
                return "Input isn't correct.."
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
