<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table
	class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>User Name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.userName}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
