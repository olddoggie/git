/* 
 * Copyright 2008 1fb.net Financial Services.
 *  
 * This document may not be reproduced, distributed or used 
 * in any manner whatsoever without the expressed written 
 * permission of 1st Financial Bank USA.
 */
package rtbLib;

import java.util.List;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;

/**
 * 
 * This test will illustrate some major limitations in XmlUnit library.
 * Please run each test method in the main method, and check the output.
 * And also pay attention to the comments we added for each test method.
 * 
 * How to understand the output message:
 * 1. Expected text value 'xx' but was 'xx', means value was changed
 * 2. Expected presence of child node 'null' but was 'a', means element a is not in expected result, but is in actual result.
 * 3. Expected presence of child node 'c' but was 'null', means element c is in expected result, but not in actual result.
 * 4. Expected element tag name 'b' but was 'a', means XmlUnit compares element a with element b which is unexpected.
 *
 * @author corwin.hu
 * Created:Aug 18, 2009
 *
 */
public class TestXmlUnitLib {

	public static void printDifferences(String expected, String actual) throws Exception
	{
		DetailedDiff myDiff = new DetailedDiff(new Diff(expected, actual));
        List allDifferences = myDiff.getAllDifferences();
        for(int i = 0; i < allDifferences.size(); i++)
        {
        	Difference diff = (Difference)allDifferences.get(i);
        	if(diff.getId() == DifferenceConstants.CHILD_NODELIST_SEQUENCE_ID
        			|| diff.getId() == DifferenceConstants.CHILD_NODELIST_LENGTH_ID) continue; //igore the sequence and number of childs
        	System.out.println(allDifferences.get(i));
        }
	}
	
	/**
	 * In this test case, we expect: 
	 * 1. XmlUnit reports that element <f> and <g> are removed. 
	 * 2. XmlUnit reports that element <e> and <h> are added.
	 * 
	 * But the actual result is:
	 * 1. XmlUnit compares element <f> with <e>.
	 * 2. XmlUnit compares element <g> with <h>.
	 * @throws Exception
	 */
	public static void testRemovedAndAdded() throws Exception 
	{
		String expected = "<root><a></a><f></f><g></g><b></b></root>";
        String actual =   "<root><a></a><e></e><h></h><b></b></root>";        
        printDifferences(expected, actual);
	}	
	
	/**
	 * This test case is similar as the first one "testRemovedAndAdded", the difference is 
	 * the removed and added elements are nested in another element, 
	 * we expect:
	 * 1. XmlUnit reports element <e> is removed.
	 * 2. XmlUnit reports element <f> is added.
	 * 
	 * But actual result is:
	 * 1. XmlUnit compares element <e> with <f>.
	 * 
	 * @throws Exception
	 */
	public static void testNestedRemovedAndAdded() throws Exception 
	{
		String expected = "<root><a><b></b><c></c><e></e></a></root>";
        String actual =   "<root><a><b></b><f></f><c></c></a></root>";        
        printDifferences(expected, actual);
	}
	
	/**
	 * This test case is used to test array of elements.
	 * We expect:
	 * 1. XmlUnit reports <d>2</d> to <d>5</d> are removed.
	 * 
	 * But actual result is:
	 * 1. XmlUnit always compares <d>2</d> to <d>5</d> with <d>1</d> in actual result
	 * @throws Exception
	 */
	public static void testArrayRemoved() throws Exception 
	{
		String expected = "<root><d>1</d><d>2</d><d>3</d><d>4</d><d>5</d></root>";
        String actual =   "<root><d>1</d></root>";        
        printDifferences(expected, actual);
	}
	
	/**
	 * This test case is used to test the situation that the array of elements are completely same, 
	 * but position of each element is different in expected result and actual result because there is 
	 * one more element before the array in expected result.
	 * 
	 * We expect:
	 * 1. XmlUnit reports element <a> is removed.
	 * 
	 * But actual result is:
	 * 1. XmlUnit compares element <a> with <d>1</d> in actual result
	 * 2. XmlUnit compares element <d>1</d> with <d>2</d>
	 * 3. XmlUnit compares element <d>2</d> with <d>3</d>
	 * @throws Exception
	 */
	public static void testRemovedWithArray() throws Exception 
	{
		String expected = "<root><a></a><d>1</d><d>2</d><d>3</d></root>";
        String actual   = "<root><d>1</d><d>2</d><d>3</d></root>";        
        printDifferences(expected, actual);
	}
	
	/**
	 * This test case is used to test the situation that the array of elements are completely same, 
	 * but position of each element is different in expected result and actual result because there is 
	 * one more element before the array in actual result.
	 * 
	 * We expect:
	 * 1. XmlUnit reports element <a> is added.
	 * 
	 * But actual result is:
	 * 1. XmlUnit compares element <d>2</d> with <d>1</d>
	 * 2. XmlUnit compares element <d>3</d> with <d>2</d>
	 * 3. XmlUnit reports element <a> from actual was added, it is correct.
	 * 4. XmlUnit reports element <d>3</d> from actual was added
	 * @throws Exception
	 */
	public static void testAddedWithArray() throws Exception 
	{
		String expected = "<root><d>1</d><d>2</d><d>3</d></root>";
        String actual   = "<root><a></a><d>1</d><d>2</d><d>3</d></root>";        
        printDifferences(expected, actual);
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{ 
		    TestXmlUnitLib.testRemovedAndAdded();
		    //TestXmlUnitLib.testNestedRemovedAndAdded();
		    //TestXmlUnitLib.testArrayRemoved();
		    //TestXmlUnitLib.testRemovedWithArray();
		    //TestXmlUnitLib.testAddedWithArray();		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}

}
