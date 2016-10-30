package com.test.javamonkey;

import javax.swing.JSlider;

public class MyJSlider extends JSlider {

	private static JSlider	js1;
	
	
	public static JSlider getMyJSlider(){
		js1 = new JSlider(0, 100);
		js1.setOrientation(JSlider.VERTICAL);
		js1.setPaintTicks(true);//设置滑块绘制刻度标记  
        js1.setPaintLabels(true); //设置主刻度标记的状态  
        js1.setMajorTickSpacing(50);//设置主刻度标记的间隔  
       // js1.setMinorTickSpacing(100);//设置副刻度标记的间隔 
        return js1;
	}
	
	
}
