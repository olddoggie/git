package utilityLib;

//import java.io.Console;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class is created to generate page information
 */
public class WebDriverCodeGenerator {

	public static void main(String[] args) throws FileNotFoundException {

		String myPat, myString, tag;
		myString = new Scanner(new File("resource/webdriveHtml.txt")).useDelimiter(
				"\\Z").next();

		String taglowerCase = "input|img|select|button|a|div";
		String tagUpperCase = taglowerCase.toUpperCase();
		tag = "(" + taglowerCase + "|" + tagUpperCase + ")";
		
		//// match pattern id has double quote
		//myPat = "(<)" + tag + "([^/!>]*?)(id=\")(.*?)(\"[^>]*?)(>)";
		//// id doesn't have double quote (")
		myPat = "(<)" + tag + "([^/!>]*?)(id=)(\\S+)(\\s+.*? |[^>]*?)(>)";
		createVariable(myPat, myString, "id");

		// match pattern when name has double quote
		myPat = "(<)" + tag + "([^/!>]*?)(name=\")(.*?)(\"[^>]*?)(>)";
		//myPat = "(<)" + tag + "([^/!>]*?)(name=)(.*?)( [^>]*?)(>)";
		
		//// match pattern when name has no doubl quote
		myPat = "(<)" + tag + "([^/!>]*?)(name=)(\\S+)(\\s+.*? |[^>]*?)(>)";
		createVariable(myPat, myString, "name");

	}

	public static void createVariable(String pat, String myStr, String type) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(myStr);
		boolean found = false;
		int count = 0;
		while (matcher.find()) {
			String locator = matcher.group(5).replaceAll((">.*"), "");
			locator = locator.replaceAll("\"", "");
			if (type.equalsIgnoreCase("id")) {
				System.out.println("public static MyWebElement " + locator + " = myWebelement(\"" + locator + "\", \"" + locator + "\");");
				found = true;
			}
			if (type.equalsIgnoreCase("name")) {
				System.out.println("public static MyWebElement " + locator + " = myWebelement(\"name:" + locator + "\", \"" + locator + "\");");
				found = true;
			}

			count++;
		}
		// System.out.println(count);
		if (!found) {
			System.out.println("No match found.%n");
		}
	}

	public static void createMethod(String pat, String myStr) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(myStr);
		boolean found = false;
		int count = 0;
		while (matcher.find()) {
			String locator = matcher.group(5).replaceAll((">.*"), "");
			String locatorMethod = locator.substring(0, 1).toUpperCase()
					+ locator.substring(1);
			;
			// System.out.println(matcher.group(0) + "----" + matcher.start();
			System.out.println("public static MyWebElement get" + locatorMethod
					+ "()\n{\nreturn myWebElement(" + locator
					+ ");\n}");
			found = true;
			count++;
		}
		// System.out.println(count);
		if (!found) {
			System.out.println("No match found.%n");
		}
	}

}

