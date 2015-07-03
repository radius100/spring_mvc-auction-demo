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
				
				<tbody>
					<tr>
						<security:authorize access="isAuthenticated()">					
							<td></td>
							<td align="right">
								<input type="text" class="form-control" id="amount">
								<button id="doStake" class="btn btn-primary">Ставка</button>
							</td>
						</security:authorize>
					</tr>
					<tr><td> </td></tr>
					<tr>
						<td></td>
						<td>
						<table id="dyntable" class="table borderless" data-toggle="table" data-url="/items/item-${item.id}/tradepool.json" data-cache="false">
    						<thead>
        						<tr>
            						<th data-field="User">User</th>
            						<th data-field="Amount">Amount</th>
            						<th data-field="Date">Date</th>
            						<th data-field="Time">Time</th>
        						</tr>
    						</thead>
						</table>
						</td>
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
		$.get("/items/item-${item.id}/follow.html",function(data,status) { 
			if (data == 'Follow')  
				$('#follow').removeClass("btn-primary").removeClass("disabled").addClass("btn-success").text("Follow");
			else if (data == 'Unfollow') 
				$('#follow').removeClass("btn-success").removeClass("disabled").addClass("btn-primary").text("Follow");
			else if (data == 'Login first') 
				$('#follow').removeClass("btn-success").addClass("btn-primary").addClass("disabled").text("Follow");
			alert("Data: " + data + "\nStatus: " + status);  
		});
	});
	
	$("#doStake").click(function() {
		
		$.post("/items/item-${item.id}/rate.html",
			{ amount: $('#amount').val() },
			function(data,status){ 
				//alert("Data: " + data + "\nStatus: " + status);
		});
		
		$('#dyntable').bootstrapTable('refresh', {
            url: '/items/item-${item.id}/tradepool.json'
        });
	
	});
	
	$("#amount").ready(function() {
		
		$.get("/items/item-${item.id}/rate-adv.html",function(data,status) {
			$('#amount').val(data);
		});
		
	});
	
 });

</script>