
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
			String fromDate = ((String)request.getAttribute("fromDate")).replaceAll("/", "");
			String toDate = ((String)request.getAttribute("toDate")).replaceAll("/", "");
			
			// Don't get array here, instead set it in servlet as a string
			String[] regions = (String[])request.getAttribute("regions");
			String url = "/meto/fetch/csv/" + fromDate + "/" + toDate + "/" + "LON";
		%>
		<div class="mainResult">
		AA <%= url %>
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

		            <div>
		                <form action="<%= url %>">
		                    <input type="submit" value="Export data">
		                </form>
		            </div>
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