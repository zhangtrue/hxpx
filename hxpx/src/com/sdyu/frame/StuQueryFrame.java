package com.sdyu.frame;

import java.awt.*;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

import javax.swing.*;

import java.sql.*;
import java.util.Vector;



//数据查询窗口



public class StuQueryFrame extends JFrame implements ActionListener {
	Container container;
	JPanel panel1, panel2, upPanel, downPanel;
	JTextArea show;
	JLabel pnLabel, nameLabel, bumenLabel, courseLabel;
	JButton pnButton, nameButton, bumenButton, courseButton;
	JTextField field1, field2;
	JComboBox comoBox;

	// 定义表格
	JScrollPane jScrollPanel1;
	JTable jtable;
	DefaultTableModel tableModel;
	ListSelectionModel lsm;
	String[] colName = { "编号", "姓名", "电话号码", "部门"};
	String[][] colValue;

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

	public StuQueryFrame() {

		setTitle("查询数据");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 400);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width / 2;
		int screenHeight = screenSize.height / 2;
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth - width / 2, screenHeight - height / 2);
		setVisible(true);

		try {
			init();
			addListener();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init() throws Exception {
		container = this.getContentPane();
		container.setLayout(new BorderLayout());

		// 上部面板的布局
		panel1 = new JPanel();
		panel2 = new JPanel();
		upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());

		pnLabel = new JLabel("电话号");
		pnButton = new JButton("查询");
		field1 = new JTextField(7);
		panel1.add(pnLabel);
		panel1.add(field1);
		panel1.add(pnButton);

		nameLabel = new JLabel("姓名");
		nameButton = new JButton("按姓名查询");
		field2 = new JTextField(6);
		panel1.add(nameLabel);
		panel1.add(field2);
		panel1.add(nameButton);
		upPanel.add(panel1, BorderLayout.NORTH);

		bumenLabel = new JLabel("部门");
		String items[]={"请选择","China Mobile","China Unicom","China Telecom"};
		comoBox=new JComboBox(items);
		bumenButton = new JButton("查询");
		
		panel2.add(bumenLabel);
		panel2.add(comoBox);
		panel2.add(bumenButton);
		
		


		upPanel.add(panel2, BorderLayout.SOUTH);

		container.add(upPanel, BorderLayout.NORTH);

		// 下部面板的布局
		StuBeanDao studao = new StuBeanDao();
		try {
			// colValue=studao.searchAll();
			tableModel = new DefaultTableModel(colValue, colName);
			jtable = new JTable(tableModel);
			jtable.setPreferredScrollableViewportSize(new Dimension(400, 280));// 表格的尺寸
			jScrollPanel1 = new JScrollPane(jtable);
			jScrollPanel1.setPreferredSize(new Dimension(380, 280));// 装载表格容器的尺寸
		} catch (Exception e) {
			e.printStackTrace();
		}

		downPanel = new JPanel();
		downPanel.add(jScrollPanel1);
		container.add(downPanel, BorderLayout.SOUTH);

	}

	// 添加事件监听
	public void addListener() throws Exception {
		pnButton.addActionListener(this);
		nameButton.addActionListener(this);
		bumenButton.addActionListener(this);

	}

	// 按钮事件处理
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == pnButton) {// 按编号查询
			// 将选定行的内容查询出来，放入数组stu中
			String stuno = field1.getText().trim();
			StuBeanDao studao = new StuBeanDao();
			String[] stu = studao.searchByPN(stuno);

			field2.setText("");
			comoBox.setSelectedIndex(0);
			tableModel.setRowCount(0);// 清空表格中的数据
			tableModel.addRow(stu); // 在表格中添加一行记录，即一个stu实体

		} else if (obj == nameButton) { // 按姓名查询
			// 将选定行的内容查询出来，放入数组stu中
			String stuname = field2.getText().trim();
			StuBeanDao studao = new StuBeanDao();
			String[] stu = studao.searchByName(stuname);
			field1.setText("");	
			comoBox.setSelectedIndex(0);
			tableModel.setRowCount(0);// 清空表格中的数据
			tableModel.addRow(stu); // 在表格中添加一行记录，即一个stu实体

		} else if (obj == bumenButton) { // 按部门查询
			// 将选定行的内容查询出来，放入数组stu中
			String bumen = String.valueOf(comoBox.getSelectedItem().toString().charAt(0));//下拉列表中被选中项的内容读取
			StuBeanDao studao = new StuBeanDao();
			String[][] stulist = studao.searchByBumen(bumen);
			field1.setText("");
			field2.setText("");
			tableModel.setRowCount(0);
			System.out.println(stulist);
			if(stulist!=null) {
				for (String[] stu : stulist) {
					tableModel.addRow(stu);
				}
			}
			
		} 

	}

}