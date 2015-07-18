<%@ include file="../LAYOUTS/taglib.jsp"%>

<security:authorize access="isAuthenticated()">
	<p class="lead">
		<spring:message code="sidemenu.title" />
	</p>
	<div class="list-group">
<!-- 
		<a href="#" class="list-group-item"><spring:message code="sidemenu.profile" /></a> 
		<a href="#" class="list-group-item"><spring:message code="sidemenu.my_items" /></a> 
		<a href="#" class="list-group-item"><spring:message code="sidemenu.im_following" /></a> 
		<a href="#" class="list-group-item"><spring:message code="sidemenu.my_rates" /></a>
 -->
		<a href=""<spring:url value="/account.html" />" class="list-group-item">Profile</a> 
		<a href="<spring:url value="/account/my-items.html" />" class="list-group-item">My items</a> 
		<a href="<spring:url value="/account/trading-monitor.html" />" class="list-group-item">Trading monitor</a> 
		<a href="#" class="list-group-item">Stats</a>
	</div>
</security:authorize>