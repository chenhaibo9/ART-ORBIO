package util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Map;

/**
 * Created by xijiaxiang on 2017/6/8.
 */
public class Excel {

	private HSSFWorkbook wb = new HSSFWorkbook();

	public HSSFSheet createSheet(String sheetName) {
		return wb.createSheet(sheetName);
	}

	public void writeCell2Sheet(HSSFSheet sheet,String[][] values ){
		//(x,y) refers to each cell
		if(values==null||values.length==0){
			return;
		}
		
		
		for(int i=0;i<values.length;i++){
			HSSFRow row = sheet.createRow(i);
			for(int j=0;j<values[i].length;j++){
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(values[i][j]);
			}
		}
	}
	public void writeCell2Sheet(HSSFSheet sheet,String[][] values,int rowPadding,int columnPadding){
		//x y 偏移量
		for(int i=0;i<values.length;i++){
			HSSFRow row = sheet.getRow(i+rowPadding);
			for(int j=0;j<values[i].length;j++){
				HSSFCell cell = row.createCell(j+columnPadding);
				cell.setCellValue(values[i][j]);
			}
		}
	}
	
	public File saveAsFile(String fileName){
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream os;
		try {
			os = new FileOutputStream(file);
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}
	public static void main(String[] args) {
		
	}
	
//	public static File createExcel() throws IOException {
//		// Map<String, AreaCount> maps = dayDeal.getAreas();//每日成交量数据
//		NumberFormat numberFormat = NumberFormat.getNumberInstance();//
//		numberFormat.setMaximumFractionDigits(2);// 保留两位小数
//		numberFormat.setGroupingUsed(false);
//		//
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("成交量");
//
//		// 设置六列的宽度
//		for (int i = 0; i < dayDeal.getAreas().size() + 2; i++) {
//			sheet.setColumnWidth(i, 4000);
//		}
//
//		// 创建字体样式Verdana
//		HSSFFont font = new FontAndStyle().new Font(wb, "Verdana").getFont();
//		// 创建单元格样式24 47
//		HSSFCellStyle styleblue = new FontAndStyle().new CellStyle(wb, 24).getStyle();
//		HSSFCellStyle styleyellow = new FontAndStyle().new CellStyle(wb, 47).getStyle();
//
//		HSSFCellStyle styledefault = wb.createCellStyle();
//		styledefault.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		styledefault.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		// styledefault.setFillBackgroundColor(HSSFColor.WHITE.index);
//		// styledefault.setFillForegroundColor(HSSFColor.WHITE.index);
//		// styledefault.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//		// 设置边框
//		/*
//		 * style.setBottomBorderColor(HSSFColor.RED.index);
//		 * style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		 * style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		 * style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		 * style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		 */
//
//		styleblue.setFont(font);// 设置字体
//		styleyellow.setFont(font);
//		styledefault.setFont(font);
//
//		// 创建标题一行，styleblue
//		HSSFRow row = sheet.createRow(0);
//		row.setHeight((short) 500);// 设定行的高度
//		HSSFCell cell = row.createCell(0);
//		cell.setCellStyle(styleblue);
//		cell.setCellValue("住宅");
//		int count = 1;
//		for (String key : dayDeal.getAreas().keySet()) {
//			cell = row.createCell(count);
//			cell.setCellStyle(styleblue);
//			cell.setCellValue(dayDeal.getAreas().get(key).getAreaName());
//			count++;
//		}
//		cell = row.createCell(count);
//		cell.setCellStyle(styleblue);
//		cell.setCellValue("合计");
//
//		// 四行数据
//		row = sheet.createRow(1);// 套数
//		row.setHeight((short) 500);// 设定行的高度
//
//		cell = row.createCell(0);
//		cell.setCellStyle(styleyellow);
//		cell.setCellValue("成交套数");
//		count = 1;
//		int counttaoshu = 0;
//		for (String key : dayDeal.getAreas().keySet()) {
//			cell = row.createCell(count);
//			cell.setCellStyle(styledefault);
//			int temp = dayDeal.getAreas().get(key).getZhu();
//			cell.setCellValue(temp);
//			counttaoshu += temp;
//			count++;
//		}
//		cell = row.createCell(count);
//		cell.setCellStyle(styledefault);
//		cell.setCellValue(counttaoshu);
//
//		row = sheet.createRow(2);// 面积
//		row.setHeight((short) 500);// 设定行的高度
//
//		cell = row.createCell(0);
//		cell.setCellStyle(styleyellow);
//		cell.setCellValue("成交面积");
//		count = 1;
//		double countarea = 0.0;
//		for (String key : dayDeal.getAreas().keySet()) {
//			cell = row.createCell(count);
//			cell.setCellStyle(styledefault);
//			double temp = dayDeal.getAreas().get(key).getZhu_Area();
//			cell.setCellValue(numberFormat.format(temp));
//			countarea += temp;
//			count++;
//		}
//		cell = row.createCell(count);
//		cell.setCellStyle(styledefault);
//		cell.setCellValue(numberFormat.format(countarea));
//
//		row = sheet.createRow(3);// 均套面积
//		row.setHeight((short) 500);// 设定行的高度
//
//		cell = row.createCell(0);
//		cell.setCellStyle(styleyellow);
//		cell.setCellValue("均套面积");
//		count = 1;
//		for (String key : dayDeal.getAreas().keySet()) {
//			cell = row.createCell(count);
//			cell.setCellStyle(styledefault);
//			if (dayDeal.getAreas().get(key).getZhu() == 0) {
//				cell.setCellValue(0.00);
//			} else {
//				cell.setCellValue(numberFormat.format(
//						dayDeal.getAreas().get(key).getZhu_Area() / (double) dayDeal.getAreas().get(key).getZhu()));
//			}
//			count++;
//		}
//		cell = row.createCell(count);
//		cell.setCellStyle(styledefault);
//		if (counttaoshu != 0) {
//			cell.setCellValue(numberFormat.format(countarea / (double) counttaoshu));
//		} else {
//			cell.setCellValue(0.00);
//		}
//
//		// 下面非住宅
//		row = sheet.createRow(6);
//		row.setHeight((short) 500);// 设定行的高度
//		// row.setRowStyle(styleblue);
//		cell = row.createCell(0);
//		cell.setCellStyle(styleblue);
//		cell.setCellValue("非住宅");
//		count = 1;
//		for (String key : dayDeal.getAreas().keySet()) {
//			cell = row.createCell(count);
//			cell.setCellStyle(styleblue);
//			cell.setCellValue(dayDeal.getAreas().get(key).getAreaName());
//			count++;
//		}
//		cell = row.createCell(count);
//		cell.setCellStyle(styleblue);
//		cell.setCellValue("合计");
//
//		// 四行数据
//		row = sheet.createRow(7);// 套数
//		row.setHeight((short) 500);// 设定行的高度
//
//		cell = row.createCell(0);
//		cell.setCellStyle(styleyellow);
//		cell.setCellValue("成交套数");
//		count = 1;
//		counttaoshu = 0;
//		for (String key : dayDeal.getAreas().keySet()) {
//			cell = row.createCell(count);
//			cell.setCellStyle(styledefault);
//			counttaoshu += dayDeal.getAreas().get(key).getFeiZhu();
//			cell.setCellValue(dayDeal.getAreas().get(key).getFeiZhu());
//			count++;
//		}
//		cell = row.createCell(count);
//		cell.setCellStyle(styledefault);
//		cell.setCellValue(counttaoshu);
//
//		row = sheet.createRow(8);// 面积
//		row.setHeight((short) 500);// 设定行的高度
//
//		cell = row.createCell(0);
//		cell.setCellStyle(styleyellow);
//		cell.setCellValue("成交面积");
//		count = 1;
//		countarea = 0;
//		for (String key : dayDeal.getAreas().keySet()) {
//			cell = row.createCell(count);
//			cell.setCellStyle(styledefault);
//			countarea += dayDeal.getAreas().get(key).getFeiZhu_Area();
//			cell.setCellValue(numberFormat.format(dayDeal.getAreas().get(key).getFeiZhu_Area()));
//			count++;
//		}
//		cell = row.createCell(count);
//		cell.setCellStyle(styledefault);
//		cell.setCellValue(numberFormat.format(countarea));
//
//		row = sheet.createRow(9);// 均套面积
//		row.setHeight((short) 500);// 设定行的高度
//
//		cell = row.createCell(0);
//		cell.setCellStyle(styleyellow);
//		cell.setCellValue("均套面积");
//		count = 1;
//		for (String key : dayDeal.getAreas().keySet()) {
//			cell = row.createCell(count);
//			cell.setCellStyle(styledefault);
//			if (dayDeal.getAreas().get(key).getFeiZhu() == 0) {
//				cell.setCellValue(0.00);
//			} else {
//				cell.setCellValue(numberFormat.format(dayDeal.getAreas().get(key).getFeiZhu_Area()
//						/ (double) dayDeal.getAreas().get(key).getFeiZhu()));
//			}
//			count++;
//		}
//		cell = row.createCell(count);
//		cell.setCellStyle(styledefault);
//		if (counttaoshu != 0) {
//			cell.setCellValue(numberFormat.format(countarea / (counttaoshu)));
//		} else {
//			cell.setCellValue(0.00);
//		}
//
//		String filePath = Property.getProperty("fileLocation", "excelLocation");
//		File file = new File(filePath + fileName);
//		if (file.exists()) {
//			file.delete();
//		} else {
//			file.createNewFile();
//		}
//		FileOutputStream os = new FileOutputStream(file);
//		wb.write(os);
//		os.close();
//
//		return file;
//	}

	
}
