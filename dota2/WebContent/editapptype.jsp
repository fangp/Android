<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改应用类型信息</title>
</head>
<body background="./i.jpg" >
<p align="center"><b>应用类型信息</b></p>
<table border=1 align="center">
<tr align="center">
<td><b>t_id</b></td>
<td><b>t_name</b></td>
</tr>
<% 
String url="jdbc:mysql://localhost:3306/tianyita";
String user="root";
String password="zouni";
String sql="select * from apptype";
Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection(url,user,password);
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery(sql);
%>
<%
while(rs.next())
{%>
<form action="editapptype1.jsp" method="post">
  <tr align="center">
  <td><input type="text" name="TID" value="<%=rs.getString("t_id") %>"></td>
  <td><input type="text" name="TNAME" value="<%=rs.getString("t_name") %>"></td>
  <td><input type="submit" name="submit" value="修改"></td>
  </tr></form>
  
<%} 
rs.close();
st.close();
conn.close();
%>
</table>
<table align="center">
<tr align="center"><td><form action="apptype.jsp" method="post">
<input type="submit" name="返回" value="返回"></form></td></tr>
</table>
</body>
</html>