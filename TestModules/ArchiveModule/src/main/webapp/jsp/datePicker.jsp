<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="../css/jquery-ui.css" type="text/css" media="all" />
		<link rel="stylesheet" href="../css/base.css" type="text/css"/>
		<script src="../js/jquery.min.js" type="text/javascript"></script>
		<script src="../js/jquery-ui.min.js" type="text/javascript"></script> 
		<script type="text/javascript">
			$(function () {
				$('#fromDate').datepicker({ dateFormat: 'yy/mm/dd' });
				$('#fromDate').datepicker();
				$('#toDate').datepicker({ dateFormat: 'yy/mm/dd' });
				$('#toDate').datepicker();
			});
		</script>
		<script>
			checked = false;
			function checkedAll () {
				if (checked == false){
					checked = true
				}else{
					checked = false
				}
				for (var i = 0; i < document.getElementById('myform').elements.length; i++) {
						document.getElementById('myform').elements[i].checked = checked;
				}
			}
			
		</script> 
		<style>
			.ui-datepicker th { background-color: #CCCCFF; }
		</style>
	</head>
	<body  >
		<form action="/getJPA" method="post" id="myform">
			<div class="base">
				<div class="leftBase">
					<table id="tblTest">
						 <tr>
							 <td>From Date : </td>
							 <td><input type="text" id="fromDate" value="" name="fromDate"  /></td>
							 <td>To Date : </td>
							 <td><input type="text" id="toDate" value="" name="toDate"  /></td>
						 </tr>
					</table>
				</div>
				<div class="rightBase">
					<input type="checkbox" value="ALL" onclick="checkedAll();">All Regions<br>
					<input type="checkbox" name="regions" value="LON">London<br>
					<input type="checkbox" name="regions" value="SW_ENG">South West England<br>
					<input type="checkbox" name="regions" value="NE_ENG">North East England<br>
					<input type="checkbox" name="regions" value="NW_ENG_N_WAL">North West England and North Wales<br>
					<input type="checkbox" name="regions" value="SC_ENG">South Central England<br>
					<input type="checkbox" name="regions" value="C_SCO">Scotland Central<br>
					<input type="checkbox" name="regions" value="E_ENG">Eastern England<br>
					<input type="checkbox" name="regions" value="N_SCO">Scotland North<br>
					<input type="checkbox" name="regions" value="S_WAL">South Wales<br>
					<input type="checkbox" name="regions" value="N_IRE">Northern Ireland<br>
					<br><br>
					<input type="submit" value="Continue"><br>
				</div>
				
			</div>
		</form>
	</body>
</html>