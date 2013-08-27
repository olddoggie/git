package bizprocesses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import java.util.zip.*;

import frameLib.MyException;

import static myconstant.BizWork.*;

public class RtbUtil {
	static String userName = BW_SERVER_USER_NAME ;
	static String password = BW_SERVER_PASSWORD;
	static String hostName = BW_SERVER_NAME;
	static String rtbAutoDir = "/1fb/testdata/rtbAutomation";
	static String rtbProdDir = "/1fb/testdata/BizworksRegressionTestbed";

	public static void main(String[] args) {
		RtbUtil.setConnection("tibco", "QAt3am11", "qsfoapv2");
		
		copyBwFolder("1fbbizprocess_default", "1fbbizprocess");
		
		//String[] files = { "C:/1fb/testdata/1.xls", "C:/1fb/testdata/2.xls" };
		//String[] files = { "C:/workspace/ITO/QA/Affinity/Test Cases/Shared/QA_Term.xls"};
		String[] files = { "resource/xls/QA_Term.xls"};
		RtbUtil.putFiles2BW(files);
		//RtbUtil.deleteBwFolder("accountbizprocess");
		RtbUtil.deleteBwFolder("1fbbizprocess");
		//RtbUtil.copyBwFolder("accountbizprocess_exception", "accountbizprocess");
		RtbUtil.generateScenarios("xls/QA_Term.xls");
		// RtbUtil.generateScenarios("xls/QA_Minimum_Payment_Due_Test.xls");
		System.out.print("done");
	}

	public static void setScenarioName(String port, String scenario) {
		try {
			URL url = new URL("http://" + BW_SERVER_NAME + ":" + port + "/RegressionTestbed?" + scenario);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.getInputStream();
			conn.disconnect();
		} catch (Exception e) {
			throw new MyException("rtb set failed");
		}
	}
	public static void turnOffRtb(String port){
		setScenarioName(port, "");
	}
	
	public static void setConnection(String myUuserName, String myPassword, String myHostName) {
		userName = myUuserName;
		password = myPassword;
		hostName = myHostName;
	}

	public static void deleteBwFolder(String bwFolderName) {
		String cmd = "rm -r " + rtbProdDir + "/" + bwFolderName;
		RtbUtil.execRemoteCmd(cmd);
	}

	public static void cleanCopyBwFolder(String from, String to) {
		String cmd = "";
		cmd = cmd + "rm -r " + rtbProdDir + "/" + to + " && ";
		cmd = cmd + "mkdir " + rtbProdDir + "/" + to + " && ";
		cmd = cmd + "cp -f -r " + rtbAutoDir + "/" + from + "/* " + rtbProdDir + "/" + to;
		RtbUtil.execRemoteCmd(cmd);
	}

	public static void copyBwFolder(String from, String to) {
		String cmd = "";
		cmd = cmd + "cp -f -r " + rtbAutoDir + "/" + from + "/* " + rtbProdDir + "/" + to;
		RtbUtil.execRemoteCmd(cmd);
	}
	
	public static void copyDefaultCapXML(){
		String cmd = "";
		cmd = cmd + "cp -f " + rtbAutoDir + "/1fbbizprocess_default/FDR/GetDataFromCAP/Default.xml " +  rtbProdDir + "/1fbbizprocess/FDR/GetDataFromCAP/";
		RtbUtil.execRemoteCmd(cmd);
	}
	
	public static void execRemoteCmd(String cmd) {
		Connection con = new Connection(hostName);
		try {
			con.connect();
			con.authenticateWithPassword(userName, password);
			Session session = con.openSession();
			session.execCommand(cmd);

			InputStream stdout = new StreamGobbler(session.getStdout());
			InputStream stderr = new StreamGobbler(session.getStderr());
			InputStreamReader insrout = new InputStreamReader(stdout);
			InputStreamReader insrerr = new InputStreamReader(stderr);
			BufferedReader stdoutReader = new BufferedReader(insrout);
			BufferedReader stderrReader = new BufferedReader(insrerr);
			while (true) {
				String line = stdoutReader.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}

			while (true) {
				String line = stderrReader.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
			session.close();
			con.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Upload multiple files to BW server
	 */
	public static void putFiles2BW(String... fileNames) {
		Connection con = new Connection(hostName);
		try {
			con.connect();
			con.authenticateWithPassword(userName, password);
			SCPClient scpClient = con.createSCPClient();
			scpClient.put(fileNames, rtbAutoDir + "/xls");
			con.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Upload one file to BW server
	 * @param path:  for example resource/xls/
	 * @param fileName: for example"QA_Minimum_Payment_Due_Test.xls"
	 */
	public static void putFile2BW(String path, String fileName) {
		Connection con = new Connection(hostName);
		try {
			con.connect();
			con.authenticateWithPassword(userName, password);
			SCPClient scpClient = con.createSCPClient();
			String xlsPath = RtbUtil.class.getClassLoader().getResource(path+fileName).getPath();
			//scpClient.put("c:/" + getWorkspacePath() + /test_bed_configuration/testcases/affinity/xls/mpd_data.xls","/1fb/testdata/rtbAutomation/xls");
			scpClient.put(xlsPath, rtbAutoDir + "/xls");
			con.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getWorkspacePath(){
		 String dir = System.getProperty("user.dir");
		 dir = dir.replace("c:\\\\(.*?)\\\\.*", "$2");
		 return dir;
	}
	/**
	 * Generate xmls for one file on BW server
	 */	
	public static void generateScenarios(String fileName) {
		String cmd = "cd  /1fb/testdata/rtbAutomation && ";
		cmd = cmd + "java -jar qaframework_fat.jar \"../BizworksRegressionTestbed/\" \"xls/" + fileName + "\" \"true\" \"-1\"";
		execRemoteCmd(cmd);
		// args[1]=rtbBaseDirectory, args[2]=book, args[3] = exportSetting,
		// args[4] = generateFlag
		// (green one/or all scenarios)
		// generate the xmls file to /1fb/testdata/BizworksRegressionTestbed
		// source file is fileName
		// when this flag ,exportAll, is false, only the green scenario will be
		// exported
		// generateFlag = -1 (most strict -- only generate xmls in both test and
		// sn_ worksheets)
		// generateFlag = 0 (moderate -- only generate xmls in sn_ worksheets)
		// generateFlag = 1 (all -- generate xmls in any worksheets)
	}
	/**
	 * Upload file to BW server and then generate xmls on BW server
	 * @param path is the local path. For example resource/xls/
	 * @param fileName
	 */
	public static void generateScenarios(String path, String fileName) {
		putFile2BW(path,fileName);
		generateScenarios(fileName);
	}

}
// private static void putDir(Connection conn, String localDirectory, String
// remoteTargetDirectory, String mode) throws IOException {
// final String[] fileList = curDir.list();
// for (String file : fileList) {
// final String fullFileName = localDirectory + "/" + file;
// if (new File(fullFileName).isDirectory()) {
// final String subDir = remoteTargetDirectory + "/" + file;
// Session sess = conn.openSession();
// sess.execCommand("mkdir " + subDir);
// sess.waitForCondition(ChannelCondition.EOF, 0);
// putDir(conn, fullFileName, subDir, mode);
// } else {
// SCPClient scpc = conn.createSCPClient();
// scpc.put(fullFileName, remoteTargetDirectory, mode);
// }
// }
// }

// Connection con = new Connection(host);
// try {
// con.connect();
// boolean isAuthed = con.authenticateWithPassword(user, pass);
// System.out.println("isAuthed====" + isAuthed);
//
// SCPClient scpClient = con.createSCPClient();
// // scpClient.put("localFiles", "remoteDirectory");
// // scpClient.get("/1fb/home/tibco/test.sh","c:/jun");
//
// scpClient.put("C:/jun/1", "/1fb/testdata/BizworksRegressionTestbed");
//
// //
// // SFTPv3Client sftpClient = new SFTPv3Client(con);
// // sftpClient.mkdir("newRemoteDir", 6);
// // sftpClient.rmdir("");
// //
// // sftpClient.createFile("newRemoteFile");
// // sftpClient.openFileRW("remoteFile");
//
// Session session = con.openSession();
// session.execCommand("uname -a && date && uptime && who"); // run
// // remote
// // command
//
// // Display output
// System.out.println("Here is some information about the remote host:");
// InputStream stdout = new StreamGobbler(session.getStdout());
//
// BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
//
// while (true) {
// String line = br.readLine();
// if (line == null)
// break;
// System.out.println(line);
// }
//
// /* Show exit status, if available (otherwise "null") */
//
// System.out.println("ExitCode: " + session.getExitStatus());
//
// session.close();
// con.close();
// } catch (IOException e) {
// e.printStackTrace();
// }