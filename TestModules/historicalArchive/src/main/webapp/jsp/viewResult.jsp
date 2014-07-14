
<%@ page import="com.nee.beans.MetoDataJPA" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="../css/tableCss.css" type="text/css"/>
		<script>
			$(document).ready(function(){
	   			$("tr:even").addClass("even");
	  			$("tr:odd").addClass("odd");
			});
		</script>
	</head>
	<body>
		<%
			
			List<MetoDataJPA> list = (List<MetoDataJPA>)request.getAttribute("list");
			
		%>
		<div class="mainResult">
			<div class="result">			
				<table class="tableCss">
					<tr>
						<th>Date</th>
						<th>Regions</th>
						<th>Max Temperature(Deg C)</th>
						<th>Pressure</th>
						<th>Max temp day</th>
						<th>Min temp day</th>
						<th>Max feels like day</th>
						<th>min temp night</th>
						<th>sunshine hours</th>
						<th>snow amount</th>
					</tr>					
					<%	for(MetoDataJPA temp : list){  %>
					<tr>
						<td><%=temp.getWeatherDate() %></td>
						<td><%=temp.getRegions() %></td>
						<td><%=temp.getTemperature() %></td>
						<td><%=temp.getPressure() %></td>
						
					</tr>
					<% } %>
					
					<% 
					String fromDate = (String)request.getAttribute("fromDate");
					String toDate = (String)request.getAttribute("toDate");
            		String[] regions = (String)request.getAttribute("regions"); %>

		            <div>
		                <form action="/meto/fetch/csv/<%=fromDate %>/<%=toDate%>/<%=regions %>">
		                    <input type="submit" value="Export data">
		                </form>
		            </div>

			        req.setAttribute("fromDate", fromDate);
			        req.setAttribute("regions", regions);
					
					
				</table>
			</div><br><br>
			<div>
				<form>
					<table>
						<tr class="lowerTable">
							<td>
								<input type="submit" value="Export Data">
							</td>
							<td>Email to user</td>
							<td>Print</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>