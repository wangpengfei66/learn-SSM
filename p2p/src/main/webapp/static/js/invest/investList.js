$(function() {
	
	//续约，点击续约按钮，发起ajax请求获得后台所有相关数据并显示到modle框
	$(".renew").click(function() {
		var id = $(this).attr("rel");
		$.get("/invest/renew",{"id":id}).done(function(data) {
			if(data.state == 'success') {
				//数据回显
				$("#cust").val(data.cust.id);
				$("#name").val(data.cust.name);
				$("#pro").val(data.pro.id);
				$("#projectName").val(data.pro.projectName);
				$("#restMoney").val(data.pro.restMoney);
				$("#investMoney").val(data.invest.investMoney);
				$("#rate").val(data.invest.rate);
				$("#month").val(data.invest.month);
				
				$("#signDate").val(data.invest.endDate);
				var signDate = data.invest.endDate;
				var endDate = moment(signDate).add(data.invest.month,"months").format("YYYY-MM-DD");
				$("#endDate").val(endDate);
			
				
			}else{
				layer.alert(data.message);
			}
		}).error(function() {
			layer.alert("系统错误");
		});
	
	});
	
	//点击续约,发起ajax请求
	$("#addBtn").click(function() {
		$.ajax({
			url:"/invest/add",
			type:"post",
			data:$("#addForm").serialize(),
			success:function(data) {
				if(data.state == 'success') {
					layer.alert("续约成功",function(){
						window.history.go(0);
					});
				}else{
					layer.alert(data.message);
				}
			},
			error:function(){
				layer.alert("系统异常");
			}
		});
	});
	
	
	$(".del").click(function() {
		var id = $(this).attr("rel");
		layer.confirm("确定要删除吗",function() {
			$.get("/invest/del",{"id":id}).done(function(data) {
				if(data.state == 'success') {
					layer.alert("删除成功",function(){
						window.history.go(0);
					});
				}else{
					layer.alert(data.message);
				}
			}).error(function() {
				layer.alert("系统错误");
			});
		});
	});
	
	$(".unuse").click(function() {
		var id = $(this).attr("rel");
		layer.confirm("确定要解约吗",function() {
			$.get("/invest/unuse",{"id":id}).done(function(data) {
				if(data.state == 'success') {
					layer.msg("解约成功",function(){
						window.history.go(0);
					});
				}else{
					layer.alert(data.message);
				}
			}).error(function() {
				layer.alert("系统错误");
			});
		});
	});
	
	
	
});