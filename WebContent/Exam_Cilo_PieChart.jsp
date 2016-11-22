<%session.setAttribute("title","CILO Pie Chart"); %>
<%@ include file="header.jsp" %> 
<!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="home.jsp">Home<span class="sr-only">(current)</span></a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Course Name">
        </div>
        <button type="submit" class="btn btn-default">Search</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <a class="navbar-brand" ><img src="img/icon.png"></a>
        <li><a href="logout.jsp">Log Out</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">It Can Dropdown <span class="caret"></span></a>
        	<ul class="dropdown-menu">
            	<li><a href="home.html">But There Is</a></li>
            	<li><a href="course.html">No Link</a></li>
            	<li><a href="addcourse.html">Just Show It For</a></li>
            	<li><a href="addpaper.html">INTERESTING</a></li>
            	<li role="separator" class="divider"></li>
            	<li><a href="joincourse.html">23333</a></li>
          	</ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  	</div><!-- /.container-fluid -->
  	</nav><!-- /.navar -->
	</div><!-- header -->
	
	<div class="row">
  		<div class="col-md-8">
  			<div class="aaa">
  			<div class="panel-heading">
  				<h3 class="panel-title">Pie Chart</h3>
  				</div><!-- panel-heading -->
  			<div class="panel-body">
  			<div class="col-md-4">
<% 
    String piadata2 = (String)request.getAttribute("chartData2");  
%>


<div id="chartContainer1" style="height: 400px; width: 100%;"></div>

<script type="text/javascript">

window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer1",
    {
        theme: "theme2",
        title:{
            text: "Coverage of different CILOs in Examination"
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
</script>
</div><!--col-md-4-->
<div class="col-md-8">
<% 
    String piadata = (String)request.getAttribute("chartData");  
	String piadata1 = (String)request.getAttribute("chartData1");
%>
<div id="chart-container">FusionCharts XT will load here!</div>
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
        "categories": <%out.print(piadata1);%>,
        "dataset":<%out.print(piadata);%>
    }
}
);
    fusioncharts.render();
});
</script>
</div><!-- col-md-8 -->
</div><!-- panel-body-->
			</div><!-- panel panel-default -->
			
  		</div><!-- col-md-8 -->
  		<div class="col-md-4">
  			<div class="aaa">
  			<div class="panel-body">
      			<img src="img/touxiang.png" alt="..." class="img-thumbnail">
      			<div class="caption">
        		<h3><%=session.getAttribute("username") %></h3>
        		
        		<p>ID:<%=session.getAttribute("user_id") %></p>
        		<p>
        		<a href="addcourse.jsp" class="btn btn-primary" role="button">Add Course</a> 
        		<a href="#" class="btn btn-default" role="button">Button</a>
        		</p>
      			</div>
      		</div><!-- panel panel-default -->
			</div><!-- panel-body-->
  		</div><!-- col-md-4 -->
	</div><!-- row -->

</body>
</html>