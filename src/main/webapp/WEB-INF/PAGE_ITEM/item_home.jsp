<%@ include file="../LAYOUTS/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css">
<link type="text/css" rel="Stylesheet" href="/resources/bjqs/bjqs.css" />
<link rel="stylesheet" href="/resources/bootstrap-table/bootstrap-table.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script src="/resources/bjqs/bjqs-1.3.min.js"></script>

<script src="/resources/bootstrap-table/bootstrap-table.js"></script>

<style type="text/css">
	body {
		padding-top: 70px;
		/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
	}

	footer {
		margin: 50px 0;
	}
	
	.bodyh {
		height: 600px;
		
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
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<tiles:insertAttribute name="slidebar" />
				<br />
				<tiles:insertAttribute name="sidemenu" />
			</div>
			
			<div class="col-md-6">
				<tiles:insertAttribute name="showitem" />
			</div>
			
		</div>
	</div>
	<!-- Page footer -->
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>