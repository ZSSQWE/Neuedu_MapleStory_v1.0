package com.neuedu.maplestory.emtity;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: GameOver
* @Description: TODO(游戏结束窗口)
* @author 张盛爽
* @date 2019年8月26日
*
 */
public class GameOver extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7060665131638112508L;
	/**
	 * 
	* @Title: loadFrame
	* @Description: TODO(游戏窗口设置)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public  void loadFrame() {
		// 大小
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		// 位置
//		this.setLocation(x, y);
		// 可见
		this.setVisible(true);
		// 窗口监听器 窗口关闭
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// 设置不可改变窗口大小 默认值是true
		this.setResizable(false);
		// 设置标题
		this.setTitle("睿道冒险岛   作者：ZSS");
		// 相对父窗口上下左右居中
		this.setLocationRelativeTo(null);
	
		
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(imageUtil.getimage("gameover"), 0, 0, null);
	}
	public static void main(String[] args) {
	  new GameOver().loadFrame();
	}
}
