<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>body.jsp</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
        body{
            background: #4E4E4E;
        }
        img{
            width: 800px;
            margin-top: 80px;
            margin-left: 270px;
        }
        p{
            text-align: center;
            font-size: 38px;
            font-family: "microsoft yahei";
            color: #8AD700;
        }
    </style>
  </head>
  
  <body style="margin: 0px;">
    <img src="<c:url value='/images/ybig_logo.png'/>" />
    <p>欢迎使用Yosebook书城！</p>
  </body>
</html>
