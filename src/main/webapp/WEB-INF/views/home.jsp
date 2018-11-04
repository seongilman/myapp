<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<div class="col-lg-8 col-md-7 col-sm-6">
					<h1><spring:message code="txt.welcome"/></h1>
					<p class="lead">Material is the metaphor</p>
				</div>
				<div class="row">
					<div class="col-lg-12">
<!-- 						<div class="page-header"> -->
<!-- 							<h1 id="containers">Containers</h1> -->
<!-- 						</div> -->
						<img alt="" src="${pageContext.request.contextPath}/resources/common/image/boast.jpg" style="width: 100%">
						<div class="jumbotron">
							<h1>Jumbotron</h1>
							<p>This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
							<p><a class="btn btn-primary btn-lg">Learn more</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Tables ================================================== -->
		<div class="bs-docs-section">
			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h1 id="tables"><spring:message code="txt.board"/></h1>
					</div>

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
							<tbody>
								<c:forEach items="${boardList }" var="list">
									<tr>
										<td>${list.no }</td>
										<td>
											<c:choose>
												<c:when test="${0 < list.depth }">
													ㄴ<div class="btn btn-primary btn-xs">R</div>${list.title }
												</c:when>
												<c:otherwise>
													${list.title }
												</c:otherwise>
											</c:choose>
										</td>
										<td>${list.nickname }</td>
										<td>${list.regDt }</td>
									</tr>
								</c:forEach>
							</tbody>
						</tbody>
					</table> 
				</div>
			</div>
		</div>

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	</div>
</body>
</html>
