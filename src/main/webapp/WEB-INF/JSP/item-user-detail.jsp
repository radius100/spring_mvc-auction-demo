<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>
<h1>${item.name}</h1>
<br />
<h3><spring:message code="item.detail.publishedBy"/></h3>
${publish.name}
<br />
Status: active
<br />
Current stack: $150
<br />
Time to last: 02:10:15
<br />
<h3>Trading by:</h3>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Имя пользователя</th>
			<th>Ставка</th>
			<th>Дата и время</th>
		</tr>
	</thead>

	<c:forEach items="${p}" var="qa">
		<tbody>
			<tr>
				<td>${qa.user.name}</td>
				<td>${qa.amount/100}</td>
				<td>${qa.date}</td>
			</tr>
		</tbody>
	</c:forEach>

</table>

<h3><spring:message code="item.detail.followedBy"/></h3>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Имя пользователя</th>
		</tr>
	</thead>
	<c:forEach items="${follows}" var="follow">
		<tbody>
			<tr>
				<td>${follow.name}</td>
			</tr>
		</tbody>
	</c:forEach>
</table>
<br />
