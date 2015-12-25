package com.cetrinw.app;

import java.util.HashMap;
import java.util.Map;

public class MapApp {
	
	public static void main(String[] args) {
		Map<String,String> hashMap = new HashMap<String, String>();
		  for (String key : hashMap.keySet()) {
			    String id = key;
			    String content = hashMap.get(key);
			  System.out.println(id+","+content);
		    }
	}
	  
}
