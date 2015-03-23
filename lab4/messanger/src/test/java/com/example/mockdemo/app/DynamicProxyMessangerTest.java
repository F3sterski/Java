package com.example.mockdemo.app;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.MessageServiceSimpleImpl;
import com.example.mockdemo.messenger.SendingStatus;

public class DynamicProxyMessangerTest {
	@Test
	public void checkTestConnection() throws MalformedRecipientException {

		InvocationHandler ih = new MessangerHandler();
		MessageService MSMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myApp = new Messenger(MSMock);

		for(int i=0;i<25;i++)
		assertEquals(0, myApp.testConnection("wp.pl"));
	}
	
	@Test
	public void checkTestConnectionFail() throws MalformedRecipientException {

		InvocationHandler ih = new MessangerHandler();
		MessageService MSMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myApp = new Messenger(MSMock);

		for(int i=0;i<25;i++)
		assertEquals(1, myApp.testConnection("wp.com"));
	}
	
	@Test
	public void checkSending() throws MalformedRecipientException {

		InvocationHandler ih = new MessangerHandler();
		MessageService MSMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myApp = new Messenger(MSMock);

		for(int i=0;i<25;i++)
		assertEquals(0, myApp.sendMessage("wp.pl", "message"));
	}
	
	@Test(expected=MalformedRecipientException.class)
	public void checkSendingException() throws MalformedRecipientException {

		InvocationHandler ih = new MessangerHandler();
		MessageService MSMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myApp = new Messenger(MSMock);

		assertEquals(2, myApp.sendMessage(".pl", "e"));
	}
	
	@Test
	public void checkSendingFail() throws MalformedRecipientException {

		InvocationHandler ih = new MessangerHandler();
		MessageService MSMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myApp = new Messenger(MSMock);

		for(int i=0;i<25;i++)
		assertEquals(1, myApp.sendMessage("wp.com", "message"));
	}	
	
	
	class MessangerHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if ("send".equals(method.getName())) {
				System.out.print("send: "+ (args[0].toString()));
				if((args[0].toString()).length()<4){
					throw new MalformedRecipientException();
				}
				if(!(args[0].toString().endsWith(".pl"))){
					return SendingStatus.SENDING_ERROR;
				}
				return SendingStatus.SENT;
			}
			if ("checkConnection".equals(method.getName())) {
				if(!(args[0].toString().endsWith(".pl"))){
					return ConnectionStatus.FAILURE;
				}
				else return ConnectionStatus.SUCCESS;
			}
			if ("getRandom".equals(method.getName())) {
				return true;
			}
			return 0.0;
		}
	}
}
