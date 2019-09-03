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
* @ClassName: Mob1
* @Description: TODO(怪物1)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public class Mob1 extends Mob {
	/**
	 * 图片类型数组
	 */
	public static Image[] imgs = new Image[100];
	/**
	 * 静态代码块加载图片
	 */
	static {
		// leftstand
		for (int i = 0; i < 4; i++) {
			imgs[i] = imageUtil.getimage("mob2_left_stand" + i);
		}
		// left walk
		for (int i = 4; i < 8; i++) {
			imgs[i] = imageUtil.getimage("mob2_left_walk" + (i - 4));
		}
		// right walk
		for (int i = 8; i < 13; i++) {
			imgs[i] = imageUtil.getimage("mob2_right_walk" + (i - 8));
		}
		// right stand
		for (int i = 13; i < 18; i++) {
			imgs[i] = imageUtil.getimage("mob2_right_stand" + (i - 13));
		}
		// 左攻击
		for (int i = 18; i < 22; i++) {
			imgs[i] = imageUtil.getimage("mob2_left_attack" + (i - 18));
		}
		// 右攻击
		for (int i = 23; i < 27; i++) {
			imgs[i] = imageUtil.getimage("mob2_right_attack" + (i - 23));
		}
		// left die
		for (int i = 28; i < 31; i++) {
			imgs[i] = imageUtil.getimage("mob2_left_die" + (i - 28));
		}
		// right die
		for (int i = 31; i < 34; i++) {
			imgs[i] = imageUtil.getimage("mob2_right_die" + (i - 31));
		}

	}
	/**
	 * 设置攻击，走，站状态
	 */
	public boolean attack, walk, stand;
    /**
     * 构造函数
     */
	public Mob1() {

	}
	/**
	 * 
	 * @param msc MapleStoryClient参数
	 * @param x x坐标
	 * @param y y坐标
	 */
	public Mob1(MapleStoryClient msc, int x, int y) {
		this.name = "魔法弓箭手";
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.width = imgs[0].getWidth(null);
		this.height = imgs[0].getHeight(null);
		this.HP = 1000;
		this.HP_FULL = this.HP;
		this.speed = 5;
		this.action = Action.STAND;
		this.dir = Direction.LEFT;
		this.aggressivity=100;
		this.defensive=100;
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
	private int mob2_left_stand = 0;// [0,3]
	 /**
     * 右站图片下标
     */
	private int mob2_right_stand = 4;// [4,7]
	 /**
     * 左走图片下标
     */
	private int mob2_left_walk = 8;// [8,12]
	 /**
     * 右走图片下标
     */
	private int mob2_right_walk = 13;// [13,17]
	 /**
     * 左死图片下标
     */
	private int mob2_left_die = 23;// [23,25]
	 /**
     * 右死图片下标
     */
	private int mob2_right_die = 26;// [26,28]
	 /**
     * 左攻击图片下标
     */
	private int mob2_left_attack = 18;// [18,21]
	/**
	 * 右攻击
	 */
	private int mob2_right_attack = 22;// [22,25]
	/**
	 * 满血
	 */
	private double HP_FULL; // 满血状态

	@Override
	public void draw(Graphics g) {

		if (mob2_left_stand > 3) {
			mob2_left_stand = 0;
		}
		if (mob2_right_stand > 7) {
			mob2_right_stand = 4;
		}
		if (mob2_left_walk > 12) {
			mob2_left_walk = 8;
		}

		if (mob2_right_walk > 17) {
			mob2_right_walk = 13;
		}
		if (mob2_left_attack > 21) {
			mob2_left_attack = 18;

		}
		if (mob2_right_attack > 25) {
			mob2_right_attack = 22;

		}
		if (mob2_left_die > 25) {
			mob2_left_die = 23;
			this.live = false;
			msc.mobs.remove(this);

		}
		if (mob2_right_die > 28) {
			mob2_right_die = 26;
			this.live = false;
			msc.mobs.remove(this);

		}

		switch (dir) {
		case LEFT:
			switch (action) {
			case STAND:

				g.drawImage(imgs[mob2_left_stand++], x, y, null);
				break;
			case WALK:
				g.drawImage(imgs[mob2_left_walk++], x, y, null);

				break;
			case DEAD:
				if (live) {
					g.drawImage(imgs[mob2_left_die++], x, y, null);
				}
				break;
			case ATTACK:
				g.drawImage(imgs[mob2_left_attack++], x, y, null);
				break;

			default:
				break;
			}
			break;
		case RIGHT:
			switch (action) {
			case STAND:

				g.drawImage(imgs[mob2_right_stand++], x, y, null);
				break;
			case WALK:
				g.drawImage(imgs[mob2_right_walk++], x, y, null);
				break;
			case DEAD:
				if (live) {
					g.drawImage(imgs[mob2_right_die++], x, y, null);
				}
				break;
			case ATTACK:
				g.drawImage(imgs[mob2_right_attack++], x, y, null);
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
		count++;
		count1++;
		if (attack&&count1>30) {
			count1=0;
			attack();
		}
		move();

		drawBlooBar(g);
		drawinfo(g);

		
		if (count > 30) {
			count = 0;
			changeimage();
		}

	}
    /**
     * 计数器
     */
    public	int count = 0;
    /**
     * 计数器1
     */
    public	int count1 = 0;
    /**
     * 
    * @Title: changeimage
    * @Description: TODO(图片切换)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	private void changeimage() {
		int nextInt = random.nextInt(100);
		if (nextInt > 10 && nextInt <= 20) {
			this.dir = Direction.RIGHT;
			walk = true;
			attack = true;
		} else if (nextInt > 20 && nextInt <= 35) {
			this.dir = Direction.LEFT;
			stand = true;
			attack = true;
		} else if (nextInt > 35 && nextInt <= 50) {
			this.dir = Direction.LEFT;
			walk = true;
			attack = false;
		} else if (nextInt > 50 && nextInt <= 70) {
			this.dir = Direction.RIGHT;
			walk = true;
			attack = true;
		} else {
			this.dir = Direction.RIGHT;
			stand = true;
			attack = true;
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
    * @Description: TODO(怪物信息)
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
    * @Description: TODO(怪物血条)
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
		Mob1Attack attack = null;
		switch (dir) {

		case LEFT:
			attack = new Mob1Attack(msc, this.x-63, this.y + height / 2, this.dir);
			break;
		case RIGHT:
			attack = new Mob1Attack(msc, this.x+width, this.y + height / 2, this.dir);
			break;

		default:
			break;
		}
		msc.attacks2.add(attack);
		
		
	}
   /**
    * 随机数变量
    */
	public static Random random = new Random();

}
