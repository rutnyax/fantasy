<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="page-header">
	<h2>
		Edit League <small>Update league settings</small>
	</h2>
</div>

<form:form commandName="league" cssClass="form-horizontal editLeagueForm">

	<c:if test="${success eq true}">
		<div class="alert alert-success">Your changes were successfully saved.</div>
	</c:if>
	
	<div class="form-group">
		<label for="inputName" class="col-sm-2 control-label">Name:</label>
		<div class="col-sm-10">
			<form:input path="leagueName" type="text" cssClass="form-control"
				id="inputName" placeholder="League Name" />
			<form:errors path="leagueName" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input type="submit" value="Update" class="btn btn-primary" />
		</div>
	</div>
</form:form>

<script type="text/javascript">
$(document).ready(function() {
	$(".editLeagueForm").validate({
		rules : {
			leagueName : {
				required : true,
				minlength : 3,
				remote: {
					url: "<spring:url value='/leagues/available.html' />",
					type: "get",
					data: {
						name: function() {
							return $("#inputName").val();
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
			leagueName: {
				remote: "League name already exists."
			}
		}
	});
});
</script>
    