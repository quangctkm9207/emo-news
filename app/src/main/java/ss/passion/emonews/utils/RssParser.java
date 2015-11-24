package ss.passion.emonews.utils;

import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import ss.passion.emonews.model.RssItem;

public class RssParser {

	public List<RssItem> getNewsList(String link) {
		try {
			URL url = new URL(link);
			InputSource source = new InputSource(url.openStream());
			source.setEncoding("utf-8");

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			RssHandler handler = new RssHandler();
			reader.setContentHandler(handler);

			reader.parse(source);
			return handler.getItems();
		} catch (Exception e) {
			System.out.print("Error: " + e.getMessage());
			return null;
		}

	}

}
