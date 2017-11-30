<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width:800px;text-align:center">
		<p>用户创建成功</p>
		<p>${user.name}</p>
		<p><img alt="success" src="/img/success.jpg" width="100%"/></p>
	</div>
</body>
<script type="text/javascript">
	var url = 'ws://' + window.location.host + '/action';
	//打开websocket
	var sock = new WebSocket(url);
	//处理连接开启
	sock.onopen = function(){
		console.log('websocket建立连接');
		prepare();
	}
	sock.onmessage = function(e){
		console.log('收到消息：',e.data);
		setTimeout(function(){
			prepare();
		  },2000);
	}
	sock.onclose = function(){
		console.log('websocket关闭连接');
	}
	
	function prepare(){
		console.log('发送消息：创建成功');
		sock.send('创建成功！');
	}
</script>
</html>