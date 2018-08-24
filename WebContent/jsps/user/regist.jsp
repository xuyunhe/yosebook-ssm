<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
    href="<c:url value='/css/css.css'/>">
<link rel="stylesheet" type="text/css"
    href="<c:url value='/jsps/css/user/regist.css'/>">
<script type="text/javascript"
    src="<c:url value='/jquery/jquery-2.1.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script>
</head>

<body>
    <header>
     <img src="<c:url value='/images/ysmall_logo.png'/>">
     <img src="<c:url value='/images/welcome_reg.png'/>" class="welcome_reg"> 
    </header>
    <section>
        <div class="divBody">
            <div class="divTitle">
                <span class="spanTitle">新用户注册</span>
            </div>
            <div class="divCenter">
                <form action="<c:url value='/user/regist.do'/>" method="post" id="registForm">
                    <table>
                        <tr>
                            <td class="tdLabel">用户名：</td>
                            <td class="tdInput"><input type="text"
                                name="loginname" id="loginname"
                                class="input" value="${form.loginname }" /></td>
                            <td class="tdError"><label
                                class="labelError" id="loginnameError">${errors.loginname }</label>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">登录密码：</td>
                            <td class="tdInput"><input type="password"
                                name="loginpass" id="loginpass"
                                class="input" value="${form.loginpass }" /></td>
                            <td class="tdError"><label
                                class="labelError" id="loginpassError">${errors.loginpass }</label>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">确认密码：</td>
                            <td class="tdInput"><input type="password"
                                name="reloginpass" id="reloginpass"
                                class="input" value="${form.reloginpass }" /></td>
                            <td class="tdError"><label
                                class="labelError" id="reloginpassError">${errors.reloginpass }</label>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">Email：</td>
                            <td class="tdInput"><input type="text"
                                name="email" id="email" class="input"
                                value="${form.email }" /></td>
                            <td class="tdError"><label
                                class="labelError" id="emailError">${errors.email }</label>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">图形验证码：</td>
                            <td class="tdInput"><input type="text"
                                name="verifyCode" id="verifyCode"
                                class="input" value="${form.verifyCode }" /></td>
                            <td class="tdError"><label
                                class="labelError" id="verifyCodeError">${errors.verifyCode }</label>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><span class="verifyCodeImg"><img
                                    id="vCode" width="100" title="点击更换" src="/yosebook-ssm/verifyCode"
                                    onclick="this.setAttribute('src','/yosebook-ssm/verifyCode?'+Math.random());"></span></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><%-- <input type="image"
                                src="<c:url value='/images/regist1.jpg'/>"
                                id="submit" /> --%>
                                <input type="submit" id="submitBtn" value="" class="btnRegist">
                            </td>   
                            <td><a class="alreadyHave" href="<c:url value='/jsps/user/login.jsp'/>">已有账号？</a></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </section>
    <footer>
        <p>Copyright &copy; 优思网 2013-2016, All Rights Reserved&nbsp;&nbsp;|&nbsp;&nbsp;京ICP证041189号音像制品经营许可证 京音网8号</p>
        <p><img src="<c:url value='/images/beian.png'/>">&nbsp;&nbsp;<img src="<c:url value='/images/kexin.png'/>"></p>
    </footer>
</body>
</html>
