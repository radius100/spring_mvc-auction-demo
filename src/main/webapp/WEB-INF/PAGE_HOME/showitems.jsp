<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<div class="row" id="pageH">
	<c:if test="${not empty items}">
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
					<div class="row">
						<c:choose>
							<c:when test="${item.dateMessage eq 1}">
								<div class="col-md-8" align="left">
									<p>
										<spring:message code="showitem.date.info_1" />
									</p>
								</div>
								<div class="col-md-4" align="center">${item.dateValue}</div>
							</c:when>
							<c:when test="${item.dateMessage eq 2}">
								<div class="col-md-8" align="left">
									<p>
										<spring:message code="showitem.date.info_2" />
									</p>
								</div>
								<div class="col-md-4" align="center">${item.dateValue}</div>
							</c:when>
							<c:when test="${item.dateMessage eq 3}">
								<div class="col-md-12" align="center">
									<p>
									<strong>
										<spring:message code="showitem.date.info_3" />
									</strong>
									</p>
								</div>
							</c:when>
							<c:when test="${item.dateMessage eq 4}">
								<div class="col-md-8" align="left">
									<p>
										<spring:message code="showitem.date.info_4" />
									</p>
								</div>
								<div class="col-md-4" align="center">${item.dateValue}</div>
							</c:when>
							<c:when test="${item.dateMessage eq 5}">
								<div class="col-md-8" align="left">
									<p>
										<spring:message code="showitem.date.info_5" />
									</p>
								</div>
								<div class="col-md-4" align="center">${item.dateValue}</div>
							</c:when>
							<c:when test="${item.dateMessage eq 6}">
								<div class="col-md-12" align="center">
									<p>
									<strong>
										<spring:message code="showitem.date.info_6" />
									</strong>
									</p>
								</div>
							</c:when>
						</c:choose>
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
							<c:when test="${item.currentAmount gt 0}">
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
						<!-- 
						<hr>
						 -->
					</div>
					<div class="row">
						<p>
						<security:authorize access="isAuthenticated()">
							<div class="col-md-4" align="center">
								<c:choose>
									<c:when test="${(item.followedByCurrentUser eq true) && (item.tradeedByCurrentUser eq false)}">
										<a class="btn btn-xs btn-success btn-product btn-block disabled"><span class="glyphicon glyphicon-star"></span> <spring:message code="showitem.btn.follow" /></a>
									</c:when>
									<c:when test="${(item.followedByCurrentUser eq true) && (item.tradeedByCurrentUser eq true) }">
										<a class="btn  btn-block btn-xs btn-primary btn-product btn-block disabled"><span class="glyphicon glyphicon-star"></span> <spring:message code="showitem.btn.follow" /></a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-xs btn-primary btn-product btn-block"><span class="glyphicon glyphicon-star-empty"></span> <spring:message code="showitem.btn.follow" /></a>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-4" align="center">
								<a href="/items/item-${item.id}.html" class="btn btn-xs btn-primary btn-product btn-block"><span class="glyphicon glyphicon-search"></span><spring:message code="showitem.btn.detail" /></a>
							</div>
							<div class="col-md-4" align="center">
								<c:choose>
									<c:when test="${item.tradeedByCurrentUser eq true}">
										<a href="/items/item-${item.id}.html#trade" class="btn btn-xs btn-success btn-product btn-block disabled"><span class="glyphicon glyphicon-usd"></span> <spring:message code="showitem.btn.trade" /></a>
									</c:when>
									<c:otherwise>
										<a href="/items/item-${item.id}.html#trade" class="btn btn-xs btn-primary btn-product btn-block"><span class="glyphicon glyphicon-usd"></span> <spring:message code="showitem.btn.trade" /></a>
										
								<!-- 	 
									 <span class="label label-success"><span class="glyphicon glyphicon-usd"></span>Success</span>
									 <div class="alert alert-info" role="alert"><span class="glyphicon glyphicon-usd"></span>Success</div>
								 -->
									</c:otherwise>
								</c:choose>
							</div>
						</security:authorize>
						<security:authorize access="! isAuthenticated()">
							<div class="col-md-12" align="center">
								<a href="/items/item-${item.id}.html" class="btn btn-sm btn-primary btn-product btn-block"><span class="glyphicon glyphicon-search"></span> <spring:message code="showitem.btn.detail" /></a>
							</div>
						</security:authorize>
						</p>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	</c:if>
</div>