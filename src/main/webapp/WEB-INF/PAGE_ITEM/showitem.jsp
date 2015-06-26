<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

${itemJson}

<div class="bodyh">
	<br />
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Описание</a></li>
		<li role="presentation"><a href="#description" aria-controls="description" role="tab" data-toggle="tab">Торги</a></li>
		<li role="presentation"><a href="#images" aria-controls="images" role="tab" data-toggle="tab">Images</a></li>
	</ul>
	<!-- Tab panes -->
	<br />
	<div class="tab-content" id="tabscontent">
		<div role="tabpanel" class="tab-pane active" id="home">
			111

			<table data-url="data1.json" data-height="299" data-sort-name="name" data-sort-order="desc">
				<thead>
					<tr>
						<th data-field="id" data-align="right" data-sortable="true">Item ID</th>
						<th data-field="name" data-align="center" data-sortable="true">Item Name</th>
						<th data-field="price" data-sortable="true">Item Price</th>
					</tr>
				</thead>
			</table>

		</div>
		<div role="tabpanel" class="tab-pane" id="description">
			222
			<table data-toggle="table" data-url="item-${item.id}/tradpool.json" data-height="299">
				<thead>
					<tr>
						<th data-field="id" data-halign="right" data-align="center">Item ID</th>
						<th data-field="name" data-halign="center" data-align="left">Item Name</th>
					</tr>
				</thead>
			</table>
		</div>
		<div role="tabpanel" class="tab-pane" id="images">
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


		</div>
	</div>
</div>
