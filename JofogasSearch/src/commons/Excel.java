package commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Excel {
	String strOutputFile;
	
	String[][] arrData = null;
	int rowCount = 0;
	int columnCount = 0;
	
	public Excel() {
		System.out.println(System.getProperty("user.dir"));
	}
	
	public String setOutputFile(String strFileName) {
		strOutputFile = System.getProperty("user.dir")+"\\"+strFileName;
		return strOutputFile;
	}

	public String[][] readExcel(String filePath,String fileName,String strSheetName) throws IOException{

	    File file =    new File(filePath+"\\"+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook wbook = null;
	    int intFileNameIndex = fileName.indexOf(".");
	    String strFileExtension = fileName.substring(intFileNameIndex);
	    CellType stringType = null;
	    String cellType;
	    
	    //Check file extension
	    if(strFileExtension.equals(".xls")){
	    	wbook = new HSSFWorkbook(inputStream);
	    }
	    else {
	    	System.out.println("The file must be an  xls file. Filename: "+fileName.substring(0, intFileNameIndex) + ", Extension: " + strFileExtension);
	    }

	    //Read sheet
	    Sheet sheet = wbook.getSheet(strSheetName);
	    
	    //Count the sheet size
	    rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum()+1;
	    columnCount = sheet.getRow(0).getLastCellNum();
	    
	    arrData = new String[rowCount][sheet.getRow(0).getPhysicalNumberOfCells()];
	    
	    stringType = sheet.getRow(0).getCell(0).getCellType();

	    for (int i = 0; i < rowCount; i++) {

	        Row row = sheet.getRow(i);
	        
	        for (int j = 0; j < columnCount; j++) {
	        	
	        	if(row.getCell(j) != null) {
	        		cellType = row.getCell(j).getCellType().toString();
		        	
		            if (cellType == "STRING") {
		            	arrData[i][j] = row.getCell(j).toString();
		            }
		            else if (cellType == "NUMERIC") {
		            	row.getCell(j).setCellType(stringType);
		            	arrData[i][j] = row.getCell(j).toString();
		            }
	        	}
	        	else {
	        		arrData[i][j] = "";
	        	}
	        }
	    }
	    return arrData;
	}
	

	public void writeExcel(String fileName,String sheetName,String[] dataToWrite) throws IOException{

	        //Create an object of File class to open xlsx file
	        File file =    new File(System.getProperty("user.dir")+"\\"+fileName);

	        //Create an object of FileInputStream class to read excel file
	        FileInputStream inputStream = new FileInputStream(file);
	        Workbook wb = null;

	        //Find the file extension by splitting  file name in substring and getting only extension name
	        String fileExtensionName = fileName.substring(fileName.indexOf("."));

	        //Check condition if the file is xlsx file
	        if(fileExtensionName.equals(".xlsx")){
		        //If it is xlsx file then create object of XSSFWorkbook class
		        wb = new XSSFWorkbook(inputStream);
	        }

	        //Check condition if the file is xls file
	        else if(fileExtensionName.equals(".xls")){
	            //If it is xls file then create object of XSSFWorkbook class
	            wb = new HSSFWorkbook(inputStream);
	        }    

	    //Read excel sheet by sheet name    
	    Sheet sheet = wb.getSheet(sheetName);

	    //Get the current count of rows in excel file
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

	    //Get the first row from the sheet
	    Row row = sheet.getRow(0);
	    if(row == null) {
	    	row = sheet.createRow(rowCount);
	    } 

	    //Create a new row and append it at last of sheet
	    Row newRow = sheet.createRow(rowCount+1);

	    //Create a loop over the cell of newly created Row    
	    for(int j = 0; j < dataToWrite.length; j++){

	        //Fill data in row
	        Cell cell = newRow.createCell(j);
	        cell.setCellValue(dataToWrite[j]);

	    }

	    //Close input stream
	    inputStream.close();

	    //Create an object of FileOutputStream class to create write data in excel file
	    FileOutputStream outputStream = new FileOutputStream(file);

	    //write data in the excel file
	    wb.write(outputStream);

	    //close output stream
	    outputStream.close();
		
	    }

}
