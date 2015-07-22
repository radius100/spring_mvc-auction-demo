<%@ include file="../LAYOUTS/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

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

.myItem{
	height: 100px;
	width: 100px;
	//border: 1px !important;
	border-bottom: 1px;
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