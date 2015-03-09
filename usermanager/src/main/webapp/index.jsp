<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Users using ajax</title>
<script
	src="<c:url value='js/jquery.min.js'/>"
	type="text/javascript"></script>
	<script src="<c:url value='js/jquery.columns.min.js'/>"></script>
	<link id="style" href="<c:url value='css/clean.css'/>" rel="stylesheet" media="screen">
</head>
<body>
	<input type="button" value="Add New User" onclick="add();" />
	<div id="userTable"></div>
</body>
<script type="text/javascript">
	// get the form values
	var name = $('#name').val();
	var surname = $('#surname').val();
	var phone = $('#phone').val();
	
	function add() {
		window.open("<c:url value='AddUser.htm'/>",null,
		"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
	}
	
	function del(id) {
		$.ajax({
			headers : {
				Accept : "text/plain; charset=utf-8",
				"Content-Type" : "text/plain; charset=utf-8"
			},
			type : "POST",
			url : "/usermanager/user/delete/"+id,
			success : function(response) {
				alert(response);
				refresh();
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	};
	
	function update(id) {
		window.open("<c:url value='user/update/"+id+".htm'/>",null,
		"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
	};
	
	function refresh(){
		$.ajax({
			headers : {
				Accept : "text/plain; charset=utf-8",
				"Content-Type" : "text/plain; charset=utf-8"
			},
			type : "POST",
			url : "/usermanager/user/list",
			data : "max=" + 3 + "&page=" + 0 + "&sorter=name&asc=" + false,
			success : function(response) {
				var resp = JSON.parse(response);
				$('#userTable').columns('setMaster',resp.data); 
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	};
	$.ajax({
		headers : {
			Accept : "text/plain; charset=utf-8",
			"Content-Type" : "text/plain; charset=utf-8"
		},
		type : "POST",
		url : "/usermanager/user/list",
		data : "max=" + 3 + "&page=" + 0 + "&sorter=name&asc=" + false,
		success : function(response) {
			var resp = JSON.parse(response);
			$('#userTable').columns({
				data:resp.data,
				schema: [
					{"header":"Name", "key":"name"},
					{"header":"Surname", "key":"surname"},
					{"header":"Phone", "key":"phone"},
					{"header":"Delete", "key":"id", "template":'<input type="button" value="Delete" onclick="del(\'{{id}}\');" />'},
					{"header":"Update", "key":"id", "template":'<input type="button" value="Update" onclick="update(\'{{id}}\');" />'}
				]
			}); 
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
</script>
</html>
