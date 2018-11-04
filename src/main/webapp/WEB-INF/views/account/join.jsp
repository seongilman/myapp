<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="_csrf" content="${_csrf.token}"/>
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
					<h1><spring:message code="txt.regist"/></h1>
					<p class="lead">Material is the metaphor</p>
				</div> --%>
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
							<h1 id="containers"><spring:message code="txt.regist"/></h1>
						</div>
					</div>
				</div>
				
				<c:if test="${not empty error}">
					<div class="alert alert-dismissible alert-warning">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Warning!</h4>
						<%-- <p>${error}</p> --%>
						<p><spring:message code="message.loginFail"/></p>
					</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="alert alert-dismissible alert-success">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<p>${msg}</p>
					</div>
				</c:if>
				
				<div class="col-lg-4 col-md-7 col-sm-6">
					<form id="loginForm" action="<c:url value='/account/join' />" method="POST">
						<div class="form-group">
							<label class="control-label" for="focusedInput"><spring:message code="txt.email"/></label>
							<input type="text" id="username" name="username" class="form-control required" placeholder="<spring:message code="txt.email"/>">
						</div>
						<div class="form-group">
							<label class="control-label" for="focusedInput"><spring:message code="txt.nickname"/></label>
							<input type="text" id="nickname" name="nickname" class="form-control required" placeholder="<spring:message code="txt.nickname"/>">
						</div>
						<div class="form-group">
							<label class="control-label" for="focusedInput"><spring:message code="txt.password"/></label>
							<input type="password" id="password" name="password" class="form-control required" placeholder="<spring:message code="txt.password"/>">
						</div>
						<div class="form-group">
							<label class="control-label" for="focusedInput"><spring:message code="txt.repassword"/></label>
							<input type="password" id="rePassword" name="rePassword" class="form-control required" placeholder="<spring:message code="txt.repassword"/>">
						</div>
						
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<br>
						<input type="submit" class="btn btn-primary" value="회원가입">
					</form>
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
		
		<!-- footer -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	</div>

<script type="text/javascript">
	(function() {
		// 스프링 시큐리티 csrf 설정으로 인해 헤더에 하기 설정을 해줘야함..
		$.ajaxSetup({
		    beforeSend: function(xhr, settings) {
		        if (!/^(GET|HEAD|OPTIONS|TRACE)$/i.test(settings.type) && !this.crossDomain) {
		    		var token = $("meta[name='_csrf']").attr("content");
		    		xhr.setRequestHeader('X-CSRF-TOKEN', token);
		        }
		    }
		});
		
	}());

	$(document).ready(function(){
		$("#loginForm").validate({
			errorClass: "has-error",
			validClass: "has-success",
			rules: {
				username: {
					required: true,
					email: true,
					remote: {
						url: "${pageContext.request.contextPath}/account/exist",
						data: {
							username: function() {
								return $("#username").val();
							}
						},
						type: "POST"
					}
				},
				nickname: {
					required: true,
					minlength: 2,
					maxlength: 8
				},
				password: {
					required: true,
					minlength: 6,
					maxlength: 16,
				},
				rePassword: {
					required: true,
					minlength: 6,
					maxlength: 16,
					equalTo: "#password"
				}
			},
			messages: {
				username: {
					required: "<spring:message code="txt.required"/>",
					email: "<spring:message code="txt.invalidEmail"/>",
					remote: "<spring:message code="txt.duplicated"/>"
				},
				nickname: {
					required: "<spring:message code="txt.required"/>",
					minlength: "<spring:message code="txt.minLength" arguments="2"/>",
					maxlength: "<spring:message code="txt.maxLength" arguments="8"/>"
				},
				password: {
					required: "<spring:message code="txt.required"/>",
					minlength: "<spring:message code="txt.minLength" arguments="6"/>",
					maxlength: "<spring:message code="txt.maxLength" arguments="16"/>"
				},
				rePassword: {
					required: "<spring:message code="txt.required"/>",
					minlength: "<spring:message code="txt.minLength" arguments="6"/>",
					maxlength: "<spring:message code="txt.maxLength" arguments="16"/>",
					equalTo: "<spring:message code="txt.notMatch" arguments="16"/>"
				}
			},
			/** highlight : 에러 하이라이트 시 설정(기본은 label) */
			highlight: function(element, errorClass, validClass) {
				$(element).addClass(errorClass).removeClass(validClass);
				$(element).parent("div").removeClass(validClass).addClass(errorClass);
				
				// 기본 설정
				// $(element).addClass(errorClass).removeClass(validClass);
			    // $(element.form).find("label[for=" + element.id + "]")
			    // .addClass(errorClass);
			},
			/** unhighlight : 에러 언 하이라이트 시 설정 */
			unhighlight: function(element, errorClass, validClass) {
				$(element).removeClass(errorClass).addClass(validClass);
				$(element).parent("div").removeClass(errorClass).addClass(validClass);
			},
			submitHandler: function(form) {
				form.submit();
			}
		});
	});
</script>
</body>
</html>
