package com.example.mockdemo.app;
import com.example.mockdemo.messenger.*;

import static org.junit.Assert.assertEquals;

import org.easymock.*;
import org.junit.Test;

public class EasyMockMessangerTest extends EasyMockSupport{

	private Messenger messenger;

	@Test
	public void ConnectionTest(){
		MessageService mockMS = EasyMock.createMock(MessageService.class);
		EasyMock.expect(mockMS.checkConnection("wp.pl")).andReturn(ConnectionStatus.SUCCESS);
		EasyMock.replay(mockMS);
		messenger = new Messenger(mockMS);
		assertEquals(0, messenger.testConnection("wp.pl"));
		
	}

	@Test
	public void ConnectionTestFail(){
		MessageService mockMS = EasyMock.createMock(MessageService.class);
		EasyMock.expect(mockMS.checkConnection("wp.com")).andReturn(ConnectionStatus.FAILURE);
		EasyMock.replay(mockMS);
		messenger = new Messenger(mockMS);
		assertEquals(1, messenger.testConnection("wp.com"));
	}
	
	@Test
	public void checkSending() throws MalformedRecipientException{
		MessageService mockMS = EasyMock.createMock(MessageService.class);
		EasyMock.expect(mockMS.send("wp.pl", "message")).andReturn(SendingStatus.SENT);
		EasyMock.replay(mockMS);
		messenger = new Messenger(mockMS);
		assertEquals(0, messenger.sendMessage("wp.pl","message"));
	}
	
	@Test
	public void checkSendingFail() throws MalformedRecipientException{
		MessageService mockMS = EasyMock.createMock(MessageService.class);
		EasyMock.expect(mockMS.send("wp.com", "message")).andReturn(SendingStatus.SENDING_ERROR);
		EasyMock.replay(mockMS);
		messenger = new Messenger(mockMS);
		assertEquals(1, messenger.sendMessage("wp.com","message"));
	}
	
	@Test(expected=MalformedRecipientException.class)
	public void checkSendingException() throws MalformedRecipientException{
		MessageService mockMS = EasyMock.createMock(MessageService.class);
		EasyMock.expect(mockMS.send("om", "message")).andThrow(new MalformedRecipientException());
		EasyMock.replay(mockMS);
		messenger = new Messenger(mockMS);
		assertEquals(2, messenger.sendMessage("om","message"));
	}
    
}
