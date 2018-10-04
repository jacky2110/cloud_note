// 定义函数，用于显示对话框
function showDlgAddNote() {
	console.log("showDlgAddNote()======================");
	
	// 1. 判断是否有笔记本被选中
	// 1.1  获取class属性可能为checked的元素的父元素
	var $li = $("#book_ul a.checked").parent();
	// 1.2 如果没有元素被选中，则提示用户进行选择
	if ($li.length == 0) {
		alert("请选择笔记本");
	// 1.3 如果用户选择了笔记本，则弹出创建笔记对话框
	} else { 
		// 弹出创建笔记本页面（用作对话框）
		$("#can").load("alert/alert_note.html");
		
		// 显示灰色背景，以突出对话框
		$(".opacity_bg").show();  // 使class属性值为"opacity_bg"的元素显示出来，该元素为深灰色半透明，用于遮挡背景
	}
}

// 因为跟【创建笔记】对话框相关的2个元素：
//    1. 包裹对话框页面$("#can")元素
//    2. 暗色背景的$(".opacity_bg")元素
// 和【创建笔记本】对话框的完全一样，所以可以直接使用【创建笔记本】对话框的关闭函数closeDlgAddBook
// 定义函数，用于关闭对话框
//function closeDlgAddNote() {
//	// 清空元素，以删除加载的页面
//	$("#can").html("");
//	
//	// 隐藏黑色背景
//	$(".opacity_bg").hide();  // 使class属性值为"opacity_bg"的元素隐藏起来
//}

//定义函数，用于向服务器发起请求，以便创建笔记本（向数据库插入记录）
function add_note() {
	
	// 获取用户输入的笔记标题
	var noteTitle = $("#input_note").val().trim();
	
	// 获取用户id
	var userId = getCookie("userId");
	
	// 获取笔记本id
	var $li = $("#book_ul a.checked").parent();  // 获取附件了bookId信息的元素
	var bookId = $li.data("bookId");  // 使用data()方法将附加到元素中的数据取出来
	
	// 打印信息
	console.log("noteTitle:" + noteTitle);
	console.log("userId:" + userId);
	console.log("bookId:" + bookId);
	
	// 1. 判断信息是否有误
	// 1.1 定义一个变量作为标记
	var ok = true;
	
	// 1.2 判断用户是否输入了笔记的名称
	if (noteTitle == "") {
		$("#alert_note_html_note_title_alert").html("笔记名称不能为空!");
		console.log("没有输入笔记名称");
		ok = false;
	}
	
	// 1.3 判断是否能读取到userId
	if (userId == null) {
		// 如果在Cookie中读不到有效的userID，表示登录已经失效，则跳转到登录页面
		window.location.href = "log_in.html";
	}
	
	// 1.4 判断是否能获取到有效的bookId
	if (bookId == null) {
		console.log("没有有效的笔记本ID");
		ok = false;
	}
	
	// 如果数据没有问题，则发送ajax请求
	if (ok == true) {
		// 发送ajax请求
		$.ajax({
			url: path + "/note/add.do",
			type:"post",
			data:{"userId":userId, "bookId":bookId, "noteTitle":noteTitle},
			dataType:"json",  // 指定服务器返回的数据格式
			success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
				// 由于本数据是由于ajax向"/note/add.do"发送登录请求引起的，
				// 服务器端与该请求对应的是AddNoteController.addNote()来处理，
				// 该方法的返回值是NoteResult<Note>，因此这里result获取到也是该类型
				
				// 读取返回值的各个字段，并打印到控制台
				console.log("[success] result.status: " + result.status);
				console.log("[success] result.msg:    " + result.msg);
				console.log("[success] result.data:   " + result.data);
				
				if (result.status == 0) {
					console.log("result.data.cn_note_id:    " + result.data.cn_note_id);
					console.log("result.data.cn_note_title: " + result.data.cn_note_title);
					
					// 在笔记本列表中添加新创建的笔记本元素
					createNoteLi(result.data.cn_note_id, result.data.cn_note_title);
					
					// 提示笔记创建成功
					alert(result.msg);
					
				} else {
					alert(result.msg);
				}
			},
			error:function() {
				alert("服务器无响应");
			}
		});
	}
	
	// 关闭创建笔记对话框
	closeDlgAddBook();
}
