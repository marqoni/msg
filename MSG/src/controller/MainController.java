package controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import controller.helper.FileTypeExtension;
import database.excel.Excel;
import database.sqlite.SQLite;
import entity.TripInfo;
import entity.collections.DriverTripCollection;
import entity.collections.TripInfoCollection;
import gui.TripViewer;


public final class MainController implements Controller {

	private Excel excel;
	private JFrame gui;
	private SQLite sqlLite;
	String pathToOutputFile, pathToInputFile;
	boolean isSqlLite = false;

	public MainController(String pathToInputFile, String pathToOutputFile) {
		this.pathToOutputFile = pathToOutputFile.trim();
		System.out.println(pathToOutputFile);
		this.pathToInputFile = pathToInputFile.trim();
		System.out.println(pathToInputFile);
		checkIfInputFileType();
		initGui();
	}

	private void startExcel(String fileName) {
		this.excel = new Excel(fileName);
	}

	public void generateMap(String driverId, String type, String outputFileName) {
		try {
			TripInfoCollection tc = null;
			if (this.isSqlLite) {
				tc = this.sqlLite.getCheckpoints(driverId);
			} else {
				DriverTripCollection dtc = getDriverTripsByDriverId(driverId);
				tc = getTripInfoCollectionByDriverTripCollection(dtc);
			}

			if (type == "Png") {

			} else {
				
			FileUtils.writeStringToFile(new File(pathToOutputFile+"\\"+outputFileName+".txt"), tc.toString(), Charset.defaultCharset());
			}
		} catch (IOException | ClassNotFoundException | SQLException | NumberFormatException | ParseException e) {
			e.printStackTrace();
			this.showJframeMessageError(e.getMessage());
		}
		this.showJFrameMessageSucsess("Operation successful.");
	}

	private void checkIfInputFileType() {
		String fileTypeExtension = FileTypeExtension.getFileTypeExtension(this.pathToInputFile);
		switch (fileTypeExtension) {
		case "xlsx":
			initExcel();
			break;
		case "db":
			initDatabase();
			break;
		default:
			throw new IllegalArgumentException("Unexpected file extension.");
		}
	}

	private void initDatabase() {
		this.sqlLite = new SQLite(this.pathToInputFile);
		isSqlLite = true;
	}

	private void initExcel() {
		this.excel = new Excel(this.pathToInputFile);
	}

	private DriverTripCollection getDriverTripsByDriverId(String driverId) throws IOException {
		try {
			return this.excel.getDriverTrips(driverId);
		} catch (IOException e) {
			e.printStackTrace();
			throw e; 
		}
	}

	private TripInfoCollection getTripInfoCollectionByDriverTripCollection(DriverTripCollection dtc)
			throws IOException {
		try {
			TripInfoCollection tic = new TripInfoCollection();
			for (int i = 0; i < dtc.getListOfDriverTrips().size(); i++) {
				tic.mergeList(this.excel.getCheckpoints(dtc.getListOfDriverTrips().get(i).getTripId()));
			}
			return tic;
		} catch (IOException e) {
			e.printStackTrace();
			throw e; 
		}
	}

	private void initGui() {

		this.gui = new TripViewer(this);
		this.gui.setVisible(true);

	}

	private void showJframeMessageError(String message) {
		JOptionPane.showMessageDialog(this.gui, message, "Error.", JOptionPane.ERROR_MESSAGE);

	}
	
	private void showJFrameMessageSucsess(String message) {
		JOptionPane.showMessageDialog (this.gui, message, "Success.", JOptionPane.INFORMATION_MESSAGE);
	}
}
