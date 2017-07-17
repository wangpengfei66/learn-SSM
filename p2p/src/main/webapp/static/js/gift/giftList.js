$(function() {
    
	 	$("#cust").select2();
	    $("#gift").select2();
	    $("#cust").change(function(){
	    	var id = $(this).val();
	    	$.get("/cust/info",{"id":id}).done(function(data){
	    		if(data.state == 'success') {
	    			var obj = data.data;
	    			$("#point").val(obj.point);
	    		}else{
	    			layer.alert(data.message);
	    		}
	    	}).error(function(){
	    		layer.alert("系统异常");
	    	});
	    	
	    });
	    
	    $("#gift").change(function(){
	    	var id = $(this).val();
	    	$.get("/gift/info",{"id":id}).done(function(data){
	    		if(data.state == 'success') {
	    			var obj = data.data;
	    			$("#needPoint").val(obj.needPoint);
	    		}else{
	    			layer.alert(data.message);
	    		}
	    	}).error(function(){
	    		layer.alert("系统异常");
	    	});
	    	
	    });
	    
	    
	    
	    
	    $("#addBtn").click(function(){
			var point = $("#point").val();
			var needPoint = $("#needPoint").val();
			if(parseInt(needPoint) < parseInt(point)) {
				$("#addForm").submit();
			}else{
				layer.alert("您的积分不足兑换此商品");
			}
		});
		
		$("#addForm").validate({
			errorElement:'span',
			errorClass:'text-danger',
			rules:{
				cust:{
					required:true,
				},
				gift:{
					required:true,
				},
			},
			messages:{
				name:{
					required:"请输入客户姓名",
				},
				gift:{
					required:"请输入所要兑换礼品",
				},
			},
			submitHandler:function(){
				$.ajax({
					url:'/gift/send',
					type:'post',
					data:$("#addForm").serialize(),
					beforeSend:function(){
						$("#addBtn").text("兑换中").attr("disabled","disabled");
					},
					success:function(data){
						if(data.state == 'success'){
							layer.alert("兑换成功",function(){
								window.location.href="/gift/send/query";
							});
						}else{
							layer.alert(data.message);
						}
					},
					error:function(){
						layer.alert("服务器异常");
					},
					complete:function(){
						$("#addBtn").text("兑换").removeAttr("disabled");
					}
					
				});
			}
	  });
   
	
});