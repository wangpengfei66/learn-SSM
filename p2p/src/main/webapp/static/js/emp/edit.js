$(function(){
	$("#editBtn").click(function(){
		$("#editForm").submit();
	});
	
	$("#editForm").validate({
		errorElement:'span',
		errorClass:'text-danger',
		rules:{
			tel:{
				required:true,
			},
			company:{
				required:true
			},
			dept:{
				required:true
			}
		},
		message:{
			tel:{
				required:"请输入修改的电话号码",
			},
			company:{
				required:true
			},
			dept:{
				required:true
			}
		},
		submitHandler:function(){
			$.post("/emp/edit",$("#editForm").serialize()).done(function(data){
				if(data.state == 'success'){
					layer.alert("修改成功",function(){
						window.location.href="/emp/list";
					});
				}else{
					layer.alert(data.message);
				}
			}).error(function(){
				layer.alert("服务器异常");
			});
			
		}
	});
	
});