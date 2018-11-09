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
            <form:form action="${pageContext.request.contextPath}/processRegister" modelAttribute="myuser" method="POST">

                <c:if test="${registrationError != null}">
                    <p style="color:red;margin-left:465px;">${registrationError}</p>
                </c:if>
                <table class="no-border">
                    <tr>
                        <td>Username:</td>
                        <td><form:input path="username" placeholder="username"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><form:password path="password" placeholder="password"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" class="button"/></td>
                    </tr>
                </table>
            </form:form>
        </section>
    </div>
<%@include file="footer.jsp" %>
</body>
</html>