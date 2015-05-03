<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		<c:out value='${team.teamName}' />
		<small> <c:out value="${team.league.leagueName}" /></small>
	</h2>
	<c:if test="${isOwner}">
		<div class="pull-right">
			<a class="btn btn-primary" title="Edit"
				href="<spring:url value='/teams/edit/${team.teamId}.html' />"> <i
				class="glyphicon glyphicon-edit"></i>
			</a> <a class="btn btn-danger triggerRemove" title="Delete"
				href="<spring:url value='/teams/remove/${team.teamId}.html' />">
				<i class="glyphicon glyphicon-trash"></i>
			</a>
		</div>
	</c:if>
</div>

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

<c:if test="${isOwner}">
	<div class="modal fade" id="removeModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Delete Team</h4>
				</div>
				<div class="modal-body">Are you sure you want to delete this
					team?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a href="" class="btn btn-danger removeBtn">Delete</a>
				</div>
			</div>
		</div>
	</div>
</c:if>

<script type="text/javascript">
	$(document).ready(function() {
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#removeModal .removeBtn").attr("href", $(this).attr("href"));
			$("#removeModal").modal();
		});
	});
</script>
