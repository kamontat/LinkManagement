package com.kamontat.constance;

import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 - 8:56 PM
 */
public enum RequestMethod {
	/**
	 * The HTTP CONNECT method method starts two-way communications with the requested resource. It can be used to open a tunnel.
	 * <p>
	 * For example, the CONNECT method can be used to access websites that use SSL (HTTPS). The client asks an HTTP Proxy server to tunnel the TCP connection to the desired destination. The server then proceeds to make the connection on behalf of the client. Once the connection has been established by the server, the Proxy server continues to proxy the TCP stream to and from the client. <br>
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/CONNECT">learn more</a>
	 */
	CONNECT,
	/**
	 * The HTTP GET method requests a representation of the specified resource. Requests using GET should only retrieve data. <br>
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">learn more</a>
	 */
	GET,
	/**
	 * The HTTP POST method sends data to the server. The type of the body of the request is indicated by the Content-Type header.
	 * <p>
	 * The difference between PUT and POST is that PUT is idempotent: calling it once or several times successively has the same effect (that is no side effect), where successive identical POST may have additional effects, like passing an order several times.
	 * <p>
	 * A POST request is typically sent via an HTML form and results in a change on the server. In this case, the content type is selected by putting the adequate string in the enctype attribute of the &lt;form&gt; element or the formenctype attribute of the &lt;input&gt; or &lt;button&gt; elements:
	 * <ul>
	 * <li>application/x-www-form-urlencoded: the values are encoded in key-value tuples separated by '&amp;', with a '=' between the key and the value. Non-alphanumeric characters are percent encoded: this is the reason why this type is not suitable to use with binary data (use application/form-data instead)</li>
	 * <li>application/form-data</li>
	 * <li>text/plain</li>
	 * </ul>
	 * <p>
	 * When the POST request is sent via another method that an HTML form, like via an XMLHttpRequest, the body can take any type. As described in the HTTP 1.1 specification, POST is designed to allow a uniform method to cover the following functions:
	 * <ul>
	 * <li>Annotation of existing resources</li>
	 * <li>Posting a message to a bulletin board, newsgroup, mailing list, or similar group of articles;</li>
	 * <li>Providing a block of data, such as the result of submitting a form, to a data-handling process;</li>
	 * <li>Extending a database through an append operation.</li>
	 * </ul>
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST">learn more</a>
	 */
	POST,
	/**
	 * The HTTP PUT request method creates a new resource or replaces a representation of the target resource with the request payload.
	 * <p>
	 * The difference between PUT and POST is that PUT is idempotent: calling it once or several times successively has the same effect (that is no side effect), where successive identical POST may have additional effects, like passing an order several times. <br>
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT">learn more</a>
	 */
	PUT,
	/**
	 * The HTTP PATCH request method applies partial modifications to a resource.
	 * <p>
	 * The HTTP PUT method is already defined to overwrite a resource with a complete new body, and for the POST method there is no standard way to discover patch format support. Unlike PUT, but like POST, PATCH is not idempotent, meaning successive identical patch requests will have different effects.
	 * <p>
	 * To find out whether a server supports PATCH, a server can advertise its support by adding it to the list in the Allow or Access-Control-Allow-Methods (for CORS) response headers.
	 * <p>
	 * Another (implicit) indication that PATCH is allowed, is the presence of the Accept-Patch header, which specifies the patch document formats accepted by the server. <br>
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PATCH">learn more</a>
	 */
	PATCH,
	/**
	 * The HTTP HEAD method requests the headers that are returned if the specified resource would be requested with an HTTP GET method. Such a request can be done before deciding to download a large resource to save bandwidth, for example.
	 * <p>
	 * A response to a HEAD method should not have a body. If so, it must be ignored. Even so, entity headers describing the content of the body, like Content-Length may be included in the response. They don't relate to the body of the HEAD response, which should be empty, but to the body of similar request using the GET method would have returned as a response.
	 * <p>
	 * If the result of a HEAD request shows that a cached resource after a GET request is now outdated, the cache is invalidated, even if no GET request has been made. <br>
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/HEAD">learn more</a>
	 */
	HEAD,
	/**
	 * The HTTP OPTIONS method is used to describe the communication options for the target resource. The client can specify a specific URL for the OPTIONS method, or an asterisk (*) to refer to the entire server. <br>
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/OPTIONS">learn more</a>
	 */
	OPTIONS,
	/**
	 * The HTTP DELETE request method deletes the specified resource. <br>
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE">learn more</a>
	 */
	DELETE;
	
	@Override
	public String toString() {
		return this.name().toUpperCase(Locale.ENGLISH);
	}
}
