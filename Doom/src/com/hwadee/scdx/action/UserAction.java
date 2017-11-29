package com.hwadee.scdx.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 系统用户控制器
 * @author zhangQ
 * @create date: 2013-12-5
 */

@Controller
@RequestMapping(value = "/user")
public class UserAction {
	
	@Autowired
	DataSource datasource;

	private List<Map<String, Object>> queryForList=new ArrayList<Map<String,Object>>();

	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(String username,String password) {
		queryForList.clear();
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(username+":"+password);
		if(isExist(username,password)){
			map.put("result", 1);//表示登陆成功
			System.out.println("登陆成功，欢迎您："+username+"!");
		}else{
			map.put("result", 2);//表示登陆失败
			System.out.println("用户名或者密码错误！");
		}
		return JSONObject.toJSONString(map); 
	}
	
	
	private boolean isExist(String username,String password){
		JdbcTemplate temp = new JdbcTemplate(datasource);
		String sql = "select  * from operater";
		queryForList = temp.queryForList(sql);
		for(int i=0;i<queryForList.size();i++){
			if(queryForList.get(i).get("o_account").equals(username)
					&&queryForList.get(i).get("o_pwd").equals(password))
				return true;
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllSuggestion")
	public String getAllSuggestion() {
		queryForList.clear();
		JdbcTemplate temp = new JdbcTemplate(datasource);
		String sql = "select  * from request";
		queryForList = temp.queryForList(sql);
		for(int i=0;i<queryForList.size();i++){
			if(queryForList.get(i).get("m_account")==null
					&&queryForList.get(i).get("re_reponse")==null){
				queryForList.get(i).put("m_account", "");
				queryForList.get(i).put("re_reponse", "");
			}
			String type=queryForList.get(i).get("type_id").toString();
			List<Map<String, Object>> map=temp.queryForList("select * from requesttype where type_id=?",new String[]{type});
			queryForList.get(i).put("type_name",map.get(0).get("type_name"));
		}
		return JSONArray.toJSONString(queryForList); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/addSuggestion")
	public String addSuggestion(HttpServletRequest request,HttpServletResponse response) {
		String content=new String();
		String type=new String();
		String account=new String();
		try {
			content = URLDecoder.decode(request.getParameter("content"),"UTF-8");
			type=URLDecoder.decode(request.getParameter("type"),"UTF-8");
			account=URLDecoder.decode(request.getParameter("account"),"UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JdbcTemplate temp = new JdbcTemplate(datasource);
		
		try{
			String sql ="insert into request(o_account,type_id,re_content) "
				+ "values(?,?,?)";
			temp.update(sql,new String[]{account,type,content});
			return "1";//消息提交成功
		}catch(Exception e)
		{
			return "2";//消息提交失败
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/customerAction")
	public String customerAction(HttpServletRequest request, HttpServletResponse response) {
		queryForList.clear();
		String name = new String();
		String phone_num1 = new String();
		String phone_num2 = new String();
		String phone_num3 = new String();
		String phone_model =new String();
		String userName =new String();
		
		try {
			userName=URLDecoder.decode(request.getParameter("userName"),"UTF-8");
			name=URLDecoder.decode(request.getParameter("name"),"UTF-8");
			phone_num1=URLDecoder.decode(request.getParameter("phone_num1"),"UTF-8");
			phone_num2=URLDecoder.decode(request.getParameter("phone_num2"),"UTF-8");
			phone_num3=URLDecoder.decode(request.getParameter("phone_num3"),"UTF-8");
			phone_model=URLDecoder.decode(request.getParameter("phone_model"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JdbcTemplate temp = new JdbcTemplate(datasource);
		System.out.println(name+":"+phone_num1+":"+phone_num2+":"+phone_num3+":"+phone_model);
		if(name.equals("request_for_data") && phone_num1.equals("request_for_data"))
		{
			//String sql_custom ="";
			//Map<String,Object> map=temp.queryForMap("select * from customer where ");
			List<Map<String,Object>> queryID = temp.queryForList("select * from "
					+ "personalcus where o_account = ?",new String[]{userName});
			Map<String,Object> map=new HashMap<String,Object>();
			for(int i=0;i<queryID.size();i++){
				String cus=queryID.get(i).get("phone_num1").toString();
				map=temp.queryForMap(""
						+ "select * from customer where phone_num1=?",new String[]{cus});
				if(map.get("phone_num2")==null)
					map.put("phone_num2", "");
				if(map.get("phone_num3")==null)
					map.put("phone_num3", "");
				queryForList.add(map);
			}
			return JSONArray.toJSONString(queryForList);
		}
		else
		{
			temp.update("INSERT INTO customer(phone_num1,name,phone_num2,phone_num3,phone_model) VALUES(?,?,?,?,?)",
					new String[]{phone_num1,name,phone_num2,phone_num3,phone_model});
			temp.update("INSERT INTO personalcus VALUES(?,?)",
					new String[]{userName,phone_num1});
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllApp")
	public String getAllApp(HttpServletRequest request,HttpServletResponse response) {
		queryForList.clear();
		JdbcTemplate temp = new JdbcTemplate(datasource);
		String sql = "select  * from app";
		queryForList = temp.queryForList(sql);
		return JSONArray.toJSONString(queryForList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllInfo")
	public String getAllInfo(HttpServletRequest request,HttpServletResponse response){
		queryForList.clear();
		String userName=new String();
		String type_id=new String();
		try {
			userName=URLDecoder.decode(request.getParameter("o_account"),"UTF-8");
			type_id=URLDecoder.decode(request.getParameter("type_id"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userName+":"+type_id);
		JdbcTemplate temp = new JdbcTemplate(datasource);
		queryForList=temp.queryForList("select * from inforcontent where infor_id=?",new String[]{type_id});
		List<Map<String,Object>> lm=temp.queryForList("select * from readinfor where o_account=?",new String[]{userName});
		for(int j=0;j<queryForList.size();j++){
			if(lm.size()==0){
				queryForList.get(j).put("readable", "未读");
			}
			else{
				for(int i=0;i<lm.size();i++){
					if(queryForList.get(j).get("con_id").equals(lm.get(i).get("con_id"))){
						queryForList.get(j).put("readable", "已读");
						break;
					}
					else
						queryForList.get(j).put("readable", "未读");
				}
			}
		}
		System.out.println(queryForList.toString());
		return JSONArray.toJSONString(queryForList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyInfo")
	public void modifyInfo(HttpServletRequest request,HttpServletResponse response){
		boolean flag=false;
		JdbcTemplate temp = new JdbcTemplate(datasource);
		String userName=new String();
		String con_id=new String();
		try {
			userName=URLDecoder.decode(request.getParameter("o_account"),"UTF-8");
			con_id=URLDecoder.decode(request.getParameter("con_id"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queryForList.clear();
		queryForList=temp.queryForList("select * from readinfor");
		if(queryForList.size()!=0)
			for(int i=0;i<queryForList.size();i++){
				if(queryForList.get(i).get("o_account").equals(userName)&&
						queryForList.get(i).get("con_id").equals(con_id))
					flag=true;
			}
		if(!flag)
			temp.update("INSERT INTO readinfor(o_account,con_id) VALUES(?,?)",
					new String[]{userName,con_id});
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllBusiness")
	public String getAllBusiness(){
		queryForList.clear();
		JdbcTemplate temp = new JdbcTemplate(datasource);
		String sql = "select  * from busdevelop";
		queryForList = temp.queryForList(sql);
		System.out.println(queryForList);
		return JSONArray.toJSONString(queryForList);
	}
	
	
}
