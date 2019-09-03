package com.neuedu.maplestory.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neuedu.maplestory.constant.Constant;

public class MyFrame extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3444149344833895154L;
	public void loadFrame() {
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
		//线程启动
		new Mytherd().start();
		
		
		
	}

	class Mytherd extends Thread{
		@Override
		public void run() {
			for(;;) {
				repaint();
				Mytherd mytherd=new Mytherd();
				try {
					mytherd.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	// 双缓冲防闪烁
	 private Image bufferImage = null;
	 @Override
	 public void update(Graphics g) {
	  if (bufferImage == null) {
	   bufferImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	  }
	  Graphics gOff = bufferImage.getGraphics();
	  Color c = gOff.getColor();
	  gOff.setColor(Color.black);
	  gOff.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	  gOff.setColor(c);
	  paint(gOff);
	  g.drawImage(bufferImage, 0, 0, null);
	 }
}