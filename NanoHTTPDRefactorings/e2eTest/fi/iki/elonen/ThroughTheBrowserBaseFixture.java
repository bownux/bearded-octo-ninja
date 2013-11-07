package fi.iki.elonen;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;

public class ThroughTheBrowserBaseFixture {
	protected DefaultSelenium selenium;
	private SeleniumServer jettyProxy;
	protected Thread httpServerThread;

	@Before
	public void init() throws Exception {
	    int proxyPort = 4444;
		launchJettyProxy(proxyPort);
		launchSelenium(proxyPort);
	}

	private void launchSelenium(int proxyPort) {
		selenium = new DefaultSelenium("localhost", proxyPort, "*safari","http://www.google.com/");
		selenium.start();
	}

	private void launchJettyProxy(int proxyPort) throws Exception {
		RemoteControlConfiguration config = new RemoteControlConfiguration();
		config.setPort(proxyPort);
	    jettyProxy = new SeleniumServer(config);
	    jettyProxy.start();
	}

	@After
	public void close() throws Exception {
		selenium.close();
		selenium.stop();
		httpServerThread.interrupt();
	}

	protected void assertTextPresent(String string) {
		assertTrue(selenium.isTextPresent(string));
	}

	protected Thread launchServerThread(Runnable runnable) {
		httpServerThread = new Thread(runnable);
		httpServerThread.start();
		return httpServerThread;
	}

}
