<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

<c:if test="${param.success eq true }">
	<div class="alert alert-success" align="center"><spring:message code="register.success" /></div>
</c:if>

<form:form commandName="user" cssClass="form-horizontal">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><spring:message code="register.name" /></label>
		<div class="col-sm-10">
			<form:input path="name" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label"><spring:message code="register.email" /></label>
		<div class="col-sm-10">
			<form:input path="email" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label"><spring:message code="register.password" /></label>
		<div class="col-sm-10">
			<form:password path="password" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-12">
			<input type="submit" value="<spring:message code="button.save" />" class="btn btn-lg btn-primary"/>
		</div>
	</div>
</form:form>
