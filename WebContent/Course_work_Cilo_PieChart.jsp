<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<html>
  <% 
   
	String piadata2 = (String)request.getAttribute("chartData2");
%> 
<head>
<title>My first chart using FusionCharts Suite XT</title>
<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/fusioncharts.js"></script>
<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/themes/fusioncharts.theme.fint.js?cacheBust=56"></script>
<script type="text/javascript" src="http://html2canvas.hertzen.com/build/html2canvas.js"></script>  
<script type="text/javascript" src="http://www.boolaw.com/tpl/default/js/jquery-1.8.3.min.js"></script> 
<script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script> 
        <script type="text/javascript">  
        var src=null;
        window.onload = function() { 
        	  print();
              chart();
            var dlButton = document.getElementById("downloadImageBtn");  
            bindButtonEvent(dlButton, "click", saveAsLocalImage);  
        };  
        
            function print(){ 
                html2canvas( $("#chartContainer") ,{            
                    onrendered: function(canvas){ 
                    	var url = canvas.toDataURL();
              			src=url;
                        $('#down_button').attr( 'href' , url );  
                        $('#down_button').attr( 'download' , 'myjobdeer.png' ) ;
                    }  
                });  
            } 
          
            function saveAsLocalImage () {  
            	
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
            
            function chart() {
                var chart = new CanvasJS.Chart("chartContainer",
                {
                	theme: "theme2",
                    title:{
                        text: "Coverage of different CILOs in course work"
                    },  
                    data: [
                        {       
                            type: "pie",
                            showInLegend: true,
                            indexLabel: "{name}: <strong>#percent %</strong>",
                            legendText: "{indexLabel}",
                            indexLabel: "#percent%", 
                           	toolTipContent: "{y} -- #percent %",
                            dataPoints:<%out.print(piadata2);%>
                        }
                    ]
                });
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
  
  <div id="chartContainer" style="height: 400px; width: 400px;"></div>
  <a type="button" id="down_button">download</a> 
  <button id="downloadImageBtn">Download Image</button>
 
</body>
 
</html>