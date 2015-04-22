<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../layout/taglib.jsp"%>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">New Team</button>

<form:form commandName="team" cssClass="form-horizontal teamForm">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">New Team</h4>
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
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#removeModal .removeBtn").attr("href", $(this).attr("href"));
			$("#removeModal").modal();
		});
		
		$('.teamForm').validate({
			rules : {
				teamName : {
					required : true,
					minlength : 3
				},
			},
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			}
		});
	});
</script>

<c:forEach items="${user.teams}" var="team">

	<h1>
		<c:out value='${team.teamName}' />
	</h1>
	<p>
		<a href='<spring:url value="/team/remove/${team.teamId}.html" />'
			class="btn btn-danger triggerRemove">Delete Team</a>
		<c:out value='${team.league.leagueName}' />
	</p>

	<!-- 	<table -->
	<!-- 		class="table table-striped table-bordered table-hover table-condensed"> -->
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
