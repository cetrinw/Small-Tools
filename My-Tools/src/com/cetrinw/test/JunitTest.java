package com.cetrinw.test;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class JunitTest {

	@org.junit.Test
	public void testRegEx() {
		
		//xxx.html : xxx.php : xxx.htm ： xxx.shtml
		//$  == String.EndWith
		String regEx = "\\S+\\.(html|shtml|htm|php)$";
		String content1 = "http://google.com/ind123.php/12312";
		String content2 = "http://cetrinw.com/ind223ex.html";
		String content3 = "http://cetrinw.com/ind23ex.htm/345";
		
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
	
	@Test
	public void testStrLength(){
		String str= "id,DATA_NUM,DATA_DT,DATA_SEQ,REL_DATE,WEB_TYPE,KEY_WD,BRAND,INDUSTRY,THEMEKEY_WD,THEME_CX,THEME_RG,PRODUCT,LINK_LOC,PROVINCE,CITY,TITLE,CONTENT,CON_SUM,LINK,RAW_TYPE";
		
		System.out.println(str.split(",").length);
	}
	
	@Test
	public void BigDe(){
		BigDecimal b1 = new BigDecimal("4.5830396368766036E-7");		
		BigDecimal b2 = new BigDecimal("0.00000045830396368766036");

		System.out.println("b1:"+b1);
		System.out.println("b2:"+b2);
	}
}
