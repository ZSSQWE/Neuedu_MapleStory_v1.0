package com.neuedu.maplestory.constant;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;
/**
 * 
* @ClassName: Constant
* @Description: TODO(常量类)
* @author 张盛爽
* @date 2019年8月25日
*
 */
public class Constant {
	/**
	 * 创建一个Properties类
	 */
	public static Properties prop = new Properties();
	
	/**
	 * 创建一个Properties类
	 */
	public static Properties userprop = new Properties();
	static {
		try {
             /**
              * 通过反射和类加载将文件里的数据引入
              */
			prop.load(Constant.class.getClassLoader().getResourceAsStream("maplestory.properties"));
			userprop.load(Constant.class.getClassLoader().getResourceAsStream("user.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 设置屏幕宽度
	 */
	public static  int GAME_WIDTH = Integer.parseInt(prop.getProperty("GAME_WIDTH"));
	/**
	 * 设置屏幕高度
	 */
	public static  int GAME_HEIGHT = Integer.parseInt(prop.getProperty("GAME_HEIGHT"));
	/**
	 * 设置图片前缀
	 */
	public static  String PREFIX = prop.getProperty("PREFIX");
	/**
	 * 设置英雄速度
	 */
	public static  int SPEED = Integer.parseInt(prop.getProperty("SPEED"));
	/**
	 * 设置英雄跳跃速度
	 */
	public static  int HERO_JUMPSPEED = Integer.parseInt(prop.getProperty("HERO_JUMPSPEED"));
	
     
}
