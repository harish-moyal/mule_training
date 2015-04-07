package com.sample.mule.impl;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sample.mule.ItemsToProcess;

public class XmlToJsonTransformer extends AbstractMessageTransformer {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlToJsonTransformer.class.getName());
	
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)	throws TransformerException {
		// Incoming request xml object
		ItemsToProcess request = (ItemsToProcess) message.getPayload();
		
		JsonMessage transformed = new JsonMessage();
		
		transformed.setNumberToDouble(String.valueOf(request.getNumberToDouble()));
		transformed.setNumberToHalf(String.valueOf(request.getNumberToHalf()));
		try {
			// Parse the java object to a json string
			String jsonString = getObjectMapper().writeValueAsString(transformed);
			// Set the json string for next component/outbound-endpoint/sub-flow
			message.setPayload(jsonString);
		} catch (JsonProcessingException e) {
			LOGGER.error("Cannot convert into json");
		}
		return message;
	}
	
	private static ObjectMapper getObjectMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        result.configure(SerializationFeature.INDENT_OUTPUT, true);
        return result;
    }

}
