package com.sdyu.frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;


//数据修改窗口


public class StuModifyFrame extends JFrame implements ActionListener {

	JTextField input1, input2, input3, input4;
	JLabel label1, label2, label3, label4, label5;
	JButton button;
	StuBaseInfoFrame stuFrame;
	int selectedRow;

	public StuModifyFrame(String stuno, int selectedRow,
			StuBaseInfoFrame stuFrame) {
		// 将第一个窗口中被选中行的行号selectedRow和该行的学号stuno以及窗口的引用都传入到第二个窗口中，以备使用
		this.selectedRow = selectedRow;
		this.stuFrame = stuFrame;
		init(stuno);
		setTitle("数据修改");
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
		// 将选定行的内容查询出来，放入数组stu中
		StuBeanDao studao = new StuBeanDao();
		String[] stu = studao.searchById(stuno);
		// 初始化组件
		input1 = new JTextField(15);
		input2 = new JTextField(15);
		input3 = new JTextField(15);
		input4 = new JTextField(15);
		
		// 将选定行的内容写入到单行文本框组件中
		input1.setText(stu[0]);
		input2.setText(stu[1]);
		input3.setText(stu[2]);
		input4.setText(stu[3]);
		
		// 编号不允许修改
		input1.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.add(new JLabel("编号"), BorderLayout.CENTER);
		panel.add(input1);
		panel.add(new JLabel("姓名"), BorderLayout.CENTER);
		panel.add(input2);
		panel.add(new JLabel("手机号码"), BorderLayout.CENTER);
		panel.add(input3);
		panel.add(new JLabel("部门"), BorderLayout.CENTER);
		panel.add(input4);

		button = new JButton("保存");
		button.addActionListener(this); // 为修改按钮添加事件侦听
		Container container = getContentPane();
		container.add(panel, BorderLayout.CENTER);
		container.add(button, BorderLayout.SOUTH);
	}

	/*
	 * 事件处理
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
				JOptionPane.showMessageDialog(StuModifyFrame.this, "请重新输入",
						"提示对话框", 1);
			} else {
				StuBean stu = new StuBean(number, name, phonenumber, bumen);
				StuBeanDao studao = new StuBeanDao();
				int result = studao.updateStu(stu);//将修改后的信息更新到数据库中
				if (result != 0) {
					// JTabled刷新，重新对tableModel赋值					
					stuFrame.tableModel.setValueAt(name, selectedRow, 1);
					stuFrame.tableModel.setValueAt(phonenumber, selectedRow, 2);
					stuFrame.tableModel.setValueAt(bumen, selectedRow, 3);
				
				
					JOptionPane.showMessageDialog(StuModifyFrame.this,
							"数据修改成功", "提示对话框", 1);

					// 关闭当前窗口
					StuModifyFrame.this.dispose();

				} else {
					JOptionPane.showMessageDialog(StuModifyFrame.this,
							"更新失败！", "提示对话框", 1);
				}

			}
		} catch (Exception ee) {
		}

	}
}