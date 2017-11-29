<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
    <%  String path=request.getContextPath();
        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加操作员</title>
<meta http-equiv="refresh" content="1;url=operateradd.jsp">
</head>
<body background="./v.jpg">
<% 
   String oaccount=request.getParameter("o_account");
   String ophone=request.getParameter("o_phone");
   String oemail=request.getParameter("o_email");
   String opwd=request.getParameter("o_pwd");
   String oname=request.getParameter("o_name");
   String className="com.mysql.jdbc.Driver";
   String url="jdbc:mysql://localhost:3306/tianyita";
   String user="root";
   String password="zouni";
   Connection conn;
   Statement st;
   Class.forName(className);
   conn=DriverManager.getConnection(url, user,password);
   String sql="INSERT INTO operater(o_account,o_phone,o_email,o_name,o_pwd) values('"+oaccount+"','"+ophone+"','"+oemail+"','"+oname+"','"+opwd+"')";
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