package org.ccil.cowan.tagsoup;

// Droidsafe Imports
import java.io.InputStream;
import java.io.Reader;

public interface AutoDetector {

	

	public Reader autoDetectingReader(InputStream i);

	}
