<%@ include file="../LAYOUTS/taglib.jsp"%>

<nav>
	<ul class="pagination">
    	<c:if test="${pagination.prevPage == -1 }">
    		<li class="disabled">
    			<a href="#" aria-label="Previous">
        			<span aria-hidden="true">&laquo;</span>
      			</a>
    		</li>
    	</c:if>
    	<c:if test="${pagination.prevPage >= 1 }">
    		<li>
    			<a href="/page-${pagination.prevPage}/index.html" aria-label="Previous">
        			<span aria-hidden="true">&laquo;</span>
      			</a>
    		</li>
    	</c:if>
    	<c:forEach items="${pagination.gridArray}" var="item" begin="0" end="${pagination.totalPages-1}">
    		<c:if test="${pagination.pageIndex == item}">
    			<li class="active"><a href="/page-${item}/index.html">${item}</a></li>
    		</c:if>
    		<c:if test="${pagination.pageIndex != item}">
    			<li><a href="/page-${item}/index.html">${item}</a></li>
    		</c:if>
    	</c:forEach>
    	<c:if test="${pagination.nextPage == -1 }">
    		<li class="disabled">
    			<a href="#" aria-label="Next">
        			<span aria-hidden="true">&raquo;</span>
      			</a>
    		</li>
    	</c:if>
    	<c:if test="${pagination.nextPage > 1 }">
    		<li>
    			<a href="/page-${pagination.nextPage}/index.html" aria-label="Next">
        			<span aria-hidden="true">&raquo;</span>
      			</a>
    		</li>
    	</c:if>
    </ul>
</nav>
