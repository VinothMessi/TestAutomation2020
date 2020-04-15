package excel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exception.MyException;

public class ExcelUtility {

	private String lExcelPath;
	private String lExcelFilePath;
	private XSSFSheet excelWSheet;

	public ExcelUtility(String excelPath, String fileName) {
		this.lExcelPath = excelPath;
		this.lExcelFilePath = fileName;
	}

	/*
	 * Set the File path, open Excel file
	 * 
	 * @params - Excel Path and Sheet Name
	 */
	public synchronized void setExcelFile(String sheetName) {
		FileInputStream excelFile = null;
		XSSFWorkbook excelWBook = null;
		try {
			// Open the Excel file
			excelFile = new FileInputStream(this.lExcelPath + this.lExcelFilePath);
			// Access the excel data sheet
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public Object[][] getTestData(String tableName) throws MyException {
		String[][] testData = null;

		Object[][] excelArray = null;
		Map<Object, Object> datamap;
		Cell cellValue;
		String test = null;

		String colHeaders = null;

		try {
			// Handle numbers and strings
			DataFormatter formatter = new DataFormatter();
			// BoundaryCells are the first and the last column
			// We need to find first and last column, so that we know which rows to read for
			// the data
			XSSFCell[] boundaryCells = findTableNameCells(tableName);
			// First cell to start with
			XSSFCell startCell = boundaryCells[0];
			// Last cell where data reading should stop
			XSSFCell endCell = boundaryCells[1];

			// Find the start row based on the start cell
			int startRow = startCell.getRowIndex();
			// Find the end row based on end cell
			int endRow = endCell.getRowIndex() - 1;
			// Find the start column based on the start cell
			int startCol = startCell.getColumnIndex() + 1;
			// Find the end column based on end cell
			int endCol = endCell.getColumnIndex() - 1;

			// Declare multi-dimensional array to capture the data from the table
			testData = new String[endRow - startRow + 1][endCol - startCol + 1];
			excelArray = new Object[endRow - startRow][1];
			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					// For every column in every row, fetch the value of the cell
					Cell cell = excelWSheet.getRow(i).getCell(j);
					// Capture the value of the cell in the multi-dimensional array
					testData[i - startRow][j - startCol] = formatter.formatCellValue(cell);
				}
			}

			int c = 0;
			for (int i = startRow; i < endRow; i++) {
				datamap = new HashMap<>();
				for (int j = startCol; j <= endCol; j++) {
					colHeaders = excelWSheet.getRow(startRow).getCell(j).toString();
					cellValue = excelWSheet.getRow(i + 1).getCell(j);
					test = formatter.formatCellValue(cellValue);
					datamap.put(colHeaders, test);
				}
				excelArray[c][0] = datamap;
				c++;
			}
		} catch (Exception e) {
			throw new MyException(
					"Unable to get Test data for the specified Test case:" + tableName + "\n" + e.getMessage());
		}
		// Return the multi-dimensional array
		return excelArray;
	}

	public XSSFCell[] findTableNameCells(String tableName) {
		DataFormatter formatter = new DataFormatter();
		// Declare begin position
		String pos = "begin";
		XSSFCell[] cells = new XSSFCell[2];

		for (Row row : excelWSheet) {
			for (Cell cell : row) {
				if (tableName.equals(formatter.formatCellValue(cell))) {
					if (pos.equalsIgnoreCase("begin")) {
						// Find the begin cell, this is used for boundary cells
						cells[0] = (XSSFCell) cell;
						pos = "end";
					} else {
						// Find the end cell, this is used for boundary cells
						cells[1] = (XSSFCell) cell;
					}
				}
			}
		}
		// Return the cells array
		return cells;
	}

}