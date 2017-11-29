<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改应用类型信息</title>
<meta http-equiv="refresh" content="1;url=editapptype.jsp">
</head>
<body   background="./r.jpg">
<% 
    String tid=request.getParameter("TID");
    String tname=request.getParameter("TNAME");
   String className="com.mysql.jdbc.Driver";
   String url="jdbc:mysql://localhost:3306/tianyita";
   String user="root";
   String password="zouni";
   Connection conn;
   Statement st;
   Class.forName(className);
   conn=DriverManager.getConnection(url, user,password);
   st=(Statement)conn.createStatement();
   String sql="update apptype set t_name='"+tname+"' where t_id="+tid+"";
   st.executeUpdate(sql);
	   out.println("信息修改成功,1秒后跳转......"); 
   conn.close();
%>
</body>
</html>