package com.neuedu.maplestory.emtity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import javax.management.monitor.Monitor;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: skilU
* @Description: TODO(英雄技能)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public class skilU extends MapleStoryObject {
	/**
	 * 图片类型数组
	 */
	public static Image[] images = new Image[100];
	/**
	 * 静态代码块加载
	 */
	static {
		for (int i = 0; i < 13; i++) {
			images[i] = imageUtil.getimage("water_column" + (i + 1));
		}
	}
	 /**
     * 构造方法
     */
	public skilU() {
		// TODO Auto-generated constructor stub
	}
	 /**
     * 
     * @param msc MapleStoryClient参数
     * @param x x坐标
     * @param y y坐标
     * @param dir 方向
     */
	public skilU(MapleStoryClient msc, int x, int y, Direction dir) {
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.img = imageUtil.getimage("bigbullet5");
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 10;

	}

	@Override
	public void move() {

	}
    /**
     * 图片数组下标
     */
	public int bigbullet = 0;

	@Override
	public void draw(Graphics g) {
		if (bigbullet > 12) {
			bigbullet = 0;
		}
		g.drawImage(images[bigbullet++], x, y, null);
		move(); 
	}
    /**
     * 随机数
     */
	public static Random random = new Random();
	/**
	 * 
	* @Title: hit
	* @Description: TODO(英雄技能与怪物碰撞)
	* @param @param mob 怪物
	* @param @param hero 英雄
	* @param @return    参数 false/true
	* @return boolean    返回类型
	* @throws
	 */
	public boolean hit(Mob mob,Hero hero) {
		
		// 子弹存在，怪物存活的情况下进行碰撞检测
		if (this.live && mob.live && mob.action != Action.DEAD && this.getRectangle().intersects(mob.getRectangle())) {
			this.live = false;
			if (bigbullet == 12) {
				msc.skilUs.remove(this);
			}
			if (mob.defensive>hero.aggressivity*4) {
				mob.HP-=1;
			}else {
				mob.HP -=hero.aggressivity*4-mob.defensive;
			}
			hero.EXP+=1;
			if (hero.EXP%100==0) {
				hero.level+=1;
				hero.aggressivity+=100;
				hero.defensive+=200;
				hero.HP+=1000;
			}
			if (mob.HP <= 0) {
				mob.action = Action.DEAD;

				if (random.nextInt(100) > 0) {
					Items items = new Items(msc, mob.x + mob.width / 2, mob.y + mob.height - 30, random.nextInt(5));
					msc.items.add(items);
				}
				hero.level+=1;
				hero.aggressivity+=100;
				hero.defensive+=100;
				hero.HP+=1000;
			}

			return true;
		}
		if (bigbullet == 12) {
			msc.skilUs.remove(this);
			
		}
		return false;

	}
	/**
	 * 
	* @Title: hit
	* @Description: TODO(英雄技能与怪物数组碰撞)
	* @param @param mobs 怪物数组
	* @param @param hero 英雄
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean hit(List<Mob> mobs,Hero hero) {
		// 当怪物不存在时，还可以放技能
		if (mobs.size()==0) {
			if (bigbullet == 12) {
				msc.skilUs.remove(this);
				
			}
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
