<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		Leagues <small>Join one or create yours</small>
	</h2>
</div>

<div role="tabpanel">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs nav-justified" role="tablist" id="league-tabs">
		<li role="presentation"><a href="#owned" aria-controls="owned"
			role="tab" data-toggle="tab">Owned by you</a></li>
		<li role="presentation"><a href="#member" aria-controls="member"
			role="tab" data-toggle="tab">You are a member</a></li>
		<li role="presentation"><a href="#others" aria-controls="others"
			role="tab" data-toggle="tab">Others</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane" id="owned">
			<c:if test="${empty owned}">
				<c:choose>
					<c:when test="${empty member}">
					<br>
						<div class="alert alert-warning text-center">
							No Leagues yet, why don't you <a href="" data-toggle="modal"
								data-target="#myModal">create a league</a>?
						</div>
					</c:when>
					<c:otherwise>
					<br>
						<div class="alert alert-warning text-center">
							You don't own any leagues. You can view leagues you're a member
							of on the next tab or <a href="" data-toggle="modal"
								data-target="#myModal">create a league</a>?
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:forEach items="${owned}" var="league">

				<h3>
					<c:out value='${league.leagueName}' />
					<span class="pull-right"> <a
						href='<spring:url value="/leagues/edit/${league.leagueId}.html" />'
						class="btn btn-primary">Manage</a></span>
				</h3>

				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>Team</th>
							<th>User</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${league.teams}" var="team">
							<tr>
								<td><c:out value="${team.teamName}" /></td>
								<td><c:out value="${team.user.userName}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:forEach>
		</div>
		<div role="tabpanel" class="tab-pane" id="member">
			<c:if test="${empty member}">
				<c:choose>
					<c:when test="${empty owned}">
					<br>
						<div class="alert alert-warning text-center">
							No Leagues yet, why don't you <a href="" data-toggle="modal"
								data-target="#myModal">create a league</a>?
						</div>
					</c:when>
					<c:otherwise>
					<br>
						<div class="alert alert-warning text-center">
							You don't own any leagues. You can view leagues you're a member
							of on the next tab or <a href="" data-toggle="modal"
								data-target="#myModal">create a league</a>?
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:forEach items="${member}" var="league">

				<h3>
					<c:out value='${league.leagueName}' />
				</h3>

				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>Team</th>
							<th>User</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${league.teams}" var="team">
							<tr>
								<td><c:out value="${team.teamName}" /></td>
								<td><c:out value="${team.user.userName}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:forEach>
		</div>
		<div role="tabpanel" class="tab-pane" id="others">
			<c:forEach items="${all}" var="league">

				<h3>
					<c:out value='${league.leagueName}' />
				</h3>

				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>Team</th>
							<th>User</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${league.teams}" var="team">
							<tr>
								<td><c:out value="${team.teamName}" /></td>
								<td><c:out value="${team.user.userName}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:forEach>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#league-tabs a:first').tab('show') // Select first tab
	});
</script>
