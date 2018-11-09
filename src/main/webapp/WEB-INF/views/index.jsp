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
<header>
    <div>
    <%@include file="navbar.jsp" %>
    </div>
</header>
<section id="content">

        <h1><c:out value="${title}"></c:out></h1><br/><br/>
</section>


</div>
<%@include file="footer.jsp" %>

</body>
</html>