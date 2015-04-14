<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<table
	class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>User Name</th>
			<th>Delete?</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href='<spring:url value="/users/${user.userId}.html" />'>
						${user.userName} </a></td>
				<td><a
					href='<spring:url value="/users/remove/${user.userId}.html" />'
					class="btn btn-danger">Remove</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
