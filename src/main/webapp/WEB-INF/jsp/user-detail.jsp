<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1>${user.userName}</h1>

<c:forEach items="${user.teams}" var="team">

	<h1>${team.teamName}</h1>
	<p>${team.league.leagueName}</p>

	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${team.players}" var="player">
				<tr>
					<td>${player.playerId}</td>
					<td>${player.playerName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:forEach>
