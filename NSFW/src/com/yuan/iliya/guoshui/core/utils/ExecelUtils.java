package com.yuan.iliya.guoshui.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yuan.iliya.guoshui.nsfw.user.entity.User;

public class ExecelUtils {
	private static ExecelUtils execelUtils = new ExecelUtils();
	
	
	public HSSFWorkbook getHssfWorkbook(){
		return new HSSFWorkbook();
	}
	
	public XSSFWorkbook getXssfWorkbook(){
		return new XSSFWorkbook();
	}
	
	public boolean is03Execel(String fileName){
		if (fileName.matches("^.*\\.(?i)(xls)$")) {
			return true;
		}
		
		return false;
	}
	
	public boolean is07Execel(String fileName){
		if (fileName.matches("^.*\\.(?i)(xlsx)$")) {
			return true;
		}
		
		return false;
	}
	
	public Workbook getWorkbook(String fileName){
		if (is03Execel(fileName)) {
			return getHssfWorkbook();
			
		}
		if (is07Execel(fileName)) {
			return getXssfWorkbook();
			
		}
		
		return null;
	}
	
	public static void exportToXls(List<User> users, OutputStream outputStream) {
		Workbook workbook = execelUtils.getHssfWorkbook();
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);

		// 头标题样式
		CellStyle cellStyle = createCellStyle(workbook, 16);

		CellStyle cellStyle2 = createCellStyle(workbook, 13);

		Sheet sheet = workbook.createSheet("用户列表");
		sheet.setDefaultColumnWidth(25);
		sheet.addMergedRegion(cellRangeAddress);
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("用户列表");

		Row row1 = createRow(sheet, 1);
		createCell(sheet, 0, "用户名", cellStyle2, row1);
		createCell(sheet, 1, "账号", cellStyle2, row1);
		createCell(sheet, 2, "所属部门", cellStyle2, row1);
		createCell(sheet, 3, "性别", cellStyle2, row1);
		createCell(sheet, 4, "电子邮箱", cellStyle2, row1);

		if (users != null && users.size() > 0) {
			for (int i = 0; i < users.size(); i++) {
				Row row2 = createRow(sheet, i + 2);
				createCell(sheet, 0, users.get(i).getUsername(), cellStyle2, row2);
				createCell(sheet, 1, users.get(i).getAccount(), cellStyle2, row2);
				createCell(sheet, 2, users.get(i).getDepartment(), cellStyle2, row2);
				createCell(sheet, 3, users.get(i).isGender() ? "男" : "女", cellStyle2, row2);
				createCell(sheet, 4, users.get(i).getEmail(), cellStyle2, row2);
			}
		}

		try {
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 创建单元格样式
	 * @param workbook 工作簿
	 * @param fontsize 字号
	 * @return
	 */
	private static CellStyle createCellStyle(Workbook workbook, int fontsize) {
		// 头标题样式
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 创建字体
		Font font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)fontsize);
		cellStyle.setFont(font);
		return cellStyle;
	}
	
	private static void createCell(Sheet sheet, int cellIndex , String value, CellStyle cellStyle,Row row){
		
		Cell cell = row.createCell(cellIndex);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}
	
	private static Row createRow(Sheet sheet, int index){
		Row row= sheet.createRow(index);
		return row;
	}
	
	
	public static List<User> importExeclToUsers(File file,String fileName){
		List<User> users = new ArrayList<User>();
		
		
		try {
			FileInputStream inputStream = new FileInputStream(file);
			Workbook workbook = execelUtils.is03Execel(fileName)?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() > 2) {
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					User user = new User();
					Row row = sheet.getRow(i);
					
					Cell cell = row.getCell(0);
					user.setUsername(cell.getStringCellValue());
					
					String account = "";
					Cell cell1 = row.getCell(1);
					try {
						account  = cell1.getStringCellValue();
						
					} catch (Exception e) {
						double dMoble= cell1.getNumericCellValue();
						account = BigDecimal.valueOf(dMoble).toString();
						
					}
					user.setAccount(account);
					
					Cell cell2 = row.getCell(2);
					user.setDepartment(cell2.getStringCellValue());
					
					Cell cell3 = row.getCell(3);
					user.setGender(cell3.getStringCellValue().equals("男"));
					
					String mobile = "";
					Cell cell4 = row.getCell(4);
					try {
						mobile  = cell4.getStringCellValue();
						
					} catch (Exception e) {
						double dMoble= cell4.getNumericCellValue();
						mobile = BigDecimal.valueOf(dMoble).toString();
						
					}
					user.setMobileNumber(mobile);
					
					Cell cell5 = row.getCell(5);
					user.setEmail(cell5.getStringCellValue());
					
					Cell cell6 = row.getCell(6);
					if (cell6.getDateCellValue() != null ) {
						user.setBirthday(cell6.getDateCellValue());
						
					}
					
					user.setPassword("123456");
					user.setState(User.USER_VALID);
					
					users.add(user);
				}
			}
			
			workbook.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return users;
	}
	
	
	
	
	
	

}
