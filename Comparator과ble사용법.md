# Comparator 과 Comparable 사용법
## 익명클래스 사용하는경우 : 주석
일반적으로는 이렇게 사용하면 될거같고
## 클래스 자체에 Comparable 사용하는 방법
Data Class 안에 값을 항상 이렇게 정렬하려면 Comparable 설정해주면 될거같음
``` java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/*
class Data{
	String key;
	String value;
	public Data(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
*/
class Data implements Comparable<Data>{
	String key;
	String value;
	public Data(String key, String value) {
		this.key = key;
		this.value = value;
	}
	@Override
	public int compareTo(Data o) {
		if(Integer.parseInt(value) < Integer.parseInt(o.value)) {
			return 1;
		}
		return -1;
	}

}
public class Test09 {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		
		map.put("A", "29");
		map.put("C", "19");
		map.put("D", "31");
		map.put("B", "15");
		
		List <Data> list = new ArrayList<>();
		for(Entry<String, String> e : map.entrySet()) {
			list.add(new Data(e.getKey(),e.getValue()));
		}
		Collections.sort(list);
		/*
		Collections.sort(list,new Comparator<Data>() {

			@Override
			public int compare(Data o1, Data o2) {
				if(Integer.parseInt(o1.value) < Integer.parseInt(o2.value)) {
					return 1;
				}
				return -1;
			}
		});
		*/
		for(Data d : list) {
			System.out.println("Key : " + d.key + ", Value : " + d.value);
		}
		
	}
}
```
