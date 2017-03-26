package com.kamontat.constance;

import java.util.*;

/**
 * Information from <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types">MIME-Type</a>
 *
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 - 8:58 PM
 */
public enum ContentType {
	/**
	 * <b>text</b> <br>
	 * This is the default value for textual files. Even if it really means unknown textual file, browsers assume they can display it.
	 */
	PLAIN,
	/**
	 * <b>text</b> <br>
	 * All HTML content should be served with this type. Alternative MIME types for XHTML, like application/xml+html, are mostly useless nowadays (HTML5 unified these formats).
	 */
	HTML,
	/**
	 * <b>text</b> <br>
	 * Any CSS files that have to be interpreted as such in a Web page must be of the text/css files. Often servers doesn't recognized files with the .css suffix as CSS files and send them with text/plain or application/octet-stream
	 */
	CSS,
	/**
	 * <b>text</b> <br>
	 */
	JAVASCRIPT,
	/**
	 * <b>images</b> <br>
	 */
	GIF,
	/**
	 * <b>images</b> <br>
	 * GIF images (lossless compression, superseded by PNG)
	 */
	PNG,
	/**
	 * <b>images</b> <br>
	 */
	JPEG,
	/**
	 * <b>images</b> <br>
	 */
	BMP,
	/**
	 * <b>images</b> <br>
	 */
	WEBP,
	/**
	 * <b>images</b> <br>
	 * SVG images (vector images).
	 */
	SVGPLUSXML,
	/**
	 * <b>images</b> <br>
	 * Many browsers support icon image types for favicons or similar. In particular, ICO images are supported in this context.
	 */
	X_ICON,
	/**
	 * <b>audio</b> <br>
	 */
	MIDI,
	/**
	 * <b>audio</b> <br>
	 */
	MPEG,
	/**
	 * <b>audio</b> <br>
	 * An audio file in the WAVE container format. The PCM audio codec (WAVE codec "1") is often supported, but other codecs have more limited support (if any).
	 */
	WAV,
	/**
	 * <b>audio/video</b> <br>
	 * <p>
	 * <b>Audio</b>: An audio file in the WebM container format. Vorbis and Opus are the most common audio codecs.
	 * </p>
	 * <p>
	 * <b>Video</b>: A video file, possibly with audio, in the WebM container format. VP8 and VP9 are the most common video codecs used within it; Vorbis and Opus the most common audio codecs.
	 * </p>
	 */
	WEBM,
	/**
	 * <b>audio/video/application</b> <br>
	 * <p>
	 * <b>Audio</b>: An audio file in the OGG container format. Vorbis is the most common audio codec used in such a container.
	 * </p>
	 * <p>
	 * <b>Video</b>: A video file, possibly with audio, in the OGG container format. Theora is the usual video codec used within it; Vorbis is the usual audio codec.
	 * </p>
	 */
	OGG,
	/**
	 * <b>application</b> <br>
	 * This is the default value for a binary files. As it really means unknown binary file, browsers usually don't automatically execute it, or even ask if it should be execute. They treat it as if the Content-Disposition header was set with the value attachment and propose a 'Save As' file.
	 */
	OCTET_STREAM,
	/**
	 * <b>application</b> <br>
	 */
	PKCS12,
	/**
	 * <b>application</b> <br>
	 */
	VNDDOTMSPOWERPOINT,
	/**
	 * <b>application</b> <br>
	 */
	XMLPLUSHTML,
	/**
	 * <b>application</b> <br>
	 */
	XML,
	/**
	 * <b>application</b> <br>
	 */
	PDF,
	/**
	 * <b>application</b> <br>
	 */
	JAVA_ARCHIVE;
	
	public static String get(Type t, ContentType c) {
		// change _ -> -
		// change $ -> .
		return t.name().toLowerCase(Locale.ENGLISH) + "/" + c.name().toLowerCase(Locale.ENGLISH).replace("_", "-").replace("dot", ".").replace("plus", "+");
	}
}
