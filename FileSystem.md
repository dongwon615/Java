# 자바를 이용해서 파일 컨트롤

## 단지 필요한 자바를 쓸 때 필요한 파일을 읽어오고 쓰는 걸 넘어서서 클라우드 시스템과 같은것을 만들 때 사용하는 것들을 생각해볼 수 있음.


### 디렉토리 읽어오고 만약 없다면 새로 생성할 수 있음. 특히 날짜와시간을 넣으면 실제로 폴더 내에 생성할 수 있는 폴더수가 정해져있기때문에 이러한 부분 해결가능.
```java

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest01 {
	public static void main(String[] args) {
		Date d = new Date();  //yyyyMMddHH
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		String s = sdf.format(d);
		System.out.println(s);
		//File 자체의 메타 정보 추출하기
		File f = new File("data/chap06/io"+ s);
		if(f.exists()) {
			System.out.println("있다");
		}else {
			System.out.println("없다");
//			System.out.println(f.mkdir());
			System.out.println(f.mkdirs());
		}
	}
}
```

### 파일 생성과 삭제

``` java

import java.io.File;

public class FileTest02 {
	public static void main(String[] args) {
		File f = new File("data");
		if(f.exists()) {
			System.out.println("있다");
			// 디렉토리인지 파일인지 판단하기
			if(f.isDirectory()) System.out.println("디렉토리임");
			if(f.isFile()) System.out.println("파일임");
			
			File child = new File(f, "test.txt");
			try {
				if(child.exists()) {
					if(child.delete()) {   //삭제
						System.out.println("파일삭제성공");
					}else {
						System.out.println("파일삭제실패");
					}
				}
				
				if(child.createNewFile()) { // 파일이 없는 경우 생성
					System.out.println("파일 생성 성공");
				}else {
					System.out.println("파일 생성 실패");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("없다");
		}
	}
}
```

### 중복된 이름을 조금씩 바꿔서 저장하는거 (다운로드할때1,2,붙여서 나오는거 만들때 renameTo 쓴다)
``` java

import java.io.File;

public class FileTest03 {
	public static void main(String[] args) {
		File f = new File("data");
		//중복된 파일이 저장되거나 할때 renameTo를 통해 다르게 저장
		f.renameTo(new File("data3"));
		
		System.out.println("종료");
	}
}

```

### File 클래스에서 알아둬야할 몇가지 메서드

```java
import java.io.File;

public class FileTest04 {
	public static void main(String[] args) {
		//알아야할 몇가지 메서드
		File f = new File("c:/test/aaa/test.txt");
		System.out.println(f.getName());
		System.out.println(f.getParent());
		System.out.println(f.getPath());
	}
}
```
### 디렉토리 리스트 출력하는법

```java

import java.io.File;

public class FileTest05 {
	//file이 디렉토리인 경우 list 알아보기
	public static void main(String[] args) {
		File f = new File("c:/SSAFY");
		System.out.println(f.isDirectory());
		
		// 파일 객체로 매번 받아쓰다보니 api가 있음
		/*
		String[] list = f.list();
		for(String s : list) {
			File child = new File(f,s);
			String type = "F : ";
			if(child.isDirectory()) {
				type = "D : ";
			}
			System.out.println(type + s);
			
		}
		 */
		
		//파일안에 listFiles() 메서드 이용해서 위와 동일한 구현 + size 추가
		File[] list = f.listFiles();
		for(File child : list) {
			String type = "F : ";
			String size = "";
			if(child.isDirectory()) {
				type = "D : ";
			}else {
				size = child.length() + "bytes";
			}
			System.out.println(type + child.getName() + " " + size);
		}
	}
}

```
