package com.ideabobo.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Utils {
	
	/**
	 * MD5加密
	 * @param pwd
	 * @return
	 */
	public final static String MD5(String pwd) {  
        //用于加密的字符  
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'A', 'B', 'C', 'D', 'E', 'F' };  
        try {  
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中  
            byte[] btInput = pwd.getBytes();  
               
            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
               
            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要  
            mdInst.update(btInput);  
               
            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文  
            byte[] md = mdInst.digest();  
               
            // 把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {   //  i = 0  
                byte byte0 = md[i];  //95  
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5   
                str[k++] = md5String[byte0 & 0xf];   //   F  
            }  
               
            //返回经过加密后的字符串  
            return new String(str);  
               
        } catch (Exception e) {  
            return null;  
        }  
    }  
	
	/**
	 * 邮件发送
	 * @param emailTitle
	 * @param content
	 * @param toEmailAddress
	 * @throws MessagingException
	 */
	public static void senEmail(String emailTitle,String content,String toEmailAddress) throws MessagingException {

        // 创建Properties 类用于记录邮箱的一些属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", "587");
        // 发送账号
        props.put("mail.user", "753764004@qq.com");
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", "yljamzemdurgbcaa");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(toEmailAddress);
        message.setRecipient(RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject(emailTitle);

        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");

        // 最后当然就是发送邮件啦
        Transport.send(message);
    }
	
	public static boolean isStringNotNull(String string) {
		if (string != null && !string.equals("") && !string.equalsIgnoreCase("null")) {
			return true;
		}

		return false;
	}
	
	private static SimpleDateFormat sdf=null;
	
	public static String getCurrentDateStr(){
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	public static String getCurrentDateTimeStr(){
		sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static String getCurrentTimeStr(){
		sdf=new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * 判断List是否不为空,size是否大于0
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> boolean listIsNotNull(List<T> list){
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

}
