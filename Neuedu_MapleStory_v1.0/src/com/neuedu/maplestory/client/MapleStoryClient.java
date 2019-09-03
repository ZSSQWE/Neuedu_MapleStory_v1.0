package com.neuedu.maplestory.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.emtity.Arrow;
import com.neuedu.maplestory.emtity.Boss;
import com.neuedu.maplestory.emtity.BossActtack1;
import com.neuedu.maplestory.emtity.BossActtack2;
import com.neuedu.maplestory.emtity.BossSkill1;
import com.neuedu.maplestory.emtity.BossSkill2;
import com.neuedu.maplestory.emtity.BossSkill3;
import com.neuedu.maplestory.emtity.BossSkill4;
import com.neuedu.maplestory.emtity.CloseAttack;
import com.neuedu.maplestory.emtity.GameOver;
import com.neuedu.maplestory.emtity.Hero;
import com.neuedu.maplestory.emtity.Items;
import com.neuedu.maplestory.emtity.Mob;
import com.neuedu.maplestory.emtity.Mob1;
import com.neuedu.maplestory.emtity.Mob1Attack;
import com.neuedu.maplestory.emtity.Mob2;
import com.neuedu.maplestory.emtity.Mob2Attack;
import com.neuedu.maplestory.emtity.SkillI;
import com.neuedu.maplestory.emtity.Virtory;
import com.neuedu.maplestory.emtity.background;
import com.neuedu.maplestory.emtity.skilK;
import com.neuedu.maplestory.emtity.skilU;
import com.neuedu.maplestory.login.User;
import com.neuedu.maplestory.util.MusicUtil;
import com.neuedu.maplestory.util.MyFrame;
import com.neuedu.maplestory.util.imageUtil;
/**
 * 
* @ClassName: MapleStoryClient
* @Description: TODO(客户端)
* @author 张盛爽
* @date 2019年8月25日
*
 */
public class MapleStoryClient extends MyFrame {

	/**
	 * Frame的ID
	 */
	private static final long serialVersionUID = -454444055859601182L;
	/**
	 * 用户类型的变量
	 */
	private static User login = null;
	/**
	 * 背景类的对象
	 */
	public background background = new background();
	/**
	 * hero类的对象
	 */
	public Hero hero = new Hero(this);
	/**
	 * hero子弹类数组
	 */
	public List<Arrow> arrows = new ArrayList<>();
	/**
	 * 英雄k技能数组
	 */
	public List<skilK> skilKs = new ArrayList<>();
	/**
	 * 英雄U技能数组
	 */
	public List<skilU> skilUs = new ArrayList<>();
	/**
	 * 英雄I技能数组
	 */
	public List<SkillI> skillIs = new ArrayList<>();
	/**
	 * 怪物数组
	 */
	public List<Mob> mobs = new ArrayList<>();
	/**
	 * 怪物1攻击数组
	 */
	public List<Mob1Attack> attacks2 = new ArrayList<>();
	/**
	 * 怪物2攻击数组
	 */
	public List<Mob2Attack> aMob2Attacks = new ArrayList<>();
	/**
	 * 道具数组
	 */
	public List<Items> items = new ArrayList<>();
	/**
	 * 英雄近战攻击数组
	 */
	public List<CloseAttack> attacks = new ArrayList<>();
	Mob1 mob1 = new Mob1();
	Boss boss = new Boss();
	/**
	 * boss攻击1数组
	 */
	public List<BossActtack1> bossActtack1s = new ArrayList<>();
	/**
	 * boss攻击2数组
	 */
	public List<BossActtack2> bossActtack2s = new ArrayList<>();
	/**
	 * boss攻击技能1数组
	 */
	public List<BossSkill1> sBossSkill1s = new ArrayList<>();
	/**
	 * boss攻击技能2数组
	 */
	public List<BossSkill2> sBossSkill2s = new ArrayList<>();
	/**
	 * boss攻击技能3数组
	 */
	public List<BossSkill3> sBossSkill3s = new ArrayList<>();
	/**
	 * boss攻击技能4数组
	 */
	public List<BossSkill4> sBossSkill4s = new ArrayList<>();
	// public List<BossActtack2> bossActtack2s = new ArrayList<>();
	/**
	 * 代码块，用来加载怪物
	 */
	{

		for (int i = 0; i < 2; i++) {
			int random = new Random().nextInt(1000) + 200;
			int random1 = new Random().nextInt(530);
			// 设置每个怪物的位置

			Mob mob = new Mob1(this, random, 510);

			// Mob mob1 = new Mob2(this, 500 + (i * 400), 510);
			Mob mob1 = new Mob2(this, random, random1, hero);
			Mob mob3 = new Mob2(this, random, random1, hero);
			mobs.add(mob3);
			mobs.add(mob1);

			// 把怪物放进容器中
			mobs.add(mob);
			// mobs.add(mob1);
		}
        /**
         * boss类对象
         */
		Mob boss = new Boss(this, new Random().nextInt(1200) + 100, new Random().nextInt(300) + 300, hero);
		mobs.add(boss);

	}
   /**
    * 空构造方法
    */
	public MapleStoryClient() {

	}
   /**
    * 构造方法
    * @param login 登录参数
    */
	public MapleStoryClient(User login) {
		this.login = login;
	}
    /**
     * 重写loadFrame()方法
     */
	@Override
	public void loadFrame() {

		super.loadFrame();
		this.setIconImage(imageUtil.getimage("icon"));
		// 键盘监听
		this.addKeyListener(new KeyAdapter() {
			/**
			 * 键盘按下时
			 */
			@Override
			public void keyPressed(KeyEvent e) {

				hero.keyPressed(e);
				// background.keyPressed(e);

			}

			/**
			 * 键盘松开时
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				hero.keyReleased(e);
				// background.keyReleased(e);
			}

		});
		/**
		 * 背景音乐
		 */
		new MusicUtil("com/neuedu/maplestory/sounds/背景音乐.mp3", true).start();
	}
   /**
    * 重写画方法，画英雄，怪物，子弹，技能
    */
	@Override
	public void paint(Graphics g) {
		/**
		 * 画背景
		 */
		background.draw(g);
		hero.draw(g);
		for (int i = 0; i < arrows.size(); i++) {
			Arrow arrow = arrows.get(i);
			arrow.draw(g);
			arrow.hit(mobs, hero);
		}

		for (int i = 0; i < attacks.size(); i++) {
			CloseAttack attack = attacks.get(i);
			attack.draw(g);
			attack.hit(mobs, hero);
		}
		for (int i = 0; i < skilKs.size(); i++) {
			skilK skilK = skilKs.get(i);
			skilK.draw(g);
			skilK.hit(mobs, hero);
		}
		for (int i = 0; i < skilUs.size(); i++) {
			skilU skilU = skilUs.get(i);
			skilU.draw(g);
			skilU.hit(mobs, hero);
		}
		for (int i = 0; i < skillIs.size(); i++) {
			SkillI skI = skillIs.get(i);
			skI.draw(g);
			skI.hit(mobs, hero);
		}

		for (int i = 0; i < mobs.size() - 1; i++) {
			Mob mob = mobs.get(i);
			mob.draw(g);

		}
		if (mobs.size() == 1) {
			Mob mob = mobs.get(0);
			mob.draw(g);
		}
		for (int i = 0; i < items.size(); i++) {
			Items items2 = items.get(i);
			items2.draw(g);

		}
		for (int i = 0; i < attacks2.size(); i++) {
			Mob1Attack mobAttack = attacks2.get(i);
			mobAttack.draw(g);
			mobAttack.hit(hero);

		}
		for (int i = 0; i < aMob2Attacks.size(); i++) {
			Mob2Attack attack = aMob2Attacks.get(i);
			attack.draw(g);
			attack.hit(hero);
		}
		for (int i = 0; i < bossActtack1s.size(); i++) {
			BossActtack1 bossActtack1 = bossActtack1s.get(i);
			bossActtack1.draw(g);
			bossActtack1.hit(hero);
		}
		for (int i = 0; i < bossActtack2s.size(); i++) {
			BossActtack2 bossActtack2 = bossActtack2s.get(i);
			bossActtack2.draw(g);
			bossActtack2.hit(hero);
		}
		for (int i = 0; i < sBossSkill1s.size(); i++) {
			BossSkill1 bossSkill1 = sBossSkill1s.get(i);
			bossSkill1.draw(g);
			bossSkill1.hit(hero);
		}
		for (int i = 0; i < sBossSkill2s.size(); i++) {
			BossSkill2 bossSkill2 = sBossSkill2s.get(i);
			bossSkill2.draw(g);
			bossSkill2.hit(hero);
		}
		for (int i = 0; i < sBossSkill3s.size(); i++) {
			BossSkill3 bossSkill3 = sBossSkill3s.get(i);
			bossSkill3.draw(g);
			bossSkill3.hit(hero);
		}
		for (int i = 0; i < sBossSkill4s.size(); i++) {
			BossSkill4 bossSkill4 = sBossSkill4s.get(i);
			bossSkill4.draw(g);
			bossSkill4.hit(hero);
		}
		hero.eatIteam(items);
		if (hero.HP <= 0) {
			MapleStoryClient.this.dispose();
			new GameOver().loadFrame();
		}
		if (mobs.size()==0) {
			MapleStoryClient.this.dispose();
			new Virtory().loadFrame();
		}
//    	sK.draw(g);
		Font font = g.getFont();
		g.setFont(new Font("微软雅黑", Font.BOLD, 30));
		Color c = g.getColor();
		g.setColor(Color.white);
		g.drawString("英雄当前HP" + hero.HP, 200, 100);
		g.drawString("英雄当前MP" + hero.MP, 200, 150);
		g.drawString("英雄当前等级" + hero.level, 200, 200);
		g.drawString("英雄当前攻击力" + hero.aggressivity, 200, 250);
		g.setFont(font);
		g.setColor(c);
	}

	public static void main(String[] args) {
		new MapleStoryClient().loadFrame();
	}
}
