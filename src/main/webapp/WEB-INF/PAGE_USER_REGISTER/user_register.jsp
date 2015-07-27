<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

<div id="pageH">
<c:if test="${param.success eq true }">
	<div class="alert alert-success" align="center"><spring:message code="register.user.success" /></div>
</c:if>
<h1 class="text-left">Register new user:</h1>
<br>
<form:form commandName="user" cssClass="form-horizontal registrationForm">
	<div class="form-group">
		<div class="col-sm-1">
			<input type="submit" value="<spring:message code="button.save" />" class="btn btn-primary"/>
		</div>
	</div>
	<div class="form-group">
		<label for="name" class="col-sm-1 control-label"><spring:message code="register.user.name" /></label>
		<div class="col-sm-12">
			<form:input path="name" class="form-control" />
			<form:errors path="name" class="text-left" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-1 control-label"><spring:message code="register.user.email" /></label>
		<div class="col-sm-12">
			<form:input path="email" class="form-control" />
			<form:errors path="email" class="text-left" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-1 control-label"><spring:message code="register.user.password" /></label>
		<div class="col-sm-12">
			<form:password path="password" class="form-control" />
			<form:errors path="password" class="text-left" />
		</div>
	</div>
	<div class="form-group">
		<label for="password_again" class="col-sm-5 control-label"><spring:message code="register.user.password.again" /></label>
		<div class="col-sm-12">
			<input type="password" id="password_again" name="password_again" class="form-control">
		</div>
	</div>
</form:form>
</div>

<script>
$(document).ready(function(){
	
	$('.registrationForm').validate(
		{
			rules: {
				name: {
					required : true,
					minlength : 3,
					maxlength : 20,
					remote : {
						url  : "<spring:url value='/user/available.html' />",
						type : "get",
						data : {
							username : function(){
								return $('#name').val();
							}
						}
					}
				},
				email: {
					required: true,
					email : true,
					minlength: 5,
					maxlength : 35
				},
				password: {
					required : true,
					minlength : 5,
					maxlength : 60
				},
				password_again: {
					required : true,
					minlength : 5,
					maxlength : 60,
					equalTo : "#password"
				}
			},
			highlight : function(element){
				$('element').closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element){
				$('element').closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages : {
				name : {
					remote : "Such username already exists"
				}
			}
		}		
	);
	
});
</script>