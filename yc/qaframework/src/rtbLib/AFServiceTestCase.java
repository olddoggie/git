package rtbLib;
//package rtb.lib;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.MalformedURLException;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//import org.ddsteps.spring.DDStepsSpringTestCase;
//import org.jdom.JDOMException;
//import org.xml.sax.SAXException;
//
//import com.meterware.httpunit.WebConversation;
//import com.meterware.httpunit.WebResponse;
//
//import org.ddsteps.dataset.excel.ExcelDataSetLoader;
//import org.ddsteps.data.*;
//import org.ddsteps.dataset.*;
//
//import rtb.*;
//
///**
// *  This class is parent class of test cases to do some common operation of test cases
// * @author eden.liu
// *
// */
//public abstract class AFServiceTestCase extends DDStepsSpringTestCase {
//	
//	public String scenario = new String();
//	public String execution = new String();
//	public boolean executeAllScenarios;
//	public NotCompareList notComparelist;
//	public CompareList compareList;
//	
//	public final String MYNULL ="MYNULL";
//	
//	public static void PrintArray(String[] strArray)
//	{
//		FileWriter outFile;
//		String resultOutput = "";
//		for (int i = 0; i < strArray.length; i++) {
//			resultOutput = resultOutput + strArray[i] + "|";
//			
//		}
//		System.out.println(resultOutput);
////		try {
////			outFile = new FileWriter("c:\\rtb_expected_result.txt");
////			PrintWriter out = new PrintWriter(outFile);
////			out.println(resultOutput);
////			out.close();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
//	
//	public AFServiceTestCase() {
//		super();
//		setDependencyCheck(false);
//	}
//
//	public boolean isExecuteAllScenarios() {
//		return executeAllScenarios;
//	}
//
//	public void setExecuteAllScenarios(boolean executeAllScenarios) {
//		this.executeAllScenarios = executeAllScenarios;
//	}	
//	
//	public String getScenario() {
//		return scenario;
//	}
//	public void setScenario(String scenario) {
//		this.scenario = scenario;
//	}
//		
//	public String getExecution() {
//		return execution;
//	}
//	public void setExecution(String execution) {
//		this.execution = execution;
//	}
//	
//	public NotCompareList getNotComparelist() {
//		return notComparelist;
//	}
//	public void setNotComparelist(NotCompareList notComparelist) {
//		this.notComparelist = notComparelist;
//	}
//	public CompareList getCompareList() {
//		return compareList;
//	}
//	public void setCompareList(CompareList compareList) {
//		this.compareList = compareList;
//	}
//	public String getRunningModeFlag() {
//		return (String)this.applicationContext.getBean("runningModeFlag");
//	}	
//	public String getTestResultPath() {
//		return (String)this.applicationContext.getBean("testResultPath");
//	}
//	public String getScenario_http_command(){
//		return  (String)this.applicationContext.getBean("scenario_http_command");
//	}
//	
//	public Boolean getExecuteAllScenarios(){
//		return  Boolean.parseBoolean((String) this.applicationContext.getBean("executeAllScenarios"));
//	}
//	
//	@Override
//	public void setUpMethod() throws Exception {
//		super.setUpMethod();
//		//RTBManager manager = new RTBManager(this.getClass());
//		//manager.clearRTB();
//		//manager.loadRTB();
//	}
//	
//	@Override
//	public void tearDownMethod() throws Exception {		
//		setScenarioName("");
//		super.tearDownMethod();
//
//	}
//		
//	/**
//	 * Set the scenario of web service to load corresponding XML files
//	 * @param scenario
//	 */
//    public void setScenarioName(String scenario){
//		WebConversation wc = new WebConversation();
//		try {			
//			WebResponse wr = wc.getResponse( getScenario_http_command()+scenario);
//		} catch (MalformedURLException e) {
//			
//		} catch (IOException e) {
//			
//		} catch (SAXException e) {
//		
//		}
//
//    }
//    /**
//     * if the folder of path doesn't exit, create this folder
//     * @param path
//     */
//	public void createFolder(String path){
//		StringTokenizer st = new StringTokenizer(path,"\\");
//		String pathToCreate = "";
//		while(st.hasMoreTokens()){
//			String p =st.nextToken();
//			if(pathToCreate.equals(""))pathToCreate = p;
//			else if(p!=null&& !p.equals("")) pathToCreate = pathToCreate+"\\"+p;
//			if(!new File(pathToCreate).isDirectory()){
//	    		new File(pathToCreate).mkdir();	    		
//	    	}			
//		}
//	}
//	
//	/**
//	 * 
//	 * @param testCase	 
//	 * @param returnedDDO
//	 * @param scenario
//	 * @param compareList 
//	 * @throws JDOMException 
//	 * @throws IOException 
//	 * @throws SAXException 
//	 */
//	public void testCase(Object testCase,Object returnedDDO,String scenario){
//		String expectedResultPath = testCase.getClass().getResource("../").getPath().replace("/bin", "").replaceFirst("/", "").replace("%20", " ") + getTestResultPath()+"\\"+testCase.getClass().getSimpleName().substring(0,testCase.getClass().getSimpleName().indexOf("Test"))+"\\expectedResult\\";		
//		String actualResultPath = testCase.getClass().getResource("../").getPath().replace("/bin", "").replaceFirst("/", "").replace("%20", " ") + getTestResultPath()+"\\"+testCase.getClass().getSimpleName().substring(0,testCase.getClass().getSimpleName().indexOf("Test"))+"\\actualResult\\";		
//		String testCaseSimpleName = testCase.getClass().getSimpleName();
//		String testCaseCompareListKey = testCaseSimpleName.substring(0,1).toLowerCase()+testCaseSimpleName.substring(1,testCaseSimpleName.lastIndexOf("Test"))+"CompareList";
//		String testCaseNotCompareListKey = testCaseSimpleName.substring(0,1).toLowerCase()+testCaseSimpleName.substring(1,testCaseSimpleName.lastIndexOf("Test"))+"NotCompareList";
//		this.setScenarioName(""); 				
//		
//		//run in update mode
//		if(getRunningModeFlag().equalsIgnoreCase("1")){
//	    	if(!new File(expectedResultPath).isDirectory()){
//	    		createFolder(expectedResultPath);	    		
//	    	}    	    
//	    	XmlUtil.generateXMLFile(expectedResultPath+scenario+".xml", returnedDDO);
//	    	assertTrue(true);	
//	    }
//		
//		// run in verification not compare in list mode
//	    else if(this.getRunningModeFlag().equalsIgnoreCase("0")){
//	    	if(!new File(actualResultPath).isDirectory()){
//	    		createFolder(actualResultPath); 		
//	    	}
//	    	XmlUtil.generateXMLFile(actualResultPath+scenario+".xml", returnedDDO); 	    	
//	    	String comparesult = XmlUtil.compareFile(expectedResultPath+scenario+".xml", actualResultPath+scenario+".xml",scenario,(ArrayList)notComparelist.getNotCompareList().get(testCaseNotCompareListKey),"notCompare");    	
//	    	if(!comparesult.equals("success")){ 		
//	    		assertNull(comparesult,"wrong");  	    		
//	    	}
//	    	else{
//	    		assertTrue(true);
//	    	}
//	    }   
//		
//		// run in verification compare in list mode
//	    else if(this.getRunningModeFlag().equalsIgnoreCase("2")){
//	    	if(!new File(actualResultPath).isDirectory()){
//	    		createFolder(actualResultPath); 		
//	    	}
//	    	XmlUtil.generateXMLFile(actualResultPath+scenario+".xml", returnedDDO); 	    	
//	     	String comparesult = XmlUtil.compareFile(expectedResultPath+scenario+".xml", actualResultPath+scenario+".xml",scenario,(ArrayList)compareList.getCompareList().get(testCaseCompareListKey),"compare");    	
//	     	if(!comparesult.equals("success")){ 		
//	    		assertNull(comparesult,"wrong");  	    		
//	    	}
//	    	else{
//	    		assertTrue(true);
//	    	}
//	    }
//    
//	    
//	}	
//}
