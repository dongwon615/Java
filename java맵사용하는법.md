# 맵을 이용하는 방법
## 맵 만들고 입력하기
맵에 대한 전반적인 코드가 포함되어있으며, 정렬과 전체 탐색등을 이용해서 알고리즘 문제 풀면 Good
``` java
/**
 * Map
 *  - HashMap, TreeMap
 *  - key, value 쌍으로 관리된다.
 *  - key는 중복되지 않는다.
 *  - put(key, value)
 *  - get(key)
 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * 맵이 VO를 대신할 수 있다. Value Object
class Member {
	String id;
	String name;
}

Map<String, String> map = new HashMap<>();
		map.put("id", "sbc");
		map.put("name", "홍");
		


*/
public class Test07 {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("id", "sbc");
		map.put("name", "홍");
		System.out.println(map);
		
		String id = map.get("id");
		System.out.println("id -> " + id);
		
		//없는 키값은 null을 준다.
		String email = map.get("email");
		System.out.println("email -> " + email);
		
		//맵은 키값이 중복되면 기존데이터값을 덮어 새로운 값으로 대체하며 기존값을 return 한다.
		System.out.println(map.put("name", "김"));
		System.out.println(map);
		
		//키와 값이 있는지
		System.out.println(map.containsKey("id"));
		System.out.println(map.containsValue("aaa"));
		
        map.reove("a");

		//TreeMap을 사용하면 key 값 정렬이 가능함.
		Map<Integer, String> map2 = new TreeMap<>();
		map2.put(1, "a");
		map2.put(5, "b");
		map2.put(3, "c");
		map2.put(19, "d");
		map2.put(8, "e");
		map2.put(27, "f");
		System.out.println(map2);
		
		//문자열은 한자한자 나누어서 나열함.
		Map<String, String> map3 = new TreeMap<>();
		map3.put("1", "a");
		map3.put("5", "b");
		map3.put("3", "c");
		map3.put("19", "d");
		map3.put("8", "e");
		map3.put("27", "f");
		System.out.println(map3);
		
	}
}
```

## 맵 전체 값 접근하는법
``` java

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test08 {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("a", "aaa");
		map.put("b", "bbb");
		map.put("c", "ccc");
		map.put("d", "ddd");
		
		//1. key 접근
		Set<String> keys = map.keySet();
		System.out.println(keys);
		
		//2. value 접근
		Collection<String> values = map.values();
		System.out.println(values);
		
		//3. key와 value 동시접근
		keys = map.keySet();
		for(String key : keys) {
			System.out.println(key + "-" + map.get(key));
		}
		
		Set<Entry<String, String>> datas = map.entrySet();
		for(Entry entry : datas) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	

		map.forEach((k,v) -> {System.out.println(k + "---" + v);});
	}
}

```
