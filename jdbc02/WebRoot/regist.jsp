<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
		function _change(){
			var imgEle = document.getElementById("imgEle");
			imgEle.src = "<c:url value='/VerifyCodeServlet'/>?" + new Date().getTime();
		}
	</script>
  </head>
  
  <body>
    <p>
    	${msg }
    </p>
    <form action="<c:url value='/RegistServlet'/>" method="post">
    	姓名：<input type="text" name="username" value="${form.username }"/>${errors.username }<br/>
    	密码:<input type="text" name="password" value="${form.password }"/>${errors.password }<br/>
    	确认密码：<input type="text" name="repassword" value="${form.repassword }"/>${errors.repassword }<br/>
    	年龄:<input type="text" name="age" value="${form.age }"/>${errors.age }<br/>
    	性别：<input type="text" name="gender" value="${form.gender }"/>${errors.gender }<br/>
    	验证码：<input type="text" name="verifyCode" size="3" value="${form.verifyCode }"/>
    		<img src="<c:url value='/VerifyCodeServlet'/>" id="imgEle"/>
    		<a href="javascript:_change()">换一张</a>
    		${errors.verifyCode }<br/>
    	<input type="submit" value="注册"/><br/>
    </form>
  </body>
</html>
