
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Add Users using ajax</title>
<script
	src="<c:url value='js/jquery.min.js'/>"
	type="text/javascript"></script>
	<script src="<c:url value='js/jquery.maskedinput.js'/>"></script>
        <script type="text/javascript">
//         var strings = new Array();
// 		strings['recaptcha.instructions_visual'] = "<spring:message code='recaptcha.instructions_visual' javaScriptEscape='true'/>";
// 		strings['recaptcha.instructions_audio'] = "<spring:message code='recaptcha.instructions_audio' javaScriptEscape='true'/>"; 
// 		strings['recaptcha.play_again'] = "<spring:message code='recaptcha.play_again' javaScriptEscape='true'/>";
// 		strings['recaptcha.cant_hear_this'] = "<spring:message code='recaptcha.cant_hear_this' javaScriptEscape='true'/>";
// 		strings['recaptcha.visual_challenge'] = "<spring:message code='recaptcha.visual_challenge' javaScriptEscape='true'/>";
// 		strings['recaptcha.audio_challenge'] = "<spring:message code='recaptcha.audio_challenge' javaScriptEscape='true'/>";
// 		strings['recaptcha.refresh_btn'] = "<spring:message code='recaptcha.refresh_btn' javaScriptEscape='true'/>"; 
// 		strings['recaptcha.help_btn'] = "<spring:message code='recaptcha.help_btn' javaScriptEscape='true'/>";
// 		strings['recaptcha.incorrect_try_again'] = "<spring:message code='recaptcha.incorrect_try_again' javaScriptEscape='true'/>";
	
// 		var RecaptchaOptions = {
// 		custom_translations : {		 
// 			 instructions_visual :  strings['recaptcha.instructions_visual'] ,
// 			 instructions_audio : strings['recaptcha.instructions_audio'],
// 			 play_again : strings['recaptcha.play_again'],
// 			 cant_hear_this : strings['recaptcha.cant_hear_this'],
// 			 visual_challenge : strings['recaptcha.visual_challenge'],
// 			 audio_challenge : strings['recaptcha.audio_challenge'],
// 			 refresh_btn : strings['recaptcha.refresh_btn'],
// 			 help_btn : strings['recaptcha.help_btn'],
// 			 incorrect_try_again : strings['recaptcha.incorrect_try_again']
// 		},		 
// 		theme : 'clean'
// 		};
        
        function doAjaxPost() {
        // get the form values
        var id = $('#id').val();
        var name = $('#name').val();
        var surname = $('#surname').val();
        var phone = $('#phone').val();
//         var recaptcha_response_field = $('recaptcha_response_field').val();
//         var recaptcha_challenge_field = $('recaptcha_challenge_field').val();
        
        $.ajax({
        type: "POST",
        url: "/usermanager/user/save",
//         data: "id=" + id + "&name=" + name + "&surname=" + surname + "&phone=" + phone + "&recaptcha_response_field=" + recaptcha_response_field + "&recaptcha_challenge_field=" + recaptcha_challenge_field,
        data: "id=" + id + "&name=" + name + "&surname=" + surname + "&phone=" + phone,
        success: function(response){
        // we have the response
        var resp = JSON.parse(response);
        alert(resp.message);
        if(resp.status) {
        	window.close();
        }
        },
        error: function(e){
        alert('Error: ' + e);
        }
        });
        }
        </script>
    </head>
    <body>
        <h1>Add User</h1>
        <table>
            <tr><td><input type="hidden" disabled="disabled" id="id" value="${user.id}"></td></tr>
            <tr><td>Name : </td><td> <input type="text" id="name" value="${user.name}"><br/></td></tr>
            <tr><td>Surname : </td><td> <input type="text" id="surname" value="${user.surname}"><br/></td></tr>
            <tr><td>Phone : </td><td> <input type="text" id="phone" value="${user.phone}"><br/></td></tr>
            <tr><td colspan="2"><input id="save" type="button" value="Add User" onclick="doAjaxPost()"><br/></td></tr>
<!--             <tr><td colspan="2"><div id="info" style="color: green;"></div></td></tr> -->
<!-- 			<tr> -->
			<!--%
		        ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LcW3OASAAAAAKEJTHMmp_bo5kny4lZXeDtgcMqC", 
		        					"6LcW3OASAAAAAKVX2duVsSy2uMMHL105-jPDrHMD", false);
		        out.print(c.createRecaptchaHtml(null, null));
		    %-->
<!-- 		    </tr> -->
        </table>
<!--         <a href="/usermanager/ShowUsers.htm">Show All Users</a> -->
    </body>
    <script type="text/javascript">
        if("${user.id}" !== "") {
        	$('#save').attr('value', 'Update User');
        }
        $("#phone").mask("(999) 999-9999");
    </script>
</html>
