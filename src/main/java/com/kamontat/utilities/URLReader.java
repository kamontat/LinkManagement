package com.kamontat.utilities;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

// This is a decorator (wrapper) for an InputStream that you can monitor readAll progress.
//import javax.swing.ProgressMonitorInputStream;

/**
 * Download a URL to a File.
 *
 * @author James Brucker
 */
public class URLReader implements Callable<Long> {
	private URL url;
	private URLConnection connection;
	private InputStream inStream;
	public static int BUFFER_SIZE = 32 * 1024; // default buffer size for readAll
	
	private long bytesRead; // number of bytes read so far
	/**
	 * length/size of URL stream.
	 */
	private long urlSize;
	/**
	 * The output file.
	 */
	private File outfile;
	/**
	 * output file to write to
	 */
	private FileOutputStream outStream;
	
	/**
	 * Initialize a new URL Reader. <br>
	 * <b>precondition</b>: url is a valid URL, outfile is a valid writable file or directory.
	 *
	 * @param url
	 * 		is the URL to readAll from
	 * @param outfile
	 * 		is a File output to write output to. If it is a writable directory
	 * 		then a file is created in that directory with same name as the download resource.
	 * @throws IOException
	 * 		if URLConnection fails,
	 * @throws FileNotFoundException
	 * 		if outfile not found or cannot be written to
	 */
	public URLReader(URL url, File outfile) throws IOException {
		if (url == null) throw new IllegalArgumentException("url cannot be null");
		if (outfile == null) throw new IllegalArgumentException("output file cannot be null");
		if (!outfile.exists() && !outfile.createNewFile()) {
			throw new IllegalArgumentException("output file " + outfile.getName() + " is not found");
		}
		if (!outfile.canWrite())
			throw new IllegalArgumentException("output file " + outfile.getName() + " is not writable");
		
		this.url = url;
		this.bytesRead = 0;
		this.urlSize = URLManager.getUrl(url).getURLSize();
		if (outfile.isDirectory()) {
			String filename = URLManager.getUrl(url).getURLFilename();
			outfile = new File(outfile, filename);
		}
		this.outfile = outfile;
		// don't open connection yet -- the server might close it before we run()
		// create output writer
		outStream = new FileOutputStream(outfile); // "rwd" mode?
	}
	
	/**
	 * Get the number of bytes downloaded so far.
	 *
	 * @return number of bytes downloaded from URL so far.
	 */
	public long getBytesRead() {
		return bytesRead;
	}
	
	public long getTotalByte() {
		return urlSize;
	}
	
	/**
	 * Read the URL connection and save bytes to output file.
	 * This method will block until the entire URL content is readAll.
	 * While reading, the bytesRead object attribute is regularly updated.
	 *
	 * @return the number of bytes actually readAll
	 * @throws IOException
	 * 		when IO error cause
	 */
	public long readAll() throws IOException {
		bytesRead = 0;
		byte[] buff = new byte[BUFFER_SIZE];
		try {
			setInput();
			if (inStream == null) return bytesRead;
			do {
				int n = read();
				if (n < 0) break;
				bytesRead += n;
			} while (true);
		} finally {
			if (inStream != null) inStream.close();
			inStream = null;
			outStream.close();
			outStream = null;
		}
		return bytesRead;
	}
	
	/**
	 * <b>precondition</b>: you need to call {@link #setInput()} first. <br>
	 * work like {@link InputStream#read(byte[])} with {@link #BUFFER_SIZE}, but add more action is save the result to output stream.
	 *
	 * @return size that read. or {@code null} if cause error
	 */
	public int read() {
		byte[] buff = new byte[BUFFER_SIZE];
		if (inStream == null) return -1;
		try {
			int n = inStream.read(buff);
			if (n < 0) return n;
			outStream.write(buff, 0, n);
			// update byte read
			bytesRead += n;
			return n;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void setInput() {
		if (inStream == null) inStream = URLManager.getUrl(url).getInputStream();
		bytesRead = 0;
	}
	
	/**
	 * Get actual name of the output file.
	 *
	 * @return string name with path of the output file
	 */
	public String getOutputFile() {
		return outfile.getPath();
	}
	
	/**
	 * start the URL reader. and close connection while finish
	 */
	@Override
	public Long call() throws Exception {
		return readAll();
	}
}
