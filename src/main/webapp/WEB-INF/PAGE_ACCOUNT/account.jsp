<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<br>
<button id="btnUpdateAll" class="btn btn-primary">Refresh All</button>
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
						<tr id="${table.itemId}">
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
					<button id="btnExpand_${table.itemId}" class="btn btn-xs btn-primary">Expand</button>
					<button id="btnHide_${table.itemId}" class="btn btn-xs btn-primary">Hide</button>
				</div>
			</div>
			<br>
			<br>
		</div>
	</c:if>
</c:forEach>
 
<br /><br /> 
<h2>Follows:</h2>
<c:forEach items="${tables}" var="table">
	<c:if test="${table.type eq 'Follow' }">
		<a href="/items/${table.itemId}.html">${table.name}</a>
		<br />
		<table class="table table-bordered table-striped table-condensed" style="display: none">
			<tr id="${table.itemId}">
				<th width="25%">User</th>
				<th width="25%">Amount</th>
				<th width="25%">Date</th>
				<th width="25%">Time</th>
			</tr>
		</table>
		
		<br />
	</c:if>
</c:forEach>


<script type="text/javascript">

jQuery(document).ready(function($) {

	var TurnOn = true;
	
	UpdateTables();
	
	function UpdateTables() {
		
		$.getJSON("/aaccount.json", function(listItems){
		
			if(listItems != null){

				$('table').fadeOut("fast");
				$(".remove_all").remove();
			
				$.each(listItems, function(i,itemInfo){
				
					$.getJSON("/items/"+itemInfo.Id+"/tradepool.json", function(json){
					
						if(json != null){
						
							var output="";
				
							$.each(json, function(i,item){
							
								output+="<tr class=\"remove_all "+itemInfo.Id
											+" \" style=\"display:none\">"
										+"<td>"+item.User+"</td>"
										+"<td>"+item.Amount+"</td>"
										+"<td>"+item.Date+"</td>"
										+"<td>"+item.Time+"</td>"
										+"</tr>"
							
							});

							$("#"+itemInfo.Id).after(output);
							$(".remove_all").fadeIn("slow");
							$('table').fadeIn("slow");
						
						}
					});
				});
			}
		});
	}

	
	setInterval(UpdateTables, 300000);
	
	
	$('#btnUpdateAll').click(function(){
		UpdateTables();
	});


	$("button[id*='btnUpdate_']").click(function(){
	
		var str = $(this).attr('id');
		str = str.slice(10);
		
		$("."+str).remove();
		
		$.getJSON("/items/"+str+"/tradepool.json", function(json){
			
			if(json != null){
			
				var output="";
				var class_visible="";
	
				$.each(json, function(i,item){
				
					if( i > 0 )
						class_hide = "";
					
					output+="<tr class=\"remove_all "+str
								+" \" style=\"display:none\">"
							+"<td>"+item.User+"</td>"
							+"<td>"+item.Amount+"</td>"
							+"<td>"+item.Date+"</td>"
							+"<td>"+item.Time+"</td>"
							+"</tr>"
				
				});

				$("#"+str).after(output);
				$("."+str).fadeIn("slow");
			
			}
		});
		
	});
	
	
	$("button[id*='btnHide_']").click(function(){
		
		var str = $(this).attr('id');
		str = str.slice(8);
		
		$.get("/account/"+str+"/hide.html",function(data,status) {
			
			if(data == 'ok')
				$('#block_'+str).hide();
							
		});
		
	});

	
	$("button[id*='btnExpand_']").click(function(){
		
		var str = $(this).attr('id');
		str = str.slice(10);
		
		var id = $(this).attr("id");
		
		$.get("/account/"+str+"/expand.html",function(data,status) {
			
			if(data == 'expand'){

				$("#"+id).html('Collapse');
			}
				
			else if(data == 'collapse'){
				
				$("#"+id).html('Expand');
			}
			
							
		});
		
	});

	
	
});

</script>