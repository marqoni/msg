package database.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.DriverTrip;
import entity.collections.DriverTripCollection;

public final class Excel {
	
	private String fileName;
	
	
	public Excel(String fileName) {
		super();
		this.fileName = fileName;
	}
	

//	public void connectToExcel() throws IOException {
//		FileInputStream fis = new FileInputStream(new File(this.fileName));
//	      
//	      XSSFWorkbook workbook = new XSSFWorkbook(fis);
//	      XSSFSheet spreadsheet = workbook.getSheetAt(0);
//	      Iterator <Row>  rowIterator = spreadsheet.iterator();
//	      
//	      XSSFRow row;
//	      while (rowIterator.hasNext()) {
//	         row = (XSSFRow) rowIterator.next();
//	         Iterator < Cell >  cellIterator = row.cellIterator();
//	         int i = 0;
//	         while ( cellIterator.hasNext()) {
//	            Cell cell = cellIterator.next();
//	            
//	            switch (cell.getCellType()) {
//	               case NUMERIC:
//	                  System.out.print(cell.getNumericCellValue() + " \t\t NUM i= "+i);
//	                  break;
//	               
//	               case STRING:
//	                  System.out.print(cell.getStringCellValue() + " \t\t STRING i= "+i);
//	                  break;
//	            }
//	            i++;
//	         }
//	         System.out.println();
//	      }
//	      fis.close();
//	}
	
	public DriverTripCollection getDriverTrips(String tripId) throws IOException  {
		DriverTripCollection listOfDriverTrips = new DriverTripCollection();
		FileInputStream fis;
	
		try {
			fis = new FileInputStream(new File(this.fileName));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
		      XSSFSheet spreadsheet = workbook.getSheetAt(0);
		      Iterator <Row>  rowIterator = spreadsheet.iterator();
		      XSSFRow row;		      
		      while (rowIterator.hasNext()) {
		         row = (XSSFRow) rowIterator.next();
		         if(row.getRowNum() == 0) {
		        	 continue;
		         }
		         DriverTrip driverTrip = getDriverTripFromRow(row);
		         listOfDriverTrips.addDriverTrip(driverTrip); //add
		         System.out.println(driverTrip);
		      }
		      fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	      
		return listOfDriverTrips;
	} 
	
	private DriverTrip getDriverTripFromRow(XSSFRow row) {
		Iterator < Cell >  cellIterator = row.cellIterator();
		Cell cell = cellIterator.next();
        String tripId = "", driverId = "";
        Date startTime = null;
        Date endTime = null;                
        int i = 0;        
        
//        while(cellIterator.hasNext() || i < 4) {                       
//              String val = "";            
//              switch(cell.getCellType()) 
//              {
//                  case NUMERIC:
//                      val = String.valueOf(formatter.formatCellValue(cell));
//                      System.out.print(cell.getNumericCellValue() + " \t NUMERIC i= "+i+"\t");
//                      break;
//                  case STRING:
//                	  System.out.print(cell.getStringCellValue() + " \t STRING i= "+i+ "\t");
//                      val = formatter.formatCellValue(cell);
//                      break;
//              }
//              i++;
//              cell = cellIterator.next();
//        }        
        
        while (i < 4 && cellIterator.hasNext()) {           
           switch (i) {
              case 0:
                 tripId = cell.getStringCellValue();
                 break;
              case 1:
                 driverId = cell.getStringCellValue();
                 break;
              case 2:
            	  startTime = DateUtil.getJavaDate((double)cell.getNumericCellValue());
                  break;
              case 3:
            	  endTime = DateUtil.getJavaDate((double)cell.getNumericCellValue());
            	  break;
           } 
           i++;
           cell = cellIterator.next();
           
        } 
        
        return new DriverTrip(tripId, driverId, startTime, endTime);
	}
	
	

}
