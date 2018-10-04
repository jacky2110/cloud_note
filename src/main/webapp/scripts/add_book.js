// 定义函数，用于显示对话框
function showDlgAddBook() {
	// 弹出创建笔记本页面（用作对话框）
	$("#can").load("alert/alert_notebook.html");
	
	// 显示灰色背景，以突出对话框
	$(".opacity_bg").show();  // 使class属性值为"opacity_bg"的元素显示出来，该元素为深灰色半透明，用于遮挡背景
}

// 定义函数，用于关闭对话框
function closeDlgAddBook() {
	// 清空元素，以删除加载的页面
	$("#can").html("");
	
	// 隐藏黑色背景
	$(".opacity_bg").hide();  // 使class属性值为"opacity_bg"的元素隐藏起来
}

// 定义函数，用于向服务器发起请求，以便创建笔记本（向数据库插入记录）
function add_notebook() {
	console.log("add_notebook()======================");
	
	// 1. 获取参数
	// 1.1 获取userId
	var userId = getCookie("userId");
	
	// 1.2获取编辑区域中的笔记标题，并取到两端的空白字符
	var bookName = $("#input_notebook").val().trim();
	
	 console.log("  userId: " + userId);
	 console.log("bookName: " + bookName);
	
	// 发送ajax请求
	$.ajax({
		url: path + "/book/add.do",
		type:"post",
		data:{"userId":userId, "bookName":bookName},
		dataType:"json",  // 指定服务器返回的数据格式
		success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
			// 由于本数据是由于ajax向"/note/save.do"发送登录请求引起的，
			// 服务器端与该请求对应的是SaveNoteController.saveNote()来处理，
			// 该方法的返回值是NoteResult<Object>，因此这里result获取到也是该类型
			
			// 读取返回值的各个字段，并打印到控制台
			console.log("[success] result.status: " + result.status);
			console.log("[success]    result.msg: " + result.msg);
			console.log("[success]   result.data: " + result.data);
			
			if (result.status == 0) {
				console.log(result.msg);
				
				// 在笔记本列表中添加新创建的笔记本元素
				createBookLi(result.data.cn_notebook_id, bookName);
				
			} else {
				alert(result.msg);
			}
		},
		error:function() {
			alert("服务器无响应");
		}
	});
	 
	closeDlgAddBook();
}