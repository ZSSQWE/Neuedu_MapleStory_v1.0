package com.neuedu.maplestory.login;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.neuedu.maplestory.util.imageUtil;

public class MyRegistPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
	
		//画一个背景图
		g.drawImage(imageUtil.getimage("loginbackground"), 0, 0, null) ;
	}
}
