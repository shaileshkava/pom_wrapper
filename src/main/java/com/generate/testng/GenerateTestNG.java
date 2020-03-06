package com.generate.testng;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//import Scripts.VerifyMessage;

public class GenerateTestNG {
	//static Logger log = Logger.getLogger(GenerateTestNG.class);
	
	public static String xmlFilePath;
	
	GenerateTestNG(String fileName){
		xmlFilePath = fileName;
	}
	
	DocumentBuilderFactory documentFactory;
	DocumentBuilder documentBuilder;
	Document document;
	Element root;
	
	public void generateFile(String className, String  browser, String parallel, 
			String threadCount, String testCaseDesc, String testData, String fileName, int testCaseNumber){
			System.out.println("Browser "+browser);
		try {
			
			//System.out.println("threadCount "+threadCount+" parallel "+parallel); 
			if(testCaseNumber==0){
	            documentFactory = DocumentBuilderFactory.newInstance();
	 
	            documentBuilder = documentFactory.newDocumentBuilder();
	 
	            document = documentBuilder.newDocument();
	            
	            //root element
	            root = document.createElement("suite");
	            String suiteName;
	            if(fileName.toLowerCase().contains("regression")){
	            	suiteName="regression-suite";
	            }else{
	            	suiteName="smoke-suite";
	            }
	            root.setAttribute("name", suiteName);
	            if(parallel.length()>=1){
	            	root.setAttribute("parallel", parallel);
		            root.setAttribute("thread-count", threadCount);
	            }
	            document.appendChild(root);
			}
            
            Element test = document.createElement("test");
            test.setAttribute("name", testCaseDesc);
        	root.appendChild(test);
        	
        	Element param = document.createElement("parameter");
        	//param.appendChild(document.createTextNode(""));
        	param.setAttribute("name", "browser");
        	param.setAttribute("value", browser);
        	
        	test.appendChild(param);
            
        	Element testName = document.createElement("parameter");
        	
        	testName.setAttribute("name", "TestCaseDescription");
        	testName.setAttribute("value", testCaseDesc);
        	
        	test.appendChild(testName);
        	
            if(testData.length()>1){
            	Element param1 = document.createElement("parameter");
	        	//param.appendChild(document.createTextNode(""));
	        	param1.setAttribute("name", "data");
	        	param1.setAttribute("value", testData);
	        	test.appendChild(param1);
	        }else{
	        	//log.error("Data is not set and it is blank");
	        	System.out.println("Data is not set and it is blank");
	        }
            
            Element classes = document.createElement("classes");
            test.appendChild(classes);
            
            Element department = document.createElement("class");
        	///department.appendChild(document.createTextNode(className));
        	department.setAttribute("name", className);
        	classes.appendChild(department);
            
        	// create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
	}

}