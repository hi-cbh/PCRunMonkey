package com.test.testcode;
//package com.test.thread;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.test.javamonkey.CommandUtil;
//
///**
// * Õ£÷πMonkey‘À––
// * @author Administrator
// *
// */
//public class MyThreadKillMonkey extends Thread {
//	String cmdline = "";
//	List<String> alist = null;
//
//	public MyThreadKillMonkey(String pidline) {
//		cmdline = pidline;
//	}
//
//	@Override
//	public void run() {
//		List<String> alist = new ArrayList<String>();
//
//		alist = CommandUtil.execCommand(cmdline);
//
//		if(alist == null){
//			return;
//		}
//		
//		
//		for (String s : alist) {
//			System.out.println(s);
//			if(s.contains("shell")){
//				String[] words = s.split("\\s+");
//				System.out.println(words[1]);
//				CommandUtil.execCommand("adb shell kill -9 " + words[1]);
//				System.out.println("kill");
//			}
//		}
//		
//
//		
//	}
//	
//
//}
