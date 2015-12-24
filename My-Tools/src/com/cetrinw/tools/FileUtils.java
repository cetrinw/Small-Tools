package com.cetrinw.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * File Operate
 * 
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

	/**
	 * reader text content
	 */
	 public static List<String> readTransData(String... filenames) {
		 List<String> list = new ArrayList<String>();
	      
	        // 从文件读入
	        if (filenames.length > 0) {
	            for (String filename : filenames) {
	                try {
	                   // FileReader fr = new FileReader(new File(filename));
	                	InputStreamReader fr=new InputStreamReader(new FileInputStream(filename),"UTF-8");
	                    BufferedReader br = new BufferedReader(fr);
	                    String line = null;
	                    while ((line = br.readLine()) != null) {
	                        if (line.trim() != "") {	                
	                            String[] items = line.split(" ");
	                            for (int i = 0; i < items.length; i++) {
	                            	list.add(items[i]);
								}
	                        }
	                    }
	                } catch (IOException e) {
	                    System.out.println("读取事务数据库失败。");
	                    System.exit(-2);
	                }
	            }
	        }
			return list;
	}
	
	/**
	 * Delete file
	 * @param sPath
	 */
	public static void deleteFile(String sPath) {
		File file = new File(sPath);

		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
}
