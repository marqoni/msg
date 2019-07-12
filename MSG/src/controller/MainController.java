package controller;

import java.io.IOException;

import javax.swing.JFrame;

import database.excel.Excel;
import database.sqlite.SQLite;

public final class MainController {
	
	private Excel excel;
	private JFrame gui;
	private SQLite sqlLite;
	
	
	public MainController() {
		//TODO prosiri parametre za arg[1]i arg[0]
		startExcel("ttp.xlsx");
	}


	private void startExcel(String fileName) {
		this.excel =  new Excel(fileName);
		try {
//			this.excel.connectToExcel();
			this.excel.getDriverTrips("test_driver");
//			this.excel.getCheckpoints(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private void startSqlLite(String username, String password, String host, String dbName) {
		this.excel =  new Excel("ttp.xlsx");
	}	
}
