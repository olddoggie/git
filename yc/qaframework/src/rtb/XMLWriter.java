package rtb;

import java.io.*;

public class XMLWriter extends FileWriter{
	private String indent = "";
	
	public XMLWriter(File file) throws IOException{
		super(file);
	}
	
	public void header(FileWriter writer) throws IOException{
		writer.write("<?xml version=\"1.0\" encoding=\"US-ASCII\"?>\n");
	}
	
	public void dataElement(FileWriter writer, String element, String value) throws IOException{
		writeXML(writer, element, "", "", value, indent, true, false);
	}
	
	public void dataElement(FileWriter writer, String element, String prefix, String uri, String value) throws IOException{
		writeXML(writer, element, prefix, uri, value, indent, true, false);
	}
	
	public void openTag(FileWriter writer, String element) throws IOException{
		writeXML(writer, element, "", "", "", indent, false, true);
		indent += "  ";
	}
	
	public void closeTag(FileWriter writer, String element) throws IOException{
		indent = indent.substring(0, indent.length()-2);
		writeXML(writer, element, "", "", "", indent, false, false);		
	}
	
	public void openTag(FileWriter writer, String element, String prefix, String uri) throws IOException{
		writeXML(writer, element, prefix, uri, "", indent, false, true);
		indent += "  ";
	}
	
	public void closeTag(FileWriter writer, String element, String prefix, String uri) throws IOException{
		indent = indent.substring(0, indent.length()-2);
		writeXML(writer, element, prefix, uri, "", indent, false, false);		
	}
	
	public void writeXML(FileWriter writer, String element, String prefix, String uri, String value, String indent, boolean isData, boolean open) throws IOException{
		String printUri = "";
		String printPrefix = "";
		
		if(prefix != null && prefix.length() > 0){	
			printUri = " xmlns:" + prefix + "=\"" + uri + "\"";
			printPrefix = prefix + ":";
		}
		
		if(isData){
			writer.write(indent+"<"+printPrefix+element+printUri+">"+value+"</"+printPrefix+element+">\n");
		} else if(open) {
			writer.write(indent+"<"+printPrefix+element+printUri+">\n");
		} else {
			writer.write(indent+"</"+printPrefix+element+">\n");
		}
	}
}
