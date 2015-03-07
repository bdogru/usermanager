
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Users using ajax</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	// get the form values
	var name = $('#name').val();
	var surname = $('#surname').val();
	var phone = $('#phone').val();

	$.ajax({
		headers : {
			Accept : "text/plain; charset=utf-8",
			"Content-Type" : "text/plain; charset=utf-8"
		},
		type : "POST",
		url : "/usermanager/user/get/54faf2225940bdf8e5f96e67",
		//         data: "name=" + name + "&surname=" + surname + "&phone=" + phone,
		success : function(response) {
			// we have the response
			console.dir(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
</script>
</head>
<body>
	<h1>Add Users using Ajax ........</h1>
	<table>
		<tr>
			<td>Name :</td>
			<td><input type="text" id="name"><br /></td>
		</tr>
		<tr>
			<td>Surname :</td>
			<td><input type="text" id="surname"><br /></td>
		</tr>
		<tr>
			<td>Phone :</td>
			<td><input type="text" id="phone"><br /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="Add Users"
				onclick="doAjaxPost()"><br /></td>
		</tr>
		<tr>
			<td colspan="2"><div id="info" style="color: green;"></div></td>
		</tr>
	</table>
	<a href="/usermanager/ShowUsers.htm">Show All Users</a>
</body>
</html>
