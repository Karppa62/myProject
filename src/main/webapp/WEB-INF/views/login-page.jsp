<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
<title><c:out value="${title}"></c:out></title>
<style><%@include file="\WEB-INF\views\css\myStyle.css"%></style>
</head>
<body>
    <div class="wrapper">
        <section id="content">
            <h1>${title}</h1>
            <form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST" style="margin:auto;">

                <c:if test="${registrationComplete != null}">
                    <p style="color:green;margin:auto;margin-left:500px;">${registrationComplete}</p>
                </c:if>

                <c:if test="${param.error != null}">
                    <p style="color:red;margin-left:500px;">Invalid username or password</p>
                </c:if>
                <table class="no-border">
                    <tr>
                        <td><label>Username: </label> </td>
                        <td><input type="text" name="username" placeholder="username"/></td>
                    </tr>
                    <tr>
                        <td><label>Password: </label> </td>
                        <td><input type="password" name="password" placeholder="password"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" class="button"/></td>
                        <td><a href="${pageContext.request.contextPath}/register" class="button">Register</a></td>
                    </tr>
                </table>
            </form:form>
        </section>
    </div>
<%@include file="footer.jsp" %>
</body>
</html>