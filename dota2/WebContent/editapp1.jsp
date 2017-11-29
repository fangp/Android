<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新数据</title>
<meta http-equiv="refresh" content="1;url=editapp.jsp">
</head>
<body  background="./z.jpg">
<% 
    String aid=request.getParameter("AID");
    String aname=request.getParameter("ANAME");
   
    String  apicture=request.getParameter("APICTURE");
   String aaddress=request.getParameter("AADDRESS");
   String className="com.mysql.jdbc.Driver";
   String url="jdbc:mysql://localhost:3306/tianyita";
   String user="root";
   String password="zouni";
   Connection conn;
   Statement st;
   Class.forName(className);
   conn=DriverManager.getConnection(url, user,password);
   st=(Statement)conn.createStatement();
   String sql="update app set a_name='"+aname+"',a_picture='"+apicture+"',a_address='"+aaddress+"' where a_id="+aid+"";
   st.executeUpdate(sql);
	   out.println("信息修改成功,1秒后跳转......"); 
   conn.close();
%>
</body>
</html>