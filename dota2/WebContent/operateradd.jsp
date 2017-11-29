<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加操作员信息</title>
</head>
<body background="./l.jpg">
<p align="center"><b>添加操作员信息：</b></p>
<form action="operateradd1.jsp" method="post">
<p align="center">o_account(varchar 8):</p>
<p align="center"><input type="text" name="o_account" ></p>
<p align="center">o_name(varchar 20):</p>
<p align="center"><input type="text" name="o_name" ></p>
<p align="center">o_phone(varchar 11):</p>
<p align="center"><input type="text" name="o_phone" ></p>
<p align="center">o_email(varchar 30):</p>
<p align="center"><input type="text" name="o_email" ></p>
<p align="center">o_pwd(varchar 8):</p>
<p align="center"><input type="text" name="o_pwd" ></p>

<p align="center"><input type="submit" name="添加" value="添加"></p>
</form>
<form action="operater.jsp" method="post">
<p align="center"><input type="submit" name="退回" value="返回"></p></form>
</body>
</html>