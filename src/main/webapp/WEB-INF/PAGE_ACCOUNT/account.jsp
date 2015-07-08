<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<button id="btnUpdate" class="btn btn-primary">Refresh</button>

<br /><br />
<h2>Trade pools:</h2>
<c:forEach items="${tables}" var="table">
	<c:if test="${table.type eq 'Trade' }">
		<a href="/items/${table.itemId}.html">${table.name}</a>
		<br />
		<table class="table table-bordered table-striped table-condensed">
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
 
<br /><br /> 
<h2>Follows:</h2>
<c:forEach items="${tables}" var="table">
	<c:if test="${table.type eq 'Follow' }">
		<a href="/items/${table.itemId}.html">${table.name}</a>
		<br />
		<table class="table table-bordered table-striped table-condensed">
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

	UpdateTables();
	
	function UpdateTables() {
		
		$.getJSON("/aaccount.json", function(listItems){
		
			if(listItems != null){

				$(".removable").remove();
			
				$.each(listItems, function(i,itemInfo){
				
					$.getJSON("/items/"+itemInfo.Id+"/tradepool.json", function(json){
					
						if(json != null){
						
							var output="";
				
							$.each(json, function(i,item){
							
								output+="<tr class=\"removable\" style=\"display:none\">"+"<td>"+item.User+"</td>"
										+"<td>"+item.Amount+"</td>"
										+"<td>"+item.Date+"</td>"
										+"<td>"+item.Time+"</td>"+"</tr>"
							
							});

							$("#"+itemInfo.Id).after(output);
							$(".removable").fadeIn("slow");
						
						}
					});
				});
			}
		});
	}

	setInterval(UpdateTables, 300000);
	
	$('#btnUpdate').click(function(){
		
		UpdateTables();
	});

	$('#btnRemove').click(function(){
		
		$(".removable").remove();
		
	});
	
});


</script>