// 定义函数，用于在搜索栏获得焦点时，且回车键被按下后，向服务器发送ajax请求，对关键词进行搜索
function searchOnEnterPressed(event) {
	console.log("searchOnEnterPressed()===============================");
	
	// 如果是回车被按下
	if (event.which == 13) {
		// 获取输入框中的文本，并将两端的空白字符去掉
		var keyword = $("#search_note").val().trim();
		
		// 复位全局变量
		page = 1;
		
		// 发送ajax请求
		sendRequestSearch(keyword, page);
	}
}

// 定义函数，用于搜索更多笔记
function searchMoreShare() {
	console.log("searchMoreShare()========================");
	
	// 获取输入框中的文本，并将两端的空白字符去掉
	var keyword = $("#search_note").val().trim();
	
	// 更新查询结果的页数
	page++;
	
	// 发送ajax请求
	sendRequestSearch(keyword, page);

}

// 定义函数，用于向服务器发送请求
// 并指定要搜索的关键词和结果页数
function sendRequestSearch(keyword, page) {
	// 发送ajax请求
	$.ajax({
		url: path + "/share/search.do",
		type:"post",
		data:{"keyword":keyword, "page":page},
		dataType:"json",  // 指定服务器返回的数据格式
		success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
			// 由于本数据是由于ajax向"/note/add.do"发送登录请求引起的，
			// 服务器端与该请求对应的是AddNoteController.addNote()来处理，
			// 该方法的返回值是NoteResult<Note>，因此这里result获取到也是该类型
			
			// 读取返回值的各个字段，并打印到控制台
			console.log("[success] result.status: " + result.status);
			console.log("[success] result.msg:    " + result.msg);

			if (result.status == 0) {
				
				// 提取返回结果中携带的搜索结果
				var data = result.data;
				
				// 先清空ul，以清除上次搜索的结果
				$("#search_ul").html("");
				
				// 遍历搜索结果
				for (var i = 0; i < data.length; i++) {
					
					console.log("遍历: " + (i + 1));
					console.log("[success] result.data.cn_share_id:    " + data[i].cn_share_id);
					console.log("[success] result.data.cn_share_title: " + data[i].cn_share_title);
					console.log("[success] result.data.cn_share_body:  " + data[i].cn_share_body);
					console.log("[success] result.data.cn_note_id:     " + data[i].cn_note_id);
					
					
					// 获取笔记的分享id
					var shareId = data[i].cn_share_id;
					
					// 获取笔记标题
					var shareTitle = data[i].cn_share_title;
					
					// 构建字符串（代表一个搜索结果的li的HTML代码）
					var str = "";
					str += '	<li class="online">';
					str += '		<a>';
					str += '			<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					str += shareTitle;
					str += '			<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
					str += '				<i class="fa fa-chevron-down"></i>';
					str += '			</button>';
					str += '		</a>';
					str += '	</li>';
					
					// 根据字符串创建jQuery对象
					$li = $(str);
					
					// 将shareId附加到jQuery对象中
					$li.data("shareId",shareId);
					
					// 将jQuery元素添加到ul元素中
					$("#search_ul").append($li);
				}
				
				// 切换显示区域（隐藏笔记列表，显示搜索结果列表）
				$("#pc_part_2").hide();
				$("#pc_part_6").show();
				
			} else {
				alert(result.msg);
			}
			
			// 返回搜索结果的数量，以便判断是否是最后一页
			return result.data.length;
		},
		error:function() {
			alert("服务器无响应");
			
			// 返回0作为搜索结果的数量，以便按照已经到达最后一页来处理
			return 0;
		}
	});
}


























