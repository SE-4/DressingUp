package com.ideabobo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ideabobo.db.Page;


public class IdeaServlet  extends HttpServlet{
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	public Gson gson = new Gson();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		request.setCharacterEncoding("utf-8");
	}
	public void renderInfoString(String mesg){
		JsonObject info = new JsonObject();
		info.addProperty("info", mesg);
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(info.toString());
			System.out.println("info.toString():"+info.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 返回字符串
	public void render(String mesg){
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(mesg);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 返回json
	public void renderJson(Object obj){
		try {
			response.setContentType("text/html;charset=UTF-8");
			String json = gson.toJson(obj);
			System.out.println("json:"+json);
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 接受请求对象
	public Object getByRequest(Class c,boolean isGet){
		System.out.println("接受请求对象");
		Map<String,String> map = new HashMap<String, String>();
	    Enumeration<String> paramNames = request.getParameterNames();
	    while (paramNames.hasMoreElements()) {
	      String paramName = (String) paramNames.nextElement();
	      String[] paramValues = request.getParameterValues(paramName);
	      
          if(paramName.equals("date") && !Utils.isStringNotNull(paramValues[0])){
        	  paramValues[0] = Utils.getCurrentDateStr();
          }
          
	      if (paramValues.length == 1) {
	        String paramValue = paramValues[0];
	        if (paramValue.length() != 0) {
	          System.out.println("参数：" + paramName + "=" + paramValue);
	          map.put(paramName, paramValue);
	        }
	      }
	    }
	    
	    Field[] fields = c.getFields();
	    Object obj = null;
	    try {
	    	obj = c.newInstance();
	    	for(int i=0;i<fields.length;i++){
	    		Object vlue = map.get(fields[i].getName());
	    		if(vlue != null){
	    			if(fields[i].getType().getSimpleName().equals("String")){
	    				if(isGet){
	    					fields[i].set(obj, encodeGet(map.get(fields[i].getName())));
	    				}else{
	    					fields[i].set(obj, map.get(fields[i].getName()));
	    				}
		    			
		    		}else{
		    			String vl = map.get(fields[i].getName());
		    			fields[i].set(obj, Integer.parseInt(vl));
		    		}
	    		}		    	
		    }
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return obj;
	}
	
	public void renderJsonpString(String mesg){
		HashMap<String,String > obj = new HashMap<String, String>();
		obj.put("info", mesg);
		String callbackFunctionName = request.getParameter("callback");		
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(callbackFunctionName+"("+gson.toJson(obj)+")");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void renderJsonpObj(Object json){
		String callbackFunctionName = request.getParameter("callback");
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(callbackFunctionName+"("+gson.toJson(json)+")");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void renderJsonToClient(Object json){
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(gson.toJson(json));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void renderStringToClient(String mesg){
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(mesg);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String encodeGet(String str){
		if(str!=null){
			try {
				str = new String(str.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return str;
	}
	
	private String fnames="";
	public String uploadFile(){
		fnames="";
		String fname = "";//保存的
		String path = request.getSession().getServletContext().getRealPath("/upload/");
		// file upload factory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// setting upload file path
		factory.setRepository(new File(path));
		// set default memory size
		factory.setSizeThreshold(1024 * 1024 * 10);
		ServletFileUpload upload1 = new ServletFileUpload(factory);
		upload1.setHeaderEncoding("utf-8");// 这一句可以解决中文乱码的问题
		try {
			List<FileItem> list = upload1.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) { // 过滤掉表单中非文件域
					String name = item.getFieldName();// input name
					String value = item.getString("utf-8");// input content
					request.setAttribute(name, value);
				} else {
					String name = item.getFieldName();// input name
					String value = item.getName();// input content
					fname = UUID.randomUUID()+value.substring(value.lastIndexOf("."),value.length());
					// output file
					OutputStream fileOutStream = new FileOutputStream(new File(path, fname));
					// input file
					InputStream fileInputStream = item.getInputStream();
					// file buffer
					byte[] buffer = new byte[1024];
					// read
					int length = 0;
					while ((length = fileInputStream.read(buffer)) > 0) {
						fileOutStream.write(buffer, 0, length);
					}
					// close
					fileInputStream.close();
					fileOutStream.close();
					item.write(new File(path, fname));
					fnames = fnames + fname + ",";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fnames;
	}
	
}
