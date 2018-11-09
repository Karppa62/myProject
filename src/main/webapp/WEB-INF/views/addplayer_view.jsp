<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
<title><c:out value="${title}"></c:out></title>
<style><%@include file="\WEB-INF\views\css\myStyle.css"%></style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
<div class="wrapper">
<header>
    <div>
        <%@include file="navbar.jsp" %>
    </div>
</header>
        <div id="sidebar">
        <c:if test="${player.getPlayerStats() != null}">
            <h3>Statistics</h3>
            <table class="no-border">
                <tr>
                    <td>Games played:</td>
                    <td>${player.playerStats.getGamesPlayed()}</td>
                </tr>
                <tr>
                    <td>Goals:</td>
                    <td>${player.playerStats.getGoals()}</td>
                </tr>
                <tr>
                    <td>Assists:</td>
                    <td>${player.playerStats.getAssists()}</td>
                </tr>
                <tr>
                    <td>Points:</td>
                    <td>${player.playerStats.getGoals() + player.playerStats.getAssists()}</td>
                </tr>
                <tr>
                    <td>Injured:</td>
                    <td>${player.playerStats.isPlayerInjured()}</td>
                </tr>
            </table>
            </c:if>
        </div>

        <section id="content_small">

        <h1><c:out value="${title}"></c:out>
        <c:if test="${player.playerStats.isInjured()}">
           <i style="text-decoration: none;color:red;font-size:40px;" class="fa fa-medkit"></i>
        </c:if>
        </h1><br/>

        <form:form action="${pageContext.request.contextPath}/players/savePlayer" modelAttribute="player" method="POST">
            <form:input type="hidden" path="id"/>

            <table class="no-border">
                <c:if test="${playerSaveError != null}">
                    <tr>
                        <td style="color:red;">${playerSaveError}</td>
                    </tr>
                </c:if>
                <tr>
                    <td>First Name:</td>
                    <td><form:input path="firstName" placeholder="First name" required="required"
                    oninvalid="this.setCustomValidity('First name is required')"
                        oninput="this.setCustomValidity('')"/></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input path="lastName" placeholder="Last name" required="required"
                    oninvalid="this.setCustomValidity('Last name is required')"
                                            oninput="this.setCustomValidity('')"/></td>
                </tr>
                <tr>
                    <td>Number:</td>
                    <td><form:select path="jerseyNumber" items="${jerseyNumbers}"/></td>
                </tr>
                <tr>
                    <td>Position:</td>
                    <td><form:select path="position">
                      <form:options items="${positions}"/>
                      </form:select>
                     </td>
                </tr>
                <tr>
                    <td>Additional info:</td>
                    <td><form:textarea path="additionalInfo" /></td>
                </tr>
                <tr>
                    <td><button onclick="goBack()">Cancel</button></td>
                    <td><input type="submit" value="Save" class="button"style="width:70px;"/></td>
                </tr>
            </table>
        </form:form>


        <script>
        function goBack() {
            window.history.back();
        }
        </script>
    </section>


</div>
<%@include file="footer.jsp" %>

</body>
</html>