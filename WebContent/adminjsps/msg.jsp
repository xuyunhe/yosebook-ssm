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
        font-size: 15px;
        color: #000;
        font-family: "microsoft yahei";
    	background: #e4e4e4;
    }
    input{
        margin-left: 20px;
        margin-top: 20px;
        color: #fff;
        width: 80px;
        height: 25px;
        background: #E74C3D;
        border: 1px solid #E74C3D;
        border-radius: 5px;
    }
    input:hover{
        background: #C93121;
        cursor: pointer;
    }
</style>
<body>
    <h2>${msg }</h2>
    <input type="button" value="返    回" onclick="history.go(-1)" />
</body>
</html>
