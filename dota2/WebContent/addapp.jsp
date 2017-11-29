<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加应用</title>
</head>
<body background="./d.jpg" >
<form action="addapp1.jsp" method="post">
<p align="center"><b>a_id(int 11):</b></p>
<p align="center"><input type="text" name="a_id" >
<p align="center"><b>a_name(varchar 20):</b></p>
<p align="center"><input type="text" name="a_name" ></p>
<p align="center"><b>a_download(int 11):</b></p>
<p align="center"><input type="text" name="a_download" ></p>
<p align="center"><b>a_score(float 5):</b></p>
<p align="center"><input type="text" name="a_score" ></p>
<p align="center"><b>a_recomment(int 11):</b></p>
<p align="center"><input type="text" name="a_recommend" ></p>
<p align="center"><b>a_address(varchar 50):</b></p>
<p align="center"><input type="text" name="a_address" ></p>
<p align="center"><input type="submit" name="添加" value="添加"></p>
</form>
<form action="app.jsp" method="post">
<p align="center"><input type="submit" name="退回" value="返回"></p></form>
</body>
</html>