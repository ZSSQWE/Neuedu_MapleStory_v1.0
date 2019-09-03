package com.neuedu.maplestory.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.neuedu.maplestory.constant.Constant;
/**
 * 
* @ClassName: imageUtil
* @Description: TODO(图片加载类)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public final class imageUtil {
	/**
	 * 定于map数组
	 */
	private static Map<String, Image> images = new HashMap<String, Image>();
    /**
     * 静态加载图片到内存
     */
	static {

		images.put("icon", imageUtil.getImage("icon/太阳"));
		images.put("gameover", imageUtil.getImage("gameover"));
		images.put("virtory", imageUtil.getImage("timg"));

		images.put("background" , imageUtil.getImage("background/bg"));

		for (int i = 0; i < 5; i++) {
			images.put("floor" + i, imageUtil.getImage("background/foot/0" + (i + 1)));
		}

		for (int i = 0; i < 13; i++) {
			images.put("plan" + i, imageUtil.getImage("background/plan/" + i));
		}

		images.put("tree0", imageUtil.getImage("background/tree/0"));
		images.put("tree1", imageUtil.getImage("background/tree/120"));
		images.put("tree2", imageUtil.getImage("background/tree/240"));
		images.put("tree3", imageUtil.getImage("background/tree/360"));

		for (int i = 0; i < 8; i++) {
			images.put("door" + i, imageUtil.getImage("background/door/" + (i * 210)));
		}

		images.put("loginbackground", imageUtil.getImage("background/loginbg"));

		
		for (int i = 0; i < 4; i++) {
			images.put("hero_right_stand" + i, imageUtil.getImage("hero/right/stand/stand1_" + i));
		}


		for (int i = 0; i < 4; i++) {
			images.put("hero_left_stand" + i, imageUtil.getImage("hero/left/stand/stand1_" + i));
		}

		
		for (int i = 0; i < 5; i++) {
			images.put("hero_right_walk" + i, imageUtil.getImage("hero/right/walk/walk1_" + i));
		}

		for (int i = 0; i < 5; i++) {
			images.put("hero_left_walk" + i, imageUtil.getImage("hero/left/walk/walk1_" + i));
		}
		

		images.put("hero_right_jump", imageUtil.getImage("hero/right/jump/jump_0"));


		images.put("hero_left_jump", imageUtil.getImage("hero/left/jump/jump_0"));
	
		for (int i = 0; i < 4; i++) {
			images.put("hero_right_shoot" + i, imageUtil.getImage("hero/right/shoot/shoot1_" + i));
		}

		
		for (int i = 0; i < 4; i++) {
			images.put("hero_left_shoot" + i, imageUtil.getImage("hero/left/shoot/shoot1_" + i));
		}

		images.put("bullet_r", imageUtil.getImage("bullet/right/bullet_r"));
		images.put("bullet_l", imageUtil.getImage("bullet/left/bullet_l"));
		
		for (int i = 0; i < 8; i++) {
			images.put("attack_l" + i, imageUtil.getImage("hero/left/swing/swingT1_" + i));
		}
	
		for (int i = 0; i < 8; i++) {
			images.put("attack_r" + i, imageUtil.getImage("hero/right/swing/swingT1_" + i));
		}


		images.put("effects_l", imageUtil.getImage("attack/hero/left/1412-8"));

		

		images.put("effects_r", imageUtil.getImage("attack/hero/right/1412-8"));

		for (int i = 1; i < 11; i++) {
			images.put("bigbullet" + i, imageUtil.getImage("bullet/bullet2/right/1064-" + i));

		}

		for (int i = 1; i < 13; i++) {
			images.put("water_column" + i, imageUtil.getImage("bullet/bullet3/1050-" + i));
		}

		for (int i = 1; i < 9; i++) {
			images.put("bomb" + i, imageUtil.getImage("bullet/bullet4/1524-" + i));
		}


		images.put("mob2_bullet_r", imageUtil.getImage("mob/mob2/bullet/01"));
		images.put("mob2_bullet_l", imageUtil.getImage("mob/mob2/bullet/02"));

		for (int i = 0; i < 4; i++) {
			images.put("mob2_left_stand" + i, imageUtil.getImage("mob/mob2/stand/left/stand1_" + i));
		}

		for (int i = 0; i < 4; i++) {
			images.put("mob2_right_stand" + i, imageUtil.getImage("mob/mob2/stand/right/stand1_" + i));
		}

		for (int i = 0; i < 5; i++) {
			images.put("mob2_left_walk" + i, imageUtil.getImage("mob/mob2/walk/left/walk2_" + i));
		}

		for (int i = 0; i < 5; i++) {
			images.put("mob2_right_walk" + i, imageUtil.getImage("mob/mob2/walk/right/walk2_" + i));
		}

		for (int i = 0; i < 3; i++) {
			images.put("mob2_left_die" + i, imageUtil.getImage("mob/mob2/die/left/proneStab_" + i));
		}

		for (int i = 0; i < 3; i++) {
			images.put("mob2_right_die" + i, imageUtil.getImage("mob/mob2/die/right/proneStab_" + i));
		}

		for (int i = 0; i < 4; i++) {

			images.put("mob2_left_attack" + i, imageUtil.getImage("mob/mob2/attack/left/shoot1_" + i));
		}
		for (int i = 0; i < 4; i++) {

			images.put("mob2_right_attack" + i, imageUtil.getImage("mob/mob2/attack/right/shoot1_" + i));
		}

		for (int i = 1; i < 6; i++) {
			images.put("items" + i, imageUtil.getImage("items/hp/0" + i));
		}
		for (int i = 1; i < 6; i++) {
			images.put("items_HP" + i, imageUtil.getImage("items/mp/0" + i));
		}

		for (int i = 0; i < 15; i++) {

			images.put("mob3_attack_l" + i, imageUtil.getImage("mob/mob3/attack/left/" + i));
		}
		for (int i = 0; i < 15; i++) {

			images.put("mob3_attack_r" + i, imageUtil.getImage("mob/mob3/attack/right/" + i));
		}
		for (int i = 0; i < 6; i++) {

			images.put("mob3_stand_l" + i, imageUtil.getImage("mob/mob3/stand/left/" + i));
		}
		for (int i = 0; i < 6; i++) {

			images.put("mob3_stand_r" + i, imageUtil.getImage("mob/mob3/stand/right/" + i));
		}
		for (int i = 0; i < 15; i++) {

			images.put("mob3_die_l" + i, imageUtil.getImage("mob/mob3/die/left/" + i));
		}
		for (int i = 0; i < 15; i++) {

			images.put("mob3_die_r" + i, imageUtil.getImage("mob/mob3/die/right/" + i));
		}
		for (int i = 0; i < 6; i++) {

			images.put("mob3_walk_l" + i, imageUtil.getImage("mob/mob3/walk/left/" + i));
		}
		for (int i = 0; i < 6; i++) {

			images.put("mob3_walk_r" + i, imageUtil.getImage("mob/mob3/walk/right/" + i));
		}
		for (int i = 0; i < 13; i++) {

			images.put("mob3_bullet" + i, imageUtil.getImage("mob/mob3/bullet/" + (i * 90)));
		}
		for (int i = 0; i < 6; i++) {

			images.put("boss_stand_l" + i, imageUtil.getImage("boss/stand/left/" + (i * 180)));
		}
		for (int i = 0; i < 6; i++) {

			images.put("boss_stand_r" + i, imageUtil.getImage("boss/stand/right/" + (i * 180)));
		}
		for (int i = 0; i < 6; i++) {

			images.put("boss_walk_l" + i, imageUtil.getImage("boss/move/left/" + (i * 150)));
		}
		for (int i = 0; i < 6; i++) {

			images.put("boss_walk_r" + i, imageUtil.getImage("boss/move/right/" + (i * 150)));
		}
		for (int i = 0; i < 17; i++) {

			images.put("boss_die_l" + i, imageUtil.getImage("boss/die/left/" + (i * 180)));
		}
		for (int i = 0; i < 17; i++) {

			images.put("boss_die_r" + i, imageUtil.getImage("boss/die/right/" + (i * 180)));
		}
		for (int i = 0; i < 23; i++) {

			images.put("boss_attack1_l" + i, imageUtil.getImage("boss/attack1/left/" + (i * 120)));
		}
		for (int i = 0; i < 23; i++) {

			images.put("boss_attack1_r" + i, imageUtil.getImage("boss/attack1/right/" + (i * 120)));
		}
		
		for (int i = 0; i < 29; i++) {

			images.put("boss_attack2_l" + i, imageUtil.getImage("boss/attack2/left/" + (i * 120)));
		}
		for (int i = 0; i < 29; i++) {

			images.put("boss_attack2_r" + i, imageUtil.getImage("boss/attack2/right/" + (i * 120)));
		}
	
		for (int i = 0; i < 22; i++) {

			images.put("boss_skill1_l" + i, imageUtil.getImage("boss/skill1/left/" + (i * 120)));
		}
		for (int i = 0; i < 22; i++) {

			images.put("boss_skill1_r" + i, imageUtil.getImage("boss/skill1/right/" + (i * 120)));
		}
		
		for (int i = 0; i < 17; i++) {

			images.put("boss_skill2_l" + i, imageUtil.getImage("boss/skill2/left/" + (i * 120)));
		}
		for (int i = 0; i < 17; i++) {

			images.put("boss_skill2_r" + i, imageUtil.getImage("boss/skill2/right/" + (i * 120)));
		}

		for (int i = 0; i < 17; i++) {

			images.put("boss_skill3_l" + i, imageUtil.getImage("boss/skill3/left/" + (i * 150)));
		}
		for (int i = 0; i < 17; i++) {

			images.put("boss_skill3_r" + i, imageUtil.getImage("boss/skill3/right/" + (i * 150)));
		}

		for (int i = 0; i < 17; i++) {

			images.put("boss_skill4_l" + i, imageUtil.getImage("boss/skill4/left/" + (i * 150)));
		}
		for (int i = 0; i < 17; i++) {

			images.put("boss_skill4_r" + i, imageUtil.getImage("boss/skill4/right/" + (i * 150)));
		}
	}
     /**
      * 
     * @Title: getImage
     * @Description: TODO(获取图片工具类)
     * @param @param imgname 图片名字
     * @param @return    参数
     * @return Image    返回类型 图片类型
     * @throws
      */
	private static Image getImage(String imgname) {
		URL u = Constant.class.getClassLoader().getResource(Constant.PREFIX + imgname + ".png");
		BufferedImage image = null;
		try {
			image = ImageIO.read(u);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return image;

	}
    /**
     * 
    * @Title: getimage
    * @Description: TODO(工具类型)
    * @param @param key
    * @param @return    参数
    * @return Image    返回类型
    * @throws
     */
	public static Image getimage(String key) {
		return images.get(key);

	}

}