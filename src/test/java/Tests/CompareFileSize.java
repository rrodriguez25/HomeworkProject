package Tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestNG.BaseTest;

public class CompareFileSize extends BaseTest {
	
	String csv1location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile1;
	String csv2location = System.getProperty("user.dir") + "//csvfiles//"+config.csvFile2;
	File csv1File = new File(csv1location);
	File csv2File = new File(csv2location);
	
	@Test
	public void CompareFileSizeTest() {
		

		//prints file size in bytes  
		String csv1Filesize = sizeInBytes(csv1File);
		System.out.println(csv1Filesize);  
		extentTest.info(config.csvFile1+" file size: "+csv1Filesize+" bytes");
		
		//prints file size in bytes  
		String csv2Filesize = sizeInBytes(csv2File);
		System.out.println(csv2Filesize);
		extentTest.info(config.csvFile2+" file size: "+csv2Filesize+" bytes");
		
		//Assert that csv file sizes are the same
		Assert.assertTrue(csv1Filesize.equals(csv2Filesize), "TEST FAILED: File Sizes does not match. "
				+ config.csvFile1+" size in bytes: "+csv1Filesize+". "+config.csvFile2+" size in bytes: "+csv2Filesize+".");
		extentTest.pass("Files size is the same.");
		
	}
	
	private String sizeInBytes(File file)   
	{  
		return file.length() + " bytes";  
	}  

}
