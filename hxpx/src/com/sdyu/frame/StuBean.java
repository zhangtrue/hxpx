package com.sdyu.frame;

public class StuBean {
	String no;
	String name;
	String phonenumber;
	String bumen;
	
	public StuBean(){
		
	}
	
	public StuBean(String no, String name,String phonenumber, String bumen) {
		
		this.no = no;
		this.name = name;
		this.phonenumber = phonenumber;
		this.bumen = bumen;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phonenumber;
	}

	public void setPhoneNumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	
	

}
