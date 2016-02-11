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

	public static void main(String[] args) {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("/Users/yoral01/Desktop/ManagementModule.xml");

			
			

			
// ***Get ACTION
			Node action = doc.getElementsByTagName("Action").item(1);
			NamedNodeMap actionAttributes = action.getAttributes();
			Node actionValues = actionAttributes.getNamedItem("DescriptionContentType");

			
			Attr galaxy = doc.createAttribute("galaxy");
			actionAttributes.setNamedItem(galaxy);
		
// ****   Add new Attribute			
			galaxy.setValue("milky way");
			actionAttributes.setNamedItem(galaxy);
			
			//Node action = attr.getNamedItem("Action");
//			System.out.println("Action value = " + actionValues);
			
			
			Element dataTag = doc.getDocumentElement();
			Element ActionTag =  (Element) dataTag.getElementsByTagName("Action").item(0);
			Element newPerson = doc.createElement("person");
			System.out.println("newPerson = " + newPerson);
			
			Element firstName = doc.createElement("firstName");
			firstName.setTextContent("Tom");
			
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("/Users/yoral01/Desktop/Person.xml"));
			transformer.transform(source, result);

			System.out.println("Done");
			
			
// *** Get Email Distro List
			Node test = doc.getElementsByTagName("Parameter").item(4);
			NamedNodeMap attr = test.getAttributes();
			Node emailValue = attr.getNamedItem("Value");
			System.out.println("emailValue = " + emailValue);
			if (emailValue.equals("Introscope.iSSNRC.Alerts@ssa.gov")){
				emailValue.removeChild(actionValues);
			}
			System.out.println("emailValue = " + emailValue);
			System.out.println("Action value = " + actionValues);
			
		//***Append v10 Action Stuff
			Element actionAdd = doc.createElement("actionTest");
			actionAdd.appendChild(doc.createTextNode("v10EMAILaction"));
			
			
			NodeList list = doc.getElementsByTagName("mail.smtp.recipient");
			System.out.println("There are " + list.getLength() + " items");
			
			for (int i = 0; i < list.getLength() ; i++) {
				Element item = (Element)list.item(i);
				System.out.println(item.getFirstChild().getNodeValue());
				System.out.println(item.getTagName());
				System.out.println(item.getLastChild());
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
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

