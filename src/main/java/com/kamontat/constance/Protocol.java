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
	HTTP,
	HTTPS;
	
	/**
	 * get protocol from link
	 *
	 * @param url
	 * 		link
	 * @return protocol
	 */
	public static Protocol getProtocol(URL url) {
		if (url == null) return null;
		return url.getProtocol().contains(HTTPS.name().toLowerCase()) ? HTTPS: HTTP;
	}
	
	@Override
	public String toString() {
		return this.name().toLowerCase(Locale.ENGLISH);
	}
}

