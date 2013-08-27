package utilityLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class __ApplicationProperties {
	static Properties properties;
	

	public static Properties getProperties(String filePath)
	{
		properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(filePath);
		    properties.load(fis);
		    fis.close();
		    return properties;
		} catch (IOException e) {
		}
		return null;
	}
}
