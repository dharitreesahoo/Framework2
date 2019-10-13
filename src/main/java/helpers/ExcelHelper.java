package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	public static String TESTDATA_SHEET_PATH = "D:/extentREport/com.swtestacademy/resources/configfile/testData.xlsx";

	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		try {
			FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH);

			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);
			
			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0; i<sheet.getLastRowNum(); i++){
				for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++){
					data[i][k] = sheet.getRow(i+1).getCell(k).toString();
					
				}
			}
			
			return data;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	
	public ArrayList<String> extractExcelContentByColumnIndex(int columnIndex){
        ArrayList<String> columndata = null;
        try {
            File f = new File("D:/extentREport/com.swtestacademy/resources/configfile/testData.xlsx");
            FileInputStream ios = new FileInputStream(f);
            XSSFWorkbook workbook = new XSSFWorkbook(ios);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            columndata = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if(row.getRowNum() > 0){ //To filter column headings
                        if(cell.getColumnIndex() == columnIndex){// To match column index
                            switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                columndata.add(cell.getNumericCellValue()+"");
                                break;
                            case Cell.CELL_TYPE_STRING:
                                columndata.add(cell.getStringCellValue());
                                break;
                            }
                        }
                    }
                }
            }
            ios.close();
            System.out.println(columndata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columndata;
    }
	
	public static void main(String[] args) {
		ExcelHelper ex =  new ExcelHelper();
		Object[][] data = ex.getTestData("Login");
				
		/*ArrayList<String> TCs = ex.extractExcelContentByColumnIndex(0);
		int count=0;
		int rowNo = 0;
		System.out.println();
		
		for (String tc:TCs)
		{
			count=count+1;
			if(tc.equals("TC_003"))
			{
				rowNo = count;
			}
		}
		System.out.println(rowNo);*/
	}


}
