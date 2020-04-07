package com.servlet.comm;


import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ideabobo.db.DBTool;
import com.ideabobo.util.IdeaServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends IdeaServlet implements Servlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		System.out.println("文件接收");
		String fileName = uploadFile();
		//fileName = "/upload/" + fileName;
		if(fileName.length()>0){
			fileName = fileName.substring(0,fileName.length()-1);
		}
		render(fileName);
	}

}
