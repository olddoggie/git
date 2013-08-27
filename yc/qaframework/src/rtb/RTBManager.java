package rtb;

//import onefbbizprocess.*;
import java.io.*;

public class RTBManager {
	private static final String baseRTBFolder = "C:\\1fb\\testdata\\BizworksRegressionTestbed";
	private String excelWorksheet;
	
	public RTBManager(Class clazz){	
		excelWorksheet = clazz.getResource(clazz.getSimpleName()+".xls").getFile();
		System.out.println(excelWorksheet);
	}

	public void clearDirectory(File dir){
		File[] subdirs = dir.listFiles();
		
		for(File f : subdirs){
			if(f.isDirectory()){
				clearDirectory(f);
			} else {
				//HACK, don't delete the default GetDataFromCAP file
				if(!f.getName().equals("default.xml"))
					f.delete();
			}
		}
	}
	
	public void clearRTB(){
		File rtb = new File(baseRTBFolder);
		File[] subdirs = rtb.listFiles();
		
		for(File f : subdirs){
			if(f.isDirectory()){
				clearDirectory(f);
			} else {
				f.delete();
			}
		}
	}
	
	public void loadRTB() throws Exception{
		Excel2RTB loader = new Excel2RTB(baseRTBFolder, excelWorksheet,true);
		loader.loadAllScenarios();
	}
	
	public static void main(String args[]) throws Exception{
		//RTBManager manager = new RTBManager(GetUserDataCR17Test.class);
		//manager.clearRTB();
		//manager.loadRTB();
	}
}
