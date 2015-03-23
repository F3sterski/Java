package com.example.mockdemo.app;
import com.example.mockdemo.messenger.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;



public class MockitoMessangerTest {

	private Messenger messenger;

	@Test
	public void ConnectionTest(){
		MessageService mockMS = mock(MessageService.class);
		when(mockMS.checkConnection("wp.pl")).thenReturn(ConnectionStatus.SUCCESS);
		messenger = new Messenger(mockMS);
		assertEquals(0, messenger.testConnection("wp.pl"));
		
	}
	
	@Test
	public void ConnectionTestFail(){
		MessageService mockMS = mock(MessageService.class);
		when(mockMS.checkConnection("wp.com")).thenReturn(ConnectionStatus.FAILURE);
		messenger = new Messenger(mockMS);
		assertEquals(1, messenger.testConnection("wp.com"));
	}
	
	@Test
	public void checkSending() throws MalformedRecipientException{
		MessageService mockMS = mock(MessageService.class);
		when(mockMS.send("wp.pl", "message")).thenReturn(SendingStatus.SENT);
		messenger = new Messenger(mockMS);
		assertEquals(0, messenger.sendMessage("wp.pl","message"));
	}
	
	@Test
	public void checkSendingFail() throws MalformedRecipientException{
		MessageService mockMS = mock(MessageService.class);
		when(mockMS.send("wp.com", "message")).thenReturn(SendingStatus.SENDING_ERROR);
		messenger = new Messenger(mockMS);
		assertEquals(1, messenger.sendMessage("wp.com","message"));
	}
	
	@Test(expected=MalformedRecipientException.class)
	public void checkSendingException() throws MalformedRecipientException{
		MessageService mockMS = mock(MessageService.class);
		when(mockMS.send("om", "message")).thenThrow(new MalformedRecipientException());
		messenger = new Messenger(mockMS);
		assertEquals(2, messenger.sendMessage("om","message"));
	}
}
