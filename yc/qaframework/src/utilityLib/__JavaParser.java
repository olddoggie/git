package utilityLib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class __JavaParser {

	public static void main(String[] args) throws FileNotFoundException {

		String myPat, myString, tag;
		myString = new Scanner(new File("C:/workspace/qaframework/src/database/SecurityWordSql.java")).useDelimiter(
				"\\Z").next();


		myPat = "(public)(.*)";
		createVariable(myPat, myString);
	}

	public static void createVariable(String pat, String myStr) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(myStr);
		boolean found = false;
		int count = 0;
		while (matcher.find()) {
			System.out.println(matcher.group(2));
			
	}
	}

}
