package fi.iki.elonen.server.httpsession;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.easymock.classextension.EasyMock;
import org.junit.After;

import fi.iki.elonen.server.IServer;

public class HTTPSessionBase {
	protected HTTPSession ourSession;
	protected Socket mockSocket;
	protected ParsedRequest parsedRequest;
	protected RequestParser parser;
	protected ResponseSender responseSender;
	protected ByteArrayOutputStream responseToWriteResultTo;
	protected IServer mockServer;
	
	protected Socket createSocket(String inputString) throws IOException {
		responseToWriteResultTo = new ByteArrayOutputStream();
		ByteArrayInputStream firstRequestBytes = new ByteArrayInputStream(inputString.getBytes());
		
		mockSocket = EasyMock.createMock(Socket.class);
		EasyMock.expect(mockSocket.getInputStream()).andReturn(firstRequestBytes);
		EasyMock.expect(mockSocket.getOutputStream()).andReturn(responseToWriteResultTo);
		EasyMock.expect(mockSocket.getOutputStream()).andReturn(responseToWriteResultTo);
		EasyMock.expect(mockSocket.getOutputStream()).andReturn(responseToWriteResultTo);
		mockSocket.close();
		mockSocket.close();
		EasyMock.expectLastCall();
		EasyMock.replay(mockSocket);
		
		return mockSocket;
	}	

	protected HTTPSession createThreadlessHTTPSession() {
		return new HTTPSession(mockSocket);
	}
	
	@After
	public void tearDown() {
		ourSession = null;
		mockSocket = null;
		mockServer = null;
	}
	
}
