<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<title><spring:message code="main.title"/></title>
<script src="${pageContext.request.contextPath}/resources/validate/jquery.validate.js"></script>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    
    <div class="container">

		<div class="page-header" id="banner">
			<div class="row">
				<%-- <div class="col-lg-8 col-md-7 col-sm-6">
					<h1><spring:message code="txt.login"/></h1>
					<p class="lead">Material is the metaphor</p>
				</div> --%>
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
							<h1 id="containers"><spring:message code="txt.authentication"/></h1>
						</div>
					</div>
				</div>
				
				<c:if test="${not empty error}">
					<div class="alert alert-dismissible alert-warning">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Warning!</h4>
						<p>${error}</p>
					</div>
				</c:if>
				<c:if test="${not empty message}">
					<div class="alert alert-dismissible alert-success">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<p>${message}</p>
					</div>
				</c:if>
				
				<form id="reSendForm" action="${pageContext.request.contextPath}/mail/authentication" method="POST">
					<input type="hidden" name="username" value="${username }"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				
				<div class="col-lg-6 col-md-7 col-sm-6">
					<c:choose>
						<c:when test="${certified eq 'SUCCESS' }">	<!-- 인증완료 -->
							인증이 완료되었습니다.
							<a href="${pageContext.request.contextPath}" class="btn btn-primary"><spring:message code="button.confirm"/></a>
						</c:when>
						<c:when test="${certified eq 'ERROR' }"><!-- 인증실패 -->
							인증요청 메일을 다시 요청하거나<br>
							문제가 계속 될 경우 관리자에게 문의하세요.
							<a href="#" class="btn btn-danger" onclick="$('#reSendForm').submit()"><spring:message code="button.reSendAuthCode"/></a>
						</c:when>
						<c:when test="${certified eq 'JOIN_SUCCESS' }"><!-- 회원가입완료 -->
							회원가입이 완료되었습니다.<br>
							이메일 인증 후 서비스 이용이 가능합니다.<br>
							3분 이내로 인증 메일이 오지 않는 경우 메일을 다시 요청하세요.
							<a href="#" class="btn btn-danger" onclick="$('#reSendForm').submit()"><spring:message code="button.reSendAuthCode"/></a>
						</c:when>
						<c:when test="${certified eq 'NOT_CERTIFIED' }"><!-- 계정 미인증 -->
							이메일 인증을 완료해주세요.<br>
							이메일 인증 후 서비스 이용이 가능합니다.<br>
							인증메일을 다시 요청하려면 재요청 버튼을 누르세요.
							<a href="#" class="btn btn-danger" onclick="$('#reSendForm').submit()"><spring:message code="button.reSendAuthCode"/></a>
						</c:when>
						<c:when test="${certified eq 'SEND_AUTHENTICATION_CODE' }"><!-- 인증메일 재전송 -->
							이메일 인증을 완료해주세요.<br>
							이메일 인증 후 서비스 이용이 가능합니다.<br>
							인증메일을 다시 요청하려면 재요청 버튼을 누르세요.
							<a href="#" class="btn btn-danger" onclick="$('#reSendForm').submit()"><spring:message code="button.reSendAuthCode"/></a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
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
