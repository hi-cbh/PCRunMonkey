package com.test.javamonkey;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

/*
 * TestButtons.java
 * @author Fancy
 */



public final class TestButtons {

    public static void main(String[] args) {
        TestButtons tb = new TestButtons();
        tb.show();
    }

    JFrame frame = new JFrame("Monkey��������");

    JButton jButton = new JButton("JButton"); // ��ť

    JToggleButton toggle = new JToggleButton("Toggle Button"); // �л���ť

    JCheckBox checkBox = new JCheckBox("Check Box"); // ��ѡ��ť

    JRadioButton radio1 = new JRadioButton("Radio Button 1"); // ��ѡ��ť

    JRadioButton radio2 = new JRadioButton("Radio Button 2");

    JRadioButton radio3 = new JRadioButton("Radio Button 3");

    JLabel label = new JLabel("Here is Status, look here."); // ���ǰ�ť���Ǿ�̬�ı�

    public TestButtons() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new java.awt.FlowLayout());

        // Ϊһ�㰴ť��Ӷ���������
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                label.setText("You clicked jButton");
//                System.out.println("run");
//                try {
//        			//CommandUtil.execCommand2("adb shell monkey -v -p com.sec.android.app.popupcalculator 100");
//        		} catch (IOException e) {
//        			// TODO Auto-generated catch block
//        			e.printStackTrace();
//        		}
            }
        });

        // Ϊ�л���ť��Ӷ���������
        toggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JToggleButton toggle = (JToggleButton) ae.getSource();
                if (toggle.isSelected()) {
                    label.setText("You selected Toggle Button");
                } else {
                    label.setText("You deselected Toggle Button");
                }
            }
        });

        // Ϊ��ѡ��ť�����Ŀ������
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();
                label.setText("Selected Check Box is " + cb.isSelected());
            }
        });

        // ��һ����ť��������һ�鵥ѡ��ť
        ButtonGroup group = new ButtonGroup();
        // ����һ���µĶ������������󣬱���
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JRadioButton radio = (JRadioButton) ae.getSource();
                if (radio == radio1) {
                    label.setText("You selected Radio Button 1");
                } else if (radio == radio2) {
                    label.setText("You selected Radio Button 2");
                } else {
                    label.setText("You selected Radio Button 3");
                }
            }
        };
        // Ϊ����ѡ��ť��Ӷ���������
        radio1.addActionListener(al);
        radio2.addActionListener(al);
        radio3.addActionListener(al);
        // ����ѡ��ť��ӵ���ť����
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);

        frame.getContentPane().add(jButton);
        frame.getContentPane().add(toggle);
        frame.getContentPane().add(checkBox);
        frame.getContentPane().add(radio1);
        frame.getContentPane().add(radio2);
        frame.getContentPane().add(radio3);
        frame.getContentPane().add(label);
        
        frame.setResizable(false);
        frame.setSize(400, 600);
    }

    public void show() {
        frame.setVisible(true);

    }

}