package com.test.testcode;

public class TestCode {

	public static void main(String[] args) {
		String s = "shell     25987 400   463420 24232 ffffffff 400a19d8 S com.android.commands.monkey";
		String[] words = s.split("\\s+");
		System.out.println(words[1]);

	}

}
