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
	<img alt="aa" src="/images/1.jpg">
	
	<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/images/item-1/add" method="post">
        <div class="form-group"><h3>New photo</h3></div>
        <div class="form-group">Photo: <input type="file" name="file"></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
    </form>
</div>
	
	<!-- Page Content -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<tiles:insertAttribute name="slidebar" />
				<br />
				<tiles:insertAttribute name="sidemenu" />
			</div>
			<div class="col-md-9">
				<tiles:insertAttribute name="carusel" />
				<p class="lead text-right" >
				active now | trading now | all
				</p>
				<tiles:insertAttribute name="showitems" />
			</div>
		</div>
	</div>
	<!-- Page footer -->
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>