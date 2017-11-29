<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑应用信息</title>
</head>
<body background="./h.jpg" >
<p align="center"><b>应用信息</b></p>
<table border=1 align="center">
<tr align="center">
<td><b>a_id</b></td>
 <td><b>a_name</b></td>
<td><b>a_picture</b></td>
<td><b>a_address</b></td>
</tr>
<% 
String url="jdbc:mysql://localhost:3306/tianyita";
String user="root";
String password="zouni";
String sql="select * from app";
Class.forName("com.mysql.jdbc.Driver");

Connection conn=DriverManager.getConnection(url,user,password);
Statement st=conn.createStatement();

ResultSet rs=st.executeQuery(sql);
%>

<%
while(rs.next())
{%>
<form action="editapp1.jsp" method="post">
  <tr align="center">
  <td><input type="text" name="AID" value="<%=rs.getString("a_id") %>"></td>
  <td><input type="text" name="ANAME" value="<%=rs.getString("a_name") %>"></td>
    
     <td><input type="text" name="APICTURE" value="<%=rs.getString("a_picture") %>"></td>
  <td><input type="text" name="AADERESS" value="<%=rs.getString("a_address") %>"></td>
  <td><input type="submit" name="submit" value="提交"></td>
  </tr></form>
  
<%} 
%>
<%
rs.close();
st.close();
conn.close();
%>
</table>
<table align="center">
<tr align="center"><td><form action="app.jsp" method="post">
<input type="submit" name="返回" value="返回"></form></td></tr>
</table>
</body>
</html>