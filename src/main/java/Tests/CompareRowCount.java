package Tests;

import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ConfigVariables.ConfigProperties;
import TestNG.BaseTest;

public class CompareRowCount extends BaseTest {

	String csv1location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile1;
	String csv2location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile2;
	
	CSVReader csv1Reader = null;
	CSVReader csv2Reader = null;
	String[] file_rows;
	
	@Test
	public void CompareRowCountTest() throws CsvValidationException, IOException {
		
		try {
			//Get number of rows for CSV-1
			int file1_numOfRows = 0;
			csv1Reader = new CSVReader(new FileReader(csv1location));
			while((file_rows=csv1Reader.readNext())!=null) {
				file1_numOfRows++;
			}
			System.out.println(config.csvFile1+" number of rows: "+file1_numOfRows);
			extentTest.info(config.csvFile1+" number of rows: "+file1_numOfRows);
			
			//Get number of rows for CSV-1
			int file2_numOfRows = 0;
			csv2Reader = new CSVReader(new FileReader(csv2location));
			while((file_rows=csv2Reader.readNext())!=null) {
				file2_numOfRows++;
			}
			System.out.println(config.csvFile2+" number of rows: "+file2_numOfRows);
			extentTest.info(config.csvFile2+" number of rows: "+file2_numOfRows);
			
			//Compare Number of Rows for both CSVs
			Assert.assertTrue(file1_numOfRows==file2_numOfRows, "TEST FAILED: Number of Row does not match. "
					+ config.csvFile1+" total rows count: "+file1_numOfRows+". "+config.csvFile2+" total rows count: "+file2_numOfRows+".");
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
