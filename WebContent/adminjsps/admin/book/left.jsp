<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'left.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/left.css'/>">
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
/*
 * 第一个参数必须和变量名相同
 */
	var bar = new Q6MenuBar("bar", "Yosebook书城");
	$(function() {
		bar.colorStyle = 2; //配色样式，共有5种
		bar.config.imgDir = "<c:url value='/menu/img/'/>";
		bar.config.radioButton = true; //多个一级分类是否排斥，意即是否能同时打开

		/*
		1. 程序设计：一级分类名称
		2. Java Javascript：二级分类名称
		3. /yosebook/jsps/book/list.jsp：点击二级分类后链接到的URL
		4. body:链接的内容在哪个框架页中显示
		*/
		<c:forEach items="${parents}" var="parent">
    		<c:forEach items="${parent.children}" var="child">
				bar.add("${parent.cname}", "${child.cname}", "/yosebook-ssm/admin/book/findByCategory.do?cid=${child.cid}", "body");
    		</c:forEach>
		</c:forEach>
		$("#menu").html(bar.toString());
	});
</script>
  </head>
  
  <body >
    <div id="menu"></div>
  </body>
</html>
