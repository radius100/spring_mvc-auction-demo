<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

<div id="pageH">
<div id="pageHeader" class="page-header blockPointer">
	<h1>Profile: ${principal}</h1>
</div>
<br>
<h3 id="titleMyAccountSettings" class="blockPointer">My account settings:</h3>
<br>
<div class="row" id="divMyAccountSettings" style="display: none">
	<div class="col-md-1"></div>
	<div class="col-md-8">
		<c:if test="${param.success eq true }">
			<div class="alert alert-success" align="center">
				<spring:message code="register.item.success" />
			</div>
		</c:if>
		<form:form commandName="userdetail" cssClass="form-horizontal">
			<div class="form-group">
				<div class="col-sm-12" align="left">
					<input type="submit" value="<spring:message code="button.save" />" class="btn btn-primary" />
				</div>
			</div>
			<div class="form-group">
				<label for="firstName" class="col-sm-3 control-label">First name</label>
				<div class="col-sm-6">
					<form:input path="firstName" class="form-control" value="${userdetail.firstName}" />
				</div>
			</div>
			<div class="form-group">
				<label for="secondName" class="col-sm-3 control-label">Second name</label>
				<div class="col-sm-6">
					<form:input path="secondName" class="form-control" value="${userdetail.secondName}" />
				</div>
			</div>
			<div class="form-group">
				<label for="phone" class="col-sm-3 control-label">Phone</label>
				<div class="col-sm-6">
					<form:input path="phone" class="form-control" value="${userdetail.phone}" />
				</div>
			</div>
			<div class="form-group">
				<label for="country" class="col-sm-3 control-label">Country</label>
				<div class="col-sm-6">
					<form:input path="country" class="form-control" value="${userdetail.country}" />
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-3 control-label">State</label>
				<div class="col-sm-6">
					<form:input path="state" class="form-control" value="${userdetail.state}" />
				</div>
			</div>
			<div class="form-group">
				<label for="city" class="col-sm-3 control-label">City</label>
				<div class="col-sm-6">
					<form:input path="city" class="form-control" value="${userdetail.city}" />
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-3 control-label">Address</label>
				<div class="col-sm-6">
					<form:input path="address" class="form-control" value="${userdetail.address}" />
				</div>
			</div>
		</form:form> 
	</div>
	<div class="col-md-3"></div>
</div>
<div class="row" id="divMyAccountSettings" style="display: none">
</div>
<h3 id="titleMyLotsSettings" class="blockPointer">My lots settings:</h3>
<br>
<div class="row" id="divMyLotsSettings" style="display: none">
	<div class="col-md-1"></div>
	<div class="col-md-8">
		<table id="addLineTable" class="table table-striped table-condensed">
			<c:forEach items="${myItems}" var="item">
				<tr id="line_item-${item.id}">
					<td width="25%">
						<c:choose>
							<c:when test="${item.hide eq true}">
								<button id="btnHide_item-${item.id}" class="btn btn-success btn-xs">Show</button>		
							</c:when>
							<c:otherwise>
								<button id="btnHide_item-${item.id}" class="btn btn-primary btn-xs">Hide</button>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${item.collapse eq true}">
								<button id="btnCollapse_item-${item.id}" class="btn btn-primary btn-xs">Expand</button>		
							</c:when>
							<c:otherwise>
								<button id="btnCollapse_item-${item.id}" class="btn btn-primary btn-xs">Collapse</button>
							</c:otherwise>
						</c:choose>
					</td>
					
					<td width="55%"><a href="/items/item-${item.id}.html">${item.name}</a></td>
					<td width="20%" align="right">
						<c:if test="${item.ownerDeletable eq true}">
							<button id="btnDelete_item-${item.id}" class="btn btn-danger btn-xs">Delete</button>
						</c:if>
						<input class="btn btn-primary btn-xs" id="edit_item-${item.id}" value="Edit" onclick="location.href='/item-${item.id}/edit.html'" type="button" />
					</td>
			</tr>
			</c:forEach>
			<tr id="clonable" style="display: none;">
				<td>
					<button id="btnHide_item-XXX" class="btn btn-primary btn-xs">Hide</button>
					<button id="btnCollapse_item-XXX" class="btn btn-primary btn-xs">Collapse</button>
				</td>
				<td><a href="/items/item-XXX.html" id="linkXXX">View lot detail</a></td>
				<td align="right">
					<input class="btn btn-primary btn-xs" id="edit_item-XXX" value="Edit" onclick="location.href='/item-XXX/edit.html'" type="button" />
					<button id="btnDelete_item-XXX" class="btn btn-danger btn-xs">Delete</button>
				</td>
			</tr>
			<tr id="lastLine">
				<td></td>
				<td></td>
				<td align="right"><button id="addNewItem" class="btn btn-primary btn-xs">Add new lot</button></td>
			</tr>
		</table>
	</div>
	<div class="col-md-3"></div>
</div>
<h3 id="titleTradeMonitorSettings" class="blockPointer">Trading monitor settings:</h3>
<br>
<div class="row" id="divTradeMonitorSettings" style="display: none">
	<div class="col-md-1"></div>
	<div class="col-md-8">
		<table class="table table-striped table-condensed">
			<c:forEach items="${monitoringItems}" var="item">
				<tr>
					<td width="25%">
						<c:choose>
							<c:when test="${item.hide eq true}">
								<button id="btnHide_item-${item.id}" class="btn btn-success btn-xs">Show</button>		
							</c:when>
							<c:otherwise>
								<button id="btnHide_item-${item.id}" class="btn btn-primary btn-xs">Hide</button>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${item.collapse eq true}">
								<button id="btnCollapse_item-${item.id}" class="btn btn-primary btn-xs">Expand</button>		
							</c:when>
							<c:otherwise>
								<button id="btnCollapse_item-${item.id}" class="btn btn-primary btn-xs">Collapse</button>
							</c:otherwise>
						</c:choose>
					</td>
					<td width="55%"><a href="/items/item-${item.id}.html">${item.name}</a></td>
					<td width="20%" align="right">
						<c:if test="${item.followedByCurrentUser eq true}">
							<button id="btnFollow_item-${item.id}" class="btn btn-danger btn-xs">Do not follow</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="col-md-3"></div>
</div>
</div>

<script type="text/javascript">
jQuery(document).ready(function($) {

	
	function getUrlParameter(sParam)
	{
	    var sPageURL = window.location.search.substring(1);
	    var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++) 
	    {
	        var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam) 
	        {
	            return sParameterName[1];
	        }
	    }
	}    
	
	$('#pageHeader').click(function(){
		$('#divMyLotsSettings').fadeToggle('fast');
		$('#divTradeMonitorSettings').fadeToggle('fast');
		
	});
	
	$('#pageHeader').dblclick(function(){
		$('#divMyAccountSettings').fadeToggle('fast');
		
	});

	
	$('#divMyLotsSettings').fadeIn('slow');
	$('#divTradeMonitorSettings').fadeIn('slow');
	
	$('#titleMyAccountSettings').click(function(){
		
		if( $('#divMyAccountSettings').is(':visible') )
			$('#divMyAccountSettings').fadeOut('fast');
		else 
			$('#divMyAccountSettings').fadeIn('fast');
	});
	
	
	$('#addNewItem').click(function(){
		
		$.get("/account/new-item.html",function(data,status) {
			
			if(status == 'success'){
				
				var id   = 'item-'+data;
				var line = 'line_';
				
				$("#clonable").clone(true).insertBefore("#lastLine");
				$("#addLineTable tr").last().prev().prev().attr('id', line+id).show();
				
				//parse: "btnHide_item-XXX"
				var str = $("#"+line+id+" #btnHide_item-XXX").attr("id");
				str = str.substring(0,8);
				$("#"+line+id+" #btnHide_item-XXX").attr("id",str+id);
				
				//parse: "btnCollapse_item-XXX"
				str = $("#"+line+id+" #btnCollapse_item-XXX").attr("id");
				str = str.substring(0,12);
				$("#"+line+id+" #btnCollapse_item-XXX").attr("id", str+id);
				
				//parse: "/items/item-XXX.html"
				str = $("#"+line+id+" #linkXXX").attr("href");
				var pre  = str.substring(0,7);
				var post = str.substring(15,20);
				$("#"+line+id+" #linkXXX").attr("href", pre+id+post);
				
				//parse: "location.href='/item-XXX/edit.html'"
				str = $("#"+line+id+" #edit_item-XXX").attr("onclick");
				pre  = str.substring(0,16);
				post = str.substring(24,35);
				$("#"+line+id+" #edit_item-XXX").attr("onclick", pre+id+post);

				//parse: "btnDelete_item-XXX"				
				str = $("#"+line+id+" #btnDelete_item-XXX").attr("id");
				str = str.substring(0,10);
				$("#"+line+id+" #btnDelete_item-XXX").attr("id",str+id);

			}			
							
		});
		
	});
	
	$('#titleMyLotsSettings').click(function(){
		
		if( $('#divMyLotsSettings').is(':visible') )
			$('#divMyLotsSettings').fadeOut('fast');
		else 
			$('#divMyLotsSettings').fadeIn('fast');
	});
	
	$('#titleTradeMonitorSettings').click(function(){
		
		if( $('#divTradeMonitorSettings').is(':visible') )
			$('#divTradeMonitorSettings').fadeOut('fast');
		else 
			$('#divTradeMonitorSettings').fadeIn('fast');
	});
	
	
	$("button[id*='btnFollow_']").click(function(){
		
		var str = $(this).attr('id');
		str = str.slice(10);
		
		var button_id = $(this).attr("id");
		
		$.get("/items/"+str+"/follow.html",function(data,status) { 
			if (data == 'unfollow') 
				$('#'+button_id).hide();
		});
		
	});

	
	$("button[id*='btnCollapse_']").click(function(){
		
		var str = $(this).attr('id');
		str = str.slice(12);
		
		var button_id = $(this).attr("id");
		
		$.get("/account/"+str+"/collapse.html",function(data,status) {
			
			if(data == 'Collapsed'){

				$("#"+button_id).html('Expand');
			}
				
			else if(data == 'Expanded'){
				
				$("#"+button_id).html('Collapse');
			}

		});
	});


	$("button[id*='btnHide_']").click(function(){
		
		var str = $(this).attr('id');
		str = str.slice(8);
		
		var button_id = $(this).attr("id");
		
		$.get("/account/"+str+"/hide.html",function(data,status) {
			
			if(data == 'Hidden'){

				$("#"+button_id).html('Show');
				$("#"+button_id).addClass('btn-success');
				$("#"+button_id).removeClass('btn-primary');
			}
				
			else if(data == 'Show'){
				
				$("#"+button_id).html('Hide');
				$("#"+button_id).addClass('btn-primary');
				$("#"+button_id).removeClass('btn-success');
			}

		});
		
	});
	
	$("button[id*='btnDelete_']").click(function(){
		
		var str = $(this).attr('id');
		str = str.slice(10);
		
		$.get("/account/"+str+"/delete.html",function(data,status) {
			
			if(data == 'Delete')
				$("#line_"+str).remove();
	
		});
	});
	
});
</script>