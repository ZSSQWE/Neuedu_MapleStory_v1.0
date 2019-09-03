package com.neuedu.maplestory.emtity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: Items
* @Description: TODO(道具类)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public class Items extends MapleStoryObject {
    
	/**
	 * 构造方法
	 */
	public Items() {
		// TODO Auto-generated constructor stub
	}
	/**变量
	 * 道具类型
	 */
	public int itemsType;
	/**
	 * 
	 * @param msc MapleStoryClient参数
	 * @param x x坐标
	 * @param y y坐标
	 * @param itemsType 道具类型
	 */
	public Items(MapleStoryClient msc,int x,int y,int itemsType) {
		this.msc=msc;
		this.x=x;
		this.y=y;
		switch (itemsType) {
		case 0:
			this.img=imageUtil.getimage("items1");
			break;
		case 1:
			this.img=imageUtil.getimage("items2");
			break;
		case 2:
			this.img=imageUtil.getimage("items3");
			break;
		case 3:
			this.img=imageUtil.getimage("items4");
			break;
		case 4:
			this.img=imageUtil.getimage("items5");
			break;
		case 5:
			this.img=imageUtil.getimage("items_HP1");
			break;
		case 6:
			this.img=imageUtil.getimage("items_HP2");
			break;
		case 7:
			this.img=imageUtil.getimage("items_HP3");
			break;
		case 8:
			this.img=imageUtil.getimage("items_HP4");
			break;
		case 9:
			this.img=imageUtil.getimage("items_HP5");
			break;

		default:
			break;
		}
		
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);
		this.itemsType=itemsType;
		
	}
	/**
	 * 重写移动方法
	 */
	@Override
	public void move() {
		if (jump) {
			jump();
		}
		
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
	 * v0初速度   
	 */
	private double v0 = 50;
	/**
	 * vt末速度
	 */
	private double vt = 0;
	/**
	 *  g重力加速度
	 */
	private double g = 9.8;
	/**
	 * delta_h单位时间位移变化量
	 */
	private double delta_h = 0;
	/**
	 * t单位时间
	 */
	private double t = 1;
	/**
	 *  jump_up默认向上跳
	 */
	private boolean jump_up = true;
	/**
	 * 是否跳
	 */
	private boolean jump = true;

	/**
	 * 
	 * @Title: herojump @Description: TODO(英雄跳的算法) @param 参数 @return void
	 *         返回类型 @throws
	 */
	private void jump() {
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
			if (y >= 600) {
				y = 600;
				// 下次向上跳的速度
				v0 = 50;
				vt = 0;
				jump_up = true;
				jump=false;

			}

		}

	}
}
