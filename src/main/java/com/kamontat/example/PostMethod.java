package com.kamontat.example;

import com.kamontat.constance.*;
import com.kamontat.utilities.RequestProp;
import com.kamontat.utilities.URLManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 - 11:53 PM
 */
public class PostMethod {
	public static void main(String[] args) {
		// test1: using github api 3 to test
		// get more useful project to convert at `https://github.com/kamontat/Conversion`
		String test = "**Today** is `monday`, *Tomorrow* is # Tuesday #";
		
		RequestProp p1 = new RequestProp(Headers.Content_Type, ContentType.get(Type.TEXT, ContentType.PLAIN));
		RequestProp p2 = new RequestProp(Headers.Content_Length, String.valueOf(test.length()));
		
		InputStream stream = URLManager.getUrl(Protocol.HTTPS, "api.github.com/markdown/raw").getSpecifyInputFromConnection(RequestMethod.POST, test, p1, p2);
		
		BufferedReader r = new BufferedReader(new InputStreamReader(stream));
		Optional<String> optional = r.lines().reduce((s, s2) -> s.concat("\n").concat(s2));
		String s = optional.orElse("");
		System.out.println(s);
	}
}
