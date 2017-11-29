<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
    <%  String path=request.getContextPath();
        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加应用类型</title>
<meta http-equiv="refresh" content="2;url=apptype.jsp">
</head>
<body background="./p.jpg">
<% int tid=Integer.parseInt(request.getParameter("t_id"));
   String tname=request.getParameter("t_name");
   String className="com.mysql.jdbc.Driver";
   String url="jdbc:mysql://localhost:3306/tianyita";
   String user="root";
   String password="zouni";
   Connection conn;
   Statement st;
   Class.forName(className);
   conn=DriverManager.getConnection(url, user,password);
   String sql="INSERT INTO apptype(t_id,t_name) values('"+tid+"','"+tname+"')";
   st=(Statement)conn.createStatement();
  int count=st.executeUpdate(sql);
  if(count>0)
  {
	   out.print("信息上传成功,2秒后自动跳转......");
  }
   conn.close();
%>
</body>
</html>