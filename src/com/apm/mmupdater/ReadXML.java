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
			Node test = doc.getElementsByTagName("Parameter").item(4);
			NamedNodeMap attr = test.getAttributes();
			Node emailValue = attr.getNamedItem("Value");
			System.out.println("emailValue = " + emailValue);
			String emailVal = emailValue.toString();
			System.out.println(emailVal);
			
			//Gets ActionDataGroup
			Node actionData = doc.getElementsByTagName("DataGroup").item(6);
			NamedNodeMap attrAction = actionData.getAttributes();
			Node actionValue = attrAction.getNamedItem("xsi:type");
			System.out.println("ActionDataTest = " + actionValue);
					
			NodeList list = doc.getElementsByTagName("DataGroup");
//			System.out.println("There are " + list.getLength() + " DataGroups");
			
			for (int i = 0; i < list.getLength() ; i++) {
				Element item = (Element)list.item(i);
				NamedNodeMap attrAction1 = item.getAttributes();
				
				Node actionValue1 = attrAction1.getNamedItem("xsi:type");
				System.out.println("DataGroup = " + actionValue1);
			
				if (actionValue1 == actionValue ){
					System.out.println("Here is the ActionDataGroup");
				    Node actionNode = createActionNode(doc);
				    item.appendChild(actionNode);   
				   
				    Node concreteClassNode = createConcreteClassNode(doc);
				    actionNode.appendChild(concreteClassNode);
				    
				    Node emailNode = createEmailNode(doc, emailVal);
				    concreteClassNode.appendChild(emailNode);
				}			
			}				
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("/Users/yoral01/DevStuff/SSAv10MMs/Addedv10xmlTest/PUSR03_ISSNRC/ManagementModule.xml"));
			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	  public static Node createActionNode(Document document) {

		    // create FirstName and LastName elements
		    Element name = document.createElement("Name");
		    Element description = document.createElement("Description");
		    Element actionName = document.createElement("ActionName");

		    name.appendChild(document.createTextNode("v10 SMTP Mail Action"));
		    actionName.appendChild(document.createTextNode("SendSMTPMailAction"));

		    // create contact element
		    Element contact = document.createElement("Action");

		    // create attribute
		    Attr genderAttribute = document.createAttribute("IsActive");
		    genderAttribute.setValue("true");
		    
		    Attr newAttribute = document.createAttribute("DescriptionContentType");
		    newAttribute.setValue("text/plain");

		    // append attribute to contact element
		    contact.setAttributeNode(genderAttribute);
		    contact.setAttributeNode(newAttribute);
		    contact.appendChild(name);
		    contact.appendChild(description);
		    contact.appendChild(actionName);

		    return contact;
		  }
	  public static Node createConcreteClassNode(Document document) {

		    // create FirstName and LastName elements
		    Element name = document.createElement("ConcreteClassData");
//		    Element contact = document.createElement("ConcreteClassData");
//		    contact.appendChild(name);
		    return name;
		  }
	  
	  public static Node createEmailNode(Document document, String emaildata) {

		    // create FirstName and LastName elements
		  	Element smtpHost = document.createElement("SMTPHost");
		  	smtpHost.appendChild(document.createTextNode("smtp.gmail.com"));
		  	Element smtpSender = document.createElement("SMTPSender");
		  	smtpSender.appendChild(document.createTextNode("INTROSCOPE@CA.com"));
		  	Element smtpRecipient = document.createElement("SMTPRecipient");
		  	smtpRecipient.appendChild(document.createTextNode(emaildata.replaceAll("Value=\"", "").replace("\"", "")));
		  	Element sendShortText = document.createElement("SendShortText");
		  	sendShortText.appendChild(document.createTextNode("false"));
		  	Element sendPlainText = document.createElement("SendPlainText");
		  	sendPlainText.appendChild(document.createTextNode("false"));
		  	Element subjectText = document.createElement("SubjectText");
		  	subjectText.appendChild(document.createTextNode("CA APM Alert from ${EM_Host}: ${Alert_Name} in ${Alert_State} state"));
		  	Element bodyText = document.createElement("BodyText");
		  	bodyText.appendChild(document.createTextNode("Alert Name: ${Alert_Name}Time Triggered: ${Alert_Time}"));
		  	
		  	
		    Element email = document.createElement("EmailData");
//		    Element contact1 = document.createElement("ConcreteClassData");
//		    contact1.appendChild(name);
		    
		    email.appendChild(smtpHost);
		    email.appendChild(smtpSender);
		    email.appendChild(smtpRecipient);
		    email.appendChild(sendShortText);
		    email.appendChild(sendPlainText);
		    email.appendChild(subjectText);
		    email.appendChild(bodyText);
		    return email;
		  }
}

