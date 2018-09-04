package com.ykp.springbootdemo.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class QrUtil {
	
	private static Integer width = 200;
	
	private static Integer height = 200;
	
	private static String format = "png";
	
	public static void generateQrCode( String content, OutputStream outputStream ) throws Exception {
		Map<EncodeHintType, Object> config = new HashMap<>();
		config.put( EncodeHintType.CHARACTER_SET, "UTF-8" );
		config.put( EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M );
		config.put( EncodeHintType.MARGIN, 0 );
		BitMatrix bitMatrix = new MultiFormatWriter().encode( content, BarcodeFormat.QR_CODE, width, height, config );
		MatrixToImageWriter.writeToStream( bitMatrix, format, outputStream );
	}
	
}
