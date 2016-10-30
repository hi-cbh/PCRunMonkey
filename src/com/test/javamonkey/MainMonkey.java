package com.test.javamonkey;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;



public class MainMonkey extends JFrame implements ActionListener{
	
	JPanel jp1,jp2,jp3,jp4, jp5, jp6, jp7, jp8, jp9,jp10=null;
	JRadioButton logjrb1,logjrb2,logjrb3=null;
	ButtonGroup bg=null;
	JLabel Mainjl,logjl, jl1, jl2, jl3,jl4 , jl5 ,jl6, jl7, jl8,jl9,jl10,jl11=null;
	JComboBox<String> jcb = null;
	JButton add, jb1, jb2, jb3, jb4, jb5, jb6, jb7 = null;
	JTextField jtf1, jtf2, jtf3=null;
	JCheckBox jcb1 ,jcb2, jcb3, jcb4, jcb5,jcb6, jcb7, jcb8,jcb9,jcb10 = null;
	JSlider js1 ,js2, js3, js4,js5, js6, js7, js8= null;
	Box slider = new Box(BoxLayout.Y_AXIS);
	
	Font f16 = new Font("",Font.BOLD,16);
	
	
	public static void main(String[] args) {

		MainMonkey  mm=new MainMonkey();
	}
	
	/**
	 * 构成函数
	 */
	public MainMonkey(){
		
		//面板声明
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();				
		jp5=new JPanel();	
		jp6=new JPanel();	
		jp7=new JPanel();
		jp8=new JPanel();
		jp9=new JPanel();
		jp10=new JPanel();
		//标签
		Mainjl=new JLabel("Monkey测试");
		this.add(Mainjl);
		
		//日志布局
		logjl=new JLabel("日志级别");
		//日志级别
		logjrb1=new JRadioButton("基本LOG");
		logjrb2=new JRadioButton("详细LOG");
		logjrb3=new JRadioButton("全部LOG");
		bg=new ButtonGroup();
		bg.add(logjrb1);
		bg.add(logjrb2);
		bg.add(logjrb3);
		logjrb3.setSelected(true);
		
		jp1.add(logjl);
		jp1.add(logjrb1);
		jp1.add(logjrb2);
		jp1.add(logjrb3);
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp1);
		
		//运行的包名
		//运行包名
		jl1=new JLabel("Package: ");
		jcb = new JComboBox<String>();
		add = new JButton("选择");
		jp2.add(jl1);
		jp2.add(jcb);
		jp2.add(add);
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp2);
		
		
		//运行种子 seed
		jl2=new JLabel("随机种子：");
		jtf1=new JTextField(10);
		jcb1 = new JCheckBox("随机运行");
		jp3.add(jl2);
		jp3.add(jcb1);
		jp3.add(jtf1);
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp3);
		
		//间隔时间
		jl3=new JLabel("间隔时间：");
		jtf2=new JTextField(10);
		jcb2 = new JCheckBox("间隔时间");
		jp4.add(jl3);
		jp4.add(jcb2);
		jp4.add(jtf2);
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp4);
				
		jp5.setBorder(BorderFactory.createTitledBorder("调试"));
		jcb3 = new JCheckBox("ignore-crashes");
		jcb4 = new JCheckBox("ignore-timeouts");
		jcb5 = new JCheckBox("ignore-security-exceptions");
		jcb6 = new JCheckBox("kill-process-after-error");
		jcb7 = new JCheckBox("randomize-throttle");
		jp5.add(jcb3);
		jp5.add(jcb4);
		jp5.add(jcb5);
		jp5.add(jcb6);
		jp5.add(jcb7);
		jp5.setLayout(new GridLayout(3,2));
		this.add(jp5);
		
		
		
		jp6.setBorder(BorderFactory.createTitledBorder("事件百分比"));
        
		jp6.setLayout(new BoxLayout(jp6,BoxLayout.Y_AXIS));
		
		js1 = MyJSlider.getMyJSlider();
		js2 = MyJSlider.getMyJSlider();
		js3 = MyJSlider.getMyJSlider();
		js4 = MyJSlider.getMyJSlider();
		js5 = MyJSlider.getMyJSlider();
		js6 = MyJSlider.getMyJSlider();
		js7 = MyJSlider.getMyJSlider();
		js8 = MyJSlider.getMyJSlider();	

		int x = 5;
		int y = 20;
		
		jp9.add(js1);
		jp9.add(Box.createVerticalStrut(x));
		jp9.add(js2);
		jp9.add(Box.createVerticalStrut(x));
		jp9.add(js3);
		jp9.add(Box.createVerticalStrut(x));
		jp9.add(js4);
		jp9.add(Box.createVerticalStrut(x));
		jp9.add(js5);
		jp9.add(Box.createVerticalStrut(x));
		jp9.add(js6);
		//jp6.add(js7);
		//jp6.add(js8);
		//jp6.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		//jp6.setLayout(new GridLayout(1,8));
		jp9.setLayout(new BoxLayout(jp9,BoxLayout.X_AXIS));
		
		jl6 = new JLabel("touch");
		jl7 = new JLabel("motion");
		jl8 = new JLabel("trackball");
		jl9 = new JLabel("nav");
		jl10 = new JLabel("appswitch");
		jl11 = new JLabel("anyevent");
		

		
		jp10.add(jl6);
		jp10.add(Box.createVerticalStrut(x));
		jp10.add(jl7);
		jp10.add(Box.createVerticalStrut(x));
		jp10.add(jl8);
		jp10.add(Box.createVerticalStrut(x));
		jp10.add(jl9);
		jp10.add(Box.createVerticalStrut(x));
		jp10.add(jl10);
		jp10.add(Box.createVerticalStrut(x));
		jp10.add(jl11);
		jp10.setLayout(new BoxLayout(jp10,BoxLayout.X_AXIS));
		
		jp6.add(jp9);
		jp6.add(jp10);
		this.add(jp6);
		
		//快捷方案
		jl4=new JLabel("快捷测试方案：");
		jb1 = new JButton("方案一");
		jb2 = new JButton("方案二");
		jb3 = new JButton("方案三");
		jp7.add(jl4);
		jp7.add(jb1);
		jp7.add(jb2);
		jp7.add(jb3);
		jp7.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp7);
		
		jl5 = new JLabel("运行测试: ");
		jtf3 = new JTextField(20);
		jp8.add(jl5);
		jp8.add(jtf3);
		jp8.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp8);
		
		
		jb5 = new JButton("Run");
		jb6 = new JButton("Close");
		jb7 = new JButton("Stop");

		this.add(jb5);
		this.add(jb6);
		this.add(jb7);
		
		
		//设置布局
		this.setLayout(null );
				
		jp1.setBounds(10, 5, 400, 30);
		
		jp2.setBounds(10, 40, 400, 40);
		
		jp3.setBounds(10, 80, 400, 40);
		
		jp4.setBounds(10, 120, 400, 40);
		
		jp5.setBounds(1, 160, 390, 100);
		
		jp6.setBounds(1, 260, 390, 165);
		
		jp8.setBounds(10, 430, 400, 40);
		
		jp7.setBounds(10, 470, 400, 40);
		
		jb5.setBounds(10, 520, 120, 30);
		
		jb7.setBounds(135, 520, 120, 30);
		
		jb6.setBounds(260, 520, 120, 30);
		
		//设置标题
		this.setTitle("Monkey 传参工具");
		//设置窗体大小
		this.setSize(400,600);
		//设置窗体初始位置
		this.setLocation(200, 150);
		//设置当关闭窗口时，保证JVM也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示窗体
		this.setVisible(true);
		this.setResizable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand()==" Run ")
		{

			
		}
		else if(e.getActionCommand()=="Close")
		{
                 
		}	
		
	}
	
	public void addSlider(JSlider slider, String des){
		Box box = new Box(BoxLayout.PAGE_AXIS);
		box.add(slider);
		box.add(new JLabel(des));
		
	}

}
