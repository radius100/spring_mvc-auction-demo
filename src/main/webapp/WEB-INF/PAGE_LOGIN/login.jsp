<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

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
