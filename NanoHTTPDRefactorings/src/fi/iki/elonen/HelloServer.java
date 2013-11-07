package fi.iki.elonen;

import java.io.IOException;

/**
 * An example of subclassing NanoHTTPD to make a custom HTTP server.
 */
public class HelloServer extends NanoHTTPD {
	public HelloServer() throws IOException {
		super(8080);
	}

	public Response serve(RequestComponents unit) {
		System.out.println(unit.getHttpMethod() + " '" + unit.getUri() + "' ");
		String msg = "<html><body><h1>Hello server</h1>\n";
		if (unit.getParms().getProperty("username") == null)
			msg += "<form action='?' method='get'>\n"
					+ "  <p>Your name: <input type='text' name='username'></p>\n"
					+ "  <p><input id='submit' type='submit' value='Submit'></p>\n"
					+ "</form>\n";
		else
			msg += "<p>Hello, " + unit.getParms().getProperty("username") + "!</p>";

		msg += "</body></html>\n";
		return new Response(HTTP_OK, MIME_HTML, msg);
	}

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		try {
			new HelloServer();
		} catch (IOException ioe) {
			System.err.println("Couldn't start server:\n" + ioe);
			System.exit(-1);
		}
		System.out.println("Listening on port 8080. Hit Enter to stop.\n");
		try {
			System.in.read();
		} catch (Throwable t) {
		}
		;
	}
}
