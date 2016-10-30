package com.test.javamonkey;

/*
 * ���ܣ�ѧ���ɼ�����ϵͳ
 * ����1����¼����ľ�̬ʵ��
 * ����2����ӶԸ�������ļ�����
 * ����3�����û��������������֤��
 * author��ywq
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

	//�������
	JButton jb1,jb2, addAppList=null;
	JRadioButton logjrb1,logjrb2,logjrb3=null;
	JPanel jp1,jp2,jp3,jp4=null;
	JTextField jtf, appjtf=null;
	JLabel jlb1,jlb2,logjlb=null;
	JPasswordField jpf=null;
	ButtonGroup bg=null;
	JComboBox<String> jcb = null;
	
	String [] applist = {
			"null",
			"testdata",
			"NULL",
			"haha"
	};
	int count = 0;
	
	//�趨�û���������
	final String stu_name="6";
	final String stu_pwd="1";
	final String tea_name="5";
	final String tea_pwd="1";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login  ms=new Login();
	}
	public Login()
	{
		 //�������
		jb1=new JButton("��¼");
		jb2=new JButton("����");
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		logjrb1=new JRadioButton("����LOG");
		logjrb2=new JRadioButton("��ϸLOG");
		logjrb3=new JRadioButton("ȫ��LOG");
		bg=new ButtonGroup();
		bg.add(logjrb1);
		bg.add(logjrb2);
		bg.add(logjrb3);
		logjrb3.setSelected(true);
		
		//�����б�
		jcb = new JComboBox<String>();
		addAppList = new JButton("��ȡӦ���б�");

		for(int i = 0; i < 2;i++){
			jcb.addItem(applist[count++]);
		}
			
		appjtf=new JTextField(20);
		
		appjtf.setEditable(false);
		
		addAppList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(count < applist.length){
					jcb.addItem(applist[count++]);
					
				}
			}
		});
		jcb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				appjtf.setText("��ѡ���� "+ jcb.getSelectedItem());
			}
		});
		jcb.setEditable(true);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();				
		
		jlb1=new JLabel("Run APP: ");
		jlb2=new JLabel("��    �룺");
		logjlb=new JLabel("��־����");
		
		jtf=new JTextField(10);
		jpf=new JPasswordField(10);
		//���뵽JPanel��

		//��־����
		jp3.add(logjlb);
		jp3.add(logjrb1);
		jp3.add(logjrb2);
		jp3.add(logjrb3);

		jp1.add(jlb1);
		jp1.add(appjtf);
		jp1.add(jcb);
		jp1.add(addAppList);

		
		
		jp2.add(jlb2);
		jp2.add(jpf);
		
		
		jp4.add(jb1);
		jp4.add(jb2);
		
		//����JFrame��
		
		this.add(jp3);
		this.add(jp1);
		this.add(jp2);
		
		this.add(jp4);
		//���ò��ֹ�����
		this.setLayout(new GridLayout(4,1));
		//���������ñ���
		this.setTitle("Monkey ���ι���");
		//���ô����С
		this.setSize(400,600);
		//���ô����ʼλ��
		this.setLocation(200, 150);
		//���õ��رմ���ʱ����֤JVMҲ�˳�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʾ����
		this.setVisible(true);
		this.setResizable(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand()=="��¼")
		{
			//���ѡ�н�ʦ��¼
			if(logjrb1.isSelected())
			{
                  tealogin();
			}else if(logjrb2.isSelected()) //ѧ���ڵ�¼ϵͳ
			{
                  stulogin();
			}
			
		}else if(e.getActionCommand()=="����")
		{
                  clear();
		}			
		
	}
	
	 //ѧ����¼�жϷ���
	public void stulogin()
	{
		if(stu_name.equals(jtf.getText())&&stu_pwd.equals(jpf.getText()))
		{
//			System.out.println("��¼�ɹ�");
			JOptionPane.showMessageDialog(null,"��¼�ɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			clear();
		}else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		}else if(jtf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		}else if(jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		}else
		{
			JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
		    //��������
			clear();
		}
	}
	//��ʦ��¼�жϷ���
	public void tealogin()
	{
		if(tea_name.equals(jtf.getText())&&tea_pwd.equals(jpf.getText()))
		{
//			System.out.println("��¼�ɹ�");
			 JOptionPane.showMessageDialog(null,"��¼�ɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			 clear();
		}else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		}else if(jtf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		}else if(jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		}else
		{
			JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
		    //��������
			clear();
		}
	}
	//����ı���������
	public	void clear()
	{
		jtf.setText("");
		jpf.setText("");
	}
		
}