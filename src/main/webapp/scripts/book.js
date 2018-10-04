function loadUserBooks() {
	
	// 从cookie中获取userId
	var userId = getCookie("userId");
	
	console.log("userId: " + userId);
	
	// 如果无法获取到有效的userId
	if (userId == null) {
		// 重定向到登录页面
		window.location.href("log_in.html");
	} else {
		$.ajax({
			url: path + "/book/loadBooks.do",
			type:"post", 
			data:{"userId":userId}, 
			dataType:"json",  // 指定服务器返回的数据格式
			success:function(result) {  // 随意填写一个参数，即可用其获取服务器的返回值
				// 由于本数据是由于ajax向"/usr/register.do"发送登录请求引起的，
				// 服务器端与该请求对应的是UserRegisterController.execute()来处理，
				// 该方法的返回值是NoteResult<List<Book>>，因此这里result获取到也是该类型
				
				
				// 读取返回值的各个字段，并打印到控制台
				 console.log("success:" + result.status);
				 console.log("success:" + result.msg);
				
				
				// 解析状态码
				if (result.status == 0) {  // 如果获取笔记本列表成功
					// console.log(result.msg);
					
					// 获取返回值中的笔记本列表
					var books = result.data;
					
					// 清空列表中的默认数据
					$("#book_ul").html("");
					
					// 遍历列表，以便将笔记本列表加载到edit.html中
					for (var i = 0; i < books.length; i++) {
						// 获取笔记本的id
						var bookId = books[i].cn_notebook_id;
						
						// 获取笔记本名称
						var bookName = books[i].cn_notebook_name;
						
						// 调用自定义函数，在edit.html页面中创建一个li元素，以显示一个笔记本的名称
						createBookLi(bookId, bookName);
					}
				}
			},
			error:function() {
				console.log("服务器无响应");
			}
		});
	}
}


//定义函数，用于在edit.html页面中创建一个li元素，以显示一个笔记本的名称
function createBookLi(bookId, bookName) {
	// 定义一个字符串
	var str = "";
	
	// 将li元素所需内容逐个附加到字符串中
	str += '<li class="online">';
	str += '<a>';
	str += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	str += '</i>';
	str += bookName;
	str += '</a>';
	str += '</li>';
	
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
	li.data("bookId", bookId);
	
	// 将jQuery元素附加到ul元素中
	$("#book_ul").append(li);
}