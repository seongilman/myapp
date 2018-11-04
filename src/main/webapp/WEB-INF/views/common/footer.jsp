<footer>
	<div class="row">
		<div class="col-lg-12">

			<ul class="list-unstyled">
				<li class="pull-right"><a href="#top">Back to top</a></li>
				<li><a href="http://seongilman.tistory.com">Blog</a></li>
				<li><a href="http://www.facebook.com/seongilman">Facebook</a></li>
				<li><a href="https://www.instagram.com/ilman.seong">Instagram</a></li>
				<li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ko">
					<img alt="" src="${pageContext.request.contextPath}/resources/common/image/ko.png" width="60%"></a>
				</li>
				<li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=us">
					<img alt="" src="${pageContext.request.contextPath}/resources/common/image/us.png" width="55%"></a>
				</li>
			</ul>
		</div>
	</div>

</footer>

<script src="${pageContext.request.contextPath}/resources/common/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/custom.js"></script>
