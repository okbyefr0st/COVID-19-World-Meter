<script type="text/javascript">
window.onload = function() { 
 
var dataPoints = [];
 
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	theme: "light2",
 	zoomEnabled: true,
	title: {
		text: "Total Biomass Energy Consumption"
	},
	axisY: {
		title: "Biomass Consumption (in Trillion BTU)",
		titleFontSize: 24,
		includeZero: false
	},
	data: [{
		type: "line",
		yValueFormatString: "#,##0.0## Trillion BTU",
		xValueType: "dateTime",
		dataPoints: dataPoints
	}]
});
 
function addData(data) {
	for (var i = 0; i < data.length; i++) {
		dataPoints.push({
			x: data[i].timestamp,
			y: data[i].value
		});
	}
	chart.render();
}
 
$.getJSON("https://canvasjs.com/data/gallery/jsp/total-biomass-energy-consumption.json", addData);
 
}
</script>