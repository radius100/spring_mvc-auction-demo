<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

<script type="text/javascript">
	$(function() {
		$('#pickerPublishDate').datetimepicker({
			viewMode : 'days'
		});
		$('#pickerStartDate').datetimepicker({
			viewMode : 'days'
		});
		$('#pickerFinishDate').datetimepicker({
			viewMode : 'days'
		});

	});
</script>

<h1>New Lot registration:</h1>
<br />
<br />

<c:if test="${param.success eq true }">
	<div class="alert alert-success" align="center">
		<spring:message code="register.item.success" />
	</div>
</c:if>

<form:form commandName="item" cssClass="form-horizontal">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><spring:message code="register.item.name" /></label>
		<div class="col-sm-4">
			<form:input path="name" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="descr" class="col-sm-2 control-label"><spring:message code="register.item.descr" /></label>
		<div class="col-sm-4">
			<form:input path="descr" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="startAmount" class="col-sm-2 control-label"><spring:message code="register.item.startAmount" /></label>
		<div class="col-sm-4">
			<div class="input-group">
				<span class="input-group-addon">$</span>
				<form:input path="startAmount" class="form-control" />
				<span class="input-group-addon">.00</span>
			</div>
			<hr>
		</div>
	</div>
	<div class="form-group">
		<label for="publishDate" class="col-sm-2 control-label"><spring:message code="register.item.publishDate" /></label>
		<div class="col-sm-4">
			<div class="input-group" id="pickerPublishDate">
				<form:input type="text" path="publishDate" class="form-control" />
				<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="startDate" class="col-sm-2 control-label"><spring:message code="register.item.startDate" /></label>
		<div class="col-sm-4">
			<div class="input-group" id="pickerStartDate">
				<form:input type="text" path="startDate" class="form-control" />
				<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="finishDate" class="col-sm-2 control-label"><spring:message code="register.item.finishDate" /></label>
		<div class="col-sm-4">
			<div class="input-group" id="pickerFinishDate">
				<form:input type="text" path="finishDate" class="form-control" />
				<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-12" align="center">
			<input type="submit" value="<spring:message code="button.save" />" class="btn btn-primary" />
		</div>
	</div>
</form:form>
