<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
<title><c:out value="${title}"></c:out></title>
</head>
<style><%@include file="\WEB-INF\views\css\myStyle.css"%></style>
<body>
<div class="wrapper">
<header>
    <div>
        <%@include file="navbar.jsp" %>
    </div>
</header>
<section id="content">
    <h1><c:out value="${title}"></c:out></h1><br/>

        <table id="roster_table">
            <tr class"no-hover">
                <th>Line</th>
                <th>LD</th>
                <th>RD</th>
                <th>LW</th>
                <th>C</th>
                <th>RW</th>
                <th></th>
            </tr>
            <c:forEach var="line" items="${lines}">
                <c:url var="editLineLink" value="editLine">
                    <c:param name="lineId" value="${line.id}" />
                </c:url>
                <tr style="height:70px;">
                    <td style="text-align:left;font-weight:bold;color:#333;">${line.getLineName()}</td>
                    <td>${line.getLeftDefender()}</td>
                    <td>${line.getRightDefender()}</td>
                    <td>${line.getLeftWing()}</td>
                    <td>${line.getCenter()}</td>
                    <td>${line.getRightWing()}</td>
                    <td class="no-border"><a href="${editLineLink}" class="button">Edit line</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>

</div>
<%@include file="footer.jsp" %>

</body>
</html>