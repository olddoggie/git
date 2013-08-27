package rtb;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

import java.util.*;

import jxl.*;
import jxl.write.*;
import jxl.read.biff.BiffException;
import java.io.*;

public class Excel2RTBSheetReader {	
	private Workbook book;
	private File rtbBaseDirectory;
	
	private Vector<Column>columns = new Vector<Column>();
	private Hashtable<String, Column>columnTable = new Hashtable<String, Column>();
	
	private int headerRow = 0;
	private int scenarioColumn = 0;
	private int baseDataColumn = 1;
	
	private static class Column{
		String name;
		String prefix;
		String uri;
		int idx;
		int level;
		boolean data;
		
		public Column(String name, String prefix, String uri, boolean data, int idx, int level){
			this.name = name;
			this.prefix = prefix;
			this.uri = uri;
			this.data = data;
			this.idx = idx;
			this.level = level;
		}
	}
	
	public Excel2RTBSheetReader(String rtbBaseDirectory, String book) throws IOException, BiffException{
		this.book = Workbook.getWorkbook(new File(book));	
		this.rtbBaseDirectory = new File(rtbBaseDirectory);
	}
	
	public void readData(Sheet sheet, int level, int baseRow, int siblingIdx, int colIdx, String indent, FileWriter writer, int parentIdx) throws IOException{
		String value = sheet.getCell(colIdx+baseDataColumn, baseRow+siblingIdx).getContents();
		Column c = columns.get(colIdx);
		String prefix = c.prefix;
		String uri = "";				
		
		System.out.println(colIdx + "\t"+ value + "\t" + columns.size());
		
		if(prefix != null && prefix.length() > 0){	
			uri = " xmlns:" + prefix + "=\"" + c.uri + "\"";
			prefix = prefix + ":";
		}
		
		//write lead
		if(!c.data && value != ""){
			writer.write(indent+"<"+prefix+c.name+uri+">\n");
		
			//read children
			int i = colIdx+1;
			while(i+1 < columns.size()+1 && Integer.parseInt(sheet.getCell(i+1, headerRow+2).getContents()) >= level+1){
				if(Integer.parseInt(sheet.getCell(i+1, headerRow+2).getContents()) == level+1)
					readData(sheet, level+1, baseRow, siblingIdx, i, indent+"  ", writer, colIdx);
				i++;				
			}
		
			//write closing tag
			writer.write(indent+"</"+prefix+c.name+">\n");
			
			//read siblings		
			if(siblingIdx != 0)return;
			i = 5+siblingIdx+1;			
			while(i < sheet.getRows() && sheet.getCell(0, i).getContents().equals("")){
				if(sheet.getCell(colIdx+baseDataColumn, i).getContents() != ""){
					readData(sheet, level, baseRow, i-baseRow, colIdx, indent, writer, parentIdx);
				}
				i++;
			}						
		} else{
			writer.write(indent+"<"+parentIdx+prefix+c.name+uri+">"+value+"</"+prefix+c.name+">\n");
			//read siblings		
			if(siblingIdx != 0)return;
			int i = 5+siblingIdx+1;			
			while(i < sheet.getRows() && sheet.getCell(parentIdx+baseDataColumn, i).getContents().equals("")){
				if(sheet.getCell(colIdx+baseDataColumn, i).getContents() != ""){
					readData(sheet, level, baseRow, i-baseRow, colIdx, indent, writer, parentIdx);
				}
				i++;
			}	
		}		
	}
	
	public void readColumns(Sheet sheet, int rowIndex){
		Column c;
		int i = 0;
		
		do{
			c = null;			
			String prefix = sheet.getCell(i+1,rowIndex).getContents();
			String uri = sheet.getCell(i+1,rowIndex+1).getContents();
			String isData = sheet.getCell(i+1,rowIndex+3).getContents();
			String name = sheet.getCell(i+1,rowIndex+4).getContents();			
			int level;
			
			try{
				level = Integer.parseInt(sheet.getCell(i+1,rowIndex+2).getContents());				
			}catch(Exception e){
				level = -1;
			}
			
			System.out.println(level);
			System.out.println(isData);
			System.out.println(name);
			
			if(isData.equalsIgnoreCase("true")){
				c = new Column(name, prefix, uri, true, i, level);
			} else if(name != null && name != ""){
				c = new Column(name, prefix, uri, false, i, level);
			}
			
			if(c!= null)columns.add(c);
			i++;
		}while(i < sheet.getColumns()-1 && c != null);
	}
	
	private void loadScenario(Sheet sheet, File outputFile, int scenarioRow){
		try{
			if(!outputFile.getParentFile().exists()){
				outputFile.getParentFile().mkdirs();
			}
			FileWriter writer = new FileWriter(outputFile);
			writer.write("<?xml version=\"1.0\" encoding=\"US-ASCII\"?>\n");
			readData(sheet, 0, scenarioRow, 0, 0, "", writer, 0);
			writer.flush();
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadScenariosForStoredProcedure(Sheet sheet, File rtbBaseDirectory){
		System.out.println(sheet.getName());
		columns = new Vector<Column>();
		headerRow = 0;
		readColumns(sheet,0);
		for(int i = 5; i < sheet.getRows(); i++){
			
			if(sheet.getCell(0, i).getContents().equals("----")){
				columns.clear();
				readColumns(sheet, i+1);
				headerRow = i+1;
				i+=6;		
			}
			
			if(!sheet.getCell(0, i).getContents().equals("")){
				String scenario = sheet.getCell(0, i).getContents();						
				String application = sheet.getCell(0, headerRow).getContents();
				String rtbPath = sheet.getCell(0, headerRow+1).getContents();
				File f = new File(rtbBaseDirectory + "/" + application + "/" + rtbPath + "/" + scenario + ".xml"); 
				loadScenario(sheet, f, i);
				System.out.println(sheet.getCell(0, i).getContents() + " " + f.getAbsolutePath());
			}
		}
	}
	
	public void loadAllScenarios() throws Exception{		
		for(int i = 0; i < book.getNumberOfSheets(); i++){
			if(!book.getSheet(i).getName().startsWith("test"))
				loadScenariosForStoredProcedure(book.getSheet(i), rtbBaseDirectory);
		}
	}
	
	public static void main(String args[])throws Exception{
		//Excel2RTB loader = new Excel2RTB("C:\\1fb\\testdata\\BizworksRegressionTestbed", "c:\\bwtestharness\\localfiles\\testdata\\1fbbizprocess\\ExcelTest.xls");
		//loader.loadAllScenarios();
	}
}
