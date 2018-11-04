<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					<p class="lead">Content</p>
				</div> --%>
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
							<h1 id="containers">${category }</h1>
						</div>
					</div>
				</div>
				
				<div class="col-lg-12 col-md-7 col-sm-6">
					<form class="form-horizontal">
						<fieldset>
							<!-- <legend>Legend</legend> -->
							<div class="form-group">
								<label for="title" class="col-lg-1 control-label"><spring:message code="txt.title"/></label>
								<div class="col-lg-11">
									<h6>${boardVO.title }</h6>
								</div>
							</div>
							
							<div class="form-group">
								<label for="writer" class="col-lg-1 control-label"><spring:message code="txt.writer"/></label>
								<div class="col-lg-6">
									<h6>${boardVO.nickname }</h6>
								</div>
								<label for="writer" class="col-lg-1 control-label"><spring:message code="txt.date"/></label>
								<div class="col-lg-2">
									<h6>${boardVO.regDt }</h6>
								</div>
								<label for="writer" class="col-lg-1 control-label"><spring:message code="txt.hits"/></label>
								<div class="col-lg-1">
									<h6>${boardVO.hits }</h6>
								</div>
							</div>
							
							<div class="form-group">
								<label for="textArea" class="col-lg-1 control-label"><spring:message code="txt.content"/></label>
								<div class="col-lg-11">
									${boardVO.content }
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-lg-11 col-lg-offset-1">
									<button id="btnList" type="button" class="btn btn-default"><spring:message code="txt.list"/></button>
									<c:if test="${fn:contains(pageContext.request.userPrincipal.authorities, 'ROLE_ADMIN') || boardVO.writer eq pageContext.request.userPrincipal.name}">
										<!-- ROLE_ADMIN(관리자) 이거나 작성자가 자신일 경우 -->
										<button id="btnModify" type="button" class="btn btn-default">수정</button>
										<button id="btnDelete" type="button" class="btn btn-default">삭제</button>
									</c:if>
									<c:if test="${boardVO.depth < 1 }">
										<button id="btnReply" type="button" class="btn btn-default">답글</button>
									</c:if>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btnList").on("click", function(){
			location.href = "${pageContext.request.contextPath}/boards/${category}";
		});
		
		$("#btnModify").on("click", function(){
			location.href = "${pageContext.request.contextPath}/boards/modify/${boardVO.no}";
		});
		
		$("#btnReply").on("click", function(){
			location.href = "${pageContext.request.contextPath}/boards/reply/${boardVO.no}";
		});
	});
</script>
</body>
  
</html>
