package com.sample.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Assert;
import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.core.io.ClassPathResource;

import com.sample.mule.DoubleIt;
import com.sample.mule.DoubleItResponse;
import com.sample.mule.ItemsToProcess;
import com.sample.mule.ItemsToResponse;

public class MuleFlowTest extends FunctionalTestCase {

    private static final String MULE_FLOW_URL = "/mulesvc/sample/soapws/DoubleIt.svc";
    private static final String MULE_ENDPOINT = "http://localhost:8090";

    @Override
    protected String[] getConfigFiles() {
        String[] configFile = new String[] {"soap-webservice-mule.xml"}; //It should be relative path to your flow xml file
        return configFile;
    }

    @Test
    public void testCorrectResponse() throws Exception {
    	String requestXml = readFile("sample/doubltItRequest.xml");
        String doubleItResponse = getResponseAfterMuleFlowExecution(requestXml);
        if (doubleItResponse != null) {
        	SOAPMessage requestMessage = MessageFactory.newInstance().createMessage(null, new ClassPathResource("sample/doubltItRequest.xml").getInputStream());
        	InputStream is = new ByteArrayInputStream(doubleItResponse.getBytes());
        	SOAPMessage responseMessage = MessageFactory.newInstance().createMessage(null, is);
        	
            JAXBContext jaxbContext = JAXBContext.newInstance(DoubleIt.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            DoubleIt request = (DoubleIt) jaxbUnmarshaller.unmarshal(requestMessage.getSOAPBody().extractContentAsDocument());
            
            jaxbContext = JAXBContext.newInstance(DoubleItResponse.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            DoubleItResponse response = (DoubleItResponse) jaxbUnmarshaller.unmarshal(responseMessage.getSOAPBody().extractContentAsDocument());
        	Assert.assertEquals(response.getResponseItem().getNumberToDouble(), request.getRequestItem().getNumberToDouble()*2);
            
        }
        Assert.assertNotNull(doubleItResponse);
    }


    private String readFile(String resourcePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(resourcePath).getFile()))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }
        } catch (IOException e) {
            Assert.fail();
        }
        return sb.toString();
    }

    protected String getResponseAfterMuleFlowExecution(String xmlRequest) throws Exception {
        String url = MULE_ENDPOINT + MULE_FLOW_URL;
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        PostMethod postMethod = new PostMethod(url);
        postMethod.addRequestHeader(org.apache.http.HttpHeaders.ACCEPT, "application/soap+xml");
        postMethod.setRequestEntity(new StringRequestEntity(xmlRequest, "application/soap+xml", StandardCharsets.UTF_8.name()));
        httpClient.executeMethod(postMethod);

        return postMethod.getResponseBodyAsString();
    }
}
