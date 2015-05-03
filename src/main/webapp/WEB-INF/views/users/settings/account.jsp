<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		My Account <small>...</small>
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

<c:forEach items="${user.teams}" var="team">

	<h1>
		<c:out value='${team.teamName}' />
	</h1>
	<p>
		<a href='<spring:url value="/team/remove/${team.teamId}.html" />'
			class="btn btn-danger triggerRemove">Delete Team</a>
		<c:out value='${team.league.leagueName}' />
	</p>

	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${team.players}" var="player">
				<tr>
					<td><c:out value='${player.playerId}' /></td>
					<td><c:out value='${player.playerName}' /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:forEach>

<!-- Modal -->
<div class="modal fade" id="removeModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Remove Team</h4>
			</div>
			<div class="modal-body">Really remove?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#removeModal .removeBtn").attr("href", $(this).attr("href"));
			$("#removeModal").modal();
		});
	});
</script>
