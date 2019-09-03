package com.neuedu.maplestory.emtity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neuedu.maplestory.client.MapleStoryClient;

/**
 * 
 * @ClassName: MapleStoryObject
 * @Description: TODO(所有类的父类，实现move和draw方法)
 * @author 张盛爽
 * @date 2019年8月16日
 *
 */
public abstract class MapleStoryObject implements Drawable, Moveable {
	/**
	 * x坐标
	 */
	public int x;
	/*
	 * y坐标
	 */
	public int y;
	/**
	 * 图片对象
	 */
	public Image img;
	/**
	 * 速度
	 */
	public int speed;

	/**
	 * 调停者
	 */
	public MapleStoryClient msc;

	/**
	 * 设置活得状态
	 */
	public boolean live = true;
	/**
	 * 图片宽
	 */
	public int width;
	/**
	 * 图片高
	 */
	public int height;
	/**
	 * 方向
	 */
	public Direction dir;
	/**
	 * 动作
	 * 
	 */
	public Action action;
	/**
	 * 当前血量
	 */
	public double HP;
	/**
	 * 满血血量
	 */
	public double HP_FULL;
	/**
	 * 当前蓝量
	 */
	public double MP;
	/**
	 * 满蓝量
	 */
	public double MP_FULL;

	/**
	 * 等级
	 */
	public int level;
	/**
	 * 攻击力
	 */
	public int aggressivity;
	/**
	 * 防御力
	 */
	public int defensive;
	/**
	 * 把移动方法定义称为抽象方法
	 */
	@Override
	public abstract void move();

	/**
	 * 把画方法定义为抽象方法
	 */
	@Override
	public abstract void draw(Graphics g);

	/**
	 * 获取对象图片的矩形
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
