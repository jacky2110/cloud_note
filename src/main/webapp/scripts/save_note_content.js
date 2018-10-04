// 定义一个函数，用于将UEditor编辑器中的笔记正文提交到服务器进行保存
function saveNoteCotent() {
	console.log("saveNote()============");
	
	// 获取参数
	// $("#note_ul a.checked")：选中id为 "note_ul"的元素中的class值为"checked"的a元素
	// $("#note_ul a.checked").parent()：选中a元素的父元素，即li元素，因此动态创建笔记列表时，在li元素中附加了笔记的id
	// 而现在需要通过笔记的id来指定要将编辑区域的内容存到哪一篇笔记中
	var $li = $("#note_ul a.checked").parent();
	
	// 获取笔记Id
	var noteId = $li.data("noteId");
	
	// 获取编辑区域中的笔记标题，并取到两端的空白字符
	var noteTitle = $("#input_note_title").val().trim();
	
	// 获取编辑区域中的笔记正文
	var noteBody = um.getContent();
	
	 console.log("noteId:      " + noteId);
	 console.log("noteTitle:   " + noteTitle);
	 console.log("noteContent: " + noteBody);
	
	// 发送ajax请求
	$.ajax({
		url: path + "/note/save.do",
		type:"post",
		data:{"noteId":noteId, "noteTitle":noteTitle, "noteBody":noteBody},
		dataType:"json",  // 指定服务器返回的数据格式
		success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
			// 由于本数据是由于ajax向"/note/save.do"发送登录请求引起的，
			// 服务器端与该请求对应的是SaveNoteController.saveNote()来处理，
			// 该方法的返回值是NoteResult<Object>，因此这里result获取到也是该类型
			
			// 读取返回值的各个字段，并打印到控制台
			console.log("success:" + result.status);
			console.log("success:" + result.msg);
			console.log("success:" + result.data);
			
			if (result.status == 0) {
				console.log(result.msg);
				
				// 重新构建笔记列表中被编辑笔记的标题
				var str = "";
				str += '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				str += 	noteTitle;
				str += '	<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
				str += '		<i class="fa fa-chevron-down"></i>';
				str += '	</button>';
				
				// 将标题更新到笔记列表中的笔记条目里
				$("#note_ul a.checked").html(str);
				
				// 提示保存成功
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
