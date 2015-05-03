<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		Teams <small>Examine your beautiful fleet</small>
	</h2>
</div>

<div class="row list-group">
	<c:forEach items="${teams}" var="team">
		<div class="col-md-6 col-sm-4">
			<div class="thumbnail">
				<img src="http://placehold.it/300x140&text=${team.teamName}.png"
					alt="Team Logo">
				<div class="list-group-item">
					<h4 class="list-group-item-heading">
						<a href='<spring:url value="/teams/${team.teamId}.html" />'><c:out
								value='${team.teamName}' /></a>
					</h4>
					<small class="list-group-item-text"><a
						href="<spring:url value='/leagues/${team.league.leagueId}.html' />"><c:out
								value="${team.league.leagueName}" /></a> </small>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
