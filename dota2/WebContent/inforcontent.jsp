<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息管理</title>
</head>
<body background="./j.jpg" >
<p align="center"><b>添加信息</b></p>
<form action="inforcontent1.jsp" method="post">
<p align="center"><b>con_id(int 11):</b></p>
<p align="center"><input type="text" name="con_id" ></p>
<p align="center"><b>infor_id(int 11):</b></p>
<p align="center"><input type="text" name="infor_id" ></p>
<p align="center"><b>con_title(varchar 20):</b></p>
<p align="center"><input type="text" name="con_title" ></p>
<p align="center"><b>con_date(date 年-月-日 ):</b></p>
<p align="center"><input type="text" name="con_date" ></p>
<p align="center"><b>con_text(text):</b></p>
<p align="center"><input type="text" name="con_text" ></p>

<p align="center"><input type="submit" name="添加" value="添加"></p>
</form>
<form action="busmag.jsp" method="post">
<p align="center"><input type="submit" name="退回" value="返回"></p></form>
</body>
</html>