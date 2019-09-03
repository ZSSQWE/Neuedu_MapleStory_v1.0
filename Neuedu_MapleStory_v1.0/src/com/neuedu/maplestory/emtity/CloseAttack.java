package com.neuedu.maplestory.emtity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.util.imageUtil;

public class CloseAttack extends MapleStoryObject {

	/**
	    * 构造方法
	    */
	public CloseAttack() {

	}
	 /**
	    * 带参构造方法
	    * @param msc MapleStoryClient参数
	    * @param x  x坐标
	    * @param y  y坐标
	    * @param dir 方向
	    */
	public CloseAttack(MapleStoryClient msc, int x, int y, Direction dir) {
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.img=imageUtil.getimage("effects_r");
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.dir = dir;
	}

	@Override
	public void move() {

	}


	@Override
	public void draw(Graphics g) {
		switch (dir) {
		case LEFT:
			g.drawImage(img, x, y, null);
			break;
		case RIGHT:
			g.drawImage(imageUtil.getimage("effects_l"), x, y, null);
			break;

		default:
			break;
		}
		
		move();

	}


	public static Random random = new Random();

	/**
	 * 
	* @Title: hit
	* @Description: TODO(英雄攻击与怪物碰撞)
	* @param @param mob 怪物
	* @param @param hero 英雄
	* @param @return    参数 true/false
	* @return boolean    返回类型
	* @throws
	 */
	public boolean hit(Mob mob,Hero hero) {
		// 子弹存在，怪物存活的情况下进行碰撞检测
		if (this.live && mob.live && mob.action != Action.DEAD && this.getRectangle().intersects(mob.getRectangle())) {
			this.live = false;
			
			if (mob.defensive>hero.aggressivity*1.5) {
				mob.HP-=1;
			}else {
				mob.HP -=hero.aggressivity*1.5-mob.defensive;
			}
			msc.attacks.remove(this);

			if (mob.HP <= 0) {
				mob.action = Action.DEAD;
				if (random.nextInt(100) > 0) {
					Items items = new Items(msc, mob.x + mob.width / 2, mob.y + mob.height - 30, random.nextInt(5));
					msc.items.add(items);
				}
				hero.level+=1;
				hero.aggressivity+=10;
				hero.defensive+=20;
			}

			return true;
		}
		if (!this.getRectangle().intersects(mob.getRectangle())) {
			msc.attacks.remove(this);
		}
			
		return false; 

	}
   /**
    * 
   * @Title: hit
   * @Description: TODO(怪物数组与英雄攻击碰撞)
   * @param @param mobs 怪物
   * @param @param hero 英雄
   * @param @return    参数 false/true
   * @return boolean    返回类型
   * @throws
    */
	public boolean hit(List<Mob> mobs,Hero hero) {
		if (mobs.size()==0) {
		
				msc.attacks.remove(this);

		}
		for (int i = 0; i < mobs.size(); i++) {
			Mob mob = mobs.get(i);
			if (this.hit(mob,hero)) {
				return true;
			}
		}

		return false;

	}

}
