package com.cetrinw.tools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;

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
