<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>boo_gj.jsp</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
table {
	color: #404040;
	font-size: 12pt;
	font-family: "microsoft yahei";
}

input[type="text"] {
	height: 30px;
	border: 1px solid #aaa;
	border-radius: 5px;
	padding: 1px 10px;
	margin-top: 3px;
	margin-bottom: 3px;
}

input[type="submit"], input[type="reset"] {
	margin-top: 5px;
	width: 89px;
	height: 30px;
	border: 1px solid #555;
	border-radius: 5px;
	background: #555;
	font-family: "microsoft yahei";
	font-size: 15px;
	color: white;
}
input[type="submit"]:hover, input[type="reset"]:hover {
    background: #333;
    cursor: pointer;
}
</style>
</head>

<body>
    <form action="<c:url value='/admin/book/findByConbination.do'/>"
        method="get">
        <table align="center">
            <tr>
                <td>书名：</td>
                <td><input type="text" name="bname" /></td>
            </tr>
            <tr>
                <td>作者：</td>
                <td><input type="text" name="author" /></td>
            </tr>
            <tr>
                <td>出版社：</td>
                <td><input type="text" name="press" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="搜　索" /> &nbsp; <input
                    type="reset" value="重新填写" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
