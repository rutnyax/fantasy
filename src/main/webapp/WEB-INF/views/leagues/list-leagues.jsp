<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		Leagues <small>Join one or create yours</small>
	</h2>
	<a class="btn btn-primary btn-sm" href='<spring:url value="/leagues/create.html" />'> Create New League</a>
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
						<a class="btn btn-primary btn-xs pull-right" href=""
							data-toggle="modal" data-target="#joinModal">Join</a> <small
							class="list-group-item-text"> Created by <span><a
								href="<spring:url value='/users/${league.owner.userId}.html' />">${league.owner.userName}</a></span>
						</small>
						<c:set var="joinId" value="${league.leagueId}" />
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<form:form commandName="joinLeague" cssClass="form-horizontal joinForm" action="${pageContext.request.contextPath}/leagues/join/${joinId}.html" method="POST">
	<!-- Modal -->
	<div class="modal fade" id="joinModal" tabindex="-1" role="dialog"
		aria-labelledby="joinModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="joinModalLabel">Join League</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="passcode" class="col-sm-2 control-label">Invite
							Code:</label>
						<div class="col-sm-10">
							<form:input path="passcode" type="text" cssClass="form-control"
								id="passcode" placeholder="Invite Code" />
							<form:errors path="passcode" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$('#league-tabs a:first').tab('show') // Select first tab

		$('.joinForm').validate({
			rules : {
				passcode : {
					required : true,
					minlength : 36,
					remote : {
						url : "<spring:url value='/leagues/join/${joinId}.html' />",
						type : "get",
						data : {
							passcode : function() {
								return $("#passcode").val();
							}
						}
					}
				},
			},
			highlight : function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages : {
				passcode : {
					remote : "Invalid invite code."
				}
			}
		});
	});
</script>
