package com.servlet.comm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.ideabobo.db.DBTool;
import com.ideabobo.util.IdeaServlet;

public class IndexServlet extends IdeaServlet {
	private DBTool userDb = new DBTool(null, User.class);

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);		
		String action = request.getParameter("action");
		if(action == null){
			// 获取登录的用户名、无登录用户名进行登录，有登陆进入主页
			String username = (String) session.getAttribute("username");
			
			if(username == null){
				request.getRequestDispatcher("pages/Index/login.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("pages/Index/index.jsp").forward(request, response);
			}
		}else if(action.equals("login")){
			String username = request.getParameter("username");
			//String roletype = request.getParameter("roletype");
			String passwd = request.getParameter("passwd");
			User u = new User();
			u.username = username;
			//u.roletype = roletype;
			u.passwd = passwd;
			
			
			try {
				ArrayList list = userDb.select(u,false);
				if(list!=null && list.size()>0){
					u = (User) list.get(0);
					session.setAttribute("username", username);
					session.setAttribute("roletype", u.roletype);
					renderInfoString("1");	
				}else{
					renderInfoString("0");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equals("logout")){
			// 去除 session
			session.removeAttribute("username");
			session.removeAttribute("roletype");
			// 页面跳转
			request.getRequestDispatcher("pages/Index/login.jsp").forward(request, response);
		}else if(action.equals("changeTheme")){
			String name = request.getParameter("name");
			session.setAttribute("tname", name);
			request.getRequestDispatcher("pages/Index/index.jsp").forward(request, response);
		}else if(action.equals("pre")){
			request.getRequestDispatcher("pre/main.jsp").forward(request, response);
		}		
	}
}
