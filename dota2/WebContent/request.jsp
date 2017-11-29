<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加应用</title>
</head>
<body background="./n.jpg">
<p align="center"><b>添加建议:</b></p>
<form action="request1.jsp" method="post">
<p align="center"><b>re_id(int 11):</b></p>
<p align="center"><input type="text" name="re_id" ></p>

<p align="center"><b>re_content(text):</b></p>
<p align="center"><input type="text" name="re_content" ></p>
<p align="center"><b>re_response(text):</b></p>
<p align="center"><input type="text" name="re_response" ></p>
<p align="center"><input type="submit" name="添加" value="添加"></p>
</form>
<form action="busmag.jsp" method="post">
<p align="center"><input type="submit" name="退回" value="返回"></p></form>
</body>
</html>