<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<img src="/resources/1.jpg" height="260" width="320"/>

<div id="banner-fade">
	<div id="my-slideshow">
		<ul class="bjqs">
			<li><img src="/resources/1.jpg" /></li>
			<li><img src="/resources/2.jpg" /></li>
			<li><img src="/resources/3.jpg" /></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$('#banner-fade').bjqs({
			animtype : 'slide',
			'height' : 250,
			'width' : 300,
			'randomstart' : true,
			'animspeed' : 7000,
			'responsive' : true,
			'nexttext' : '>>>',
			'prevtext' : '<<<',
			'showmarkers' : true
		});
	});
</script>
