package rtbLib;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * 
 * <p>
 * This class is used to compare two XML files and report the differences
 * </p>
 * <p>
 * Company: 1FB USA
 * </p>
 *
 * @author Jason.huang
 * Created:Sep 7, 2009
 *
 */
public class XMLComparator {

	private List<String> difference = new ArrayList<String>(); // store the difference information
	private final String CHANGE_LINE = "\n";
	private String actualNodePath = null;
	private String rootName = null;
	private List fieldCompareList = null;
	private String comparePattern = null;
	private XMLOutputter outputter = new XMLOutputter();
	private final static int outputLength = 50; 
	
	public List getDifference(){
		return difference;
	}
	
	public XMLComparator(){
		
	}
	
	public XMLComparator(List fieldCompareList, String comparePattern){
		this.fieldCompareList = fieldCompareList;
	    this.comparePattern = comparePattern;
	}
	
	/**
	 * get the direct children names
	 * @param expectRoot
	 * @return
	 */
	private List<String> getChildrenNames(Element expectRoot){
	    List<Element> children = expectRoot.getChildren();
		List<String> childrenNames = new ArrayList<String>();
		for(int i = 0; i < children.size(); i++){
			Element child = (Element)children.get(i);
			if(!childrenNames.contains(child.getName()))
			childrenNames.add(child.getName());
		}		
	    return childrenNames;
	}
	
	/**
	 * Method used to compare two XML document and generate the difference result
	 * @param expectRoot: expect document to compare
	 * @param actualRoot: actual document to compare
	 * @param fieldCompareList: field list to compare or not to compare depends on parameter comparePattern
	 * @param comparePattern: specify fields in fieldCompareList need to compare or needn't compare
	 */
	public void compareTwoXML(Element expectRoot, Element actualRoot){
	    List<String> expectNames = getChildrenNames(expectRoot);
	    if(expectNames.isEmpty()){
	    	difference.add("Expect xml doesn't have any node.");
	    	return;
	    }
	    List<String> actualNames = getChildrenNames(actualRoot);
	    if(actualNames.isEmpty()){
	    	difference.add("Actual xml doesn't have any node.");
	    	return;
	    }
	    rootName = actualRoot.getName();   	    
	    for(int i=0 ; i < actualNames.size(); i++){	    	
	    	String nodeName = actualNames.get(i);
	    	actualNodePath = nodeName;
	    		    	
            if(comparePattern.equals("compare") && fieldCompareList != null){
            	//check if the field is not in compare list, skip this element
            	if(fieldCompareList.isEmpty()){
            		return;
            	} else if(!isNeedCompare(actualNodePath)){
            		expectRoot.removeChildren(nodeName);
            		continue;
            	} 
            } else if(comparePattern.equals("notCompare") && fieldCompareList != null){
            	//check if the field is in the not compare list, skip this element
            	if(fieldCompareList.isEmpty()){
            		
            	} else if(!isNeedCompare(actualNodePath)){
            		expectRoot.removeChildren(nodeName);
            		continue;
            	}
            }
            
	    	List<Element> actualNodes = actualRoot.getChildren(nodeName);
	    	List<Element> expectNodes = expectRoot.getChildren(nodeName); 
	        if(actualNodes.size() == 1){
	        	//just one node
	        	Element actualNode = actualNodes.get(0);
	        	if(actualNode.getChildren().size() == 0){
	        		//value is text
	        		compareText(expectNodes, actualNode);
	        	} else {
	        		//have nested nodes
	        		compareNode(expectNodes, actualNode);
	        	}
	        } else {
	        	// has many same name nodes, compare nodes list
	        	compareNodesList(expectNodes, actualNodes);
	        }
	        expectRoot.removeChildren(nodeName);
	    }	    
	    checkRemainExpectNodes(expectRoot);
	}
	
	/**
	 * handle the actualNode value is text node
	 * @param expectNodes
	 * @param actualNode
	 */
	private void compareText(List<Element> expectNodes, Element actualNode){
		String actualValue = actualNode.getText();		
		if(expectNodes.isEmpty()){
			difference.add("Add an object " + actualNodePath + ": " + trimIfNeeded(outputter.outputString(actualNode)));
		} else {
		    Element expectNode = expectNodes.get(0);
		    if(expectNode.getChildren().isEmpty()){
		    	//expect value also is text, then compare the text content
		    	if(!expectNode.getText().equals(actualNode.getText())){
		    		difference.add("The value of object " + actualNodePath + " is modified:" + CHANGE_LINE + "actual:" + actualValue + CHANGE_LINE + "expect:" + expectNode.getText());		    		
		    	}
		    } else {
		    	//expect has nested node
		    	difference.add("The structure of object " + actualNodePath + " is modified");	
		    } 
		    if(expectNodes.size() > 1){
		    	// expect value is array , the first already compare, record the rest information
		    	for(int i = 1 ; i < expectNodes.size(); i++){
		    		difference.add("Remove an object " + actualNodePath + ": " + trimIfNeeded(outputter.outputString(expectNodes.get(i))));
		    	}
		    }
		    
		}
	}
	
	/**
	 * handle the actualNode has nested nodes
	 * @param expectNodes
	 * @param actualNode
	 */
	private void compareNode(List<Element> expectNodes, Element actualNode){
		
		if(expectNodes.isEmpty()){
			difference.add("Add an object " + actualNodePath + ": " + trimIfNeeded(outputter.outputString(actualNode)));
		} else {
		    Element expectNode = expectNodes.get(0);
		    if(expectNode.getChildren().isEmpty()){
		    	//actual node has children, but expect node is a text node
		    	difference.add("The structure of object " + actualNodePath + " is modified");	
		    } else {
		    	//both actual and expect nodes have nested nodes, compare their children nodes
		    	List<String> actualNames = getChildrenNames(actualNode);
		    	String oldNodePath =  actualNodePath;
		    	for(int i = 0; i < actualNames.size(); i++ ){
		    		String nodeName = actualNames.get(i);
		    		actualNodePath = oldNodePath + "/" + nodeName;
		    		// check this field need compare or not
		    		if(fieldCompareList != null && !isNeedCompare(actualNodePath)){
		    			expectNode.removeChildren(nodeName);
		            	continue;
		    		}

		    		List<Element> actualChildNodes = actualNode.getChildren(nodeName);
		    		List<Element> expectChildNodes = expectNode.getChildren(nodeName);
			        if(actualChildNodes.size() == 1){
			        	//just one node
			        	Element actualChildNode = actualChildNodes.get(0);
			        	if(actualChildNode.getChildren().size() == 0){
			        		//actual node is a text node
			        		compareText(expectChildNodes, actualChildNode);
			        	} else {
			        		//actual node has children nodes
			        		compareNode(expectChildNodes, actualChildNode);
			        	}
			        } else {
			        	//has many same name nodes
			        	compareNodesList(expectChildNodes, actualChildNodes);
			        }
			        expectNode.removeChildren(nodeName);
		    	}
		    	actualNodePath = oldNodePath;
		    	
		    	//if still has children nodes in expectNode not compared, means 
		    	//these children nodes are not exist in actual result
		    	if(!expectNode.getChildren().isEmpty()){
		    		List<Element> remainNodes = expectNode.getChildren();
		    		for(int i = 0; i < expectNode.getChildren().size(); i++){
		    			Element remainNode = remainNodes.get(i);
		    			if(fieldCompareList == null || (fieldCompareList != null && isNeedCompare(actualNodePath + "/" + remainNode.getName()))){
		    		     	difference.add("Remove an object " + actualNodePath + "/" + remainNode.getName() + ": " + trimIfNeeded(outputter.outputString(remainNode)));
		    			}
		    		}
		    	}
		    }
		    
		    
		    if(expectNodes.size() > 1){
		    	// expect value is array but actual value is a single node, means some nodes removed from the actual result, 
		    	// the first node already compared, record the rest information
		    	for(int i = 1 ; i < expectNodes.size(); i++){
		    		difference.add("Remove an object " + actualNodePath + ": " + trimIfNeeded(outputter.outputString(expectNodes.get(i))));
		    	}
		    }
		    
		}
	}
	
	/**
	 * handle the actualNodes is a list include many same name nodes
	 * @param expectNodes
	 * @param actualNodes
	 */
	private void compareNodesList(List<Element> expectNodes, List<Element> actualNodes){
		
		if(expectNodes.isEmpty()){
			for(int i = 0; i < actualNodes.size(); i++){
				difference.add("Add an object " + actualNodePath + ": " + trimIfNeeded(outputter.outputString(actualNodes.get(i))));
			}			 
		} else {
	    	int expectLength = expectNodes.size();
	    	int actualLength = actualNodes.size();
	    	int arrayLength = actualLength < expectLength ? actualLength : expectLength;		    	
	    	for(int i = 0 ; i < arrayLength; i++){
	    		Element expectNode = expectNodes.get(i);
	    		Element actualNode = actualNodes.get(i);
		    	List<Element> newExpectNodes = new ArrayList<Element>();
		    	newExpectNodes.add(expectNode);
		    	if(actualNode.getChildren().isEmpty()){
		    		// actual value is text
		    		compareText(newExpectNodes, actualNode);
		    	} else {
		    		//actual value have nested node
		    		compareNode(newExpectNodes, actualNode);
		    	}
	    	}
	    	// handle the remain arrary
	    	if(expectLength > actualLength){
		    	for(int i = actualLength ; i < expectLength; i++){
		    		difference.add("Remove an object " + actualNodePath + ": " + trimIfNeeded(outputter.outputString(expectNodes.get(i))));
		    	}
	    	}
	    	if(actualLength > expectLength){
		    	for(int i = expectLength ; i < actualLength; i++){
		    		difference.add("Add an object " + actualNodePath + ": " + trimIfNeeded(outputter.outputString(actualNodes.get(i))));
		    	}
	    	}		    
		}
	}
	
	/**
	 * handle the remain nodes which haven't been compared in expected document 
	 * @param expectRoot
	 * @param fieldCompareList
	 * @param comparePattern
	 */
	private void checkRemainExpectNodes(Element expectRoot){
		List<String> expectNames = getChildrenNames(expectRoot);
	    if(!expectNames.isEmpty()){
	    	for(int i = 0; i < expectNames.size(); i++){
	    		if(fieldCompareList == null || (fieldCompareList != null && isNeedCompare(expectNames.get(i)))){
	    			difference.add("Remove an object " + expectNames.get(i) + ": " + trimIfNeeded(outputter.outputString(expectRoot.getChild(expectNames.get(i)))));
	    	    }
	    		
	    	}	    	
	    }
	}
	
	/**
	 * determine the node need to compare or not based on fieldCompareList and comparePattern
	 * @param name
	 * @param fieldCompareList
	 * @param pattern
	 * @return
	 */
	private boolean isNeedCompare(String name){
		boolean isCompare = true;
		String compareName = rootName + "." + name.replaceAll("/", ".");
    	if(comparePattern.equals("compare") && !fieldCompareList.contains(compareName)){
    		isCompare = false;
    	} else if(comparePattern.equals("notCompare") && fieldCompareList.contains(compareName)){
    		isCompare = false;
    	}
    	return isCompare;
	}
	
	private static String trimIfNeeded(String source){
		if(source != null && source.length() > outputLength)
		{
			return source.substring(0, outputLength) + "......";
		}
		return source;
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static void main(String[] args) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document expectedDoc = builder.build("D:/VSSFILE/QA/Automation Testing/bwtestharness/localfiles/testresult/1fbbizprocess/GetUserData/expectedResult/s_GetUserData_01.xml");
		Document actualDoc = builder.build("D:/VSSFILE/QA/Automation Testing/bwtestharness/localfiles/testresult/1fbbizprocess/GetUserData/actualResult/s_GetUserData_01.xml");
	//	Document expectedDoc = builder.build(new ByteArrayInputStream("<root><a></a><f></f><g></g><b></b></root>".getBytes("UTF-8")));
	//	Document actualDoc = builder.build(new ByteArrayInputStream("<root><a></a><e></e><h></h><b></b></root>".getBytes("UTF-8")));
		
		List compareList = new ArrayList();
		//compareList.add("UserDataDDO.resultActionEntries.resultActionEntryPosted");
		compareList.add("UserDataDDO.accountId");
		compareList.add("UserDataDDO.renewalCodeId");
		XMLComparator test = new XMLComparator(compareList, "notCompare");
		test.compareTwoXML(expectedDoc.getRootElement(), actualDoc.getRootElement());
		List difference = test.getDifference();
		for(Iterator it = difference.iterator(); it.hasNext();){
			String result = it.next().toString();
			System.out.println(result);
		}
		
		/*  Test data
		 * 1. simple text node, simple array, simple nested
		 *    Expect:<root><a></a><b>4</b><d>1</d><d>6</d><d>3</d><e><h>1</h><f>3</f><g>3</g></e></root>
		 *    Actual:<root><b>1</b><c></c><d>2</d><d>3</d><e><h>1</h><f>31</f><j>3</j></e></root>
		 * 2. complex nested, include array
		 *    Expect:<root><b><b>4</b><d>4</d><d>1</d><d>5</d><d>5</d></b></root>
		 *    Actual:<root><b><b>1</b><d>7</d><d>1</d><d>5</d></b></root>
		 * 3. complex nested, two level
		 *    Expect:<root><b><h><b>4</b></h><g><d>4</d><d>1</d><d>5</d><d>5</d></g></b></root>
		 *    Actual:<root><b><h><b>1</b></h><g><d>7</d><d>1</d><d>5</d></g></b></root>
		 * 4. complex array, has nested node and array
		 *    Expect:<root><d>7</d><d><b><h><b>4</b></h><g><d>4</d><d>1</d><d>5</d><d>5</d></g></b></d><d><j>1</j><j>3</j><j>3</j></d></root>
		 *    Actual:<root><d>1</d><d><b><h><b>1</b></h><g><d>7</d><d>1</d><d>5</d></g></b></d><d><j>3</j><j>3</j><j>4</j></d><d>3</d></root>
		 */
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
