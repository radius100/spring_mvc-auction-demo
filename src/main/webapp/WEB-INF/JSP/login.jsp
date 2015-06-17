<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<style>
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
</style>

<form class="form-signin" action="/login" method="POST">
	<h2 class="form-signin-heading">
		<spring:message code="login.intro" />
	</h2>
	<input type="text" name="username" class="form-control" placeholder="<spring:message code="login.name" />" required autofocus> <input type="password" name="password" class="form-control"
		placeholder="<spring:message code="login.password" />" required>
	<div class="checkbox">
		<label> <input type="checkbox" value="remember-me">
		<spring:message code="login.remember_me" /></label>
	</div>
	<button class="btn btn-lg btn-primary btn-block" type="submit">
		<spring:message code="login.button_ok" />
	</button>
</form>