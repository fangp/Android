<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新数据</title>
<meta http-equiv="refresh" content="2;url=operateredit.jsp">
</head>
<body background="./u.jpg">
<% 
    String OACCOUNT=request.getParameter("oaccount");
    String ONAME=request.getParameter("oname");
    String OPHONE=request.getParameter("ophone");
    String OEMAIL=request.getParameter("oemail");
    String  OPWD=request.getParameter("opwd");
   String className="com.mysql.jdbc.Driver";
   String url="jdbc:mysql://localhost:3306/tianyita";
   String user="root";
   String password="zouni";
   Connection conn;
   Statement st;
   Class.forName(className);
   conn=DriverManager.getConnection(url, user,password);
   st=(Statement)conn.createStatement();
   String sql="update operater set o_name='"+ONAME+"',o_phone='"+OPHONE+"',o_email='"+OEMAIL+"',o_pwd='"+OPWD+"' where o_account="+OACCOUNT+"";
   st.executeUpdate(sql);
	   out.println("信息修改成功,2秒后跳转......"); 
   conn.close();
%>
</body>
</html>