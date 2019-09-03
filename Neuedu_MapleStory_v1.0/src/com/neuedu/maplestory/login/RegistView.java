package com.neuedu.maplestory.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.neuedu.maplestory.util.imageUtil;

public class RegistView extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField nickNameField = null;
	private JPasswordField passwordField = null;
	private JPasswordField confirmPasswordField = null;
    private JPanel jp;
	/**
	 * @param args
	 */
	
	public RegistView() {
		UIManager.put("TextField.font", Tookit.getFont1()) ;
		UIManager.put("Label.font", Tookit.getFont1()) ;
		UIManager.put("Button.font", Tookit.getFont1()) ;
		this.init();
	}

	private void init() {
		JPanel buttom = new JPanel(new BorderLayout()) ;
		buttom.add(this.RegistPanel()) ;
		this.add(buttom) ;
		this.setSize(509, 357) ;
		this.setLocationRelativeTo(null) ;
		this.setResizable(false) ;
		this.setIconImage(imageUtil.getimage("hero_right_stand1_0")) ;
		this.setTitle("冒险岛注册界面") ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
	}
	/**
	 *处理注册面板
	 * @return
	 */
	public JPanel RegistPanel(){
		JPanel jPanel = new JPanel(new BorderLayout()) ;
		//上面
		JLabel jRegist = new JLabel(
				new ImageIcon("regist.png"), JLabel.CENTER);
		jPanel.add(jRegist, BorderLayout.NORTH) ;//加一个jlable
		//下面
		MyRegistPanel myPanel = new MyRegistPanel();
		myPanel.setLayout(null);
		final JLabel nickName = new JLabel("昵    称", JLabel.CENTER);
		nickName.setBounds(280, 40, 65, 15);
		nickName.setFont(Tookit.getFont4());
		myPanel.add(nickName);
		
		//加一个面板可以存入多个jlabel
		jp = new JPanel();
		jp.setOpaque(false);
		jp.setBounds(130, 80, 140, 200);
		myPanel.add(jp) ;
		nickNameField = new JTextField(12);
		nickNameField.setBounds(280, 60, 140, 21);
		myPanel.add(nickNameField);
		
		JLabel password = new JLabel("密     码", JLabel.CENTER);
		password.setFont(Tookit.getFont4());
		password.setBounds(280, 90, 65, 15);
		passwordField = new JPasswordField(12);
		passwordField.setBounds(280, 110, 140, 21);
		myPanel.add(password);
		myPanel.add(passwordField);
		
		JLabel confrimPassword = new JLabel("确认密码", JLabel.CENTER);
		confrimPassword.setFont(Tookit.getFont4());
		confrimPassword.setBounds(280, 140, 85, 15);
		confirmPasswordField = new JPasswordField(12);
		confirmPasswordField.setBounds(280, 160, 140, 21);
		myPanel.add(confrimPassword);
		myPanel.add(confirmPasswordField);

		JButton regist = new JButton("注册");
		regist.setBounds(350, 190, 81, 30);
		myPanel.add(regist) ;
		regist.addActionListener(new ActionListener() {
			
			// 注册逻辑处理
			@Override
			public void actionPerformed(ActionEvent e) {
				// 文本框中昵称的值ֵ
				String nickName = nickNameField.getText().trim();
				// 文本框中密码的值ֵ
				String password = String.valueOf(passwordField.getPassword()).trim();
				// 文本框中确认密码的值ֵ
				String confirmPassword = String.valueOf(confirmPasswordField.getPassword()).trim();
				if (nickName.equals("")) {
					JOptionPane.showMessageDialog(RegistView.this, "请输入昵称！");
				    return;
				} else if (nickName.length() > 6) {
					JOptionPane.showMessageDialog(RegistView.this, "昵称不能大于6位！");
				    return;
				}
				if (password.equals("")) {
					JOptionPane.showMessageDialog(RegistView.this, "请输入密码！");
					return;
				}
				if (confirmPassword.equals("")) {
					JOptionPane.showMessageDialog(RegistView.this, "请输入确认密码！");
					return;
				}
				if (!(password.equals(confirmPassword))) {
					JOptionPane.showMessageDialog(RegistView.this, "两次密码必须相同！");
					return;
				}
				
				//验证逻辑判断
				ImplUserService service = new UserService();
				boolean success = 
						service.insertUser(new User(null, nickName, password, null, null, null));
				if (success) {
					JOptionPane.showConfirmDialog(null, "注册成功！");
					new LoginView().setVisible(true);
					RegistView.this.dispose();
				} else {
					JOptionPane.showMessageDialog(RegistView.this, "该用户已注册！");
				    return;
				}
			}
		});
		regist.setPreferredSize(new Dimension(80, 30));
		regist.setBackground(new Color(0x71B8EC));
		
		JButton reset = new JButton("重置");
		reset.setBounds(300, 230, 81, 30);
		myPanel.add(reset) ;
		reset.addActionListener(new ActionListener() {
			
			//所有文本框设置为空
			@Override
			public void actionPerformed(ActionEvent e) {
				nickNameField.setText("") ;
				passwordField.setText("") ;
				confirmPasswordField.setText("") ;
			}
		});
		
		reset.setPreferredSize(new Dimension(80, 30));
		reset.setBackground(new Color(75, 158, 217));
		
		JButton login = new JButton("登录");
		login.setBounds(250, 190, 81, 30) ;
		myPanel.add(login) ;
		login.addActionListener(new ActionListener() {
			
			// 跳转到登录页面，关闭该窗口
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView().setVisible(true);
				RegistView.this.dispose();
			}
		});
		
		reset.setPreferredSize(new Dimension(80, 30));
		reset.setBackground(new Color(90, 177, 234));
		
		jPanel.add(myPanel);
		return jPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		nickNameField.setText(((JLabel)e.getSource()).getText()) ;
		//设置面板属性	
		jp.removeAll() ;
		this.remove(jp) ;
		this.repaint() ;
		this.validate() ;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jLabel = (JLabel)e.getSource() ;
		jLabel.setForeground(Tookit.getColor()) ;
		jLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jLabel = (JLabel)e.getSource() ;
		jLabel.setForeground(Color.black) ;
		jLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)) ;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
