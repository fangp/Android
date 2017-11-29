<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
    <%  String path=request.getContextPath();
        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加业务推送</title>
<meta http-equiv="refresh" content="1;url=busdevelop.jsp">
</head>
<body   background="./q.jpg">
<% int bid=Integer.parseInt(request.getParameter("b_id"));
   String bname=request.getParameter("b_name");

   String bcontent=request.getParameter("b_content");
   String className="com.mysql.jdbc.Driver";
   String url="jdbc:mysql://localhost:3306/tianyita";
   String user="root";
   String password="zouni";
   Connection conn;
   Statement st;
   Class.forName(className);
   conn=DriverManager.getConnection(url, user,password);
   String sql="INSERT INTO busdevelop(b_id,b_name,b_content) values('"+bid+"','"+bname+"','"+bcontent+"')";
   st=(Statement)conn.createStatement();
  int count=st.executeUpdate(sql);
  if(count>0)
  {
	   out.print("信息添加成功,1秒后自动跳转......");
  }
   conn.close();
%>
</body>
</html>