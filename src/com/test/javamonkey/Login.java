package com.test.javamonkey;

/*
 * 功能：学生成绩管理系统
 * 步骤1：登录界面的静态实现
 * 步骤2：添加对各个组件的监听。
 * 步骤3：对用户名和密码进行验证。
 * author：ywq
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

	//定义组件
	JButton jb1,jb2, addAppList=null;
	JRadioButton logjrb1,logjrb2,logjrb3=null;
	JPanel jp1,jp2,jp3,jp4=null;
	JTextField jtf, appjtf=null;
	JLabel jlb1,jlb2,logjlb=null;
	JPasswordField jpf=null;
	ButtonGroup bg=null;
	JComboBox<String> jcb = null;
	
	String [] applist = {
			"null",
			"testdata",
			"NULL",
			"haha"
	};
	int count = 0;
	
	//设定用户名和密码
	final String stu_name="6";
	final String stu_pwd="1";
	final String tea_name="5";
	final String tea_pwd="1";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login  ms=new Login();
	}
	public Login()
	{
		 //创建组件
		jb1=new JButton("登录");
		jb2=new JButton("重置");
		//设置监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		logjrb1=new JRadioButton("基本LOG");
		logjrb2=new JRadioButton("详细LOG");
		logjrb3=new JRadioButton("全部LOG");
		bg=new ButtonGroup();
		bg.add(logjrb1);
		bg.add(logjrb2);
		bg.add(logjrb3);
		logjrb3.setSelected(true);
		
		//下拉列表
		jcb = new JComboBox<String>();
		addAppList = new JButton("获取应用列表");

		for(int i = 0; i < 2;i++){
			jcb.addItem(applist[count++]);
		}
			
		appjtf=new JTextField(20);
		
		appjtf.setEditable(false);
		
		addAppList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(count < applist.length){
					jcb.addItem(applist[count++]);
					
				}
			}
		});
		jcb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				appjtf.setText("您选择了 "+ jcb.getSelectedItem());
			}
		});
		jcb.setEditable(true);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();				
		
		jlb1=new JLabel("Run APP: ");
		jlb2=new JLabel("密    码：");
		logjlb=new JLabel("日志级别");
		
		jtf=new JTextField(10);
		jpf=new JPasswordField(10);
		//加入到JPanel中

		//日志级别
		jp3.add(logjlb);
		jp3.add(logjrb1);
		jp3.add(logjrb2);
		jp3.add(logjrb3);

		jp1.add(jlb1);
		jp1.add(appjtf);
		jp1.add(jcb);
		jp1.add(addAppList);

		
		
		jp2.add(jlb2);
		jp2.add(jpf);
		
		
		jp4.add(jb1);
		jp4.add(jb2);
		
		//加入JFrame中
		
		this.add(jp3);
		this.add(jp1);
		this.add(jp2);
		
		this.add(jp4);
		//设置布局管理器
		this.setLayout(new GridLayout(4,1));
		//给窗口设置标题
		this.setTitle("Monkey 传参工具");
		//设置窗体大小
		this.setSize(400,600);
		//设置窗体初始位置
		this.setLocation(200, 150);
		//设置当关闭窗口时，保证JVM也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示窗体
		this.setVisible(true);
		this.setResizable(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand()=="登录")
		{
			//如果选中教师登录
			if(logjrb1.isSelected())
			{
                  tealogin();
			}else if(logjrb2.isSelected()) //学生在登录系统
			{
                  stulogin();
			}
			
		}else if(e.getActionCommand()=="重置")
		{
                  clear();
		}			
		
	}
	
	 //学生登录判断方法
	public void stulogin()
	{
		if(stu_name.equals(jtf.getText())&&stu_pwd.equals(jpf.getText()))
		{
//			System.out.println("登录成功");
			JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);
			clear();
		}else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);
		}else if(jtf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);
		}else if(jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);
		}else
		{
			JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);
		    //清空输入框
			clear();
		}
	}
	//教师登录判断方法
	public void tealogin()
	{
		if(tea_name.equals(jtf.getText())&&tea_pwd.equals(jpf.getText()))
		{
//			System.out.println("登录成功");
			 JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);
			 clear();
		}else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);
		}else if(jtf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);
		}else if(jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);
		}else
		{
			JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);
		    //清空输入框
			clear();
		}
	}
	//清空文本框和密码框
	public	void clear()
	{
		jtf.setText("");
		jpf.setText("");
	}
		
}