<%@ include file="../LAYOUTS/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 
<link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
 -->
<link rel="stylesheet" href="/resources/bootstrap/cosmo/bootstrap.min.css">

<script src="/resources/bootstrap/jquery.min.js"></script>
<script src="/resources/bootstrap/bootstrap.min.js"></script> 

<style type="text/css">
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}

footer {
	margin: 50px 0;
}

#pageH {
    min-height: 600px;
}

</style>

<title><tiles:getAsString name="title" /></title>
</head>

<body>
	<!-- Page menu -->
	<div class="container">
		<tiles:insertAttribute name="menu" />
	</div>
	<!-- Page Content -->
	<div class="container" id="pageH">
		<div class="row">
			<div class="col-md-12"> <tiles:insertAttribute name="error500" /> </div>
		</div>
	</div>
	<!-- Page footer -->
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>