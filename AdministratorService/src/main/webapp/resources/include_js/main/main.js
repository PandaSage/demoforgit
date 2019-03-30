google.charts.load('current', {'packages':['bar']});

//Bar Chart v1
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
	var data = google.visualization.arrayToDataTable([
		['Date', 'success', 'error', 'not work'],
		['10/01', 1000, 400, 200],
		['10/02', 1170, 460, 250],
		['10/03', 660, 1120, 300],
		['10/04', 1030, 540, 350]
	]);

	var options = {
		chart: {
			title: 'Company Performance',
			subtitle: 'Sales, Expenses, and Profit: 2014-2017',
		},
		bars: 'horizontal' // Required for Material Bar Charts.
	};

	var chart = new google.charts.Bar(document.getElementById('barchart_material'));
	chart.draw(data, google.charts.Bar.convertOptions(options));
};

$(document).ready(function () {
	//grid width size config
	var	gridWidth = $('.content-wrapper').width() - 50;
	$("#jqGrid").jqGrid({
		url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
		mtype: "GET",
		datatype: "jsonp",
		colModel: [
			{ label: 'OrderID', name: 'OrderID', key: true, width: 75 },
			{ label: 'Customer ID', name: 'CustomerID', width: 150 },
			{ label: 'Order Date', name: 'OrderDate', width: 150,
			formatter : 'date', formatoptions: { srcformat : 'Y-m-d H:i:s', newformat :'ShortDate'}},
			{ label: 'Freight', name: 'Freight', width: 150 },
			{ label:'Ship Name', name: 'ShipName', width: 150 }
		],
		viewrecords: true,
		width: gridWidth,
		height: 230,
		rowNum: 30,
		pager: "#jqGridPager"
	});
	
	$("#jqGrid2").jqGrid({
		url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
		mtype: "GET",
		datatype: "jsonp",
		colModel: [
			{ label: 'OrderID', name: 'OrderID', key: true, width: 75 },
			{ label: 'Customer ID', name: 'CustomerID', width: 150 },
			{ label: 'Order Date', name: 'OrderDate', width: 150,
				formatter : 'date', formatoptions: { srcformat : 'Y-m-d H:i:s', newformat :'ShortDate'}},
				{ label: 'Freight', name: 'Freight', width: 150 },
				{ label:'Ship Name', name: 'ShipName', width: 150 }
				],
				viewrecords: true,
				width: gridWidth,
				height: 230,
				rowNum: 30,
				pager: "#jqGridPager2"
	});
	
	//content 사이즈에 맞게 초기 width 설정
	/*jQuery('#jqGrid').jqGrid( 'setGridWidth', $('.content-wrapper').width() - 50 );*/
});