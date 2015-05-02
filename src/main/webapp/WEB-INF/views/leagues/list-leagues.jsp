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
			<br>
			<c:if test="${empty owned}">
				<c:choose>
					<c:when test="${empty member}">
						<p class="text-center">No Leagues yet, why don't you create
							one?</p>
					</c:when>
					<c:otherwise>
						<p class="text-center">You don't own any leagues. You can view
							leagues you're a member of on the next tab.</p>
					</c:otherwise>
				</c:choose>
			</c:if>
			<div class="list-group">
				<c:forEach items="${owned}" var="league">
					<div class="list-group-item clearfix">
						<h4 class="list-group-item-heading">
							<a href='<spring:url value="/leagues/${league.leagueId}.html" />'><c:out
									value='${league.leagueName}' /></a> <span class="badge pull-right"><c:out
										value='${fn:length(league.teams)}' /> teams</span>
						</h4> <a
								href='<spring:url value="/leagues/edit/${league.leagueId}.html" />'
								class="btn btn-primary btn-xs pull-right">Manage</a>
						<small class="list-group-item-text"> Created by <span><a
								href="<spring:url value='/users/${league.owner.userId}.html' />">${league.owner.userName}</a></span>
						</small>
					</div>
				</c:forEach>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="member">
			<br>
			<c:if test="${empty member}">
				<c:choose>
					<c:when test="${empty owned}">
						<p class="text-center">No Leagues yet, why don't you create
							one?</p>
					</c:when>
					<c:otherwise>
						<p class="text-center">You're not a member of any leagues you
							do not own.</p>
					</c:otherwise>
				</c:choose>
			</c:if>
			<div class="list-group">
				<c:forEach items="${member}" var="league">
					<div class="list-group-item clearfix">
						<h4 class="list-group-item-heading">
							<a href='<spring:url value="/leagues/${league.leagueId}.html" />'><c:out
									value='${league.leagueName}' /></a> <span class="badge pull-right"><c:out
										value='${fn:length(league.teams)}' /> teams</span>
						</h4>
						<small class="list-group-item-text"> Created by <span><a
								href="<spring:url value='/users/${league.owner.userId}.html' />">${league.owner.userName}</a></span>
						</small>
					</div>
				</c:forEach>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="others">
			<br>
			<div class="list-group">
				<c:forEach items="${other}" var="league">
					<div class="list-group-item clearfix">
						<h4 class="list-group-item-heading">
							<a href='<spring:url value="/leagues/${league.leagueId}.html" />'><c:out
									value='${league.leagueName}' /></a><span class="badge pull-right"><c:out
									value='${fn:length(league.teams)}' /> teams</span>
						</h4>
						<a class="btn btn-primary btn-xs pull-right"
							href="<spring:url value='/leagues/join/${league.leagueId}.html' />">Join</a>

						<small class="list-group-item-text"> Created by <span><a
								href="<spring:url value='/users/${league.owner.userId}.html' />">${league.owner.userName}</a></span>
						</small>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#league-tabs a:first').tab('show') // Select first tab
	});
</script>
