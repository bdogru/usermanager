
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users Added using Ajax</title>
    </head>
    <body style="color: green;">
    The following are the users added in the list :<br>
        <ul>
            <c:forEach items="${Users}" var="user">
                <li>Id : <c:out value="${user.id}" />; Name : <c:out value="${user.name}" />; Surname : <c:out value="${user.surname}"/>; Phone : <c:out value="${user.phone}"/></li>
            </c:forEach>
        </ul>
    </body>
</html>

