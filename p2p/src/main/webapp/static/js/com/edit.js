$(function(){
	
	$("#editComBtn").click(function(){
		$("#editComForm").submit();
	});
	
	$("#editComForm").validate({
		errorElement:'span',
		errorClass:'text-danger',
		rules:{
			name:{
				required:true
			},
			tel:{
				required:true
			},
			address:{
				required:true
			}
			
		},
		messages:{
			name:{
				required:'请输入公司名称'
			},
			tel:{
				required:'请输入分公司负责人电话'
			},
			address:{
				required:'请输入具体地址'
			}
		},
		submitHandler:function(){
			$.ajax({
				url:'/com/edit',
				type:'post',
				data:$("#editComForm").serialize(),
				beforeSend:function(){
					$("#editComBtn").text("修改中").attr("disabled","disabled");
				},
				success:function(data){
					if(data.state == 'success'){
						layer.alert("修改成功");
						window.location.href="/com/list";
					}else{
						layer.alert(data.message);
					}
				},
				error:function(){
					layer.alert("服务器异常");
				},
				complete:function(){
					$("#eidtComBtn").text("修改").removeAttr("disabled");
				}
				
			});
		}
		
	});
	
	
	
});