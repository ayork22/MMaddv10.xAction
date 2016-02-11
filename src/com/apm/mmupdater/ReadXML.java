package com.apm.mmupdater;

import java.io.File;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.DocumentCSS;
import org.xml.sax.SAXException;

//import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

public class ReadXML {

	public static void main(String[] args) throws TransformerConfigurationException {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("/Users/yoral01/Desktop/ManagementModule.xml");
			
// *** Get Email Distro List
//			Node test = doc.getElementsByTagName("Parameter").item(4);
//			NamedNodeMap attr = test.getAttributes();
//			Node emailValue = attr.getNamedItem("Value");
//			System.out.println("emailValue = " + emailValue);
			
			//Gets ActionDataGroup
			Node actionData = doc.getElementsByTagName("DataGroup").item(6);
			NamedNodeMap attrAction = actionData.getAttributes();
			Node actionValue = attrAction.getNamedItem("xsi:type");
//			System.out.println("ActionData = " + actionValue);
					
			NodeList list = doc.getElementsByTagName("DataGroup");
//			System.out.println("There are " + list.getLength() + " DataGroups");
			
			for (int i = 0; i < list.getLength() ; i++) {
				Element item = (Element)list.item(i);
				NamedNodeMap attrAction1 = item.getAttributes();
				Node actionValue1 = attrAction1.getNamedItem("xsi:type");
//				System.out.println("ActionData = " + actionValue1);
			
				if (actionValue1 == actionValue ){
					System.out.println("Here is the ActionDataGroup");	
				}			
			}		
			// write the content into xml file
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			DOMSource source = new DOMSource(doc);
//			StreamResult result = new StreamResult(new File("/Users/yoral01/Desktop/test.xml"));
//			transformer.transform(source, result);
			System.out.println("Done");
		} catch (ParserConfigurationException e) {
			//System.out.println("This Broke");
			e.printStackTrace();
		} catch (SAXException e) {
			//System.out.println("This Broke");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("This Broke");
			e.printStackTrace();
		}
	}
}

