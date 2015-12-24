package com.cetrinw.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	/**
	 * 读取Excel文件,取得表格内容
	 * 
	 * @param fileName
	 *            文件路径
	 * @return List<String>
	 */
	public List<String> readExcel(String fileName) {
		List<String> list = new ArrayList<String>();

		Sheet sheet = getWorkbook(fileName).getSheetAt(0); // 获得第一个表单
		Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
		while (rows.hasNext()) {
			Row row = rows.next(); // 获得行数据
			// System.out.println("Row #" + row.getRowNum()); //获得行号从0开始
			Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
			while (cells.hasNext()) {
				Cell cell = cells.next();
				if (cell.getColumnIndex() == 2 && cell.getRowIndex() > 1) {
					list.add(cell.getStringCellValue());
				}
				/**
				 * System.out.println("Cell #" + cell.getColumnIndex()); switch
				 * (cell.getCellType()) { //根据cell中的类型来输出数据 case
				 * HSSFCell.CELL_TYPE_NUMERIC:
				 * System.out.println(cell.getNumericCellValue()); break; case
				 * HSSFCell.CELL_TYPE_STRING:
				 * System.out.println(cell.getStringCellValue()); break; case
				 * HSSFCell.CELL_TYPE_BOOLEAN:
				 * System.out.println(cell.getBooleanCellValue()); break; case
				 * HSSFCell.CELL_TYPE_FORMULA:
				 * System.out.println(cell.getCellFormula()); break; default:
				 * System.out.println("unsuported sell type"); break; }
				 */
			}
		}

		return list;
	}

	private static Workbook getWorkbook(String fileName) {
		boolean isE2007 = false; // 判断是否是excel2007格式
		if (fileName.endsWith("xlsx"))
			isE2007 = true;
		InputStream input;
		Workbook wb = null;
		try {
			input = new FileInputStream(fileName);
			if (isE2007)
				wb = new XSSFWorkbook(input);
			else
				wb = new HSSFWorkbook(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 根据文件格式(2003或者2007)来初始化
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}
}
