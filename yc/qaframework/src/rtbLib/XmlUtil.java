package rtbLib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;


/**
 * This class is the tool class  to generate or compare xml files
 * 
 * @author eden.liu
 *
 */
public class XmlUtil {
		
	private static final String ELEMENT_TAG_NAME_CHANGE = "element tag name";

	private static final String ELEMENT_ADD_OR_REMOVE = "presence of child node";

	private static final String ELEMENT_VALUE_CHANGE = "text value";
	
	private static final String CHANGE_LINE = "\n";
	
	private static final String CHANGE_TWO_LINE = "\n\n";
	
	private static final String[] notInlcudedFieldsList = {"__equalsCalc","__hashCodeCalc","typeDesc","pdfData"};
	
	private static SAXBuilder builder = new SAXBuilder();
		
	private static XMLOutputter outputter = new XMLOutputter();
		
	/**
	 * 
	 * @param expectedFileName	expectedResult xml file name
	 * @param actualResultDocument	actualResult xml file name
	 * @param scenario
	 * @param fieldCompareList	if pattern equals compare, it is compare list, else if pattern equals notCompare, it is not compare list
	 * @param pattern if it equals compare, run in compare mode, else if it equals notCompare , run in not compare mode
	 * @return	if the compare is passed , returned string "success" , else the detailed reportLog message will be returned  
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws JDOMException 
	 */
	public static String compareFile(String baseFileName,String actualResultDocument,String scenario, List fieldCompareList,String pattern){
		
				boolean compareresult = true;
				StringBuffer reportLog = new StringBuffer();	
				
				try {					
					reportLog.append("\n-----------------------------------------------\n");
					reportLog.append("Scenario File:"+scenario+".xml"+CHANGE_LINE+CHANGE_LINE);
					
					Document expectedDoc = builder.build(baseFileName);
					Document actualDoc = builder.build(actualResultDocument);
			    	XMLComparator compare = new XMLComparator(fieldCompareList, pattern);
                    compare.compareTwoXML(expectedDoc.getRootElement(), actualDoc.getRootElement());
            	    List difference = compare.getDifference();
            	    if(!difference.isEmpty()){
            		   compareresult = false;
            		   for(Iterator it = difference.iterator(); it.hasNext();){
                		   String result = it.next().toString();
                		   reportLog.append(result + CHANGE_LINE);
                	   }
            	    } 						

				} catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					compareresult = false;
					reportLog.append("One JDOMException happened" + CHANGE_LINE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					compareresult = false;
					reportLog.append("Can't find expect xml" + CHANGE_LINE);
				} finally {
					reportLog.append("\n-----------------------------------------------\n");	
				}				

			if(compareresult == false){
				return reportLog.toString();
			}else{
				return "success";
			}
	}	

	/**
	 * Check whether the field is to represent a status 
	 * @param simpleClassName
	 * @return
	 */	
	private static boolean isStatusObject(Object obj) {
		return obj.getClass().getSimpleName().indexOf("ResultId")!=-1 || obj.getClass().getSimpleName().indexOf("StatusId")!=-1 || (obj.getClass().getSimpleName().indexOf("Status")!=-1 && obj.getClass().getSimpleName().indexOf("Status")==obj.getClass().getSimpleName().length()-6)
		       &&!(obj instanceof Number && obj instanceof String) ;
	}

		/**
		 * Create XML document for the returned object
		 * @param returnedObject
		 * @return return the root element that represent the returnedObject
		 * @throws IOException
		 * @throws JDOMException
		 */
		public static Element createXml(Object returnedObject, String tagName){
			
			
			if(isStatusObject(returnedObject)){	
				Element root = new Element(tagName);
				root.addContent((String)XmlUtil.refectGetValue(returnedObject, "_value_"));	
				return root;						 
		   }
			
			
			//generate the xml dom for each field
			Field[] fields = returnedObject.getClass().getDeclaredFields();
			String fieldName = null;
			Object field = null;
			Element root;
			if(tagName.equals("")){
				root = new Element(returnedObject.getClass().getSimpleName());
			}else{
				root = new Element(tagName);
			}
			
			for(int i = 0; i<fields.length;i++){
				fieldName = fields[i].getName();
				if(isFieldNeeded(fieldName)){
					try {					
						field = PropertyUtils.getSimpleProperty(returnedObject, fieldName);
						
						if(field == null){
							Element fieldElement = new Element(fieldName);
							fieldElement.addContent("");							
							root.addContent(fieldElement);
						}else if( field.getClass().isArray()){
							for  (int  j=0;j<Array.getLength(field);j++){
								Object arrayObj = Array.get(field, j);
								//Updated by Jun 05/07/2010
								/// when field is string array
								if ((arrayObj instanceof Number) || (arrayObj instanceof String)||(field instanceof Date))
								{
									Element fieldElement = new Element(fieldName);
									fieldElement.addContent(arrayObj.toString());							
									root.addContent(fieldElement);
								}
								else
								{
									root.addContent(createXml(arrayObj,fieldName));
								}
							}
						} 
						
						//when type of field is list
						else if(field instanceof List){
							Object listObj = null;
							for(Iterator<List> iter = ((List)field).iterator(); iter.hasNext();){
								listObj = iter.next();
								root.addContent(createXml(listObj,fieldName));
							}
						}
						
						else if(isStatusObject(field)){	
							Element fieldElement = new Element(fieldName);
							fieldElement.addContent((String)XmlUtil.refectGetValue(field, "_value_"));	
							root.addContent(fieldElement);							 
					   }
					 
					    else if(!(field instanceof Number) && !(field instanceof String)&& !(field instanceof Date) && !(field instanceof Boolean)){
							root.addContent(createXml(field,fieldName));
						}

					    else {
								Element fieldElement = new Element(fieldName);
								fieldElement.addContent(field.toString());							
								root.addContent(fieldElement);
					     }
						
					    }catch (Exception e) {
					        e.printStackTrace();
				        }
				}
			}
			return root ;
		}
		
		private static String refectGetValue(Object outputDDO,String fieldName){
			String expectedValue = null;
			try {
				
				Field expectedField = outputDDO.getClass().getDeclaredField(fieldName);
				expectedField.setAccessible(true);
				expectedValue =	(String)expectedField.get(outputDDO);
				} catch (IllegalArgumentException e) {		
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SecurityException e) {				
					e.printStackTrace();
				} catch (NoSuchFieldException e) {			
					e.printStackTrace();
				}
			return expectedValue;
		}
		
		/**
		 * Check whether the field need to be created in the xml file
		 * @param fieldName the name of the field in the returned object
		 * @return if the fieldName is not to be created , return false, else return true
		 */
		private static boolean isFieldNeeded(String fieldName){
			for (int i = 0; i < notInlcudedFieldsList.length; i++) {
				if(notInlcudedFieldsList[i].equals(fieldName)){
						return false;
				}
			}
			return true;
		}
		
		/**
		 * Generate xml files for returned Object
		 * 
		 */
		public static void generateXMLFile(String fileName,Object returnedObject){
			Element root ;
			
	        if(returnedObject.getClass().getSimpleName().equals("GetOnlineStatementOutputDDO")){
	        	root = new Element("root");
	        	
	        	root.addContent(createXml(returnedObject,""));
	        	
	        	Element pdfData = new Element("pdfData");
	        	
	        	//TODO convert the pdfData from byte arry to string
		        //pdfData.addContent(new String(((GetOnlineStatementOutputDDO)returnedObject).getPdfData()));
		        pdfData.addContent("");
		  
	        	root.addContent(pdfData);
	        }else {
	        	root = createXml(returnedObject,"");
	        }	        
	        
	        
			Document doc = new Document(root); 		
	        try {
	        	 Format format = Format.getRawFormat();
		         format.setEncoding("UTF-8");           
		         format.setIndent("    ");  
		         
				 XMLOutputter XMLOut = new XMLOutputter(format); 
				 XMLOut.output(doc, new FileOutputStream(fileName));
				
			} catch (Exception e) {			
				e.printStackTrace();
			} 	
		}
	
		public static UnKnownException GetUnknowException(String errorId,String errorMesg){
			UnKnownException unKnownException = new UnKnownException();
			unKnownException.setErrorCode(errorId);
			unKnownException.setErrorMessage(errorMesg);
			return unKnownException;
			
			
		}

	}

 
