<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'body.jsp' starting page</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
    body{
        font-family: "microsoft yahei";
    }
    
    a{
        margin: 20px;
        font-size: 20px;
        text-decoration: none;
    }
    a:hover{
        color: orange;
    }
    img{
        width:500px;
        height:150px;
        margin-top:80px;
        margin-left:310px;
    }
</style>
</head>

<body>
    <h1 align="center">图书管理</h1>
    <p align="center">
        <a href="<c:url value='/admin/book/addPre.do'/>">添加图书</a>
        <a href="<c:url value='/adminjsps/admin/book/gj.jsp'/>">高级搜索</a>
    </p>
    <img src="/yosebook-ssm/images/ybig_logo.png">
</body>
</html>
