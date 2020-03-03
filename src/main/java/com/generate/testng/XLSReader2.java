package com.generate.testng;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XLSReader2 {

    private final Fillo fillo;
    private final String filePath;
    
    private Connection connection;
    
    private String parallel;
    private String threadCt;
    
    public XLSReader2(String filePath) {
        fillo = new Fillo();
        this.filePath = filePath;
    }
    
    public void getTests(String query) {
        try {
            connection = fillo.getConnection(this.filePath);
            Recordset recordset = connection.executeQuery(query);
            while(recordset.next()){
            	parallel = recordset.getField("Parallel");
            	threadCt = recordset.getField("thread-count");
                
                if(parallel.length()<=0){
                	parallel="";
                }
                if(threadCt.length()<=0){
                	threadCt="0";
                }
                
            }
        } catch (FilloException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public void getTests(String query, String fileName) {
        try {
            connection = fillo.getConnection(this.filePath);
            Recordset recordset = connection.executeQuery(query);
            this.createSuite(recordset, fileName);
        } catch (FilloException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private void createSuite(Recordset recordset, String fileName) {
        XmlMapper xmlMapper = new XmlMapper();
        GenerateTestNG suite = new GenerateTestNG(fileName);
        try {
        	int i=0;
            while (recordset.next()) {

                String testName = recordset.getField("TestCaseDescription");
                String className = recordset.getField("ClassName");
                String paramValue = recordset.getField("Browser");
                System.out.println("Parameter Browser "+paramValue);
                /*String parallelVal = recordset.getField("Parallel");
                String threadCount = recordset.getField("thread-count");*/
                String testDescription = recordset.getField("TestCaseDescription");
                String testData = recordset.getField("Data");
                
                if(testData.length()<=0){
                	testData="";
                }
                
                suite.generateFile(className, paramValue, parallel, threadCt, testDescription ,testData ,fileName,i);
                
                i++;
            }
            //xmlMapper.writeValue(new File("xmlfile.xml"), suite);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            recordset.close();
        }
    }

}