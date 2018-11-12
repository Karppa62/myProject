<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
<style><%@include file="\WEB-INF\views\css\myStyle.css"%></style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title><c:out value="${title}"></c:out></title>
</head>
<body>
<div class="wrapper">
<header>
    <div>
        <%@include file="navbar.jsp" %>
    </div>
</header>
    <section id="content">
        <h1><c:out value="${title}"></c:out></h1><br/>

        <div style="min-width:80%;margin:0px 120px;">
        <a href="${pageContext.request.contextPath}/players/addPlayer/" class="button">Add new player</a>
        <br/><br/>

        <form:form action="${pageContext.request.contextPath}/players/searchPlayers" modelAttribute="player" method="POST">
            <label>Search players: </label> <input type="text" name="searchValue" placeholder="Search" /><br/>
            <label>Show: </label>
            <input type="radio" name="position" value="" checked> All
            <input type="radio" name="position" value="D"> Defenders
            <input type="radio" name="position" value="F"> Forwards
            <br/><br/>
            <input type="submit" value="Search" class="button"/>
        </form:form><br/>
    </div>
        <table id="players_table">
            <tr class"no-hover">
                <th style="text-align:left;">Name</th>
                <th>#</th>
                <th>Position</th>
                <th>Games<br/> played</th>
                <th>Goals</th>
                <th>Assists</th>
                <th>Points</th>
            </tr>
            <c:forEach var="player" items="${player_list}">
                 <c:url var="detailsLink" value="detailsPlayer">
                    <c:param name="playerId" value="${player.id}" />
                </c:url>
                <c:url var="editStatsLink" value="editStats">
                    <c:param name="playerId" value="${player.id}" />
                </c:url>
                <c:url var="deleteLink" value="deletePlayer">
                    <c:param name="playerId" value="${player.id}" />
                </c:url>
                <c:url var="toggleInjuredLink" value="toggleInjured">
                    <c:param name="playerId" value="${player.id}" />
                </c:url>
                <c:if test="${player.playerStats.isInjured()}">
                    <c:set var="isInjured" value="Mark healthy"/>
                    <c:set var="injured" value="fa fa-medkit"/>
                </c:if>
                <c:if test="${!player.playerStats.isInjured()}">
                    <c:set var="isInjured" value="Mark injured"/>
                    <c:set var="injured" value=""/>
                </c:if>

                <tr class=<c:out value=""/>>
                    <td><a href="${detailsLink}">${player.lastName} ${player.firstName}</a> <i class='<c:out value="${injured}"/>' style="text-decoration: none;font-size:20px;color:red;"</i></td>
                    <td class="cell_style">${player.jerseyNumber}</td>
                    <td class="cell_style">${player.position}</td>
                    <td class="cell_style">${player.playerStats.getGamesPlayed()}</td>
                    <td class="cell_style">${player.playerStats.getGoals()}</td>
                    <td class="cell_style">${player.playerStats.getAssists()}</td>
                    <td class="cell_style">${player.playerStats.getGoals() + player.playerStats.getAssists()}</td>
                    <td class="no-border" style="text-align:center;padding-left:20px;"><a style="text-decoration: none;color:green;" class="fa fa-pencil" href="${editStatsLink}" title="Edit stats"></a></td>
                    <td class="no-border"><a style="text-decoration: none;font-size:20px;color:grey;"
                        class="fa fa-trash" style="" href="${deleteLink}"
                        onclick="if (!(confirm('Delete ${player.firstName} ${player.lastName}?'))) return false"
                        title="Delete player"></a>
                    </td>
                    <td class="no-border"><a href="${toggleInjuredLink}" class="button"><c:out value="${isInjured}"/></a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
    </section>

</div>
<%@include file="footer.jsp" %>
</body>
</html>