<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1>${user.userName}</h1>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">New Team</button>

<form:form commandName="team" cssClass="form-horizontal">
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
