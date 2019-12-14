package com.sdyu.frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;


//�����޸Ĵ���


public class StuModifyFrame extends JFrame implements ActionListener {

	JTextField input1, input2, input3, input4;
	JLabel label1, label2, label3, label4, label5;
	JButton button;
	StuBaseInfoFrame stuFrame;
	int selectedRow;

	public StuModifyFrame(String stuno, int selectedRow,
			StuBaseInfoFrame stuFrame) {
		// ����һ�������б�ѡ���е��к�selectedRow�͸��е�ѧ��stuno�Լ����ڵ����ö����뵽�ڶ��������У��Ա�ʹ��
		this.selectedRow = selectedRow;
		this.stuFrame = stuFrame;
		init(stuno);
		setTitle("�����޸�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width / 2;
		int screenHeight = screenSize.height / 2;
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth - width / 2, screenHeight - height / 2);
		setVisible(true);
	}

	public void init(String stuno) {
		// ��ѡ���е����ݲ�ѯ��������������stu��
		StuBeanDao studao = new StuBeanDao();
		String[] stu = studao.searchById(stuno);
		// ��ʼ�����
		input1 = new JTextField(15);
		input2 = new JTextField(15);
		input3 = new JTextField(15);
		input4 = new JTextField(15);
		
		// ��ѡ���е�����д�뵽�����ı��������
		input1.setText(stu[0]);
		input2.setText(stu[1]);
		input3.setText(stu[2]);
		input4.setText(stu[3]);
		
		// ��Ų������޸�
		input1.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.add(new JLabel("���"), BorderLayout.CENTER);
		panel.add(input1);
		panel.add(new JLabel("����"), BorderLayout.CENTER);
		panel.add(input2);
		panel.add(new JLabel("�ֻ�����"), BorderLayout.CENTER);
		panel.add(input3);
		panel.add(new JLabel("����"), BorderLayout.CENTER);
		panel.add(input4);

		button = new JButton("����");
		button.addActionListener(this); // Ϊ�޸İ�ť����¼�����
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
			String bumen = input4.getText();
			
			if (number.equals("") | name.equals("") | phonenumber.equals("")
					| bumen.equals("")) {
				JOptionPane.showMessageDialog(StuModifyFrame.this, "����������",
						"��ʾ�Ի���", 1);
			} else {
				StuBean stu = new StuBean(number, name, phonenumber, bumen);
				StuBeanDao studao = new StuBeanDao();
				int result = studao.updateStu(stu);//���޸ĺ����Ϣ���µ����ݿ���
				if (result != 0) {
					// JTabledˢ�£����¶�tableModel��ֵ					
					stuFrame.tableModel.setValueAt(name, selectedRow, 1);
					stuFrame.tableModel.setValueAt(phonenumber, selectedRow, 2);
					stuFrame.tableModel.setValueAt(bumen, selectedRow, 3);
				
				
					JOptionPane.showMessageDialog(StuModifyFrame.this,
							"�����޸ĳɹ�", "��ʾ�Ի���", 1);

					// �رյ�ǰ����
					StuModifyFrame.this.dispose();

				} else {
					JOptionPane.showMessageDialog(StuModifyFrame.this,
							"����ʧ�ܣ�", "��ʾ�Ի���", 1);
				}

			}
		} catch (Exception ee) {
		}

	}
}