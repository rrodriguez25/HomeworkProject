package ConfigVariables;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

	public static Properties prop;
	private static String configPath = "//src//main//java//Configuration//configsetting.properties";
	
	public String csvFile1 = "";
	public String csvFile2 = "";
	
	public ConfigProperties()
	{

		prop = new Properties();
		
		try {
			InputStream instm = new FileInputStream(System.getProperty("user.dir")+configPath);
			prop.load(instm);
			
			this.csvFile1 = prop.getProperty("CSVFileName1");
			this.csvFile2 = prop.getProperty("CSVFileName2");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
