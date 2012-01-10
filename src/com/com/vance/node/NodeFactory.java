package com.vance.node;

import javax.xml.parsers.*;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

import com.vance.domain.DomainObject;
import com.vance.persistence.DBManager;
import com.vance.persistence.SummaryTask;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

public class NodeFactory {
	private static ArrayList domainObjectKey=new ArrayList();
	private static HashMap<String, String> nodeMppaing = new HashMap<String, String>();
	public static void main(String arg[]) throws Exception {
		
		//Initialize the domain object reflection class;
		for(StartUP s:StartUP.values()){
			domainObjectKey.add(s.getDomainClassName());
		}
		
		String xmlRecords = "<data><employee><name>A</name>"
				+ "<title>Manager</title></employee></data>";

		String path = "/home/vance/testFile.log";

		File file = new File(path);

		BufferedReader bufferReader = new BufferedReader(new FileReader(file));

		StringBuilder str = new StringBuilder("");
		String lineStr = "";
		while ((lineStr = bufferReader.readLine()) != null) {

			str.append(lineStr.trim());
		}

		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(str.toString()));

		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("person");

		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);

			NodeList name = element.getElementsByTagName("first-name");
			Element line = (Element) name.item(0);
			// System.out.println("first-name: " +
			// getCharacterDataFromElement(line));

			NodeList title = element.getElementsByTagName("title");
			line = (Element) title.item(0);
			// System.out.println("Title: " +
			// getCharacterDataFromElement(line));

			NodeList positions = element.getElementsByTagName("position");
			line = (Element) positions.item(0);
			// System.out.println(summary.getLength());
			processPositions(positions);
			// System.out.println("Title: " +
			// getCharacterDataFromElement(line));

		}
		
		

	}

	public static void processPositions(NodeList positions) {
//		HashMap<String, String> nodeMppaing = new HashMap<String, String>();
		for (int i = 0; i < positions.getLength(); i++) {
			final Node node = positions.item(i);
			NodeList childNode = node.getChildNodes();
			if (node.hasChildNodes()) {
				processPositions(childNode);
			} else {
//				System.out.println(node.getParentNode().getNodeName());
//				System.out.println(node.getNodeValue());
//				nodeMppaing.put(node.getParentNode().getNodeName(),
//						node.getNodeName());
//				
				
					if(node.getParentNode().getNodeName().toLowerCase()==domainObjectKey.get(i)){
//						Class class=Class.forName();
					}
				
				
			}
		}
		
		

	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
}
