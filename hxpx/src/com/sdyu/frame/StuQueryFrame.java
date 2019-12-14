package com.sdyu.frame;

import java.awt.*;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

import javax.swing.*;

import java.sql.*;
import java.util.Vector;



//���ݲ�ѯ����



public class StuQueryFrame extends JFrame implements ActionListener {
	Container container;
	JPanel panel1, panel2, upPanel, downPanel;
	JTextArea show;
	JLabel pnLabel, nameLabel, bumenLabel, courseLabel;
	JButton pnButton, nameButton, bumenButton, courseButton;
	JTextField field1, field2;
	JComboBox comoBox;

	// ������
	JScrollPane jScrollPanel1;
	JTable jtable;
	DefaultTableModel tableModel;
	ListSelectionModel lsm;
	String[] colName = { "���", "����", "�绰����", "����"};
	String[][] colValue;

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

	public StuQueryFrame() {

		setTitle("��ѯ����");
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

		// �ϲ����Ĳ���
		panel1 = new JPanel();
		panel2 = new JPanel();
		upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());

		pnLabel = new JLabel("�绰��");
		pnButton = new JButton("��ѯ");
		field1 = new JTextField(7);
		panel1.add(pnLabel);
		panel1.add(field1);
		panel1.add(pnButton);

		nameLabel = new JLabel("����");
		nameButton = new JButton("��������ѯ");
		field2 = new JTextField(6);
		panel1.add(nameLabel);
		panel1.add(field2);
		panel1.add(nameButton);
		upPanel.add(panel1, BorderLayout.NORTH);

		bumenLabel = new JLabel("����");
		String items[]={"��ѡ��","China Mobile","China Unicom","China Telecom"};
		comoBox=new JComboBox(items);
		bumenButton = new JButton("��ѯ");
		
		panel2.add(bumenLabel);
		panel2.add(comoBox);
		panel2.add(bumenButton);
		
		


		upPanel.add(panel2, BorderLayout.SOUTH);

		container.add(upPanel, BorderLayout.NORTH);

		// �²����Ĳ���
		StuBeanDao studao = new StuBeanDao();
		try {
			// colValue=studao.searchAll();
			tableModel = new DefaultTableModel(colValue, colName);
			jtable = new JTable(tableModel);
			jtable.setPreferredScrollableViewportSize(new Dimension(400, 280));// ���ĳߴ�
			jScrollPanel1 = new JScrollPane(jtable);
			jScrollPanel1.setPreferredSize(new Dimension(380, 280));// װ�ر�������ĳߴ�
		} catch (Exception e) {
			e.printStackTrace();
		}

		downPanel = new JPanel();
		downPanel.add(jScrollPanel1);
		container.add(downPanel, BorderLayout.SOUTH);

	}

	// ����¼�����
	public void addListener() throws Exception {
		pnButton.addActionListener(this);
		nameButton.addActionListener(this);
		bumenButton.addActionListener(this);

	}

	// ��ť�¼�����
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == pnButton) {// ����Ų�ѯ
			// ��ѡ���е����ݲ�ѯ��������������stu��
			String stuno = field1.getText().trim();
			StuBeanDao studao = new StuBeanDao();
			String[] stu = studao.searchByPN(stuno);

			field2.setText("");
			comoBox.setSelectedIndex(0);
			tableModel.setRowCount(0);// ��ձ���е�����
			tableModel.addRow(stu); // �ڱ�������һ�м�¼����һ��stuʵ��

		} else if (obj == nameButton) { // ��������ѯ
			// ��ѡ���е����ݲ�ѯ��������������stu��
			String stuname = field2.getText().trim();
			StuBeanDao studao = new StuBeanDao();
			String[] stu = studao.searchByName(stuname);
			field1.setText("");	
			comoBox.setSelectedIndex(0);
			tableModel.setRowCount(0);// ��ձ���е�����
			tableModel.addRow(stu); // �ڱ�������һ�м�¼����һ��stuʵ��

		} else if (obj == bumenButton) { // �����Ų�ѯ
			// ��ѡ���е����ݲ�ѯ��������������stu��
			String bumen = String.valueOf(comoBox.getSelectedItem().toString().charAt(0));//�����б��б�ѡ��������ݶ�ȡ
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