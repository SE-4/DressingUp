package com.servlet.comm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.ideabobo.db.DBTool;
import com.ideabobo.util.IdeaServlet;
import com.ideabobo.util.Utils;

public class ClientServlet extends IdeaServlet {
	private DBTool userdbTool = new DBTool(null, User.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		String action = request.getParameter("action");
		if(action.equals("login")){
			login();
		}else if(action.equals("register")){
			register();
		}else if(action.equals("checkUser")){
			checkUser();
		}else if(action.equals("updateUser")){
			updateUser();
		}else if(action.equals("findPasswd")){
			findPasswd();
		}else if(action.equals("updatePassword")){
			updatePassword();
		}else if(action.equals("updateUser")){
			updateUser();
		}
		
		
	}
	
	// 登录
	public void login(){
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		User user = new User();
		user.username = username;
		user.passwd = passwd;
		try {
			ArrayList<User> rl = userdbTool.select(user,false);
			if(rl!=null&&rl.size()>0){
				User user2 = rl.get(0);
				if(user2.roletype.equals("2")){
					renderJsonToClient(rl.get(0));
				}else{
					renderJsonToClient("fail");
				}
				
			}else{
				renderJsonToClient("fail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 注册
	public void register(){
		User user = (User) getByRequest(User.class,false);
		
		try {
			
			List<User> users = userdbTool.select("where username='"+user.username+"'");
			if(users.size()>0){
				render("fail,用户名已存在");
			}else{
				// 普通用户
				user.roletype = "2";
				userdbTool.insert(user);
				renderJsonpString("success");
			}

		} catch (Exception e) {
			renderJsonpString("fail");
			e.printStackTrace();
		}
		
	}
	
	public void checkUser(){
		User u = new User();
		u.username = request.getParameter("username");
		try {
			ArrayList rl = userdbTool.select(u,false);
			if(rl!=null&&rl.size()>0){
				renderJsonpString("fail");
			}else{
				renderJsonpString("success");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUser(){
		User user = (User) getByRequest(User.class,false);
		try {
			userdbTool.update(user);
			renderJsonpString("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 找回密码
	public void findPasswd(){
		User u = new User();
		u.username = request.getParameter("username");
		u.email = request.getParameter("toEmail");
		try {
			ArrayList<User> rl = userdbTool.select(u,false);
			if(rl!=null&&rl.size()>0){
				System.out.println("email:"+rl.get(0).email);
				Utils.senEmail("找回密码", "用户名："+rl.get(0).username+" 密码："+rl.get(0).passwd, rl.get(0).email);
				renderJsonToClient("success");
			}else{
				renderJsonToClient("fail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void updatePassword(){
		String username = request.getParameter("username");
		String passwd = request.getParameter("newPasswd");
		User user = new User();
		user.username = username;
		System.out.println("passwd:"+passwd);
		try {
			ArrayList<User> rl = userdbTool.select(user,false);
			if(rl!=null&&rl.size()>0){
				User user2 = rl.get(0);
				user2.passwd = passwd;
				userdbTool.update(user2);
				renderJsonToClient(user2);
			}else{
				renderJsonToClient("fail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
