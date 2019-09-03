package com.neuedu.maplestory.emtity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: Mob1Attack
* @Description: TODO(怪物攻击类)
* @author 张盛爽
* @date 2019年8月26日
*
 */

public class Mob1Attack extends MapleStoryObject {
    /**
     * 构造方法
     */
	public Mob1Attack() {

	}
    /**
     * 
     * @param msc MapleStoryClient参数
     * @param x x坐标
     * @param y y坐标
     * @param dir 方向
     */
	public Mob1Attack(MapleStoryClient msc, int x, int y, Direction dir) {
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.img = imageUtil.getimage("mob2_bullet_l");
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.dir = dir;
		this.speed = 30;
		if (dir == Direction.LEFT) {
			this.img = imageUtil.getimage("mob2_bullet_l");
		} else {
			this.img = imageUtil.getimage("mob2_bullet_r");
		}
	}

	@Override
	public void move() {
		if (dir == Direction.LEFT) {
			x -= speed;
		} else {
			x += speed;
		}
		outofBounds();
	}

	public void outofBounds() {
		if (this.x > Constant.GAME_WIDTH + 100 || this.x < -200) {
			msc.attacks2.remove(this);
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();

	}
	/**
	 * 
	* @Title: hit
	* @Description: TODO(怪物技能与英雄碰撞)
	* @param @param hero 英雄
	* @param @return    参数
	* @return boolean    返回类型 false/true
	* @throws
	 */
	public boolean hit(Hero hero) {
		// 子弹存在，怪物存活的情况下进行碰撞检测
		if (this.live && hero.live && hero.action != Action.DEAD
				&& this.getRectangle().intersects(hero.getRectangle())) {
			this.live = false;
			msc.attacks2.remove(this);
			hero.HP-=20;
			return true;
		}

		return false;

	}
	/**
	 * 
	* @Title: hit
	* @Description: TODO(怪物数组的技能与英雄碰撞)
	* @param @param mobs 怪物数组
	* @param @param hero 英雄
	* @param @return    参数 false/true
	* @return boolean    返回类型
	* @throws
	 */
	public boolean hit(List<Mob> mobs,Hero hero) {
		for (int i = 0; i < mobs.size(); i++) {
			    Mob mob = mobs.get(i);
			   if (this.hit(hero)) { 
				 
				return true;
			  }
		}
		
		return false;

	}

}
