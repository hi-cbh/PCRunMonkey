package com.test.javamonkey;

import java.awt.FlowLayout;
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


//import com.test.thread.MyThreadCmdLine;
//import com.test.thread.MyThreadKillMonkey;
import com.test.thread.MonkeyThread;

public class MainMonkey extends JFrame implements ActionListener {

	//布局
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10 = null;
	JRadioButton logjrb1, logjrb2, logjrb3 = null;
	ButtonGroup bg = null;
	JLabel Mainjl, logjl, jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10,
			jl11 = null;
	JComboBox<String> jcb = null;
	JButton add, jb1, jb2, jb3, jb4, jb5, jb6, jb7 = null;
	JTextField jtf1, jtf2, jtf3 = null;
	JCheckBox jcb1, jcb2, jcb3, jcb4, jcb5, jcb6, jcb7, jcb8, jcb9, jcb10,
			jcb11 = null;
	JSlider js1, js2, js3, js4, js5, js6, js7, js8 = null;
	Box slider = new Box(BoxLayout.Y_AXIS);

	//固定常量
	public static final String RUNTIME = "500"; // 间隔时间
	public static final String RUNCNT = "1000"; // 运行次数
	public static final String PIDLINE = "adb shell ps | grep com.android.commands.monkey";
	public static final String APPLINE = "adb shell pm list package -3 -i com";

	//运行命令的变量
	public static String cmdline = "adb shell monkey ";
	public static String logline = "-v -v -v";
	public static String packageline = "";
	public static String seedline = "";
	public static String timeline = RUNTIME; // 间隔时间默认0.5秒
	public static String debugline = "";
	public static String runline = RUNCNT;
	public static String evenLine = "";

	private static int a = 80;
	private static int b = 20;
	private static int c = 0;
	private static int d = 0;
	private static int f = 0;
	private static int g = 0;
	private static int abc1 = 0;
	private static int abc2 = 0;
	private static int abc3 = 0;
	private static int abc4 = 0;
	private static int abc5 = 0;
	private static int abc6 = 0;
	
	List<String> stream = null;
	List<String> bllist = null;
	static int debuglist[] = { 1, 1, 1, 1, 1 };

	private static int runCnt = 0;
//	MyThreadCmdLine startMonkey = null;
	//MyThreadKillMonkey stopline = null;
	
	MonkeyThread sm = new MonkeyThread();
	MyActionListener listener = new MyActionListener();
	AddActionListener addlistener = new AddActionListener();
	MyChageListener mcl = new MyChageListener();
	
	
	static String[] debugStrList = { "--ignore-crashes", "--ignore-timeouts",
			"--ignore-security-exceptions", "--kill-process-after-error",
			"--randomize-throttle" };

	static String[] eventStrList = {
		"--pct-touch",
		"--pct-motion",
		"--pct-trackball",
		"--pct-nav",
		"--pct-appswitch",
		"--pct-anyevent"
		};
	
	public static void main(String[] args) {

		new MainMonkey();
	}

	/**
	 * 构成函数
	 */
	public MainMonkey() {

		// 面板声明
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
		jp8 = new JPanel();
		jp9 = new JPanel();
		jp10 = new JPanel();
		// 标签
		Mainjl = new JLabel("Monkey测试");
		this.add(Mainjl);

		// 日志布局
		logjl = new JLabel("日志级别");
		// 日志级别
		logjrb1 = new JRadioButton("基本LOG");
		logjrb2 = new JRadioButton("详细LOG");
		logjrb3 = new JRadioButton("全部LOG");
		bg = new ButtonGroup();
		bg.add(logjrb1);
		bg.add(logjrb2);
		bg.add(logjrb3);
		logjrb3.setSelected(true);

		// 添加侦听

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
					// System.out.println("You selected Radio 2");
				} else {
					logtmp = " -v -v -v ";
					// System.out.println("You selected Radio 3");
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

		// 运行的包名
		// 运行包名
		jl1 = new JLabel("Package: ");
		jcb = new JComboBox<String>();
		add = new JButton("选择");
		jp2.add(jl1);
		jp2.add(jcb);
		jp2.add(add);
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp2);
		System.out.println("action listenter");

		//add Action Listener
		add.addActionListener(listener);


		// 运行种子 seed
		jl2 = new JLabel("随机种子：");
		jtf1 = new JTextField(10);
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

				if (tmpjcb.isSelected()) {
					jtf1.setEditable(false);
					jtf1.setText("");
					seedline = "";
				} else {
					jtf1.setEditable(true);

				}
			}
		});

		jtf1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});

		// 间隔时间
		jl3 = new JLabel("间隔时间：");
		jtf2 = new JTextField(10);
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
				// 复选框，选中
				if (tmpjcb.isSelected()) {
					jtf2.setEditable(false);
					jtf2.setText(RUNTIME);
					timeline = RUNTIME;
				} else {
					jtf2.setEditable(true);
					jtf2.setText("");
					timeline = "";
				}
			}
		});

		jtf2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 关键，屏蔽掉非法输入
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
		jp5.setLayout(new GridLayout(3, 2));
		this.add(jp5);

		
		jcb3.addItemListener(new MyItemListener());
		jcb4.addItemListener(new MyItemListener());
		jcb5.addItemListener(new MyItemListener());
		jcb6.addItemListener(new MyItemListener());
		jcb7.addItemListener(new MyItemListener());

		jp6.setBorder(BorderFactory.createTitledBorder("事件百分比"));

		jp6.setLayout(new BoxLayout(jp6, BoxLayout.Y_AXIS));

		js1 = MyJSlider.getMyJSlider();
		js2 = MyJSlider.getMyJSlider();
		js3 = MyJSlider.getMyJSlider();
		js4 = MyJSlider.getMyJSlider();
		js5 = MyJSlider.getMyJSlider();
		js6 = MyJSlider.getMyJSlider();
		js7 = MyJSlider.getMyJSlider();
		js8 = MyJSlider.getMyJSlider();

		int x = 5;
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
		jp9.setLayout(new BoxLayout(jp9, BoxLayout.X_AXIS));
		
		
		
		js1.setValue(20);
		js2.setValue(30);
		js3.setValue(30);
		js4.setValue(0);
		js5.setValue(0);
		js6.setValue(0);

		js1.addChangeListener(mcl);
		js2.addChangeListener(mcl);
		js3.addChangeListener(mcl);
		js4.addChangeListener(mcl);
		js5.addChangeListener(mcl);
		js6.addChangeListener(mcl);

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
		jp10.setLayout(new BoxLayout(jp10, BoxLayout.X_AXIS));

		jp6.add(jp9);
		jp6.add(jp10);
		this.add(jp6);
		
		// 快捷方案
		jl4 = new JLabel("快捷测试方案：");
		jb1 = new JButton("方案一");
		jb2 = new JButton("方案二");
		jb3 = new JButton("方案三");
		jp7.add(jl4);
		jp7.add(jb1);
		jp7.add(jb2);
		jp7.add(jb3);
		jp7.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(jp7);

		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//日志
				logjrb2.setSelected(true);
				//seed
				jcb1.setSelected(true);
				jtf1.setText("");
				//间隔时间
				jcb2.setSelected(false);
				jtf2.setText("1000");
				//调试
				jcb3.setSelected(true);
				jcb4.setSelected(true);
				jcb5.setSelected(false);
				jcb6.setSelected(false);
				jcb7.setSelected(false);

				//事件百分比
				js1.setValue(90);
				js2.setValue(5);
				js3.setValue(5);
				js4.setValue(0);
				js5.setValue(0);
				js6.setValue(0);
				
				//事件
				jcb11.setSelected(false);
				jtf3.setText("300000");
				

			}
		});
		
		
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//日志
				logjrb3.setSelected(true);
				//seed
				jcb1.setSelected(false);
				jtf1.setText("48373");
				//间隔时间
				jcb2.setSelected(false);
				jtf2.setText("2000");
				//调试
				jcb3.setSelected(true);
				jcb4.setSelected(true);
				jcb5.setSelected(false);
				jcb6.setSelected(false);
				jcb7.setSelected(true);

				//事件百分比
				js1.setValue(60);
				js2.setValue(20);
				js3.setValue(20);
				js4.setValue(0);
				js5.setValue(0);
				js6.setValue(0);
				
				//事件
				jcb11.setSelected(false);
				jtf3.setText("200000");
				
			}
		});
		
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//日志
				logjrb3.setSelected(true);
				//seed
				jcb1.setSelected(false);
				jtf1.setText("2345346");
				//间隔时间
				jcb2.setSelected(false);
				jtf2.setText("1500");
				//调试
				jcb3.setSelected(true);
				jcb4.setSelected(true);
				jcb5.setSelected(true);
				jcb6.setSelected(true);
				jcb7.setSelected(true);

				//事件百分比
				js1.setValue(50);
				js2.setValue(10);
				js3.setValue(10);
				js4.setValue(10);
				js5.setValue(10);
				js6.setValue(10);
				
				//事件
				jcb11.setSelected(false);
				jtf3.setText("100000");
				
				
			}
		});
		
		
		
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
				// 复选框，选中
				if (tmpjcb.isSelected()) {
					jtf3.setEditable(false);
					jtf3.setText(RUNCNT);
					runline = RUNCNT;
				} else {
					jtf3.setEditable(true);
					jtf3.setText("");
					runline = "";
				}
			}
		});

		jtf3.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 关键，屏蔽掉非法输入
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

		//运行脚本
		jb5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (jtf1.isEditable() && !jtf1.getText().equals("")) {
					seedline = " -s " + jtf1.getText();
				} else if (jtf1.isEditable() && jtf1.getText().equals("")) {
					seedline = "";
				}

				// 间隔时间必须有，默认为1000（1秒）
				if (jtf2.isEditable() && !jtf2.getText().equals("")) {
					timeline = " --throttle " + jtf2.getText();
				}
				// 输入框不可写，内容不为空
				else if (!jtf2.isEditable() && jtf2.getText().equals(RUNTIME)) {
					timeline = " --throttle " + RUNTIME;
				} else if (jtf2.isEditable() && jtf2.getText().equals("")) {
					timeline = " --throttle " + RUNTIME;
				}

				// 事件次数
				if (jtf3.isEditable() && !jtf3.getText().equals("")) {
					runline = " " + jtf3.getText();
				}
				// 输入框不可写，内容不为空
				else if (!jtf3.isEditable() && jtf3.getText().equals(RUNCNT)) {
					runline = " " + jtf3.getText();
				} else if (jtf3.isEditable() && jtf3.getText().equals("")) {
					runline = " " + RUNCNT;
				}

				debugline = getDebugStr();
				// System.out.println("---------next()-----------");
				//System.out.println(cmdline + logline + packageline + seedline+ timeline + debugline + runline);
				evenLine =  getEventStr();
				
				sm.monkeyRun(cmdline + logline + packageline+ seedline + timeline + debugline + runline + evenLine);
			}

		});

		//停止
		jb7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sm.monkeyStopRun();
				System.out.println("stop");
			}
		});

		// 设置布局
		this.setLayout(null);

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

		// 设置标题
		this.setTitle("Monkey 传参工具");
		// 设置窗体大小
		this.setSize(400, 600);
		// 设置窗体初始位置
		this.setLocation(800, 50);
		// 设置当关闭窗口时，保证JVM也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 显示窗体
		this.setVisible(true);
		this.setResizable(false);

	}

	

	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			stream = CommandUtil
					.execCommand(APPLINE); 

			List<String> packagelist = CommandUtil.getPackage(stream);

			if (packagelist == null || packagelist.size() == 0) {
				return;
			}

			int count = packagelist.size();

			
			if(runCnt != 0 ){
				int itemcnt = jcb.getItemCount();
				System.out.println("jcb.getItemCount() " + itemcnt);
				jcb.removeActionListener(addlistener);
				//jcb.removeItemAt(0);
				
				for(int tmp = 0; tmp < itemcnt; tmp++){
					System.out.println("tmp: " + tmp);
					//每次都清除第一位
					jcb.removeItemAt(0);
				}
			}
			//第一次运行
			runCnt++;
			jcb.addActionListener(addlistener);
			
			for (int i = 0; i < count; i++) {
				jcb.addItem(packagelist.get(i));
			}
		}
		
	}
	
	class AddActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			packageline = " -p " + jcb.getSelectedItem().toString().trim();

		}
	}
	
	
	
	
	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox source = (JCheckBox) e.getItemSelectable();
			if (source == jcb3) {
				debuglist[0] = changeNumber(source.isSelected());
			} else if (source == jcb4) {
				debuglist[1] = changeNumber(source.isSelected());
			} else if (source == jcb5) {
				debuglist[2] = changeNumber(source.isSelected());
			} else if (source == jcb6) {
				debuglist[3] = changeNumber(source.isSelected());
			} else if (source == jcb7) {
				debuglist[4] = changeNumber(source.isSelected());
			}
		}
	}

	class MyChageListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider js0 = (JSlider) e.getSource();
			a = js1.getValue();
			b = js2.getValue();
			c = js3.getValue();
			d = js4.getValue();
			f = js5.getValue();
			g = js6.getValue();
			
			int [] il = new int [6];
			
			if (js0 == js1) {
				js2.removeChangeListener(mcl);
				js3.removeChangeListener(mcl);
				js4.removeChangeListener(mcl);
				js5.removeChangeListener(mcl);
				js6.removeChangeListener(mcl);
				il[0] = a;
				il[1] = b;
				il[2] = c;
				il[3] = d;
				il[4] = f;
				il[5] = g;
				
				if(!addState(abc1, a)){
					return;
				}
				changeState(il);
				
				js1.setValue(il[0]);
				js2.setValue(il[1]);
				js3.setValue(il[2]);
				js4.setValue(il[3]);
				js5.setValue(il[4]);
				js6.setValue(il[5]);

				js2.addChangeListener(mcl);
				js3.addChangeListener(mcl);
				js4.addChangeListener(mcl);
				js5.addChangeListener(mcl);
				js6.addChangeListener(mcl);
				
				abc1 = 0;
				abc2 = 0;
				abc3 = 0;
				abc4 = 0;
				abc5 = 0;
				abc6 = 0;
				
			} else if (js0 == js2) {

				js1.removeChangeListener(mcl);
				js3.removeChangeListener(mcl);
				js4.removeChangeListener(mcl);
				js5.removeChangeListener(mcl);
				js6.removeChangeListener(mcl);
				il[1] = a;
				il[0] = b;
				il[2] = c;
				il[3] = d;
				il[4] = f;
				il[5] = g;
				
				if(!addState(abc2, b)){
					return;
				}
				changeState(il);

				js1.setValue(il[1]);
				js2.setValue(il[0]);
				js3.setValue(il[2]);
				js4.setValue(il[3]);
				js5.setValue(il[4]);
				js6.setValue(il[5]);
				
				js1.addChangeListener(mcl);
				js3.addChangeListener(mcl);
				js4.addChangeListener(mcl);
				js5.addChangeListener(mcl);
				js6.addChangeListener(mcl);
				abc1 = 0;
				abc2 = 0;
				abc3 = 0;
				abc4 = 0;
				abc5 = 0;
				abc6 = 0;
			} else if (js0 == js3) {

				js1.removeChangeListener(mcl);
				js2.removeChangeListener(mcl);
				js4.removeChangeListener(mcl);
				js5.removeChangeListener(mcl);
				js6.removeChangeListener(mcl);
				il[2] = a;
				il[1] = b;
				il[0] = c;
				il[3] = d;
				il[4] = f;
				il[5] = g;
				
				if(!addState(abc3, c)){
					return;
				}
				changeState(il);

				js1.setValue(il[2]);
				js2.setValue(il[1]);
				js3.setValue(il[0]);
				js4.setValue(il[3]);
				js5.setValue(il[4]);
				js6.setValue(il[5]);
				
				js1.addChangeListener(mcl);
				js2.addChangeListener(mcl);
				js4.addChangeListener(mcl);
				js5.addChangeListener(mcl);
				js6.addChangeListener(mcl);
				abc1 = 0;
				abc2 = 0;
				abc3 = 0;
				abc4 = 0;
				abc5 = 0;
				abc6 = 0;
			} else if (js0 == js4) {

				js1.removeChangeListener(mcl);
				js2.removeChangeListener(mcl);
				js3.removeChangeListener(mcl);
				js5.removeChangeListener(mcl);
				js6.removeChangeListener(mcl);
				il[3] = a;
				il[1] = b;
				il[2] = c;
				il[0] = d;
				il[4] = f;
				il[5] = g;
				
				if(!addState(abc4, d)){
					return;
				}
				changeState(il);

				js1.setValue(il[3]);
				js2.setValue(il[1]);
				js3.setValue(il[2]);
				js4.setValue(il[0]);
				js5.setValue(il[4]);
				js6.setValue(il[5]);
				
				js1.addChangeListener(mcl);
				js2.addChangeListener(mcl);
				js3.addChangeListener(mcl);
				js5.addChangeListener(mcl);
				js6.addChangeListener(mcl);
				abc1 = 0;
				abc2 = 0;
				abc3 = 0;
				abc4 = 0;
				abc5 = 0;
				abc6 = 0;
				
			} else if (js0 == js5) {

				js1.removeChangeListener(mcl);
				js2.removeChangeListener(mcl);
				js3.removeChangeListener(mcl);
				js4.removeChangeListener(mcl);
				js6.removeChangeListener(mcl);
				il[4] = a;
				il[1] = b;
				il[2] = c;
				il[3] = d;
				il[0] = f;
				il[5] = g;
				
				if(!addState(abc5, f)){
					return;
				}
				changeState(il);

				js1.setValue(il[4]);
				js2.setValue(il[1]);
				js3.setValue(il[2]);
				js4.setValue(il[3]);
				js5.setValue(il[0]);
				js6.setValue(il[5]);
				
				js1.addChangeListener(mcl);
				js2.addChangeListener(mcl);
				js3.addChangeListener(mcl);
				js4.addChangeListener(mcl);
				js6.addChangeListener(mcl);
				abc1 = 0;
				abc2 = 0;
				abc3 = 0;
				abc4 = 0;
				abc5 = 0;
				abc6 = 0;
			} else if (js0 == js6) {

				js1.removeChangeListener(mcl);
				js2.removeChangeListener(mcl);
				js3.removeChangeListener(mcl);
				js4.removeChangeListener(mcl);
				js5.removeChangeListener(mcl);
				il[5] = a;
				il[1] = b;
				il[2] = c;
				il[3] = d;
				il[4] = f;
				il[0] = g;
				
				if(!addState(abc6, g)){
					return;
				}
				changeState(il);

				js1.setValue(il[5]);
				js2.setValue(il[1]);
				js3.setValue(il[2]);
				js4.setValue(il[3]);
				js5.setValue(il[4]);
				js6.setValue(il[0]);
				
				js1.addChangeListener(mcl);
				js2.addChangeListener(mcl);
				js3.addChangeListener(mcl);
				js4.addChangeListener(mcl);
				js5.addChangeListener(mcl);
				
				abc1 = 0;
				abc2 = 0;
				abc3 = 0;
				abc4 = 0;
				abc5 = 0;
				abc6 = 0;
			}
			
			

		}

	}

	public boolean addState(int num1, int num2){
		//增量的时候运行，非增量的时候不运行
		if(num1 == 0 ){
			num1 = num2;
		}else if(num1 > num2){
			//System.out.println("减法---------");
			return false;
		}else{
			num1 = num2;
		}
		
		return true;
	}
	
	
	
	public void changeState(int il[]){
		int n = 0; //为0的个数
		int counti = 0;
		int last = 0; //剩余
		
		for(int i=0;i < 6; i++){
			if(il[i] ==0){
				n++;
				continue;
			}
			counti = counti + il[i];
		}
		
		//System.out.println("counti: " + counti);
		//System.out.println("当前为0个数：" + n);
		//总数超过100
		if(counti > 100){
			
			int lasti = (counti - 100)%(5-n);
			last = (counti - 100)/(5-n);
			int nn = (5-n);
			//等于0时
			if(last == 0){
				last = lasti;
				lasti = 0;
			}
			
			//System.out.println("last0: " + last);
			//System.out.println("lasti0: " + lasti);
			
			if(nn == 0)
			{
				return;
			}
			//平均不足
			if(last < nn){
				for(int i0=1; i0<6; i0++){
					if(il[i0] == 0){
						continue;
					}
					if(il[i0] < last){
						last = il[i0] - last;
						il[i0] = 0;
					}
					else if(il[i0] > last){
						il[i0] = il[i0] - last;
					}
					
					if(last == 0){
						return;
					}
				}
			}
			
			//平均数大于1
			for(int ii =1; ii< 6; ii++){
//				System.out.println("last: " + last);
//				System.out.println("lasti: " + lasti);	
				if(il[ii] == 0){
					
					continue;
				}
				if(il[ii] >= last){
					il[ii] = il[ii] - last;
					//System.out.println("run: " + il[ii]);
				}else if(il[ii] < last){
					lasti = last - il[ii];
					il[ii] = 0;
				}
				
				if(lasti >= 1){
//					System.out.println("lastiii: lasti: " + lasti);
//					System.out.println("lastiii: ii: " + ii);
//					System.out.println("lastiii: n: " + n);
//					System.out.println("lastiii: " + ((5 - n) - (5-ii)));
//					System.out.println("lastiii: " + (ii-n));
					if(ii-n == 0){
						continue;
					}
					last = last + lasti/(ii-n);
					
				}
			}
		}
//		int cc = 0;
//		for(int iii: il){
//			System.out.println("iii: " + iii);
//			cc = cc + iii;
//			
//		}
		

	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == " Run ") {

		} else if (e.getActionCommand() == "Close") {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}

	public int changeNumber(boolean bl) {

		if (bl == true) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public List<String> getDebugList() {
		int cnt = debuglist.length;
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < cnt; i++) {
			if (debuglist[i] == 0) {
				list.add(debugStrList[i]);
			}
		}

		for (String str : list) {
			System.out.println("str: " + str);
		}

		return list;
	}

	public String getDebugStr() {
		int cnt = debuglist.length;
		String tmp = " ";
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < cnt; i++) {
			if (debuglist[i] == 0) {
				list.add(debugStrList[i]);
			}
		}

		if (list.size() == 0) {
			return "";
		}

		for (String str : list) {
			tmp = tmp + str + " ";
		}
		System.out.println("tmp: " + tmp);
		return tmp;
	}
	
	public String getEventStr(){
		
		int tmp[] = new int[6];
		
		tmp[0] = js1.getValue();
		tmp[1] = js2.getValue();
		tmp[2] = js3.getValue();
		tmp[3] = js4.getValue();
		tmp[4] = js5.getValue();
		tmp[5] = js6.getValue();
		
		String str = " ";
		
		for(int i=0; i<6; i++){
			str = str + eventStrList[i] + " " + tmp[i] + " ";
		}
		

		return str;
	}
}
