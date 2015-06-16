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

<!-- 

body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}


<html><head><title>Login Page</title></head><body onload='document.f.username.focus();'>
<h3>Login with Username and Password</h3><form name='f' action='/login' method='POST'>
<table>
	<tr><td>User:</td><td><input type='text' name='username' value=''></td></tr>
	<tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
	<tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
	<input name="_csrf" type="hidden" value="f5601fd2-711f-4079-bbba-686c4787d768" />
</table>
</form></body></html>

 -->
