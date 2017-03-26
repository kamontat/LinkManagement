package com.kamontat.utilities;

import javax.swing.*;

/**
 * Read URL in bg(background), start running using method {@link #execute()}. <br>
 * And if you want to do something when reader completely use method {@link #done()}. <br>
 * <p>
 * The last thing, in this class you also can cancel the process by {@link #cancel(boolean)} and check is cancel by {@link #isCancelled()} <br>
 * And {@link #get()} method will return all byte that program read. ({@link URLReader#readAll()} - the result of this method)
 *
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 - 11:11 PM
 */
public class ReaderBackground extends SwingWorker<Long, Void> {
	private URLReader reader;
	
	public ReaderBackground(URLReader reader) {
		this.reader = reader;
	}
	
	/**
	 * call {@link URLReader#readAll()} method
	 *
	 * @return the number of byte that program read
	 * @throws Exception
	 * 		have some error
	 */
	@Override
	protected Long doInBackground() throws Exception {
		reader.setInput();
		int n = 0;
		do {
			n = (int) ((reader.read() * 100) / reader.getTotalByte());
			System.out.println(Thread.currentThread() + "reading: " + n);
			if (n < 0) break;
			setProgress(n);
		} while (n != -1);
		return reader.call();
	}
}
