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
<script src="/resources/jquery-cookie/jquery.cookie.min.js" type="text/javascript"></script>

<link href="/resources/bjqs/bjqs.css" type="text/css" rel="Stylesheet" />
<link href="/resources/bootstrap-table/bootstrap-table.css" rel="stylesheet">

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
	height: 900px;
}

.borderless tbody tr td, .borderless tbody tr th, .borderless thead tr th
	{
	border: none;
}

.blockPointer:hover {
    cursor: pointer;
    color: blue;
}

.blockPointer1:hover {
    cursor: pointer;
   border-top: 1px solid red;
}

.amountInput {
	text-align: right;
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