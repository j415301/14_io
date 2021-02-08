package com.io.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileController {
	
	public void basicFile() {
		//���� Ŭ������ �̿��ؼ� ������ �ٷﺸ��!
		
		File f = new File("test.txt");//test.txt��� �̸��� ���� ��ü ����
		//default ������ ����
		//�Ű������� ���ϸ� �ۼ��ϸ� �⺻ ������Ʈ ��ΰ� default ��η� ������
		
		//������ file ��ü�� �̿��� ���� �����ϱ�
		try {
			f.createNewFile();//���� ���� �޼ҵ�
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//������Ʈ ��ΰ� �ƴ� �ٸ� ��ο� ������ �����ϱ�
		File f1 = new File("C:\\Users\\User\\test.txt");//\ �� ���� �� ��� escape������ �ν���, ���ڿ� �ȿ� \�� ���� ������ \\����
		try {
			if(f1.createNewFile()){//��ȯ��: boolean -> �б�ó�� ����
				System.out.println("test.txt������ �����߽��ϴ�.");
			} else {
				System.out.println("test.txt���� ������ �����߽��ϴ�.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//������Ʈ ���ο��� ��� ã��
		System.out.println(FileController.class.getResource("").getPath());//������Ʈ�� �����θ� ������
		//getResource("/"): bin���� ��
		//getResource("../"): ���� ������Ʈ ��ġ�� 2�� �� ���� ����
		//getResource("")=("./"): ���� project ���� ��ġ
		//getPath(): ��θ� ���ڿ��� ��Ÿ��
		String path = FileController.class.getResource("/").getPath();
		path = path.substring(0,path.lastIndexOf("bin"));//bin ���� ���(=14_io����)�� ����
		//�Ľ�: ���� ���ϴ� �����͸� �̾Ƴ��� ��
		System.out.println(path);
		File f2 = new File(path+"/pathTest.txt");
		try {
			f2.createNewFile();//bin�� pathTest ���� ����
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		//���丮(=����) �����ϱ�: File ��ü�� �̿��� ����
		//mkdir() / mkdirs() : ���� ���� �޼ҵ� (make directory)
		//mkdir(): ���� ���� �� �� ����
		//mkdirs(): ���� ���� ���� ���� ����
		File dir = new File(path+"/data/a/b/c");
		dir.mkdirs();//14_io ������ ���������� data ������ (����ó�� �� �ʿ� ����)
		
		//����� (����, ����)
		//delete(): ������ ����
		File deleteFile = null;
		deleteFile = new File(path+"test.txt");
		deleteFile.delete();
		File delDir = new File(path+"data/a/b/c");//���������� �ִ� ���� ������ ���� ���� �������� ���������� ������ �� = ���� ������ ���� ���¿��� ���� ����
		delDir.delete();
	}
	
	//������Ʈ�� bs������ ����� bs.txt ������ �����غ���
	//public void fileTest(String path, String fileName) {
	public void fileTest() {	
		//���ڵ�
		/*
		String path = FileController.class.getResource("/").getPath();
		path = path.substring(0,path.lastIndexOf("bin"));
		//System.out.println(path);
		File dirBs = new File("bs");
		dirBs.mkdir();
		path = path.concat("bs/");
		//System.out.println(path);
		File bs = new File(path+"bs.txt");
		try {
			bs.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		//���ڵ�
		String path = FileController.class.getResource("/").getPath();
		path = path.substring(0,path.lastIndexOf("bin"));
		File dir = new File(path,"bs");
		dir.mkdir();
		File f = new File(path+"bs","bs.txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//File��ü�� �̿��ؼ� ���Ͽ� ���� ������ ����غ���
	public void fileInfo() {
		String path = FileController.class.getResource("/").getPath();
		path = path.substring(0,path.lastIndexOf("bin"));
		File f = new File(path+"bs","bs.txt");
		File dir = new File(path+"bs");
		System.out.println(f.isDirectory());//�̰� ���丮��?//false
		System.out.println(dir.isDirectory());//true
		if(f.isFile()) {
			System.out.println(f.canExecute());//���������̴�?//true
			System.out.println(f.canRead());//���� �� �ִ�?(���� ����)//true
			System.out.println(f.canWrite());//������ �� �ִ�?//true
			System.out.println(f.getName());//���ϸ��� ����?//bs.txt
			System.out.println(f.getParent());//�θ��� ��� ���//C:\Users\User\eclipse-workspace\14_io\bs
			System.out.println(f.getPath());//������ ��� ���//C:\Users\User\eclipse-workspace\14_io\bs\bs.txt
			File parentFile = f.getParentFile();//�θ� ���� ��ü�� ��ȯ
			System.out.println(parentFile.getName());//bs
			System.out.println(f.isHidden());//������ �ִ�?//false
			System.out.println(new Date(f.lastModified()));//�ֱ� ���� ��¥, long Ÿ������ ��µ�
			Date modify = new Date(f.lastModified());
			String mDate = new SimpleDateFormat("yyyy-MM-dd E hh:mm:ss").format(modify);
			System.out.println(mDate);
			System.out.println(f.length());//0
			f = new File("C:\\Users\\User\\test.txt");
			if(f.exists()){//������ �����ϴ�?
				System.out.println("���� �־�!");
			} else {
				System.out.println("���� ����!");
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println();
		
		//���� ��ü ���� ��������
		File rootDir = new File("C:\\Program Files");
		String[] files = rootDir.list();
		for (String file : files) {
			File temp = new File(rootDir.getAbsolutePath(),file);
			if (temp.isFile()) System.out.println(file);
		}
		System.out.println();
		File[] filelist = rootDir.listFiles();
		for (File t : filelist) {
			if (t.isDirectory()) {
				System.out.println(t.getName());
			}
		}
	}
	
	

}
