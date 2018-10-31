package com.ctfo.mobileapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置文件信息
 * 
 * @author LiangJian 2012-10-13 16:56:51
 */
public enum PropertiesUtil {
	// 枚举方式单例模式
	PROPERTIES;
	Logger logger=Logger.getLogger(PropertiesUtil.class);
	Properties configProperties=new Properties();
	private PropertiesUtil() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("system.properties");
		try {
			configProperties.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			 logger.error("load config failed!",e);
		}
		
	}

	/**
	 * 获取配置文件中的值
	 * 
	 * @param path
	 *            配置文件路径
	 * @param key
	 *            配置文件中的key
	 * @return
	 */
	public String read(String path, String key) {
		// 读取配置文件
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 取得配置文件中的值
		return p.getProperty(key);
	}
	
	public String getStringVal(String key,String def){
		String str=configProperties.getProperty(key);
		if(str!=null&&str.trim().length()>0){
			return str;
		}else{
			return def;
		}
		
	}
	public int getIntVal(String key,int def){
		try{
		String str=configProperties.getProperty(key);
		if(str!=null&&str.trim().length()>0){
			return Integer.parseInt(str);
		}else{
			return def;
		}
		}catch (Exception e) {
			logger.warn("get int value faied! "+key+",default is apply "+def);
			return def;
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			String value = PropertiesUtil.PROPERTIES.read("system.properties", "attach_url");
			System.out.println(value);
			Thread.sleep(5000);
		}
		
	}
}
