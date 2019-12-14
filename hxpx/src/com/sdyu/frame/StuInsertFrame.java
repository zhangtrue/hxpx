package com.sdyu.frame;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

//������ݴ���

public class StuInsertFrame extends JFrame implements ActionListener {
	JTextField input1, input2, input3, input4;
	JLabel label1, label2, label3, label4, label5;
	JButton button;
	StuBaseInfoFrame stuFrame;

	public StuInsertFrame(StuBaseInfoFrame stuFrame)	{
		this.stuFrame=stuFrame;
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("������ݴ���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width / 2;
		int screenHeight = screenSize.height / 2;
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth - width / 2, screenHeight - height / 2);
		setVisible(true);
	}
	
	public void init() throws Exception{
		input1 = new JTextField(15);
		input2 = new JTextField(15);
		input3 = new JTextField(15);
		input4 = new JTextField(15);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		panel.add(new JLabel("���"), BorderLayout.CENTER);
		panel.add(input1);
		panel.add(new JLabel("����"), BorderLayout.CENTER);
		panel.add(input2);
		panel.add(new JLabel("�ֻ�����"), BorderLayout.CENTER);
		panel.add(input3);
		panel.add(new JLabel("����"), BorderLayout.CENTER);
		panel.add(input4);
		
		
		button = new JButton("���");
		button.addActionListener(this);   //����¼�����
		Container container = getContentPane();
		container.add(panel, BorderLayout.CENTER);
		container.add(button, BorderLayout.SOUTH);
	}

	
	/*
	 * �¼�����
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String number = input1.getText().trim();
			String name = input2.getText().trim();
			String phonenumber = input3.getText().trim();
			String bumen = input4.getText().trim();
			if (number.equals("") | name.equals("") | phonenumber.equals("")
					| bumen.equals("")) {
				JOptionPane.showMessageDialog(StuInsertFrame.this, "����������",
						"��ʾ�Ի���", 1);
			} else {
				StuBean stu=new StuBean(number,name,phonenumber,bumen);
				StuBeanDao studao=new StuBeanDao();
				int result=studao.insertStu(stu);
				if(result!=0){
					Object[] rowData={number,name,phonenumber,bumen};
					stuFrame.tableModel.addRow(rowData);
					JOptionPane.showMessageDialog(StuInsertFrame.this, "������ӳɹ�",
							"��ʾ�Ի���", 1);
					input1.setText("");
					input2.setText("");
					input3.setText("");
					input4.setText("");
					
				}else{
					JOptionPane.showMessageDialog(StuInsertFrame.this, "���������������飡",
							"��ʾ�Ի���", 1);
				}
				
			}
		} catch (Exception ee) {
		}

	}
}
