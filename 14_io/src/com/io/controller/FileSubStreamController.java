package com.io.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.io.model.vo.Member;

public class FileSubStreamController {
	
	public void stringChangeOutput() {
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new OutputStreamWriter(System.out));
			output.write("�ȳ��ϼ���");
			output.flush();//������ ������ ��������� -> CPU�� ���� �� �ֵ���
			//Buffered~�� �����͸� �� ���ۿ� ������ flush�� ���۸� ������� ���ۿ� ���� ������ �ѹ��� ����
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
			try {
				if (output!=null) output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stringChangeInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
			System.out.print("���ڿ� �Է� : ");
			String value = br.readLine();
			System.out.println("value : "+value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//���� �̿��ϱ�
	public void bufferedReaderTest() {
		try (BufferedReader reader = new BufferedReader(new FileReader("sample.txt"));){
			StringBuffer sb = new StringBuffer();
			String temp = null;
			while ((temp=reader.readLine())!=null) {
				sb.append(temp+"\n");
			}
			System.out.println(sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void bufferedWriterTest() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("sample.txt"));){
			bw.write("������ �������̶�\n");
			bw.write("���亴 ������?\n");
			bw.write("���亴 ��ġ�� ����� �Ͽ��Ͽ� ����ϴ� �ſ���..��\n");
			
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�����͸� �����غ���.
	public void memberSave() {
		//���̵�, �н�����, ����, ����, Ű, ������
		//memberdata.bat
		Scanner sc = new Scanner(System.in);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("member.dat"));){
			for (int i=0 ; i<3 ; i++) {
				System.out.print("���̵� : ");
				String id = sc.next();
				System.out.print("��й�ȣ : ");
				String pw = sc.next();
				System.out.print("����(M/F) : ");
				char gender = sc.next().toUpperCase().charAt(0);
				System.out.print("���� : ");
				int age = sc.nextInt();
				System.out.print("Ű : ");
				double height = sc.nextDouble();
				System.out.print("������ : ");
				double weight = sc.nextDouble();
				bw.write(id+",");
				bw.write(pw+",");
				bw.write(gender+",");
				bw.write(age+",");
				bw.write(String.valueOf(height)+",");
				bw.write(String.valueOf(weight)+"\n");
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMember() {
		try (BufferedReader reader = new BufferedReader(new FileReader("member.dat"));){
			StringBuffer sb = new StringBuffer();
			String temp = null;
			while ((temp=reader.readLine())!=null) {
				sb.append(temp+"\n");
			}
			//System.out.println(sb);
			
			//������ �Ľ� ó���ϱ� 
			String[] members = new String(sb).split("\n");
			Member[] membersArr = new Member[3];
//			System.out.println(members[2]);
			for (int i=0 ; i<members.length ; i++) {
				String[] memberData = members[i].split(",");
				membersArr[i] = new Member();
				membersArr[i].setId(memberData[0]);
				membersArr[i].setPw(memberData[1]);
				membersArr[i].setGender(memberData[2].charAt(0));
				membersArr[i].setAge(Integer.parseInt(memberData[3]));
				membersArr[i].setHeight(Double.parseDouble(memberData[4]));
				membersArr[i].setWeight(Double.parseDouble(memberData[5]));
			}
			for (Member m : membersArr) {
				System.out.println(m);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//DataType IO
	public void saveMemberDataType() {
		//DataInputStream �⺻ ��Ʈ�� -> FileInputStream
		//DataOutputStream �⺻ ��Ʈ�� -> FileOutputStream
		try(Scanner sc = new Scanner(System.in);
				DataOutputStream dis
				= new DataOutputStream(new FileOutputStream("memberdata.dat"));){
			for (int i=0 ; i<2 ; i++) {
				System.out.print("���̵� : ");
				dis.writeUTF(sc.next());
				System.out.print("�н����� : ");
				dis.writeUTF(sc.next());
				System.out.print("����(M/F) : ");
				dis.writeChar(sc.next().charAt(0));
				System.out.print("���� : ");
				dis.writeInt(sc.nextInt());
				System.out.print("Ű : ");
				dis.writeDouble(sc.nextDouble());
				System.out.print("������ : ");
				dis.writeDouble(sc.nextDouble());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMemberDataType() {
		try(DataInputStream dis = new DataInputStream (new FileInputStream("memberdata.dat"))){
			Member[] members = new Member[2];
			for(int i=0 ; i<2 ; i++) {
				members[i] = new Member();
				members[i].setId(dis.readUTF());//���Ͽ� ������ ������� �ҷ����� ��
				members[i].setPw(dis.readUTF());
				members[i].setGender(dis.readChar());
				members[i].setAge(dis.readInt());
				members[i].setHeight(dis.readDouble());
				members[i].setWeight(dis.readDouble());
			}
			for (Member m : members) {
				System.out.println(m);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ü �������
	public void objOutputTest() {
		try(Scanner sc = new Scanner(System.in); 
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("memberdata.dat"));){
			Member m = new Member();
			Member[] members = new Member[2];
			for (int i=0 ; i<2; i++) {
				//members[i] = new Member();
				System.out.print("���̵� : ");
				m.setId(sc.next());
				//members[i].setId(sc.next());
				System.out.print("�н����� : ");
				m.setPw(sc.next());
				//members[i].setPw(sc.next());
				System.out.print("����(M/F) : ");
				m.setGender(sc.next().charAt(0));
				//members[i].setGender(sc.next().charAt(0));
				System.out.print("���� : ");
				m.setAge(sc.nextInt());
				//members[i].setAge(sc.nextInt());
				System.out.print("Ű : ");
				m.setHeight(sc.nextDouble());
				//members[i].setHeight(sc.nextDouble());
				System.out.print("������ : ");
				m.setWeight(sc.nextDouble());
				//members[i].setWeight(sc.nextDouble());
			}
			oos.writeObject(m);//��ü ����!
			//oos.writeObject(members);//��ü ����!
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadObject() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("memberdata.dat"))){
			for (int i=0 ; i<2 ; i++) {
				try {
					Member m = (Member)ois.readObject();
					System.out.println(m);
				} catch (EOFException e) {
					System.out.println("������ ��� �о����ϴ�.");
					break;
				}
			}//try~catch������ ���ǽĿ� �ִ��Է°��� �־����, �Է��ϰ� ���� ��ŭ �Է��� �� ����
//			Member[] members = (Member[])ois.readObject();
//			for (Member m : members) {
//				System.out.println(m);
//			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
