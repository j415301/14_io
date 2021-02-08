package com.io.model.vo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6698578289567979719L;
	private String id;
	private String pw;
	private char gender;
	private int age;
	private double height;
	private double weight;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String pw, char gender, int age, double height, double weight) {
		super();
		this.id = id;
		this.pw = pw;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", gender=" + gender + ", age=" + age + ", height=" + height
				+ ", weight=" + weight + "]";
	}
	


	/*
	public void insertMem() {
		try (BufferedReader br = new BufferedReader(new FileReader("member.dat"));){
			StringBuffer sb = new StringBuffer();
			String temp = null;
			while ((temp=br.readLine())!=null) {
				sb.append(temp+"\n");
			}
			String a = new String(sb);
			String[] user = a.split("/n");
			for (int i=0 ; i<user.length ; i++) {
				new String[] a = user[i].split(",");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*///쌤코드는 filelsubstreamcontroller().loadMember에 있음

}
