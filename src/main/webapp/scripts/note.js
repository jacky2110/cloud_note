function loadBookNotes() {
	// 获取参数（笔记本id）
	// $(this)用于选择HTML元素，由于该函数将用作单击事件的回调函数，因此当前HTML元素就是被单击的元素
	// data()方法用于向元素中附加信息，也可以将附加的信息取出来
	var bookId = $(this).data("bookId");
	
	console.log("bookId: " + bookId);
	
	// 设置选中效果
	$("#book_ul a").removeClass("checked");  // 删除所有<a>元素的名为checked的class属性，使其
	$(this).find("a").addClass("checked");  // 为被单击元素中的子元素添加属性
                
	// 发送ajax请求
	$.ajax({
		url: path + "/note/loadBookNotes.do",
		type:"post", 
		data:{"bookId":bookId}, 
		dataType:"json",  // 指定服务器返回的数据格式
		success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
			// 由于本数据是由于ajax向"/usr/register.do"发送登录请求引起的，
			// 服务器端与该请求对应的是UserRegisterController.execute()来处理，
			// 该方法的返回值是NoteResult<List<Book>>，因此这里result获取到也是该类型
			
			// 读取返回值的各个字段，并打印到控制台
			console.log("success:" + result.status);
			console.log("success:" + result.msg);
			
			// 获取返回值中的笔记本列表
			var notes = result.data;
			
			// 清空列表中的默认数据
			$("#note_ul").html("");
			
			// 遍历列表，以便将笔记本列表加载到edit.html中
			for (var i = 0; i < notes.length; i++) {
				// 获取笔记的id
				var noteId = notes[i].cn_note_id;
				
				// 获取笔记的标题
				var noteTitle = notes[i].cn_note_title;
				
				// 调用自定义函数，在edit.html页面中创建一个li元素，以显示一个笔记的标题
				createNoteLi(noteId, noteTitle);
			}
		},
		error:function() {
			console.log("服务器无响应");
		}
        });
}

//定义函数，用于在edit.html页面中创建一个li元素，以显示一个笔记的名称
function createNoteLi(noteId, noteTitle) {
	
	// 定义一个字符串
	var str = "";
	
	// 将li元素所需内容逐个附加到字符串中
	// 下面的html元素内容，来自<ul id="note_ul" class="contacts-list">元素的内容
	// 注意去掉<a  class="checked">中的checked，不然所有条目都会显示为深色的选中状态
	str += '	<li class="online">';
	str += '		<a>';
	str += '			<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	str += noteTitle;
	str += '			<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
	str += '				<i class="fa fa-chevron-down"></i>';
	str += '			</button>';
	str += '		</a>';
	str += '		<div class="note_menu" tabindex="-1">';
	str += '			<dl>';
	str += '				<dt>';
	str += '					<button type="button" class="btn btn-default btn-xs btn_move" title="移动至...">';
	str += '						<i class="fa fa-random"></i>';
	str += '					</button>';
	str += '				</dt>';
	str += '				<dt>';
	str += '					<button type="button" class="btn btn-default btn-xs btn_share" title="分享">';
	str += '						<i class="fa fa-sitemap"></i>';
	str += '					</button>';
	str += '				</dt>';
	str += '				<dt>';
	str += '					<button type="button" class="btn btn-default btn-xs btn_delete" title="删除">';
	str += '						<i class="fa fa-times"></i>';
	str += '					</button>';
	str += '				</dt>';
	str += '			</dl>';
	str += '		</div>';
	str += '	</li>';
	
	// 将字符串转换为jQuery对象给的li元素
	// 以下格式都可以创建一个jQuery对象，该对象可以用作网页元素
	//   ■ $("文本") ： 创建普通文件
	//   ■ $("<b>文本</b>") ： 创建粗体文本
	//   ■ $("<li>文本</li>") ： 创建列表项
	// 如果不是使用data方法向该元素中附加数据，则可以直接在需要用到该元素的地方使用字符串创建匿名对象
	// 例如：$("#book_ul").append(str);
	var li = $(str);  // 根据拼接的字符串创建一个jQuery对象，将被用作网页元素
	
	// 将bookId附加到jQuery对象（单击笔记本显示笔记时会用到）
	// jQuery对象的data方法可以向元素附加任意数据，也可以将元素中的数据读取出来
	li.data("noteId", noteId);
	
	// 将jQuery元素附加到ul元素中
	$("#note_ul").append(li);
}
