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
					<h1>Access is denied</h1>
					<p class="lead">HTTP Status 403</p>
					<c:choose>
							<c:when test="${empty username}">
							  <h2></h2>
							  <div class="alert alert-dismissible alert-danger">
								  <button type="button" class="close" data-dismiss="alert">&times;</button>
								  <strong><spring:message code="error.accessDenied"/></strong> 
								</div>
							</c:when>
							<c:otherwise>
					                    <div class="alert alert-dismissible alert-danger">
								  <button type="button" class="close" data-dismiss="alert">&times;</button>
								  <strong>Username : ${username}. <spring:message code="error.accessDenied"/></strong> 
								</div>
							</c:otherwise>
						</c:choose>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
<!-- 							<h1 id="containers">Containers</h1> -->
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	</div>

</body>
</html>
