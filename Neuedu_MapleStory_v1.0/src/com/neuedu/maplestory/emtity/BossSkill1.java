
package com.neuedu.maplestory.emtity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: BossSkill1
* @Description: TODO(boss技能1的类)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public class BossSkill1 extends MapleStoryObject {
	/**
	 * 图片类型数组
	 */
	public static Image[] images = new Image[100];
	/**
	 * 静态代码块加载图片
	 */
	static {
		// skill1
		for (int i = 0; i < 22; i++) {
			images[i] = imageUtil.getimage("boss_skill1_l" + i);
		}
		for (int i = 22; i < 44; i++) {
			images[i] = imageUtil.getimage("boss_skill1_r" + (i - 22));
		}
	}
	/**
	    * 构造方法
	    */
	public BossSkill1() {

	}
	/**
	    * 带参构造方法
	    * @param msc MapleStoryClient参数
	    * @param x  x坐标
	    * @param y  y坐标
	    * @param dir 方向
	    */
	public BossSkill1(MapleStoryClient msc, int x, int y, Direction dir) {
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.width = images[0].getWidth(null);
		this.height = images[0].getHeight(null);
		this.dir = dir;
		this.speed = 30;
	}
    /**
     * 重写移动方法
     */
	@Override
	public void move() {

	}
	 /**
     * 图片数组下标
     */
	public int attackcount_l = 0;
	 /**
     * 图片数组下标
     */
	public int attackcount_r = 22;

	@Override
	public void draw(Graphics g) {
		if (attackcount_l > 21) {
			attackcount_l = 0;
		}
		if (attackcount_r > 43) {
			attackcount_r = 22;
		}
		switch (dir) {
		case LEFT:
			g.drawImage(images[attackcount_l++], x, y, null);
			break;
		case RIGHT:
			g.drawImage(images[attackcount_r++], x, y, null);
			break;
		default:
			break;
		}

		move();

	}
	/**
     * 
    * @Title: hit
    * @Description: TODO(boss攻击与英雄碰撞)
    * @param @param hero 英雄
    * @param @return    参数 ture/false
    * @return boolean    返回类型
    * @throws
     */
	public boolean hit(Hero hero) {
		// 子弹存在，怪物存活的情况下进行碰撞检测
		if (this.live && hero.live && hero.action != Action.DEAD
				&& this.getRectangle().intersects(hero.getRectangle())) {
			this.live = false;
			if (attackcount_l == 21 || attackcount_r == 43) {
				msc.sBossSkill1s.remove(this);
			}
			hero.HP -= hero.level*100;
			return true;
		} else if (attackcount_l == 21 || attackcount_r == 43) {
			msc.sBossSkill1s.remove(this);

		}

		return false;

	}

}
