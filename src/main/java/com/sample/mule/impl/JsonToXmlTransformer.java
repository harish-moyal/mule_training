package com.sample.mule.impl;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sample.mule.ItemsToResponse;

public class JsonToXmlTransformer extends AbstractMessageTransformer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonToXmlTransformer.class.getName());
	
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)	throws TransformerException {
		// Incoming request xml object
		try {
			JsonMessage request = getObjectMapper().readValue(message.getPayloadAsString(), JsonMessage.class);
			ItemsToResponse response = new ItemsToResponse();
			response.setNumberToDouble(Integer.valueOf(request.getNumberToDouble())*2);
			response.setNumberToHalf(Integer.valueOf(request.getNumberToHalf())/2);
			message.setPayload(response);
		} 
		 catch (Exception e) {
			LOGGER.error("Error transforming JSON to XML");
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
