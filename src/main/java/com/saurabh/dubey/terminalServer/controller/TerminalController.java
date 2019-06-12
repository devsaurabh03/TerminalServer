package com.saurabh.dubey.terminalServer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.saurabh.dubey.terminalServer.client.PoolClient;
import com.saurabh.dubey.terminalServer.model.MessagePayload;
import com.saurabh.dubey.terminalServer.model.TerminalID;

@RestController
public class TerminalController {

	@Autowired
	PoolClient client;
	
	@RequestMapping(value = "/getTerminalFromServer" ,method = RequestMethod.GET)
	public MessagePayload getTerminalFromServer() throws JsonParseException, JsonMappingException, IOException{
		return client.getTerminal();

	}
	
	@RequestMapping(value = "/releaseTerminalFromServer" ,method = RequestMethod.POST)
	public void releaseTerminal(@RequestBody TerminalID termId) {
		client.releaseTerminal(termId);
	}
}
