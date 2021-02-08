package com.io.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileController {
	
	public void basicFile() {
		//파일 클래스를 이용해서 파일을 다뤄보자!
		
		File f = new File("test.txt");//test.txt라는 이름의 파일 객체 생성
		//default 생성자 없음
		//매개변수에 파일명만 작성하면 기본 프로젝트 경로가 default 경로로 설정됨
		
		//생성한 file 객체를 이용해 파일 생성하기
		try {
			f.createNewFile();//파일 생성 메소드
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//프로젝트 경로가 아닌 다른 경로에 파일을 저장하기
		File f1 = new File("C:\\Users\\User\\test.txt");//\ 한 개만 쓸 경우 escape문으로 인식함, 문자열 안에 \를 쓰고 싶으면 \\쓰기
		try {
			if(f1.createNewFile()){//반환형: boolean -> 분기처리 가능
				System.out.println("test.txt파일을 생성했습니다.");
			} else {
				System.out.println("test.txt파일 생성에 실패했습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//프로젝트 내부에서 경로 찾기
		System.out.println(FileController.class.getResource("").getPath());//프로젝트의 절대경로를 가져옴
		//getResource("/"): bin으로 감
		//getResource("../"): 실제 프로젝트 위치의 2번 위 상위 폴더
		//getResource("")=("./"): 현재 project 실제 위치
		//getPath(): 경로를 문자열로 나타냄
		String path = FileController.class.getResource("/").getPath();
		path = path.substring(0,path.lastIndexOf("bin"));//bin 전의 경로(=14_io까지)만 추출
		//파싱: 내가 원하는 데이터를 뽑아내는 거
		System.out.println(path);
		File f2 = new File(path+"/pathTest.txt");
		try {
			f2.createNewFile();//bin에 pathTest 파일 생성
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		//디렉토리(=폴더) 생성하기: File 객체를 이용해 생성
		//mkdir() / mkdirs() : 폴더 생성 메소드 (make directory)
		//mkdir(): 하위 폴더 한 개 생성
		//mkdirs(): 여러 개의 하위 폴더 생성
		File dir = new File(path+"/data/a/b/c");
		dir.mkdirs();//14_io 폴더의 하위폴더로 data 생성됨 (예외처리 할 필요 없음)
		
		//지우기 (파일, 폴더)
		//delete(): 파일을 지움
		File deleteFile = null;
		deleteFile = new File(path+"test.txt");
		deleteFile.delete();
		File delDir = new File(path+"data/a/b/c");//하위폴더가 있는 폴더 삭제시 가장 하위 폴더부터 순차적으로 지워야 함 = 하위 폴더가 없는 상태에서 삭제 가능
		delDir.delete();
	}
	
	//프로젝트에 bs폴더를 만들고 bs.txt 파일을 저장해보자
	//public void fileTest(String path, String fileName) {
	public void fileTest() {	
		//내코드
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
		
		//쌤코드
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
	
	//File객체를 이용해서 파일에 대한 정보를 출력해보자
	public void fileInfo() {
		String path = FileController.class.getResource("/").getPath();
		path = path.substring(0,path.lastIndexOf("bin"));
		File f = new File(path+"bs","bs.txt");
		File dir = new File(path+"bs");
		System.out.println(f.isDirectory());//이거 디렉토리니?//false
		System.out.println(dir.isDirectory());//true
		if(f.isFile()) {
			System.out.println(f.canExecute());//실행파일이니?//true
			System.out.println(f.canRead());//읽을 수 있니?(보안 관련)//true
			System.out.println(f.canWrite());//수정할 수 있니?//true
			System.out.println(f.getName());//파일명이 뭐니?//bs.txt
			System.out.println(f.getParent());//부모의 경로 출력//C:\Users\User\eclipse-workspace\14_io\bs
			System.out.println(f.getPath());//본인의 경로 출력//C:\Users\User\eclipse-workspace\14_io\bs\bs.txt
			File parentFile = f.getParentFile();//부모를 파일 객체로 변환
			System.out.println(parentFile.getName());//bs
			System.out.println(f.isHidden());//숨겨져 있니?//false
			System.out.println(new Date(f.lastModified()));//최근 수정 날짜, long 타입으로 출력됨
			Date modify = new Date(f.lastModified());
			String mDate = new SimpleDateFormat("yyyy-MM-dd E hh:mm:ss").format(modify);
			System.out.println(mDate);
			System.out.println(f.length());//0
			f = new File("C:\\Users\\User\\test.txt");
			if(f.exists()){//파일이 존재하니?
				System.out.println("파일 있엉!");
			} else {
				System.out.println("파일 없엉!");
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println();
		
		//폴더 전체 내용 가져오기
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
