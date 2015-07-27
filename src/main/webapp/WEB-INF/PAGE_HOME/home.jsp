<%@ include file="../LAYOUTS/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/resources/bootstrap/cosmo/bootstrap.min.css">
<script src="/resources/bootstrap/jquery.min.js"></script>
<script src="/resources/bootstrap/bootstrap.min.js"></script> 
<script src="/resources/jquery-cookie/jquery.cookie.min.js" type="text/javascript"></script>

<script>
	$(document).ready(
			function() {
				$('#myCarousel').carousel({
					interval : 7000
				})
				$('.fdi-Carousel .item').each(
						function() {
							var next = $(this).next();
							if (!next.length) {
								next = $(this).siblings(':first');
							}
							next.children(':first-child').clone().appendTo(
									$(this));

							if (next.next().length > 0) {
								next.next().children(':first-child').clone()
										.appendTo($(this));
							} else {
								$(this).siblings(':first').children(
										':first-child').clone().appendTo(
										$(this));
							}
						});
			});
</script>

<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}

.slide-image {
	width: 100%;
}

.carousel-holder {
	margin-bottom: 30px;
}

.carousel-control, .item {
	border-radius: 4px;
}

.caption {
	height: 430px;
	overflow: hidden;
}

.caption h4 {
	white-space: nowrap;
}

.thumbnail img {
	width: 100%;
}

.ratings {
	padding-right: 10px;
	padding-left: 10px;
	color: #d17581;
}

.thumbnail {
	padding: 0;
	height: 460px;
}

.thumbnail .caption-full {
	padding: 9px;
	color: #333;
}

footer {
	margin: 50px 0;
}

</style>

<style>
.carousel-inner.onebyone-carosel {
	margin: auto;
	width: 90%;
}

.onebyone-carosel .active.left {
	left: -33.33%;
}

.onebyone-carosel .active.right {
	left: 33.33%;
}

.onebyone-carosel .next {
	left: 33.33%;
}

.onebyone-carosel .prev {
	left: -33.33%;
}
</style>

<title><tiles:getAsString name="title" /></title>

</head>

<body>
	<!-- Page menu -->
	<div class="container">
		<tiles:insertAttribute name="menu" />
	</div>
	<div class="container">
    <!--  
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="uploadFile" method="post">
        <div class="form-group"><h3>New photo</h3></div>
        <div class="form-group">Photo: <input type="file" name="file"></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
    </form>
    -->
    <!-- 
    <form method="POST" action="uploadFile" enctype="multipart/form-data">
        File to upload: <input type="file" name="file"><br> 
        <input type="submit" value="Upload"> 
    </form>
     -->
</div>
 	<!-- Page Content -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<tiles:insertAttribute name="carusel" />
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<tiles:insertAttribute name="slidebar" />
				<br />
				<tiles:insertAttribute name="sidemenu" />
			</div>
			<div class="col-md-9">
				<tiles:insertAttribute name="bodynav" />
				<br>
				<br>
				<tiles:insertAttribute name="showitems" />
				<div class="row">
					<div class="col-md-12 text-center">
						<tiles:insertAttribute name="pagination" />
					</div>
				</div>			
			</div>
		</div>
	</div>
	<!-- Page footer -->
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>