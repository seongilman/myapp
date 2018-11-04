<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a href="${pageContext.request.contextPath}" class="navbar-brand">Seongilman</a>
			<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
       
		<div class="navbar-collapse collapse" id="navbar-main">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes"><spring:message code="txt.me"/><span class="caret"></span></a>
					<ul class="dropdown-menu" aria-labelledby="themes">
						<li><a href="${pageContext.request.contextPath}/me/profile"><spring:message code="txt.profile"/></a></li>
						<!-- <li><a href="#">Menu 1-2</a></li> -->
						<!-- <li><a href="#">Menu 1-3</a></li> -->
					</ul>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes"><spring:message code="txt.board"/><span class="caret"></span></a>
					<ul class="dropdown-menu" aria-labelledby="themes">
						<li><a href="${pageContext.request.contextPath}/boards/standard?pageNo=1"><spring:message code="txt.standard"/></a></li>
						<!-- <li><a href="../united/">United</a></li> -->
						<!-- <li><a href="../yeti/">Yeti</a></li> -->
					</ul>
				</li>
				<li>
					<a href="http://seongilman.tistory.com"><spring:message code="txt.blog"/></a>
				</li>
           
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="download">Menu 2<span class="caret"></span></a>
					<ul class="dropdown-menu" aria-labelledby="download">
						<li><a href="#">Menu 2-1</a></li>
						<li><a href="#">Menu 2-2</a></li>
					</ul>
				</li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="isAnonymous()">
					<%-- <li>
						<a style="background: url('${pageContext.request.contextPath}/resources/common/image/default-profile-thumbnail.jpg') center center no-repeat;background-size: 25px;"></a>
					</li> --%>
					<li>
						<a href="${pageContext.request.contextPath}/account/login"><spring:message code="txt.login"/></a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/account/join"><spring:message code="txt.regist"/></a>
					</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<c:url value="/j_spring_security_logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
					<li>
						<a href="#" onclick="$('#logoutForm').submit()"><sec:authentication property="principal.nickname" /> | Logout</a>
					</li>
				</sec:authorize>
			</ul>

		</div>
	</div>
</div>