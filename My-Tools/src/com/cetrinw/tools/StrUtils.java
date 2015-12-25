package com.cetrinw.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.prepare.utils.DateTimeUtils;

/**
 * String operate
 * 
 * @author Cetrin Wang
 *
 */
public class StrUtils {

	/**
	 * BLOB -> String
	 * 
	 * @param blob
	 * @return
	 */
	private static String blob2String(Blob blob) {
		try {
			if (blob == null || blob.length() == 0) {
				return "";
			} else {
				String content = new String(blob.getBytes((long) 1,
						(int) blob.length()));
				return content.trim();
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * String -> Blob
	 * @param str
	 * @return 
	 */
	public static InputStream string2Blob(String str) {
		InputStream inputStream = null;
		try {
			if (str != null) {
				inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException(e.getMessage());
		}
		return inputStream;
	}
	
	/**
	 * inputstream -> String 
	 * @param in
	 * @return
	 */
	public static String inStream2string(InputStream in) {
		StringBuffer buffer = new StringBuffer();
		try {
			if (in != null) {
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String line = "";
				while ((line = bf.readLine()) != null) {
					buffer.append(line);
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException(e.getMessage());
		}
		return buffer.toString();
	}
	
	public static Date str2date(String dateStr) throws ParseException {
		dateStr = dateStr.replace("/", "-");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		Date date = new Date();
		String years = new Integer(year).toString().substring(0, 2);
		String[] dt = dateStr.split(" ");
		String t = null, d = null;
		if (dt.length == 2) {
			d = dt[0];
			t = dt[1];
		} else {
			d = dt[0];
		}
		// date 规范
		String[] da = d.split("-");
		if (da[0].length() == 2) {// 年
			da[0] = years + da[0];
		}
		d = da[0] + "-" + da[1] + "-" + da[2];
		if (t != null) {
			String[] ta = d.split(":");
			if (ta.length == 2) {// 年
				t = t + ":00";
			}
		}
		if (d != null && t != null) {
			dateStr = d + " " + t;
		} else {
			dateStr = d + " 00:00:00";
		}
		Date time = DateTimeUtils.strToDate(dateStr, new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"));
		return time;
	}
	
	/**
	 * remove special character by regEx
	 * @param str
	 * @return
	 */
	public static String formatContent(String str){
		String content = "";
		try {
			if(str == null || "".equals(str)){
				return "";
			}else{
				String regEx_html = "<(.[^>]*)>"; 
			    Pattern p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
			    Matcher m_html = p_html.matcher(str); 
			    content = m_html.replaceAll(""); // 
			    
			    return content.trim();
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * copy str
	 * @param str
	 * @param length
	 * @return
	 */
	public static String copyString(String str, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += str + ",";
		}
		return result.substring(0, result.length() - 1);
	}
}
