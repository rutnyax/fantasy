<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		Edit Team <small>Update team settings</small>
	</h2>
</div>

<form:form commandName="team" cssClass="form-horizontal editTeamForm">

	<c:if test="${success eq true}">
		<div class="alert alert-success">Your changes were successfully saved.</div>
	</c:if>
	
	<div class="form-group">
		<label for="inputName" class="col-sm-2 control-label">Name:</label>
		<div class="col-sm-10">
			<form:input path="teamName" type="text" cssClass="form-control"
				id="inputName" placeholder="Team Name" />
			<form:errors path="teamName" />
		</div>
		<input id="leagueName" type="hidden" value="${team.league.leagueName}" />
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input type="submit" value="Update" class="btn btn-primary" />
		</div>
	</div>
</form:form>

<script type="text/javascript">
$(document).ready(function() {
	$(".editTeamForm").validate({
		rules : {
			teamName : {
				required : true,
				minlength : 3,
				remote: {
					url: "<spring:url value='/teams/available.html' />",
					type: "get",
					data: {
						teamname: function() {
							return $("#inputName").val();
						},
						leaguename: function() {
							return $("#leagueName").val();
						}
					}
				}
			},
		},
		highlight: function(element) {
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		unhighlight: function(element) {
			$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
		},
		messages: {
			teamName: {
				remote: "Team name already exists in this league."
			}
		}
	});
});
</script>
