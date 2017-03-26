package com.kamontat.example;

import com.kamontat.utilities.ReaderBackground;
import com.kamontat.utilities.URLManager;
import com.kamontat.utilities.URLReader;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 - 10:36 PM
 */
public class Usage {
	
	public static void main(String[] args) throws IOException {
		/* ---------- test1: url-manager ---------- */
		URLManager manager = URLManager.getUrl("https://github.com/kamontat/Conversion/raw/v1.0.1/production/converter-1.0.1.jar");
		// toString: (URLManager{protocol=https, url=https://github.com/kamontat/Conversion/raw/v1.0.1/production/converter-1.0.1.jar})
		System.out.println(manager);
		// file name: converter-1.0.1.jar
		System.out.println(manager.getURLFilename());
		// file size: 26347
		System.out.println(manager.getURLSize());
		// header: null: [HTTP/1.1 200 OK]
		//         Access-Control-Allow-Origin: [*]
		//         . . .
		manager.printHeader();
		
		/* ---------- test2: url-reader ---------- */
		URLReader r = new URLReader(manager.getUrl(), Paths.get(".").toFile());
		// execute in background
		ReaderBackground bg = new ReaderBackground(r);
		// run
		bg.execute();
	}
}
