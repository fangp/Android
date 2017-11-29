<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加应用类型信息</title>
</head>
<body background="./b.jpg" >
<form action="addapptype1.jsp" method="post">
<p align="center"><b>t_id(int 11):</b><input type="text" name="t_id" ></p>
<p align="center"><b>t_name(varchar 20):</b><input type="text" name="t_name" ></p>
<p align="center"><input type="submit" name="添加" value="添加"></p>
</form>
<form action="apptype.jsp" method="post">
<p align="center"><input type="submit" name="退回" value="返回"></p></form>
</body>
</html>