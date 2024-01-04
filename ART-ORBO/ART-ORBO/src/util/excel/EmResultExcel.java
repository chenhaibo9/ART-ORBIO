package util.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class EmResultExcel {
//	public static void main(String[] args) {
//		Excel excel = new Excel();
//		HSSFSheet sheettest = excel.createSheet("test");
//		String[][] styles = genValues();
//		excel.writeCell2Sheet(sheettest, styles);
//		
//		String[][] values={{"1","2"},{"11","22"}};
//		excel.writeCell2Sheet(sheettest, values, 1, 1);
//		excel.saveAsFile("D://test.xls");
//	}
	private static Excel excel = new Excel();

	public static void save(String sheetName,String[][] values){
		HSSFSheet sheettest = excel.createSheet(sheetName);
		String[][] styles = genValues();
		excel.writeCell2Sheet(sheettest, styles);
		excel.writeCell2Sheet(sheettest, values, 1, 1);
		//excel.saveAsFile("D://test.xls");
	}
	public static void save(String fileName){
		excel.saveAsFile(fileName);
	}
	public static String[][] genValues() {
		String[][] values = new String[41][201];
		for (int i = 0; i < 41; i++) {
			for (int j = 0; j < 201; j++) {
				if (i == 0 && j == 0) {
					values[i][j] = "";
				}

				if (i == 0 && j != 0) {
					values[i][j] = j + "";
				}
				if (j == 0 && i != 0) {
					values[i][j] = (((i-1) % 8 ) * 500+500) + "";
					
				}

			}
		}
		return values;
	}
}
