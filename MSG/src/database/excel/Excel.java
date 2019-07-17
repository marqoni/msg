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
import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.DriverTrip;
import entity.TripInfo;
import entity.collections.DriverTripCollection;
import entity.collections.TripInfoCollection;

public final class Excel {
	
	private String fileName;
	
	
	public Excel(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public DriverTripCollection getDriverTrips(String fileName) throws IOException  {
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
		         if(driverTrip.getDriverId().equals(fileName)){
		    		 listOfDriverTrips.addDriverTrip(driverTrip); //add
		         }
		      }
		      fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
//	    System.out.println(listOfDriverTrips);
		return listOfDriverTrips;
	} 
	
	private DriverTrip getDriverTripFromRow(XSSFRow row) {	
		Iterator < Cell >  cellIterator = row.cellIterator();
		String tripId = "", driverId = "";
        Date startTime = null;
        Date endTime = null;                
        int i = 0;        
       
        while (cellIterator.hasNext() && i < 4 ) {
        	
           Cell cell = cellIterator.next();
           
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
        } 
       
        return new DriverTrip(tripId, driverId, startTime, endTime);
	}
	
	public TripInfoCollection getCheckpoints(String tripId) throws IOException  {
		TripInfoCollection listOfCheckpointsByTrip = new TripInfoCollection();
		FileInputStream fis;
	
		try {
			fis = new FileInputStream(new File(this.fileName));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
		      XSSFSheet spreadsheet = workbook.getSheetAt(1);
		      Iterator <Row>  rowIterator = spreadsheet.iterator();
		      XSSFRow row;	
		      String previousRowTripId = "";
		      
		      char alphabet =  'A';
		      
		      while (rowIterator.hasNext()) {
		         row = (XSSFRow) rowIterator.next();
		         if(row.getRowNum() == 0) {
		        	 continue;
		         }
		         TripInfo tripInfo = getTripCheckpointsFromRow(row);         
			         
		         if(tripInfo.getTripId().equals(tripId)){
		        	 tripInfo.setCharacterSign(String.valueOf(alphabet));
		    		  //add
		         } else if ((tripInfo.getTripId().equals(previousRowTripId))) {
		         tripInfo.setCharacterSign(String.valueOf(alphabet));	    		 
		         }	else {		        	 
		        	 tripInfo.setCharacterSign(String.valueOf(alphabet));
		         }	         		         
		         listOfCheckpointsByTrip.addTripCheckpoint(tripInfo);		         
		         previousRowTripId = tripInfo.getTripId();
		      }
		      fis.close();
		      
		      
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return listOfCheckpointsByTrip;
	}

	private TripInfo getTripCheckpointsFromRow(XSSFRow row) {
		Iterator < Cell >  cellIterator = row.cellIterator();		
        double ID = 0;
        String tripId = "", offsetX = "", offsetY = "", characterSign = "";
        Date startTime = null;  
        int i = 0;               
        while (cellIterator.hasNext() && i < 5 ) {        	     	
           Cell cell = cellIterator.next();
                                  
           switch (i) {
              case 0:
                 ID = cell.getNumericCellValue();
                 break;
              case 1:
            	 tripId = cell.getStringCellValue();           		
                 break;
              case 2:
            	  offsetX = cell.toString();
                  break;
              case 3:
            	  offsetY = cell.toString();
                  break;
              case 4:
            	  startTime = DateUtil.getJavaDate((double)cell.getNumericCellValue());
            	  break;                  
           } 
           i++;          
        }
        
        return new TripInfo(ID, tripId, offsetX, offsetY, startTime, characterSign);
	}
	
}
