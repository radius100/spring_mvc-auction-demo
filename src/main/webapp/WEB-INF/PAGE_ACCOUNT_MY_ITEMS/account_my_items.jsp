<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>
<button id="btnUpdateAll" class="btn btn-primary btn-sm">Refresh All</button>
<br>
<br>
<h1>Online trading monitor:</h1>
<br>
<br>
<h2>Trade pools:</h2>
<c:forEach items="${tables}" var="table">
	<c:if test="${table.type eq 'Trade' }">
		<div id="block_${table.itemId}">
			<div class="row">
				<div class="col-md-10">	
					<a href="/items/${table.itemId}.html#trade">${table.name}</a>
					<br>
					<table id="table_${table.itemId}" class="table table-bordered table-striped table-condensed" style="display: none">
						<tr id="table_head_${table.itemId}">
							<th width="25%">User</th>
							<th width="25%">Amount</th>
							<th width="25%">Date</th>
							<th width="25%">Time</th>
						</tr>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<button id="btnUpdate_${table.itemId}" class="btn btn-xs btn-primary">Refresh</button>
					<!-- collapse -->
					<c:choose>
						<c:when test="${table.collapsed eq true}">
							<button id="btnCollapse_${table.itemId}" class="btn btn-xs btn-primary _collapsed">Expand</button>
						</c:when>
						<c:otherwise>
							<button id="btnCollapse_${table.itemId}" class="btn btn-xs btn-primary _expanded">Collapse</button>
						</c:otherwise>
					</c:choose>							
					<button id="btnHide_${table.itemId}" class="btn btn-xs btn-primary">Hide</button>
				</div>
			</div>
			<br>
			<br>
		</div>
	</c:if>
</c:forEach>
<h2>Follows:</h2>
<c:forEach items="${tables}" var="table">
	<c:if test="${table.type eq 'Follow' }">
		<div id="block_${table.itemId}">
			<div class="row">
				<div class="col-md-10">	
					<a href="/items/${table.itemId}.html#trade">${table.name}</a>
					<br>
					<table id="table_${table.itemId}" class="table table-bordered table-striped table-condensed" style="display: none">
						<tr id="table_head_${table.itemId}">
							<th width="25%">User</th>
							<th width="25%">Amount</th>
							<th width="25%">Date</th>
							<th width="25%">Time</th>
						</tr>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<button id="btnUpdate_${table.itemId}" class="btn btn-xs btn-primary">Refresh</button>
					<!-- collapse -->
					<c:choose>
						<c:when test="${table.collapsed eq true}">
							<button id="btnCollapse_${table.itemId}" class="btn btn-xs btn-primary _collapsed">Expand</button>
						</c:when>
						<c:otherwise>
							<button id="btnCollapse_${table.itemId}" class="btn btn-xs btn-primary _expanded">Collapse</button>
						</c:otherwise>
					</c:choose>							
					<button id="btnHide_${table.itemId}" class="btn btn-xs btn-primary">Hide</button>
				</div>
			</div>
			<br>
			<br>
		</div>
	</c:if>
</c:forEach>

<script type="text/javascript">

jQuery(document).ready(function($) {

	UpdateTables();
	
	setInterval(UpdateTables, 300000);
	
	function UpdateTables() {
		
		$.getJSON("/account-info.json", function(listItems){
		
			if(listItems != null){

				$('table').fadeOut("fast");
				
				$(".all").remove();
			
				$.each(listItems, function(i,itemInfo){
				
					$.getJSON("/items/"+itemInfo.Id+"/tradepool.json", function(json){
					
						if( json != null ){
						
							var output="";
							collapse_line = "first_line_"+itemInfo.Id;
				
							$.each(json, function(i,item){
							
								if( i == 1 )
									collapse_line = "collapse_line_"+itemInfo.Id; 
								
								output+="<tr class=\"all all_"+itemInfo.Id
										+" "+collapse_line+"\" style=\"display:none\">"
										+"<td>"+item.User+"</td>"
										+"<td>"+item.Amount+"</td>"
										+"<td>"+item.Date+"</td>"
										+"<td>"+item.Time+"</td>"
										+"</tr>"

							});

							$("#table_head_"+itemInfo.Id).after(output);

							if(itemInfo.Collapsed == true){
								//показать только первую строку				
								$(".first_line_"+itemInfo.Id).fadeIn("fast");
							}
								
							else{
								//показать все строки				
								$(".all_"+itemInfo.Id).fadeIn("fast");
							}	
							
							$('table').fadeIn("slow");
						}
					});
				});
			}
		});
	}

	
	$('#btnUpdateAll').click(function(){
		UpdateTables();
	});


	$("button[id*='btnUpdate_']").click(function(){
	
		var str = $(this).attr('id');
		str = str.slice(10);
		
		$.getJSON("/items/"+str+"/tradepool.json", function(json){
			
			if( json != null ){
			
				var output = "";
				var collapse_line = "first_line_"+str;
				
				$.each(json, function(i,item){
					
					if( i == 1 )
						collapse_line = "collapse_line_"+str; 
					
					output+="<tr class=\"all all_"+str+" "
							+collapse_line+" \" style=\"display:none\">"
							+"<td>"+item.User+"</td>"
							+"<td>"+item.Amount+"</td>"
							+"<td>"+item.Date+"</td>"
							+"<td>"+item.Time+"</td>"
							+"</tr>"
				
				});

				$(".all_"+str).remove();
				
				$("#table_head_"+str).after(output);

				if ( $("#btnCollapse_"+str).hasClass('_collapsed') == true ){
					//показать только первую строку
					$(".first_line_"+str).fadeIn("fast");
				}
				else if ( $("#btnCollapse_"+str).hasClass('_expanded') == true ){
					//показать все строки
					$(".all_"+str).fadeIn("fast");
				}
			
			}
		});
		
	});
	
	
	$("button[id*='btnHide_']").click(function(){
		
		var str = $(this).attr('id');
		str = str.slice(8);
		
		$.get("/account/"+str+"/hide.html",function(data,status) {
			
			if(data == 'hidden')
				$('#block_'+str).hide();
							
		});
		
	});

	
	$("button[id*='btnCollapse_']").click(function(){
		
		var str = $(this).attr('id');
		str = str.slice(12);
		
		var button_id = $(this).attr("id");
		
		$.get("/account/"+str+"/collapse.html",function(data,status) {
			
			if(data == 'Collapsed'){

				//скрыть все кроме первой строки
				$(".collapse_line_"+str).fadeOut("fast");
				//показать только первую строку				
				$(".first_line_"+str).fadeIn("fast");
				
				$("#"+button_id).html('Expand');
				$("#"+button_id).addClass('_collapsed');
				$("#"+button_id).removeClass('_expanded');
			}
				
			else if(data == 'Expanded'){
				
				//показать все строки				
				$(".all_"+str).fadeIn("fast");

				$("#"+button_id).html('Collapse');
				$("#"+button_id).addClass('_expanded');
				$("#"+button_id).removeClass('_collapsed');
				
			}
							
		});
		
	});
	
});

</script>