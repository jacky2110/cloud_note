function userRegister() {
	// console.log("=================================");
	// console.log("注册按钮被单击");

	// 获取用户输入的下列信息并去掉两端的空白字符
	var name = $("#regist_username").val().trim();  // 账号
	var nick = $("#nickname").val().trim();         // 昵称
	var password = $("#regist_password").val().trim();  // 密码
	var final_password = $("#final_password").val().trim();  // 确认密码

	// console.log("注册信息: "+name+","+nick+","+password+","+final_password);

	$("#warning_1").hide();
	$("#warning_2").hide();
	$("#warning_3").hide();

	// 格式检测
	var ok = true;

	// 检测用户名
	if (name == "") {
		// 使用复合选择器 id+元素类型
		$("#warning_1 span").html("用户名不能为空");
		$("#warning_1").show();  // 将div元素显示出来
		ok = false;
	}

	// 检测密码： 1.不为空 2.最少6位
	if (password == "") {
		$("#warning_2 span").html("密码不能为空");
		$("#warning_2").show();  // 将div元素显示出来
		ok = false;
	} else if (password.length > 0  &&  password.length < 6) {
		$("#warning_2 span").html("密码不能少于6个字符");
		$("#warning_2").show();  // 将div元素显示出来
		ok = false;
	}

	// 检测确认密码： 是否与密码一致
	if (final_password != password) {
		$("#warning_3 span").html("两次输入的密码不一致");
		$("#warning_3").show();  // 将div元素显示出来
		ok = false;
	}

	// console.log("ok = " + ok);

	// 如果用户输入的数据没有问题
	if (ok == true) {
	
		// console.log("发送ajax数据给服务器");
	
		$.ajax({
			url: path + "/user/register.do",
			type:"post", 
			data:{"name":name, "nick":nick, "password":password}, 
			dataType:"json",  // 指定服务器返回的数据格式
			success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
				// 由于本数据是由于ajax向"/usr/register.do"发送登录请求引起的，
				// 服务器端与该请求对应的是UserRegisterController.execute()来处理，
				// 该方法的返回值是NoteResult<User>，因此这里result获取到也是该类型
				
				// 读取返回值的各个字段，并打印到控制台
				// console.log("success:" + result.status);
				// console.log("success:" + result.msg);
				
				// 解析状态码
				if (result.status == 0) {  // 如果注册成功
				// console.log(result.msg);
				
				// 返回登录页面（通过模拟【返回】按钮被单击）
				$("#back").click();
				
				} else if (result.status == 1) { // 如果用户名已经被占用
				// console.log(result.msg + "==============");
				
				$("#warning_1 span").html(result.msg);  // 读取返回值中的消息字段
				$("#warning_1").show();
				}
			},
			error:function() {
				console.log("服务器无响应");
			}
		});
	}
}
