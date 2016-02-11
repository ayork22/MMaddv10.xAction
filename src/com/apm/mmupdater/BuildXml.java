package com.apm.mmupdater;

      
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class BuildXml {
  private Document document;

  public BuildXml() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      document = builder.newDocument();
    }catch (ParserConfigurationException parserException) {
      parserException.printStackTrace();
    }

    Element root = document.createElement("test");
    document.appendChild(root);

    
    
    // add child element using method <Action>
    Node actionNode = createActionNode(document);
    root.appendChild(actionNode);
    
    Node concreteClassNode = createConcreteClassNode(document);
    actionNode.appendChild(concreteClassNode);
    
    Node emailNode = createEmailNode(document);
    concreteClassNode.appendChild(emailNode);

    // write the XML document to disk
    try {

      // create DOMSource for source XML document
      Source xmlSource = new DOMSource(document);

      // create StreamResult for transformation result
      Result result = new StreamResult(new FileOutputStream("/Users/yoral01/Desktop/myDocument.xml"));

      // create TransformerFactory
      TransformerFactory transformerFactory = TransformerFactory.newInstance();

      // create Transformer for transformation
      Transformer transformer = transformerFactory.newTransformer();

      transformer.setOutputProperty("indent", "yes");
      transformer.transform(xmlSource, result);

      // transform and deliver content to client
    }

    // handle exception creating TransformerFactory
    catch (TransformerFactoryConfigurationError factoryError) {
      System.err.println("Error creating " + "TransformerFactory");
      factoryError.printStackTrace();
    }catch (TransformerException transformerError) {
      System.err.println("Error transforming document");
      transformerError.printStackTrace();
    }    catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  public Node createActionNode(Document document) {

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
  
  public Node createConcreteClassNode(Document document) {

	    // create FirstName and LastName elements
	    Element name = document.createElement("ConcreteClassData");
//	    Element contact = document.createElement("ConcreteClassData");
//	    contact.appendChild(name);
	    return name;
	  }
  
  public Node createEmailNode(Document document) {

	    // create FirstName and LastName elements
	  	Element smtpHost = document.createElement("SMTPHost");
	  	smtpHost.appendChild(document.createTextNode("smtp.gmail.com"));
	  	Element smtpSender = document.createElement("SMTPSender");
	  	smtpSender.appendChild(document.createTextNode("INTROSCOPE@CA.com"));
	  	Element smtpRecipient = document.createElement("SMTPRecipient");
	  	smtpRecipient.appendChild(document.createTextNode("false"));
	  	Element sendShortText = document.createElement("SendShortText");
	  	sendShortText.appendChild(document.createTextNode("false"));
	  	Element sendPlainText = document.createElement("SendPlainText");
	  	sendPlainText.appendChild(document.createTextNode("agyork22@gmail.com"));
	  	Element subjectText = document.createElement("SubjectText");
	  	subjectText.appendChild(document.createTextNode("CA APM Alert from ${EM_Host}: ${Alert_Name} in ${Alert_State} state"));
	  	Element bodyText = document.createElement("BodyText");
	  	bodyText.appendChild(document.createTextNode("Alert Name: ${Alert_Name}Time Triggered: ${Alert_Time}"));
	  	
	  	
	    Element email = document.createElement("EmailData");
//	    Element contact1 = document.createElement("ConcreteClassData");
//	    contact1.appendChild(name);
	    
	    email.appendChild(smtpHost);
	    email.appendChild(smtpSender);
	    email.appendChild(smtpRecipient);
	    email.appendChild(sendShortText);
	    email.appendChild(sendPlainText);
	    email.appendChild(subjectText);
	    email.appendChild(bodyText);
	    return email;
	  }
//  EmailData

  public static void main(String args[]) {
    BuildXml buildXml = new BuildXml();
  }
}
