package com.migestion.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public abstract class XlsReader {

	
	public Collection<Object> readXls( String location ) throws FileNotFoundException, IOException{
		
		InputStream inputStream = XlsReader.class.getResourceAsStream(location);
		Collection<Object> collection = readXls(new HSSFWorkbook(inputStream));
		inputStream.close();
		return collection;
	}
	public Collection<Object> readXls( File file ) throws FileNotFoundException, IOException{
		FileInputStream inputStream = new FileInputStream(file);
		Collection<Object> collection = readXls(new HSSFWorkbook( inputStream  ));
		inputStream.close();
		return collection;
	}
	
	public Collection<Object> readXls( HSSFWorkbook workbook ) throws FileNotFoundException, IOException{
		
		Collection<Object> collection = new ArrayList<Object>();
		
        //Get first/desired sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        
        Boolean skip = true;
        
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
             
            ArrayList<Object> columns = new ArrayList<Object>();
            int index=0;
            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType())
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        
                        columns.add(index, new Double( cell.getNumericCellValue() ));
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        columns.add(index, cell.getStringCellValue());
                        break;
                }
                index++;
            }

            if(skip)
            	skip=false;
            else
            	collection.add( buildObject( columns ) );
            
            System.out.println("");
        }
//        inputStream.close();

        return collection;
	}
	
	
	protected abstract Object buildObject(ArrayList<Object> columns);
	


}
