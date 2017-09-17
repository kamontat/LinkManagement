package com.kamontat.utilities;

import javax.swing.*;
import java.beans.PropertyChangeListener;

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
	
	public URLReader getReader() {
		return reader;
	}
	
	/**
	 * call {@link URLReader#readAll()} method,
	 * you can handle progress by using property change {@link ReaderBackground#addPropertyChangeListener(PropertyChangeListener)}
	 * To use it, you need to know 3 thing.
	 * <ol>
	 * <li>propertyName = which is {@link URLReader#PROPERLY_KEY}</li>
	 * <li>oldValue = total byte that already downloaded (exclude current downloading)</li>
	 * <li>newValue = current loading byte</li>
	 * </ol>
	 * mean: if you want total download byte so far you just plus both oldValue and newValue together.
	 *
	 * @return the number of byte that program read
	 * @throws Exception
	 * 		have some error
	 */
	@Override
	protected Long doInBackground() throws Exception {
		reader.setInput();
		reader.setOutput();
		int n = 0;
		do {
			long save = reader.getBytesRead();
			int readByte = reader.read();
			firePropertyChange(URLReader.PROPERLY_KEY, save, readByte);
		} while (reader.getBytesRead() < reader.getTotalByte());
		return reader.call();
	}
}
