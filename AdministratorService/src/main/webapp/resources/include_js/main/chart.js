	google.charts.load('current', {'packages':['bar']});

	//Bar Chart v1
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
			['Year', 'Sales', 'Expenses', 'Profit'],
			['2014', 1000, 400, 200],
			['2015', 1170, 460, 250],
			['2016', 660, 1120, 300],
			['2017', 1030, 540, 350]
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
	}


	//Bar Chart v2
	google.charts.setOnLoadCallback(drawStuff);

	function drawStuff() {
		var data = new google.visualization.arrayToDataTable([
			['Opening Move', 'Percentage'],
			["King's pawn (e4)", 44],
			["Queen's pawn (d4)", 31],
			["Knight to King 3 (Nf3)", 12],
			["Queen's bishop pawn (c4)", 10],
			['Other', 3]
		]);

		var options = {
			title: 'Chess opening moves',
			width: 900,
			legend: { position: 'none' },
			chart: { title: 'Chess opening moves',
			       subtitle: 'popularity by percentage' },
			bars: 'horizontal', // Required for Material Bar Charts.
			axes: {
				x: {
					0: { side: 'top', label: 'Percentage'} // Top x-axis.
				}
			},
			bar: { groupWidth: "90%" }
		};

		var chart = new google.charts.Bar(document.getElementById('top_x_div'));
		chart.draw(data, options);
	};

	//Column Chart v1
	google.charts.setOnLoadCallback(columnChart);

	function columnChart() {
		var data = google.visualization.arrayToDataTable([
			['Year', 'Sales', 'Expenses', 'Profit'],
			['2014', 1000, 400, 200],
			['2015', 1170, 460, 250],
			['2016', 660, 1120, 300],
			['2017', 1030, 540, 350]
		]);

		var options = {
			chart: {
			title: 'Company Performance',
			subtitle: 'Sales, Expenses, and Profit: 2014-2017',
			}
		};

		var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

		chart.draw(data, google.charts.Bar.convertOptions(options));
	}

	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(pieChart);
	function pieChart() {
		var data = google.visualization.arrayToDataTable([
			['Language', 'Speakers (in millions)'],
			['Assamese', 13], ['Bengali', 83], ['Bodo', 1.4],
			['Dogri', 2.3], ['Gujarati', 46], ['Hindi', 300],
			['Kannada', 38], ['Kashmiri', 5.5], ['Konkani', 5],
			['Maithili', 20], ['Malayalam', 33], ['Manipuri', 1.5],
			['Marathi', 72], ['Nepali', 2.9], ['Oriya', 33],
			['Punjabi', 29], ['Sanskrit', 0.01], ['Santhali', 6.5],
			['Sindhi', 2.5], ['Tamil', 61], ['Telugu', 74], ['Urdu', 52]
		]);

		var options = {
			title: 'Indian Language Use',
			legend: 'none',
			pieSliceText: 'label',
			slices: {   4: {offset: 0.2},
						12: {offset: 0.3},
						14: {offset: 0.4},
						15: {offset: 0.5},
			},
		};

		var chart = new google.visualization.PieChart(document.getElementById('piechart'));
		chart.draw(data, options);
	}