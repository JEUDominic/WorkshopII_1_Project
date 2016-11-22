<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pie Chart</title>
<script type="text/javascript"></script>
<script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>
<body>
<% 
	String piadata1 = (String)request.getAttribute("chartData1");  
    String piadata2 = (String)request.getAttribute("chartData2");  
%>
<div id="chartContainer1" style="height: 400px; width: 100%;"></div>
</body>

<script type="text/javascript">

window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer1",
    {
        theme: "theme2",
        title:{
            text: "Gaming Consoles Sold in 2012"
        },        
        data: [
            {       
                type: "pie",
                showInLegend: true,
                toolTipContent: "{y} - #percent %",
                yValueFormatString: "#,##0,,.## Million",
                legendText: "{indexLabel}",
                dataPoints:<%out.print(piadata2);%>
            }
        ]
    });
    chart.render();

}


</script>

</html>