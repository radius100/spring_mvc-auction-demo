<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

<div id="pageH">
	<div id="pageHeader" class="page-header blockPointer">
		<h1>${item.name}:<br>
		<small> ${item.descr}</small></h1>
	</div>
	<br>
	<div class="container">
    <!--  
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="uploadFile" method="post">
        <div class="form-group"><h3>New photo</h3></div>
        <div class="form-group">Photo: <input type="file" name="file"></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
    </form>
    -->
    <!-- 
    <form method="POST" action="uploadFile" enctype="multipart/form-data">
        File to upload: <input type="file" name="file"><br> 
        <input type="submit" value="Upload"> 
    </form>
     -->
	</div>
	<br>
	
	<h3 id="titleImages" class="blockPointer">Images:</h3>
	<br>
	<div class="row" id="divImages" style="display: none">
		<div class="col-md-1"></div>
		<div class="col-md-10">
       		<img src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRnm256OocmHn9U_j0vOfJ1_b4K83OptMe82pUBEC4LVDw3CKdB7g" width="100%"/>				
		</div>
		<div class="col-md-1"></div>
	</div>
	<h3 id="titleDescripton" class="blockPointer">Description:</h3>
	<br>
	<div class="row" id="divDescr" style="display: none">
		<div class="col-md-2"></div>
		<div class="col-md-9">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-11">
				<!-- Button follow -->
					<c:if test="${item.publishedByCurrentUser eq false}">
						<security:authorize access="isAuthenticated()">
						<c:choose>
							<c:when test="${item.followedByCurrentUser eq true}">
								<td><button id="btnFollow" class="btn btn-success">Follow</button></td>
							</c:when>
							<c:otherwise>
								<td><button id="btnFollow" class="btn btn-primary">Follow</button></td>
							</c:otherwise>
						</c:choose>
						<br>
						</security:authorize>
					</c:if>
					<br>
					<br>
					<table class="table borderless">
						<col width="35%">
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
									<td align="right"><spring:message code="showitem.status.active" /></td>
								</tr>
							</c:when>
							<c:when test="${item.block eq true}">
								<tr class="info">
									<td><spring:message code="showitem.status" /></td>
									<td><spring:message code="showitem.status.block" /></td>
								</tr>
							</c:when>
						</c:choose>
						<tr>
							<c:choose>
								<c:when test="${item.preTrading eq true}">
									<td><spring:message code="countdown.start_in" /></td>
								</c:when>
								<c:otherwise>
									<td><spring:message code="countdown.finish_in" /></td>
								</c:otherwise>
							</c:choose>
							<td align="right">
								<span id="countDownColor" class="countdownContainer"></span>
							</td>
							
						</tr>
						<tr>
							<td><spring:message code="showitem.start_price" /></td>
							<td align="right">$ ${item.startAmount}</td>
						</tr>
						<tr>
							<td><spring:message code="showitem.current_price" /></td>
							<td align="right">$ ${item.currentAmount}</td>
						</tr>
						</tbody>
					</table>
					<h3 id="titleFollowers" class="blockPointer"><spring:message code="showitem.followers" /></h3>
					<table id="tableFollowers" class="table borderless" style="display: none">
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
					<h3 id="titleTraders" class="blockPointer"><spring:message code="showitem.traders" /></h3>
					<table id="tableTraders" class="table borderless" style="display: none">
						<col width="30%">
						<tbody>
							<c:if test="${item.traders eq '[]'}">
								<tr>
									<td></td>
									<td>none</td>
								</tr>
							</c:if>
							<c:forEach items="${item.traders}" var="trader">
								<tr>
									<td></td>
									<td>${trader.name}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<h3 id="titlePublisher" class="blockPointer"><spring:message code="showitem.publisher" /></h3>
					<table id="tablePublisher" class="table borderless">
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
			<div class="col-md-2"></div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-11">
						<c:if test="${item.publishedByCurrentUser eq false}">
							<security:authorize access="! isAuthenticated()">
								<button id="btnRefresh" class="btn btn-primary">Refresh</button>					
							</security:authorize>
							<security:authorize access="isAuthenticated()">					
								<div class="form form-inline">
											<input type="text" id="inputAmount" class="form-control" style="text-align: right">
											<button id="btnRate" class="btn btn-success">Ставка</button>
											<button id="btnRefresh" class="btn btn-primary">Refresh</button>
								</div>
								
							</security:authorize>
						</c:if>
						<br>
						<br>
						<table class="table borderless" id="dyntable">
							<thead>
        						<tr id="appendPointLine">
        							<th align="left">User</th>
            						<th align="center">Amount</th>
            						<th align="right">Date</th>
            						<th align="right">Time</th>
        						</tr>
    						</thead>
						</table>
						<br>
						<br>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
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
		
		$(function() {
		    
			$.get("/items/item-${item.id}/countdown.html",function(data,status) { 

				data = 'Jul 29, 2015 09:00:00';
				
				if(data.charAt(0) == '!'){
					
					var str = data.substring(1,data.length);
					$('.countdownContainer').text(str);
				}
					
				
				else
					$('.countdownContainer').countdown({
			        	date: data
			    	});
			
			});
			
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
		
		$('#titleFollowers').click(function(){
			$('#tableFollowers').fadeToggle('fast');			
		});
		
		$('#titleTraders').click(function(){
			$('#tableTraders').fadeToggle('fast');			
		});
		
		$('#titlePublisher').click(function(){
			$('#tablePublisher').fadeToggle('fast');			
		});

		
		$("#btnFollow").click(function() { 
			
			$.get("/items/item-${item.id}/follow.html",function(data,status) { 
				
				if (data == 'follow')  
					$('#btnFollow').removeClass("btn-primary").addClass("btn-success");
				
				else if (data == 'unfollow') 
					$('#btnFollow').removeClass("btn-success").addClass("btn-primary");
				
				else if (data == 'fail_login') 
					OnLoginFail();
			
			});
		});
		
		refreshTableAndRate();
		setInterval(refreshTableAndRate, 7000);
		$("#amount").ready(RateAdvs);
		
		function refreshTable(){
			
			$.getJSON("/items/item-${item.id}/tradepool.json", function(json){
				
				if( json != null ){
				
					var output="";
		
					$.each(json, function(i,item){
					
						output+="<tr class=\"tempLine\" style=\"display:none\">"
								+"<td>"+item.User+"</td>"
								+"<td>"+item.Amount+"</td>"
								+"<td>"+item.Date+"</td>"
								+"<td>"+item.Time+"</td>"
								+"</tr>"

					});

				}
				
				if( output.length > 0 ){
					$('.tempLine').remove();
					$('#appendPointLine').after(output);
					$('.tempLine').fadeToggle('fast');
				}
				
			});

		}
		
		
		$('#btnRefresh').click(function(){
			
			refreshTable();
						
		});		
		
		
		function refreshTableAndRate(){
			
			refreshTable();
			RateAdvs();
			
		}
		

		function RateAdvs() {
			
			$.get("/items/item-${item.id}/rate-adv.html",function(data,status) {
				
				if( data.localeCompare('fail_login') == 0 )
					OnLoginFail();
					
				else 
					$('#inputAmount').val(data);
					
			});
		}
		
			
		function OnLoginFail() {
		
			$('#btnRate').hide();
		
			$('#inputAmount').hide();
			$('#labelAmount').hide();
			
			$('#btnFollow').hide();
			
		}
		
		
		$("#btnRate").click(function() {
			
			$.post("/items/item-${item.id}/rate.html",
				{ 
					amount: $('#inputAmount').val() 
				},
				
				function(data,status){ 
				
					if( data.localeCompare("ok") == 0 )
						refreshTableAndRate();
					
					else if(data.localeCompare("fail_login") == 0)
						OnLoginFail();
					
					else if(data.localeCompare("fail") == 0)
						RateAdvs();
					
			});
		});
			
	});
</script>