//package com.test.testcode;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.test.javamonkey.CommandUtil;
//
//public class MyThreadCmdLine extends Thread {
//	String cmdline = "";
//	List<String> alist = null;
//
//	public MyThreadCmdLine(String pidline) {
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
//		for (String s : alist) {
//			
//			System.out.println(s);
//
//		}
//	}
//	
//
//}
