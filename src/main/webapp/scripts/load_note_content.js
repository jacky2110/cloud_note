function loadNoteContent() {
	console.log("loadNoteContent()================");
	
	// 获取参数（笔记id）
	// $(this)用于选择HTML元素，由于该函数将用作单击事件的回调函数，因此当前HTML元素就是被单击的元素
	// data()方法用于向元素中附加信息，也可以将附加的信息取出来
	var noteId = $(this).data("noteId");
	console.log("noteId: " + noteId);
	
	// 设置选中效果
	$("#note_ul a").removeClass("checked");  // 删除所有<a>元素的名为checked的class属性，使其
	$(this).find("a").addClass("checked");  // 为被单击元素中的子元素添加属性
	
	// 发送ajax请求
	$.ajax({
		url: path + "/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",  // 指定服务器返回的数据格式
		success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
			// 由于本数据是由于ajax向"/usr/register.do"发送登录请求引起的，
			// 服务器端与该请求对应的是UserRegisterController.execute()来处理，
			// 该方法的返回值是NoteResult<List<Book>>，因此这里result获取到也是该类型
			
			// 读取返回值的各个字段，并打印到控制台
			console.log("success:" + result.status);
			console.log("success:" + result.msg);
			
			if (result.status == 0) {
				// 获取笔记标题
				var noteTitle = result.data.cn_note_title;
				
				// 获取笔记正文
				var noteContent = result.data.cn_note_body;
				
				// 将笔记标题放入标题栏
				$("#input_note_title").val(noteTitle);
				
				// 将笔记正文放入编辑器
				um.setContent(noteContent);
			} else if (status == 1) {
				console.log(result.msg);
			}
		},
		error:function() {
			console.log("服务器无响应");
		}
	});
}