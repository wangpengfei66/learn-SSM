$(function() {
    
	 	$("#cust").select2();
	    $("#pro").select2();
	    $("#pro").change(function(){
	    	var id = $(this).val();
	    	$.get("/pro/info",{"id":id}).done(function(data){
	    		if(data.state == 'success') {
	    			var obj = data.data;
	    			$("#restMoney").val(obj.restMoney);
	    			$("#month").val(obj.month);
	    			$("#rate").val(obj.rate);
	    		}else{
	    			layer.alert(data.message);
	    		}
	    	}).error(function(){
	    		layer.alert("系统异常");
	    	});
	    	
	    });
	    
	  //失去焦点的时候触发该事件
		$("#investMoney").blur(function(){
			var invest = $(this).val();//11111
			var remain = $("#restMoney").val();//100000
			if(parseInt(invest) > parseInt(remain)) {
				layer.msg("您输入的金额不能大于剩余金额");
				flag = false;
			} else{
				flag = true;
			}
			
		});
		
	    $("#addBtn").click(function(){
			if(flag) {
				$("#addForm").submit();
			}else{
				layer.msg("您输入的金额不能大于剩余金额");
			}
		});
		
		$("#addForm").validate({
			errorElement:'span',
			errorClass:'text-danger',
			rules:{
				cust:{
					required:true,
				},
				pro:{
					required:true,
				},
				investMoney:{
					required:true,
					digits:true,
					min:1000 
				},
				signDate:{
					required:true
				},
				endDate:{
					required:true
				}
			},
			messages:{
				name:{
					required:"请输入客户姓名",
				},
				proName:{
					required:"请输入项目名称",
				},
				investMoney:{
					required:"请输入投资金额",
					digits:"请输入正整数",
					min:"最小投资金额为1000元"
				},
				signDate:{
					required:"请输入签订日期"
				},
				endDate:{
					required:"请输入到期时间"
				}
			},
			submitHandler:function(){
				$.ajax({
					url:'/invest/add',
					type:'post',
					data:$("#addForm").serialize(),
					beforeSend:function(){
						$("#addBtn").text("新增中").attr("disabled","disabled");
					},
					success:function(data){
						if(data.state == 'success'){
							layer.alert("新增成功",function(){
								window.location.href="/invest/list";
							});
						}else{
							layer.alert(data.message);
						}
					},
					error:function(){
						layer.alert("服务器异常");
					},
					complete:function(){
						$("#addBtn").text("新增").removeAttr("disabled");
					}
					
				});
			}
	  });
   
	
});