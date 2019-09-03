package com.neuedu.maplestory.emtity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: background
* @Description: TODO(画背景)
* @author 张盛爽
* @date 2019年8月25日
*
 */
public class background extends MapleStoryObject {
	/**
	 * 定义图片数组
	 */
	public static Image[] images = new Image[100];
	/**
	 * 静态代码块加载数组
	 */
	static {

		for (int i = 0; i < 3; i++) {
			images[i] = imageUtil.getimage("tree" + i);
		}
		for (int i = 3; i < 11; i++) {
			images[i] = imageUtil.getimage("door" + (i-3));
		}
	}
    /**
     * 构造方法
     */
	public background() {
		this.x = 0;
		this.y = 0;
		this.img = imageUtil.getimage("background");
		this.width = img.getWidth(null);
		this.speed = Constant.SPEED;
	}
	/**
	 * 构造方法
	 * @param x x坐标
	 * @param y y坐标
	 * @param image 图片类型参数
	 */
	public background(int x,int y,Image image) {
		this.x = x;
		this.y = y;
		this.img = image;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
   /**
    * 重写move方法
    */
	@Override
	public void move() {

	}

	/**
	 * 英雄动背景随着动
	 */
//	@Override
//	public void keyPressed(KeyEvent e) {
//
//		super.keyPressed(e);
//	}
//
//	/**
//	 * 英雄停背景随着停
//	 */
//	@Override
//	public void keyReleased(KeyEvent e) {
//		super.keyReleased(e);
//	}
    /**
     * 图片数组下标
     */
	public int treecount = 0;
	/**
     * 图片数组下标
     */
	public int doorcount = 3;

	/**
	 * 画背景
	 */
	@Override
	public void draw(Graphics g) {
		if (treecount > 2) {
			treecount = 0;
		}
		if (doorcount > 10) {
			doorcount = 3;
		}
//		g.drawImage(img, this.x-Constant.GAME_WIDTH, this.y, null);
//		g.drawImage(img, this.x, this.y, null);
		g.drawImage(imageUtil.getimage("background"), x , y, null);
//		g.drawImage(imageUtil.getimage("background3"), x + 2*Constant.GAME_WIDTH, y, null);

		for (int i = 0; i < 12; i++) {
			g.drawImage(imageUtil.getimage("floor2"), 0 + i * 130, 600, null);
		}
		for (int i = 0; i < 7; i++) {
			g.drawImage(imageUtil.getimage("plan3"), 0 + i * 150, 590, null);
		}

		g.drawImage(images[treecount++],  600, 190, null);
		g.drawImage(imageUtil.getimage("tree3"), 600, 570, null);
		
		g.drawImage(imageUtil.getimage("floor4"), 200, 490, null);
		g.drawImage(imageUtil.getimage("floor3"), 400, 380, null);
		g.drawImage(imageUtil.getimage("floor3"), 600, 270, null);
		
		g.drawImage(imageUtil.getimage("floor4"), 700, 150, null);
		g.drawImage(imageUtil.getimage("floor3"), 1000, 200, null);
		g.drawImage(imageUtil.getimage("floor3"), 750, 380, null);
		
		g.drawImage(imageUtil.getimage("floor4"), 1400, 450, null);
		g.drawImage(imageUtil.getimage("floor3"), 1150, 350, null);
		g.drawImage(imageUtil.getimage("floor3"), 1300, 280, null);
		
		g.drawImage(imageUtil.getimage("floor3"), 0, 150, null);
		g.drawImage(imageUtil.getimage("floor3"), 150, 150, null);
		g.drawImage(imageUtil.getimage("floor3"), 300, 150, null);
		
		g.drawImage(images[doorcount++], 1300, 30, null);
	    
		move();

	}

}
