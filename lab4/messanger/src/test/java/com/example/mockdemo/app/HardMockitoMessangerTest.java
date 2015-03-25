package com.example.mockdemo.app;
import com.example.mockdemo.messenger.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


@RunWith( MockitoJUnitRunner.class )
public class HardMockitoMessangerTest {
	
	final String VALID_MESSAGE = "message";
	final String VALID_ADRESS = "wp.pl";
	final String INVALID_MESSAGE = "A";
	final String INVALID_ADRESS = "wp.com";	

	@Spy private MessageServiceSimpleImpl mockMS;
	
	@Test
	public void ConnectionSatusFail(){
		when(mockMS.checkConnection(anyString())).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(1,new Messenger(mockMS).testConnection(VALID_ADRESS));
	}
	@Test
	public void ConnectionSatusSuccess(){
		
		when(mockMS.checkConnection(anyString())).thenAnswer(new Answer() {
		     public ConnectionStatus answer(InvocationOnMock invocation) {
		         Object[] args = invocation.getArguments();
		         Object mock = invocation.getMock();
		         if(args[0].toString().endsWith(VALID_ADRESS)){
		        	 return ConnectionStatus.SUCCESS;
		         }
		         else{
		        	 return ConnectionStatus.FAILURE;
		         }
		         
		     }
		 });
		
		assertEquals(0,new Messenger(mockMS).testConnection(VALID_ADRESS));
	}
	
	@Test
	public void sendTestSuccess() throws MalformedRecipientException{
		ArgumentCaptor<String> server = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> message = ArgumentCaptor.forClass(String.class);
		
		when(mockMS.send(VALID_ADRESS, VALID_MESSAGE)).thenReturn(SendingStatus.SENT);
	    verify(mockMS).send(server.capture(), message.capture());	
		   
		   assertEquals(VALID_ADRESS, server.getValue());
		   assertEquals(VALID_MESSAGE, message.getValue());

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
