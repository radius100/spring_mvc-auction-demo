<%@ include file="../LAYOUTS/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/resources/bootstrap/cosmo/bootstrap.min.css">
<script src="/resources/bootstrap/jquery.min.js"></script>
<script src="/resources/bootstrap/bootstrap.min.js"></script> 
<script src="/resources/jquery-cookie/jquery.cookie.min.js" type="text/javascript"></script>
<script src="/resources/validationplugin/jquery.validate.min.js" type="text/javascript"></script>

<style type="text/css">

body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}

#pageH {
    min-height: 650px;
}

footer {
	margin: 50px 0;

</style>

<title><tiles:getAsString name="title" /></title>

</head>

<body>
	<!-- Page menu -->
	<div class="container">
		<tiles:insertAttribute name="menu" />
	</div>
	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-md-4" align="center"></div>
			<div class="col-md-4" align="center">
				<tiles:insertAttribute name="register" />
			</div>
			<div class="col-md-4" align="center"></div>
		</div>
	</div>
	<!-- Page footer -->
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>