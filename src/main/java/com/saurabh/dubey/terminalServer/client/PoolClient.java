package com.saurabh.dubey.terminalServer.client;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saurabh.dubey.terminalServer.model.MessagePayload;
import com.saurabh.dubey.terminalServer.model.TerminalID;

@Component
public class PoolClient {

	@Autowired
	ObjectMapper objectMapper ;
	
	@Autowired
	RestTemplate restTemplate;
	
	public MessagePayload getTerminal() throws JsonParseException, JsonMappingException, IOException {
		
		String response =  restTemplate.getForObject("http://localhost:8080/getTerminalFromPool", String.class);
		MessagePayload messagePayload = objectMapper.readValue(response, MessagePayload.class);
		messagePayload.setTimestamp(System.currentTimeMillis());
		return messagePayload;
	
	}
	
	public void releaseTerminal(TerminalID termiD) {
		 restTemplate.postForObject("http://localhost:8080/releaseTerminalFromPool", termiD,String.class);
	}
	
	@Bean
	public RestTemplate rest(RestTemplateBuilder restTemplateBuilder) {
		 return restTemplateBuilder
	            .setConnectTimeout(30000)
	            .setReadTimeout(30000)
	            .build();
	}
	
	@Bean
	public ObjectMapper mapper(){
		return new ObjectMapper();
	}
}
