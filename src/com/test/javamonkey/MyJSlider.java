package com.test.javamonkey;

import javax.swing.JSlider;

public class MyJSlider extends JSlider {

	private static JSlider	js1;
	
	
	public static JSlider getMyJSlider(){
		js1 = new JSlider(0, 100);
		js1.setOrientation(JSlider.VERTICAL);
		js1.setPaintTicks(true);//���û�����ƿ̶ȱ��  
        js1.setPaintLabels(true); //�������̶ȱ�ǵ�״̬  
        js1.setMajorTickSpacing(50);//�������̶ȱ�ǵļ��  
       // js1.setMinorTickSpacing(100);//���ø��̶ȱ�ǵļ�� 
        return js1;
	}
	
	
}
