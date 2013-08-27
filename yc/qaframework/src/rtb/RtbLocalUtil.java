package rtb;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.vfs.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

import java.util.*;
import java.lang.Boolean;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;
import java.io.*;

public class RtbLocalUtil {

	public static Vector<String> GetStoredProcedureVector(String desDirPath) {
		File fo = new File(desDirPath);
		String databaseNames[];
		File storeProcedureFile;
		String storedProcedureNames[];
		Vector<String> storedProcedureVector = new Vector<String>();

		if (fo.isDirectory()) {
			databaseNames = fo.list();
			for (int i = 0; i < databaseNames.length; i++) {
				storeProcedureFile = new File(fo.getAbsolutePath() + "\\"
						+ databaseNames[i]);
				storedProcedureNames = storeProcedureFile.list();
				if (storeProcedureFile.isDirectory()) {
					for (int j = 0; j < storedProcedureNames.length; j++) {
						storedProcedureVector.addElement(databaseNames[i]
								+ "\\" + storedProcedureNames[j]);
						System.out.println(fo.getAbsolutePath() + "\\"
								+ databaseNames[i] + "\\"
								+ storedProcedureNames[j]);
					}
				}
			}
		}
		return storedProcedureVector;
	}

	public static void CopyExceptionXML(String baseDir, String exceptionFileName) {
		String srcFileName;
		String myScenario;
		String copyDir;
		String dbStoredProcedure;
		String myStoredProcedure;
		File desFile;

		Excel2RTB loader;
		Vector<String> myScenarios;
		Vector<String> storedProcedureVector = GetStoredProcedureVector(baseDir);

		try {
			for (Iterator iterator = storedProcedureVector.iterator(); iterator
					.hasNext();) {
				dbStoredProcedure = (String) iterator.next();
				myStoredProcedure = dbStoredProcedure.split("\\\\")[1];
				copyDir = baseDir + dbStoredProcedure;
				srcFileName = baseDir + exceptionFileName;
				FileUtils.copyFileToDirectory(new File(srcFileName), new File(
						copyDir));
				desFile = new File(copyDir + "\\exception.xml");
				desFile.renameTo(new File(copyDir + "\\" + myStoredProcedure
						+ "_exception.xml"));
				// }
				// else
				// {
				// RTB2ExcelExtractor.extractRTB(new File(copyDir));
				// }

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Delete XML is done successfully");
	}

	public static void RTB2ExcelAll(String baseDir,
			Boolean generateErrorScenarioFlag) {
		String copyDir;
		String dbStoredProcedure;

		Vector<String> storedProcedureVector = GetStoredProcedureVector(baseDir);

		/*
		 * try { for (Iterator iterator = storedProcedureVector.iterator();
		 * iterator .hasNext();) { dbStoredProcedure = (String) iterator.next();
		 * copyDir = baseDir + dbStoredProcedure;
		 * 
		 * RTB2ExcelExtractor.extractRTB(new File(copyDir),
		 * generateErrorScenarioFlag);
		 * 
		 * }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		for (Iterator iterator = storedProcedureVector.iterator(); iterator
				.hasNext();) {
			dbStoredProcedure = (String) iterator.next();
			copyDir = baseDir + dbStoredProcedure;
			try {
				RTB2ExcelExtractor.extractRTB(new File(copyDir),
						generateErrorScenarioFlag);
			} catch (Exception e) {
				System.out.println("========================"+ copyDir);
				e.printStackTrace();
			}
		}

		System.out.println("Generate excle from xml is done successfully");
	}

	public static void Excel2RTBAll(String baseDir, String bizProcess,
			Boolean exportAllFlag, int generateFlag) {
		String copyDir;
		String dbStoredProcedure;
		String myStoredProcedure;
		String myExcelFile;
		Excel2RTB loader;

		Vector<String> storedProcedureVector = GetStoredProcedureVector(baseDir
				+ "\\" + bizProcess);
		try {
			for (Iterator iterator = storedProcedureVector.iterator(); iterator
					.hasNext();) {
				dbStoredProcedure = (String) iterator.next();
				myStoredProcedure = dbStoredProcedure.split("\\\\")[1];
				copyDir = baseDir + "\\" + bizProcess + "\\"
						+ dbStoredProcedure + "\\";
				myExcelFile = copyDir + myStoredProcedure + ".xls";
				if (new File(myExcelFile).exists()) {
					loader = new Excel2RTB(baseDir, myExcelFile, exportAllFlag,
							generateFlag);
					loader.loadAllScenarios();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Generating xmls from excel is done successfully");
	}

	public static void DeleteExistingXML(String baseDir, String excelBaseDir,
			String[] myExcelArray) {
		File srcFile;
		String myScenario;

		Excel2RTB loader;
		Vector<String> myScenarios;
		Vector<String> storedProcedureVector = GetStoredProcedureVector(baseDir);

		for (int k = 0; k < myExcelArray.length; k++) {
			try {
				loader = new Excel2RTB("", excelBaseDir + myExcelArray[k], true);
				myScenarios = loader.executableScenarios;
				for (int i = 0; i < myScenarios.size(); i++) {
					myScenario = myScenarios.elementAt(i);
					System.out.println(myScenarios.elementAt(i));

					for (Iterator iterator = storedProcedureVector.iterator(); iterator
							.hasNext();) {
						srcFile = new File(baseDir + (String) iterator.next()
								+ "\\" + myScenario + ".xml");
						if (srcFile.exists()) {
							FileUtils.deleteQuietly(srcFile);
						}
					}
				}
				System.out.println("Delete XML is done successfully");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String args[]) throws Exception {

		String xmlBaseDir = "C:\\1fb\\testdata\\BizworksRegressionTestbed\\1fbbizprocess\\";
		int key;
		key = 5;

		switch (key) {
		case 5:
			// This utility will generate xml from one excel file
			xmlBaseDir = "C:/1fb/testdata/BizworksRegressionTestbed/";
			Excel2RTB loader = new Excel2RTB(xmlBaseDir,
					"C:/1fb/testdata/BizworksRegressionTestbed/accountbizprocess/FDR/GetBonusProgramData/GetBonusProgramData.xls",
					true, 1);
			loader.loadAllScenarios();
			break;
			
		case 6:
			// This utility will generate excel from xml files (need the default xml file in the folder)
			// true will generate exception files
			xmlBaseDir = "C:/1fb/testdata/BizworksRegressionTestbed/accountbizprocess/FDR/GetBonusProgramData";
			RTB2ExcelExtractor.extractRTB(new File(xmlBaseDir),true);
			break;
			
		case 1:
			// This utility will copy exception file to each stored procedure
			// folder
			// and Rename the exception xml to storeProcedureName_exception
			String exceptionFileName = "exception.xml";
			CopyExceptionXML(xmlBaseDir, exceptionFileName);
			break;
		case 2:
			// This utility will generate excel file for each sub SP folder
			// under xmlBaseDir
			// The exception case will not be converted
			// false means the exception scenarios will be ignored
			RTB2ExcelAll(xmlBaseDir, false);
			break;
		case 25:
			//// This utility will generate excel files for all xml files in cvs>DatabaseXMLfiles folder
			String spFolderPath;
			File spFolder;
			FileSystemManager fsManager;

			// delete cvs folders
			try {
				fsManager = VFS.getManager();
				FileObject fileObject = fsManager.resolveFile(xmlBaseDir);
				FileObject[] myDirectries = fileObject
						.findFiles(new FileTypeSelector(FileType.FOLDER));
				for (FileObject file2 : myDirectries) {
					if (file2.getURL().toString().indexOf("CVS") > 0) {
						file2.delete(new FileTypeSelector(FileType.FILE));
						file2.delete(new FileTypeSelector(FileType.FOLDER));
					}
				}
			} catch (FileSystemException e) {
				e.printStackTrace();
			}

			//// move xmls to its own folders
			File dir = new File(xmlBaseDir);
			System.out.println("Getting all files in " + dir.getCanonicalPath()
					+ " including those in subdirectories");
			// List<File> files = (List<File>) FileUtils.listFiles(dir,
			// TrueFileFilter.INSTANCE, FileFilterUtils.makeCVSAware(null));
			String[] myExtensions = { "xml", "XML" };
			List<File> files = (List<File>) FileUtils.listFiles(dir,
					myExtensions, true);
			for (File file : files) {
				spFolderPath = file.getCanonicalPath().replace(".xml", "")
						.replace(".XML", "");
				spFolder = new File(spFolderPath);
				FileUtils.moveFileToDirectory(file, spFolder, true);
				System.out.println("file: " + file.getCanonicalPath());
			}

			RTB2ExcelAll(xmlBaseDir, false);
			break;
		case 3:
			// This utility delete all scenarios defined in the excel files from
			// the scenario library
			String excelFileBaseDir = "C:\\workspace\\bwtestharness\\localfiles\\testdata\\1fbbizprocess\\";
			String[] myExcelArray = {
					"QA_Affinity_Shared_GeneralGetUserDatatest.xls",
					"QA_Affinity_Shared_NegAmtest.xls",
					"QA_Minimum_Payment_Due_Test.xls" };
			DeleteExistingXML(xmlBaseDir, excelFileBaseDir, myExcelArray);
			break;
		case 4:
			// This utility will generate xml from all worksheets of the excel file
			xmlBaseDir = "C:\\workspace\\bwtestharness\\bwscenariofiles\\1fbbizprocess_zip";
			Excel2RTBAll(xmlBaseDir, "1fbbizprocess", true, 1);
			break;
		case 45:
			// This utility will generate xml from all worksheets the name of
			// which start with "SN_"" of the excel file
			xmlBaseDir = "C:\\workspace\\bwtestharness\\bwscenariofiles\\1fbbizprocess_zip";
			Excel2RTBAll(xmlBaseDir, "1fbbizprocess", true, 0);
			break;
		default:
			break;

		}

	}
}
