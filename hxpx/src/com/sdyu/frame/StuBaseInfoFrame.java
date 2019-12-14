package com.sdyu.frame;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sdyu.util.ConnectionUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//�����������

public class StuBaseInfoFrame extends JFrame implements ActionListener,ListSelectionListener{
		//��������
		Container contentPane;
		//�������õ����
		JPanel upPanel, downPanel;
		//����ͼ�ν���Ԫ��
		JButton insertButton, queryButton, deleteButton, modifyButton;
	JLabel jLabel1,jLabel2;	
	//��ܵĴ�С
	Dimension faceSize=new Dimension(400,400);
	//������
	JScrollPane jScrollPanel1;
	JTable jtable;
	DefaultTableModel tableModel;
	ListSelectionModel lsm;
	String[] colName={"���","����","�绰����","����"};
	String[][] colValue;
	
	GridBagLayout girdBag=new GridBagLayout();
	GridBagConstraints girdBagCon;
	
	public StuBaseInfoFrame() {
		//���ô��ڵĳߴ�ͱ���
		this.setSize(faceSize);
		this.setTitle("�绰�����ѯ");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width / 2;
		int screenHeight = screenSize.height / 2;
		int height = this.getHeight();
		int width = this.getWidth();
		this.setLocation(screenWidth - width / 2, screenHeight - height / 2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		try{
			init();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		public void init() throws Exception{
			contentPane = this.getContentPane();
			contentPane.setLayout(new BorderLayout());
			
			//�ϲ����Ĳ���
			StuBeanDao studao=new StuBeanDao();
			try{
				colValue=studao.searchAll();
				tableModel = new DefaultTableModel(colValue,colName);
				jtable=new JTable(tableModel);
				jtable.setPreferredScrollableViewportSize(new Dimension(400,300));//���ĳߴ�
				lsm=jtable.getSelectionModel();
				lsm.setSelectionMode(lsm.SINGLE_SELECTION);
				lsm.addListSelectionListener(this);  //Ϊ�����ӵ���ѡ��ʱ�ļ�����
				jScrollPanel1=new JScrollPane(jtable);
				jScrollPanel1.setPreferredSize(new Dimension(380,300));//װ�ر�������ĳߴ�
			}catch(Exception e){
				e.printStackTrace();
			}
			
			upPanel = new JPanel();
			upPanel.add(jScrollPanel1);
			contentPane.add(upPanel,BorderLayout.NORTH);
			
			//�²����Ĳ���
			insertButton = new JButton("���");			
			queryButton = new JButton("��ѯ");
			modifyButton = new JButton("�޸�");
			deleteButton = new JButton("ɾ��");		
			
		
			//����¼�����
			insertButton.addActionListener(this);
			queryButton.addActionListener(this);
			modifyButton.addActionListener(this);
			deleteButton.addActionListener(this);
		
		
		
			downPanel = new JPanel();
			downPanel.add(insertButton);		
			downPanel.add(modifyButton);
			downPanel.add(deleteButton);
			downPanel.add(queryButton);
			
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
			
			contentPane.add(downPanel,BorderLayout.SOUTH);
		}
		
	/*
	 * �¼�����
	*/
	//����ť��ѡ��ʱ�Ĳ���
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();		
		if(obj==insertButton){
			new StuInsertFrame(StuBaseInfoFrame.this).setVisible(true);
			//ѡ�С���������ťʱ������������
		}else if(obj==modifyButton){
			//ѡ�С��޸ġ���ťʱ�����޸Ĵ���
			int selectedRow=jtable.getSelectedRow();
			String stuno=colValue[selectedRow][0];
			new StuModifyFrame(stuno,selectedRow,StuBaseInfoFrame.this).setVisible(true);		
		}else if(obj==queryButton){
			new StuQueryFrame().setVisible(true);		
		}else if(obj==deleteButton){
			
			int selectedRow=jtable.getSelectedRow();
			String stuno=colValue[selectedRow][0];
	    	try{
	    		StuBeanDao studao=new StuBeanDao();
	    		int result=studao.deleteStu(stuno);
	    		if(result!=0){
	    			JOptionPane.showMessageDialog(StuBaseInfoFrame.this, "����ɾ���ɹ�",
							"��ʾ", 1);
	    		}
	    		//��JTable��ɾ��ѡ����
	    		tableModel.removeRow(selectedRow) ;
	    		//JTableˢ��
	    		tableModel.fireTableDataChanged();
	    		jScrollPanel1.validate();
	         
	         }
	           catch(Exception e1) 
	           {  
	        	   System.out.println(e1);
	           }
		}
		
	}
	//�����ѡ��ʱ�Ĳ���
	@Override
	public void valueChanged(ListSelectionEvent e) {
		modifyButton.setEnabled(true);
		deleteButton.setEnabled(true);
	}
	

	
}
