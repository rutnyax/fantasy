<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form commandName="user" cssClass="form-horizontal">
  <div class="form-group">
    <label for="inputName" class="col-sm-2 control-label">Name:</label>
    <div class="col-sm-10">
    <form:input path="userName" type="text" cssClass="form-control" id="inputName" placeholder="Full Name" />
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail" class="col-sm-2 control-label">Email:</label>
    <div class="col-sm-10">
      <form:input path="userEmail" type="email" cssClass="form-control" id="inputEmail" placeholder="Email Address" />
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="col-sm-2 control-label">Password:</label>
    <div class="col-sm-10">
      <form:password path="userPassword" class="form-control" id="inputPassword" placeholder="Password" />
    </div>
  </div>
<!--   <div class="form-group"> -->
<!--     <div class="col-sm-offset-2 col-sm-10"> -->
<!--       <div class="checkbox"> -->
<!--         <label> -->
<!--           <input type="checkbox"> Remember me -->
<!--         </label> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <input type="submit" value="Save" class="btn btn-default" />
    </div>
  </div>
</form:form>