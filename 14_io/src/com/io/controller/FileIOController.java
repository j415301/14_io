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
		//���Ͽ� Ư�� �����͸� �����Ϸ��� outputStream�� �����Ͽ� �����͸� ������ = RAM���� �ϵ��
		//String��ü�� �����ϸ� �ݵ�� �� ��ü�� ��ȯ(close())�� ����� ��
		//���Ͽ� ���� �޼ҵ� �̿��� ���� ��κ� IOException ó���� ����� ��
		//OutputStream output = new OutputStream();-> �߻�Ŭ�����̱� ������ ��ü ���� �Ұ���
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(new File("bs/th.txt"));
			
			//�����͸� �־��!
			//FileOutputStream���� �����ϴ� write() �޼ҵ� �̿�
			byte[] data = "ABCDE".getBytes();
			output.write(data);
			output.write("1234".getBytes());//���ڷ� ������ ����, String���� �Ľ��ؼ� ������ ���� ���·� �� ��
			output.write("���ɸ���".getBytes());//�ѱ��� �� ���ڿ� 2byte�� ������ ������� ������� �ȵ�
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
		//���Ͽ��� �����͸� ���������� InputStream��ü�� �̿��� = �ϵ忡�� RAM����
		//InputStream is = new InputStream();-> �߻�Ŭ�����̱� ������ ��ü ���� �Ұ���
		FileInputStream fis = null;//�о�� ������ �ݾ���� �ϱ� ������ null�� �ʱ�ȭ
		try {
			fis = new FileInputStream("bs/th.txt");
			
			//������ �����͸� �о������ read() �޼ҵ� �̿�
			//int������ ��ȯ�� �ϱ� ������ int�� �޾ƾ� ��
			//read �� ���� 1byte�� �޾ƿ�
			int data = -1;//�ƹ� ���̳� �ʱ�ȭ�ص� ��, ���� while���� ���� ������ �ٷ� �����ϱ�
			while ((data = fis.read())!=-1) {//������ �����͸� 1byte�� �дٰ� ���������� �� ������ -1�� ����ϱ� ������ -1�� �Ǹ� �� while���� ���߸鼭 read�� ����
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
	
	//���ڿ��� Reader/Writer ���
		public void saveStrFile() {
			//Writer
			//Writer w = new Writer();
			try (FileWriter w = new FileWriter("bs/bs.txt");){
				w.write("���� ���ְ� �Ծ�����?");
				w.write("�׽�Ʈ");
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
					//String���� ���������� �ϸ� ��� ���ο� String ��ü�� ����Ƿ� ȿ�������� X
					//���� �������� �����Ǵ� StringBuffer ���
				}
				System.out.println(sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
