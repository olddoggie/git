package aff.tool;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import utilityLib.MethodParamNamesScaner;


/**
 * 
 * @author jyu
 * This class export the parameter of the keyword method together with the keyword
 * first, you need a JAVA-to-COM bridge like JACOB, COM4J or a similar tool. It is sufficient if it supports automation interfaces. 
 */
public class KeywordExcel {
	

	public static void main(String[] args) throws ClassNotFoundException, BiffException, IOException, WriteException {

		List<Class<? extends Object>> classNames = getClassesFromPackages("src/aff/pages", "aff.pages");
		List<List<String>> keywordList = new ArrayList<List<String>>();
		List<List<String>> libraryList = new ArrayList<List<String>>();
		for (Class<? extends Object> c1 : classNames) {
				keywordList.addAll(generateKeywords(c1));
				libraryList.addAll(generateLibrary(c1));
		}
		// writeExcel("resource/xls/keyword_template.xls", keywordList,
		// libraryList);
		writeExcel("resource/xls/Library.xls", keywordList, libraryList);

	}
	

	public static List<Class<? extends Object>> getClassesFromPackages(String packageDir, String packageName) throws ClassNotFoundException {
		File dir = new File(packageDir);
		String[] classNames = dir.list();
		Arrays.sort(classNames);
		List<Class<? extends Object>> myList = new ArrayList<Class<? extends Object>>();
		for (String s : classNames) {
			if (s.matches(".*java")) {
				Class<? extends Object> c = Class.forName(packageName + "." + s.replace(".java", ""));
				myList.add(c);
			}
		}
		return myList;
	}

	public static List<List<String>> generateKeywords(Class<? extends Object> c) {
		
		List<List<String>> cList = new ArrayList<List<String>>();
		String className = c.getSimpleName();

		Method[] mArr = c.getDeclaredMethods();
		for (Method m : mArr) {
			if (Modifier.isPublic(m.getModifiers())) {
				List<String> keywordList = new ArrayList<String>();
				// add class name
				keywordList.add(className);
				// add keyword
				String methodName = m.getName();
				////keywordList.add(m.getName());
				// add keyword parameters
				List<String> methodsList = MethodParamNamesScaner.getParamNames(m);
				String paraList ="";
				for(String s : methodsList){
						paraList = paraList + s+ ",";
					}	
				int len = paraList.length();
				String methodSignature = "";
				if(paraList.length()>0){
					methodSignature = methodName + "(" + paraList.substring(0, len-1) + ")";
				}else
				{
					methodSignature =  methodName + "()";
				}

				keywordList.add(methodSignature);
				// we actually don't need this piece of info. It is only used to make sure that the keyword is activated in excel
				//keywordList.add("a");
				cList.add(keywordList);
			}

		}

		Field[] fArr = c.getDeclaredFields();
		for (Field f : fArr) {
			List<String> keywordList = new ArrayList<String>();
			// if(f.getType().getName().contains("MyWebElement"))
			if (Modifier.isPublic(f.getModifiers())) {
				keywordList.add(className);
				keywordList.add(f.getName());
				//keywordList.add("a");
				cList.add(keywordList);
			}
		}		
		return cList;
	}

	public static List<List<String>> generateLibrary(Class<? extends Object> c) {
		List<List<String>> cList = new ArrayList<List<String>>();
		String className = c.getSimpleName();

		Method[] mArr = c.getDeclaredMethods();
		List<String> kList = new ArrayList<String>();
		for (Method m : mArr) {
			if (Modifier.isPublic(m.getModifiers())) {
				kList.add(m.getName());
			}
		}
		Field[] fArr = c.getDeclaredFields();
		for (Field f : fArr) {
			if (Modifier.isPublic(f.getModifiers())) {
				kList.add(f.getName());
			}
		}
		int max = 0;
		String longestString = "";
		for (String s : kList) {
			int sLen = s.length();
			if (sLen > max) {
				max = sLen;
				longestString = s;
			}
		}
		if (!longestString.equals("")) {
			List<String> excelList = new ArrayList<String>();
			excelList.add(className);
			excelList.add(longestString);
			cList.add(excelList);
		}
		return cList;

	}

	public static void writeExcel(String filePath, List<List<String>> keywordList, List<List<String>> libraryList) throws BiffException, IOException,
			WriteException {

		// The default file only has the title column for keywords.
		// The default file has not keywords		
		Workbook defaultWorkbook = Workbook.getWorkbook(new File("resource/xls/library_template.xls"));
		File libaryFile = new File(filePath);
		if (libaryFile.exists()) {
			libaryFile.delete();
		}
		WritableWorkbook wb = Workbook.createWorkbook(libaryFile, defaultWorkbook);

		WritableSheet sheet = wb.getSheet("Keyword_Target");
		int row = 1;
		for (List<String> slist : keywordList) {
			int column = 1;
			for (String s : slist) {
				sheet.addCell(new Label(column, row, s));
				column++;
			}
			row++;
		}

		sheet = wb.getSheet("Library");
		row = 1;
		for (List<String> slist : libraryList) {
			int column = 1;
			for (String s : slist) {
				
				sheet.addCell(new Label(column, row, s));
				column++;
			}
			row++;
		}

		wb.write();
		wb.close();

	}

}
