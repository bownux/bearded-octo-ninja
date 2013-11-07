package fi.iki.elonen;

import org.junit.Test;


public class HelloServerTest extends ThroughTheBrowserBaseFixture {
	@Test
	public void canEnterBob() throws Exception {
		httpServerThread = launchHelloServer();
		selenium.open("http://localhost:8080/");
		assertTextPresent("Hello server");
		
		selenium.type("username", "bob");
		selenium.click("submit");
		selenium.waitForPageToLoad("1000");
		assertTextPresent("Hello, bob!");
	}

	private Thread launchHelloServer() {
		Runnable runnable = new Runnable() {
	
			public void run() {
				HelloServer.start();
			}
		};
		return launchServerThread(runnable);
	}
	
	
}
