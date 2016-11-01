package com.test.javamonkey;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/***
 * 执行命令行工具类
 * 
 * @author yangxiaolong 2014-04-30
 * 
 */
public class CommandUtil {

	public static final String TAG = CommandUtil.class.getSimpleName();
	public static final String COMMAND_SH = "cmd /c";
	public static final String COMMAND_LINE_END = "\n";
	public static final String COMMAND_EXIT = "exit\n";

	/**
	 * 执行单条命令
	 * 
	 * @param command
	 * @return
	 */
	public static List<String> execute(String command) {
		return execute(new String[] { command });
	}

	/**
	 * 可执行多行命令（bat）
	 * 
	 * @param commands
	 * @return
	 */
	public static List<String> execute(String[] commands) {
		List<String> results = new ArrayList<String>();
		int status = -1;
		if (commands == null || commands.length == 0) {
			return null;
		}
		System.out.println("execute command start : " + commands);
		Process process = null;
		BufferedReader successReader = null;
		BufferedReader errorReader = null;
		StringBuilder errorMsg = null;

		DataOutputStream dos = null;
		try {
			// TODO
			process = Runtime.getRuntime().exec(COMMAND_SH);
			dos = new DataOutputStream(process.getOutputStream());
			for (String command : commands) {
				if (command == null) {
					continue;
				}
				dos.write(command.getBytes());
				dos.writeBytes(COMMAND_LINE_END);
				dos.flush();
			}
			dos.writeBytes(COMMAND_EXIT);
			dos.flush();

			status = process.waitFor();

			errorMsg = new StringBuilder();
			successReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			errorReader = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			String lineStr;
			while ((lineStr = successReader.readLine()) != null) {
				results.add(lineStr);
				System.out.println(" command line item : " + lineStr);
			}
			while ((lineStr = errorReader.readLine()) != null) {
				errorMsg.append(lineStr);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) {
					dos.close();
				}
				if (successReader != null) {
					successReader.close();
				}
				if (errorReader != null) {
					errorReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (process != null) {
				process.destroy();
			}
		}
		System.out.println(String.format(Locale.CHINA,
				"execute command end,errorMsg:%s,and status %d: ", errorMsg,
				status));
		return results;
	}

	public static void execCommand2(String command) {
		System.out.println("execCommand2");
		Runtime runtime = null;
		Process proc = null;

		try {

			runtime = Runtime.getRuntime();
			System.out.println("before");
			proc = runtime.exec(command);
			System.out.println("after");
			if (proc.waitFor() != 0) {
				System.err.println("exit value = " + proc.exitValue());
			}
			System.out.println("run");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			System.out.println("wait for");
			while ((line = in.readLine()) != null) {
				// stringBuffer.append(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				proc.destroy();
			} catch (Exception e2) {
			}
		}
	}

	public static List<String> execCommand(String command) {
		Runtime runtime = null;
		Process proc = null;
		WatchThread wt = null;
		ArrayList<String> commandStream = null;
		try {
			runtime = Runtime.getRuntime();
			proc = runtime.exec(command);

			wt = new WatchThread(proc);
			wt.start();

			if (proc.waitFor() != 0) {
				System.err.println("exit value = " + proc.exitValue());
			}
			commandStream = wt.getStream();
			wt.setOver(true);

			//System.out.println("commandStream: " + commandStream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				proc.destroy();
			} catch (Exception e2) {
			}
		}
		
		return commandStream;
	}

	public static class WatchThread extends Thread {
		Process p;
		boolean over;
		ArrayList<String> stream;

		public WatchThread(Process p) {
			this.p = p;
			over = false;
			stream = new ArrayList<String>();
		}

		public void run() {
			try {
				if (p == null)
					return;
				Scanner br = new Scanner(p.getInputStream());
				while (true) {
					if (p == null || over)
						break;
					while (br.hasNextLine()) {
						String tempStream = br.nextLine();
						if (tempStream.trim() == null
								|| tempStream.trim().equals(""))
							continue;
						stream.add(tempStream);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void setOver(boolean over) {
			this.over = over;
		}

		public ArrayList<String> getStream() {
			return stream;
		}
	}

	
	public static List<String> getPackage(List<String> stream){
		List<String> results = new ArrayList<String>();
		int status = -1;
		if (stream == null || stream.size() == 0) {
			return null;
		}
		
		for(String line : stream){
			
			if(line.contains("package:")){
				String str = line.substring(8, line.indexOf(" "));
				System.out.println("line : " + str);
				results.add(str);
			}
		}
		
		return results;
	}
	
	
}
