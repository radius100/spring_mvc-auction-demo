<%@ include file="../LAYOUTS/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/resources/bootstrap/cosmo/bootstrap.min.css">
<script src="/resources/bootstrap/jquery.min.js"></script>
<script src="/resources/bootstrap/bootstrap.min.js"></script> 
<script src="/resources/jquery-cookie/jquery.cookie.min.js" type="text/javascript"></script>

<spring:url value="/resources/datetimepicker/moment.min.js" var="momentJs" />
<script src="${momentJs}"></script>
<spring:url value="/resources/datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepickerJs" />
<script src="${datetimepickerJs}"></script>
<spring:url value="/resources/datetimepicker/bootstrap-datetimepicker.min.css" var="datetimepickerCss" />
<link href="${datetimepickerCss}" rel="stylesheet" type="text/css" />

<link href="/resources/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="/resources/fileinput/fileinput.min.js" type="text/javascript"></script>
<script src="/resources/fileinput/fileinput_locale_ru.js"></script>


<style type="text/css">

body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}

footer {
	margin: 50px 0;
}

.blockPointer:hover {
    cursor: pointer;
    color: blue;
}

.blockPointer1:hover {
    cursor: pointer;
   border-top: 1px solid red;
}

.borderless tbody tr td, .borderless tbody tr th, .borderless thead tr th {
    border: none;
}
#pageH {
    min-height: 900px;
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
				<tiles:insertAttribute name="sidemenu" />
			</div>
			<div class="col-md-9">
				<tiles:insertAttribute name="item_edit" />
			</div>
		</div>
	</div>
	<!-- Page footer -->
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>