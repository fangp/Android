<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
    <%  String path=request.getContextPath();
        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加应用</title>
<meta http-equiv="refresh" content="1;url=addapp.jsp">
</head>
<body background="./o.jpg">
<% int aid=Integer.parseInt(request.getParameter("a_id"));
   String aname=request.getParameter("a_name");
   int adownload=Integer.parseInt(request.getParameter("a_download"));
   float ascore=Float.valueOf(request.getParameter("a_score"));
   int  arecommend=Integer.parseInt(request.getParameter("a_recommend"));
   String aaddress=request.getParameter("a_address");
   String className="com.mysql.jdbc.Driver";
   String url="jdbc:mysql://localhost:3306/tianyita";
   String user="root";
   String password="zouni";
   Connection conn;
   Statement st;
   Class.forName(className);
   conn=DriverManager.getConnection(url, user,password);
   String sql="INSERT INTO app(a_id,a_name,a_download,a_score,a_recommend,a_address) values('"+aid+"','"+aname+"','"+adownload+"','"+ascore+"','"+arecommend+"','"+aaddress+"')";
   st=(Statement)conn.createStatement();
  int count=st.executeUpdate(sql);
  if(count>0)
  {
	   out.print("信息上传成功,1秒后自动跳转......");
  }
   conn.close();
%>
</body>
</html>