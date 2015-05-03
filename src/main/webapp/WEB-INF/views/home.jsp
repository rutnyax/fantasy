<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		Players <small>All Players</small>
	</h2>
</div>

<table id="all-players" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${players}" var="player">
			<tr>
				<td><c:out value='${player.playerId}' /></td>
				<td><c:out value='${player.playerName}' /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">
	$(document).ready(function() {
		$('#all-players').DataTable();
	});
</script>
