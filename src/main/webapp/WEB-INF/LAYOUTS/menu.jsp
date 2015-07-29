<%@ include file="../LAYOUTS/taglib.jsp"%>

<!-- Static navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<spring:url value="/index.html" />"><spring:message code="menu.auctionline" /></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="${current=='index'?'active':''}"><a href='<spring:url value="/index.html" />'><spring:message code="menu.home" /></a></li>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li class="${current=='users'?'active':''}"><a href="<spring:url value="/users.html" />"><spring:message code="menu.users" /></a></li>
				</security:authorize>
				<security:authorize access="! isAuthenticated()">
					<li class="${current=='register'?'active':''}"><a href="<spring:url value="/user/register.html" />"><spring:message code="menu.user-register" /></a></li>
					<li class="${current=='login'?'active':''}"><a href="<spring:url value="/login.html" />"><spring:message code="menu.login" /></a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li><a href="<spring:url value="/logout" />"><spring:message code="menu.logout" /></a></li>
				</security:authorize>
				<li><a href="#">Contact</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAuthenticated()">
					<li class="${current=='account'?'active':''}"><a href="<spring:url value="/account.html" />"><spring:message code="menu.account" /></a></li>
				</security:authorize>
				<li id="en" style="display:none"><a href="?lang=en"><spring:message code="lang.en" /></a></li>
				<li id="ru" style="display:none"><a href="?lang=ru"><spring:message code="lang.ru" /></a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>

<script>
	$(document).ready(function() {

		var currentLocal = $.cookie('currentLocal');
	
//		alert(currentLocal);
		
		if (currentLocal == 'en') {
			$("#ru").show();
			lang='ru'
		} else if (currentLocal == 'ru'){
			$("#en").show();
		}
		
	});
</script>

