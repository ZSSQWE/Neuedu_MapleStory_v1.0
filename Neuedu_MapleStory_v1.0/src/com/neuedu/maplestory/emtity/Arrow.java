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
 * @ClassName: Arrow
 * @Description: TODO(英雄子弹类)
 * @author 张盛爽
 * @date 2019年8月25日
 *
 */
public class Arrow extends MapleStoryObject {
	/**
	 * 构造方法
	 */
	public Arrow() {

	}

	/**
	 * 带参构造方法
	 * 
	 * @param msc MapleStoryClient对象
	 * @param x   x坐标
	 * @param y   y坐标
	 * @param dir 方向
	 */
	public Arrow(MapleStoryClient msc, int x, int y, Direction dir) {
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.img = imageUtil.getimage("bullet_r");
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 10;
		this.dir = dir;
		// 根据方向换子弹图片
		if (dir == Direction.LEFT) {
			this.img = imageUtil.getimage("bullet_l");
		} else {
			this.img = imageUtil.getimage("bullet_r");
		}
	}

	/**
	 * 重写移动方法
	 */
	@Override
	public void move() {
		if (dir == Direction.LEFT) {
			// x -= speed;
			x -= (int) (5 * 10);
			y -= (int) (Math.sin(10 * 360));
		} else {
			x += (int) (5 * 10);
			y += (int) (Math.sin(10 * 360));
			// x += speed;
		}

		// 边界判断
		outofBounds();
	}
   /**
    * 重写画方法
    */
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();

	}
   /**
    * 
   * @Title: outofBounds
   * @Description: TODO(边界判断。超出边界范围的东西都要移出掉)
   * @param     参数 无
   * @return void    返回类型 无
   * @throws 无
    */
	public void outofBounds() {
		if (this.x > Constant.GAME_WIDTH + 100 || this.x < -200) {
			msc.arrows.remove(this);
		}
	}
   /**
    * 定义随机数变量
    */
	public static Random random = new Random();

	/**
	 * 
	* @Title: hit
	* @Description: TODO(检测子弹与怪物碰撞)
	* @param @param mob 怪物
	* @param @param hero 英雄
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean hit(Mob mob, Hero hero) {
		// 子弹存在，怪物存活的情况下进行碰撞检测
		if (this.live && mob.live && mob.action != Action.DEAD && this.getRectangle().intersects(mob.getRectangle())) {
			this.live = false;
			msc.arrows.remove(this);
			if (mob.defensive > hero.aggressivity) {
				mob.HP -= 1;
			} else {
				mob.HP -= hero.aggressivity - mob.defensive;
			}
			hero.EXP += 1;
			if (hero.EXP % 100 == 0) {
				hero.level += 1;
				hero.aggressivity += 100;
				hero.defensive += 200;
				hero.HP += 1000;
			}
			if (mob.HP <= 0) {
				mob.action = Action.DEAD;

				if (random.nextInt(100) > 0) {
					Items items = new Items(msc, mob.x + mob.width / 2, mob.y + mob.height - 30, random.nextInt(10));
					msc.items.add(items);
				}
				hero.level += 1;
				hero.aggressivity += 100;
				hero.defensive += 200;
				hero.HP += 1000;

			}

			return true;
		}

		return false;

	}
    /**
     * 
    * @Title: hit
    * @Description: TODO(检测子弹与怪物数组里的怪物碰撞)
    * @param @param mobs 怪物数组
    * @param @param hero 英雄
    * @param @return    参数
    * @return boolean    返回类型
    * @throws 无
     */
	public boolean hit(List<Mob> mobs, Hero hero) {
		for (int i = 0; i < mobs.size(); i++) {
			Mob mob = mobs.get(i);
			if (this.hit(mob, hero)) {
				return true;
			}
		}

		return false;

	}

}
