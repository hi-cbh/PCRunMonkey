package com.test.javamonkey;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/***
 * ִ�������й�����
 * 
 * @author yangxiaolong 2014-04-30
 * 
 */
public class CommandUtil {

	public static final String TAG = CommandUtil.class.getSimpleName();
	public static final String COMMAND_SH = "";
	public static final String COMMAND_LINE_END = "\n";
	public static final String COMMAND_EXIT = "exit\n";


	/**
	 * ִ�е�������
	 * 
	 * @param command
	 * @return
	 */
	public static List<String> execute(String command) {
		return execute(new String[] { command });
	}

	/**
	 * ��ִ�ж������bat��
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

	
	
	public static void execCommand2(String command) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec(command);
		try {
			if (proc.waitFor() != 0) {
				System.err.println("exit value = " + proc.exitValue());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
				System.out.println(line);
			}
			

		} catch (InterruptedException e) {
			System.err.println(e);
		} finally {
			try {
				proc.destroy();
			} catch (Exception e2) {
			}
		}
	}

	

}
