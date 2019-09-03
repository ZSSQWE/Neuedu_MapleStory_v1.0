package com.neuedu.maplestory.emtity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.MusicUtil;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: Boss
* @Description: TODO(boss类)
* @author 张盛爽
* @date 2019年8月25日
*
 */
public class Boss extends Mob {
	/**
	 * 图片类型数组
	 */
	public static Image[] images = new Image[400];
	/**
	 * 静态代码块加载图片
	 */
	static {

		for (int i = 0; i < 6; i++) {
			images[i] = imageUtil.getimage("boss_stand_l" + i);
		}

		for (int i = 6; i < 12; i++) {
			images[i] = imageUtil.getimage("boss_stand_r" + (i - 6));
		}
		// walk
		for (int i = 12; i < 18; i++) {
			images[i] = imageUtil.getimage("boss_walk_l" + (i - 12));
		}
		for (int i = 18; i < 24; i++) {
			images[i] = imageUtil.getimage("boss_walk_r" + (i - 18));
		}
		// die
		for (int i = 24; i < 41; i++) {
			images[i] = imageUtil.getimage("boss_die_l" + (i - 24));
		}
		for (int i = 41; i < 58; i++) {
			images[i] = imageUtil.getimage("boss_die_r" + (i - 41));
		}

	}
	/**
	 * 定义攻击，走，站，技能1，技能2，技能3，技能4开关
	 */
	public boolean attack, walk, stand, skill, skill1, skill2, skill3;
	/**
	 * 定义攻击类型
	 */
	public int attackType;
	/**
	 * 定义技能类型
	 */
	public int skillType;
    /**
     * 构造方法
     */
	public Boss() {

	}
    /**
     * 中介者模式，引用英雄类变量
     */
	Hero hero;
    /**
     * 带参构造
     * @param msc MapleStoryClient参数
     * @param x x坐标
     * @param y y坐标
     * @param hero Hero参数
     */
	public Boss(MapleStoryClient msc, int x, int y, Hero hero) {
		this.name = "boss";
		this.msc = msc;
		this.x = x;
		this.y = y;
		this.width = images[0].getWidth(null);
		this.height = images[0].getHeight(null);
		this.HP = 50000;
		this.HP_FULL = this.HP;
		this.speed = 5;
		this.action = Action.STAND;
		this.dir = Direction.LEFT;
		this.defensive = 1000;
		this.hero = hero;
	}
  /**
   * 重写move方法
   */
	@Override
	public void move() {

		switch (dir) {
		case LEFT:
			switch (action) {
			case WALK:
				x -= speed;
				break;
			case DOWN:
				
				if (y <= 0 + height) {
					y += speed;
				} 
				down = false;
				break;
			case UP:
				if (y >= 530 - height) {
					y-= speed;
				} 
				up = false;
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
			case DOWN:

				if (y <= height) {
					y += speed;
				} 
				down = false;
				break;
			case UP:
				if (y >= 530 - height) {
					y -= speed;
				} 
				up = false;
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
    * @Description: TODO(边界判断)
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
     * 左站图片在数组的下标
     */
	private int boss_left_stand = 0;// [0,5]
	/**
     * 右站图片在数组的下标
     */
	private int boss_right_stand = 6;// [6,11]
	/**
     * 左走图片在数组的下标
     */
	private int boss_left_walk = 12;// [12,17]
	/**
     * 右走图片在数组的下标
     */
	private int boss_right_walk = 18;// [18,23]
	/**
     * 左死图片在数组的下标
     */
	private int boss_left_die = 24;// [24,40]
	/**
     * 右死图片在数组的下标
     */
	private int boss_right_die = 41;// [41,57]
    /**
     * 满血
     */
	private double HP_FULL; // 满血状态
    /**
     * 重写画方法
     */
	@Override
	public void draw(Graphics g) {

		if (boss_left_stand > 5) {
			boss_left_stand = 0;
		}
		if (boss_right_stand > 11) {
			boss_right_stand = 6;
		}
		if (boss_left_walk > 17) {
			boss_left_walk = 12;
		}
		if (boss_right_walk > 23) {
			boss_right_walk = 18;
		}
		if (boss_left_die > 40) {
			boss_left_die = 24;
			this.live = false;
			msc.mobs.remove(this);
		}

		if (boss_right_die > 57) {
			boss_right_die = 41;
			this.live = false;
			msc.mobs.remove(this);

		}

		switch (dir) {
		case LEFT:
			switch (action) {
			case STAND:
				g.drawImage(images[boss_left_stand++], x, y, null);
				break;
			case WALK:
				g.drawImage(images[boss_left_walk++], x, y, null);
				break;
			case DEAD:
				if (live) {
					g.drawImage(images[boss_left_die++], x, y, null);
				}
				break;
			default:
				break;
			}

			break;
		case RIGHT:
			switch (action) {
			case STAND:
				g.drawImage(images[boss_right_stand++], x, y, null);
				break;
			case WALK:
				g.drawImage(images[boss_right_walk++], x, y, null);
				break;
			case DEAD:
				if (live) {
					g.drawImage(images[boss_right_die++], x, y, null);
				}
				break;
			default:
				break;
			}
			break;

		default:
			break;
		}
		count++;
		if (attack) {
			int attackType = random.nextInt(2);
			if (attackType == 0) {
				attack();
			} else {
				attack1();
			}

		}
		if (skill) {

			skill1();

		}
		if (skill1) {

			skill2();

		}
		if (skill2) {

			skill3();

		}
		if (skill3) {

			skill4();

		}
		if (jump) {
			bossjump();
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
     * 定义计时器
     */
	int count = 0;
	/**
	 * 定义随机数变量
	 */
	public static Random random = new Random();
   /**
    * 
   * @Title: changeimage
   * @Description: TODO(切换图片的方法)
   * @param     参数
   * @return void    返回类型
   * @throws
    */
	private void changeimage() {
		int nextInt = random.nextInt(1000);
		if (nextInt > 500 && nextInt <= 700) {
			this.dir = Direction.RIGHT;
			walk = true;
			attack = true;

		} else if (nextInt > 200 && nextInt <= 300) {
			this.dir = Direction.LEFT;
			up = true;
			skill = true;
		} else if (nextInt > 800 && nextInt <= 950) {
			this.dir = Direction.LEFT;
			up = true;
			skill1 = true;
		} else if (nextInt > 100 && nextInt <= 200) {
			this.dir = Direction.RIGHT;
			down = true;
			skill2 = true;
		} else if (nextInt > 400 && nextInt <= 500) {
			walk = true;
			skill2 = true;
		} else if (nextInt < 100) {
			skill3 = true;
			up = true;
		} else {
			this.dir = Direction.RIGHT;
			jump = true;
		}
		if (walk) {
			this.action = Action.WALK;

			if (attack) {
				this.action = Action.ATTACK;
			}
		} else if (attack) {

			this.action = Action.ATTACK;
		} else if (up) {

			this.action = Action.UP;
		} else if (down) {

			this.action = Action.DOWN;
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
    * @Description: TODO(画boss信息的方法)
    * @param @param g    参数 画笔
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
    * @Description: TODO(画血条方法)
    * @param @param g    参数 画笔
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
    * @Description: TODO(boss攻击方法)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	public void attack() {
		new MusicUtil("com/neuedu/maplestory/sounds/boss火.mp3").start();
		BossActtack1 attack = null;
		switch (dir) {

		case LEFT:
			attack = new BossActtack1(msc, hero.x, hero.y, this.dir);
			break;
		case RIGHT:
			attack = new BossActtack1(msc, hero.x, hero.y, this.dir);
			break;

		default:
			break;
		}
		msc.bossActtack1s.add(attack);

		this.attack = false;
	}
	/**
     * 
    * @Title: attack
    * @Description: TODO(boss另一种攻击方法)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	public void attack1() {
		BossActtack2 attack = null;
		switch (dir) {

		case LEFT:
			attack = new BossActtack2(msc, hero.x, hero.y, this.dir);
			break;
		case RIGHT:
			attack = new BossActtack2(msc, hero.x, hero.y, this.dir);
			break;

		default:
			break;
		}
		msc.bossActtack2s.add(attack);
		this.attack = false;
	}
    /**
     * 
    * @Title: skill1
    * @Description: TODO(boss技能1方法)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	public void skill1() {
		new MusicUtil("com/neuedu/maplestory/sounds/boss冲击.mp3").start();
		BossSkill1 skill = null;
		switch (dir) {

		case LEFT:
			skill = new BossSkill1(msc, hero.x, hero.y, this.dir);
			break;
		case RIGHT:
			skill = new BossSkill1(msc, hero.x, hero.y, this.dir);
			break;

		default:
			break;
		}
		msc.sBossSkill1s.add(skill);
		this.skill = false;
	}
   /**
    * 
   * @Title: skill2
   * @Description: TODO(boss技能2方法)
   * @param     参数
   * @return void    返回类型
   * @throws
    */
	public void skill2() {
		new MusicUtil("com/neuedu/maplestory/sounds/boss冲击.mp3").start();
		BossSkill2 skill = null;
		switch (dir) {

		case LEFT:
			skill = new BossSkill2(msc, hero.x, hero.y, this.dir);
			break;
		case RIGHT:
			skill = new BossSkill2(msc, hero.x, hero.y, this.dir);
			break;

		default:
			break;
		}
		msc.sBossSkill2s.add(skill);
		this.skill1 = false;
	}
    /**
     * 
    * @Title: skill3
    * @Description: TODO(boss技能3方法)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	public void skill3() {
		new MusicUtil("com/neuedu/maplestory/sounds/boss回血.mp3").start();
		BossSkill3 skill = null;
		switch (dir) {

		case LEFT:
			skill = new BossSkill3(msc, x, y, this.dir);
			break;
		case RIGHT:
			skill = new BossSkill3(msc, x, y, this.dir);
			break;

		default:
			break;
		}
		msc.sBossSkill3s.add(skill);
		if (HP < 50000) {
			HP += HP_FULL * 0.1;
		} else {
			HP = 50000;
		}

		this.skill2 = false;
	}
    /**
     * 
    * @Title: skill4
    * @Description: TODO(boss技能4方法)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	public void skill4() {
		BossSkill4 skill = null;
		switch (dir) {

		case LEFT:
			skill = new BossSkill4(msc, x, y, this.dir);
			break;
		case RIGHT:
			skill = new BossSkill4(msc, x, y, this.dir);
			break;

		default:
			break;
		}
		msc.sBossSkill4s.add(skill);
		defensive += 100;
		this.skill3 = false;
	}
    /**
     * 定义初速度
     */
	private double v0 = Constant.HERO_JUMPSPEED;
	/**
	 * 定义末速度
	 */
	private double vt = 0;
	/**
	 * 重力加速度
	 */
	private double g = 9.8;
	/**
	 * 单位时间y的变化量
	 */
	private double delta_h = 0;
	/**
	 * 单位时间
	 */
	private double t = 1;
	/**
	 * 向上跳的状态
	 */
	private boolean jump_up = true;
	/**
	 * 跳，向上，向下状态
	 */
	private boolean jump, up, down;
    /**
     * 
    * @Title: bossjump
    * @Description: TODO(noss跳方法)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	private void bossjump() {
		if (jump_up) {
			// 向上
			vt = v0 - g * t;
			v0 = vt;
			delta_h = v0 * t;
			y -= delta_h;

			if (vt <= 0) {
				vt = 0;

				jump_up = false;// 进入自由落体

			}

		} else {
			// 向下
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			if (y >= 530) {
				y = 530;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
				this.action = Action.STAND;
			}
		}

	}

}
