<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<h1>Players from database:</h1>

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
			<c:forEach items="${players}" var="player">
				<tr>
					<td><c:out value='${player.playerId}' /></td>
					<td><c:out value='${player.playerName}' /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
