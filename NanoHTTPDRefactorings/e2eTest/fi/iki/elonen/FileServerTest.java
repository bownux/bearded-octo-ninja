package fi.iki.elonen;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileServerTest extends ThroughTheBrowserBaseFixture {
	private static String port;

	@Test
	public void directoryListingTest() throws Exception {
		port = "8089";
		httpServerThread = launchDefaultNanoHTTPD();
		selenium.open("http://localhost:" +
				port +
				"/");

		String directoryListing = "Directory /" + "\n" + 
				".classpath  (744 bytes)" + "\n" +
				".project  (380 bytes)" + "\n" +
				".svn/" + "\n" +
				"bin/" + "\n" +
				"e2eTest/" + "\n" +
				"lib/" + "\n" +
				"src/" + "\n" +
				"test/";
		assertEquals(directoryListing, selenium.getBodyText());
	}

	private Thread launchDefaultNanoHTTPD() {
		Runnable runnable = new Runnable() {
			public void run() {
				FileServer.start(new String[]{port});
			}
		};
		return launchServerThread(runnable);
	}

}
