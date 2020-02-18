# List와 Set 를 이용한 iterator, stream, ramda 사용법 
## 우선 interface 개념을 이용해서 List는 ArrayList와 LinkedList 상관없이 사용 (Set도 동일함. HashSet..)

1. List의 iterator 함수 사용형태
2. Stream을 이용해서 forEach함수 사용하기.
comparator, comparable 사용하듯 consumer 사용하기
3. 람다함수는 메소드가 하나인 인터페이스라고하는데,,
쨋든 (매개변수) -> {원하는 함수코딩;} 하면 사용가능

``` java

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Test02 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		//list2는 수정불가
		//List<String> list2 = Arrays.asList("a","b","c");
		
		System.out.println(list);
		
		// iterator 사용하는방법
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		
		//함수형 프로그래밍
		//함수형프로그래밍.1 ( 내부에서 만들어서 - 한번만 쓸꺼면)
		list.stream().forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.print(t + " ");
			}
		});	
		System.out.println();
		
		//함수형프로그래밍.1 ( 클래스 생성해서 )	
		list.stream().forEach(new MyConsumer());
		System.out.println();
		
		//스트림에서 람다사용.
		list.stream().forEach(t -> System.out.print(t + " "));
		//매개변수가 여러개 + 실행문이 여러문장  (t) ->  {System.out.print(t + " ");}
		
		
	}
}
class MyConsumer implements Consumer{

	@Override
	public void accept(Object t) {
		System.out.print(t + " ");
	}
	
}
```
##Set도 나머지 대부분이 동일하지만  hashcode와 equals 를 오버라이딩해줘야하는부분만 확인따로함
``` java
package com.ssafy.java.day01.chap04_collection;

import java.util.HashSet;
import java.util.Set;

class MySet{
	int val;
	public MySet(int val) {
		this.val = val;
	}
	
	/*
	 //객체가 같은지 판단
	public int hashCode() {
		return this.val;
	}
	public boolean equals(Object o) {
		// o가 MySet 타입인지 확인, null도 아닌지 등등 더해야함
		return true;
	}
	*/
}
public class Test04 {
	public static void main(String[] args) {
		Set<MySet> set = new HashSet<>();
		set.add(new MySet(1));
		set.add(new MySet(2));
		set.add(new MySet(2));
		set.add(new MySet(3));
		//4가 출력된다
		System.out.println(set.size());
		//위에 주석풀면 3이 됨.
		
	}
}
```
