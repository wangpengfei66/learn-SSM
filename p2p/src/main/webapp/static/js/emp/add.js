  $(function(){
	$("#addBtn").click(function(){
		$("#addForm").submit();
	});
	  
	$("#addForm").validate({
		errorElement:'span',
		errorClass:'text-danger',
		rules:{
			userName:{
				required:true
			},
			idNo:{
				required:true,
				rangelength : [ 5, 5 ],
				remote : "/validate/idNo"
			},
			tel:{
				required:true,
				rangelength : [ 5, 5 ],
				remote : "/validate/tel"
			},
			company:{
				required:true
			},
			dept : {
				required : true,
			}
		},
		messages:{
			userName:{
				required:"请输入用户名"
			},
			idNo:{
				required:"请输入身份证号码",
				rangelength :"身份证号码位数错误",
				remote : "身份证号码已存在"
			},
			tel:{
				required:"请输入电话号码",
				rangelength : "电话号码位数错误",
				remote : "此电话号码已存在"
			},
			company:{
				required:"请选择公司"
			},
			dept : {
				required : "请选择员工部门",
			}
		},
		submitHandler:function(){
			$.ajax({
				url:"/emp/add",
				type:"post",
				data:$("#addForm").serialize(),
				beforeSend:function(){
					$("#addBtn").text("提交中").attr("disabled","disabled");
				},
				success:function(data){
					alert(data);
					alert(data.state);
					if(data.state == 'success'){
						layer.alert("新增成功",function(){
							window.location.href = "/emp/list";
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

