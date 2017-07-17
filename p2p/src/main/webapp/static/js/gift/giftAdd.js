$(function() {
	$("#addBtn").click(function(){
		$("#addForm").submit();
	});
	  
	$("#addForm").validate({
		errorElement:'span',
		errorClass:'text-danger',
		rules:{
			giftName:{
				required:true
			},
			num:{
				required:true,
			},
			point:{
				required:true,
			},
			price:{
				required:true
			}
		},
		messages:{
			giftName:{
				required:"请输入礼品名称"
			},
			num:{
				required:"请输入新增礼品数量",
			},
			point:{
				required:"请输入兑换所需积分",
			},
			price:{
				required:"请输入礼品单价"
			}
		},
		submitHandler:function(){
			$.ajax({
				url:"/gift/add",
				type:"post",
				data:$("#addForm").serialize(),
				beforeSend:function(){
					$("#addBtn").text("提交中").attr("disabled","disabled");
				},
				success:function(data){
					if(data.state == 'success'){
						layer.alert("新增成功",function(){
							window.location.href = "/gift/list";
						});
					}
				},
				error:function(data){
					alert(data.message);
				},
				complete:function(){
					var btn = $("#addBtn");
					btn.removeAttr("disabled");
					btn.text("新增");
				}
			});
		}
		
	});  
	  
	
});