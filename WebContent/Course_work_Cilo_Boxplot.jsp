<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<html>
  <% 
    String piadata = (String)request.getAttribute("chartData");  
	String piadata1 = (String)request.getAttribute("chartData1");
	%> 
<head>
<title>My first chart using FusionCharts Suite XT</title>

<script  src="http://html2canvas.hertzen.com/build/html2canvas.js"></script>  
<script  src="http://www.boolaw.com/tpl/default/js/jquery-1.8.3.min.js"></script> 
<script  src="http://static.fusioncharts.com/code/latest/fusioncharts.js"></script>
<script  src="http://static.fusioncharts.com/code/latest/themes/fusioncharts.theme.fint.js?cacheBust=56"></script>
<script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script> 
        <script type="text/javascript">  
        var src=null;
        window.onload = function() { 
        	chart();
            var dlButton = document.getElementById("downloadImageBtn");  
            bindButtonEvent(dlButton, "click", saveAsLocalImage);  
        };  
        
            function print(){ 
                html2canvas( $("#div") ,{            
                    onrendered: function(canvas){ 
                    	var url = canvas.toDataURL();
              			src=url;
                        $('#down_button').attr( 'href' , url );  
                        $('#down_button').attr( 'download' , 'myjobdeer.png' ) ;
                    }  
                });  
            } 
          
            function saveAsLocalImage () {  
            	print();
            	 var type = 'png';
            	 var imgData = src;
            	 var _fixType = function (type) {
            	       type = type.toLowerCase().replace(/jpg/i, 'jpeg');
            	       var r = type.match(/png|jpeg|bmp|gif/)[0];
            	       return 'image/' + r;
            	  };
            	    
            	    // 加工image data，替换mime type
               imgData = imgData.replace(_fixType(type), 'image/octet-stream');
               window.location.href=imgData; // it will save locally  
            }  
            
            
            function chart(){
                var chart = new FusionCharts({
                type: 'boxandwhisker2d',
                renderAt: 'chartContainer',
                dataFormat: 'json',
                dataSource: {
                    "chart": {
                        "caption": "Coverage of difference cognitive levels in exam paper",
                        "xAxisName": "Cognitive",
                        "YAxisName": "Percentage of marks",
                        "numberPrefix": "",
                        "theme": "fint",
                        "usePlotGradientColor": "",
                        "showValues": "0",
                        "showMean": "1"
                    },
                    "categories": [{ "category" :[{ "label" : "cilo1" },{ "label" : "cilo2" },{ "label" : "cilo3" }] } ],
                    "dataset":[ {"seriesname": "marks","lowerboxcolor": "#008ee4", "upperboxcolor": "#6baa01", "data": [  { "value" : " 24,20,10,17,16,18,18,18,24,21,18,20,20,22,22,19,20,20,21 " },{ "value" : " 35,30,21,29,26,26,26,26,36,31,27,33,30,36,35,31,31,29,27 " },{ "value" : " 28,23,18,27,21,21,21,21,29,25,22,27,26,31,28,26,24,23,22 " }] }]

                }
            }
            );
                chart.render();
            }
            
          
        function bindButtonEvent(element, type, handler)  
        {  
               if(element.addEventListener) {  
                  element.addEventListener(type, handler, false);  
               } else {  
                  element.attachEvent('on'+type, handler);  
               }  
        }  
        
    
        
        </script>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
</head>
<body>
  <div id="div">
  12321312
  <div id="chartContainer" style="height: 400px; width: 400px;"></div>
  </div>
  <a type="button" id="down_button">download</a> 
  <button id="downloadImageBtn">Download Image</button>
 
</body>
 
</html>