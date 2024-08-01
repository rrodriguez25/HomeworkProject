package Tests;

import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ConfigVariables.ConfigProperties;
import TestNG.BaseTest;

public class CompareColumnHeaderName extends BaseTest {

	//public ConfigProperties config = new ConfigProperties();
	
	String csv1location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile1;
	String csv2location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile2;
	CSVReader csv1Reader = null;
	CSVReader csv2Reader = null;
	
	@Test
	public void CompareColumnsHeaderNameTest() throws CsvValidationException, IOException
	{
		boolean testpass = true;
		int notMatchingCount = 0;
		
		//Get number of rows from CSV 1
		int file1RowCount = 0;
		csv1Reader = new CSVReader(new FileReader(csv1location));
		String[] file1_rows;
		String[] file1_columnHeaders = null;
		while((file1_rows=csv1Reader.readNext())!=null) {
			if(file1RowCount==0)
			{
				file1_columnHeaders = file1_rows;
				break;
			}
		}

		//Get number of rows from CSV 2
		int file2RowCount = 0;
		csv2Reader = new CSVReader(new FileReader(csv2location));
		String[] file2_rows;
		String[] file2_columnHeaders = null;
		while((file2_rows=csv2Reader.readNext())!=null) {
			if(file2RowCount==0)
			{
				file2_columnHeaders = file2_rows;
				break;
			}
		}
		
		//Compare Column Header Name
		for(int i = 0; i < file1_columnHeaders.length; i++)
		{
			if(file1_columnHeaders[i].equals(file2_columnHeaders[i]))
			{
				System.out.println("Column Header at index "+ i+ ", PASS");
				extentTest.pass("Column Header \""+file1_columnHeaders[i]+"\" at index "+ i+ " matching.");
			}
			else
			{	
				System.out.print("Column Header not matching at index "+ i + ", FAILED. ");
				System.out.println(config.csvFile1+" ColumnHeader: "+ file1_columnHeaders[i] + ". "+config.csvFile2+" ColumnHeader: "+ file2_columnHeaders[i]);
				extentTest.fail("Column Header at index "+ i + ". <br />"
						+ config.csvFile1+" ColumnHeaderName: "+ file1_columnHeaders[i] + "<br />"
						+ config.csvFile2+" ColumnHeaderName: "+ file2_columnHeaders[i]);
				notMatchingCount++;
				testpass = false;
			}
		}
		
		Assert.assertTrue(testpass, "Found "+notMatchingCount+" ColumnHeader(s) name(s) not matching");
		extentTest.pass("All Column Header Names are matching");

	}
}
