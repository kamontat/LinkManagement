package com.kamontat.utilities;

import com.kamontat.constance.Headers;

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 - 9:46 PM
 */
public class RequestProp {
	private String key;
	private String value;
	
	public RequestProp(Headers header, String value) {
		this.key = String.valueOf(header);
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
}
