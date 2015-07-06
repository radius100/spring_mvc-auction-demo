<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<button id="btnRate" class="btn btn-primary">Ставка</button>

<div id="check">

</div>

<input type="text" class="form-control" id="amount">

!!!!
<span>
!!!!
<!-- 
<table><thead><th>User</th><th>Amount</th><th>Date</th><th>Time</th></thead><tbody>
    <tr>
    	<td>Row 1: Col 1</td>
    	<td>Row 1: Col 2</td>
    </tr>
  </tbody>
</table>
 -->

<script type="text/javascript">

jQuery(document).ready(function($) {
	
	
	//попробовать $.load()
	
	$.getJSON("/items/item-3/tradepool.json", function(json){
	
		if(json != null){
				          
			var output="<table><thead><th>User</th><th>Amount</th><th>Date</th><th>Time</th></thead><tbody>";
			var output2="";
			$.each(json, function(i,item){
				
				output+="<tr>"+"<td>"+item.User+"</td>"
							+"<td>"+item.Amount+"</td>"
							+"<td>"+item.Date+"</td>"
							+"<td>"+item.Time+"</td>"
				
			});

			output+="</tr></tbody></table>";
			$('table').addClass("table");
			
			$(output).appendTo("#check");
		 
			// $('#amount').val(output);
	
		}
	});
		
});


</script>