<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>

<!-- CKEDITOR 이미지 업로드 -->

<!-- 
	* ${CKEditorFuncNum }은 CKEditor가 파일을 전송할 때 자동으로 'CKEditorFuncNum'라는 GET 타입의 데이터를 전송(callback function)한 값을 리턴받는다.
-->

<script type="text/javascript">
	window.parent.CKEDITOR.tools.callFunction("${CKEditorFuncNum}", "${fileUrl}", '${message}');
</script>
</body>
</html>