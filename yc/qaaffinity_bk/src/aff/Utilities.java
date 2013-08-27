package aff;

import utilityLib.Tools;

public class Utilities {

	public static void resetAffinity() {
		// String[] processNames = { "iexplore.exe", "extra.exe",
		// "affinitysharevariables.exe", "ffbsharedmemory.exe" };
		killProcesses("iexplore.exe");
		killProcesses("ffbsharedmemory.exe");
	}

	public static void killProcesses(String p) {
		try {
			while (Tools.isProcessRunging(p)) {
				System.out.print(p + ", ");
				Tools.killProcess(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
