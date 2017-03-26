package com.kamontat.constance;

import java.net.URL;
import java.util.*;

/**
 * protocol for website link
 *
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 - 8:54 PM
 */
public enum Protocol {
	HTTP("http://"),
	HTTPS("https://");
	
	private String string;
	
	Protocol(String s) {
		string = s;
	}
	
	/**
	 * <b>Format</b>: {@code http://} or {@code https://}
	 *
	 * @return string protocol
	 */
	public String getProtocol() {
		return string;
	}
	
	/**
	 * get protocol from link
	 *
	 * @param url
	 * 		link
	 * @return protocol
	 */
	public static Protocol getProtocol(URL url) {
		if (url == null) return null;
		return url.getProtocol().toLowerCase(Locale.ENGLISH).contains(HTTPS.toString()) ? HTTPS: HTTP;
	}
	
	/**
	 * check contains protocol with input string
	 *
	 * @param link
	 * 		link or protocol
	 * @return protocol
	 */
	public static Protocol getProtocol(String link) {
		if (link == null) return null;
		return link.toLowerCase(Locale.ENGLISH).contains(HTTPS.toString()) ? HTTPS: HTTP;
	}
	
	@Override
	public String toString() {
		return this.name().toLowerCase(Locale.ENGLISH);
	}
}

