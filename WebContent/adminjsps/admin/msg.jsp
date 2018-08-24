<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'mgs.jsp' starting page</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<style type="text/css">
body {
	background: rgb(254, 238, 189);
}
h2{
    margin-left: 40px;
}
</style>
<body>
    <h2>${msg }</h2>
    <ul>
        <c:forEach items="${links }" var="link">
            <li>${link }</li>
        </c:forEach>
    </ul>
</body>
</html>
