# 자바사용할때 각종 IO에 대한 내용.

##  IO의 Stream 개념과 Reader개념을 구분할 줄 알아야한다.

byte 단위로 읽어오는 것과 char 단위로 읽어오는것이 기본차이며 메서드가 요구하는 대로 양식에 맞춰서 읽어서 주면 됨. > BufferedReader생성할때 InputStreamReader로 만드는 이유가 그런것임. 

Object 단위로 읽어오는 것도 뒤에 예시가 있음.
```java
/**
 * 
 *				입력				출력
 *	단위
 *	byte	InputStream		OutputStream
 *	
 *	char	Reader			Writer
 *
 *	입력 : read(), read(byte[]), read(char[])
 *	출력 : write(int i), write(byte[]), write(char[]), write(String s)
 *
 *
 *	API 클래스
 *	== 파일 처리용
 *	FileInputStream	FileOutputStream
 *	FileReader		FileWriter
 * 
 * 	== 속도 개선용
 * 	BufferedInputStream	BufferedOutputStream
 * 	BufferedReader		BufferedWriter
 * 
 * 	== 기본데이터 처리용
 * 	DataInputStream		DataOutputStream
 * 
 * 	== 객체 처리용
 * 	ObjectInputStream	ObjectOutputStream
 * 
 * 	==출력 클래스
 * 	PrintStream			PrintWriter
 * 
 * 	1.5버전부터 Scanner 편리한 기능을 모았음. (입력만 함)
 */

//System.in은 InputStream 이다.
//그러나 byte단위기 때문에 한글이 깨지게 됨.

import java.io.IOException;
import java.io.InputStream;

public class IOTest01 {
	public static void main(String[] args) {
		System.out.println((int)'\r');
		System.out.println((int)'\n');
		
		InputStream in = System.in;
		try {
			// int read() -> 아스키 코드
			while(true) {
				System.out.print((char)in.read());				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
```
## 라인을 읽으면 '\r''\n'이 같이 읽어짐
``` java
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOTest02 {
	public static void main(String[] args) {
		System.out.println((int)'\r');
		System.out.println((int)'\n');
		
		InputStream in = System.in;
		InputStreamReader isr = new InputStreamReader(in);
		try {
			// int read() -> 아스키 코드
			while(true) {
				System.out.print((char)isr.read());				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

```
## 파일내용 전체 읽어오는법 fr.read 가 -1 나올때까지 

```java

import java.io.File;
import java.io.FileInputStream;

public class IOTest03 {
	public static void main(String[] args) {
		String fileName = "data/chap06/io/test03.txt";
		File f = new File(fileName);
		
		try {
			FileInputStream fr = new FileInputStream(fileName);
//			FileReader fr = new FileReader(fileName); // 한글 처리 가능
			while(true) {
				//read의 결과가 -1이면 파일의 끝이다.
				int ch = fr.read();
				if(ch == -1) {
					break;
				}
				System.out.print((char)ch);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

```

## File에 내용을 적을땐 flush를 해야 중간에 내용이 들어가고 , 닫으면 남아있는 것들을 모두 파일에 쓰고 닫아짐.

```java

import java.io.FileOutputStream;
import java.io.FileWriter;

public class IOTest04 {
	public static void main(String[] args) {
		try {
			//한글 처리를 위해서는 FileWriter 사용해야함
			//FileOutputStream fos = new FileOutputStream("data/chap06/io/test04.txt", true);
			FileWriter fos = new FileWriter("data/chap06/io/test04.txt", true);
			fos.write(100);
			fos.write('d');
			fos.write('a');
			fos.write('가');
			fos.flush(); //닫지 않고 버퍼내용 출력시킬때.
			fos.close(); //버퍼를 닫아줘야 출력을시킴.
			
			System.out.println("파일 출력 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

```
## 파일 전체 복사하는 예시 (Buffer에 담아서 옮기는게 훨씬 빠름 - 하나씩 담아서 옮기지 않기때문에)

```java
package com.ssafy.java.day02.chap06_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


//파일 복사해보기

public class IOTest05 {
	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("data/chap06/io/test04.txt");
		FileWriter fw = new FileWriter("data/chap06/io/test05.txt");
		
		//속도향상
		BufferedReader bis = new BufferedReader(fr);
		BufferedWriter bos = new BufferedWriter(fw);
		while(true) {
			int ch = bis.read();
			if(ch == -1) {
				break;
			}
			bos.write(ch);
		}
		bis.close();
		bos.close();
		fw.flush();
		fr.close();
		fw.close();
	}
}
```
## 기본형 데이터 받아오고 출력하기
스트림으로 저장하고 받으면 4byte의 int나 8byte double, 2byte의 char를 한자한자 구분하지 않음

따라서 write(100)을 'd'로 저장하게 되는것임
Data로 시작하는것들을 이용하면 단위별 저장가능하지만 중요한것은 저장한 순서대로 읽어내야 제대로 값을 받아올수있음.

```java

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class IOTest07 {
	public static void main(String[] args) {
		//기본형 데이터 출력하기
		try {
			FileOutputStream fos = new FileOutputStream("data/chap06/io/test07.java");
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeInt(100);
			dos.writeChar('다');
			dos.writeDouble(3.14);
			dos.close();
			fos.close();
			System.out.println("파일 출력 성공");
			
			DataInputStream dis = new DataInputStream(new FileInputStream("data/chap06/io/test07.java"));
			System.out.println(dis.readDouble());
			System.out.println(dis.readInt());
			System.out.println(dis.readChar());
			dis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

```

## 리스트내용 저장해뒀다가 다시 가져오기

현재 가지고있는 list나 배열 등등 가지고있는 것을 끄고 켤때 저장하고 로딩하는것을 해야한다.
이를 위해 파일에 저장한다면 저장해두는 형식을 고려해야함. 이를 프로토콜이라 부름 저장하는 방식을 정해야 로딩할때 읽는방식이 정해지기때문이다.
따라서 밑에 코드에 save1, load1과 같이 구현을 해보았음

그러나 자바에서 Object 단위로 저장하고 불러오는것이 있음... 따라서 save2, load2를 보면 사용법이 나와있음.
!!중요!!한것은 byte단위로 직렬화하는 상속을 받아와야한다는것과 원치않는 인자를 빼는 것을 유심히 볼것

```java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

class User implements Serializable {
	private String id;
	private String name;
	private transient String pass; // 직렬화 제외시키려면 transient사용

	public User() {
		
	}
	
	public User(String id, String name, String pass) {
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	@Override
	public String toString() {
		return id + ":" + name + ":" + pass;
	}
	
	
}
public class IOTest08 {
	//save1 load1 처럼 읽고쓰는건 나만의 프로토콜( 약속 ) 그러나 객체단위 읽고쓰기 가능
	// >> save2, load2
	private static void save1(ArrayList<User> list) throws Exception {
		//user1.dat
		//id:a,name:aaa,pass:1234
		//a:aaa:1234
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/chap06/io/user1.dat"));
		for(User u : list) {
			bw.write(u.toString() + "\n");
		}
		bw.close();
	}
	private static ArrayList<User> load1() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("data/chap06/io/user1.dat"));
		StringTokenizer st;
		ArrayList<User> list = new ArrayList<>();
		while(true) {
			String line = br.readLine();
			if(line == null) {
				break;
			}
			st = new StringTokenizer(line, ":");
			list.add(new User(st.nextToken(),st.nextToken(),st.nextToken()));
		}
		br.close();
		
		return list;
	}
	
	private static void save2(ArrayList<User> list) throws Exception {
		FileOutputStream fos = new FileOutputStream("data/chap06/io/user2.dat");
		ObjectOutput oos = new ObjectOutputStream(fos);
		oos.writeObject(list);
		oos.close();
		fos.close();
	}
	
	private static ArrayList<User> load2() throws Exception {
		FileInputStream fis = new FileInputStream("data/chap06/io/user2.dat");
		ObjectInput ois = new ObjectInputStream(fis);
		ArrayList<User> list = (ArrayList<User>)ois.readObject();
		ois.close();
		fis.close();
		return list;
	}
	
	
	public static void main(String[] args) throws Exception {
		ArrayList<User> list = new ArrayList<>();
		list.add(new User("a","aaa","1234"));
		list.add(new User("b","bbb","2345"));
		list.add(new User("c","ccc","3456"));
		save1(list);
		System.out.println("파일 저장 완료");
		
		ArrayList<User>list2 = load1();
		for(User u : list2) {
			System.out.println(u);
		}
		System.out.println("파일 가져오기 완료");
		System.out.println("--------------------------------------");
		System.out.println("-----------ObjectInput 사용 -------------");
		
		save2(list);
		list2 = load2();
		for(User u : list2) {
			System.out.println(u);
		}
		
		System.out.println("-----------ObjectInput 사용 끝 -------------");
		//Exception in thread "main" java.io.NotSerializableException: com.ssafy.java.day02.chap06_io.User
		//직렬화 오류발생!!
		//따라서 User를 Serializable 할 수 있게 해줘야함. (implements Serializable)
	}
}


```
