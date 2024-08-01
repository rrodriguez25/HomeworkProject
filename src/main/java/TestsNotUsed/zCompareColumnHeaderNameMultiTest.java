package TestsNotUsed;

import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class zCompareColumnHeaderNameMultiTest {
	
	String csv1location = System.getProperty("user.dir") + "//csvfiles//File1.csv";
	String csv2location = System.getProperty("user.dir") + "//csvfiles//File2.csv";
	CSVReader csv1Reader = null;
	CSVReader csv2Reader = null;
	
	@DataProvider
	public Object[][] TestData() throws IOException  {
		

	try {
	
			//Get number of rows from CSV 1
			int file1RowCount = 0;
			int file1ColumnCount = 0;
			//int fileColumnname
			csv1Reader = new CSVReader(new FileReader(csv1location));
			String[] file1_rows;
			String[] file1_columnHeaders = null;
			while((file1_rows=csv1Reader.readNext())!=null) {
				if(file1RowCount==0)
				{
					file1_columnHeaders = file1_rows;
					file1ColumnCount = file1_columnHeaders.length;
					break;
				}
			}
			System.out.println("File1.csv number of columns:"+file1ColumnCount);
			
			
			
			//Get number of rows from CSV 2
			int file2RowCount = 0;
			int file2ColumnCount = 0;
			csv2Reader = new CSVReader(new FileReader(csv2location));
			String[] file2_rows;
			String[] file2_columnHeaders = null;
			while((file2_rows=csv2Reader.readNext())!=null) {
				if(file2RowCount==0)
				{
					file2_columnHeaders = file2_rows;
					file2ColumnCount = file2_columnHeaders.length;
					break;
				}
			}
			
			String[] matchedColumns = new String[2];
			
			for(int i = 0; i < 2; i++)
			{
				matchedColumns[i] = file1_columnHeaders[i]+file2_columnHeaders[i];

				
			}
			
			System.out.println("File2.csv number of column:"+file2ColumnCount);
			
			Object[][] data = new Object[file1_columnHeaders.length][matchedColumns.length];
			
			for (int i = 0; i < file1_columnHeaders.length; i++)
	        {
				for (int col = 0; col < 2; col++)
				{
					if(col == 0)
						data[i][col] = file1_columnHeaders[i];
					else if (col == 1)
						data[i][col] = file2_columnHeaders[i];
				}
	        }

			return data;
		}
		catch (Exception e) {
			return null;
		}
		finally {
			if(csv1Reader != null)
				csv1Reader.close();
			if(csv2Reader != null)
				csv2Reader.close();
		}
}
	@Test(dataProvider = "TestData")
	public void CompareColumnsHeader(String file1_columnName, String file2_columnName)
	{
		int index = 0;
			Assert.assertTrue(file1_columnName.equals(file2_columnName), "TEST FAILED: Column Name does not match. "
				+ "CSV1 column at index: "+index+" is "+file1_columnName+". "
				+ "CSV2 column at index: "+index+" is "+file2_columnName+".");
		
	}
	/*
	@Test
	public void CompareColumnCOunt(int file1ColumnCount, int file2ColumnCount)
	{
		Assert.assertTrue(file1ColumnCount==file2ColumnCount, "TEST FAILED: Number of Row does not match. "
				+ "CSV1 total columns: "+file1ColumnCount+". CSV2 total columns: "+file2ColumnCount+".");
	}
	*/
	
	
}
	