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
    </div>

    <section id="content_small">
        <h1><c:out value="${title}"></c:out>
            <c:if test="${player.playerStats.isInjured()}">
               <i style="text-decoration: none;color:red;font-size:40px;" class="fa fa-medkit"></i>
            </c:if>
        </h1>
        <br/>

        <c:url var="updateLink" value="updatePlayer">
            <c:param name="playerId" value="${player.id}" />
        </c:url>
            <table class="no-border">
                <tr>
                    <td>First Name:</td>
                    <td>${player.firstName}</td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td>${player.lastName}</td>
                </tr>
                <tr>
                    <td>Number:</td>
                    <td>${player.jerseyNumber}</td>
                </tr>
                <tr>
                    <td>Position:</td>
                    <td>${player.position}</td>
                </tr>
                <tr>
                    <td>Injured:</td>
                    <td>${player.playerStats.isPlayerInjured()}</td>
                </tr>
                <tr>
                    <td>Additional info:</td>
                    <td>${player.additionalInfo}</td>
                </tr>
                <tr>
                    <td><button onclick="goBack()">Back</button></td>
                    <td><a href="${updateLink}" class="button">Edit info</a></td>
                </tr>
            </table>

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