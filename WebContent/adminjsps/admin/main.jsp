<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>main.jsp</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
    href="<c:url value='/adminjsps/admin/css/main.css'/>">
</head>

<body style="overflow: hidden;">
    <table class="table" align="center">
        <tr>
            <td colspan="2" align="center" height="110px;"><iframe
                    frameborder="0" style="border-radius: 7px;padding:0;"
                    src="<c:url value='/adminjsps/admin/top.jsp'/>"
                    name="top"></iframe></td>
        </tr>
        <tr>
            <td><iframe frameborder="0" style="border-radius: 7px;" 
                    src="<c:url value='/adminjsps/admin/body.jsp'/>"
                    name="body"></iframe></td>
        </tr>
    </table>
</body>
</html>
