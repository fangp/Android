<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
    <%  String path=request.getContextPath();
        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加信息</title>
<meta http-equiv="refresh" content="1;url=inforcontent.jsp">
</head>
<body background="./s.jpg">
<% int conid=Integer.parseInt(request.getParameter("con_id"));
int inforid=Integer.parseInt(request.getParameter("infor_id"));
   String contitle=request.getParameter("con_title");
   String condate=request.getParameter("con_date");
   String content = request.getParameter("con_text");
   String className="com.mysql.jdbc.Driver";
   String url="jdbc:mysql://localhost:3306/tianyita";
   String user="root";
   String password="zouni";
   Connection conn;
   Statement st;
   Class.forName(className);
   conn=DriverManager.getConnection(url, user,password);
   String sql="INSERT INTO inforcontent(con_id,con_title,con_date,con_text) values('"+conid+"','"+contitle+"','"+condate+"','中文')";
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