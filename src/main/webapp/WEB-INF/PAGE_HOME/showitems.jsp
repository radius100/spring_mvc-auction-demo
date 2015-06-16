<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<div class="row">
	<c:forEach items="${items}" var="item">
		<div class="col-sm-4 col-md-4">
			<div class="thumbnail">
				<h4 class="text-center">
					<span class="label label-info">${item.name}</span>
				</h4>
				<img src="http://placehold.it/650x450&text=Galaxy S5" class="img-responsive">
				<div class="caption">
					<div class="row">
						<div class="col-md-6 col-xs-6">
							<h4>Galaxy S5</h4>
						</div>
						<div class="col-md-6 col-xs-6 price text-right">
							<h4>
								<label>$649.99</label>
							</h4>
						</div>
					</div>
					<!-- 	<p>32GB, 2GB Ram, 1080HD, 5.1 inches, Android</p>  -->
					<div class="row">
						<div class="col-md-12 lead" align="center">${item.dateMessage}</div>
					</div>
					<div class="row">
						<div class="col-md-8" align="left">
							<spring:message code="showitem.start_price" />
						</div>
						<div class="col-md-4" align="center">$${item.startAmount}</div>
						<div class="col-md-8" align="left">
							<spring:message code="showitem.current_price" />
						</div>
						<c:choose>
							<c:when test="${item.currentAmount > 0}">
								<div class="col-md-4" align="center">${item.currentAmount}</div>
							</c:when>
							<c:otherwise>
								<div class="col-md-4" align="center">-</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="row">
						<div class="col-md-8" align="left">
							<spring:message code="showitem.traders" />
						</div>
						<c:choose>
							<c:when test="${item.tradersCount gt 0}">
								<div class="col-md-4" align="center">${item.tradersCount}</div>
							</c:when>
							<c:otherwise>
								<div class="col-md-4" align="center">-</div>
							</c:otherwise>
						</c:choose>
						<div class="col-md-8" align="left">
							<spring:message code="showitem.followers" />
						</div>
						<c:choose>
							<c:when test="${item.tradersCount gt 0}">
								<div class="col-md-4" align="center">${item.followersCount}</div>
							</c:when>
							<c:otherwise>
								<div class="col-md-4" align="center">-</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="row">
						<hr>
					</div>
					<div class="row">
						<security:authorize access="isAuthenticated()">
							<div class="col-md-4" align="center">
								<c:choose>
									<c:when test="${item.followedByCurrentUser eq true}">
										<a class="btn btn-xs btn-success btn-product disabled"><span class="glyphicon glyphicon-star"></span> <spring:message code="showitem.btn.follow" /></a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-xs btn-primary btn-product"><span class="glyphicon glyphicon-star-empty"></span> <spring:message code="showitem.btn.follow" /></a>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-4" align="center">
								<a href="#" class="btn btn-xs btn-primary btn-product"><span class="glyphicon glyphicon-search"></span> <spring:message code="showitem.btn.detail" /></a>
							</div>
							<div class="col-md-4" align="center">
								<c:choose>
									<c:when test="${item.tradeedByCurrentUser eq true}">
										<a href="#" class="btn btn-xs btn-success btn-product disabled"><span class="glyphicon glyphicon-usd"></span> <spring:message code="showitem.btn.trade" /></a>
									</c:when>
									<c:otherwise>
										<a href="#" class="btn btn-xs btn-primary btn-product"><span class="glyphicon glyphicon-usd"></span> <spring:message code="showitem.btn.trade" /></a>
									</c:otherwise>
								</c:choose>
							</div>
						</security:authorize>
						<security:authorize access="! isAuthenticated()">
							<div class="col-md-12" align="center">
								<a href="#" class="btn btn-xs btn-primary btn-product"><span class="glyphicon glyphicon-search"></span> <spring:message code="showitem.btn.detail" /></a>
							</div>
						</security:authorize>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
