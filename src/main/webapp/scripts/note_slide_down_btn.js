// 定义函数，用于在笔记下拉按钮（笔记条目中的向下三角形）被单击时显示下拉菜单
function noteBtnSlideDownOnClicked() {
	
	console.log("noteBtnSlideDownOnClicked()=======================");
	
	console.log($(this));
	console.log($(this).parents("li"));
	console.log($(this).parents("li").find("div"));
	
	// 选中被单击对象的所有类型为li的父祖元素，本例中只有一个结果，就是class值为online的li
	// 然后再选中其子元素中类型为div的元素，这就是class值为note_menu的div，里面包含了菜单的内容
	var note_menu = $(this).parents("li").find("div");
	
	// 先隐藏有可能已经被打开的菜单
	hideNoteMenu();	
		
	// 当鼠标离开下拉菜单时，将其隐藏
	$("#note_ul div").mouseleave(hideNoteMenu);
	
	// 显示菜单（动画下拉）
	note_menu.slideDown(500);
		
	// 阻止事件冒泡，添加一个返回false的语句即可阻值事件冒泡
	// 也就是在本元素处理完单击事件后，不再将单击事件传递给外层元素
	// 这样做的目的是因为在整个body元素上绑定了单击事件，以关闭弹出的菜单
	// 而如果不阻止事件冒泡的话，单击该元素弹出菜单后，单击事件最终会传递到body而导致菜单刚弹出就被关闭
	return false;
}

// 定义函数，用于隐藏笔记下拉菜单
function hideNoteMenu() {
	console.log("hideNoteMenu()=======================");
	$("#note_ul div").hide();
}