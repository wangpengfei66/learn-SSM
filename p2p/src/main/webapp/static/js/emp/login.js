$(function() {
		
		$("#login").click(function() {
			$("#subForm").submit();
		});
		$("#subForm").validate({
			errorElement:'span',
			errorClass:'text-danger',
			rules:{
				userName:{
					required:true
				},
				password:{
					required:true
				}
			},
			messages:{
				userName:{
					required:'请输入用户名'
				},
				password:{
					required:'请输入密码'
				}
			},
			submitHandler:function(){
				$.ajax({
					url:'/login',
					type:'post',
					data:$("#subForm").serialize(),
					beforeSend:function(){
						$("#login").text("登录中").attr("disabled","disabled");	
					},
					success:function(data){
						if(data.state == 'success') {
							if(data.data) {
								window.location.href=data.data;
							}else{
								window.location.href="/home";//这个地方务必要加else，否则会出问题
							}
						}else{
							layer.alert(data.message);
						}
					},
					error:function(){
						layer.alert("系统错误");
					},
					complete:function(){
						$("#login").text("登录").removeAttr("disabled");
					}
				});
			}
		});
		
	});