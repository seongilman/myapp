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

<!-- CKEDITOR 이미지 업로드 리스트 -->
<c:forEach items="${uploadList }" var="list" varStatus="status">
	<a href="#" class="returnImage" id="${list}">
		<img src="${list}" alt="Hejsan" id="image_${status.count }" width="200" height="200" />
	</a>
</c:forEach>

<script type="text/javascript">
    $(document).ready(function () {
        $(".returnImage").click("click", function (e) {
            var urlImage = $(this).attr("id");
            //alert(urlImage);
            window.opener.updateValue("cke_67_textInput", urlImage);
            window.close();
        });
    });
</script>
</body>
</html>