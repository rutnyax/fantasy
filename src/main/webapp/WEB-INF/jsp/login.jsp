<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
<!--
.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="name"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
-->
</style>

<form class="form-signin" name="f" action="/fantasy-football/login"
	method="POST">
	<h2 class="form-signin-heading">Please sign in</h2>
	<label for="inputName" class="sr-only">Name</label> <input type="text"
		name="username" id="inputName" class="form-control" placeholder="Name"
		required autofocus />
	<!-- 	<label for="inputEmail" class="sr-only">Email</label> -->
	<!-- 	<input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email Address" required autofocus /> -->
	<label for="inputPassword" class="sr-only">Password</label> <input
		type="password" name="password" id="inputPassword"
		class="form-control" placeholder="Password" required />
	<!-- 	<div class="checkbox"> -->
	<!-- 		<label> <input type="checkbox" value="remember-me" /> -->
	<!-- 			Remember me -->
	<!-- 		</label> -->
	<!-- 	</div> -->
	<input name="submit" type="submit" value="Sign in"
		class="btn btn-lg btn-primary btn-block" />
	<!-- 	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button> -->
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>