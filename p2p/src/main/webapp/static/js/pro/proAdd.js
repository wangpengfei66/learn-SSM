$(function(){
    $("#signDate").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,
            startDate:moment().format("YYYY-MM-DD")
        }).on('changeDate',function(e) {
        	//获得周期控件的值
        	var month = $("#month").val();
        	//根据开始日期和周期时间获取截止日期的值
        	var date = moment(e.format('yyyy-mm-dd')).add(month,'months').format('YYYY-MM-DD');
        	//将获取到的值添加到截止日期中
        	$("#endDate").val(date);
        });
    //修复bug，当month改变的时候，也触发下边的值
    $("#month").change(function(){
    	var signDate = $("#signDate").val();
    	var month = $("#month").val();
    	var date = moment(signDate).add(month,'month').format("YYYY-MM-DD");
    	$("#endDate").val(date);
    });
	    
    $("#proBtn").click(function(){
		$("#proForm").submit();
	});
	
	$("#proForm").validate({
		errorElement:'span',
		errorClass:'text-danger',
		rules:{
			projectName:{
				required:true,
				remote:"/validate/projectName"
			},
			projectNo:{
				required:true,
				remote:"/validate/projectNo"
			},
			money:{
				required:true
			},
			rate:{
				required:true
			},
			month:{
				required:true
			},
			signDate:{
				required:true
			},
			endDate:{
				required:true
			}
		},
		messages:{
			projectName:{
				required:'请输入项目名称',
				remote:"项目已存在"
			},
			projectNo:{
				required:'请输入项目编号',
				remote:"项目编号已存在"
			},
			money:{
				required:'请输入项目金额'
			},
			rate:{
				required:'请输入回报率'
			},
			month:{
				required:'请输入项目周期'
			},
			signDate:{
				required:'请输入项目开始日期'
			},
			endDate:{
				required:'请输入项目结束日期'
			}
		},
		submitHandler:function(){
			$.ajax({
				url:'/pro/add',
				type:'post',
				data:$("#proForm").serialize(),
				beforeSend:function(){
					$("#proBtn").text("新增中").attr("disabled","disabled");
				},
				success:function(data){
					if(data.state == 'success'){
						layer.alert("新增成功",function(){
							window.location.href="/pro/list";
						});
					}else{
						layer.alert(data.message);
					}
				},
				error:function(){
					layer.alert("服务器异常");
				},
				complete:function(){
					$("#proBtn").text("新增").removeAttr("disabled");
				}
				
			});
		}
  });
});