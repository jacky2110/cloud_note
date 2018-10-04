function userLogin() {
				// 测试绑定是否生效
				// alert("按钮单击");
				
				// 获取用户输入的账号并去掉两端的空白字符
				var name = $("#count").val().trim();
				var password = $("#password").val().trim();
				//alert(name + "," + password);
				
				$("#count_span").html("");
				$("#password_span").html("");
			
				//格式检测
				var ok = true;
				if (name == "") {
					$("#count_span").html("用户名不能为空");
					ok = false;
				}
				if (password == "") {
					$("#password_span").html("密码不能为空");
					ok = false;
				}
				
				// 调用ajax对象，向服务器发送数据
 				if (ok == true) {
 					$.ajax({
 						url: path + "/user/login.do", 
 						type:"post", 
 						data:{"name":name, "password":password}, 
 						dataType:"json",
 						success:function(result){  // 随意填写一个参数，即可用其获取服务器的返回值
 							console.log("登录成功！");
 							
 							// 由于本数据是由于ajax向"/usr/login.do"发送登录请求引起的，
 							// 服务器端与该请求对应的是UserLoginController.execute()来处理，
 							// 该方法的返回值是NoteResult<User>，因此这里result获取到也是该类型
 							
 							// 读取返回值的各个字段，并打印到控制台
 							console.log(result.status);
 							console.log(result.msg);
 							console.log(result.data.cn_user_id);
 							console.log(result.data.cn_user_name);
 							console.log(result.data.cn_user_password);
 							console.log(result.data.cn_user_token);
 							console.log(result.data.cn_user_nick);
 							
 							
 							// 如果登录成功
 							if (result.status == 0) {
 								// 将用户信息保存到cookie中
 								var userId = result.data.cn_user_id;
 								addCookie("userId", userId, 2);  // 参3为cookie有效期，单位小时
 								
 								// 转发到主页面edit.html
 								window.location.href = "edit.html";
 							} else if (status == 1) { // 如果用户名输入错误
 								$("#count_span").html(result.msg);  // 读取返回值中的消息字段
 							} else if (status == 2) { // 如果密码输入错误
 								$("#password_span").html(result.msg);
 							}
 						},
						error:function(){
							alert("登录失败！");
						}
 					});
 				}
			}