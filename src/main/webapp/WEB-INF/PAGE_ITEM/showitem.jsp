<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

<div id="pageH">
	<div id="pageHeader" class="page-header blockPointer">
		<h1>${item.name}:<br>
		<small> ${item.descr}</small></h1>
	</div>
	<br>

	<h3 id="titleImages" class="blockPointer">Images:</h3>
	<br>
	<div class="row" id="divImages" style="display: none">
		<div class="col-md-1"></div>
		<div class="col-md-10">

       		<img src="http://placehold.it/650x450&text=Galaxy%20S5" width="100%"/>				
				<br>	
				
		</div>
		<div class="col-md-1"></div>
	</div>
	<h3 id="titleDescripton" class="blockPointer">Description:</h3>
	<br>
	<div class="row" id="divDescr" style="display: none">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-11">
				<!-- Button follow -->
					<c:if test="${item.publishedByCurrentUser eq false}">
						<security:authorize access="isAuthenticated()">
						<c:choose>
							<c:when test="${item.followedByCurrentUser eq true}">
								<td><button id="follow" class="btn btn-success">Follow</button></td>
							</c:when>
							<c:otherwise>
								<td><button id="follow" class="btn btn-primary">Follow</button></td>
							</c:otherwise>
						</c:choose>
						</security:authorize>
					</c:if>
					<p></p> <br>
					<table class="table table-condensed">
						<col width="30%">
						<tbody>
		<!-- 
									<tr>
										<td><spring:message code="showitem.descr" /></td>
										<td align="justify">${item.fullDescr}</td>
									</tr>
		 -->
						<c:choose>
							<c:when test="${item.active eq true}">
								<tr class="active">
									<td><spring:message code="showitem.status" /></td>
									<td><spring:message code="showitem.status.active" /></td>
								</tr>
							</c:when>
							<c:when test="${item.block eq true}">
								<tr class="info">
									<td><spring:message code="showitem.status" /></td>
									<td><spring:message code="showitem.status.block" /></td>
								</tr>
							</c:when>
							<c:when test="${item.sell eq true}">
								<tr class="success">
									<td><spring:message code="showitem.status" /></td>
									<td><spring:message code="showitem.status.sell" /></td>
								</tr>
							</c:when>
						</c:choose>
						<tr>
							<td><spring:message code="showitem.start_in" /></td>
							<td>добавить if 20 min</td>
						</tr>
						<tr>
							<td><spring:message code="showitem.start_price" /></td>
							<td>$ ${item.startAmount}</td>
						</tr>
						<tr>
							<td><spring:message code="showitem.current_price" /></td>
							<td>$ ${item.currentAmount}</td>
						</tr>
						</tbody>
					</table>

							<h3>
								<spring:message code="showitem.followers" />
							</h3>
							<table class="table borderless">
								<col width="30%">
								<tbody>
									<c:forEach items="${item.followers}" var="follower">
										<tr>
											<td></td>
											<td>${follower.name}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<h3>
								<spring:message code="showitem.traders" />
							</h3>
							<table class="table borderless">
								<col width="30%">
								<tbody>
									<c:forEach items="${item.traders}" var="trader">
										<tr>
											<td></td>
											<td>${trader.name}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<h3>
								<spring:message code="showitem.publisher" />
							</h3>
							<table class="table borderless">
								<col width="30%">
								<tbody>
									<tr>
										<td></td>
										<td><a href="#">${item.publisher.name}</a></td>
									</tr>
								</tbody>
							</table>


				</div>
			</div>
			
			<div class="col-md-1"></div>
		</div>
		</div>

		<h3 id="titleTradePool" class="blockPointer">Trade pool:</h3>
		<br>
		<div class="row" id="divTradePool" style="display: none">
			<div class="col-md-1"></div>
			<div class="col-md-10">
			Trade Pool

			<div class="col-md-1"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
	jQuery(document).ready(function($) {

		$('#banner-fade').bjqs({
			animtype : 'slide',
			'height' : 250,
			'width' : 300,
			'randomstart' : true,
			'animspeed' : 7000,
			'responsive' : true,
			'nexttext' : '>>>',
			'prevtext' : '<<<',
			'showmarkers' : true
		});
		
		$('#startWin').click(function() {

			$('#startWin').hide();
			$('#switchWin').show();

		});

		$('#switchWin').click(function() {

			$('#switchWin').hide();
			$('#startWin').show();

		});

		$('#pageHeader').click(function() {
			$('#divTradePool').fadeToggle('fast');
			$('#divDescr').fadeToggle('fast');

		});

		$('#pageHeader').dblclick(function() {
			$('#divImages').fadeToggle('fast');

		});

		$('#divTradePool').fadeIn('slow');

		$('#titleImages').click(function() {
			$('#divImages').fadeToggle('fast');
		});

		$('#titleTradePool').click(function() {
			$('#divTradePool').fadeToggle('fast');
		});

		$('#titleDescripton').click(function() {
			$('#divDescr').fadeToggle('fast');
		});

		/*
		 * Добавить чтение локали из кук
		 *	
		 * Проверки:
		 *	Заполненные поля являются датами и временем 
		 *  PublisDate >= now, можно менять пока не опубликовано.. т.е. Прошел срок указанной PublishDate 
		 *  неделя >= (StartDate - PublishDate) >= 1 час
		 *	(FinishDate - StartDate) <= 30 дней
		 *
		 */
		var loc = 'en';

		$('#pickerPublishDate').datetimepicker({
			viewMode : 'days',
			format : 'DD-MMM-YYYY HH:mm',
			locale : loc
		});
		$('#pickerStartDate').datetimepicker({
			viewMode : 'days',
			format : 'DD-MMM-YYYY HH:mm',
			locale : loc

		});
		$('#pickerFinishDate').datetimepicker({
			viewMode : 'days',
			format : 'DD-MMM-YYYY HH:mm',
			locale : loc
		});

	});
</script>