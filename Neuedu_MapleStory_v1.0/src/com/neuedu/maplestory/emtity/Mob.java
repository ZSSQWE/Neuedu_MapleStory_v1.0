package com.neuedu.maplestory.emtity;

import java.awt.Graphics;

/**
 * 
* @ClassName: Mob
* @Description: TODO(怪物的父类)
* @author 张盛爽
* @date 2019年8月26日
*
 */

public abstract class Mob extends MapleStoryObject{
   /**
    * 名字
    */
	public String name;
	/**
	 * 经验
	 */
	
	public int EXP;
	
	@Override
	public abstract void move();

	@Override
	public abstract void draw(Graphics g);

}
