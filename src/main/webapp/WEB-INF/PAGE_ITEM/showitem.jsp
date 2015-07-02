<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

${itemJson}

<div class="bodyh">
	<br />
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Описание</a></li>
		<li role="presentation"><a href="#description" aria-controls="description" role="tab" data-toggle="tab">Торги online!</a></li>
		<li role="presentation"><a href="#images" aria-controls="images" role="tab" data-toggle="tab">Images</a></li>
	</ul>
	<!-- Tab panes -->
	<br />
	<div class="tab-content" id="tabscontent">
		<div role="tabpanel" class="tab-pane active" id="home">
			<input type="submit" class="btn btn-primary" id="doStake" value="Follow">
			<table class="table borderless">
				<col width="3%">
				<tbody>
					<tr>
						<td></td>
						<td>
							<h3>${item.name}</h3>
							<p>${item.descr}</p> <br />
							<table class="table table-condensed">
								<col width="30%">
								<tbody>
									<tr>
										<td><spring:message code="showitem.descr" /></td>
										<td align="justify">${item.fullDescr}</td>
									</tr>
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
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div role="tabpanel" class="tab-pane" id="description">
			<br /> <br />

			<table class="table borderless">
				<col width="3%">
				<col width="50%">
				<tbody>
					<tr>
						<td></td>
						<td><input type="text" class="form-control amountInput" id="amount">
						<td><input type="submit" class="btn btn-primary" id="doStake" value="Ваша ставка"></td>
						<security:authorize access="isAuthenticated()">
							<c:choose>
								<c:when test="${item.followedByCurrentUser eq true}">
									<td><button id="follow" class="btn btn-success">Unfollow</button></td>
								</c:when>
								<c:otherwise>
									<td><button id="follow" class="btn btn-primary">Follow</button></td>
								</c:otherwise>
							</c:choose>
						</security:authorize>
					</tr>
					<tr>
						<table class="table borderless">
							<thead>
								<tr>
									<th>Пользователь</th>
									<th>Ставка</th>
									<th>Дата</th>
									<th>Время</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${item.tradePools}" var="tradepool">
									<tr>
										<td>${tradepool.user.name}</td>
										<td>${tradepool.amount}</td>
										<td>${tradepool.messageDate}</td>
										<td>${tradepool.messageTime}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</tr>
				</tbody>
			</table>
		</div>
		<div role="tabpanel" class="tab-pane" id="images">
			<div id="banner-fade">
				<img src="/resources/1.jpg" />
				<div id="my-slideshow">
					<ul class="bjqs">
						<li><img src="/resources/1.jpg" /></li>
						<li><img src="/resources/2.jpg" /></li>
						<li><img src="/resources/3.jpg" /></li>
					</ul>
				</div>
			</div>
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
	
	$("#follow").click(function() { 
		$.get("/items/item-3/follow.html",function(data,status) { 
			if (data == 'Follow') { 
				$('#follow').removeClass("btn-primary").addClass("btn-success").text("Unfollow");
				//$("#follow").text("Unfollow");
			}
			else if (data == 'Unfollow') 
				$('#follow').removeClass("btn-success").addClass("btn-primary").text("Follow");
				//$("#follow").text("Follow");
			else if (data == 'Login first') 
				$('#follow').hide();
			alert("Data: " + data + "\nStatus: " + status);  

		});
	});
  });
</script>