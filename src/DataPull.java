import java.io.IOException;
import java.util.Map;

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
		        .data("username", "", "password", "")
		       .method(Method.POST)
		       .execute();
		
		Document newPage = res.parse();
		if (newPage.title().contains("Profile")) {
			System.out.println("made it");
		}
		else {
			System.out.println("didnt make it");
		}
		res = Jsoup.connect("http://www.silverdaddies.com/search_handler.asp")
				.cookies(res.cookies())
				.data("zip", "10001", "radius", "20")
				.data("profileid", "", "age_min", "", "age_max", "")
				.data("height_feet_min", "")
				.data("height_feet_max", "")
				.data("height_cen_min", "")
				.data("height_cen_max", "")
				.data("weight_lb_min", "")
				.data("weight_lb_max", "")
				.data("weight_kg_min", "")
				.data("weight_kg_max", "")
				.data("sexpref", "one")
				.data("top", "")
				.data("bottom", "")
				.data("oral_receive", "")
				.data("oral_give", "")
				.data("partnered", "-")
				.data("useLocation", "")
				.data("city", "")
				.data("state", "NY")
				.data("province", "")
				.data("country", "United States")
				.data("seek", "one")
				.data("seek1", "")
				.data("seek2", "")
				.data("seek3", "")
				.data("seek4", "")
				.data("seek5", "")
				.data("profile", "")
				.data("words", "")
				.data("text", "checked")
				.data("image", "checked")
				.data("online", "")
				.data("preferage", "")
				.data("travelers", "checked")
				.data("travelersfuture", "")
				.data("onlytravelers", "")
				.data("sort", "default")
				.method(Method.POST)
				.execute();
		
		newPage = res.parse();
		System.out.println(newPage.toString());
		String html = "<div class='content'><center>bullshit<table cellspacing='0' cellpadding='0'><tr><td>Test 1</td><td>Test 2</td></tr></table><table cellspacing='0' cellpadding='0'><tr><td>Test 3</td><td>Test 4</td></tr></table></center></div>";
		Elements tables = doc.select("table");
		Element table = tables.get(1);
	}
}
