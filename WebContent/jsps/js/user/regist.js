$(function(){
	/*
	 * 1.遍历错误信息，循环，调用一个方法来显示错误信息
	 */
	$(".labelError").each(function(){
		showError($(this));//遍历每个元素调用showError方法
	});
	
	$(".input").focus(function(){
		var labelId = $(this).attr("id")+"Error";
		$("#"+labelId).text("");
		showError($("#"+labelId));
	});
	
	$(".input").blur(function(){
		var id = $(this).attr("id"); //获取当前输入框的id
		var funName = "validate"+
					   id.substring(0,1).toUpperCase()+
					   id.substring(1) + "()";
		eval(funName);   //执行方法
	});
	/*
	 * 表单提交时校验
	 */
	$("#registForm").submit(function(){
		var flag = true;
		if(!validateLoginname()){
			flag = false;
		}
		if(!validateLoginpass()){
			flag = false;
		}
		if(!validateReloginpass()){
			flag = false;
		}
		if(!validateEmail()){
			flag = false;
		}
		if(!validateVerifyCode()){
			flag = false;
		}
		return flag;
	});
});
/*
 * 判断当前元素是否存在内容
 */
function showError(ele){
	var text = ele.text();
	if(!text){
		ele.css("display","none");//隐藏元素
	}else{
		ele.css("display","");//显示元素
	}
}

function validateLoginname(){
	var value = $("#loginname").val();
	if(!value){
		$("#loginnameError").text("用户名不能为空！");
		showError($("#loginnameError"));
		return false;
	}
	if(value.length<=3 || value.length>20){
		$("#loginnameError").text("用户名长度必须在4~20之间！");
		showError($("#loginnameError"));
		return false;
	}
	$.ajax({
		url:"/yosebook-ssm/user/ajaxValidateLoginname.do",
		data:{loginname:value},
		type:"POST",
		dataType:"json",
		async:true,
		cache:false,
		success:function(result){
			if(result){
				$("#loginnameError").text("该用户名已被注册！");
				showError($("#loginnameError"));
				return false;
			}
		}
	});
	return true;
}

function validateLoginpass(){
	var value = $("#loginpass").val();
	if(!value){
		$("#loginpassError").text("密码不能为空！");
		showError($("#loginpassError"));
		return false;
	}
	if(value.length<=3 || value.length>20){
		$("#loginpassError").text("密码长度必须在4~20之间！");
		showError($("#loginpassError"));
		return false;
	}
	return true;
}

function validateReloginpass(){
	var value = $("#reloginpass").val();
	if(!value){
		$("#reloginpassError").text("密码不能为空！");
		showError($("#reloginpassError"));
		return false;
	}
	if(value!=$("#loginpass").val()){
		$("#reloginpassError").text("两次密码输入不同！");
		showError($("#reloginpassError"));
		return false;
	}
	return true;
}

function validateEmail(){
	var value = $("#email").val();
	if(!value){
		$("#emailError").text("Email不能为空！");
		showError($("#emailError"));
		return false;
	}
	if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
		$("#emailError").text("Email格式不正确！");
		showError($("#emailError"));
		return false;
	}
	$.ajax({
		url:"/yosebook-ssm/user/ajaxValidateEmail.do",
		data:{email:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(result){
			if(result){
				$("#emailError").text("该Email已被注册！");
				showError($("#emailError"));
				return false;
			}
		}
	});
	return true;
}

function validateVerifyCode(){
	var value = $("#verifyCode").val();
	if(!value){
		$("#verifyCodeError").text("验证码不能为空！");
		showError($("#verifyCodeError"));
		return false;
	}
	if(value.length!=4){
		$("#verifyCodeError").text("错误的验证码！");
		showError($("#verifyCodeError"));
		return false;
	}
	$.ajax({
		url:"/yosebook-ssm/user/ajaxValidateVerifyCode.do",
		data:{verifyCode:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(result){
			if(!result){
				$("#verifyCodeError").text("错误的验证码！");
				showError($("#verifyCodeError"));
				return false;
			}
		}
	});
	return true;
}





















