package utilityLib;

import java.io.File;


/**
 * This class is created to update the default configuration file 
 * to point to QA environment so that QA can debug using BW designer
 */
public class EnviromentBuild {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		updateFileName("C:/ws1/1fbbizprocess/defaultVars");
		System.out.println("done");

	}
	
	
	public static void updateFileName(String directory)
	{
		File dir = new File(directory);
		File defaultFile = new File(directory + "/defaultVars.substvar");
		File qaFile = new File(directory + "/defaultVars.substvar.qa");
		if(qaFile.exists())
		{
			defaultFile.delete();
			qaFile.renameTo(defaultFile);
			System.out.println(qaFile.getAbsolutePath());
		}		
		for (File f : dir.listFiles()) {
			if(f.isDirectory())
			{
				updateFileName(f.getAbsolutePath());
			}
		}
	}

}
