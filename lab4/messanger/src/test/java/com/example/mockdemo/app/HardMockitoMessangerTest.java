package com.example.mockdemo.app;
import com.example.mockdemo.messenger.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


@RunWith( MockitoJUnitRunner.class )
public class HardMockitoMessangerTest {

	@Spy private MessageServiceSimpleImpl mockMS;
	
	@Test
	public void ConnectionSatusFail(){
		when(mockMS.checkConnection(anyString())).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(1,new Messenger(mockMS).testConnection("wp.pl"));
	}
	@Test
	public void ConnectionSatusSuccess(){
		
		when(mockMS.checkConnection(anyString())).thenAnswer(new Answer() {
		     public ConnectionStatus answer(InvocationOnMock invocation) {
		         Object[] args = invocation.getArguments();
		         Object mock = invocation.getMock();
		         if(args[0].toString().endsWith(".pl")){
		        	 return ConnectionStatus.SUCCESS;
		         }
		         else{
		        	 return ConnectionStatus.FAILURE;
		         }
		         
		     }
		 });
		
		assertEquals(0,new Messenger(mockMS).testConnection("wp.pl"));
	}	

}
