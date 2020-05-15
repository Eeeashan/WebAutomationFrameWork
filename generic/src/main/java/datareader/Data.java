package datareader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Data {
    static Workbook book;
    static Sheet sheet;
    public static String testDataPath = "C:\\Users\\easha\\OneDrive\\Desktop\\Automation\\WebAutomationFrameWork\\automationpractice\\testData\\TestData.xlsx";


    public static Object[][] getTestData(String sheetName) throws IOException, InvalidFormatException {
        FileInputStream file = null;
        try{
            file = new FileInputStream(testDataPath);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        book = WorkbookFactory.create(file);
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i =0; i<sheet.getLastRowNum();i++){
            for (int k =0; k<sheet.getRow(0).getLastCellNum();k++){
                data[i][k] = sheet.getRow(i+1).getCell(k).toString();

            }
        }

        return data;
    }

}
