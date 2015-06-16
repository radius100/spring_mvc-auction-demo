<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

<h1>${user.name}</h1>

<h3>Published:</h3>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Название лота</th>
			<th>Стартовая цена</th>
		</tr>
	</thead>
	<c:forEach items="${publishs}" var="publish">
		<tbody>
			<tr>
				<td><a
					href="<spring:url value="/items/item-${publish.id}.html" />">
						${publish.name} </a></td>
				<td>${publish.startPrice}</td>
			</tr>
		</tbody>
	</c:forEach>
</table>
<br />
<h3>Followed:</h3>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Название лота</th>
			<th>Стартовая цена</th>
		</tr>
	</thead>
	<c:forEach items="${follows}" var="follow">
		<tbody>
			<tr>
				<td><a
					href="<spring:url value="/items/item-${follow.id}.html" />">
						${follow.name} </a></td>
				<td>${follow.startPrice}</td>
			</tr>
		</tbody>
	</c:forEach>
</table>
