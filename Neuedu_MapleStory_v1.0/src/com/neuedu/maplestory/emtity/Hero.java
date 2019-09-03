package com.neuedu.maplestory.emtity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.MusicUtil;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: Hero
* @Description: TODO(英雄类)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public class Hero extends MapleStoryObject {
	/**
	 * 图片类型数组
	 */
	public static Image[] images = new Image[100];
	/**
	 * 静态代码块加载图片
	 */
	static {
		// 右走
		for (int i = 0; i < 5; i++) {
			images[i] = imageUtil.getimage("hero_right_walk" + i);
		}
		// 左走
		for (int i = 5; i < 10; i++) {
			images[i] = imageUtil.getimage("hero_left_walk" + (i - 5));
		}
		// 右射击
		for (int i = 10; i < 14; i++) {
			images[i] = imageUtil.getimage("hero_right_shoot" + (i - 10));
		}
		// 左射击
		for (int i = 14; i < 18; i++) {
			images[i] = imageUtil.getimage("hero_left_shoot" + (i - 14));
		}
		// 右站
		for (int i = 18; i < 22; i++) {
			images[i] = imageUtil.getimage("hero_right_stand" + (i - 18));
		}
		// 左站
		for (int i = 22; i < 26; i++) {
			images[i] = imageUtil.getimage("hero_left_stand" + (i - 22));
		}
		// 左近战攻击
		for (int i = 26; i < 34; i++) {
			images[i] = imageUtil.getimage("attack_l" + (i - 26));
		}
		// 右近战攻击
		for (int i = 34; i < 42; i++) {
			images[i] = imageUtil.getimage("attack_r" + (i - 34));
		}
	}

	/**
	 * 人物上下左右跳
	 */
	public boolean up, down, left, right, walk, jump, shoot, stand, skilQ, skilU, skilI, pick, attack;
     public int EXP;
	/**
	 * 
	 * @param x     x坐标
	 * @param y     y坐标
	 * @param image 图片对象
	 */
	public Hero(MapleStoryClient msc) {
		this.msc = msc;
		this.x = 100;
		this.y = 530;
		this.speed = Constant.SPEED;
		this.dir = Direction.RIGHT;
		this.img = imageUtil.getimage("hero_right_stand0");
		this.height = img.getHeight(null);
		this.width = img.getWidth(null);
		this.action = Action.STAND;
		this.MP = 15000;
		this.HP = 50000;
		this.HP_FULL = this.HP;
		this.MP_FULL = this.MP;
		this.aggressivity=100;
		this.level=1;
		this.defensive=100;
		this.EXP=0;

	}
	/**
	    * 构造方法
	    */
	public Hero() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 移动方法
	 */
	@Override
	public void move() {

//		if (up) {
//			y -= speed;
//		}
//		if (down) {
//			y += speed;
//		}
		if (walk) {
			if (left) {
				x -= speed;

			}
			if (right) {
				x += speed;
			}

		}

		outofwindow();
	}

	/**
	 * 
	 * @Title: outofwindow @Description: TODO(判断是否移出可见窗口) @param 参数 @return void
	 *         返回类型 @throws
	 */
	private void outofwindow() {
		if (x <= 0) {
			x = 0;
		}
		if (y <= 0) {
			y = 0;
		}
		if (x > Constant.GAME_WIDTH - width) {
			x = Constant.GAME_WIDTH - width;
		}
		if (y > Constant.GAME_HEIGHT - height) {
			y = Constant.GAME_HEIGHT - height;
		}
	}

    /**
     * 右走图片下标
     */
	public int walk_right_count = 0;// [0,4]
	 /**
     * 左走图片下标
     */
	public int walk_left_count = 5;// [5,9]
	 /**
     * 右射击图片下标
     */
	public int shoot_right_count = 10;// [10,13]
	 /**
     * 左射击图片下标
     */
	public int shoot_left_count = 14;// [14,17]
	 /**
     * 右站图片下标
     */
	public int stand_right_count = 18;// [18,21]
	 /**
     * 左站图片下标
     */
	public int stand_left_count = 22;// [22,25]
	/**
	 * 左近战攻击
	 */
	public int attack_right_count = 34;// [34,41]
	 /**
     * 右近战图片下标
     */
	public int attack_left_count = 26;// [26,33]
	/**
	 * 图片切换
	 */
	private int ratecount = 0;// 图片切换频率问题
    /**
     * 重写画方法
     */
	@Override
	public void draw(Graphics g) {
		ratecount++;
		// 走路图片进行循环
		if (walk_right_count > 4) {
			walk_right_count = 0;
		}
		if (walk_left_count > 9) {
			walk_left_count = 5;
		}
		// 射击图片进行循环
		if (shoot_right_count > 13) {
			shoot_right_count = 10;
		}
		if (shoot_left_count > 17) {
			shoot_left_count = 14;
		}
		// 站着图片进行循环
		if (stand_right_count > 21) {
			stand_right_count = 18;
		}
		if (stand_left_count > 25) {
			stand_left_count = 22;
		}
		// 共攻击图片进行循环
		if (attack_right_count > 41) {
			attack_right_count = 34;
		}
		if (attack_left_count > 33) {
			attack_left_count = 26;
		}
		// 判断方向
		switch (dir) {
		case LEFT:
			switch (action) {
			case STAND:
				if (ratecount >= 10) {
					ratecount = 0;
					g.drawImage(images[stand_left_count++], x, y, null);
				} else {
					g.drawImage(images[stand_left_count], x, y, null);
				}

				break;

			case WALK:
				g.drawImage(images[walk_left_count++], x, y, null);
				break;
			case JUMP:
				g.drawImage(imageUtil.getimage("hero_left_jump"), x, y, null);
				break;
			case SHOOT:
				g.drawImage(images[shoot_left_count++], x, y, null);
				break;
			case ATTACK:
				g.drawImage(images[attack_left_count++], x, y, null);
				break;

			default:
				break;
			}

			break;
		case RIGHT:
			switch (action) {
			case STAND:
				if (ratecount >= 10) {
					ratecount = 0;
					g.drawImage(images[stand_right_count++], x, y, null);
				} else {
					g.drawImage(images[stand_right_count], x, y, null);
				}

				break;
			case WALK:
				g.drawImage(images[walk_right_count++], x, y, null);
				break;
			case JUMP:
				g.drawImage(imageUtil.getimage("hero_right_jump"), x, y, null);
				break;
			case SHOOT:
				g.drawImage(images[shoot_right_count++], x, y, null);
				break;
			case ATTACK:
				g.drawImage(images[attack_right_count++], x, y, null);
				break;

			default:
				break;
			}

			break;

		default:
			break;
		}
		if (shoot) {
			shoot();
		}
		if (attack) {
			attack();
		}
		if (skilQ && MP >= 100) {
			MP -= 100;

			skilQ();
			new MusicUtil("com/neuedu/maplestory/sounds/电攻击.mp3").start();
		}
		if (skilU && MP >= 300) {
			MP -= 300;
			new MusicUtil("com/neuedu/maplestory/sounds/水系攻击.mp3").start();
			skilU();

		}
		if (skilI && MP >= 150) {
			MP -= 150;
			skilI();

		}
		// 判断方向
		checkdir();
		if (jump) {
			herojump();
		}
		if (walk) {
			herodown();
		}
		if (down) {
			down();
		}
		
		// floorhit();
		move();
		changeimage();
		drawBlooBar(g);
		drawinfo(g);
		drawMPBar(g);
	}

	/**
	 * 
	 * @Title: changeimage @Description: TODO(只进行图片的切换) @param 参数 @return void
	 *         为action设置各种状态 @throws
	 */
	private void changeimage() {
		if (walk) {
			this.action = Action.WALK;
			if (shoot) {
				this.action = Action.SHOOT;
			}
			if (attack) {
				this.action = Action.ATTACK;
			}
			if (jump) {
				this.action = Action.JUMP;
				if (shoot) {
					this.action = Action.SHOOT;
				}
				if (attack) {
					this.action = Action.ATTACK;
				}

			}

		} else if (shoot) {

			this.action = Action.SHOOT;
		} else if (attack) {

			this.action = Action.ATTACK;
		} else if (jump) {

			this.action = Action.JUMP;
			if (shoot) {
				this.action = Action.SHOOT;
			}
			if (attack) {
				this.action = Action.ATTACK;
			}
		} else {
			this.action = Action.STAND;
		}
		if (stand) {
			this.action = Action.STAND;
		}

	}

	/**
	 * 
	 * @Title: keyPressed @Description: TODO(当按键按下时触发上下左右移动) @param @param e
	 *         参数 @return void 返回类型 @throws
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			walk = true;
			stand = false;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			walk = true;
			stand = false;
			break;
		case KeyEvent.VK_SPACE:
			jump = true;
			stand = false;
			break;
		case KeyEvent.VK_J:
			shoot = true;
			stand = false;
			break;
		case KeyEvent.VK_K:
			skilQ = true;
			stand = false;
			break;
		case KeyEvent.VK_U:
			skilU = true;
			stand = false;
			break;
		case KeyEvent.VK_I:
			skilI = true;
			stand = false;
			break;
		case KeyEvent.VK_L:
			pick = true;
			stand = false;
			break;
		case KeyEvent.VK_H:
			attack = true;
			stand = false;
			break;
		default:
			break;
		}

	}

	/**
	 * 
	 * @Title: keyReleased @Description: TODO(当按键松开时触发停止上下左右的移动) @param @param e
	 *         参数 @return void 返回类型 @throws
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			walk = false;
			stand = true;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			walk = false;
			stand = true;
			break;
		case KeyEvent.VK_J:
			shoot = false;
			stand = true;
			break;
		case KeyEvent.VK_K:
			skilQ = false;
			stand = true;
			break;
		case KeyEvent.VK_U:
			skilU = false;
			stand = true;
			break;
		case KeyEvent.VK_I:
			skilI = false;
			stand = true;
			break;
		case KeyEvent.VK_L:
			pick = false;
			stand = true;
			break;
		case KeyEvent.VK_H:
			attack = false;
			stand = true;
			break;

		default:
			break;
		}

	}

	/**
	 * 
	 * @Title: checkdir @Description: TODO(确认人物的移动方向) @param 参数 @return void
	 *         返回类型 @throws
	 */
	private void checkdir() {
		if (left) {
			this.dir = Direction.LEFT;
		}
		if (right) {
			this.dir = Direction.RIGHT;
		}

	}

	/**
	 * v0初速度 vt末速度 g重力加速度 delta_h单位时间位移变化量 t单位时间 jump_up默认向上跳
	 */
	private double v0 = Constant.HERO_JUMPSPEED;
	private double vt = 0;
	private double g = 9.8;
	private double delta_h = 0;
	private double t = 1;
	private boolean jump_up = true;
	Rectangle r = new Rectangle(120, 490, imageUtil.getimage("floor4").getWidth(null),
			imageUtil.getimage("floor4").getHeight(null));
	Rectangle r1 = new Rectangle(300, 380, imageUtil.getimage("floor3").getWidth(null) + 70,
			imageUtil.getimage("floor3").getHeight(null));
	Rectangle r2 = new Rectangle(560, 270, imageUtil.getimage("floor3").getWidth(null),
			imageUtil.getimage("floor3").getHeight(null));

	Rectangle r3 = new Rectangle(600, 150, imageUtil.getimage("floor4").getWidth(null),
			imageUtil.getimage("floor4").getHeight(null));
	Rectangle r4 = new Rectangle(1000, 200, imageUtil.getimage("floor1").getWidth(null)+100,
			imageUtil.getimage("floor1").getHeight(null));
	Rectangle r5 = new Rectangle(750, 420, imageUtil.getimage("floor1").getWidth(null),
			imageUtil.getimage("floor3").getHeight(null));

	Rectangle r6 = new Rectangle(1400, 450, imageUtil.getimage("floor1").getWidth(null),
			imageUtil.getimage("floor4").getHeight(null));
	Rectangle r7 = new Rectangle(1150, 350, imageUtil.getimage("floor1").getWidth(null)+100,
			imageUtil.getimage("floor1").getHeight(null));
	Rectangle r8 = new Rectangle(1300, 280, imageUtil.getimage("floor1").getWidth(null),
			imageUtil.getimage("floor3").getHeight(null));

	/**
	 * 
	 * @Title: herojump @Description: TODO(英雄跳的算法) @param 参数 @return void
	 *         返回类型 @throws
	 */
	private void herojump() {
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
			if (floorhit(r)) {
				y = r.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else if (floorhit(r1)) {
				y = r1.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else if (floorhit(r2)) {
				y = r2.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else if (floorhit(r3)) {
				y = r3.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else if (floorhit(r4)) {
				y = r4.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else if (floorhit(r5)) {
				y = r5.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else if (floorhit(r6)) {
				y = r6.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else if (floorhit(r7)) {
				y = r7.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else if (floorhit(r8)) {
				y = r8.y - this.height / 2;
				v0 = Constant.HERO_JUMPSPEED;
				vt = 0;
				jump_up = true;
				jump = false;
				stand = true;
			} else {
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

//			

		}

	}
    /**
     * 
    * @Title: herodown
    * @Description: TODO(英雄向下落，是否与平台碰撞)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	private void herodown() {
		if ((x < r.x && y + height / 2 == r.y) || (x > r.x + r.width && y + height / 2 == r.y)) {// 第一块下落
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			System.out.println(y);
			down();
		} else if ((x < r1.x && y + height / 2 == r1.y) || (x > r1.x + r1.width && y + height / 2 == r1.y)) {// 第二块右边下落

			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			down();
		} else if ((x < r2.x && y + height / 2 == r2.y) || (x > r2.x + r2.width && y + height / 2 == r2.y)) {
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			down();
		} else if ((x < r3.x && y + height / 2 == r3.y) || (x > r3.x + r3.width && y + height / 2 == r3.y)) {
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			down();
		}else if ((x < r4.x && y + height / 2 == r4.y) || (x > r4.x + r4.width && y + height / 2 == r4.y)) {
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			down();
		}else if ((x < r5.x && y + height / 2 == r5.y) || (x > r5.x + r5.width && y + height / 2 == r5.y)) {
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			down();
		}else if ((x < r6.x && y + height / 2 == r6.y) || (x > r6.x + r6.width && y + height / 2 == r6.y)) {
			System.out.println(222);
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			down();
		}else if ((x < r7.x && y + height / 2 == r7.y) || (x > r7.x + r7.width && y + height / 2 == r7.y)) {
			System.out.println(111);
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			down();
		}else if ((x < r8.x && y + height / 2 == r8.y) || (x > r8.x + r8.width && y + height / 2 == r8.y)) {
			vt = v0 + g * t;
			v0 = vt;
			delta_h = v0 * t;
			y += delta_h;
			down();
		}
	}
   /**
    * 
   * @Title: shoot
   * @Description: TODO(射击方法)
   * @param     参数
   * @return void    返回类型
   * @throws
    */
	public void shoot() {
		new MusicUtil("com/neuedu/maplestory/sounds/攻击声音.mp3").start();
		Arrow arrow = null;
		switch (dir) {

		case LEFT:
			arrow = new Arrow(msc, this.x - 63, this.y + height / 2, this.dir);
			break;
		case RIGHT:
			arrow = new Arrow(msc, this.x + width, this.y + height / 2, this.dir);
			break;

		default:
			break;
		}
		msc.arrows.add(arrow);
	}
    /**
     * 
    * @Title: attack
    * @Description: TODO(近战攻击方法)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	public void attack() {
		new MusicUtil("com/neuedu/maplestory/sounds/近战.mp3").start();
		CloseAttack attack = null;
		switch (dir) {

		case LEFT:
			attack = new CloseAttack(msc, this.x - width, this.y - height / 2, this.dir);
			break;
		case RIGHT:
			attack = new CloseAttack(msc, this.x, this.y - height / 2, this.dir);
			break;

		default:
			break;
		}
		msc.attacks.add(attack);
	}
    /**
     * 
    * @Title: skilQ
    * @Description: TODO(英雄技能1)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	public void skilQ() {
		skilK sK = null;
		switch (dir) {

		case LEFT:
			sK = new skilK(msc, this.x - 50, this.y - 20, this.dir);
			break;
		case RIGHT:
			sK = new skilK(msc, this.x + 20, this.y - 20, this.dir);
			break;

		default:
			break;
		}

		msc.skilKs.add(sK);
	}
    /**
     * 
    * @Title: skilU
    * @Description: TODO(英雄技能2)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	private void skilU() {

		skilU sU = null;
		switch (dir) {

		case LEFT:
			sU = new skilU(msc, this.x - 400, this.y - 160, this.dir);
			break;
		case RIGHT:
		    sU = new skilU(msc, this.x + 400, this.y - 160, this.dir);
			break;

		default:
			break;
		}
		msc.skilUs.add(sU);

	}
    /**
     * 
    * @Title: skilI
    * @Description: TODO(英雄技能3)
    * @param     参数
    * @return void    返回类型
    * @throws
     */
	private void skilI() {
		SkillI skillI = null;
		switch (dir) {

		case LEFT:
			skillI = new SkillI(msc, this.x, 0, this.dir);
			break;
		case RIGHT:
			skillI = new SkillI(msc, this.x, 0, this.dir);
			break;

		default:
			break;
		}
		msc.skillIs.add(skillI);

	}
     /**
      * 
     * @Title: eatIteam
     * @Description: TODO(英雄与道具碰撞)
     * @param @param items
     * @param @return    参数
     * @return boolean    返回类型 true/false
     * @throws
      */
	public boolean eatIteam(Items items) {
		// 子弹存在，怪物存活的情况下进行碰撞检测
		if (items.live && this.getRectangle().intersects(items.getRectangle())) {
			if (pick) {
				msc.items.remove(items);
				switch (items.itemsType) {
				case 0:
					HP += 50;
					break;
				case 1:
					HP += 150;
					break;
				case 2:
					HP += 300;
					break;

				case 3:
					HP = (int) (HP + HP_FULL * 0.5);
					break;

				case 4:
					HP = HP_FULL;
					break;
				case 5:
					MP += 150;
					break;
				case 6:
					MP += 300;
					break;
				case 7:
					MP += 450;
					break;
				case 8:
					MP = (int) (MP + MP_FULL * 0.5);
					break;
				case 9:
					MP = MP_FULL;
					break;

				default:
					break;
				}
			}

			return true;
		}

		return false;

	}
    /**
     * 
    * @Title: eatIteam
    * @Description: TODO(英雄与道具类碰撞)
    * @param @param items
    * @param @return    参数
    * @return boolean    返回类型
    * @throws
     */
	public boolean eatIteam(List<Items> items) {
		for (int i = 0; i < items.size(); i++) {
			Items item = items.get(i);
			if (this.eatIteam(item)) {
				return true;
			}
		}

		return false;

	}
    /**
     * 
    * @Title: floorhit
    * @Description: TODO(跳跃时是否与平台碰撞)
    * @param @param r  矩形
    * @param @return    参数
    * @return boolean    返回类型 false/true
    * @throws
     */
	public boolean floorhit(Rectangle r) {

		if (this.getRectangle().intersects(r) && this.y + height / 2 < r.y) {
			this.y = r.y - this.height / 2;
			return true;
		}
		return false;

	}
   /**
    * 
   * @Title: down
   * @Description: TODO(向下跳的方法检测)
   * @param     参数
   * @return void    返回类型
   * @throws
    */
	public void down() {
		if (floorhit(r)) {
			y = r.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		}else if (floorhit(r1)) {
			y = r1.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		} else if (floorhit(r2)) {
			y = r2.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		} else if (floorhit(r3)) {
			y = r3.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		} else if (floorhit(r4)) {
			y = r4.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		} else if (floorhit(r5)) {
			y = r5.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		} else if (floorhit(r6)) {
			y = r6.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		} else if (floorhit(r7)) {
			y = r7.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		} else if (floorhit(r8)) {
			y = r8.y - height / 2;
			v0 = Constant.HERO_JUMPSPEED;
			vt = 0;
			jump_up = true;
			jump = false;
			stand = true;
			this.action = Action.STAND;
		}
	}
	/**
	 * 
	* @Title: drawinfo
	* @Description: TODO(写英雄信息)
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
		g.drawString("LV." + level + " " + "神箭手", x, y);
		g.setColor(color);

	}
    /**
     * 
    * @Title: drawMPBar
    * @Description: TODO(画英雄蓝条)
    * @param @param g    参数
    * @return void    返回类型
    * @throws
     */
	private void drawMPBar(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.BLUE);
		// 画矩形
		g.drawRect(340, 680, 300, height / 10);
		// 计算不同生命值血条的宽度
		double width = MP / MP_FULL * 300;
		if (MP / MP_FULL <= 0.7) {
			g.setColor(Color.BLACK);
		}
		if (MP / MP_FULL <= 0.2) {
			g.setColor(Color.BLACK);
		}
		// 填充
		g.fillRect(340, 680, (int) width, height / 10);
		g.setColor(color);
	}
	/**
	 * 
	* @Title: drawBlooBar
	* @Description: TODO(画英雄血条)
	* @param @param g    参数
	* @return void    返回类型
	* @throws
	 */
	private void drawBlooBar(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.CYAN);
		// 画矩形
		g.drawRect(20, 680, 300, height / 10);
		// 计算不同生命值血条的宽度
		double width = HP / HP_FULL * 300;
		if (HP / HP_FULL <= 0.7) {
			g.setColor(Color.YELLOW);
		}
		if (HP / HP_FULL <= 0.2) {
			g.setColor(Color.RED);
		}
		// 填充
		g.fillRect(20, 680, (int) width, height / 10);
		g.setColor(color);
	}
}
