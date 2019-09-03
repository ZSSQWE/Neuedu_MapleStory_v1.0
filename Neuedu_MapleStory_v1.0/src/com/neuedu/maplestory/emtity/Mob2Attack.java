
package com.neuedu.maplestory.emtity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: Mob2Attack
* @Description: TODO(怪物攻击类)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public class Mob2Attack extends MapleStoryObject {
	/**
	 * 图片类型数组
	 */
	public static Image[] images = new Image[100];
	/**
	 * 静态代码块加载
	 */
	static {
		for (int i = 0; i < 13; i++) {
			images[i] = imageUtil.getimage("mob3_bullet" + i);
		}
	}
	 /**
     * 构造方法
     */
	public Mob2Attack() {

	}
	 /**
     * 
     * @param msc MapleStoryClient参数
     * @param x x坐标
     * @param y y坐标
     * @param dir 方向
     */
	public Mob2Attack(MapleStoryClient msc, int x, int y, Direction dir) {
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.img = images[0];
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 30;
		this.dir = dir;

	}

	@Override
	public void move() {
//		if (dir == Direction.LEFT) {
//			x -= speed;
//		} else {
//			x += speed;
//		}
	}
    /**
     * 图片数组下标
     */
	public int bulletcount = 0;

	@Override
	public void draw(Graphics g) {
		if (bulletcount > 12) {
			bulletcount = 0;
		}
		switch (dir) {
		case LEFT:
			g.drawImage(images[bulletcount++], x, y, null);
			break;
		case RIGHT:
			g.drawImage(images[bulletcount++], x, y, null);
			break;

		default:
			break;
		}

		move();

	}

	
    /**
     * 随机数变量
     */
	public static Random random = new Random();

	/**
	 * 
	* @Title: hit
	* @Description: TODO(怪物攻击与英雄碰撞)
	* @param @param hero 英雄
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean hit(Hero hero) {

		// 子弹存在，怪物存活的情况下进行碰撞检测
		if (this.live && hero.live && hero.action != Action.DEAD&& this.getRectangle().intersects(hero.getRectangle())) {
			this.live = false;
			if (bulletcount==12) {
				msc.aMob2Attacks.remove(this);
			}
			hero.HP-=10;
			if (hero.HP <= 0) {
				hero.action = Action.DEAD;
				
			}

			return true;
		}else if (bulletcount==12) {
			msc.aMob2Attacks.remove(this);
		}
		
		return false;

	}

//	public boolean hit(List<Mob> mobs) {
//		for (int i = 0; i < mobs.size(); i++) {
//			Mob mob = mobs.get(i);
//			if (this.hit(mob)) {
//				return true;
//			}
//		}
//
//		return false;
//
//	}

}
