package sweetshop.driverfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

	private Properties prop;
	
	public Properties init_properties() {
		prop = new Properties();
		try {
			FileInputStream inputFile = new FileInputStream("./src/test/resources/sweetshopConfig/config.properties");
			prop.load(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
}
