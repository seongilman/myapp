<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<title><spring:message code="main.title"/></title>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    
    <div class="container">

		<div class="page-header" id="banner">
			<div class="row">
				<%-- <div class="col-lg-8 col-md-7 col-sm-6">
					<h1><spring:message code="txt.board"/></h1>
					<p class="lead">Material is the metaphor</p>
				</div> --%>
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
							<h1 id="containers">${category }</h1>
						</div>
					</div>
				</div>
				
				<!-- Tables ================================================== -->
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th><spring:message code="txt.no"/></th>
							<th><spring:message code="txt.title"/></th>
							<th><spring:message code="txt.writer"/></th>
							<th><spring:message code="txt.date"/></th>
						</tr>
					</thead>
					<tbody>
						<tr class="warning">
							<td>
								-
							</td>
							<td onclick="viewContent(0)">
								<a href="#">공지사항</a>
							</td>
							<td>
								seongilman
							</td>
							<td>
								2017.05.12 
							</td>
						</tr>
						<c:forEach items="${boardList }" var="list">
							<tr>
								<td>${list.no }</td>
								<td onclick="viewContent(${list.no})">
									<c:choose>
										<c:when test="${0 < list.depth }">
											<c:forEach begin="0" end="${list.indent }">
												&nbsp;
											</c:forEach>
											ㄴ<div class="btn btn-primary btn-xs">R</div><a href="#">${list.title }</a>
										</c:when>
										<c:otherwise>
											<a href="#">${list.title }</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td>${list.nickname }</td>
								<td>${list.regDt }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table> 
				<sec:authorize access="isAuthenticated()">
					<div style="" align="">
						<a href="${pageContext.request.contextPath}/boards/write?category=${category }" class="btn btn-default"><spring:message code="btn.write"/></a>
					</div>
				</sec:authorize>
				
				<div align="center">
					<ul id="pagination" class="pagination">
						<li <c:if test="${pagingVO.curPage eq pagingVO.viewFirstPage }">class="disabled"</c:if>>
							<a href="#" data-page="${pagingVO.viewFirstPage }">&laquo;</a>
						</li>
						<li <c:if test="${pagingVO.curPage eq pagingVO.curPrevPage }">class="disabled"</c:if>>
							<a href="#" data-page="${pagingVO.curPrevPage }">&lsaquo;</a>
						</li>
						
						<c:forEach items="${pagingVO.pageList }" varStatus="pageList">
							<li <c:if test="${pagingVO.curPage eq pageList.count }">class="active"</c:if>>
								<a href="#" data-page="${pageList.count }">${pageList.count }</a>
							</li>
						</c:forEach>
						
						<li <c:if test="${pagingVO.curPage eq pagingVO.curNextPage}">class="disabled"</c:if>>
							<a href="#" data-page="${pagingVO.curNextPage }">&rsaquo;</a>
						</li>
						<li <c:if test="${pagingVO.curPage eq pagingVO.viewLastPage}">class="disabled"</c:if>>
							<a href="#" data-page="${pagingVO.nextPage }">&raquo;</a>
						</li>
						
						<!-- <li class="disabled"><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">&raquo;</a></li> -->
					</ul>
				</div>
			</div>
		</div>

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#pagination > li > a").on("click", function(){
			var isDisabled = $(this).parent().attr("class") == "disabled" ? true : false;
			
			if(!isDisabled){
				var url = "${pageContext.request.contextPath}/boards/${category }";
				location.href = url + "?pageNo=" + $(this).attr("data-page");
			};
		});
			
	});
	function viewContent(boardNo){
		location.href = "${pageContext.request.contextPath}/boards/${category }/" + boardNo;
	};
</script>
</body>
  
</html>
