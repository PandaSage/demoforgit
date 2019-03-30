<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="false"/>
	
		<input type="hidden" name="i_sGetRetrunUrl" value=""/>
		<input type="hidden" name="i_sGetRetrunParams" value=""/>
		<div class="content-wrapper">
			<!-- content-header -->
			<section class="content-header">
				<h1>
					배치 / AIP 구동 현황
					<small>(일별)</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Simple tables</li>
				</ol>
			</section>

			<!-- content -->
			<section class="content">
				<h2 class="tit">실행결과(grid)</h2>
				<div class="scrBox_X">
					<table id="jqGrid"></table>
						<div id="jqGridPager"></div>
				</div>
				<br/>
				
				<h2 class="tit">실행결과(grid)</h2>
				<div class="scrBox_X">
					<table id="jqGrid2"></table>
						<div id="jqGridPager2"></div>
				</div>
				<br/>
				
				<h2 class="tit">실행결과(chart)</h2>
				<div class="scrBox_X">
					<div id="barchart_material" style="width: 900px; height: 500px; overflow: hidden;"></div>
				</div>
			</section>
			<!-- //.content -->
		</div>
		<!-- //.content-wrapper -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" flush="false">
		<jsp:param name="includeJs" value="/main/main.js"/>
	</jsp:include>
