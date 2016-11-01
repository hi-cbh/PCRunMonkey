package com.test.javamonkey;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class MainMonkey extends JFrame implements ActionListener{
	
	JPanel jp1,jp2,jp3,jp4, jp5, jp6, jp7, jp8, jp9,jp10=null;
	JRadioButton logjrb1,logjrb2,logjrb3=null;
	ButtonGroup bg=null;
	JLabel Mainjl,logjl, jl1, jl2, jl3,jl4 , jl5 ,jl6, jl7, jl8,jl9,jl10,jl11=null;
	JComboBox<String> jcb = null;
	JButton add, jb1, jb2, jb3, jb4, jb5, jb6, jb7 = null;
	JTextField jtf1, jtf2, jtf3=null;
	JCheckBox jcb1 ,jcb2, jcb3, jcb4, jcb5,jcb6, jcb7, jcb8,jcb9,jcb10, jcb11= null;
	JSlider js1 ,js2, js3, js4,js5, js6, js7, js8= null;
	Box slider = new Box(BoxLayout.Y_AXIS);
	
	
	
	Font f16 = new Font("",Font.BOLD,16);
	public static String cmdline = "adb shell monkey ";
	public static String logline = "-v -v -v";
	public static String packageline = "";
	public static String seedline = "";
	public static String timeline = "1000"; //间隔时间默认1秒
	public static String debugline = "";
	
	public static String runline = "1000";
	
	public String[] list = {" "};
	List<String> stream = null;
	List<String> bllist = null;
	static int debuglist[] = {1,1,1,1,1};
	static String[] debugStrList = {
		"--ignore-crashes",
		"--ignore-timeouts",
		"--ignore-security-exceptions",
		"--kill-process-after-error",
		"--randomize-throttle"
	};
	
	
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
		
		//添加侦听

        // 生成一个新的动作监听器对象，备用
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JRadioButton radio = (JRadioButton) ae.getSource();
                String logtmp = "";
                if (radio == logjrb1) {
                	logtmp = " -v ";
                	
                   // System.out.println("You selected Radio 1");
                } else if (radio == logjrb2) {
                	logtmp = " -v -v ";
                	//System.out.println("You selected Radio 2");
                } else {
                	logtmp = " -v -v -v ";
                	//System.out.println("You selected Radio 3");
                }
                logline = logtmp;
            }
        };
		
        logjrb1.addActionListener(al);
        logjrb2.addActionListener(al);
        logjrb3.addActionListener(al);
		
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
		System.out.println("action listenter");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				String[] tmp = {"adb","shell","pm","list","package","-3","-i","com"};
//				CommandUtil.execute(tmp);
				//System.out.println("put down");
				stream = CommandUtil.execCommand("adb shell pm list package -s -i calc");
				
				List<String> packagelist =  CommandUtil.getPackage(stream);
				
				if(packagelist == null || packagelist.size() == 0){
					return;
				}
				
				int count = packagelist.size();
				
				for(int i = 0; i < count; i++){
					jcb.addItem(packagelist.get(i));
				}
			}
		});
		
		jcb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				packageline = " -p "+ jcb.getSelectedItem().toString().trim();
				
			}
		});
		

		//运行种子 seed
		jl2=new JLabel("随机种子：");
		jtf1=new JTextField(10);
		jcb1 = new JCheckBox("随机运行");
		jp3.add(jl2);
		jp3.add(jcb1);
		jp3.add(jtf1);
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp3);
		jcb1.setSelected(true);
		jtf1.setEditable(false);
		
		jcb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox tmpjcb = (JCheckBox) e.getSource();
				
				if(tmpjcb.isSelected()){
					jtf1.setEditable(false);
					jtf1.setText("");
					seedline = "";
				}
				else{
					jtf1.setEditable(true);
					
				}
			}
		});
		
		jtf1.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }  
        });
		
		
		//间隔时间
		jl3=new JLabel("间隔时间：");
		jtf2=new JTextField(10);
		jcb2 = new JCheckBox("默认");
		jp4.add(jl3);
		jp4.add(jcb2);
		jp4.add(jtf2);
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp4);
		
		jcb2.setSelected(true);
		jtf2.setEditable(false);
		jtf2.setText(timeline);
		
		jcb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox tmpjcb = (JCheckBox) e.getSource();
				//复选框，选中
				if(tmpjcb.isSelected()){
					jtf2.setEditable(false);
					jtf2.setText("1000");
					timeline = "1000";
				}
				else{
					jtf2.setEditable(true);
					jtf2.setText("");
					timeline = "";
				}
			}
		});
		
		jtf2.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }  
        });
		

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
		
		jcb3.addItemListener(new MyItemListener());
		jcb4.addItemListener(new MyItemListener());
		jcb5.addItemListener(new MyItemListener());
		jcb6.addItemListener(new MyItemListener());
		jcb7.addItemListener(new MyItemListener());

		
		
		
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
		
		js1.setValue(80);
		js2.setValue(20);
		js3.setValue(0);
		js4.setValue(0);
		js5.setValue(0);
		js6.setValue(0);
		
		js1.addChangeListener(new MyChageListener());
		js2.addChangeListener(new MyChageListener());
		js3.addChangeListener(new MyChageListener());
		js4.addChangeListener(new MyChageListener());
		js5.addChangeListener(new MyChageListener());
		js6.addChangeListener(new MyChageListener());
		
		
		
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
		
		jl5 = new JLabel("事件数量: ");
		jcb11 = new JCheckBox("默认");
		jtf3 = new JTextField(16);
		jp8.add(jl5);
		jp8.add(jcb11);
		jp8.add(jtf3);
		jp8.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp8);
		
		jcb11.setSelected(true);
		jtf3.setEditable(false);
		jtf3.setText(runline);
		
		jcb11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox tmpjcb = (JCheckBox) e.getSource();
				//复选框，选中
				if(tmpjcb.isSelected()){
					jtf3.setEditable(false);
					jtf3.setText("1000");
					runline = "1000";
				}
				else{
					jtf3.setEditable(true);
					jtf3.setText("");
					runline = "";
				}
			}
		});
		
		jtf3.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }  
        });

		jb5 = new JButton("Run");
		jb6 = new JButton("Close");
		jb7 = new JButton("Stop");

		this.add(jb5);
		this.add(jb6);
		this.add(jb7);
		
		jb6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			System.out.println("close");
				 System.exit(0);
			}
		});
		
		
		
		jb5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(jtf1.isEditable() && !jtf1.getText().equals("")){
					seedline = " -s " + jtf1.getText();
				}
				else if(jtf1.isEditable() && jtf1.getText().equals("")){
					seedline = "";
				}
				
				//间隔时间必须有，默认为1000（1秒）
				if(jtf2.isEditable() && !jtf2.getText().equals("")){
					timeline = " --throttle " + jtf2.getText();
				}
				//输入框不可写，内容不为空
				else if(!jtf2.isEditable() && jtf2.getText().equals("1000")){
					timeline = " --throttle 1000";
				}
				else if(jtf2.isEditable() && jtf2.getText().equals("")){
					timeline = " --throttle 1000";
				}
				
				
				//事件次数
				if(jtf3.isEditable() && !jtf3.getText().equals("")){
					runline = " " + jtf3.getText();
				}
				//输入框不可写，内容不为空
				else if(!jtf3.isEditable() && jtf3.getText().equals("1000")){
					runline = " " + jtf3.getText();
				}
				else if(jtf3.isEditable() && jtf3.getText().equals("")){
					runline = " 1000";
				}
				
				debugline = getDebugStr();
				//System.out.println("---------next()-----------");
				System.out.println(cmdline + logline + packageline + seedline + timeline + debugline + runline);
				CommandUtil.execCommand(cmdline + logline + packageline + seedline + timeline + debugline + runline);
			}
			
		});
		
		
		
		
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
		this.setLocation(100, 50);
		//设置当关闭窗口时，保证JVM也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示窗体
		this.setVisible(true);
		this.setResizable(false);
		
	}

	
	class MyItemListener implements ItemListener {  

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox source = (JCheckBox)e.getItemSelectable();
			 if(source == jcb3){
				debuglist[0]=changeNumber(source.isSelected());
			 }else if(source == jcb4){
				 debuglist[1]=changeNumber(source.isSelected());
			 }else if(source == jcb5){
				 debuglist[2]=changeNumber(source.isSelected());
			 }else if(source == jcb6){
				 debuglist[3]=changeNumber(source.isSelected());
			 }else if(source == jcb7){
				 debuglist[4]=changeNumber(source.isSelected());
			 }
		} 
	}
	
	
	class MyChageListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider js0 =  (JSlider)e.getSource();
			if(js0 == js1){
				
			}else if(js0 == js2){
				
			}else if(js0 == js3){
				
			}else if(js0 == js4){
				
			}else if(js0 == js5){
				
			}else if(js0 == js6){
				
			}
		}
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		
		if(e.getActionCommand()==" Run ")
		{

			
		}
		else if(e.getActionCommand()=="Close")
		{
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}	
		
	}
	

	
	public int changeNumber(boolean bl){
		
		if(bl == true){
			return 0;
		}else{
			return 1;
		}
	}

	

	public List<String> getDebugList(){
		int cnt = debuglist.length;
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i< cnt; i++){
			if(debuglist[i] == 0){
				list.add(debugStrList[i]);
			}
		}
		
		for(String str : list){
			System.out.println("str: " + str);
		}
		
		return list;
	}
	
	public String getDebugStr(){
		int cnt = debuglist.length;
		String tmp =" ";
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i< cnt; i++){
			if(debuglist[i] == 0){
				list.add(debugStrList[i]);
			}
		}
		
		if(list.size() == 0){
			return "";
		}
		
		for(String str : list){
			tmp = tmp + str + " ";
		}
		System.out.println("tmp: " + tmp);
		return tmp;
	}
}
