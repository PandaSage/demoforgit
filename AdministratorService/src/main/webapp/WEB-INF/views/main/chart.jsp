<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	<jsp:include page="/WEB-INF/views/include/header.jsp" flush="false"/>
		<input type="text" name="i_sGetRetrunUrl" value=""/>
		<input type="text" name="i_sGetRetrunParams" value=""/>
		<div class="content-wrapper">
			<!-- content-header -->
			<section class="content-header">
				<h1>
					Charts
					<small>차트</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Charts</li>
				</ol>
			</section>

			<!-- content -->
			<section class="content">
				<h2 class="tit">Bar Chart v1(반응형 X)</h2>
				<div class="scrBox_X">
					<div id="barchart_material" style="width: 900px; height: 500px; overflow: hidden;"></div>
				</div>

				<br/><br/><br/><br/>

				<h2 class="tit">Bar Chart v2 (반응형 X)</h2>
				<div class="scrBox_X">
					<div id="top_x_div" style="width: 900px; height: 500px; overflow: hidden;"></div>
				</div>

				<br/><br/><br/><br/>

				<h2 class="tit">Column Chart v1 (반응형 X)</h2>
				<div class="scrBox_X">
					<div id="columnchart_material" style="width: 900px; height: 500px; overflow: hidden;"></div>
				</div>

				<h2 class="tit">Pie Chart v1 (반응형 X)</h2>
				<div class="scrBox_X">
					<div id="piechart" style="width: 900px; height: 500px; overflow: hidden;"></div>
				</div>
			</section>
			<!-- //.content -->
		</div>
		<!-- //.content-wrapper -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" flush="false">
		<jsp:param name="includeJs" value="/main/chart.js"/>
	</jsp:include>
