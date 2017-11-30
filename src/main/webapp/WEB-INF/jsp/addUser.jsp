<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建</title>
</head>
<body>
	新增用户:
	<form action="/user/addUser" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"/></td>
			</tr>
		</table>
	</form>
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
		console.log('发送消息：准备');
		sock.send('准备！');
	}
</script>
</html>