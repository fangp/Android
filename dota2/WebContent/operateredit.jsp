<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑操作员信息</title>
</head>
<body background="./m.jpg">
<p align="center"><b>操作员</b></p>
<table border=1 align="center">
<tr align="center">
<td><b>o_account(不能改动)</b></td>
 <td><b>o_name</b></td>
<td><b>o_phone</b></td>
<td><b>o_email</b></td>
<td><b>o_pwd</b></td>
</tr>
<% 
String url="jdbc:mysql://localhost:3306/tianyita";
String user="root";
String password="zouni";
String sql="select * from operater";
Class.forName("com.mysql.jdbc.Driver");

Connection conn=DriverManager.getConnection(url,user,password);
Statement st=conn.createStatement();

ResultSet rs=st.executeQuery(sql);
%>

<%
while(rs.next())
{%>
<form action="operateredit1.jsp" method="post">
  <tr align="center">
  <td><input type="text" name="oaccount" value="<%=rs.getString("o_account") %>"></td>
  <td><input type="text" name="oname" value="<%=rs.getString("o_name") %>"></td>
   <td><input type="text" name="ophone" value="<%=rs.getString("o_phone") %>"></td>
    <td><input type="text" name="oemail" value="<%=rs.getString("o_email") %>"></td>
     <td><input type="text" name="opwd" value="<%=rs.getString("o_pwd") %>"></td>
  <td><input type="submit" name="submit" value="提交"></td>
  </tr></form>
  
<%} 
rs.close();
st.close();
conn.close();
%>
</table>
<table align="center">
<tr align="center"><td><form action="operater.jsp" method="post">
<input type="submit" name="返回" value="返回"></form></td></tr>
</table>
</body>
</html>