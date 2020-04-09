package util;

import com.google.common.collect.Ordering;

import exception.MyException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class Util {

	private Util() {
	}

	public static void sleep(long msec) throws MyException {
		try {
			Thread.sleep(msec);
		} catch (Exception e) {
			throw new MyException("Unable to sleep for:" + msec + "secs");
		}
	}

	public static String getSimpleDateFormat(String format) throws MyException {
		String formattedDate = "";
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			formattedDate = formatter.format(date);
		} catch (Exception e) {
			throw new MyException("Unable to get date in specified format:" + format);
		}
		return formattedDate;
	}

	public static String getCurrentDateTime() throws MyException {
		String date = "";
		try {
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			date = formatter.format(currentDate.getTime()).replace("/", "_");
			date = date.replace(":", "_");
		} catch (Exception e) {
			throw new MyException("Unable to get current date and time");
		}
		return date;
	}

	public static boolean verifyTextContains(String actualText, String expText) throws MyException {
		boolean flag = false;
		try {
			if (actualText.toLowerCase().contains(expText.toLowerCase())) {
				flag = true;
			}
		} catch (Exception e) {
			throw new MyException("Unable to verify wether the actual text: " + actualText + " "
					+ "contains the expected text:" + expText);
		}
		return flag;
	}

	public static boolean verifyTextMatch(String actualText, String expText) throws MyException {
		boolean flag = false;
		try {
			if (actualText.equals(expText)) {
				flag = true;
			}
		} catch (Exception e) {
			throw new MyException("Unable to verify wether the actual text: " + actualText + " "
					+ "matches the expected text:" + expText);
		}
		return flag;
	}

	public static Boolean verifyListContains(List<String> actList, List<String> expList) throws MyException {
		try {
			int expListSize = expList.size();
			for (int i = 0; i < expListSize; i++) {
				if (!actList.contains(expList.get(i))) {
					return false;
				}
			}
		} catch (Exception e) {
			throw new MyException("Unable to verify wether the actual list: " + actList + " "
					+ "contains the expected list:" + expList);
		}
		return true;
	}

	public static Boolean verifyListMatch(List<String> actList, List<String> expList) throws MyException {
		boolean found = false;
		try {
			int actListSize = actList.size();
			int expListSize = expList.size();
			if (actListSize != expListSize) {
				return false;
			}

			for (int i = 0; i < actListSize; i++) {
				found = false;
				for (int j = 0; j < expListSize; j++) {
					if (verifyTextMatch(actList.get(i), expList.get(j))) {
						found = true;
						break;
					}
				}
			}
			if (found) {
				return true;
			}
		} catch (Exception e) {
			throw new MyException("Unable to verify wether the actual list: " + actList + " "
					+ "matches the expected list:" + expList);
		}
		return found;
	}

	public static Boolean verifyItemPresentInList(List<String> actList, String item) throws MyException {
		try {
			int actListSize = actList.size();
			for (int i = 0; i < actListSize; i++) {
				if (!actList.contains(item)) {
					return false;
				}
			}
		} catch (Exception e) {
			throw new MyException(
					"Unable to verify wether the item: " + item + " " + "is present in the list:" + actList);
		}
		return true;
	}

	public static boolean isListAscendingOrder(List<Long> list) throws MyException {
		boolean sorted = false;
		try {
			sorted = Ordering.natural().isOrdered(list);
		} catch (Exception e) {
			throw new MyException("Unable to verify wether the given list: " + list + " " + "is in ascending order");
		}
		return sorted;
	}

	public static String createDirectory(String rootDirectory, String directoryToBeCreated) throws MyException {
		String dir = "";
		try {
			dir = rootDirectory + "//" + directoryToBeCreated + "_" + Util.getCurrentDateTime();
			new File(dir).mkdirs();
		} catch (Exception e) {
			throw new MyException("Unable to create new directory:" + dir);
		}
		return dir;
	}
	
	public static boolean verify(String filePath, String fileName, String fileExtension) throws MyException {
		boolean flag = false;
		if (!filePath.isEmpty()) {
			if (!fileName.isEmpty()) {
				if (FilenameUtils.getExtension(fileName).equals(fileExtension)) {
					flag = true;
				} else {
					throw new MyException(fileName + " " + "File extension is incorrect");
				}
			} else {
				throw new MyException("File name is empty");
			}
		} else {
			throw new MyException("File path is empty");
		}
		return flag;
	}

}