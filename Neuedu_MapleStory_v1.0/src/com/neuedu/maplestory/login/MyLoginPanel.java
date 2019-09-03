package com.neuedu.maplestory.login;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.neuedu.maplestory.util.imageUtil;

public class MyLoginPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7998346760556669811L;
    @Override
    protected void paintComponent(Graphics g) {
    	g.drawImage(imageUtil.getimage("loginbackground"), 0, 0, null);
    }
}
