<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <title><c:out value="${title}"></c:out></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style><%@include file="\WEB-INF\views\css\myStyle.css"%></style>
</head>

<body>
    <div class="wrapper">
    <header>
        <div>
            <%@include file="navbar.jsp" %>
        </div>
    </header>
    <div id="sidebar">
       <h3>Info</h3>
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
               <td>${player.playerStats.isPlayerInjured()}
                   <c:if test="${player.playerStats.isInjured()}">
                      <i style="text-decoration: none;color:red;font-size:20px;" class="fa fa-medkit"></i>
                   </c:if>
               </td>
           </tr>
           <tr>
               <td>Additional info:</td>
               <td>${player.additionalInfo}</td>
           </tr>
        </table>
    </div>

    <section id="content_small">
        <h1><c:out value="${title}"></c:out></h1><br/>

        <form:form action="${pageContext.request.contextPath}/players/saveStats" modelAttribute="playerStats" method="POST">
            <form:input type="hidden" path="stats_id"/>
            <form:input type="hidden" path="injured"/>
            <table class="no-border">
                <tr>
                    <td>Games played:</td>
                    <td><form:input path="gamesPlayed" type="number" step="1" min="0" style="width:50px;" required="required"
                        oninvalid="this.setCustomValidity('Value must be at least 0')"
                            oninput="this.setCustomValidity('')"/></td>
                </tr>
                <tr>
                    <td>Goals:</td>
                    <td><form:input path="goals" type="number" step="1" min="0" style="width:50px;" required="required"
                        oninvalid="this.setCustomValidity('Value must be at least 0')"
                                oninput="this.setCustomValidity('')"/></td>
                </tr>
                <tr>
                    <td>Assists:</td>
                    <td><form:input path="assists" type="number" step="1" min="0" style="width:50px;" required="required"
                        oninvalid="this.setCustomValidity('Value must be at least 0')"
                                oninput="this.setCustomValidity('')"/></td>
                </tr>
                <tr>
                    <td><button onclick="goBack()">Cancel</button></td><td><input type="submit" value="Save" class="button" style="width:70px;"/></td>
                </tr>
            </table>
        </form:form>

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