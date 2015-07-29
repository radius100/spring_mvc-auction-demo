<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

<div id="pageH">
	<div id="pageHeader" class="page-header blockPointer">
		<h1>Edit:</h1>
	</div>
	<br>
	<div id="item-${item.id}"></div>
	<form:form commandName="item" cssClass="form-horizontal">
		<div class="form-group">
			<div class="col-sm-12" align="left">
				<input type="submit" value="<spring:message code="button.save" />" class="btn btn-primary" />
			</div>
		</div>
		<br>
		<h3 id="titleImages" class="blockPointer">Images:</h3>
		<br>
		<div class="row" id="divImages" style="display: none">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				Images
			</div>
			<div class="col-md-1"></div>
		</div>
		<h3 id="titleDescripton" class="blockPointer">Description:</h3>
		<br>
		<div class="row" id="divDescr" style="display: none">
			<div class="col-md-1"></div>
			<div class="col-md-10">

				<div class="form-group">
					<label for="fullDescr" class="col-sm-2 control-label"><spring:message code="register.item.fullDescr" /></label>
					<div class="col-sm-6">
						<form:textarea path="fullDescr" class="custom-control form-control" rows="10" style="resize: none" value="${item.fullDescr}"></form:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-1"></div>
		<h3 id="titleMandate" class="blockPointer">Mandate:</h3>
		<br>
		<div class="row" id="divMandate" style="display: none">
			<div class="col-md-1"></div>
			<div class="col-md-10">

				<div class="form-group">
					<label for="name" class="col-sm-2 control-label"><spring:message code="register.item.name" /></label>
					<div class="col-sm-4">
						<form:input path="name" class="form-control" value="${item.name}" />
					</div>
				</div>
				<div class="form-group">
					<label for="descr" class="col-sm-2 control-label"><spring:message code="register.item.descr" /></label>
					<div class="col-sm-4">
						<form:input path="descr" class="form-control" value="${item.descr}" />
					</div>
				</div>
				<div class="form-group">
					<label for="startAmount" class="col-sm-2 control-label"><spring:message code="register.item.startAmount" /></label>
					<div class="col-sm-4">
						<div class="input-group">
							<span class="input-group-addon">$</span>
							<form:input path="startAmount" class="form-control" value="${item.startAmount}" />
							<span class="input-group-addon">.00</span>
						</div>
						<hr>
					</div>
				</div>
				<div class="form-group">
					<label for="publishDate" class="col-sm-2 control-label"><spring:message code="register.item.publishDate" /></label>
					<div class="col-sm-4">
						<div class="input-group" id="pickerPublishDate">
							<!-- 
							<form:input type="text" path="publishDate" class="form-control" value="${formatPublishDate}"/>
							 -->
							<form:input path="publishDateAsString" class="form-control datetimepickerInput" value="${formatPublishDate}" />
							<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label id="notNessesary" for="startDate" class="col-sm-2 control-label"><spring:message code="register.item.startDate" /></label>
					<div class="col-sm-4">
						<div class="input-group" id="pickerStartDate">
							<form:input type="text" path="startDateAsString" class="form-control" value="${formatStartDate}" />
							<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="finishDate" class="col-sm-2 control-label"><spring:message code="register.item.finishDate" /></label>
					<div class="col-sm-4">
						<div class="input-group" id="pickerFinishDate">
							<form:input type="text" path="finishDateAsString" class="form-control" value="${formatFinishDate}" />
							<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-1"></div>
		</div>
	</form:form>
	<button id="btnAA">Send post</button>
</div>

<script type="text/javascript">
	jQuery(document).ready(function($) {

		$('#startWin').click(function() {

			$('#startWin').hide();
			$('#switchWin').show();

		});

		$('#switchWin').click(function() {

			$('#switchWin').hide();
			$('#startWin').show();

		});

		$('#pageHeader').click(function() {
			$('#divMandate').fadeToggle('fast');
			$('#divDescr').fadeToggle('fast');

		});

		$('#pageHeader').dblclick(function() {
			$('#divImages').fadeToggle('fast');

		});

		$('#divMandate').fadeIn('slow');
		//$('#divDescr').fadeIn('slow');

		$('#titleImages').click(function() {
			$('#divImages').fadeToggle('fast');
		});

		$('#titleMandate').click(function() {
			$('#divMandate').fadeToggle('fast');
		});

		$('#titleDescripton').click(function() {
			$('#divDescr').fadeToggle('fast');
		});

		/*
		 * Добавить чтение локали из кук
		 *	
		 * Проверки:
		 *	PublisDate >= now, можно менять пока не опубликовано.. т.е. Прошел срок указанной PublishDate 
		 *  неделя >= (StartDate - PublishDate) >= 1 час
		 *	(FinishDate - StartDate) <= 30 дней
		 *
		 */
		var loc = 'en';
		 
		 
		 
		 /*
		 publish minDate - now
		 		 maxDate - now + mounth
		 start	 minDate - now
		 		 maxDate - minDate + 2 weaks
		 finish  minDate - start minDate + 1 day
		 		 maxDate - start maxDate + mounth
		 
		 */
		 
		 /*
		 1. на старте получить JSON с мин и макс значаниеми для каждого поля
		 2. обновить опции по мин макс значениям для каждого поля
		 3. если пользователь покинул поле проверить на корректность интервалы
		 */
		 
		 function DateTimeAdviseAndCheck(){
			 
			
			 
		 }
		 /* 
		 	$("#publishDateAsString").prop('disabled', true);
		 	$("#startDateAsString").prop('disabled', true);
		 	$("#finishDateAsString").prop('disabled', true);
		 */
		 $("#btnAA").click(function(){
			    
			 	var id=$("div[id*='item-']").attr('id');
			 	var path='/items/'+id+'/datetime.json';
			 				 	
			 	$.post(path,
			    {
			    	PublishDateInputBox: $('#publishDateAsString').val(),
			    	StartDateInputBox: $('#startDateAsString').val(),
			    	FinishDateInputBox: $('#finishDateAsString').val(),
			    },
			    function(data, status){
			        
			    	$('#pickerPublishDate').data("DateTimePicker").minDate(data.PublishDateMin);
					$('#pickerPublishDate').data("DateTimePicker").maxDate(data.PublishDateMax);
					$('#publishDateAsString').val(data.PublishDate);
					
			        $('#pickerStartDate').data("DateTimePicker").minDate(data.StartDateMin);
				 	$('#pickerStartDate').data("DateTimePicker").maxDate(data.StartDateMax);
				 	$('#startDateAsString').val(data.StartDate)
				 	
				 	$('#pickerFinishDate').data("DateTimePicker").minDate(data.FinishDateMin);
				 	$('#pickerFinishDate').data("DateTimePicker").maxDate(data.FinishDateMax);
				 	$('#finishDateAsString').val(data.FinishDate) 
					 
				//	$('#pickerPublishDate').data("DateTimePicker").minDate('30-Jul-2015 01:00');
			
			    });
			});

		 
		$('#pickerPublishDate').datetimepicker({
			viewMode : 'days',
			format : 'DD-MMM-YYYY HH:mm',
			//maxDate : '30-Jul-2015 10:10',
			//minDate : 
			locale : loc
		});
	
		$('#pickerStartDate').datetimepicker({
			viewMode : 'days',
			format : 'DD-MMM-YYYY HH:mm',
			minDate : '29-Jul-2015',
			locale : loc

		});
		
		$('#pickerFinishDate').datetimepicker({
			viewMode : 'days',
			format : 'DD-MMM-YYYY HH:mm',
			locale : loc
		});
		
	});
</script>