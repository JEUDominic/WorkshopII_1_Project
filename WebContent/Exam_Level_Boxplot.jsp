<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>My first chart using FusionCharts Suite XT</title>

</head>
<body>
<% 
    String piadata1 = (String)request.getAttribute("chartData1");  
%>
  <div id="chart-container">FusionCharts XT will load here!</div>
</body>
<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/fusioncharts.js"></script>
<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/themes/fusioncharts.theme.fint.js?cacheBust=56"></script>
<script type="text/javascript">
  FusionCharts.ready(function(){
    var fusioncharts = new FusionCharts({
    type: 'boxandwhisker2d',
    renderAt: 'chart-container',
    width: '500',
    height: '350',
    dataFormat: 'json',
    dataSource: {
        "chart": {
            "caption": "Coverage of difference cognitive levels in exam paper",
            "subcaption": "",
            "xAxisName": "Cognitive",
            "YAxisName": "Percentage of marks",
            "numberPrefix": "",
            "theme": "fint",
            "usePlotGradientColor": "",
            "showValues": "0",
            "showMean": "1"
        },
        "categories": [{
            "category": [{
                "label": "Knowledge"
            }, {
                "label": "Comprehension"
            }, {
                "label": "Application"
            }, {
                "label": "Analysis"
            }, {
                "label": "Synthesis"
            }, {
                "label": "Education"
            }]
        }],
        "dataset":<%out.print(piadata1);%>
    }
}
);
    fusioncharts.render();
});
</script>
</html>