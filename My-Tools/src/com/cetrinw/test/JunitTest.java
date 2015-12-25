package com.cetrinw.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JunitTest {

	@org.junit.Test
	public void testRegEx() {
		
		//xxx.html : xxx.php : xxx.htm ： xxx.shtml
		String regEx = "\\S+\\.(html|shtml|htm|php)";
		String content1 = "http://google.com/ind123.php";
		String content2 = "http://cetrinw.com/ind223ex.html";
		String content3 = "http://cetrinw.com/ind23ex.htm";
		
		
		Pattern p_html = Pattern.compile(regEx);
		Matcher result1 = p_html.matcher(content1);
		Matcher result2 = p_html.matcher(content2);
		Matcher result3 = p_html.matcher(content3);
		
		if(result1.find()){
			System.out.println(result1.group(0) +":find");
		}
		if(result2.find()){
			System.out.println(result2.group(0) +":find");
		}
		if(result3.find()){
			System.out.println(result3.group(0) +":find");
		}
	}
}
