package com.servlet.comm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.ideabobo.db.DBTool;
import com.ideabobo.db.Page;
import com.ideabobo.util.IdeaServlet;

public class UserServlet extends IdeaServlet {
	private DBTool dbTool = new DBTool(null, User.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		String action = request.getParameter("action");
		if(action == null){
			String pageNo = (String) this.request.getParameter("page");
			String pageSizes = (String) this.request.getParameter("rows");
			Page page = new Page();
			if (pageNo == null) {
				page.pageSize = 50;
				page.pageNo = 1;
			} else {
				page.pageSize = (Integer.parseInt(pageSizes));
				int pn = (Integer.parseInt(pageNo));
				page.pageNo = pn;
				if(pn<1){
					page.pageNo = 1;
				}
				
			}
			
			
			User model = new User();
			boolean flag = false;
			String username =  request.getParameter("searchStr");
			if(username != null && !username.equals("")){
				model.username = username;
				flag = true;
			}
			
			if(flag){
				page.model = model;
			}
			try {
				page = dbTool.getByPage(page);
				renderJson(page);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equals("add")){
			User model = (User) getByRequest(User.class,false);
			try {
				dbTool.insert(model);
				render("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(action.equals("edit")){
			User model = (User) getByRequest(User.class,false);
			try {
				dbTool.update(model);
				render("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equals("delete")){
			String id = request.getParameter("id");
			try {
				int c = dbTool.delete(Integer.parseInt(id));
				renderInfoString("success");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equals("select")){

			String pageNo = (String) this.request.getParameter("page");
			String pageSizes = (String) this.request.getParameter("rows");
			Page page = new Page();
			if (pageNo == null) {
				page.pageSize = 50;
				page.pageNo = 1;
			} else {
				page.pageSize = (Integer.parseInt(pageSizes));
				int pn = (Integer.parseInt(pageNo));
				page.pageNo = pn;
				if(pn<1){
					page.pageNo = 1;
				}
				
			}
			
			User model = new User();
			boolean flag = false;
			String searchStr =  request.getParameter("searchStr");
			if(searchStr != null && !searchStr.equals("")){
				model.name = searchStr;
				flag = true;
			}
			
			if(flag){
				page.model = model;
			}
			try {
				page = dbTool.getByPage(page);
				renderJson(page.rows);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
}
