package com.test.thread;

import java.util.ArrayList;
import java.util.List;

import com.test.javamonkey.CommandUtil;
import com.test.javamonkey.MainMonkey;

public class MonkeyThread {

	MyThreadKillMonkey st =null;
	MyThreadCmdLine  mt = null;
	
	
	public void monkeyRun(String cmd){
		if(mt != null){
			monkeyStopRun();
			mt.stop();
			mt = null;
		}
		
		mt = new MyThreadCmdLine(cmd);
		mt.start();
		
	}
	
	public void monkeyStopRun(){
		if(st != null){
			st.stop();
			st = null;
		}
		st = new MyThreadKillMonkey();
		st.start();
	}
	
	public void stop(){
		if(st != null){
			st.stop();
			st = null;
		}
		
		if(mt != null){
			mt.stop();
			mt = null;
		}
		
	}
	
	
	/**
	 * Õ£÷πMonkey‘À––
	 * 
	 * @author Administrator
	 * 
	 */
	public class MyThreadKillMonkey extends Thread {

		List<String> alist = null;

		@Override
		public void run() {
			List<String> alist = new ArrayList<String>();

			alist = CommandUtil.execCommand(MainMonkey.PIDLINE);

			if (alist == null) {
				return;
			}

			for (String s : alist) {
				System.out.println(s);
				if (s.contains("shell")) {
					String[] words = s.split("\\s+");
					System.out.println(words[1]);
					CommandUtil.execCommand("adb shell kill -9 " + words[1]);
					System.out.println("kill");
				}
			}

		}

	}
	
	
	public class MyThreadCmdLine extends Thread {
		String cmdline = "";
		List<String> alist = null;

		public MyThreadCmdLine(String pidline) {
			cmdline = pidline;
		}

		@Override
		public void run() {
			List<String> alist = new ArrayList<String>();

			System.out.println("runing: " + cmdline);
			alist = CommandUtil.execCommand(cmdline);

			if(alist == null){
				return;
			}
			
			for (String s : alist) {
				
				System.out.println(s);

			}
		}
		

	}

}
