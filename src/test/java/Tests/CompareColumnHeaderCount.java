package Tests;

import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import TestNG.BaseTest;

public class CompareColumnHeaderCount extends BaseTest {
	
	//public ConfigProperties config = new ConfigProperties();
	
	String csv1location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile1;
	String csv2location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile2;
	
	CSVReader csv1Reader = null;
	CSVReader csv2Reader = null;
	
	
	@Test
	public void CompareColumnHeaderCountTest() throws CsvValidationException, IOException {
		
		try {
			//Get number of rows from CSV 1
			int file1RowCount = 0;
			int file1ColumnCount = 0;
			//int fileColumnname
			csv1Reader = new CSVReader(new FileReader(csv1location));
			String[] file1_rows;
			String[] file1_columns;
			while((file1_rows=csv1Reader.readNext())!=null) {
				if(file1RowCount==0)
				{
					file1_columns = file1_rows;
					file1ColumnCount = file1_columns.length;
					break;
				}
			}
			System.out.println(config.csvFile1 +" number of column headers:"+file1ColumnCount);
			extentTest.info(config.csvFile1 +" number of column headers:"+file1ColumnCount);
			
			//Get number of rows from CSV 2
			int file2RowCount = 0;
			int file2ColumnCount = 0;
			csv2Reader = new CSVReader(new FileReader(csv2location));
			String[] file2_rows;
			while((file2_rows=csv1Reader.readNext())!=null) {
				if(file2RowCount==0)
				{
					file1_columns = file2_rows;
					file2ColumnCount = file1_columns.length;
					break;
				}
			}
			System.out.println(config.csvFile2 +" number of column headers:"+file2ColumnCount);
			extentTest.info(config.csvFile2 +" number of column headers:"+file2ColumnCount);
			
			//Compare Number of Rows for both CSVs
			Assert.assertTrue(file1ColumnCount==file2ColumnCount, "TEST FAILED: Column Headers count do not match. "
					+ config.csvFile1 +" column headers count: "+file1ColumnCount+"."+config.csvFile2 +" column headers count: "+file2ColumnCount+".");
			extentTest.pass("Column Headers count matching on both CSVs");
		}
		catch (Exception e) {
			
		}
		finally {
			if(csv1Reader != null)
				csv1Reader.close();
			if(csv2Reader != null)
				csv2Reader.close();
		}
		
	}
}
