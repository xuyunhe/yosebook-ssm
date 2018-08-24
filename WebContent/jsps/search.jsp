<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>按书名查询</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	margin-top: 5px;
	margin-bottom: 0px;
	margin-left: 200px;
	color: #404040;
}

input {
	width: 300px;
	height: 30px;
	border-style: solid;
	margin: 0px;
	margin-left: 110px;
	margin-top: 10px;
	border-color: #15B69A;
    border-radius: 3px;
}

a {
	text-transform: none;
	text-decoration: none;
	border-width: 0px;
    color:#15B69A;
}

a:hover {
	border-width: 0px;
    color:#8AD700;
}

span {
	margin: 0px;
}
</style>
</head>

<body>
    <form action="<c:url value='/book/findByBname.do'/>" method="get"
        target="body" id="form1">
        <input type="text" name="bname" />
        <span>
            <a href="javascript:document.getElementById('form1').submit();">
                <img style="margin-top: 10px; border-radius: 3px;" align="top" border="0"
                src="../images/btn.bmp" />
            </a>
            <a href="<c:url value='/jsps/gj.jsp'/>" style="font-size: 11pt;font-family: 'microsoft yahei';"
            target="body">高级搜索</a>
        </span>
    </form>
</body>
</html>
