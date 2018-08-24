<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
  <title> 登录 </title>
  <meta charset="utf-8"/>
  	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/login.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/common.js'/>"></script>
    <script src="<c:url value='/jsps/js/user/login.js'/>"></script>
    <script type="text/javascript">
    	$(function(){
    		var loginname = window.decodeURI("${cookie.loginname.value}");
    		if("${requestScope.user.loginname}"){
    			loginname="${requestScope.user.loginname}";
    		}
    		$("#loginname").val(loginname);
    	});
    </script>
 </head>
 <body>
	<header>
		<img src="<c:url value='/images/ysmall_logo.png'/>">
		<img src="<c:url value='/images/welcome.png'/>" class="welcome">
	</header>
	<section>
	  <div id="content">
	  	<form target="_top" action="<c:url value='/user/login.do'/>" method="post" id="loginForm">
			<table>
				<tr>
					<td rowspan="10">
						<img src="<c:url value='/images/yose_show.jpg'/>">
					</td>
				</tr>
				<tr>
					<td>
						<input class="txtUsername input" placeholder="邮箱/用户名/手机号" name="loginname" id="loginname" value="${user.loginname }">
					</td>
				</tr>
				<tr>
					<td><label class="error" id="loginnameError">${msg } ${errors.loginname}</label></td>
				</tr>
				<tr>
					<td>
						<input type="password" class="txtUserpwd input" placeholder="密码" name="loginpass" id="loginpass" value="${user.loginpass }">
					</td>
				</tr>
				<tr>
					<td><label class="error" id="loginpassError">${errors.loginpass}</label></td>
				</tr>
				<tr>
					<td>
						<input type="text" class="txtVerifyCode input" placeholder="验证码" name="verifyCode" id="verifyCode" value="${user.verifyCode }">
						<img id="vCode" src="/yosebook-ssm/verifyCode" title="点击更换" 
                        onclick="this.setAttribute('src','/yosebook-ssm/verifyCode?'+Math.random());"/>
					</td>
				</tr>
				<tr>
					<td><label class="error" id="verifyCodeError">${errors.verifyCode }</label></td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="chkAuto" name="autoLogin">
						<label for="chkAuto" class="lbAuto">自动登录</label>
						<!-- <input type="checkbox" id="chkSafe" name="forgetPwd"> -->
						&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" class="forgetPwd">忘记密码?</a>
					</td>
				</tr>
				<tr>				
					<td>
						<input type="submit" id="btnLogin" value="">
					</td>
				</tr>
				<tr>
					<td>
						<a href="<c:url value='/jsps/user/regist.jsp'/>"><input type="button" id="btnRegist" value="免费注册&gt;&gt;"></a>
					</td>
				</tr>
			</table>
		</form>
	  </div>
	</section>
	<footer>
		<p>Copyright &copy; 优思网 2013-2016, All Rights Reserved&nbsp;&nbsp;|&nbsp;&nbsp;京ICP证041189号音像制品经营许可证 京音网8号</p>
		<p><img src="<c:url value='/images/beian.png'/>">&nbsp;&nbsp;<img src="<c:url value='/images/kexin.png'/>"></p>
	</footer>
 </body>
</html>
