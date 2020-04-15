package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exception.MyException;

public class ExcelReader {

	private String fileName;
	private File excelFile;
	private FileInputStream excelFIS;
	private Workbook wb;
	private Sheet sh;
	private Cell cellValue;

	private String colHeaders;
	private String testdata;
	private int rowCount;
	private int colCount;
	private String fileExtention;

	private Object[][] excelArray;
	private Map<Object, Object> datamap;
	private DataFormatter formatter;

	public ExcelReader(String excelFilePath, String excelFileName) {
		setFileName(excelFileName);
		setExcelFile(new File(excelFilePath + getFileName()));
	}

	public Object[][] readExcelSheet(String excelSheetName) throws MyException {

		try {
			setExcelFIS(new FileInputStream(excelFile));
		} catch (FileNotFoundException e) {
			throw new MyException("Unable to read excel file:" + getFileName());
		}
		setFileExtention(getFileName().substring(fileName.indexOf('.')));

		if (getFileExtention().equalsIgnoreCase(".xlsx")) {
			try {
				setWb(new XSSFWorkbook(excelFIS));
			} catch (IOException e) {
				throw new MyException("Unable to initiate XSSFWorkbook class object");
			}

		} else {
			try {
				setWb(new HSSFWorkbook(excelFIS));
			} catch (IOException e) {
				throw new MyException("Unable to initiate HSSFWorkbook class object");
			}
		}
		setSh(getWb().getSheet(excelSheetName));

		setRowCount(getSh().getLastRowNum());
		setColCount(getSh().getRow(0).getLastCellNum());

		setExcelArray(new Object[getRowCount()][1]);
		setFormatter(new DataFormatter());

		for (int i = 0; i < getRowCount(); i++) {
			setDatamap(new HashMap<>());
			for (int j = 0; j < getColCount(); j++) {
				setColHeaders(getSh().getRow(0).getCell(j).toString());
				setCellValue(getSh().getRow(i + 1).getCell(j));
				setTestdata(getFormatter().formatCellValue(getCellValue()));
				getDatamap().put(getColHeaders(), getTestdata());
			}
			getExcelArray()[i][0] = getDatamap();
		}
		return getExcelArray();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public FileInputStream getExcelFIS() {
		return excelFIS;
	}

	public void setExcelFIS(FileInputStream excelFIS) {
		this.excelFIS = excelFIS;
	}

	public Workbook getWb() {
		return wb;
	}

	public void setWb(Workbook wb) {
		this.wb = wb;
	}

	public Sheet getSh() {
		return sh;
	}

	public void setSh(Sheet sh) {
		this.sh = sh;
	}

	public Cell getCellValue() {
		return cellValue;
	}

	public void setCellValue(Cell cellValue) {
		this.cellValue = cellValue;
	}

	public String getColHeaders() {
		return colHeaders;
	}

	public void setColHeaders(String colHeaders) {
		this.colHeaders = colHeaders;
	}

	public String getTestdata() {
		return testdata;
	}

	public void setTestdata(String testdata) {
		this.testdata = testdata;
	}

	public Object[][] getExcelArray() {
		return excelArray;
	}

	public void setExcelArray(Object[][] excelArray) {
		this.excelArray = excelArray;
	}

	public Map<Object, Object> getDatamap() {
		return datamap;
	}

	public void setDatamap(Map<Object, Object> datamap) {
		this.datamap = datamap;
	}

	public DataFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(DataFormatter formatter) {
		this.formatter = formatter;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

}