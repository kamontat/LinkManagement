package com.kamontat.link;

import com.kamontat.utilities.URLsUtil;

import java.net.URL;

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 - 6:24 PM
 */
public class UrlData {
	private URL link;
	
	private String name;
	private int size;
	
	public UrlData(URL link) {
		this.link = link;
	}
	
	public void fetchData() {
		URLsUtil.getUrl(link).getURLSize();
	}
}
