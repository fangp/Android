<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>业务推送</title>
</head>
<body background="./f.jpg" >
<form action="busdevelop1.jsp" method="post">
<p align="center"><b>b_id(int 11):</b></p>
<p align="center"><input type="text" name="b_id" ></p>
<p align="center"><b>b_name(varchar 20):</b></p>
<p align="center"><input type="text" name="b_name" ></p>
<p align="center"><b>b_content(varchar 255):</b></p>
<p align="center"><input type="text" name="b_varchar" ></p>

<p align="center"><input type="submit" name="添加" value="添加"></p>
</form>
<form action="busmag.jsp" method="post">
<p align="center"><input type="submit" name="退回" value="返回"></p></form>
</body>
</html>