package Tests;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ConfigVariables.ConfigProperties;
import TestNG.BaseTest;

public class CompareRowData extends BaseTest {

	String csv1location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile1;
	String csv2location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile2;
	CSVReader csv1Reader = null;
	CSVReader csv2Reader = null;
	String[] file_rows;
	
	@Test
	public void CompareColumnsHeaderName() throws CsvValidationException, IOException, IllegalAccessException
	{
		
		boolean testpass = true;
		csv1Reader = new CSVReader(new FileReader(csv1location));
		csv2Reader = new CSVReader(new FileReader(csv2location));
		
		//Get number of rows from CSV 1
		int file1RowCount = 0;
		List<String[]> file1_csvData = new ArrayList<>();
		String[] file1_rows;
		while((file1_rows=csv1Reader.readNext())!=null) {
			file1_csvData.add(file1_rows);
			file1RowCount++;
		}
		
		//Get number of rows from CSV 2
		List<String[]> file2_csvData = new ArrayList<>();
		while((file_rows=csv2Reader.readNext())!=null) {
			file2_csvData.add(file_rows);
		}
		
		int notMatchingRowCount = 0;
		
		for(int i = 0; i < file1RowCount; i++)
		{
			String csv1RowAsString = Arrays.toString(file1_csvData.get(i));
			String csv2RowAsString = Arrays.toString(file2_csvData.get(i));
			
			//Check if row have same data
			if(csv1RowAsString.equals(csv2RowAsString))
			{
				System.out.println("Row "+ i+ ": Matching. PASS");
				extentTest.pass("Row "+ i+ ": Matching");
			}
			else
			{	
				String[] csv1_row = file1_csvData.get(i);
				String[] csv2_row = file2_csvData.get(i);
				
				//Check if number of columns are matching
				if(csv1_row.length != csv2_row.length)
				{
					System.out.print("Row "+i+": columns count not matching, FAILURED. ");
					System.out.println(config.csvFile1+" number of columns: "+ csv1_row.length + ". "+config.csvFile1+" number of columns: "+ csv2_row.length);
					extentTest.fail("Row "+i+": Columns count not matching. <br/>"
							+ "At "+config.csvFile1+", row "+i+", columns count: "+ csv1_row.length + "<br/>"
							+ "At "+config.csvFile2+", row "+i+", columns count: "+ csv2_row.length);
					testpass = false;
				} 
				else
				{
					//Check what column is not matching
					for(int j = 0; j < csv1_row.length; j++)
					{
						if(!csv1_row[j].equals(csv2_row[j]))
						{
							System.out.print("Row "+i+": Different data vale at column "+j+", FAILURED. ");
							System.out.println("File 1 DataValue: "+ csv1_row[j] + ". File 2 DataValue: "+ csv2_row[j]);
							extentTest.fail("Row "+i+": Different data vale at column "+j+" <br/>"
									+ "At "+config.csvFile1+", data value: \""+ csv1_row[j] + "\" <br/>"
									+ "At "+config.csvFile2+", data value: \""+ csv2_row[j] + "\"");
							testpass = false;
						}
					}
				}
				notMatchingRowCount++;
				testpass = false;
			}
			
		}
		
		Assert.assertTrue(testpass, "Found "+notMatchingRowCount+" rows not matching between both files.");
		extentTest.pass("All data rows are matching");	
	}
}
