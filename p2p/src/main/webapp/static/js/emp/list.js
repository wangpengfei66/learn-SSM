$(function(){
	 $(".del").click(function() {
		 	var id = $(this).attr("rel");//获取id的值
			layer.confirm("是否删除？",function(){
				$.get("/emp/del",{"id":id}).done(function(data){
					if(data.state == 'success'){
						layer.msg("删除成功");
						window.history.go(0);
					}else{
						layer.msg(date.message);
					}
				}).error(function(){
					layer.msg("服务器异常");
				});
			});
		});
});