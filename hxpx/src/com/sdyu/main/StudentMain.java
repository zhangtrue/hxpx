package com.sdyu.main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.sdyu.frame.StuBaseInfoFrame;


//�����


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
        setTitle(s);             //���ô��ڵı���   
        menubar=new JMenuBar(); 
        menu=new JMenu("�绰��");
        subMenu=new JMenuItem("�ҵĵ绰��");
        menubar.add(menu); 
        menu.add(subMenu);
        setJMenuBar(menubar);
        //��Ӳ˵��¼�����
        subMenu.addActionListener(this);
      
     }


    public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();		
		if(obj==subMenu){
			
			StuBaseInfoFrame sf=new StuBaseInfoFrame();
		}
		
	}





}