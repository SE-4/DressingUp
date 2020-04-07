package com.servlet.comm;
  
  
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;  
  
  
public class StartServlet implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("服务器启动成功");
		
		String ip;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println("ip地址："+ip + " 如果不是局域网ip请自行使用 ipconfig 查询");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}  
       
        
      
}  