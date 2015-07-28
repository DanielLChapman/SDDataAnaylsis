import java.io.IOException;

import org.jsoup.*;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class DataPull {

	public static void main(String[] args) throws IOException {
		
		
		Document doc = Jsoup.connect("http://www.silverdaddies.com/login.asp").get();
		Elements text = doc.getElementsByClass("textcontent");
		Elements form = text.select("form");
		StringBuilder urlTemp = new StringBuilder();
		urlTemp.append( form.outerHtml().toString());
		urlTemp.delete(0, 27);
		StringBuilder url = new StringBuilder();
		url.append("http://www.silverdaddies.com/");
		while(urlTemp.charAt(0) != '"' ) {
			url.append(urlTemp.charAt(0));
			urlTemp.deleteCharAt(0);
		}
		System.out.println(url.toString());
		
		Connection.Response res = Jsoup.connect(url.toString())
		        .data("username", "username", "password", "password")
		       .method(Method.POST)
		       .execute();
		
		Document newPage = res.parse();
		if (newPage.title().contains("Profile")) {
			System.out.println("made it");
		}
		else {
			System.out.println("didnt make it");
		}
		String html = "<div class='content'><center>bullshit<table cellspacing='0' cellpadding='0'><tr><td>Test 1</td><td>Test 2</td></tr></table><table cellspacing='0' cellpadding='0'><tr><td>Test 3</td><td>Test 4</td></tr></table></center></div>";
		Elements tables = doc.select("table");
		Element table = tables.get(1);
	}
}
