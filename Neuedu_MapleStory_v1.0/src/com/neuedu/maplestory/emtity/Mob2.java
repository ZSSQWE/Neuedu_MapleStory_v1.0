
package com.neuedu.maplestory.emtity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: Mob2
* @Description: TODO(怪物2)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public class Mob2 extends Mob {
	/**
	 * 图片类型数组
	 */
	public static Image[] images = new Image[200];
	/**
	 * 静态代码块加载图片
	 */
	static {

		for (int i = 0; i < 6; i++) {
			images[i] = imageUtil.getimage("mob3_stand_l" + i);
		}
		
		for (int i = 6; i < 12; i++) {
			images[i] = imageUtil.getimage("mob3_stand_r" + (i - 6));
		}
		// right walk
		for (int i = 12; i < 18; i++) {
			images[i] = imageUtil.getimage("mob3_walk_l" + (i - 12));
		}
		// right stand
		for (int i = 18; i < 24; i++) {
			images[i] = imageUtil.getimage("mob3_walk_r" + (i - 18));
		}
		// left die
		for (int i = 24; i < 39; i++) {
			images[i] = imageUtil.getimage("mob3_attack_l" + (i - 24));
		}
		// right die
		for (int i = 39; i < 54; i++) {
			images[i] = imageUtil.getimage("mob3_attack_r" + (i - 39));
		}

		// 左攻击
		for (int i = 54; i < 69; i++) {
			images[i] = imageUtil.getimage("mob3_die_l" + (i - 54));
		}
		// 右攻击
		for (int i = 69; i < 84; i++) {
			images[i] = imageUtil.getimage("mob3_die_r" + (i - 69));
		}
	}
	/**
	 * 设置攻击，走，站状态
	 */
	public boolean attack, walk, stand;
	 /**
     * 构造函数
     */
	public Mob2() {

	}
	/**
	 * 调停者模式
	 */
     Hero hero;
     /**
      * 
      * @param msc MapleStoryClient参数
      * @param x x坐标
      * @param y y坐标
      * @param hero 英雄
      */
	public Mob2(MapleStoryClient msc, int x, int y,Hero hero) {
		this.name = "变节首领";
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.width = images[0].getWidth(null);
		this.height = images[0].getHeight(null);
		this.HP = 2000;
		this.HP_FULL = this.HP;
		this.speed = 5;
		this.action = Action.STAND;
		this.dir = Direction.LEFT; 
		this.hero=hero;
	}

	@Override
	public void move() {

		switch (dir) {
		case LEFT:
			switch (action) {
			case WALK:
				x -= speed;
				break;
			case STAND:

				break;
			default:
				break;
			}
			break;
		case RIGHT:
			switch (action) {
			case WALK:
				x += speed;
				break;
			case STAND:

				break;
			default:
				break;
			}
			break;

		default:
			break;
		}
		outofwindow();
	}
	 /**
	    * 
	   * @Title: outofwindow
	   * @Description: TODO(移除超出界限的东西)
	   * @param     参数
	   * @return void    返回类型
	   * @throws
	    */
	private void outofwindow() {
		if (x <= 0) {
			dir = Direction.RIGHT;
		}

		if (x > Constant.GAME_WIDTH - width) {
			dir = Direction.LEFT;
		}

	}
	  /**
     * 左站图片下标
     */
	private int mob2_left_stand = 0;// [0,5]
	 /**
     * 右站图片下标
     */
	private int mob2_right_stand = 6;// [6,11]
	 /**
     * 左走图片下标
     */
	private int mob2_left_walk = 12;// [12,17]	
	 /**
     * 右走图片下标
     */
	private int mob2_right_walk = 18;// [18,23]
	 /**
     * 左攻击图片下标
     */
	private int mob2_left_attack = 24;// [24,38]
	/**
	 * 右攻击
	 */
	private int mob2_right_attack = 39;// [39,53]
	/**
     * 左死图片下标
     */
	private int mob2_left_die = 54;// [54,68]
	 /**
     * 右死图片下标
     */
	private int mob2_right_die = 69;// [69,84]
	/**
	 * 满血
	 */
	private double HP_FULL; // 满血状态

	@Override 
	public void draw(Graphics g) {

		if (mob2_left_stand > 5) {
			mob2_left_stand = 0;
		}
		if (mob2_right_stand > 11) {
			mob2_right_stand = 6;
		}
		if (mob2_left_walk > 17) {
			mob2_left_walk = 12;
		}
		if (mob2_right_walk > 23) {
			mob2_right_walk = 18;
		}
		if (mob2_left_attack > 38) {
			mob2_left_attack = 24;
			this.attack = false;
		}
		if (mob2_right_attack > 53) {
			mob2_right_attack = 39;
			this.attack = false;

		}
		if (mob2_left_die > 68) {
			mob2_left_die = 54;
			this.live = false;
			msc.mobs.remove(this);

		}
		
		if (mob2_right_die > 84) {
			mob2_right_die = 69;
			this.live = false;
			msc.mobs.remove(this);

		}
	
		switch (dir) {
		case LEFT:
			switch (action) {
			case STAND:

				g.drawImage(images[mob2_left_stand++], x, y, null);
				break;
			case WALK:
				g.drawImage(images[mob2_left_walk++], x, y, null);

				break;
			case DEAD:
				if (live) {
					g.drawImage(images[mob2_left_die++], x, y, null);
				}
				break;
			case ATTACK:
				g.drawImage(images[mob2_left_attack++], x, y-120, null);
				break;

			default:
				break;
			}
			break;
		case RIGHT:
			switch (action) {
			case STAND:

				g.drawImage(images[mob2_right_stand++], x, y, null);
				break;
			case WALK:
				g.drawImage(images[mob2_right_walk++], x, y, null);
				break;
			case DEAD:
				if (live) {
					g.drawImage(images[mob2_right_die++], x, y, null);
				}
				break;
			case ATTACK:				
			    g.drawImage(images[mob2_right_attack++], x, y-120, null);
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
		if (attack) {
			attack();
		}
		move();

		drawBlooBar(g);
		drawinfo(g);

		count++;
		if (count > 30) {
			count = 0;
			changeimage();
		}

	}
   /**
    * 计时器
    */
	int count = 0;
   /**
    * 
   * @Title: changeimage
   * @Description: TODO(图片变换)
   * @param     参数
   * @return void    返回类型
   * @throws
    */
	private void changeimage() {
		int nextInt = random.nextInt(100);
		if (nextInt > 10 && nextInt <=20) {
			this.dir = Direction.RIGHT;
			walk = true;
		}else if (nextInt > 20 && nextInt <=35) {
			this.dir = Direction.LEFT;
			stand=true;
			attack = true;		
		}else if (nextInt > 35 && nextInt <=50) {
			this.dir = Direction.LEFT;
			walk=true;
		}else if (nextInt > 50 && nextInt <=70) {
			this.dir = Direction.RIGHT;
			walk=true;
			attack = true;
		}else {
			this.dir = Direction.RIGHT;
			stand=true;
		}
		if (walk) {
			this.action = Action.WALK;

			if (attack) {
				this.action = Action.ATTACK;
			}
		} else if (attack) {

			this.action = Action.ATTACK;
		} else {
			this.action = Action.STAND;
		}
//		if (stand) {
//			this.action = Action.STAND;
//		}

	}
    /**
     * 
    * @Title: drawinfo
    * @Description: TODO(画怪物信息)
    * @param @param g    参数
    * @return void    返回类型
    * @throws
     */
	private void drawinfo(Graphics g) {
		int x = this.x + 10;
		int y = this.y + this.height + 20;
		Color color = g.getColor();
		Font font = g.getFont();
		g.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));
		g.fillRect(x, y - 10, width + 10, 15);
		g.setColor(Color.WHITE);
		g.drawString("LV." + level + " " + name, x, y);
		g.setColor(color);

	}
    /**
     * 
    * @Title: drawBlooBar
    * @Description: TODO(画怪物血条)
    * @param @param g    参数
    * @return void    返回类型
    * @throws
     */
	private void drawBlooBar(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.CYAN);
		// 画矩形
		g.drawRect(x, y - 18, width, height / 10);
		// 计算不同生命值血条的宽度
		double width = HP / HP_FULL * this.width;
		if (HP / HP_FULL <= 0.7) {
			g.setColor(Color.YELLOW);
		}
		if (HP / HP_FULL <= 0.2) {
			g.setColor(Color.RED);
		}
		// 填充
		g.fillRect(x, y - 18, (int) width, height / 10);
		g.setColor(color);
	}
   /**
    * 
   * @Title: attack
   * @Description: TODO(攻击方法)
   * @param     参数
   * @return void    返回类型
   * @throws
    */
	public void attack() {
		Mob2Attack attack = null;
		switch (dir) {

		case LEFT:
			attack = new Mob2Attack(msc, new Random().nextInt(hero.x)+50, hero.y-this.height, this.dir);
			break;
		case RIGHT:
			attack = new Mob2Attack(msc, new Random().nextInt(hero.x)+50, hero.y-this.height , this.dir);
			break;

		default:
			break;
		}
		msc.aMob2Attacks.add(attack);
	}
   /**
    * 随即数变量
    */
	public static Random random = new Random();

}
