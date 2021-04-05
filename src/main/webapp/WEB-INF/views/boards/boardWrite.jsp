<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<title><spring:message code="main.title"/></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    
    <div class="container">

		<div class="page-header" id="banner">
			<div class="row">
				<%-- <div class="col-lg-8 col-md-7 col-sm-6">
					<h1><spring:message code="txt.write"/></h1>
					<p class="lead">Material is the metaphor</p>
				</div> --%>
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
							<h1 id="containers"><spring:message code="txt.write"/></h1>
						</div>
					</div>
					
					<div class="col-lg-12 col-md-7 col-sm-6">
						<form id="boardContentForm" class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/boards/write">
							<fieldset>
								<!-- <legend>Legend</legend> -->
								<div class="form-group">
									<label for="inputEmail" class="col-lg-1 control-label"><spring:message code="txt.title"/></label>
									<div class="col-lg-11">
										<input type="text" class="form-control" id="title" name="title" value="${boardVO.title }" placeholder="<spring:message code="txt.title"/>">
									</div>
								</div>
								
								<div class="form-group">
									<label for="textArea" class="col-lg-1 control-label"><spring:message code="txt.content"/></label>
									<div class="col-lg-11">
										<textarea class="form-control" rows="3" id="textArea">${boardVO.content }</textarea>
										<span class="help-block"><spring:message code="txt.blockHelper"/></span>
										<input type="hidden" id="content" name="content"/>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-lg-11 col-lg-offset-1">
										<button id="btnCancel" type="button" class="btn btn-default"><spring:message code="button.cancel"/></button>
										<c:choose>
											<c:when test="${type eq 'WRITE' }">
												<button id="btnConfirm" type="button" class="btn btn-primary"><spring:message code="button.confirm"/></button>
											</c:when>
											<c:when test="${type eq 'MODIFY' }">
												<button id="btnModify" type="button" class="btn btn-default">수정</button>
											</c:when>
											<c:when test="${type eq 'REPLY' }">
												<button id="btnReply" type="button" class="btn btn-default">등록</button>
											</c:when>
										</c:choose>
									</div>
								</div>
							</fieldset>
							
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" id="category" name="category" value="${category eq null ? boardVO.category : category }"><!-- 카테고리 -->
							<input type="hidden" id="no" name="no"/><!-- 글번호 -->
							<input type="hidden" id="family" name="family"/><!-- 부모글그룹 -->
							<input type="hidden" id="parent" name="parent"/><!-- 원글 -->
							<input type="hidden" id="depth" name="depth"/><!-- 계층(원글은 0) -->
							<input type="hidden" id="indent" name="indent"/><!-- 들여쓰기(원글은 0) -->
							<input type="hidden" id="writer" name="writer"/><!-- 작성자 -->
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	</div>

</body>
  
  <script type="text/javascript">
  	$(document).ready(function(){
  		
  		if("${type}" == "REPLY"){
//   			$("#title").val("ㄴRe : " + $("#title").val());
  			$("#textArea").val($("#textArea").val() + "<br>============================<br>ㄴRe: ");
  		}
  		
  		CKEDITOR.config.language = '<spring:message code="app.locale"/>';
  		
        // ckeditorImageUpload.jsp 따로 필요 없이 바로 페이지에서 실행
        // CKEDITOR.config.filebrowserImageUploadUrl = '/myapp/file/upload/ckeditorjs?${_csrf.parameterName}=${_csrf.token}';	//이미지 업로드 경로

        // ckeditorImageUpload.jsp 가 실행
        CKEDITOR.config.filebrowserImageUploadUrl = '/myapp/file/upload/ckeditor?${_csrf.parameterName}=${_csrf.token}';	//이미지 업로드 경로
		CKEDITOR.config.filebrowserImageBrowseUrl= '/myapp/file/upload/list';

        
        CKEDITOR.replace('textArea');
        $("#btnCancel").on("click", function(){
        	location.href = document.referrer;
        });
        
        $("#btnConfirm").on("click", function(){
        	if(confirm("게시글을 등록하시겠습니까?")){
        		$("#no").val(0);
        		$("#family").val(0);
        		$("#parent").val(0);
        		$("#depth").val(0);
        		$("#indent").val(0);
	        	var data = CKEDITOR.instances.textArea.getData();
	        	$("#content").val(data);
	        	$("#boardContentForm").attr("action", "${pageContext.request.contextPath}/boards/write").submit();
// 	        	$("#boardContentForm").submit();
        	}
        });
        
        $("#btnModify").on("click", function(){
        	if(confirm("게시글을 수정하시겠습니까?")){
        		$("#no").val("${boardVO.no }");
        		$("#family").val("${boardVO.family }");
        		$("#parent").val("${boardVO.parent }");
        		$("#depth").val("${boardVO.depth }");
        		$("#indent").val("${boardVO.indent }");
        		$("#writer").val("${boardVO.writer }");
	        	var data = CKEDITOR.instances.textArea.getData();
        		$("#content").val(data);
	        	$("#boardContentForm").attr("action", "${pageContext.request.contextPath}/boards/modify/${boardVO.no }").submit();
        	}
        });
        
        $("#btnReply").on("click", function(){
	        	$("#no").val(0);
	        	$("#title").val("[Re:${boardVO.no}]" + $("#title").val());
        		$("#family").val("${boardVO.family }");
        		$("#parent").val("${boardVO.no }");
        		$("#depth").val(parseInt("${boardVO.depth }") + 1);
        		$("#indent").val(parseInt("${boardVO.indent }") + 1);
        	if(confirm("답글을 등록하시겠습니까?")){
	        	var data = CKEDITOR.instances.textArea.getData();
        		$("#content").val(data);
	        	$("#boardContentForm").attr("action", "${pageContext.request.contextPath}/boards/reply").submit();
        	}
        });
  	});
  	
  	/** ckeditorUploadList.jsp에서 호출하는 function */
  	function updateValue(id, value) {
  	    $(".cke_dialog_ui_input_text").each(function (index) {
  	        if ($(this).prop('tagName') == 'INPUT' && $(this).attr('type') == 'text' && $(this).attr('aria-required') == 'true') {
  	            $(this).val(value);
  	        }
  	    });
  	}
  </script>
</html>
