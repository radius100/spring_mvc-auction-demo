<%@ include file="../LAYOUTS/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css">
<!-- 
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<style type="text/css">

body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}

.tabscontent {
	padding-top: 70px;
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
			<div class="col-md-12" align="center">
				<tiles:insertAttribute name="register" />
			</div>
		</div>
	</div>
	<!-- Page footer -->
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>