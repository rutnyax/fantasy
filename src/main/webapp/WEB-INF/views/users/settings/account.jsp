<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		My Account <small>My Teams</small>
	</h2>
</div>

<c:if test="${verified eq true}">
	<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>Welcome!</strong> Your account has been verified.
	</div>
</c:if>

<div class="row list-group">
	<c:forEach items="${user.teams}" var="team">
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
