package com.cetrinw.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * File Operate
 * @author Cetrin Wang
 *
 */
public class FileUtils {
	
	/**
	 * 将List -> file
	 * 
	 * @param list
	 * @param filePath
	 */
	public void write2Txt(List<String> list, String filePath) {

		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath);
			for (String strs : list) {
				writer.write(strs);
				writer.write("\r\n");
				writer.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
