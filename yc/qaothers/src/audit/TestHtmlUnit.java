package audit;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TestHtmlUnit {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 */
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	    final WebClient webClient = new WebClient();
	    final HtmlPage mainPage = webClient.getPage("http://support.1fbusa.com/afcollagent/builtInTest.do");
	    // htmlUnit doesn't have getFrameById
	    final HtmlPage packagePage = (HtmlPage)mainPage.getFrames().get(0).getEnclosedPage();
	    String txt = packagePage.asText();
	    System.out.println(txt);
	}

}
