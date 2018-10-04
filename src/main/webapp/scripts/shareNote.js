// 定义函数，用于响应分享笔记按钮被单击
function shareNoteBtnOnClick() {
	console.log("shareNoteBtnOnClick()=========================");
	
	// 获取分享按钮父祖元素中的li元素，因为里面保存了笔记的id
	var $li = $(this).parents("li");
	
	// 获取笔记的id
	var noteId = $li.data("noteId");
	
	// 打印笔记id
	console.log("[shareNoteBtnOnClick()]noteId: " + noteId);
	
	// 如果数据没有问题，则发送ajax请求
	if (noteId != null) {
		// 发送ajax请求
		$.ajax({
			url: path + "/share/add.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",  // 指定服务器返回的数据格式
			success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
				// 由于本数据是由于ajax向"/note/add.do"发送登录请求引起的，
				// 服务器端与该请求对应的是AddNoteController.addNote()来处理，
				// 该方法的返回值是NoteResult<Note>，因此这里result获取到也是该类型
				
				// 读取返回值的各个字段，并打印到控制台
				console.log("[success] result.status: " + result.status);
				console.log("[success] result.msg:    " + result.msg);
				console.log("[success] result.data.cn_share_id:    " + result.data.cn_share_id);
				console.log("[success] result.data.cn_share_title: " + result.data.cn_share_title);
				console.log("[success] result.data.cn_share_body:  " + result.data.cn_share_body);
				console.log("[success] result.data.cn_note_id:     " + result.data.cn_note_id);
				

				if (result.status == 0) {
					// 获取笔记标题的2种方法：
					// 1. 从本地元素的text()方法获取已经显示出来的笔记标题
					var noteTitle = $li.text();
					// 2. 从服务器的返回结果中获取笔记标题
//					var noteTitel = result.data.cn_share_title;
					
					var str = "";
					str += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					str += noteTitle;
					str += '<i class="fa fa-sitemap"></i>';  // 添加该元素可以添加一个图标表示已分享
					str += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
					str += '	<i class="fa fa-chevron-down"></i>';
					str += '</button>';
					
					// 更新笔记条目的显示，为其添加一个图标，表示该笔记已分享
					$li.find("a").html(str);
					
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
}