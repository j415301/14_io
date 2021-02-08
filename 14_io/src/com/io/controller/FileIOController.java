package com.io.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIOController {
	
	public void saveFile() {
		//파일에 특정 데이터를 저장하려면 outputStream을 연결하여 데이터를 저장함 = RAM에서 하드로
		//String객체를 생성하면 반드시 그 객체는 반환(close())을 해줘야 함
		//파일에 대한 메소드 이용은 거의 대부분 IOException 처리를 해줘야 함
		//OutputStream output = new OutputStream();-> 추상클래스이기 때문에 객체 생성 불가능
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(new File("bs/th.txt"));
			
			//데이터를 넣어보자!
			//FileOutputStream에서 제공하는 write() 메소드 이용
			byte[] data = "ABCDE".getBytes();
			output.write(data);
			output.write("1234".getBytes());//숫자로 넣으면 깨짐, String으로 파싱해서 넣으면 원래 형태로 잘 들어감
			output.write("점심먹자".getBytes());//한글은 한 글자에 2byte기 때문에 원본대로 입출력이 안됨
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
	
	public void loadFile() {
		//파일에서 데이터를 가져오려면 InputStream객체를 이용함 = 하드에서 RAM으로
		//InputStream is = new InputStream();-> 추상클래스이기 때문에 객체 생성 불가능
		FileInputStream fis = null;//읽어온 다음에 닫아줘야 하기 때문에 null로 초기화
		try {
			fis = new FileInputStream("bs/th.txt");
			
			//파일의 데이터를 읽어오려면 read() 메소드 이용
			//int형으로 반환을 하기 때문에 int로 받아야 함
			//read 한 번에 1byte씩 받아옴
			int data = -1;//아무 값이나 초기화해도 됨, 차피 while에서 파일 데이터 바로 읽으니까
			while ((data = fis.read())!=-1) {//파일의 데이터를 1byte씩 읽다가 마지막까지 다 읽으면 -1을 출력하기 때문에 -1이 되면 이 while문이 멈추면서 read를 멈춤
				System.out.println((char)data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis!=null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//문자열은 Reader/Writer 사용
		public void saveStrFile() {
			//Writer
			//Writer w = new Writer();
			try (FileWriter w = new FileWriter("bs/bs.txt");){
				w.write("점심 맛있게 먹었나요?");
				w.write("테스트");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void loadStrFile() {
			//Reader
			try (FileReader fr = new FileReader("bs/bs.txt");){
				StringBuffer sb = new StringBuffer();
				int data = -1;
				while((data=fr.read())!=-1) {
					sb.append((char)data);
					//String으로 누적연산을 하면 계속 새로운 String 객체를 만드므로 효율적이지 X
					//따라서 원본값이 수정되는 StringBuffer 사용
				}
				System.out.println(sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
