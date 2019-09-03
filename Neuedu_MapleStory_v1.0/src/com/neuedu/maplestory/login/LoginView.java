package com.neuedu.maplestory.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.util.imageUtil;

public class LoginView extends JFrame implements MouseInputListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4216442584702662228L;
	private JTextField nickNameField = null;
	private JPasswordField passwordField = null;
	private JPanel jp;
	
	public static void main(String[] args) {
		new LoginView().setVisible(true);
	}
	
	public LoginView() {
		UIManager.put("TextField.font", Tookit.getFont1());
		UIManager.put("Label.font", Tookit.getFont1());
		UIManager.put("Button.font", Tookit.getFont1());
		this.init();
	}

	private void init() {
		JPanel buttom = new JPanel(new BorderLayout());
		buttom.add(this.loginPanel());
		this.add(buttom);
		this.setSize(509, 357);// 窗口大小
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(imageUtil.getimage("hero_right_stand1_0"));// 窗口图标
		this.setTitle("冒险岛登录");// 窗口标题
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口

		
	}

	private Component loginPanel() {
		JPanel jPanel = new JPanel(new BorderLayout());
		JLabel jLogin = new JLabel(new ImageIcon("src/login.png"), JLabel.CENTER);
		jPanel.add(jLogin, BorderLayout.NORTH);
		MyLoginPanel myPanel = new MyLoginPanel();
		myPanel.setLayout(null);
		
		final JLabel nickName = new JLabel("账号", JLabel.CENTER);
		nickName.setBounds(290, 50, 65, 30);
		nickName.setFont(Tookit.getFont4());
		myPanel.add(nickName);
		
		jp = new JPanel();
		jp.setOpaque(false);
		jp.setBounds(310, 100, 140, 200);
		myPanel.add(jp);
		
		nickNameField = new JTextField(12);
		nickNameField.setBounds(300, 80, 140, 21);
		myPanel.add(nickNameField);
		
		JLabel password = new JLabel("密码", JLabel.CENTER);
		password.setFont(Tookit.getFont4());
		password.setBounds(290, 110, 65, 15);
		passwordField = new JPasswordField(12);
		passwordField.setBounds(300, 140, 140, 21);
		myPanel.add(password);
		myPanel.add(passwordField);
		
		JButton login = new JButton("登录");
		login.setBounds(150, 230, 81, 30);
		myPanel.add(login);
		login.setPreferredSize(new Dimension(80, 30));
		login.setBackground(new Color(0x71B8EC));//背景颜色
		//点击登录按钮产生的事件
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获得账号文本框的内容并且去掉内容的前后空格
				String nickName = nickNameField.getText().trim();
				// 获得密码文本框的内容并且去掉内容的前后空格
				String password = String.valueOf(passwordField.getPassword()).trim();
                // 如果账号为空，则提示信息
				if (nickName.equals("")) {
					// 弹出窗口提示
					JOptionPane.showMessageDialog(LoginView.this, "请输入账号");
					return;
				}
				// 如果密码为空则提示输入密码
				if (password.equals("")) {
					// 弹出窗口提示
					JOptionPane.showMessageDialog(LoginView.this, "密码不能为空");
					return;
				}
				ImplUserService service = new UserService();
				User login = service.selectUser(nickName, password);
				if (login != null) { 
					JOptionPane.showMessageDialog(LoginView.this, "欢迎" + login.getUsername() + "来到冒险岛!");
					// 登录成功后，跳到游戏界面
					new MapleStoryClient(login).loadFrame();
					// 当前窗口消失
					LoginView.this.dispose();
				} else { 
					JOptionPane.showMessageDialog(LoginView.this, "账号密码错误");
					return;
				}
				
			}
		});
		

		JButton regist = new JButton("注册");
		regist.setBounds(250, 230, 81, 30);
		myPanel.add(regist);	
		regist.setPreferredSize(new Dimension(80, 30));
		regist.setBackground(new Color(46, 116, 188));
		regist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegistView().setVisible(true);
				LoginView.this.dispose();
			}
		});
		
		JButton updatePass = new JButton("修改密码");
		updatePass.setBounds(340, 180, 100, 30);
		myPanel.add(updatePass);		
		updatePass.setPreferredSize(new Dimension(80, 30));
		updatePass.setBackground(new Color(90, 177, 234));

		JButton scores = new JButton("历史分数");
		scores.setBounds(340, 230, 100, 30);
		scores.setPreferredSize(new Dimension(80, 30));
		scores.setBackground(new Color(127, 197, 245));
		myPanel.add(scores);
		jPanel.add(myPanel);
		return jPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		nickNameField.setText(((JLabel) e.getSource()).getText());
		jp.removeAll();
		this.remove(jp);
		this.repaint();
		this.validate();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jLabel = (JLabel) e.getSource();
		jLabel.setForeground(Tookit.getColor());
		jLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jLabel = (JLabel) e.getSource();
		jLabel.setForeground(Color.black);
		jLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
