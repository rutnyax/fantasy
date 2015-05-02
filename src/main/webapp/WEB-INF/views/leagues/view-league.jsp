<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		<c:out value='${league.leagueName}' />
	</h2>
	<c:if test="${isOwner}">
		<small>Invite code: <c:out value="${league.passcode}" />
			<button title="Copy to clipboard" class="btn btn-default copy-button"
				data-clipboard-text="${league.passcode}" data-copied-hint="Copied!"
				type="button">
				<span class="glyphicon glyphicon-copy"></span>
			</button>
		</small>
		<div class="pull-right">
			<a class="btn btn-primary" title="Edit"
				href="<spring:url value='/leagues/edit/${league.leagueId}.html' />">
				<i class="glyphicon glyphicon-edit"></i>
			</a> <a class="btn btn-danger" title="Delete"> <i
				class="glyphicon glyphicon-trash"></i>
			</a>
		</div>
	</c:if>
</div>

<div class="page-header">
	<h3>
		Teams
		<c:if test="${not hasTeam}">
			<a class="btn btn-primary btn-sm"> Register Your Team</a>
		</c:if>
	</h3>
</div>

<c:choose>
	<c:when test="${isMember or isOwner}">
		<c:if test="${not hasTeam}">
			<div class="alert alert-warning text-center">
				You have not registered a team for this league yet, why don't you <a
					href="" data-toggle="modal" data-target="#newTeamModal">register
					your team</a>?
			</div>
		</c:if>
	</c:when>
	<c:otherwise>
		<div class="alert alert-warning text-center">
			You are not a member of this league yet, why don't you <a
				href="<spring:url value='/leagues/join/${league.leagueId}.html' />">join
				now</a>?
		</div>
	</c:otherwise>
</c:choose>

<div class="list-group">
	<c:forEach items="${league.teams}" var="team">
		<a href='<spring:url value="/teams/${team.teamId}.html" />'
			class="list-group-item">
			<h5 class="list-group-item-heading">
				<c:out value="${team.teamName}" />
			</h5> <small class="list-group-item-text"> Owned by <span>
					<c:out value="${team.user.userName}" />
			</span>
		</small>
		</a>
	</c:forEach>
</div>

<form:form commandName="team" cssClass="form-horizontal teamForm">
	<!-- Modal -->
	<div class="modal fade" id="newTeamModal" tabindex="-1" role="dialog"
		aria-labelledby="newTeamModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="newTeamModalLabel">New Team</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">Name:</label>
						<div class="col-sm-10">
							<form:input path="teamName" type="text" cssClass="form-control"
								id="inputName" placeholder="Team Name" />
							<form:errors path="teamName" />
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
	var client = new ZeroClipboard($('.copy-button'));
	client.on('ready', function(event) {
		client.on('copy', function(event) {
			event.clipboardData.setData('text/plain',
					$('copy-text').val());
		});
	});
	client.on('error', function(event) {
		ZeroClipboard.destroy();
	});

	$('.teamForm').validate({
		rules : {
			teamName : {
				required : true,
				minlength : 3
			},
		},
		highlight : function(element) {
			$(element).closest('.form-group').removeClass(
					'has-success').addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass(
					'has-error').addClass('has-success');
		}
	});
});
</script>
