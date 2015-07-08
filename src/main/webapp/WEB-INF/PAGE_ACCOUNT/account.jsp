<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

${accountJson}

<button id="btnRemove" class="btn btn-primary">Remove</button>
<button id="btnRate3" class="btn btn-primary">3</button>
<button id="btnRate2" class="btn btn-primary">2</button>

<table class="table table-bordered table-striped table-condensed">
	<tr id="item-3">
		<th width="25%">User</th>
		<th width="25%">Amount</th>
		<th width="25%">Date</th>
		<th width="25%">Time</th>
	</tr>

</table>
 
<table class="table table-bordered table-striped table-condensed">
	<tr id="item-2">
		<th width="25%">User</th>
		<th width="25%">Amount</th>
		<th width="25%">Date</th>
		<th width="25%">Time</th>
	</tr>

</table>

//$("[id*='item-']")

<script type="text/javascript">

jQuery(document).ready(function($) {

	
	
	$('#btnRate3').click(function(){
		
		$.getJSON("/items/item-3/tradepool.json", function(json){
			
			if(json != null){
				var output="";
		
				$.each(json, function(i,item){
					
					output+="<tr class=\"removable\">"+"<td>"+item.User+"</td>"
								+"<td>"+item.Amount+"</td>"
								+"<td>"+item.Date+"</td>"
								+"<td>"+item.Time+"</td>"+"</tr>"
					
				});

				$("#item-3").after(output);
				
			}
		})

		
	});

	$('#btnRemove').click(function(){
		
		$(".removable").remove();
		
	});
	
	$('#btnRate2').click(function(){
		
		$.getJSON("/items/item-2/tradepool.json", function(json){
			
			if(json != null){
				var output="";
		
				$.each(json, function(i,item){
					
					output+="<tr class=\"removable\">"+"<td>"+item.User+"</td>"
								+"<td>"+item.Amount+"</td>"
								+"<td>"+item.Date+"</td>"
								+"<td>"+item.Time+"</td>"+"</tr>"
					
				});

				$("#item-2").after(output);
				
			}
		})

		
	});


});


</script>