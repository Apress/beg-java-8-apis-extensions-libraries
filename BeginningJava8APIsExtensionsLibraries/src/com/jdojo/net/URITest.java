// URITest.java
package com.jdojo.net;

import java.net.URI;
import java.net.URISyntaxException;

public class URITest {
	public static void main(String[] args) {
		String baseURIStr = "http://www.jdojo.com/javaintro.html?" + 
		                    "id=25&rate=5.5%25#foo";
		String relativeURIStr = "../sports/welcome.html";

		try {
			URI baseURI = new URI(baseURIStr);
			URI relativeURI = new URI(relativeURIStr);

			// Resolve the relative URI with respect to the base URI 
			URI resolvedURI = baseURI.resolve(relativeURI);

			printURIDetails(baseURI);
			printURIDetails(relativeURI);
			printURIDetails(resolvedURI);
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void printURIDetails(URI uri) {
		System.out.println("URI:" + uri);
		System.out.println("Normalized:" + uri.normalize());
		String parts = "[Scheme=" + uri.getScheme() + 
		               ", Authority=" + uri.getAuthority() + 
		               ", Path=" + uri.getPath() + 
		               ", Query:" + uri.getQuery() + 
		               ", Fragment:" + uri.getFragment() + "]";

		System.out.println(parts);
		System.out.println();
	}
}
