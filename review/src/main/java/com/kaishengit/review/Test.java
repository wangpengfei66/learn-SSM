package com.kaishengit.review;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test {

	public static void main(String[] args) {

		/*Map<String, String> maps = new HashMap<>();
		maps.put("name", "tom");
		maps.put("age", "18");
		maps.put("address", "北京");
		maps.put("tel", "123");
		
		Set<Entry<String, String>> sets = maps.entrySet();
		for(Entry<String,String> set : sets) {
			System.out.println(set.getKey() + "-->" + set.getValue());
			
		}*/
		
		StringBuffer sb = new StringBuffer("aa");
		for(int i = 0;i < 1000; i ++) {
			sb.append("jj");
		}
		
	}

}
