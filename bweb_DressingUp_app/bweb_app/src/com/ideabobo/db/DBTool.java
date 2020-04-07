package com.ideabobo.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ideabobo.util.Utils;

public class DBTool {
	public Connection conn = JdbcUtil.getConnection();
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public Object obj = null;
	public String tableName = null;
	public Class c = null;
	public Field[] fields = null;
	public DBTool(String tm,Class c){
		this.c = c;
		// 识别表名称
		if(tm != null){
			this.tableName = tm;
		}else{
			// 根据对象命名 前缀加对象名称（小写）
			this.tableName = ConfigUtil.px+c.getSimpleName().toLowerCase();
		}		
		this.fields = c.getFields();
	}
	
	// 查询
	public ArrayList select(Object model,boolean flag) throws Exception {
		ArrayList al = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from "+tableName+" where 1=1");
		if(model!=null){
			for(int i=0;i<this.fields.length;i++){
				Field field = this.fields[i];
				String vname = field.getName();
				Object value = field.get(model); 
				if(value != null){
					if(flag){
						// String 类型构造 sql语句
						if(value instanceof String){
							String fv = value.toString();
							sb.append(" and "+vname+" like '%"+fv+"%'");
						// Integer 类型构造 sql语句
						}else if(value instanceof Integer){
							int fv = (Integer)value;
							if(fv!=0){
								sb.append(" and "+vname+"="+fv);
							}					
						}
					}else{
						if(value instanceof String){
							String fv = value.toString();
							sb.append(" and "+vname+" = '"+fv+"'");
						}else if(value instanceof Integer){
							int fv = (Integer)value;
							if(fv!=0){
								sb.append(" and "+vname+"="+fv);
							}					
						}
					}
					
				}
			}
		}
		// 输出sql语句
		System.out.println(sb.toString());		
		ps = conn.prepareStatement(sb.toString());
		rs = ps.executeQuery();
		while(rs.next()){
			Object result = c.newInstance();
			for(int i=0;i<this.fields.length;i++){
				Field field = this.fields[i];
				String vname = field.getName();
				try{
					if(field.getType().getSimpleName().equals("Integer")){
						field.set(result, rs.getInt(vname));
					}else if(field.getType().getSimpleName().equals("String")){
						field.set(result, rs.getString(vname));
					}
				} catch(Exception e){
					
				}
								
			}
			al.add(result);
		}
		return al;
	}
	
	
	public ArrayList select(String where) throws Exception {
		ArrayList al = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from "+tableName+" "+where);

		System.out.println(sb.toString());		
		ps = conn.prepareStatement(sb.toString());
		rs = ps.executeQuery();
		while(rs.next()){
			Object result = c.newInstance();
			for(int i=0;i<this.fields.length;i++){
				Field field = this.fields[i];
				String vname = field.getName();
				try{
					if(field.getType().getSimpleName().equals("Integer")){
						field.set(result, rs.getInt(vname));
					}else if(field.getType().getSimpleName().equals("String")){
						field.set(result, rs.getString(vname));
					}
				} catch(Exception e){
					
				}
								
			}
			al.add(result);
		}
		return al;
	}
	
	
	
	public int insert(Object o) throws Exception {
		Class c = o.getClass();
		Field[] fs = c.getFields();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into " + tableName);
		StringBuffer sql_name = new StringBuffer();
		StringBuffer sql_value = new StringBuffer();
		for (Field f : fs) {
			if ("id".equals(f.getName())) {
				
			} else {
				if (f.getType().getSimpleName().equals("String")) {
					sql_name.append(f.getName().toLowerCase() + ",");
					String fd="";
					if(f.get(o)!=null){
						fd = (String) f.get(o);
					}
					if(f.getName().toLowerCase().equals("date")){
						sql_value.append("\'" + Utils.getCurrentDateTimeStr() + "\'" + ",");
					}else{
						sql_value.append("\'" + fd + "\'" + ",");
					}
				} else {
					sql_name.append(f.getName().toLowerCase() + ",");
					sql_value.append(f.get(o) + ",");
				}
			}
		}
		String names = sql_name.toString().substring(0, sql_name.length() - 1);
		String values = sql_value.toString().substring(0,sql_value.length() - 1);
		sql.append("(" + names + ")").append(" ").append("values(").append(values).append(");");
		System.out.println(sql.toString());
		ps = conn.prepareStatement(sql.toString());
		return ps.executeUpdate();		
	}
	
	
	public int update(Object o) throws Exception {
		Class c = o.getClass();
		Field[] fs = c.getFields();
		StringBuffer sql = new StringBuffer();
		sql.append("update " + tableName + " set ");
		String id = "";
		for(int i=0;i<fs.length;i++){			
			Field f = fs[i];
			if ("id".equals(f.getName())) {
				id = f.get(o).toString();
				continue;
			} else {
				if (f.getType().getSimpleName().equals("String")) {
					if(f.get(o)!=null){
						sql.append(f.getName().toLowerCase()+"="+"\'" + f.get(o) + "\'" + ",");
					};					
				} else {
					if(f.get(o)!=null){
						sql.append(f.getName().toLowerCase()+"=" + f.get(o)+",");
					}					
				}
			}			
		}
		String sql2 = sql.toString().substring(0,sql.length() - 1)+" where id="+id;
		System.out.println("update sql:"+sql2);
		ps = conn.prepareStatement(sql2);
		return ps.executeUpdate();		
	}
	
	public int delete(Object o) throws Exception {
		Class c = o.getClass();
		String id = (c.getField("id").get(o)).toString();
		String sql = "delete from " + tableName + " where id="+id;
		ps = conn.prepareStatement(sql);
		return ps.executeUpdate();		
	}
	
	public int delete(int id) throws Exception {
		String sql = "delete from " + tableName + " where id="+id;
		ps = conn.prepareStatement(sql);
		return ps.executeUpdate();		
	}
	
	public int delete(String tid) throws Exception {
		int id = Integer.parseInt(tid);
		String sql = "delete from " + tableName + " where id="+id;
		ps = conn.prepareStatement(sql);
		return ps.executeUpdate();		
	}
	
	public Page getByPage(Page page) throws Exception {
		ArrayList al = new ArrayList();
		StringBuffer sb = new StringBuffer();
		StringBuffer countsql = new StringBuffer();
		countsql.append("select count(*) from "+tableName+" where 1=1");
		sb.append("select * from "+tableName+" where 1=1");
		if(page.model!=null){
			for(int i=0;i<this.fields.length;i++){
				Field field = this.fields[i];
				String vname = field.getName();
				Object value = field.get(page.model); 
				if(value != null){
					if(value instanceof String){
						String fv = value.toString();
						sb.append(" and "+vname+" like '%"+fv+"%'");
						countsql.append(" and "+vname+" like '%"+fv+"%'");
					}else if(value instanceof Integer){
						int fv = (Integer)value;
						sb.append(" and "+vname+"="+fv);
						countsql.append(" and "+vname+"="+fv);
											
					}
				}
			}
		}
		ps = conn.prepareStatement(countsql.toString());
		rs = ps.executeQuery();
		if(rs.next()){
			page.total = rs.getInt(1);
		}
		sb.append(" limit "+((page.pageNo-1)*(page.pageSize))+","+page.pageSize);
		System.out.println(sb.toString());
		ps = conn.prepareStatement(sb.toString());
		rs = ps.executeQuery();
		while(rs.next()){
			Object result = c.newInstance();
			for(int i=0;i<this.fields.length;i++){
				Field field = this.fields[i];
				String vname = field.getName();
				
				if(field.getType().getSimpleName().equals("Integer")){
					field.set(result, rs.getInt(vname));
				}else if(field.getType().getSimpleName().equals("String")){
					field.set(result, rs.getString(vname));
				}				
			}
			al.add(result);
		}
		page.totalPage = page.total/page.pageSize + 1;
		
		page.rows = al;
		return page;
	}
}
