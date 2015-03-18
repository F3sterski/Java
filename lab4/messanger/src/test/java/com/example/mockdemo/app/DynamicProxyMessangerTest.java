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

		for(int i=0;i<25;i++)
		assertEquals(0, myApp.sendMessage(".pl", "e"));
	}
	
	class MessangerHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if ("send".equals(method.getName())) {
				if(((String)args[1]).length()<4){
					throw new MalformedRecipientException();
				}
				return SendingStatus.SENT;
			}
			System.out.print("tutajjestem");
			if ("checkConnection".equals(method.getName())) {
				return ConnectionStatus.SUCCESS;
			}
			System.out.print("tutaj");
			if ("getRandom".equals(method.getName())) {
				return true;
			}
			return 0.0;
		}
	}
}
