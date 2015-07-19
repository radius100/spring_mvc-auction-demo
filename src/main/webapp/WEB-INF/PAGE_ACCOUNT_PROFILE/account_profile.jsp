<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<div class="page-header">
	<h1>Profile:</h1>
</div>
<br>
<h3 id="titleMyLotsSettings">My lots settings:</h3>
<br>
<div class="row" id="divMyLotsSettings" style="display: none">
	<div class="col-md-1"></div>
	<div class="col-md-8">
		<table class="table table-striped table-condensed">
			<c:forEach items="${myItems}" var="item">
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
						<!--  
						<button id="edit_Item-${item.id}" class="btn btn-primary btn-xs">Edit</button>
						-->
						<input class="btn btn-primary btn-xs" id="edit_Item-${item.id}" value="Edit" onclick="location.href='/item-${item.id}/edit.html'" type="button" />
						<button id="delete_Item-${item.id}" class="btn btn-danger btn-xs">Delete</button>
					</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="col-md-3"></div>
</div>
<h3 id="titleTradeMonitorSettings">Trading monitor settings:</h3>
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
<br>
<br>
<br>
<br>
<script type="text/javascript">
jQuery(document).ready(function($) {

	$('#divMyLotsSettings').fadeIn('slow');
	$('#divTradeMonitorSettings').fadeIn('slow');
	
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
});
</script>







