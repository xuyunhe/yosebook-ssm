<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>信息板</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 14px;
		color: #404040;
		font-family: "microsoft yahei";
	}
	
	.divBody {
		margin:20px auto;
	}
	
	.divTitle {
		text-align:left;
		width: 900px;
		height: 25px;
		line-height: 25px;
		background-color: #efeae5;
		border: 5px solid #efeae5;
        border-radius: 10px 10px 0px 0px;
	}
	.divContent {
		width: 900px;
		height: 230px;
		border: 5px solid #efeae5;
		margin-right:20px; 
		margin-bottom:20px;
        border-radius: 0 0 10px 10px;
	}
	.spanTitle {
		margin-top: 10px;
		margin-left:10px;
		height:25px;
		font-weight: 900;
	}
a {text-decoration: none;}
a:visited {color: #018BD3;}
a:hover {color:#FF6600; text-decoration: underline;}
}
</style>

  </head>
  
  <body>
  <c:choose>
  	<c:when test="${code eq 'success' }">
  		<c:set var="img" value="/images/duihao.jpg"/>
  		<c:set var="title" value="成功"/>
  	</c:when>
  	<c:when test="${code eq 'error' }">
  		<c:set var="img" value="/images/cuohao.png"/>
  		<c:set var="title" value="失败"/>
  	</c:when>
  	
  </c:choose>
<div class="divBody">
	<div class="divTitle">
		<span class="spanTitle">${title }</span>
	</div>
	<div class="divContent">
	  <div style="margin: 20px;">
		<img style="float: left; margin-right: 30px;margin-top: 17px;" src="<c:url value='${img }'/>" width="150"/>
		<span style="font-size: 30px; color: #c30; font-weight: 900;">${msg }</span>
		<br/>
		<br/>
		<br/>
		<br/>
		<span style="margin-left: 50px;"><a target="_top" href="<c:url value='/jsps/user/login.jsp'/>">去登录</a></span>
		<span style="margin-left: 50px;"><a target="_top" href="<c:url value='/index.jsp'/>">返回主页</a></span>
	  </div>
	</div>
</div>


  </body>
</html>
