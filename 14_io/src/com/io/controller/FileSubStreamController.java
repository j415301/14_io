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
			output.write("안녕하세요");
			output.flush();//버퍼의 내용을 종료시켜줌 -> CPU가 읽을 수 있도록
			//Buffered~로 데이터를 한 버퍼에 모으고 flush로 버퍼를 종료시켜 버퍼에 모인 내용을 한번에 전송
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
			System.out.print("문자열 입력 : ");
			String value = br.readLine();
			System.out.println("value : "+value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//버퍼 이용하기
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
			bw.write("여러분 월요일이라서\n");
			bw.write("월요병 힘들죠?\n");
			bw.write("월요병 고치는 방법은 일요일에 출근하는 거에요..ㅋ\n");
			
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//데이터를 보관해보자.
	public void memberSave() {
		//아이디, 패스워드, 성별, 나이, 키, 몸무게
		//memberdata.bat
		Scanner sc = new Scanner(System.in);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("member.dat"));){
			for (int i=0 ; i<3 ; i++) {
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String pw = sc.next();
				System.out.print("성별(M/F) : ");
				char gender = sc.next().toUpperCase().charAt(0);
				System.out.print("나이 : ");
				int age = sc.nextInt();
				System.out.print("키 : ");
				double height = sc.nextDouble();
				System.out.print("몸무게 : ");
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
			
			//데이터 파싱 처리하기 
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
		//DataInputStream 기본 스트림 -> FileInputStream
		//DataOutputStream 기본 스트림 -> FileOutputStream
		try(Scanner sc = new Scanner(System.in);
				DataOutputStream dis
				= new DataOutputStream(new FileOutputStream("memberdata.dat"));){
			for (int i=0 ; i<2 ; i++) {
				System.out.print("아이디 : ");
				dis.writeUTF(sc.next());
				System.out.print("패스워드 : ");
				dis.writeUTF(sc.next());
				System.out.print("성별(M/F) : ");
				dis.writeChar(sc.next().charAt(0));
				System.out.print("나이 : ");
				dis.writeInt(sc.nextInt());
				System.out.print("키 : ");
				dis.writeDouble(sc.nextDouble());
				System.out.print("몸무게 : ");
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
				members[i].setId(dis.readUTF());//파일에 대입한 순서대로 불러오게 됨
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
	
	//객체 보내기ㅜ
	public void objOutputTest() {
		try(Scanner sc = new Scanner(System.in); 
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("memberdata.dat"));){
			Member m = new Member();
			Member[] members = new Member[2];
			for (int i=0 ; i<2; i++) {
				//members[i] = new Member();
				System.out.print("아이디 : ");
				m.setId(sc.next());
				//members[i].setId(sc.next());
				System.out.print("패스워드 : ");
				m.setPw(sc.next());
				//members[i].setPw(sc.next());
				System.out.print("성별(M/F) : ");
				m.setGender(sc.next().charAt(0));
				//members[i].setGender(sc.next().charAt(0));
				System.out.print("나이 : ");
				m.setAge(sc.nextInt());
				//members[i].setAge(sc.nextInt());
				System.out.print("키 : ");
				m.setHeight(sc.nextDouble());
				//members[i].setHeight(sc.nextDouble());
				System.out.print("몸무게 : ");
				m.setWeight(sc.nextDouble());
				//members[i].setWeight(sc.nextDouble());
			}
			oos.writeObject(m);//객체 저장!
			//oos.writeObject(members);//객체 저장!
			
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
					System.out.println("파일을 모두 읽었습니다.");
					break;
				}
			}//try~catch문으로 조건식에 최대입력값을 넣어놓고, 입력하고 싶은 만큼 입력할 수 있음
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
