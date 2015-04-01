package com.example.JBehave.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.MessageServiceSimpleImpl;
import com.example.mockdemo.messenger.SendingStatus;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MessangerSteps {
	
	private MessageService mess;
	private Messenger MS;
	private String server;
	private String message;
	private boolean MalformedRecipientException;

	@Given("a messenger")
	public void MessangerServiceSetup(){
		MS = new Messenger(mess);
	}
	
	@When("set server to $a")
	public void settestConnection(String server){
		MessageService mockMS = mock(MessageService.class);
		if(server.endsWith(".pl")){
			when(mockMS.checkConnection(server)).thenReturn(ConnectionStatus.SUCCESS);			
		}else{
			when(mockMS.checkConnection(server)).thenReturn(ConnectionStatus.FAILURE);			
		}
		MS = new Messenger(mockMS);
		this.server = server;
	}
	
    @Then("testConnection should return $result")
	public void shouldtestConnection(int result){
		assertEquals(result, MS.testConnection(server));
	}
    
	@When("set parameters server and message to $a $b") 
	public void setmessage(String server, String message){
		MessageService mockMS = mock(MessageService.class);
		if(server.endsWith(".pl")){
			when(mockMS.checkConnection(server)).thenReturn(ConnectionStatus.SUCCESS);			
		}else{
			when(mockMS.checkConnection(server)).thenReturn(ConnectionStatus.FAILURE);			
		}		
		try{
		if (message == null || message.length() < 3){
			when(mockMS.send(server, message)).thenThrow(new MalformedRecipientException());		
		}
		if (server == null || server.length() < 4){
			when(mockMS.send(server, message)).thenThrow(new MalformedRecipientException());			
		}
		if (mockMS.checkConnection(server) == ConnectionStatus.FAILURE) {
			when(mockMS.send(server,message)).thenReturn(SendingStatus.SENDING_ERROR);
		}else{
			when(mockMS.send(server,message)).thenReturn(SendingStatus.SENT);	
		}
		}catch(MalformedRecipientException e){
			MalformedRecipientException = true;
			MS = new Messenger(mockMS);
			this.message = message;
			this.server = server;			
		}
		MS = new Messenger(mockMS);
		this.message = message;
		this.server = server;
	}
	
    @Then("send should return $result")
	public void shouldSend(int result) throws MalformedRecipientException{
    		assertEquals(result, MS.sendMessage(server,message));
	}  
	
    @Then("send should return exception $result")
	public void shouldExceptionSend(int result) throws MalformedRecipientException{
    		assertEquals(result, MS.sendMessage(server,message));
	}  
}
