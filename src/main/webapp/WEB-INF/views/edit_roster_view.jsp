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
    <div id="sidebar"style="min-height:127%;">
        <c:forEach items="${current_lines}" var="line">
            <br/>
            <h4 style="padding-bottom:8px;">${line.getLineName()}</h4>

            <p>Defenders</p>
            <p style="padding-left:25px;">${line.getLeftDefender()}</p>
            <p style="padding-left:25px;">${line.getRightDefender()}</p>
            <br/>
            <p >Forwards</p>
            <p style="padding-left:25px;">${line.getLeftWing()}</p>
            <p style="padding-left:25px;">${line.getCenter()}</p>
            <p style="padding-left:25px;">${line.getRightWing()}</p>

        </c:forEach>
    </div>

    <section id="content_small" style="min-height:127%;">
        <h1><c:out value="${title}"></c:out></h1><br/>

        <table id="roster_table">
            <tr class"no-hover">
                <th>LD</th>
                <th>RD</th>
                <th>LW</th>
                <th>C</th>
                <th>RW</th>
            </tr>
            <form:form action="${pageContext.request.contextPath}/roster/saveLine" modelAttribute="line" method="POST">
                <form:input type="hidden" path="id"/>
                <form:input type="hidden" path="lineName"/>
                <tr class="no-border" style="height:80px;">
                    <td><form:select path="LD">
                        <option value="0">-- Select --</option>
                        <c:forEach items="${defenders}" var="player">
                           <option value="${player.id}" <c:out value="${player.id == line.LD ? 'selected': ''}"/>>${player.jerseyNumber} ${player.firstName} ${player.lastName}</option>
                       </c:forEach>
                       </form:select>
                    </td>
                    <td><form:select path="RD">
                        <option value="0">-- Select --</option>
                        <c:forEach items="${defenders}" var="player">
                           <option value="${player.id}" <c:out value="${player.id == line.RD ? 'selected': ''}"/>>${player.jerseyNumber} ${player.firstName} ${player.lastName}</option>
                       </c:forEach>
                       </form:select>
                    </td>
                    <td><form:select path="LW">
                        <option value="0">-- Select --</option>
                        <c:forEach items="${forwards}" var="player">
                           <option value="${player.id}" <c:out value="${player.id == line.LW ? 'selected': ''}"/>>${player.jerseyNumber} ${player.firstName} ${player.lastName}</option>
                       </c:forEach>
                       </form:select>
                    </td>

                    <td><form:select path="C">
                        <option value="0">-- Select --</option>
                        <c:forEach items="${forwards}" var="player">
                           <option value="${player.id}" <c:out value="${player.id == line.getC() ? 'selected': ''}"/>>${player.jerseyNumber} ${player.firstName} ${player.lastName}</option>
                       </c:forEach>
                       </form:select>
                    </td>
                    <td><form:select path="RW">
                        <option value="0">-- Select --</option>
                        <c:forEach items="${forwards}" var="player">
                           <option value="${player.id}" <c:out value="${player.id == line.RW ? 'selected': ''}"/>>${player.jerseyNumber} ${player.firstName} ${player.lastName}</option>
                       </c:forEach>
                       </form:select>
                    </td>
                </tr>
                <tr class="no-border" style="text-align:left;height:80px;">
                    <td><button onclick="goBack()">Cancel</button></td>
                    <td><input type="submit" value="Save" class="button" style="width:70px;"/><td>
                </tr>
            </form:form>
        </table>
        <br/>
    </section>

    </div>
<%@include file="footer.jsp" %>
<script>
function goBack() {
    window.history.back();
}
</script>
</body>
</html>