<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<div class="col-md-9">

	<div class="panel panel-default" align="left">
		<div class="panel-heading">Лот 1</div>
		<div class="panel-body">
			<p>
				Basic panel example <br />Basic panel example <br />Basic panel example
			</p>
			<hr>
			<p>
				Lot: ${item.name}<br>
				<c:forEach items="${item.tradePools}" var="tp">
					${tp.user.name} - ${tp.amount} - ${tp.messageDate} - ${tp.messageTime}<br>
				</c:forEach>
			</p>
		</div>
	</div>
</div>
<!-- 
<div class="col-md-6">

	<div class="panel panel-default">
		<div class="panel-heading">Лот 1</div>
		<div class="panel-body">
			<p>Basic panel example <br />Basic panel example <br />Basic panel example</p>
			<hr>
			<p>
			See more snippets like these online store reviews at Bootsnipp - http://bootsnipp.com.

Want to make these reviews work? Check out this building a review system tutorial over at maxoffsky.com!

Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum
			</p>
			
		</div>
	</div>
</div>
 -->