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

//基础数据面板

public class StuBaseInfoFrame extends JFrame implements ActionListener,ListSelectionListener{
		//定义容器
		Container contentPane;
		//定义所用的面板
		JPanel upPanel, downPanel;
		//定义图形界面元素
		JButton insertButton, queryButton, deleteButton, modifyButton;
	JLabel jLabel1,jLabel2;	
	//框架的大小
	Dimension faceSize=new Dimension(400,400);
	//定义表格
	JScrollPane jScrollPanel1;
	JTable jtable;
	DefaultTableModel tableModel;
	ListSelectionModel lsm;
	String[] colName={"编号","姓名","电话号码","部门"};
	String[][] colValue;
	
	GridBagLayout girdBag=new GridBagLayout();
	GridBagConstraints girdBagCon;
	
	public StuBaseInfoFrame() {
		//设置窗口的尺寸和标题
		this.setSize(faceSize);
		this.setTitle("电话号码查询");
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
			
			//上部面板的布局
			StuBeanDao studao=new StuBeanDao();
			try{
				colValue=studao.searchAll();
				tableModel = new DefaultTableModel(colValue,colName);
				jtable=new JTable(tableModel);
				jtable.setPreferredScrollableViewportSize(new Dimension(400,300));//表格的尺寸
				lsm=jtable.getSelectionModel();
				lsm.setSelectionMode(lsm.SINGLE_SELECTION);
				lsm.addListSelectionListener(this);  //为表格添加单行选中时的监听器
				jScrollPanel1=new JScrollPane(jtable);
				jScrollPanel1.setPreferredSize(new Dimension(380,300));//装载表格容器的尺寸
			}catch(Exception e){
				e.printStackTrace();
			}
			
			upPanel = new JPanel();
			upPanel.add(jScrollPanel1);
			contentPane.add(upPanel,BorderLayout.NORTH);
			
			//下部面板的布局
			insertButton = new JButton("添加");			
			queryButton = new JButton("查询");
			modifyButton = new JButton("修改");
			deleteButton = new JButton("删除");		
			
		
			//添加事件监听
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
	 * 事件处理
	*/
	//当按钮被选中时的操作
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();		
		if(obj==insertButton){
			new StuInsertFrame(StuBaseInfoFrame.this).setVisible(true);
			//选中“新增”按钮时，打开新增窗口
		}else if(obj==modifyButton){
			//选中“修改”按钮时，打开修改窗口
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
	    			JOptionPane.showMessageDialog(StuBaseInfoFrame.this, "数据删除成功",
							"提示", 1);
	    		}
	    		//从JTable中删除选定行
	    		tableModel.removeRow(selectedRow) ;
	    		//JTable刷新
	    		tableModel.fireTableDataChanged();
	    		jScrollPanel1.validate();
	         
	         }
	           catch(Exception e1) 
	           {  
	        	   System.out.println(e1);
	           }
		}
		
	}
	//当表格被选中时的操作
	@Override
	public void valueChanged(ListSelectionEvent e) {
		modifyButton.setEnabled(true);
		deleteButton.setEnabled(true);
	}
	

	
}
