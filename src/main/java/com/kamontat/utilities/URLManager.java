package com.kamontat.utilities;

import com.kamontat.constance.Protocol;
import com.kamontat.constance.RequestMethod;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * URL Utility
 *
 * @author kamontat
 * @version 1.0
 * @since Wed 08/Mar/2017 - 10:30 PM
 */
public class URLManager {
	/**
	 * http connection.
	 */
	public static final Class<HttpURLConnection> HTTP_CONNECTION = HttpURLConnection.class;
	/**
	 * https connection.
	 */
	public static final Class<HttpsURLConnection> HTTPS_CONNECTION = HttpsURLConnection.class;
	/**
	 * default connection.
	 */
	public static final Class<URLConnection> CONNECTION = URLConnection.class;
	
	/**
	 * web protocol
	 */
	private Protocol protocol;
	/**
	 * web url/link
	 */
	private URL url;
	
	
	/**
	 * get url utility
	 *
	 * @param url
	 * 		the url link
	 * @return url utility
	 * @see URLManager
	 */
	public static URLManager getUrl(URL url) {
		return new URLManager(url);
	}
	
	/**
	 * get url utility
	 *
	 * @param link
	 * 		the link
	 * @return url utility
	 * @see URLManager
	 */
	public static URLManager getUrl(String link) {
		Protocol p = Protocol.getProtocol(link);
		if (p == null) return getUrl(Protocol.HTTP, link);
		
		return getUrl(p, link.replace(p.getProtocol(), "").replace(p.toString(), ""));
	}
	
	/**
	 * get url utility
	 *
	 * @param protocol
	 * 		web protocol
	 * @param link
	 * 		web link (without <b>http://</b> or <b>https://</b>)
	 * @return url utility
	 * @see URLManager
	 */
	public static URLManager getUrl(Protocol protocol, String link) {
		if (protocol == null) protocol = Protocol.HTTP;
		return new URLManager(protocol, link);
	}
	
	/**
	 * Constructor Private
	 *
	 * @param protocol
	 * 		web protocol
	 * @param link
	 * 		web link
	 */
	private URLManager(Protocol protocol, String link) {
		this.protocol = protocol;
		// add protocol
		link = protocol.getProtocol() + link;
		
		try {
			url = new URL(link);
		} catch (MalformedURLException ignore) {
			if (protocol == Protocol.HTTP) protocol = Protocol.HTTPS;
			else protocol = Protocol.HTTP;
			new URLManager(protocol, link);
		}
	}
	
	/**
	 * Constructor Private
	 *
	 * @param url
	 * 		web url
	 */
	private URLManager(URL url) {
		this.protocol = Protocol.getProtocol(url);
		this.url = url;
	}
	
	public URL getUrl() {
		return url;
	}
	
	public Protocol getProtocol() {
		return protocol;
	}
	
	/**
	 * get connection
	 *
	 * @param tClass
	 * 		output class
	 * @param <T>
	 * 		the class to cast (beware if it cause {@link ClassCastException} this method will return null)
	 * @return url connection
	 * @see URL#openConnection() or {@code null}
	 */
	public <T extends URLConnection> T getConnection(Class<T> tClass) {
		if (checkNull()) return null;
		try {
			return tClass.cast(url.openConnection());
		} catch (IOException | ClassCastException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return input stream or {@code null}
	 */
	public InputStream getInputStream() {
		try {
			return getConnection(URLManager.CONNECTION).getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get all content inside url
	 *
	 * @return content
	 */
	public String getContent() {
		StringBuilder builder = new StringBuilder();
		if (checkNull()) return builder.toString();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getConnection(CONNECTION).getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line).append("\n");
			}
		} catch (IOException ignored) {
		}
		
		return builder.toString();
	}
	
	/**
	 * get content length/size
	 *
	 * @return size of content, or {@code -1} if have some error
	 */
	public long getURLSize() {
		if (checkNull()) return -1L;
		return getConnection(CONNECTION).getContentLengthLong();
	}
	
	/**
	 * get file name <br>
	 * Example: https://xyz.com/re/as/fe/test.txt <br>
	 * the return will be "test.txt"
	 *
	 * @return file name
	 */
	public String getURLFilename() {
		if (checkNull()) return "";
		
		String filename = url.getPath();
		int k = filename.lastIndexOf("/");
		if (k == filename.length() - 1 || k < 0) return "";
		if (k >= 0) filename = filename.substring(k + 1);
		return filename;
	}
	
	/**
	 * @param method
	 * 		request method {@link RequestMethod}
	 * @param input
	 * 		can be {@code null}
	 * @param props
	 * 		request property e.g. headers({@link com.kamontat.constance.Headers})
	 * @return input stream from specify request, or {@code null} if cause some error
	 */
	public InputStream getSpecifyInputFromConnection(RequestMethod method, String input, RequestProp... props) {
		if (checkNull()) return null;
		
		HttpURLConnection c;
		if (getProtocol() == Protocol.HTTP) {
			c = getConnection(HTTP_CONNECTION);
		} else {
			c = getConnection(HTTPS_CONNECTION);
		}
		
		if (c == null) return null;
		try {
			c.setRequestMethod(method.toString());
			for (RequestProp p : props) {
				c.setRequestProperty(p.getKey(), p.getValue());
			}
			
			if (input != null) {
				c.setDoOutput(true);
				c.getOutputStream().write(input.getBytes());
			}
			
			return c.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get all header fields
	 *
	 * @return header in {@link Map} value, or {@code null}
	 */
	public Map<String, List<String>> getHeader() {
		if (checkNull()) return null;
		return getConnection(CONNECTION).getHeaderFields();
	}
	
	/**
	 * print all header to (DEBUG TOOL)
	 */
	public void printHeader() {
		if (checkNull()) {
			System.err.println("cause some null value");
			return;
		}
		Map header = getHeader();
		for (Object key : header.keySet()) {
			System.out.printf("%s: %s\n", key, header.get(key));
		}
	}
	
	@Override
	public String toString() {
		return "URLManager{" + "protocol=" + protocol + ", url=" + url + '}';
	}
	
	private boolean checkNull() {
		return url == null;
	}
}
