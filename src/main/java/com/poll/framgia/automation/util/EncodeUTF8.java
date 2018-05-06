package com.poll.framgia.automation.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class EncodeUTF8 {
	
	public String encodeUTF8(String inputString) throws Exception{
	Charset charsetE = Charset.forName("iso-8859-1");
	CharsetEncoder encoder = charsetE.newEncoder();

	//i believe from here to the end will probably stay the same, as per your posted example.
	Charset charsetD = Charset.forName("UTF-8");
	CharsetDecoder decoder = charsetD.newDecoder();

	ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(inputString));
	CharBuffer cbuf = decoder.decode(bbuf);
	final String result = cbuf.toString();
	System.out.println(result);
	return result;
	}
}
