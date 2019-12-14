package com.sdyu.main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.sdyu.frame.StuBaseInfoFrame;


//主面板


public class StudentMain extends JFrame implements ActionListener{
	JMenuBar menubar;
    JMenu menu;
    JMenuItem  subMenu;
    
	public StudentMain(){
		
	} 
    public StudentMain(String s,int x,int y,int w,int h) {
       init(s);
       setLocation(x,y);
       setSize(w,h);
       setVisible(true);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    }
    void init(String s){
        setTitle(s);             //设置窗口的标题   
        menubar=new JMenuBar(); 
        menu=new JMenu("电话本");
        subMenu=new JMenuItem("我的电话本");
        menubar.add(menu); 
        menu.add(subMenu);
        setJMenuBar(menubar);
        //添加菜单事件监听
        subMenu.addActionListener(this);
      
     }


    public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();		
		if(obj==subMenu){
			
			StuBaseInfoFrame sf=new StuBaseInfoFrame();
		}
		
	}





}