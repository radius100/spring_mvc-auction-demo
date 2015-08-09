<%@ include file="../LAYOUTS/taglib.jsp"%>

<div class="row">
	<div class="col-md-6">
		<p class="lead text-left">
		<small>
			Lots per page:
			<select>
				<option>9</option>
				<option>30</option>
				<option>60</option>
			</select>
			| sort: 
			<c:if test="${pagination.sortDirection eq 'ASC'}">
				<a href="/desc/index.html">&darr;</a> 
			</c:if>
			<c:if test="${pagination.sortDirection eq 'DESC'}">
				<a href="/asc/index.html">&uarr;</a>
			</c:if>
			</small>
		</p>	
	</div>
	<div class="col-md-6">
		<p class="lead text-right">
			<a href="/trading/index.html">trading</a>&ensp;
			|&nbsp;<a href="/pretrading/index.html">pretrading</a>&ensp;
			|&nbsp;<a href="/active/index.html">all</a>&ensp;
			|&nbsp;<a href="/archive/index.html">archive</a>&ensp;
		</p>
	</div>
</div>
				