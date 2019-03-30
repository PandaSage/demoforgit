<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<!-- footer -->
		<footer class="footer">
			<strong>Copyright &copy; 2017 Ishift.</strong> All rights reserved.
		</footer>
		<!-- //.footer -->

		<!-- control-sidebar-->
		<aside class="control-sidebar">
		<!-- control-sidebar-tabs -->
		<ul class="control-sidebar-tabs">
			<li class="active">
				<a href="#" data-toggle="tab">
					<i class="fa fa fa-wrench"></i>
				</a>
			</li>
			<li>
				<a href="#" data-toggle="tab">
					<i class="fa fa-home"></i>
				</a>
			</li>
			<li>
				<a href="#" data-toggle="tab">
					<i class="fa fa-gears"></i>
				</a>
			</li>
		</ul>
		</aside>
		<!-- //.control-sidebar -->
	</div>
	<!-- ./wrapper -->
	<!-- jquery -->
	<script type="text/javascript" src="/resources/js/jquery/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery/jquery.easing.1.3.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery/jquery-ui.js"></script>
	
	<!-- jqGride -->
	<script type="text/javascript" src="/resources/js/jquery/grid.locale-kr.js"></script>
	<script type="text/javascript" src="/resources/js/jquery/jquery.jqGrid.js"></script>
	
	<!-- default js -->
	<script type="text/javascript" src="/resources/js/default.js"></script>
	
	<!-- chart -->
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script src=<%="/resources/include_js" + (String)request.getParameter("includeJs")%>></script>
</body>
</html>