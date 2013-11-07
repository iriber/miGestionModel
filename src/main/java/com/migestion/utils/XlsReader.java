package com.migestion.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class XlsReader {

	
	public void readXls( String location ) throws FileNotFoundException, IOException{
		
		InputStream inputStream = XlsReader.class.getResourceAsStream(location);
		readXls(new HSSFWorkbook(inputStream));
		inputStream.close();
	}
	public void readXls( File file ) throws FileNotFoundException, IOException{
		FileInputStream inputStream = new FileInputStream(file);
		readXls(new HSSFWorkbook( inputStream  ));
		inputStream.close();
	}
	
	public void readXls( HSSFWorkbook workbook ) throws FileNotFoundException, IOException{
		
        //Get first/desired sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
             
            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType())
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                }
            }
            System.out.println("");
        }
//        inputStream.close();
       
	}
	


}
