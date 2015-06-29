<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../LAYOUTS/taglib.jsp"%>

${itemJson}

<div class="bodyh">
	<br />
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Описание</a></li>
		<li role="presentation"><a href="#description" aria-controls="description" role="tab" data-toggle="tab">Торги online!</a></li>
		<li role="presentation"><a href="#images" aria-controls="images" role="tab" data-toggle="tab">Images</a></li>
	</ul>
	<!-- Tab panes -->
	<br />
	<div class="tab-content" id="tabscontent">
		<div role="tabpanel" class="tab-pane active" id="home">
			<table class="table borderless">
				<col width="3%">
				<tbody>
					<tr>
						<td></td>
						<td>
							<h3>${item.name}</h3>
							<p>${item.descr}</p>
							<table class="table table-condensed">
								<col width="30%">
								<tbody>
									<tr>
										<td>Descr:</td>
										<td align="justify">${item.fullDescr}</td>
									</tr>
									<tr class="active">
										<td>Status:</td>
										<td>Active</td>
									</tr>
									<tr>
										<td>Start in:</td>
										<td>20 min</td>
									</tr>
									<tr>
										<td>Start amount:</td>
										<td>$ ${item.startAmount}</td>
									</tr>
									<tr>
										<td>Current amount:</td>
										<td>$ 25</td>
									</tr>
								</tbody>
							</table>
							<h3>Follow by:</h3>
							<table class="table borderless">
								<col width="30%">
								<tbody>
									<c:forEach items="${item.followers}" var="follower">
									<tr>
										<td></td>
										<td>${follower.name}</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<h3>Trade by:</h3>
							<table class="table borderless">
								<col width="30%">
								<tbody>
									<c:forEach items="${item.traders}" var="trader">
									<tr>
										<td></td>
										<td>${trader.name}</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<h3>Publish by:</h3>
							<table class="table borderless">
								<col width="30%">
								<tbody>
									<tr>
										<td></td>
										<td><a href="#">${item.publisher.name}</a></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div role="tabpanel" class="tab-pane" id="description">

			<table class="table borderless">
				<col width="3%">
				<col width="50%">
				<tbody>
					<tr>
						<td></td>
						<td>
						<span class="input-group-addon">$</span>
						<input type="text" class="form-control amountInput" id="amount">	
						<span class="input-group-addon">.00</span>
						</td>
						<td>
						<input type="submit" class="btn btn-primary" id="doStake" value="Ваша ставка">
						</td>
					</tr>
				</tbody>
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
